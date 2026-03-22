package pl.edu.pw.bd.hospital.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data @Builder
public class AppointmentDto {
    private Long id;
    public Long doctorId;
    public Long patientId;
    private Long appointmentStatusId;
    public LocalDateTime datetime;
    public String patientsNote;
    public String appointmentType;
    
}
