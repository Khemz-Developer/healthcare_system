package com.devstack.healthcare.system.api;

import com.devstack.healthcare.system.dto.request.RequestDoctorDto;
import com.devstack.healthcare.system.service.DoctorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}")
    public String getDoctors(@PathVariable Long id) {
        return "Doctor with id: " + id;
    }

    @PostMapping
    public String createDoctor(@RequestBody RequestDoctorDto doctorDto) {
        doctorService.createDoctor(doctorDto);
        return doctorDto.toString();
    }
    @PutMapping(params = "id")
    public String updateDoctor( @RequestBody RequestDoctorDto doctorDto, @RequestParam Long id) {
        return doctorDto.toString();
    }
    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        return "Doctor with id: " + id + " deleted";
    }

    @GetMapping(path="/list", params = {"searchText", "page", "size"})
    public  String getAllDoctors(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return "List of all doctors";
    }
}
