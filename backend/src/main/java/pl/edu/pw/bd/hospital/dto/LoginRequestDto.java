package pl.edu.pw.bd.hospital.dto;

import lombok.*;

@Data @Builder
public class LoginRequestDto {
    private String login;
    private String password;
}