package ro.msg.learning.shop.service.exceptions;

public class OrderNotCompleted extends RuntimeException{
    public OrderNotCompleted(){

    }
    public OrderNotCompleted(String message) {
        super(message);
    }

    public OrderNotCompleted(String message, Throwable cause) {
        super(message, cause);
    }
}
