package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ptit.entities.MovieLink;

import java.util.List;

@Repository
public interface MovieLinkRepository extends JpaRepository<MovieLink,Integer> {
    List<MovieLink> findByMovie_Id(int id);
}
