package com.karorimesh.task.domain;

import com.karorimesh.task.enums.Role;
import com.karorimesh.task.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "app_user")
@SQLRestriction("deleted_at is null")
public class User extends BaseDomain implements UserDetails {
//    User: id, username, email, password, role, createdAt
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "user_sequence_gen",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence_gen"
    )
    private Long id;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getStatus().equals(Status.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getStatus().equals(Status.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.getStatus().equals(Status.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus().equals(Status.ACTIVE);
    }
}
