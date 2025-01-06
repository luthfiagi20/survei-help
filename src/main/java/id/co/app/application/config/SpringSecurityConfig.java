package id.co.app.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CustomAuthProvider authProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/simple-captcha-endpoint").permitAll()
                .antMatchers("/login_check_data").permitAll()
                .antMatchers("/pagejs/**").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/register").permitAll()
                .antMatchers("https://docs.google.com/forms/**").permitAll()
                .antMatchers("/simpan-register").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl("/login_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/")//
                .failureUrl("/login?error=3")//
                .usernameParameter("akun")//
                .passwordParameter("pass")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
                .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(authProvider);
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

}
