package com.gravygo.model;

import java.util.HashMap;
import java.util.Map;

public class Cart 
{
	private Map<Integer, CartItem> items;
	private float total;
	
	public Cart()
	{
		items = new HashMap<>();
	}

	public Map<Integer, CartItem> getItems()
	{
		return items;	
	}
	
	public float getTotal() {
		return total;
	}

//	public void setTotal(int total) {
//		this.total = total;
//	}

	public void addItem(CartItem item)
	{
		int itemId = item.getItemId();
		if (items.containsKey(itemId)) 
		{
			CartItem existingItem = items.get(itemId);
			total -= existingItem.getSubTotal();
			existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
			existingItem.setSubTotal(existingItem.getQuantity() * existingItem.getPrice());
			total += existingItem.getSubTotal();
			items.put(itemId, existingItem);
		}
		else
		{
			total += item.getSubTotal();
			items.put(itemId, item);
		}
	}
	
	public void updateItem(int itemId, int quantity)
	{
		if (items.containsKey(itemId))
		{
			if (quantity <= 0) {
				total -= items.get(itemId).getSubTotal();
				items.remove(itemId);			
			}
			else {
				CartItem existingItem = items.get(itemId);
				total -= existingItem.getSubTotal();
				
				existingItem.setQuantity(quantity);
				existingItem.setSubTotal(quantity * existingItem.getPrice());
				total += existingItem.getSubTotal();
			}			
		}	
	}
	
	public void removeItem(int itemId)
	{
		total -= items.get(itemId).getSubTotal();
		items.remove(itemId);
	}
	
	
	public void clearCart()
	{
		total = 0;
		items.clear();
	}
}












