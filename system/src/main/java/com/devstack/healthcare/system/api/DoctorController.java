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
    public ResponseEntity<StandardResponse> getDoctors(@PathVariable Long id) {
        return new ResponseEntity<>(
                new StandardResponse(200,"Doctor found!", doctorService.getDoctor(id)),
                HttpStatus.OK
        );
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
    public ResponseEntity<StandardResponse> updateDoctor(@RequestBody RequestDoctorDto doctorDto, @PathVariable Long id) {
        doctorService.updateDoctor(doctorDto, id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Doctor updated!", doctorDto.getName()),
                HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteDoctor(@PathVariable Long id) {

        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Doctor deleted!", id),
                HttpStatus.OK
        );
    }

    @GetMapping(path="/list", params = {"searchText", "page", "size"})
    public  ResponseEntity<StandardResponse> getAllDoctors(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return new ResponseEntity<>(
                new StandardResponse(200, "Doctors found!", doctorService.getAllDoctors(searchText, page, size)),
                HttpStatus.OK
        );
    }

    @GetMapping("/findByName")
    public ResponseEntity<StandardResponse> findDoctorsByNames(@RequestParam String name) {
        List<ResponseDoctorDto> doctors = doctorService.findDoctorsByNames(name);
        return new ResponseEntity<>(
                new StandardResponse(200, "Doctors found!", doctors),
                HttpStatus.OK
        );
    }
}
