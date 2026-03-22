package pl.edu.pw.bd.hospital.entity;

import java.util.Date;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(schema="sys", name="patients")
@Data @NoArgsConstructor @AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Patient  implements java.io.Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull @Temporal(TemporalType.DATE)
    @Column(length=7)
    private Date birthdate;
    
    @NotNull @Column(name="NAME", length=50)
    private String name;
    
    @NotNull @Column(name="SURNAME", nullable=false, length=50)
    private String surname;

    @NotNull @Column(length=1)
    private char gender;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id", referencedColumnName="id")
    private Account account;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="patient")
    private Set<Appointment> appointments;
}
