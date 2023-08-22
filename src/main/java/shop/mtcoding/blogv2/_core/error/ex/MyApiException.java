package shop.mtcoding.blogv2._core.error.ex;

public class MyApiException extends RuntimeException { // Json 요청
    public MyApiException(String message) {
        super(message);
    }

} 
