package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ptit.entities.Credit;

@Repository
public interface CreditRepository extends JpaRepository<Credit,Integer> {
}
