package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration 
@EnableWebSecurity 
public class SecurityConfig {

    @Bean 
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http) throws Exception {
            http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/cart/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/products").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/admin", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(PathRequest.toH2Console())
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            );

            return http.build();
        }

     
	@Bean
	WebSecurityCustomizer ignoringCustomizer() {
		  return (web) -> web.ignoring().requestMatchers("/h2-console/**");
	}
	

	@Bean
	public UserDetailsService users() {
	    UserDetails admin = User.withDefaultPasswordEncoder()
	       .username("admin")
	       .password("admin")
	       .roles("ADMIN")
	       .build();
	    UserDetails user = User.withDefaultPasswordEncoder()
	       .username("user").password("user").roles("USER").build();
	    return new InMemoryUserDetailsManager(admin, user);
	}
	
}

