package com.example.book_your_show.config;

import com.example.book_your_show.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsCreator implements UserDetails {
    String username;
    String password;
    List<GrantedAuthority>authorities=new ArrayList<>();
    public UserDetailsCreator(User user){
        this.username=user.getEmailId();
        this.password=user.getPassword();
        String role=user.getRole().getDisplayName();
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(role);
        authorities.add(simpleGrantedAuthority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
