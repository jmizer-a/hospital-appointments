package pl.edu.pw.bd.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(schema="sys", name="tests")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Test implements java.io.Serializable {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotNull @JoinColumn(name="test_type_id", referencedColumnName="id")
    private TestType testType;

    @NotNull @Column(length=100)
    String result;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="examination_id", referencedColumnName="id")
    private Examination examination;
}
