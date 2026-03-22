package pl.edu.pw.bd.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.bd.hospital.entity.AppointmentStatus;

public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, Long> {}