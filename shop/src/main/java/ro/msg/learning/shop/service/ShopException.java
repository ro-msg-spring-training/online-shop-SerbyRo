package ro.msg.learning.shop.service;

public class ShopException extends Exception{
    public ShopException(){

    }
    public ShopException(String message) {
        super(message);
    }

    public ShopException(String message, Throwable cause) {
        super(message, cause);
    }
}
