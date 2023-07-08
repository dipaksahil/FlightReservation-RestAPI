package com.flight.reser.service.impl;

import com.flight.reser.entity.User;
import com.flight.reser.payload.UserDTO;
import com.flight.reser.repository.UserRepository;
import com.flight.reser.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }


    public UserDTO getUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(int id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        // Only update the fields if a new value has been provided
        if(userDTO.getFirstName() != null) {
            user.setFirstName(userDTO.getFirstName());
        }
        if(userDTO.getLastName() != null) {
            user.setLastName(userDTO.getLastName());
        }
        if(userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        // If the password has been set in the DTO, encode and update it.
        if(userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        //updatedUserDetails.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        //user.updateUserDetails(updatedUserDetails);
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

        public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
//       @Override
//         public boolean validateUser(String email, String password) {
//        User user = userRepository.findByEmail(email);
//        if (user != null) {
//            // You should uncomment the following line once you integrate password encoding.
//            // return passwordEncoder.matches(password, user.getPassword());
//
//            // For now, you can simply compare the plaintext passwords.
//            return user.getPassword().equals(password);
//        } else {
//            throw new RuntimeException("User with email " + email + " not found");
//        }
//    }
}