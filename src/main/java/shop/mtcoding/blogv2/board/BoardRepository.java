package shop.mtcoding.blogv2.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
 * JpaRepository는 Spring Data JPA에서 제공하는 인터페이스로,
 * 기본적인 CRUD (Create, Read, Update, Delete) 메서드를 자동으로 생성해줍니다.
 */

// 스프링이 실행될 때, BoardRepository의 구현체가 Ioc 컨테이너에 생선된다.
public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 아래 오리지날 쿼리를 JPQL로 간단히 @Query("select b from Board b join b.user") 이렇게 만들수 있다.
    // select id, title, content, user_id, created_at from board_tb b inner join user_tb u on b.user_id = u.id; 
    // fetch를 붙여야 *를 하여 전체를 조회한다.
    @Query("select b from Board b join fetch b.user")  // JPA공식이다
    List<Board> mFindAll();
 
}
