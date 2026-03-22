package pl.edu.pw.bd.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.bd.hospital.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {}
