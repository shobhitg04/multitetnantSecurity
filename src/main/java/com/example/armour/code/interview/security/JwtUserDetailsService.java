package com.example.armour.code.interview.security;

import com.example.armour.code.interview.tenant.entity.User;
import com.example.armour.code.interview.tenant.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if(null == user){
            throw new UsernameNotFoundException("Invalid user name or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {

        Set<SimpleGrantedAuthority> authorities = new HashSet();
        user.getRoles().stream().forEach(role ->{
                    Set<SimpleGrantedAuthority> simpleAuths = role.getPermissions().stream().map(permission ->{
                        return  new SimpleGrantedAuthority(permission.getName());
                    }).collect(Collectors.toSet());
                    authorities.addAll(simpleAuths);
                }
        );

        return authorities;
    }
}
