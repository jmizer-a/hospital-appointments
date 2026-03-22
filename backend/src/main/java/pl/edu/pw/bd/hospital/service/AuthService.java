package pl.edu.pw.bd.hospital.service;

import pl.edu.pw.bd.hospital.dto.LoginRequestDto;
import pl.edu.pw.bd.hospital.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto request);
}
