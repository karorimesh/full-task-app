package com.karorimesh.task.services;

import com.karorimesh.task.domain.User;
import com.karorimesh.task.enums.Status;
import com.karorimesh.task.model.UserDTO;
import com.karorimesh.task.model.UserModel;
import com.karorimesh.task.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public UserDTO add(UserModel model){
        var user = modelMapper.map(model, User.class);
        user.setStatus(Status.ACTIVE);
        user.setPassword(new BCryptPasswordEncoder().encode(model.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDTO> findAll(){
        return userRepository.findAll()
                .stream().map( user ->
                modelMapper.map(user, UserDTO.class))
                .toList();
    }
}
