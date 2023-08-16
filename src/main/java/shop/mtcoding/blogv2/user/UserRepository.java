package shop.mtcoding.blogv2.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 직접 쿼리를 짜는 법
     // executeQuery
     @Query(value = "select * from user_tb where id = :id", nativeQuery = true)
     User mFindById(@Param("id") Integer id);
 
     // executeQuery
     @Query(value = "select * from user_tb where username = :username", nativeQuery = true)
     User findByUsername(@Param("username") String username);

    // @Modifying <-- excuteQuery()를 실행해주는 어노테이션
    @Modifying // executeUpdate
    @Query(value = "insert into user_tb(created_at, email, password, username) values(now(), :email, :password, :username)", nativeQuery = true)
    void mSave(@Param("username") String username, @Param("password") String password, @Param("email") String email);
} 