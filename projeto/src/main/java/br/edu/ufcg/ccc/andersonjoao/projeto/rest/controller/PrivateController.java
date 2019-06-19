package br.edu.ufcg.ccc.andersonjoao.projeto.rest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {

    @GetMapping("/private")
    public String privateMsg() {
        return "Mensagem privada";
    }

    @GetMapping("/public")
    public String publicMsg() {
        return "Mensagem publica";
    }
}
