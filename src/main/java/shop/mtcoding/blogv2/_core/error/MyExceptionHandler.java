package shop.mtcoding.blogv2._core.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.blogv2._core.error.ex.MyApiException;
import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2._core.util.Script;

// 클라이언트에게 DS에서 보낼꺼니깐 Rest를 써서 데이터로 전송한다.
@RestControllerAdvice
public class MyExceptionHandler {
    
    @ExceptionHandler(MyException.class)
    public String error(MyException e){
       return Script.back(e.getMessage());
    }

    @ExceptionHandler(MyApiException.class)
    public ApiUtil<String> error(MyApiException e){
       return new ApiUtil<String>(false, e.getMessage());
    }

}
