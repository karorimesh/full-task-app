package com.karorimesh.task.resource;

import com.karorimesh.task.model.AuthDTO;
import com.karorimesh.task.model.AuthModel;
import com.karorimesh.task.model.UserDTO;
import com.karorimesh.task.model.UserModel;
import com.karorimesh.task.services.AuthService;
import com.karorimesh.task.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserDTO> register(@RequestBody UserModel model){
        return ResponseEntity.ok(userService.add(model));
    }

    @PostMapping("login")
    public ResponseEntity<AuthDTO> login (@RequestBody AuthModel model){
        return authService.authenticate(model);
    }
}
