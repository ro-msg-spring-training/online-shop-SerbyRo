package ro.msg.learning.shop.service.exceptions;

public class LocationNotFound extends RuntimeException{
    public LocationNotFound(){

    }
    public LocationNotFound(String message) {
        super(message);
    }

    public LocationNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
