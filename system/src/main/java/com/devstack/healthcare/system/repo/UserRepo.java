package com.devstack.healthcare.system.repo;

import com.devstack.healthcare.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE username=?1", nativeQuery = true)
    User findByUsername(String username);
}
