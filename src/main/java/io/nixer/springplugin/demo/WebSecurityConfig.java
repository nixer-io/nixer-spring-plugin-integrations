package io.nixer.springplugin.demo;

import java.util.LinkedHashMap;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Very basic in-memory single user IAM.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        final LinkedHashMap<Class<? extends AuthenticationException>, AuthenticationFailureHandler> loginFailureHandlers = new LinkedHashMap<>();
        loginFailureHandlers.put(LockedException.class, new SimpleUrlAuthenticationFailureHandler("/login?error=LOCKED")); // TODO pass info
        httpSecurity
                .anonymous().and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .and().csrf()
        ;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("user").password(encoder.encode("user")).roles("USER");
    }

}
