package br.edu.ufcg.ccc.andersonjoao.projeto.rest.controller;

import br.edu.ufcg.ccc.andersonjoao.projeto.exception.auth.InvalidDataException;
import br.edu.ufcg.ccc.andersonjoao.projeto.exception.auth.WrongCredentialsException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class AuthController {

    private final String TOKEN_KEY = "banana";
    private final Pattern emailPattern = Pattern.compile("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$");

    @Autowired
    private UserService userService;

    @ApiOperation(value="Registra um usuário")
    @PostMapping("/register")
    public LoginResponse register(@RequestBody User user) {

        // verificacoes

        // Verifica se senha satisfaz determinadas condicoes
        if(user.getPassword() == null || user.getPassword().trim().length() == 0) {
            throw new InvalidDataException("Senha inválida!");
        }

        // Verifica se email satisfaz determinadas condicoes
        if(user.getEmail() == null || !emailPattern.matcher(user.getEmail()).find()) {
            throw new InvalidDataException("Email inválido!");
        }

        // Verifica se firstName satisfaz determinadas condicoes
        if(user.getFirstName() == null || user.getFirstName().trim().length() == 0) {
            throw new InvalidDataException("Primeiro nome inválido!");
        }

        // Verifica se lastName satisfaz determinadas condicoes
        if(user.getLastName() == null || user.getLastName().trim().length() == 0) {
            throw new InvalidDataException("Último nome inválido!");
        }

        // Recupera o usuario
        User authUser = userService.findByEmail(user.getEmail());

        // Verifica se o email já está sendo utilizado
        if(authUser != null) {
            throw new InvalidDataException("Já existe um usuario com esse email!");
        }

        user.setPassword(user.getPassword().trim());

        User userRegistered = userService.save(user);

        return this.authenticate(userRegistered);
    }

    @ApiOperation(value="Autentica um usuário")
    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody User user) {

        // Recupera o usuario
        User authUser = userService.findByEmail(user.getEmail());

        // verificacoes
        if(authUser == null || !authUser.getPassword().equals(user.getPassword().trim())) {
            throw new WrongCredentialsException("Usuario e/ou senha inválidos!");
        }

        String token = Jwts.builder().
                setSubject(authUser.getEmail()).
                signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
                setExpiration(new Date(System.currentTimeMillis() + 1 * 600 * 1000))
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
