package pl.edu.pw.bd.hospital.service;

import pl.edu.pw.bd.hospital.dto.DoctorDto;
import java.util.List;

public interface DoctorService {
  List<DoctorDto> findAll();
  DoctorDto findById(Long id);
  DoctorDto save(DoctorDto dto);
  void delete(Long id);
}
