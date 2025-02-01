package com.devstack.healthcare.system.util.mapper;

import com.devstack.healthcare.system.dto.request.RequestDoctorDto;
import com.devstack.healthcare.system.dto.response.ResponseDoctorDto;
import com.devstack.healthcare.system.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "id", ignore = true) // Ignore id mapping
    Doctor toDoctor(RequestDoctorDto dto); // convert RequestDoctorDto to Doctor means from request dto to entity

    ResponseDoctorDto toResponseDoctorDto(Doctor doctor); // convert Doctor to ResponseDoctorDto means from entity to response dto


    List<ResponseDoctorDto> toResponseDoctorDtoList(List<Doctor> list); // convert List of Doctor to List of ResponseDoctorDto
}
