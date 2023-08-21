package shop.mtcoding.blogv2.board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2.board.BoardRequest.UpdateDTO;
import shop.mtcoding.blogv2.user.User;

/*
 *  1. 비지니스 로직 처리(핵심로직)
 *  2. 트랜잭션 관리
 *  3. 예외처리 (2단계)
 *  4. DTO 변환 (2단계)
 */

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // @Transactional 어노테이션은 트랜잭션을 적용하는 역할을 합니다.
    // 이 메서드가 실행되는 동안의 데이터베이스 작업들이 하나의 트랜잭션으로 묶이게 됩니다.
    @Transactional
    public void 글쓰기(BoardRequest.SaveDTO saveDTO, int sessionUserId) {
        // Board 객체 생성 및 데이터 설정
        Board board = Board.builder()
                .title(saveDTO.getTitle())
                .content(saveDTO.getContent())
                .user(User.builder().id(sessionUserId).build()) // 게시글 작성자 설정
                .build();

        // 생성한 Board 객체를 Repository를 통해 데이터베이스에 저장
        boardRepository.save(board);
    }

    public Page<Board> 게시글목록보기(Integer page) {

        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        return boardRepository.findAll(pageable);

    }

    public Board 상세보기(Integer id) {
        // board 만 가져오면 된다!!
        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isPresent()) {
            return boardOP.get();
        } else {
            throw new RuntimeException(id + "는 찾을 수 없습니다");
        }
    }

    public Board 게시글수정보기(Integer id) {
        // board 만 가져오면 된다!!
        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isPresent()) {
            return boardOP.get();
        } else {
            throw new RuntimeException(id + "는 찾을 수 없습니다");
        }
    }

    @Transactional
    public void 게시글수정하기(Integer id, UpdateDTO updateDTO) {
        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isPresent()) {
            Board board = boardOP.get();
            board.setTitle(updateDTO.getTitle());
            board.setContent(updateDTO.getContent());
        } else {
            throw new RuntimeException(id + "는 찾을 수 없습니다");
        }
    } // flush (더티체킹)

    @Transactional
    public void 삭제하기(Integer id) {
        try {
            boardRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(id + "를 찾을 수 없어요");
        }
    }
}
