package org.wowyomad.questionaire.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wowyomad.questionaire.dto.AuthenticationResponse;
import org.wowyomad.questionaire.dto.UserDto;
import org.wowyomad.questionaire.dto.UserPasswordChangeDto;
import org.wowyomad.questionaire.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
@RequiredArgsConstructor
public class    UserController {

    private final UserService userService;
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Integer id) {

        return userService.getUser(id);
    }

    @GetMapping("")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthenticationResponse> updateProfile(@PathVariable("id") Integer id,@Valid @RequestBody UserDto patchUser) {
        return new ResponseEntity<>(userService.patchUser(id, patchUser), HttpStatus.OK);
    }

    @PatchMapping("/{id}/reset-password")
    public ResponseEntity<AuthenticationResponse> resetPassword(@PathVariable("id") Integer id, @Valid @RequestBody UserPasswordChangeDto password) {
        return new ResponseEntity<>(userService.updatePassword(id, password), HttpStatus.OK);
    }
}
