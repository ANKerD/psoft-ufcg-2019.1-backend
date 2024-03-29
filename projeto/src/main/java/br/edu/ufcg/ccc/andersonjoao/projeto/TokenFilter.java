package br.edu.ufcg.ccc.andersonjoao.projeto;


import br.edu.ufcg.ccc.andersonjoao.projeto.exception.token.InvalidTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer ")) {
            throw new InvalidTokenException("Token inexistente ou mal formatado!");
        }

        // Extraindo apenas o token do cabecalho.
        String token = header.substring(7);

        try {
            Jwts.parser().setSigningKey("banana").parseClaimsJws(token).getBody();
        }catch(SignatureException e) {
            throw new InvalidTokenException("Token invalido ou expirado!");
        }

        chain.doFilter(request, response);
    }

}

