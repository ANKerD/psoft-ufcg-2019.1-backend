package br.edu.ufcg.anderson.dantas.lab2.rest.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @PostMapping("/register")
    public LoginResponse register(@RequestBody User user) throws ServletException {
        String token = Jwts.builder().
                setSubject(authUser.getLogin()).
                signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
                setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                .compact();

        return this.authenticate(user);
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody User user) throws ServletException {

        // Recupera o usuario
        User authUser = userService.findByLogin(user.getLogin());

        // verificacoes
        if(authUser == null) {
            throw new ServletException("Usuario nao encontrado!");
        }

        if(!authUser.getPassword().equals(user.getPassword())) {
            throw new ServletException("Senha invalida!");
        }

        String token = Jwts.builder().
                setSubject(authUser.getLogin()).
                signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
                setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                .compact();

        return new LoginResponse(token);


    }

    private class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }
}
