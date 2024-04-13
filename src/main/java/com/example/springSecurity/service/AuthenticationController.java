package com.example.springSecurity.service;


import com.example.springSecurity.domain.user.*;
//import com.example.springSecurity.infra.security.TokenService;
import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final UserRepository repository;

   // private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository repository /*TokenService tokenService*/) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        //this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data){
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

//        var token = tokenService.generateToken((Users) auth.getPrincipal());
//        return ResponseEntity.ok(new LoginResponseDTO(token));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO){
        if(this.repository.findByLogin(registerDTO.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        Users newUser = new Users(registerDTO.login(), encryptedPassword, registerDTO.role());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();

    }
}
