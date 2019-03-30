package com.gildedrose.web.configuration;

import com.gildedrose.web.authentication.CustomAuthenticationProvider;
import com.gildedrose.web.authentication.LoginPageFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;


@Configuration
@EnableWebSecurity
@ComponentScan("com.gildedrose.web.authentication")
//@Profile("!https")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.addFilterBefore(new LoginPageFilter(), DefaultLoginPageGeneratingFilter.class);
        // @formatter:off

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").fullyAuthenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*").not().authenticated()
                .antMatchers(HttpMethod.GET ,"/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
//                .failureHandler(customAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
//                .logoutSuccessHandler(logoutSuccessHandler())
        ;

        http.headers()
                .frameOptions()
                .sameOrigin()
                .defaultsDisabled()
                .cacheControl();

    }

}