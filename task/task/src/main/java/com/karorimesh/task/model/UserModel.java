package com.karorimesh.task.model;

import com.karorimesh.task.enums.Role;
import lombok.Data;

@Data
public class UserModel {
    private String username;
    private String email;
    private String password;
    private Role role;
}
