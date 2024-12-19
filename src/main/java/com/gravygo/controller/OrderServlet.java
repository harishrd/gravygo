package com.gravygo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gravygo.dbUtils.DBUtils;
import com.gravygo.model.Cart;
import com.gravygo.model.CartItem;
import com.gravygo.model.User;

@SuppressWarnings("serial")
@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	
	// USE DAOIMPL FILIES TO INTERACT WITH THE DATABASE
	// USE DAOIMPL FILIES TO INTERACT WITH THE DATABASE
	// USE DAOIMPL FILIES TO INTERACT WITH THE DATABASE

	
	private static final String INSERT_INTO_ORDERTABLE = "INSERT INTO `orderTable`(`userId`, `restaurantId`, `totalAmount`, `status`, `paymentMode`) VALUES(?,?,?,?,?)";
    private static final String INSERT_INTO_ORDER_ITEM = "INSERT INTO `orderItem`(`orderId`, `menuId`, `quantity`, `subTotal`) VALUES(?,?,?,?)";
    private static final String INSERT_INTO_ORDER_HISTORY = "INSERT INTO `orderHistory`(`orderId`, `userId`, `totalAmount`, `status`) VALUES(?,?,?,?)";

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        int userId = user.getUserId();
        int restaurantId = (int) session.getAttribute("restaurantId");
        Cart cart = (Cart) session.getAttribute("cart");
        float totalAmount = cart.getTotal();
        String paymentMode = req.getParameter("paymentMode");
        String status = "Delivered";

        Connection con = null;
        try {
            con = DBUtils.myConnect();
            con.setAutoCommit(false); // Begin transaction

            // Insert into OrderTable and get generated orderId
            PreparedStatement pstmt = con.prepareStatement(INSERT_INTO_ORDERTABLE, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, restaurantId);
            pstmt.setFloat(3, totalAmount);
            pstmt.setString(4, status);
            pstmt.setString(5, paymentMode);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
            pstmt.close();
            rs.close();

            // Insert into OrderItem for each cart item
            for (CartItem item : cart.getItems().values()) {
                PreparedStatement pstmtOrderItem = con.prepareStatement(INSERT_INTO_ORDER_ITEM);
                pstmtOrderItem.setInt(1, orderId);
                pstmtOrderItem.setInt(2, item.getItemId());
                pstmtOrderItem.setInt(3, item.getQuantity());
                pstmtOrderItem.setFloat(4, item.getSubTotal());
                pstmtOrderItem.executeUpdate();
                pstmtOrderItem.close();
            }

            // Insert into OrderHistory
            PreparedStatement pstmtOrderHistory = con.prepareStatement(INSERT_INTO_ORDER_HISTORY);
            pstmtOrderHistory.setInt(1, orderId);
            pstmtOrderHistory.setInt(2, userId);
            pstmtOrderHistory.setFloat(3, totalAmount);
            pstmtOrderHistory.setString(4, status);
            pstmtOrderHistory.executeUpdate();
            pstmtOrderHistory.close();

            session.setAttribute("orderId", orderId);
            con.commit(); // Commit the transaction
            resp.sendRedirect("order-conformation.jsp");
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback the transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        } finally {
            if (con != null) {
                try {
                    con.close(); // Close the connection
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}