package com.example.springSecurity.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Users (String login, String password, UserRole role){
        this.login = login;
        this.password = password;
        this.role = role;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       if(this.role==UserRole.ADMIN)
           return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), (new SimpleGrantedAuthority("ROlE_USER")));
       else{
           return List.of(new SimpleGrantedAuthority("ROLE_USER"));
       }
    }
    @Override
    public String getUsername() {
        return login;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
