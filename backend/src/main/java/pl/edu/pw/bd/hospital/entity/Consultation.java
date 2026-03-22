package pl.edu.pw.bd.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema="sys", name="consultations")
@Builder
@DiscriminatorValue("CONS")
public class Consultation extends Appointment  {
    public Consultation() {}
}
