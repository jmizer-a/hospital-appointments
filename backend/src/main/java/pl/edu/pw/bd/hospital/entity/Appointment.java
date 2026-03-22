package pl.edu.pw.bd.hospital.entity;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(schema="sys", name="appointments")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="appointment_type", columnDefinition="CHAR(4)")
public abstract class Appointment  implements java.io.Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotNull @JoinColumn(name="APPOINTMENT_STATUS_ID", referencedColumnName="id")
    private AppointmentStatus appointmentStatus;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotNull @JoinColumn(name="DOCTOR_ID", referencedColumnName="id")
    private Doctor doctor;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotNull @JoinColumn(name="PATIENT_ID", referencedColumnName="id")
    private Patient patient;

    @Column(name="DATETIME", nullable=false, length=7)
    private LocalDateTime datetime;

    @Column(name="PATIENTS_NOTE", length=1000)
    private String patientsNote;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="appointment")
    private Set<MedicalDocument> medicalDocuments;
}


