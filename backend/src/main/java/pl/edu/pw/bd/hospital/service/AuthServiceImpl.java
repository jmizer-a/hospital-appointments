package pl.edu.pw.bd.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.bd.hospital.dto.LoginRequestDto;
import pl.edu.pw.bd.hospital.dto.LoginResponseDto;
import pl.edu.pw.bd.hospital.entity.Account;
import pl.edu.pw.bd.hospital.entity.Patient;
import pl.edu.pw.bd.hospital.repository.AccountRepository;
import pl.edu.pw.bd.hospital.repository.PatientRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        Account account = accountRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (!account.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Patient patient = patientRepository.findByAccount_Login(request.getLogin())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return LoginResponseDto.builder()
                .patientId(patient.getId())
                .build();
    }
}