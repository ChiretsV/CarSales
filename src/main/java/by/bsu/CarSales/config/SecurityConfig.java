package by.bsu.CarSales.config;

import by.bsu.CarSales.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/index.html#/").permitAll()
                .antMatchers("/registration/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/car/{id}", "car/all", "car/update", "car/delete", "car/create").hasAnyRole("USER", "ADMIN")
                .antMatchers("/user/update", "/user/delete").hasAnyRole("USER", "ADMIN")
                .antMatchers("/user/{id}", "/user/delete").hasAnyRole("USER", "ADMIN")
                .antMatchers("/user/all").hasRole ("ADMIN")
                .antMatchers("/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
