package com.leepon.cloud.service;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Description 权限类型
 * @Author 苏小城
 * @Date 2019/3/31 11:19 AM
 * @Version 1.0
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
