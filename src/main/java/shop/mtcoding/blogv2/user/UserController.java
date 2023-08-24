package shop.mtcoding.blogv2.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.error.ex.MyApiException;
import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2._core.util.Script;

@Controller
public class UserController {

    @Autowired // DI
    private UserService userService;

    @Autowired
    private HttpSession session;

    // 브라우저 GET /logout 요청을 함, (request1)
    // 서버는 / 주소를 응답의 헤더에 담음 (Location), 상태코드 302
    // 브라우저는 GET / 로 재용청을 함. (request2)
    // 최종적으로 index 페이지 응답받고 렌더링함
    // 로그아웃 요청 응답
    @GetMapping("/logout")
    public String logout() {
        session.invalidate(); // 현재 세션을 무효화하여 사용자의 로그인 세션을 종료합니다. 사용자는 로그아웃 상태가 됩니다.
        return "redirect:/"; // 요청이 두번 일어남. 3백번대 응답 받고 다시 재요청 한다.
    }

    // C - V
    // 회원가입 화면 호출
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }
    
    // 회원가입 중복 체크
    @GetMapping("/api/check")
    public @ResponseBody ApiUtil<String> check(String username){
        // 유효성검사
        if(username.isBlank()){
            throw new MyApiException("유저네임을 입력하세요.");
        }
        
        // 핵심로직
        userService.중복체크(username);
       
        // 응답
        return new ApiUtil<String>(true, "중복체크 완료");
        
    }

    // M - V - C
    // 회원가입 요청 응답
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        // System.out.println(joinDTO.getPic().getOriginalFilename());
        // System.out.println(joinDTO.getPic().getSize());
        // System.out.println(joinDTO.getPic().getContentType());

        userService.회원가입(joinDTO);
        return "user/loginForm"; // persist 초기화
    } 

    // 로그인화면 호출
    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    // 로그인 요청 응답
    @PostMapping("/login")
    public @ResponseBody String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/");
    }

    // // session = 서버측 저장소 = 스테이트풀 서버
    // @PostMapping("/login")
    // public @ResponseBody String login(UserRequest.LoginDTO loginDTO) {

    // User sessionUser = userService.로그인(loginDTO);
    // if (sessionUser == null) {
    // return Script.back("로그인 실패");
    // }
    // session.setAttribute("sessionUser", sessionUser);
    // return Script.href("/");
    // }

    // 회원수정 화면 호출
    @GetMapping("/user/updateForm")
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.회원정보보기(sessionUser.getId());
        request.setAttribute("user", user);
        return "user/updateForm";
    }

    // 회원수정 요청 응답
    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO updateDTO) {
        // 1. 회원수정 (서비스)
        // 2. 세션동기화
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.회원수정(updateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return "redirect:/";
    }
}