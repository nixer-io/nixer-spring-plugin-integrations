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

    @Bean
    public RequestContextFilter requestContextFilter() {
        return new OrderedRequestContextFilter();
    }


    private Map<String, String> users() {
        final Map<String, String> users = new HashMap<>();
        users.put("existingUser1", "gup5A686zjnVhw+v");
        users.put("existingUser2", "PgwWldkDIP15gZ8u");
        users.put("existingUser3", "HaMaUiG0UyCi1Yot");
        users.put("existingUser4", "wb7ac0Ie/79XS/9Y");
        users.put("existingUser5", "5CLWkPL5bmpGUDBg");
        users.put("existingUser6", "ZyS67ePRz0VdKhyd");
        users.put("existingUser7", "lTqm4co9CdDsylja");
        users.put("existingUser8", "rEN0C+L4RNQBxKAJ");
        users.put("existingUser9", "RYvlC6PCyjb/wfGT");
        users.put("existingUser10", "rDRz3gXbeY0+nGQF");
        users.put("existingUser11", "5BUluVChX1TDhIGQ");
        users.put("existingUser12", "KfpE50i6Y/i9gup3");
        users.put("existingUser13", "Tx9EQhFlbVBGqlnQ");
        users.put("existingUser14", "8tC19h/F+Rnl6d5N");
        users.put("existingUser15", "sRUZcMoghW62stvS");
        users.put("existingUser16", "uxMKMbR4aURGjJda");
        users.put("existingUser17", "Tx9lTtPDHK0epzp0");
        users.put("existingUser18", "qMkDXC/Wiy1I2Mtn");
        users.put("existingUser19", "4bDCOniyko6SgCse");
        users.put("existingUser20", "C0wKe9kmM1IltT75");

        return users;
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
                .when(Conditions::isUserAgentLoginOverThreshold)
                .then(CAPTCHA)
                .buildRule()
                .rule("credentialStuffingActive")
                .when(Conditions::isGlobalCredentialStuffing)
                .then(CAPTCHA)
                .buildRule();
    }
}
