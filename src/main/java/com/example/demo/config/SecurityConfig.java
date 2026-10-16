package com.example.demo.config;

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

@Configuration  //Indica que la clase contiene la configuracuión de la app
@EnableWebSecurity  //Habilita la seguridad web en la aplicación
public class SecurityConfig {

    //Configura la seguridad de la aplicación, definiendo las reglas de acceso a las rutas y los detalles de autenticación
    @Bean 
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http) throws Exception {
            http //Define que usuarios pueden acceder a cada ruta
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/", "/index", "/index.html", "/login", "/error",
            			"/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
            	.requestMatchers("/h2-console", "/h2-console/**").permitAll()
            	.requestMatchers(HttpMethod.GET, "/products", "/products/search", "/products/filter").permitAll()
            	.requestMatchers("/admin", "/admin/**", "/products/add",
            			"/products/edit/**", "/products/save", "/products/delete/**").hasRole("ADMIN")
            	.requestMatchers("/cart", "/cart/**").permitAll()
            	.anyRequest().authenticated()
            ) 
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/products", false)
                .permitAll()
            ) //configura el cierre de sesión, definiendo la URL de cierre de sesión y la URL a la que se redirige después del cierre de sesión
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
            ) //Configura la protección CSRF y las cabeceras HTTP para permitir el acceso a la consola H2
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(PathRequest.toH2Console())
            ) //permite que la consola H2 se muestre en un iframe, lo cual es necesario para su funcionamiento
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            );

            return http.build();
        }

     
	@Bean
	WebSecurityCustomizer ignoringCustomizer() {
		  return (web) -> web.ignoring().requestMatchers("/h2-console/**");
	}
	
    //Define como se obtienen los datos de los usuarios que pueden autenticarese en la aplicación
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

