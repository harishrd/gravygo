document.addEventListener('DOMContentLoaded', function () {
    const errorMessage = document.querySelector('.error-message');
    if (errorMessage) {
        setTimeout(function () {
            errorMessage.style.display = 'none';
        }, 5000);
    }
});
