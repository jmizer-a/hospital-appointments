package pl.edu.pw.bd.hospital.entity;

import java.util.Set;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(schema="sys", name="wards")
@Data @NoArgsConstructor @AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Ward  implements java.io.Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length=50)
    private String name;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="wards")
    private Set<Doctor> doctors;
}
