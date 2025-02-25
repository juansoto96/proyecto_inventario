// package com.proyecto_inventario.controller;

// import com.proyecto_inventario.model.User;
// import com.proyecto_inventario.repository.UserRepository;
// import com.proyecto_inventario.util.JwtUtil;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import java.util.Optional;

// @CrossOrigin(origins = "http://localhost:3000")
// @RestController
// @RequestMapping("/auth")
// public class AuthController {
//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private JwtUtil jwtUtil;

//     private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody User user) {
//         Optional<User> dbUser = userRepository.findByUsername(user.getUsername());

//         if (dbUser.isPresent() && passwordEncoder.matches(user.getPassword(), dbUser.get().getPassword())) {
//             String token = jwtUtil.generateToken(user.getUsername());
//             return ResponseEntity.ok().body("{ \"token\": \"" + token + "\" }");
//         } else {
//             return ResponseEntity.status(401).body("Credenciales inválidas");
//         }
//     }
// }
package com.proyecto_inventario.controller;

import com.proyecto_inventario.dto.LoginRequest;
import com.proyecto_inventario.model.User;
import com.proyecto_inventario.repository.UserRepository;
import com.proyecto_inventario.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Depuración: Mostrar los datos recibidos
        System.out.println("Usuario recibido: " + loginRequest.getUsername());
        System.out.println("Contraseña recibida: " + loginRequest.getPassword());

        Optional<User> dbUser = userRepository.findByUsername(loginRequest.getUsername());

        if (dbUser.isPresent()) {
            System.out.println("Contraseña en la base de datos (hash): " + dbUser.get().getPassword());

            if (passwordEncoder.matches(loginRequest.getPassword(), dbUser.get().getPassword())) {
                String token = jwtUtil.generateToken(dbUser.get().getUsername());
                return ResponseEntity.ok().body("{ \"token\": \"" + token + "\" }");
            } else {
                System.out.println("⚠️ Contraseña incorrecta");
                return ResponseEntity.status(401).body("Credenciales inválidas");
            }
        } else {
            System.out.println("⚠️ Usuario no encontrado");
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}



