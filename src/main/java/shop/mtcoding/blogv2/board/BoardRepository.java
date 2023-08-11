package shop.mtcoding.blogv2.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
 *  JpaRepository <-- update 빼고 기본 CRUD를 다 만들어준다.
 * save(), findById(), findAll(), count(), deleteById()
 */
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
