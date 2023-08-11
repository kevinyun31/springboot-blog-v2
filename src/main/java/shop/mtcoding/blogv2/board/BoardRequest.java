package shop.mtcoding.blogv2.board;

import lombok.Getter;
import lombok.Setter;

public class BoardRequest {
      
    @Getter @Setter
    public static class SaveDTO{  // public static코드 문에  BoardRequest.SaveDTO 으로 불러 쓸수 있다.
     private String title;
     private String content;
    }

    
}
