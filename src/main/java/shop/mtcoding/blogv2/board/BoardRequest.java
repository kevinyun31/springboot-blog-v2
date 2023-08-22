package shop.mtcoding.blogv2.board;

import lombok.Getter;
import lombok.Setter;

public class BoardRequest {

    @Getter
    @Setter
    // SaveDTO 클래스는 BoardRequest 클래스 내에서 정의된 정적(inner) 클래스입니다.
    // public static코드 때문에 BoardRequest.SaveDTO 으로 불러 쓸수 있다.
    public static class SaveDTO {
        private String title; // 글 제목을 저장하는 변수
        private String content; // 글 내용을 저장하는 변수
    }

    @Getter
    @Setter
    public static class UpdateDTO {
        private String title; // 글 제목을 수정하는 변수
        private String content; // 글 내용을 수정하는 변수
    }

} 
