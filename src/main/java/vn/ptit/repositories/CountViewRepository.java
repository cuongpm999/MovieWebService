package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ptit.entities.CountView;

@Repository
public interface CountViewRepository extends JpaRepository<CountView,Integer> {
    public int countCountViewByMovie_Id(int id);
}
