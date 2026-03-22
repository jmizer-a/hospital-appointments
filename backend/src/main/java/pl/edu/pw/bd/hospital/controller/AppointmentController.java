package pl.edu.pw.bd.hospital.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.bd.hospital.dto.AppointmentDto;
import pl.edu.pw.bd.hospital.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping
    public List<AppointmentDto> list() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public AppointmentDto get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDto create(@RequestBody AppointmentDto d) {
        return service.save(d);
    }

    @PutMapping("{id}")
    public AppointmentDto update(@PathVariable Long id, @RequestBody AppointmentDto d) {
        d.setId(id);
        return service.save(d);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}