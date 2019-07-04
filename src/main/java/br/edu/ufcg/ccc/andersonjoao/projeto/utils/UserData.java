package br.edu.ufcg.ccc.andersonjoao.projeto.utils;

import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;

public class UserData {
    public static String getUserEmail(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return Jwts.parser().setSigningKey("banana").parseClaimsJws(token).getBody().getSubject();
    }
}
