package shop.mtcoding.blogv2._core.error.ex;

public class MyException extends RuntimeException{ // 일반 요청 
    public MyException(String message) {
        super(message);
    }
}
