package pl.edu.pw.bd.hospital.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.bd.hospital.dto.DoctorDto;
import pl.edu.pw.bd.hospital.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService service;

    @GetMapping
    public List<DoctorDto> list() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public DoctorDto get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto create(@RequestBody DoctorDto d) {
        return service.save(d);
    }

    @PutMapping("{id}")
    public DoctorDto update(@PathVariable Long id, @RequestBody DoctorDto d) {
        d.setId(id);
        return service.save(d);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
