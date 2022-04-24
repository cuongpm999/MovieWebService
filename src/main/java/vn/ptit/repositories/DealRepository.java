package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ptit.entities.Deal;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal,Integer> {
    List<Deal> findByStatusTrue();
}
