package pl.edu.pw.bd.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.bd.hospital.dto.AppointmentDto;
import pl.edu.pw.bd.hospital.entity.*;
import pl.edu.pw.bd.hospital.repository.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentStatusRepository statusRepository;

    private AppointmentDto toDto(Appointment a) {
        return AppointmentDto.builder()
                .id(a.getId())
                .doctorId(a.getDoctor().getId())
                .patientId(a.getPatient().getId())
                .appointmentStatusId(a.getAppointmentStatus().getId())
                .datetime(a.getDatetime())
                .appointmentType(getAppointmentTypeFromInstance(a))
                .patientsNote(a.getPatientsNote())
                .build();
    }

    private Appointment toEntity(AppointmentDto dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow();
        Patient patient = patientRepository.findById(dto.getPatientId()).orElseThrow();
        AppointmentStatus status = statusRepository.findById(1L).orElseThrow();

        Appointment appointment;
        if ("EXAM".equals(dto.getAppointmentType())) {
            appointment = new Examination();
        } else if ("CONS".equals(dto.getAppointmentType())) {
            appointment = new Consultation();
        } else {
            throw new IllegalArgumentException("Invalid appointment type");
        }

        System.out.println("DTO datetime: " + dto.getDatetime());

        appointment.setId(dto.getId());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentStatus(status);
        appointment.setDatetime(dto.getDatetime());
        appointment.setPatientsNote(dto.getPatientsNote());

        return appointment;
    }

    private String getAppointmentTypeFromInstance(Appointment appointment) {
        if (appointment instanceof Examination) return "EXAM";
        if (appointment instanceof Consultation) return "CONS";
        throw new IllegalStateException("Unknown appointment subclass");
    }

    @Override
    public List<AppointmentDto> findAll() {
        return appointmentRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public AppointmentDto findById(Long id) {
        return appointmentRepository.findById(id)
                .map(this::toDto)
                .orElseThrow();
    }

    @Override
    public AppointmentDto save(AppointmentDto dto) {
        return toDto(appointmentRepository.save(toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
