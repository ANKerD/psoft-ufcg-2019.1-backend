package br.edu.ufcg.ccc.andersonjoao.projeto.rest.controller;

import br.edu.ufcg.ccc.andersonjoao.projeto.exception.InvalidDataException;
import br.edu.ufcg.ccc.andersonjoao.projeto.exception.auth.WrongCredentialsException;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.service.UserService;

import java.util.regex.Pattern;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final int SECOND = 1000;
    private final int MINUTE = 60 * SECOND;
    private final int HOUR = 60 * MINUTE;

    private final String TOKEN_KEY = "banana";
    private final Pattern emailPattern = Pattern.compile("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$");

    @Autowired
    private UserService userService;

    @ApiOperation(value="Registra um usuário")
    @PostMapping("/register")
    public LoginResponse register(@RequestBody UserModel userModel) {

        // verificacoes

        // Verifica se senha satisfaz determinadas condicoes
        if(userModel.getPassword() == null || userModel.getPassword().trim().length() == 0) {
            throw new InvalidDataException("Senha inválida!");
        }

        // Verifica se email satisfaz determinadas condicoes
        if(userModel.getEmail() == null || !emailPattern.matcher(userModel.getEmail()).find()) {
            throw new InvalidDataException("Email inválido!");
        }

        // Verifica se firstName satisfaz determinadas condicoes
        if(userModel.getFirstName() == null || userModel.getFirstName().trim().length() == 0) {
            throw new InvalidDataException("Primeiro nome inválido!");
        }

        // Verifica se lastName satisfaz determinadas condicoes
        if(userModel.getLastName() == null || userModel.getLastName().trim().length() == 0) {
            throw new InvalidDataException("Último nome inválido!");
        }

        // Recupera o usuario
        UserModel authUserModel = userService.findByEmail(userModel.getEmail());

        // Verifica se o email já está sendo utilizado
        if(authUserModel != null) {
            throw new InvalidDataException("Já existe um usuario com esse email!");
        }

        userModel.setPassword(userModel.getPassword().trim());

        UserModel userModelRegistered = userService.save(userModel);

        return this.authenticate(userModelRegistered);
    }

    @ApiOperation(value="Autentica um usuário")
    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody UserModel userModel) {

        // Recupera o usuario
        UserModel authUserModel = userService.findByEmail(userModel.getEmail());

        // verificacoes
        if(authUserModel == null || !authUserModel.getPassword().equals(userModel.getPassword().trim())) {
            throw new WrongCredentialsException("Usuario e/ou senha inválidos!");
        }

        String token = Jwts.builder().
                setSubject(authUserModel.getEmail()).
                signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
                setExpiration(new Date(System.currentTimeMillis() + 2 * HOUR))
                .compact();

        return new LoginResponse(token);


    }

    @Data
    private class LoginResponse {
        private String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }
}
