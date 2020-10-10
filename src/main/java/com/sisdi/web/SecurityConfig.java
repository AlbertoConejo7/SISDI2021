package com.sisdi.web;

import com.sisdi.data.UserService;
import com.sisdi.model.Usuario;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//Login 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
        UserService list=new UserService();
        for(Usuario u:list.getUsuarios().values()){
            auth.inMemoryAuthentication()
                    .withUser(u.getTempUser().getEmail())
                    .password("{noop}"+u.getPassword())
                    .roles("USER");
        }
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/editOffice/**", "/addOffice", "/listOffices/**", "/versionOffice")
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
