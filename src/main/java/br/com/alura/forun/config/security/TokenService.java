package br.com.alura.forun.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${api.jwt.expiration}")
    private  String expiration;

    @Value("${api.jwt.secret}")
    private  String secret;

    public String gerarToken(Authentication authenticate, String usuario) {

        Date hj = new Date();
        Date dataExperacao = new Date(hj.getTime()+Long.parseLong(expiration) );

        Object logado =  authenticate.getPrincipal();
        return Jwts.builder().setIssuer("API de Teste ALURA")
                .setSubject(usuario)
                .setIssuedAt(hj)
                .setExpiration(dataExperacao)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public boolean isTokenValido(String toke) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(toke);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getEmail(String toke) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(toke).getBody();

        return claims.getSubject();
    }
}
