package com.proyecto_inventario.service;

import com.proyecto_inventario.model.User;
import com.proyecto_inventario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Se usa en el login

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // La contraseña ya debe estar encriptada en la BD
                .roles("USER") // Cambia según sea necesario
                .build();
    }

    // Método para registrar usuarios con contraseña encriptada
    public User registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, encodedPassword);
        return userRepository.save(newUser);
    }
}
