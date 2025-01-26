package com.devstack.healthcare.system.repo;

import com.devstack.healthcare.system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
@EnableJpaRepositories // this annotation is not necessary :annotation is used to enable JPA repositories
public interface DoctorRepo extends JpaRepository<Doctor,Long> {

    public List<Doctor> findAllByName(String name);

    @Query("SELECT d FROM Doctor d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Doctor> findByNameContaining(@Param("name") String name);

    @Query(value = "SELECT * FROM doctor WHERE name LIKE %:searchText% OR address LIKE %:searchText% OR contact LIKE %:searchText%", nativeQuery = true)
    public List<Doctor> searchDoctors(String searchText, Pageable pageable);
}
