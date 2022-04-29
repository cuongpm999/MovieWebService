package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ptit.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query("select sum(p.totalMoney) from Payment p")
    Long totalMoney();
}
