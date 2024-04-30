package com.socialmediaapp.twitter.controller;

import com.socialmediaapp.twitter.dao.PostRepo;
import com.socialmediaapp.twitter.dao.UserRepo;
import com.socialmediaapp.twitter.post.Post;
import com.socialmediaapp.twitter.service.UserService;
import com.socialmediaapp.twitter.userdto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.socialmediaapp.twitter.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        if (userService.userExists(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden, Account already exists");
        }

        userRepo.save(user);
        log.info("Account Creation Successful");
        return ResponseEntity.ok("Account Creation Successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (userService.userExists(user)) {
            if (userService.userpwd(user)) {
                return ResponseEntity.ok("Login Successful");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username/Password Incorrect");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not exist");
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setName(user.getName());
                    userDTO.setEmail(user.getEmail());
                    userDTO.setUserID(user.getUserID());
                    return userDTO;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setUserID(user.getUserID());
            return ResponseEntity.ok(userDTO);
        } else {
            log.info("User does not exist");
            return ResponseEntity.notFound().build();
        }
    }
}

