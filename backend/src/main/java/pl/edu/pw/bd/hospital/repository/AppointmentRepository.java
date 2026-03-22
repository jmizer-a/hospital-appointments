package pl.edu.pw.bd.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.bd.hospital.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {}