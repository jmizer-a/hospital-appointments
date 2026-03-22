package pl.edu.pw.bd.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.edu.pw.bd.hospital.entity.constraints.Arc;

@Entity
@Table(schema="sys", name="accounts")
@Data @NoArgsConstructor @AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Arc(value=Account.class, field1="getPatient", field2="getDoctor")
public class Account  implements java.io.Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull @Column(unique=true, length=20)
    private String login;

    @NotNull @Column(length=100)
    private String password;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="account")
    private Patient patient;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="account")
    private Doctor doctor;
}
