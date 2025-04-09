package com.codewithyash.blog.controllers;

import com.codewithyash.blog.payloads.ApiResponse;
import com.codewithyash.blog.payloads.UserDto;
import com.codewithyash.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Post-> create user.
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Get-> get user.
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.FOUND);
    }
    // Get all user.
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }


    // Put-> Update user.
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete-> Delete user.
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully.", true), HttpStatus.OK);
    }
}
