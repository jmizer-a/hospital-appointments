package pl.edu.pw.bd.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.bd.hospital.entity.Patient;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByAccount_Login(String Login);
}