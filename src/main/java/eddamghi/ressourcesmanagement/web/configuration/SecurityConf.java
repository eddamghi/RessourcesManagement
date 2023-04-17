package eddamghi.ressourcesmanagement.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConf {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("Admin").password(passwordEncoder.encode("5393")).roles("ADMIN","USER").build(),
                User.withUsername("User").password(passwordEncoder.encode("1370")).roles("USER").build()
        );
    }
    // bean annotation means that this method will run when the application starts
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpsecurity) throws Exception {
        httpsecurity.formLogin().loginPage("/login").permitAll();
        httpsecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();
        httpsecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
        httpsecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated();
        httpsecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        return httpsecurity.build();
    }

}
