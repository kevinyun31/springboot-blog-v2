package shop.mtcoding.blogv2._core.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.blogv2._core.util.Script;

// 클라이언트에게 DS에서 보낼꺼니깐 Rest를 써서 뷰로 전송한다.
@RestControllerAdvice
public class MyExceptionHandler {
    
    @ExceptionHandler(RuntimeException.class)
    public String error(RuntimeException e){
       return Script.back(e.getMessage());
    }
}
