package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService { // ✅ implements UserDetailsService

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Register a new user
    public User register(String username, String rawPassword) {
        String hashed = passwordEncoder.encode(rawPassword);
        User u = new User(username, hashed);
        return userRepository.save(u);
    }

    // ✅ Find by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // ✅ Validate credentials
    public boolean validateCredentials(String username, String rawPassword) {
        Optional<User> opt = findByUsername(username);
        if (opt.isEmpty()) return false;
        User u = opt.get();
        return passwordEncoder.matches(rawPassword, u.getPassword());
    }

    // ✅ Required by Spring Security for authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1️⃣ Fetch user from DB
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // 2️⃣ Convert roles (if you have roles in your entity)
        Set<SimpleGrantedAuthority> authorities = user.getRoles() == null
                ? Set.of()
                : user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getroleName()))
                .collect(Collectors.toSet());

        // 3️⃣ Return Spring Security's User object
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
