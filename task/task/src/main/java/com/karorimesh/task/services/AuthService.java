package com.karorimesh.task.services;


import com.karorimesh.task.enums.Role;
import com.karorimesh.task.model.AuthDTO;
import com.karorimesh.task.model.AuthModel;
import com.karorimesh.task.model.UserDTO;
import com.karorimesh.task.util.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<AuthDTO> authenticate(AuthModel model) {
        try{
            var principal = (UserDetails) authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword())
            ).getPrincipal();
            var token = Jwt.generate(principal);
            return ResponseEntity.ok(AuthDTO.builder()
                    .user(UserDTO.builder()
                            .username(principal.getUsername())
                            .role(Role.valueOf(
                                    principal.getAuthorities().stream().findFirst().get().getAuthority()
                            ))
                            .build())
                    .token(token)
                    .build());
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthDTO.builder()
                    .error("Invalid credentials")
                    .build());
        }
    }
}
