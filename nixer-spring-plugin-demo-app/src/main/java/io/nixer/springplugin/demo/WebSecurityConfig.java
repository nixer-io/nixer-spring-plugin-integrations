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

import static io.nixer.nixerplugin.core.detection.filter.behavior.Behaviors.CAPTCHA;

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
        users.put("existingUser21", "eatzrttmaz");
        users.put("existingUser22", "qblbwlhsaa");
        users.put("existingUser23", "vclxjcxjuq");
        users.put("existingUser24", "agfiaxicke");
        users.put("existingUser25", "tjfjovfznh");
        users.put("existingUser26", "xuhjufapqy");
        users.put("existingUser27", "spxneoyjnp");
        users.put("existingUser28", "twagoxlbjy");
        users.put("existingUser29", "mlvovvabvy");
        users.put("existingUser30", "cycryclprc");
        users.put("existingUser31", "rmbvxlwxwp");
        users.put("existingUser32", "fgihjlxwrz");
        users.put("existingUser33", "qzweliazjd");
        users.put("existingUser34", "grvxnqvtrk");
        users.put("existingUser35", "urjtvwoyvl");
        users.put("existingUser36", "uafsqirblm");
        users.put("existingUser37", "mrhxsivnni");
        users.put("existingUser38", "dedzevkynl");
        users.put("existingUser39", "zmxsfyybzf");
        users.put("existingUser40", "zjguvprmvp");
        users.put("existingUser41", "unkaizwqgt");
        users.put("existingUser42", "zvgadsyyij");
        users.put("existingUser43", "jlefeqykuw");
        users.put("existingUser44", "whjammsiep");
        users.put("existingUser45", "iawqgomchp");
        users.put("existingUser46", "ribbidhmbr");
        users.put("existingUser47", "hcrhjvviba");
        users.put("existingUser48", "xwvevukcda");
        users.put("existingUser49", "ecxwkyiygl");
        users.put("existingUser50", "skddurtvpt");
        users.put("existingUser51", "gcyugslpcd");
        users.put("existingUser52", "izexwpwylv");
        users.put("existingUser53", "juwoblwfwp");
        users.put("existingUser54", "cigboczhun");
        users.put("existingUser55", "gqqjynmmja");
        users.put("existingUser56", "inmzhawgja");
        users.put("existingUser57", "xazqspakqk");
        users.put("existingUser58", "qixtodmczl");
        users.put("existingUser59", "bjdqjfnoib");
        users.put("existingUser60", "efcspsaytq");
        users.put("existingUser61", "wghphdoicv");
        users.put("existingUser62", "zihbgmlfpn");
        users.put("existingUser63", "sugpduxcuh");
        users.put("existingUser64", "ivgchimbnj");
        users.put("existingUser65", "hgnpkcllwm");
        users.put("existingUser66", "fubbuauuqq");
        users.put("existingUser67", "hvvbnqlpkk");
        users.put("existingUser68", "zbiooacmde");
        users.put("existingUser69", "stejuizopb");
        users.put("existingUser70", "ulzuaxpkxw");
        users.put("existingUser71", "tswuqqyksf");
        users.put("existingUser72", "onmuyxapyf");
        users.put("existingUser73", "sclcbsjsrx");
        users.put("existingUser74", "awvntqhldv");
        users.put("existingUser75", "cftxsfyyzy");
        users.put("existingUser76", "ogqmahmwzy");
        users.put("existingUser77", "xyngwpdhbs");
        users.put("existingUser78", "hdyxbzhrxt");
        users.put("existingUser79", "ybxiduqkni");
        users.put("existingUser80", "vxjjgmkcsb");
        users.put("existingUser81", "eijsawqpzw");
        users.put("existingUser82", "mdqglagxss");
        users.put("existingUser83", "gzlpdxkaxh");
        users.put("existingUser84", "lbbymvsnby");
        users.put("existingUser85", "mbrvfwzlix");
        users.put("existingUser86", "oqlstzejgb");
        users.put("existingUser87", "zoxcwsedad");
        users.put("existingUser88", "fcgnojsyuo");
        users.put("existingUser89", "ohpagkihcz");
        users.put("existingUser90", "ovnjylnfhm");
        users.put("existingUser91", "hqhdsuspbr");
        users.put("existingUser92", "kdogfaptiw");
        users.put("existingUser93", "lappvqzesp");
        users.put("existingUser94", "aieksfmnxn");
        users.put("existingUser95", "iznzwyuaon");
        users.put("existingUser96", "qyyhioczxo");
        users.put("existingUser97", "htqzwekbdn");
        users.put("existingUser98", "grdgxcqdex");
        users.put("existingUser99", "ywljpdvudw");

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
                .then(CAPTCHA)
                .buildRule()
                .rule("ipLoginOverThreshold")
                .when(Conditions::isIpLoginOverThreshold)
                .then(CAPTCHA)
                .buildRule()
                .rule("userAgentLoginOverThreshold")
                .when(Conditions::isUserAgentLoginOverThreshold)
                .then(CAPTCHA)
                .buildRule()
                .rule("usernameLoginOverThreshold")
                .when(Conditions::isUsernameLoginOverThreshold)
                .then(CAPTCHA)
                .buildRule()
                .rule("credentialStuffingActive")
                .when(Conditions::isGlobalCredentialStuffing)
                .then(CAPTCHA)
                .buildRule();
    }
}
