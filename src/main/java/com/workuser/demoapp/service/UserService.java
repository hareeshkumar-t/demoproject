package com.workuser.demoapp.service;

import com.workuser.demoapp.model.User;
import com.workuser.demoapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    /*public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }*/

    public ResponseEntity<Optional<User>> getUserById(Long id) {
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<List<User>> createUser(List<User> user) {
        return new ResponseEntity<List<User>>(userRepository.saveAll(user), HttpStatus.CREATED);
    }

    /*public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }).orElse(null);
    }*/

    public ResponseEntity<Optional<User>> updateUser(Long id, User userDetails) {
        return new ResponseEntity<>(userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }), HttpStatus.OK);
    }


    /*public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }*/

    public ResponseEntity<String> deleteUser(Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
    }

}
