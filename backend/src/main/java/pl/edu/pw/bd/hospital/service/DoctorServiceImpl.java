package pl.edu.pw.bd.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.bd.hospital.dto.DoctorDto;
import pl.edu.pw.bd.hospital.entity.Doctor;
import pl.edu.pw.bd.hospital.repository.DoctorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repo;

    private DoctorDto toDto(Doctor d) {
        return DoctorDto.builder()
                .id(d.getId())
                .firstName(d.getName())
                .lastName(d.getSurname())
                .build();
    }

    private Doctor toEntity(DoctorDto dto) {
        return Doctor.builder()
                .id(dto.getId())
                .name(dto.getFirstName())
                .surname(dto.getLastName())
                .build();
    }

    @Override
    public List<DoctorDto> findAll() {
        return repo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public DoctorDto findById(Long id) {
        return repo.findById(id)
                .map(this::toDto)
                .orElseThrow();
    }

    @Override
    public DoctorDto save(DoctorDto dto) {
        return toDto(repo.save(toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
