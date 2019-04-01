package com.leepon.cloud.service;

import com.leepon.cloud.dto.User;
import com.leepon.cloud.repository.UserRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Description TODO
 * @Author 苏小城
 * @Date 2019/3/31 3:48 PM
 * @Version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        if (StringUtils.isNotEmpty(user.getAuthorities())) {
            List<String> strings = Arrays.asList(StringUtils.split(user.getAuthorities(), ","));
            if (CollectionUtils.isNotEmpty(strings)) {
                strings.forEach(p -> authorities.add(new GrantedAuthorityImpl(p)));
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
