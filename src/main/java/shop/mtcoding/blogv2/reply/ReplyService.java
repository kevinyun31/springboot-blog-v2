package shop.mtcoding.blogv2.reply;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2.board.Board;
import shop.mtcoding.blogv2.board.BoardRepository;
import shop.mtcoding.blogv2.reply.ReplyRequest.SaveDTO;
import shop.mtcoding.blogv2.user.User;

@Service
public class ReplyService {

    // ReplyRepository 인터페이스를 스프링 컨테이너에서 자동으로 주입받습니다.
    // 이를 통해 댓글 데이터를 데이터베이스에 저장하거나 조회하는데 사용할 수 있습니다.
    @Autowired
    private ReplyRepository replyRepository;

    // BoardRepository 인터페이스를 스프링 컨테이너에서 주입받습니다.
    // 이를 통해 게시판 데이터를 데이터베이스에서 조회하는데 사용할 수 있습니다.
    @Autowired
    private BoardRepository boardRepository;

    
    @Transactional
    public void 댓글쓰기(SaveDTO saveDTO, Integer sessionId) {
        // insert into reply_tb(comment, board_id, user_id) values(?,?,?)

        Board board = Board.builder().id(saveDTO.getBoardId()).build();
        User user = User.builder().id(sessionId).build();

        Reply reply = Reply.builder()
                .comment(saveDTO.getComment())
                .board(board)
                .user(user)
                .build();
        replyRepository.save(reply); // entity : Reply 객체
    }

    // @Transactional
    // public void 댓글삭제(Integer id, Integer sessionUserId) {
    // // 권한체크
    // Optional<Reply> reply = replyRepository.findById(id);
    // if (replyOP.getUser) {

    // }

}
