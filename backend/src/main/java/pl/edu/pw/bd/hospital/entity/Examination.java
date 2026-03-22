package pl.edu.pw.bd.hospital.entity;

import java.util.Set;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema="sys", name="examinations")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@Builder
@DiscriminatorValue("EXAM")
public class Examination extends Appointment {
    @OneToMany(fetch=FetchType.LAZY, mappedBy="examination")
    private Set<Test> tests;
}
