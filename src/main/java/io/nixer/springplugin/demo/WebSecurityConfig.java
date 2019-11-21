package io.nixer.springplugin.demo;

import java.util.HashMap;
import java.util.Map;

import io.nixer.nixerplugin.captcha.config.CaptchaConfigurer;
import io.nixer.nixerplugin.captcha.security.CaptchaChecker;
import io.nixer.nixerplugin.core.detection.filter.FilterConfiguration;
import io.nixer.nixerplugin.core.detection.filter.behavior.Conditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.RequestContextFilter;

import static io.nixer.nixerplugin.core.detection.filter.behavior.Behaviors.*;

/**
 * Very basic in-memory single user IAM.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CaptchaChecker captchaChecker;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(
                        "/static/**"
                ).permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutUrl("/logout").permitAll()
        ;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        var configurer = auth.inMemoryAuthentication().passwordEncoder(encoder);

        users().forEach((user, pass) -> configurer.withUser(user).password(encoder.encode(pass)).roles("USER"));

        configurer.withObjectPostProcessor(new CaptchaConfigurer(captchaChecker));
    }


    private Map<String, String> users() {
        final Map<String, String> users = new HashMap<>();
        users.put("user", "user");
        users.put("user1", "user1");
        users.put("user2", "user2");
        return users;
    }

    @Bean
    public RequestContextFilter requestContextFilter() {
        return new OrderedRequestContextFilter();
    }

    /**
     * Configures rules. Rules define what happens at what conditions.
     */
    @Bean
    public FilterConfiguration.BehaviorProviderConfigurer behaviorConfigurer() {
        return builder -> builder
                .rule("blacklistedIp")  // we want to hide fact that request was blocked. So pretending regular login error.
                .when(Conditions::isBlacklistedIp)
                .then(BLOCKED_ERROR)
                .buildRule()
                .rule("ipLoginOverThreshold")
                .when(Conditions::isIpLoginOverThreshold)
                .then(CAPTCHA)
                .buildRule()
                .rule("userAgentLoginOverThreshold")
                .when(Conditions::isIpLoginOverThreshold)
                .then(CAPTCHA)
                .buildRule()
                .rule("credentialStuffingActive")
                .when(Conditions::isGlobalCredentialStuffing)
                .then(CAPTCHA)
                .buildRule();
    }
}
