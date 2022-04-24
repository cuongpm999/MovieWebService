package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ptit.entities.DigitalWallet;

@Repository
public interface DigitalWalletRepository extends JpaRepository<DigitalWallet,Integer> {
}
