package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ptit.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByStatusTrue();
    @Query("select u from User u where u.email = ?1 and u.password = ?2")
    User findWithEmailAndPassword(String email,String password);
    @Query("select u from User u where u.email = ?1")
    User findWithEmail(String email);
}
