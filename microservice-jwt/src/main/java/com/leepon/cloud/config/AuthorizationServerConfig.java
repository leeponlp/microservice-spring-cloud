//package com.leepon.cloud.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//

/**
 * @task:
 * @discrption:
 * @author: 苏小城
 * @date: 2017/04/28 10:25
 * @version: 1.0.0
 */
//@Configuration
//@EnableAuthorizationServer
//@Slf4j
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    public static final String AUTH_SIGN_KEY_NAME = "jwt.key";
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(tokenStore())
//                .accessTokenConverter(accessTokenConverter())
//                .authenticationManager(authenticationManager);
//    }
//
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("secret")
//                .secret("secret")
//                .authorizedGrantTypes("client_credentials", "password")
//                .authorities("ROLE_TRUSTED_CLIENT")
//                .scopes("read", "write")
//                .accessTokenValiditySeconds(3600);
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        String key = "";
//        log.info("===============私钥信息:{}===============:",key);
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(key);
//        return converter;
//    }
//
//
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setSupportRefreshToken(true);
//        defaultTokenServices.setAccessTokenValiditySeconds(28800);
//        return defaultTokenServices;
//    }
//
//}
