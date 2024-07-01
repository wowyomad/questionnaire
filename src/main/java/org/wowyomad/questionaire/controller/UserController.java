package org.wowyomad.questionaire.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wowyomad.questionaire.dto.AuthenticationResponse;
import org.wowyomad.questionaire.dto.UserDto;
import org.wowyomad.questionaire.dto.UserPasswordResetDto;
import org.wowyomad.questionaire.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Integer id) {

        return userService.getUser(id);
    }

    @GetMapping("")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<AuthenticationResponse> updateProfile(@PathVariable("id") Integer id,@Valid @RequestBody UserDto updatedUser) {
        return new ResponseEntity<>(userService.updateUser(id, updatedUser), HttpStatus.OK);
    }

    @PutMapping("/{id}/reset-password")
    public ResponseEntity<UserDto> resetPassword(@PathVariable("id") Integer id, @Valid @RequestBody UserPasswordResetDto password) {
        return new ResponseEntity<>(userService.updatePassword(id, password), HttpStatus.OK);
    }
}
