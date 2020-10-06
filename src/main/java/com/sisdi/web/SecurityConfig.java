package com.sisdi.web;

import com.sisdi.data.UserService;
import com.sisdi.model.Usuario;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers("/addOffice" )
//                    .hasRole("USER")
//                .antMatchers("/")
//                    .hasRole("USER")
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                ;
//    }
}
