package com.flight.reser.controller;

import com.flight.reser.payload.UserDTO;
import com.flight.reser.service.UserService;
import com.flight.reser.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
     @Autowired
    private EmailService emailService;
// http://localhost:8080/api/user/create
    @PostMapping("/create")
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserDTO user = userService.createUser(userDTO);
        String emailContent = "Welcome to our service, " + user.getFirstName() + " " + user.getLastName() + "!" +
                "\nThank you for signing up!";
        emailService.sendEmail(user.getEmail(), "Welcome to our service!", emailContent);

        return user;
    }
    // http://localhost:8080/api/user/{id}

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable int id) {
        return userService.getUser(id);
    }
    // http://localhost:8080/api/user/all

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    // http://localhost:8080/api/user/update/{id}

    @PutMapping("/update/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }
    // http://localhost:8080/api/user/delete/{id}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }
    // http://localhost:8080/api/user/login
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO) {
//        boolean valid = userService.validateUser(userDTO.getEmail(), userDTO.getPassword());
//        if (valid) {
//            return new ResponseEntity<>("Login successful", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
//        }
//    }
}