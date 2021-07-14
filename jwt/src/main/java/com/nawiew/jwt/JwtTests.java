package com.nawiew.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

/**
 * @author nawiew
 * @packageName:com.nawiew.jwt
 * @ClassName:JwtTests
 * @Description:
 * @date 2021/7/7 18:07
 */
public class JwtTests {
    //过期时间，毫秒，24小时
    private static long tokenExpiration=24*60*60*1000;
    //秘钥
    private static String tokenSignKey="nawiew-jwt";

    @Test
    public void testCreateToken(){
        String token= Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .setSubject("guli-user")
                .setIssuer("nawiew")
                .setAudience("nawiew")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+tokenExpiration))
                .setNotBefore(new Date(System.currentTimeMillis()+20*1000))
                .setId(UUID.randomUUID().toString())

                .claim("nickname","nawiew")
                .claim("avatar","1.jpg")

                .signWith(SignatureAlgorithm.HS256, tokenSignKey)
                .compact();

        System.out.println(token);
    }

    @Test
    public void testGetUserInfo(){

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndWxpLXVzZXIiLCJpc3MiOiJuYXdpZXciLCJhdWQiOiJuYXdpZXciLCJpYXQiOjE2MjU2NTMzNzgsImV4cCI6MTYyNTczOTc3OCwibmJmIjoxNjI1NjUzMzk4LCJqdGkiOiI1Y2RmNGRlZi01MGE3LTQ5MDYtODE3Mi0yZDJmYjU4ZWQxZjMiLCJuaWNrbmFtZSI6Im5hd2lldyIsImF2YXRhciI6IjEuanBnIn0._SBUhmJLUxrrQff1TTvw5NZYivz2gst1sZZqPQVwYPw";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);

        Claims claims = claimsJws.getBody();

        String subject = claims.getSubject();
        String issuer = claims.getIssuer();
        String audience = claims.getAudience();
        Date issuedAt = claims.getIssuedAt();
        Date expiration = claims.getExpiration();
        Date notBefore = claims.getNotBefore();
        String id = claims.getId();

        System.out.println(subject);
        System.out.println(issuer);
        System.out.println(audience);
        System.out.println(issuedAt);
        System.out.println(expiration);
        System.out.println(notBefore);
        System.out.println(id);;

        String nickname = (String)claims.get("nickname");
        String avatar = (String)claims.get("avatar");

        System.out.println(nickname);
        System.out.println(avatar);
    }



















}
