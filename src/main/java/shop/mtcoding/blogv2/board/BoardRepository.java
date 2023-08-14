package shop.mtcoding.blogv2.board;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * JpaRepository는 Spring Data JPA에서 제공하는 인터페이스로,
 * 기본적인 CRUD (Create, Read, Update, Delete) 메서드를 자동으로 생성해줍니다.
 */

 // 스프링이 실행될 때, BoardRepository의 구현체가 Ioc 컨테이너에 생선된다.
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
