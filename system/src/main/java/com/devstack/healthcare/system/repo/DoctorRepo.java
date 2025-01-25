package com.devstack.healthcare.system.repo;

import com.devstack.healthcare.system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories // this annotation is not necessary :annotation is used to enable JPA repositories
public interface DoctorRepo extends JpaRepository<Doctor,Long> {
}
