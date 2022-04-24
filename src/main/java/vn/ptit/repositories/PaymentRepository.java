package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ptit.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
