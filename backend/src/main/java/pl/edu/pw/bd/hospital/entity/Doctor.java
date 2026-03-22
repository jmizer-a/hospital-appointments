package pl.edu.pw.bd.hospital.entity;

import java.util.Date;
import java.util.Set;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(schema="sys", name="doctors")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Doctor implements java.io.Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(schema="sys", name="doctor_ward",
        joinColumns={@JoinColumn(name="doctor_id", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="ward_id", referencedColumnName="id")})
    private Set<Ward> wards;
    
    @NotNull @Column(length=50)
    private String name;

    @NotNull @Column(length=50)
    private String surname;

    @Temporal(TemporalType.DATE)
    @Column(nullable=false, length=7)
    private Date dateEmployed;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(schema="sys", name="doctor_specialties",
        joinColumns = {@JoinColumn(name="doctor_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="doctor_type_id", referencedColumnName="id")})
    private Set<DoctorType> doctorTypes;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="doctor")
    private Set<Appointment> appointments;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id", referencedColumnName="id")
    private Account account;
}
