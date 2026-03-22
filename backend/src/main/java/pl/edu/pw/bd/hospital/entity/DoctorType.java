package pl.edu.pw.bd.hospital.entity;

import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(schema="sys", name="doctor_types")
@Data @NoArgsConstructor @AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class DoctorType  implements java.io.Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull @Column(length=50)
    private String name;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="doctorTypes")
    Set<Doctor> doctors;
}


