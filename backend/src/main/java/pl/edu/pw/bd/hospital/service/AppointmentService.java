package pl.edu.pw.bd.hospital.service;

import pl.edu.pw.bd.hospital.dto.AppointmentDto;
import java.util.List;

public interface AppointmentService {
    List<AppointmentDto> findAll();
    AppointmentDto findById(Long id);
    AppointmentDto save(AppointmentDto dto);
    void delete(Long id);
}