package com.sisdi.web;

import com.sisdi.data.UserData;
import com.sisdi.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;
//Login 
@Configuration
@EnableWebSecurity
@Service
public class SecurityConfig extends WebSecurityConfigurerAdapter{  
    @Autowired
    private UserData userData;
    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
        this.userData.init();
        for(Usuario u:userData.getUsuarios().values()){
            auth.inMemoryAuthentication()
                    .withUser(u.getTempUser().getEmail())
                    .password("{noop}"+u.getPassword())
                    .roles("USER");
        }
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/addOffice", "/listOffices/**", "/versionOffice", "/pendingOffice", "/editOffice/**")
                    .permitAll()
                    .antMatchers("/")
                    .hasRole("USER")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }
}
