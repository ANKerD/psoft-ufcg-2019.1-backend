package br.edu.ufcg.ccc.andersonjoao.projeto.exception.profile;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {
    public NotFound(String msg) {
        super(msg);
    }
}
