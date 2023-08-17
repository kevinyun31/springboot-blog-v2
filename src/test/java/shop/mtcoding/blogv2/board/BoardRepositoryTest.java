package shop.mtcoding.blogv2.board;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv2.user.User;

@DataJpaTest // 모든 Repository, EntityManager
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findAll_paging_test() throws JsonProcessingException {
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
        Page<Board> boardPG = boardRepository.findAll(pageable);
        ObjectMapper om = new ObjectMapper();

        // ObjectMapper는 boardPG객체의 getter를 호출하면서 json을 만든다.
        String json = om.writeValueAsString(boardPG); // 자바객체를 JSON으로 변환
        System.out.println(json);
    }

    @Test
    public void findById_test() {
        Optional<Board> boardOP = boardRepository.findById(6);
        if (boardOP.isPresent()) {
            System.out.println("테스트 : board가 있습니다");
        }
    }

    @Test
    public void mFindAll_test() {
        boardRepository.mFindAll();
    }

    @Test
    public void findAll_test() {
        System.out.println("조회 직전");
        List<Board> boarList = boardRepository.findAll();
        System.out.println("조회 후 : LAZY");
        // 행 : 5개 -> 객체 : 5개
        // 각행 : Board (id=1, title=제목1, content=내용, created_at=날짜, User(id=1))
        System.out.println(boarList.get(0).getId()); // 1번 (조회 X)
        System.out.println(boarList.get(0).getUser().getId()); // 1번 (조회 X)

        // LAZY Loading - 지연로딩
        // 연관된 객체에 null을 참조하려고 하면 조회가 일어난다
        // null -> ssar LAZY 되어 있어서 없어야 하는데 하이버네이트가 없으니 알아서 찾아서 조회힌다
        System.out.println(boarList.get(0).getUser().getUsername()); // 1번 (조회 O)
    }

    @Test
    public void save_test() {
        // 비영속 객체
        Board board = Board.builder()
                .title("제목6")
                .content("내용6")
                .user(User.builder().id(1).build())
                .build();

        // 영속 객체
        boardRepository.save(board); // insert 자동으로 실행됨
        // 디비데이터와 동기화됨
        System.out.println(board.getId());
    }
}
