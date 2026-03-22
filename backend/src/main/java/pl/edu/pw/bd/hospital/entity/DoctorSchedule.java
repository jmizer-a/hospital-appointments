package pl.edu.pw.bd.hospital.entity;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema="sys", name="doctor_schedules")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class DoctorSchedule  implements java.io.Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="doctor_id", referencedColumnName="id")
    private Doctor doctor;

    @Temporal(TemporalType.DATE)
    @Column(name="start_", nullable=false, length=7)
    private Date start;

    @Temporal(TemporalType.DATE)
    @Column(name="end_", nullable=false, length=7)
    private Date end;
}


