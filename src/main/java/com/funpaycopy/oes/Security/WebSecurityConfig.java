package com.funpaycopy.oes.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {return new BCryptPasswordEncoder(8);}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/reg", "/search/**", "/css/**", "/usericons/**", "/api/**").permitAll().
                antMatchers(HttpMethod.GET, "/profile/**", "/item/**").permitAll().
                antMatchers(HttpMethod.POST,  "/profile/**").hasAnyAuthority("USER", "ADMIN", "MODERATOR").
                antMatchers("/profileEDT", "/buy/**", "/searchBuy/**", "/profile", "/thanku", "/rights/**", "/support/**", "/linkPhoto", "/filePhoto").
                hasAnyAuthority("USER", "ADMIN", "MODERATOR").
                antMatchers(HttpMethod.POST, "/item").hasAuthority("SELLER").
                antMatchers("/requests/**", "/users/**").hasAnyAuthority("MODERATOR", "ADMIN").
                antMatchers("/backup", "/restore").hasAuthority("ADMIN").
                anyRequest().
                fullyAuthenticated().and().
                formLogin().loginPage("/login").permitAll().and().
                logout().permitAll().and().
                csrf().disable().cors().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).
                passwordEncoder(getPasswordEncoder()).
                usersByUsernameQuery("SELECT login, password, active FROM user WHERE login = ?").
                authoritiesByUsernameQuery("SELECT u.login, ur.roles FROM user u INNER JOIN user_roles ur on u.id_user = ur.user_id WHERE login = ?");
    }
}
