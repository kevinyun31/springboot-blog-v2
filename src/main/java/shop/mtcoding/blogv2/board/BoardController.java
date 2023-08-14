package shop.mtcoding.blogv2.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blogv2.user.User;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    // 1. 데이터 받기 (V)
    // 2. 인증체크 (:TODO)
    // 3. 유효성 검사 (:TODO)
    // 4. 핵심로직 호출 (서비스의 메서드 이용)
    // 5. view or data 응답
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) { // 1. 데이터 받기
        // 테스트 후엔 주석처리 하기
        // System.out.println("title : " +saveDTO.getTitle());
        // System.out.println("content : " +saveDTO.getContent());

        // 2. 인증체크 (:TODO)

        // 3. 유효성 검사 (:TODO)

        // 4. 핵심로직 호출 (서비스의 메서드 이용)

        boardService.글쓰기(saveDTO, 1);

        return "redirect:/";
    }

} // class
