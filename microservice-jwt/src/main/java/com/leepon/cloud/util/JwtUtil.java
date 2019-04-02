package com.leepon.cloud.util;

import com.leepon.cloud.constant.ConstantKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description TODO...
 * @Author 苏小城
 * @Date 2019-4-2 10:09
 * @Version 1.0
 */
public class JwtUtil {

    private static final Long expiration = 30202011L;

    /**
     * 解析token
     * @param jsonWebToken
     * @return
     */
    public static Claims parseToken(String jsonWebToken) {

        Claims claims = Jwts.parser().setSigningKey(ConstantKey.SIGNING_KEY)
                .parseClaimsJws(jsonWebToken).getBody();
        return claims;

    }

    /**
     * 新建token

     * @param audience
     * @return
     */
    public static String createToken(String audience) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setAudience(audience)
                .signWith(SignatureAlgorithm.HS512, ConstantKey.SIGNING_KEY);

        // 添加Token签发时间
        builder.setIssuedAt(now);
        // 添加Token过期时间
        if (expiration >= 0) {
            long expMillis = nowMillis + expiration;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        // 生成JWT
        return builder.compact();
    }

    /**
     * 刷新令牌
     *
     * @param claims
     * @return
     */
    public static String refreshToken(Claims claims) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setIssuer((String) claims.get("iss")).setAudience((String) claims.get("aud"))
                .signWith(SignatureAlgorithm.HS512, ConstantKey.SIGNING_KEY);

        // 添加Token签发时间
        builder.setIssuedAt(now);
        // 添加Token过期时间
        if (expiration >= 0) {
            long expMillis = nowMillis + expiration;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        // 生成Token
        return builder.compact();
    }


    public static void main(String[] args) {

        String token = createToken("hello jwt");
        System.err.println("密文信息： "+token);
        String audience = parseToken(token).getAudience();
        System.err.println("明文信息： "+audience);

    }


}
