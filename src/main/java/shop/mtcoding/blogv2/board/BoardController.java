package shop.mtcoding.blogv2.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.util.Script;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    // 게시글 수정 요청 응답
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO updateDTO) { // 1.PathVarible 값 받기
        // where데이터, body데이터, session값
        boardService.게시글수정하기(id, updateDTO);
        return "redirect:/board/" + id;
    }

    // 게시글 수정 화면 호출
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, Model model) {
        Board board = boardService.상세보기(id);
        model.addAttribute("board", board); // request에 담는 것과 동일
        return "board/updateForm";
    }

    // 게시글 삭제
    @PostMapping("/board/{id}/delete")
    public @ResponseBody String delete(@PathVariable Integer id) {
        // 인증체크
        boardService.삭제하기(id);
        return Script.href("/");
    }

    // 게시글 상세보기 화면 호출
    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Board board = boardService.상세보기(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    // 게시글 상세보기 화면 호출 테스트
    @GetMapping("/test/board/{id}")
    public @ResponseBody Board testDetail(@PathVariable Integer id) {
        Board board = boardRepository.mFindByIdJoinRepliesInUser(id).get();
        return board;
    }

    // localhost:8080?page=1&keyword=바나나
    // 게시글목록 화면 호출
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "0") Integer page, HttpServletRequest request) {
        Page<Board> boardPG = boardService.게시글목록보기(page);
        request.setAttribute("boardPG", boardPG);
        request.setAttribute("prevPage", boardPG.getNumber() - 1);
        request.setAttribute("nextPage", boardPG.getNumber() + 1);

        return "index";
    }

    // 게시글목록 화면 호출 테스트
    @GetMapping("/test")
    public @ResponseBody Page<Board> test(@RequestParam(defaultValue = "0") Integer page, HttpServletRequest request) {
        Page<Board> boardPG = boardService.게시글목록보기(page);
        return boardPG; // ViewResolver (X), MessageConverter (O) -> json 직렬화
    }

    // 글쓰기 화면 호출
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    // 1. 데이터 받기 (V)
    // 2. 인증체크 (:TODO)
    // 3. 유효성 검사 (:TODO)
    // 4. 핵심로직 호출 (서비스의 메서드 이용)
    // 5. view or data 응답
    // 글쓰기 요청 응답
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) { // 1. 데이터 받기
        boardService.글쓰기(saveDTO, 1);
        return "redirect:/";
    }

} // class 
