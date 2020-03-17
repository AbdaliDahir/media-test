package com.github.throyer.common.springboot.api.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.github.throyer.common.springboot.api.models.entity.Permissao;
import com.github.throyer.common.springboot.api.models.security.Authorized;
import com.github.throyer.common.springboot.api.models.security.Login;
import com.github.throyer.common.springboot.api.models.security.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * TokenService
 */
@Service
public class TokenService {

    @Value("${common.token.expiration-in-hours}")
    private Integer EXPIRATION_TIME_EM_HORAS;

    @Value("${common.token.secret}")
    private String SECRET;

    @Autowired
    private AuthenticationManager authManager;

    private static final String ISSUER = "com.github.common.springboot.api";

    private static final String PERMISSOES_KEY = "roles";
    private static String TIPO_TOKEN = "Bearer";

    public Long getExpirationTimeInSeconds() {
        return (EXPIRATION_TIME_EM_HORAS * 3600L);
    }

    /**
     * Transformar um token JWT em um usuario autenticado.
     * @param accessToken token de acesso.
     * @return usuario autenticado.
     */
    public Authorized toAuthorized(String accessToken) {

        var token = parseToken(accessToken);

        var id = Long.parseLong(token.getBody().getId());

        var username = token.getBody().getSubject();

        var authorities = Arrays.stream(token.getBody().get(PERMISSOES_KEY).toString().split(","))
            .map(Permissao::new)
                .collect(Collectors.toList());

        return new Authorized(username, id, authorities);
    }

    public Token buildToken(Login login) {
        
        /* autenticar usuario */
        var usuarioAutenticado = (Authorized) authManager
            .authenticate(login.toAuthenticationToken())
                .getPrincipal();
        
        /* contruir token */
        return new Token(buildToken(usuarioAutenticado), getExpirationTimeInSeconds(), TIPO_TOKEN);
    }

    /**
     * Contruir Token.
     * @param authorized
     * @return {String} token
     */
    public String buildToken(Authorized authorized) {

        var HOJE = new Date();

        return Jwts.builder()
            .setIssuer(ISSUER)
            .setSubject(authorized.getUsername())
            .setId(authorized.getId().toString())
            .claim(PERMISSOES_KEY, formatarPermissoes(authorized.getAuthorities()))
            .setIssuedAt(HOJE)
            .setExpiration(getExpirationDate())
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }

    /**
     * Gera uma data de expiração.
     * Cria um date que equivale a HOJE + UM DIA
     * @return {Date} mais um dia.
     */
	private Date getExpirationDate() {

        var instant = LocalDateTime
            .now()
            .plusHours(EXPIRATION_TIME_EM_HORAS)
            .atZone(ZoneId.systemDefault())
            .toInstant();
            
        return Date.from(instant);
    }

    /**
     * Faz o parse do Token para Jws<Claims>
     * @param token {string}
     * @return token parseado {Jws<Claims>}
     */
    private Jws<Claims> parseToken(String token) {
        return Jwts.parser()
            .setSigningKey(SECRET)
                .parseClaimsJws(token);
    }

    private String formatarPermissoes(Collection<GrantedAuthority> authorities) {
        return authorities.stream()
            .map(permissao -> permissao.getAuthority())
                .collect(Collectors.joining(","));
    }
}