package com.example.proov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service    //@Service teeb kogu asja Springile kättesaadavaks
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired  //injectib encoderi, mis on loodud SpringSecurityConfiguration sees
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) { //siia tuleks visata erind
        return User.withUsername("test")    //kasutajanimi
                .password(passwordEncoder.encode("test"))   //parool
                .roles("USER").build(); //roll
    }
}
