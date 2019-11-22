package com.training.SpringBootTask.security;

import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.services.UserService;
import com.training.SpringBootTask.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("customUserDetailsService")
public class JwtUserDetailService implements UserDetailsService {
    private UserService userService;

    @Autowired
    public JwtUserDetailService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userService.findByLogin(s);
        user.orElseThrow(() -> new UsernameNotFoundException("No user with username - " + s));
        return createUserDetails(user.get());
    }

    private UserDetails createUserDetails(User user) {
       return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new HashSet<SimpleGrantedAuthority>());
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
