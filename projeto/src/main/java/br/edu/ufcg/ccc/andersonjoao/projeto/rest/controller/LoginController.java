package br.edu.ufcg.ccc.andersonjoao.projeto.rest.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.User;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.service.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final String TOKEN_KEY = "banana";
    private final Pattern emailPattern = Pattern.compile("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.([a-z]+)?$");;

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public LoginResponse register(@RequestBody User user) throws ServletException {

        // verificacoes

        // Verifica se senha satisfaz determinadas condicoes
        if(user.getPassword() != null && user.getPassword().trim().length() > 0) {
            throw new ServletException("Senha inválida!");
        }

        // Verifica se email satisfaz determinadas condicoes
        if(user.getEmail() != null && emailPattern.matcher(user.getEmail()).find()) {
            throw new ServletException("Email inválido!");
        }

        // Verifica se firstName satisfaz determinadas condicoes
        if(user.getFirstName() != null && user.getFirstName().trim().length() > 0) {
            throw new ServletException("Primeiro nome inválido!");
        }

        // Verifica se lastName satisfaz determinadas condicoes
        if(user.getLastName() != null && user.getLastName().trim().length() > 0) {
            throw new ServletException("Último nome inválido!");
        }

        // Recupera o usuario
        User authUser = userService.findByEmail(user.getEmail());

        // Verifica se o email já está sendo utilizado
        if(authUser != null) {
            throw new ServletException("Já existe um usuario com esse email!");
        }

        User userRegistered = userService.save(user);

        return this.authenticate(userRegistered);
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody User user) throws ServletException {

        // Recupera o usuario
        User authUser = userService.findByEmail(user.getEmail());

        // verificacoes
        if(authUser == null) {
            throw new ServletException("Usuario nao encontrado!");
        }

        if(!authUser.getPassword().equals(user.getPassword())) {
            throw new ServletException("Senha invalida!");
        }

        String token = Jwts.builder().
                setSubject(authUser.getEmail()).
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
