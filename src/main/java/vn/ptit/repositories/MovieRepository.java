package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ptit.entities.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findByStatusTrue();
    @Query("select count(m.id) from Movie m where m.status = true")
    Long countWithStatusTrue();
}
