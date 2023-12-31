package shop.mtcoding.blogv2.reply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.error.ex.MyApiException;
import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2.user.User;

@Controller
public class ReplyController {

  @Autowired
  private ReplyService replyService;

  @Autowired
  private HttpSession session;

  // 댓글 삭제
  @DeleteMapping("/api/reply/{id}/delete")  
  // json으로 받아라고 헤더에 @ResponseBody, @RequestBody를 넣어서 json 객체로 보내고 받고 가능 해진다.
  public @ResponseBody ApiUtil<String> delete(@PathVariable Integer id) {

    // 현재 세션에서 "sessionUser"라는 이름으로 저장된 사용자 정보를 가져옵니다.
    // 이 정보는 사용자가 로그인 상태인 경우 세션에 저장된 사용자 객체 입니다.
    // 1. 인증체크
    User sessionUser = (User) session.getAttribute("sessionUser");
    if (sessionUser == null) {
      throw new MyApiException("인증되지 않았습니다");
    }

    // replyService라는 댓글 관련 비즈니스 로직을 처리하는 서비스 객체의 댓글삭제 메소드를 호출합니다.
    // id는 삭제할 댓글의 ID이며, sessionUser.getId()는 현재 로그인한 사용자의 ID를 의미합니다.
    // 2. 핵심로직
    replyService.댓글삭제(id, sessionUser.getId());

    // 3. 응답
    return new ApiUtil<String>(true, "댓글삭제 완료");
  }

  // 댓글 쓰기
  @PostMapping("/api/reply/save")
  // json으로 받아라고 헤더에 @ResponseBody, @RequestBody를 넣어서 json 객체로 보내고 받고 가능 해진다.
  public @ResponseBody ApiUtil<String> save(@RequestBody ReplyRequest.SaveDTO saveDTO) {
    User sessionUser = (User) session.getAttribute("sessionUser");
    if (sessionUser == null) {
      // return new ApiUtil<String>(false, "인증이 되지 않았습니다");
      throw new MyApiException("인증되지 않았습니다");
    }
    replyService.댓글쓰기(saveDTO, sessionUser.getId());
    return new ApiUtil<String>(true, "댓글쓰기 성공");
  }

}
