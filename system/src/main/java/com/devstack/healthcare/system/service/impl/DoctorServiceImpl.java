package com.devstack.healthcare.system.service.impl;

import com.devstack.healthcare.system.dto.request.RequestDoctorDto;
import com.devstack.healthcare.system.dto.response.ResponseDoctorDto;
import com.devstack.healthcare.system.dto.response.paginated.PaginatedDoctorResponseDto;
import com.devstack.healthcare.system.entity.Doctor;
import com.devstack.healthcare.system.repo.DoctorRepo;
import com.devstack.healthcare.system.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;

    @Autowired // Autowired annotation used for automatic dependency injection
    public DoctorServiceImpl(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public void createDoctor(RequestDoctorDto dto) { // createDoctor method is used to create a new doctor
        UUID uuid = UUID.randomUUID();
        long docId = uuid.getMostSignificantBits();
        Doctor doctor = new Doctor(
                docId,dto.getName(),dto.getAddress(), dto.getContact(), dto.getSalary()
        );

        doctorRepo.save(doctor);
    }

    @Override
    public void updateDoctor(RequestDoctorDto dto, long id) { // updateDoctor method is used to update the doctor details
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if(selectedDoctor.isEmpty()){
            throw new RuntimeException("Doctor not found");
        }
        Doctor doc = selectedDoctor.get();
        doc.setName(dto.getName());
        doc.setAddress(dto.getAddress());
        doc.setContact(dto.getContact());
        doc.setSalary(dto.getSalary());
        doctorRepo.save(doc);
    }

    @Override
    public void deleteDoctor(long id) { // deleteDoctor method is used to delete the doctor
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if(selectedDoctor.isEmpty()){
            throw new RuntimeException("Doctor not found");
        }
        doctorRepo.deleteById(selectedDoctor.get().getId()); // deleteById method is used to delete the entity by its id
    }

    @Override
    public ResponseDoctorDto getDoctor(long id) { // getDoctor method is used to get the doctor details
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if(selectedDoctor.isEmpty()){
            throw new RuntimeException("Doctor not found");
        }
        Doctor doc = selectedDoctor.get();
        return new ResponseDoctorDto(
                doc.getId(), doc.getName(), doc.getAddress(), doc.getContact(), doc.getSalary()
        );
    }

    @Override
    public PaginatedDoctorResponseDto getAllDoctors(String searchText, int page, int size) {
        searchText = "%" + searchText + "%";
        List<Doctor> doctors = doctorRepo.searchDoctors(searchText, PageRequest.of(page, size));
        long doctorCount = doctorRepo.countDoctors(searchText);
        List<ResponseDoctorDto> dtos = new ArrayList<>();
        doctors.forEach(
                doctor -> {
                    dtos.add(
                            new ResponseDoctorDto(
                                    doctor.getId(), doctor.getName(), doctor.getAddress(), doctor.getContact(), doctor.getSalary()
                            )
                    );
                });

        return new PaginatedDoctorResponseDto(

                doctorCount, dtos
        );
    }


    @Override
    public List<ResponseDoctorDto> findDoctorsByNames(String name) { // findDoctorsByNames method is used to find the doctor by name
        // Change the repository method based on matching logic
        return doctorRepo.findByNameContaining(name).stream()
                .map(doctor -> new ResponseDoctorDto(
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getAddress(),
                        doctor.getContact(),
                        doctor.getSalary()
                )).toList();
    }

}
