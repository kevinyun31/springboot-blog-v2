package shop.mtcoding.blogv2.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyRequest {

    @Getter
    @Setter
    public static class SaveDTO {
        private Integer boardId;
        private String comment; // 댓글 내용을 저장하는 변수
    }

}
