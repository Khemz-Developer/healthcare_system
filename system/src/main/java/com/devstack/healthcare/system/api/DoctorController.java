package com.devstack.healthcare.system.api;

import com.devstack.healthcare.system.dto.request.RequestDoctorDto;
import com.devstack.healthcare.system.dto.response.ResponseDoctorDto;
import com.devstack.healthcare.system.service.DoctorService;
import com.devstack.healthcare.system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}")
    public ResponseDoctorDto getDoctors(@PathVariable Long id) {
        return doctorService.getDoctor(id);
    }

    @PostMapping
    public ResponseEntity<StandardResponse> createDoctor(@RequestBody RequestDoctorDto doctorDto) {
        doctorService.createDoctor(doctorDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Doctor saved!", doctorDto.getName()),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{id}")
    public String updateDoctor(@RequestBody RequestDoctorDto doctorDto, @PathVariable Long id) {
        doctorService.updateDoctor(doctorDto, id);
        return doctorDto.toString();
    }
    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id) {

        doctorService.deleteDoctor(id);
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

    @GetMapping("/findByName")
    public List<ResponseDoctorDto> findDoctorsByNames(@RequestParam String name) {
        return doctorService.findDoctorsByNames(name);
    }
}
