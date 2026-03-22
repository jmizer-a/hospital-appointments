package pl.edu.pw.bd.hospital.dto;

import lombok.*;

@Data @Builder
public class DoctorDto {
  private Long id;
  private String firstName;
  private String lastName;
}
