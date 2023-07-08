package com.flight.reser.service;

import com.flight.reser.payload.UserDTO;

import java.util.List;

public interface UserService {

    public UserDTO createUser(UserDTO userDTO);
    public UserDTO getUser(int id);
    public List<UserDTO> getAllUsers();
    public UserDTO updateUser(int id, UserDTO userDTO);
    public void deleteUser(int id);

//    boolean validateUser(String email, String password);
}
