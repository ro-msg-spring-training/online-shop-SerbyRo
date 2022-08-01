package ro.msg.learning.shop.service.exceptions;

public class ProductException extends Exception{
    public ProductException(){

    }
    public ProductException(String message) {
        super(message);
    }

    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
