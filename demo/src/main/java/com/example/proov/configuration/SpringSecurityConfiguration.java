package com.example.proov.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity  //ütleb Springile, et pane kogu security stuff tööle
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()    //autoriseeri päringud, mis tehakse...
                .antMatchers("/").permitAll()   // ...'/' ja '/home' pihta
//                .anyRequest().authenticated()   //any other must be authenticated
                .anyRequest().permitAll()       //now all is permitted
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.cors();
        http.csrf().disable();   //kui seda poleks, siis get päringuid saab teha, aga postida ei saa
    }

    @Bean   //sellega ütleme, et nii saab luua encoderit
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
