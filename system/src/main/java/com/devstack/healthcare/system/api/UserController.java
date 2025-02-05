package com.devstack.healthcare.system.api;

import com.devstack.healthcare.system.dto.request.RequestUserDto;

import com.devstack.healthcare.system.service.UserService;
import com.devstack.healthcare.system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/visitor/signup")
    public ResponseEntity<StandardResponse> signup(@RequestBody RequestUserDto userDto){
        userService.signup(userDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "User saved!", userDto.getFullName()),
                HttpStatus.CREATED
        );
    }
}
