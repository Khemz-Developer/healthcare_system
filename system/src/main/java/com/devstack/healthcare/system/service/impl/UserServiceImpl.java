package com.devstack.healthcare.system.service.impl;

import com.devstack.healthcare.system.dto.request.RequestUserDto;
import com.devstack.healthcare.system.entity.User;
import com.devstack.healthcare.system.entity.UserRole;
import com.devstack.healthcare.system.entity.UserRoleHasUser;
import com.devstack.healthcare.system.repo.UserRepo;
import com.devstack.healthcare.system.repo.UserRoleHasUserRepo;
import com.devstack.healthcare.system.repo.UserRoleRepo;
import com.devstack.healthcare.system.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private  final UserRoleRepo userRoleRepo;

    private final UserRoleHasUserRepo userRoleHasUserRepo;

    private final PasswordEncoder passwordEncoder;

    private final UserRepo userRepo;

    public UserServiceImpl(UserRoleRepo userRoleRepo, UserRoleHasUserRepo userRoleHasUserRepo, PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.userRoleRepo = userRoleRepo;
        this.userRoleHasUserRepo = userRoleHasUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }
    @Override
    public void signup(RequestUserDto userDto) {

        UserRole userRole;

        if(userDto.getId() == 1){
           userRole =  userRoleRepo.findUserRoleByName("ADMIN");
        }else {
           userRole =  userRoleRepo.findUserRoleByName("DOCTOR");
        }

        if(userRole == null){
            throw new RuntimeException("User role not found");
        }

        User user = new User(

                userDto.getId(),
                userDto.getFullName(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                true,
                true,
                true,
                true,
                null

        );

        UserRoleHasUser userData = new UserRoleHasUser(user,userRole);
        userRepo.save(user);
        userRoleHasUserRepo.save(userData);


    }
}
