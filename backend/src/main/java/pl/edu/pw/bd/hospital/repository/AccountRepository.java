package pl.edu.pw.bd.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.bd.hospital.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByLogin(String login);
}