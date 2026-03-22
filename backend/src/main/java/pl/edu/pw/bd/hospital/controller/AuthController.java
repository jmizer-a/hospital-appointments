package pl.edu.pw.bd.hospital.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.bd.hospital.dto.LoginRequestDto;
import pl.edu.pw.bd.hospital.dto.LoginResponseDto;
import pl.edu.pw.bd.hospital.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }
}