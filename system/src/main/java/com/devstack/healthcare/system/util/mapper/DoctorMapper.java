package com.devstack.healthcare.system.util.mapper;

import com.devstack.healthcare.system.dto.request.RequestDoctorDto;
import com.devstack.healthcare.system.dto.response.ResponseDoctorDto;
import com.devstack.healthcare.system.entity.Doctor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    ResponseDoctorDto toResponseDoctorDto(Doctor doctor); // convert Doctor to ResponseDoctorDto means from entity to response dto

    Doctor toDoctor(RequestDoctorDto dto); // convert RequestDoctorDto to Doctor means from request dto to entity

    List<ResponseDoctorDto> toResponseDoctorDtoList(List<Doctor> list); // convert List of Doctor to List of ResponseDoctorDto
}
