package com.leepon.cloud.config;

import com.leepon.cloud.constant.ConstantKey;
import com.leepon.cloud.service.GrantedAuthorityImpl;
import com.leepon.cloud.util.StringTool;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Description TODO
 * @Author 苏小城
 * @Date 2019/3/31 10:31 AM
 * @Version 1.0
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("token为空");
        }
        String userInfo;
        try {
            userInfo = Jwts.parser()
                    .setSigningKey(ConstantKey.SIGNING_KEY)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody()
                    .getSubject();
            long end = System.currentTimeMillis();
            log.info("执行时间: {}", (end - start) + " 毫秒");
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            if (StringUtils.isNotEmpty(userInfo)) {
                String authorityInfo = userInfo.split("-")[1];
                if (StringUtils.isNotEmpty(authorityInfo)) {
                    String[] split = StringTool.clear(authorityInfo, "[", "]").split(",");
                    for (int i = 0; i < split.length; i++) {
                        authorities.add(new GrantedAuthorityImpl(split[i]));
                    }
                }
                return new UsernamePasswordAuthenticationToken(userInfo, null, authorities);
            }

        } catch (ExpiredJwtException e) {
            logger.error("token已过期: {} " + e);
            throw new RuntimeException("token已过期");
        } catch (UnsupportedJwtException e) {
            logger.error("token格式错误: {} " + e);
            throw new RuntimeException("token格式错误");
        } catch (MalformedJwtException e) {
            logger.error("token没有被正确构造: {} " + e);
            throw new RuntimeException("token没有被正确构造");
        } catch (SignatureException e) {
            logger.error("签名失败: {} " + e);
            throw new RuntimeException("签名失败");
        } catch (IllegalArgumentException e) {
            logger.error("非法参数异常: {} " + e);
            throw new RuntimeException("非法参数异常");
        }

        return null;
    }


}
