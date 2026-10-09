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
                //Permite el acceso público a la página de inicio, al carrito y a las rutas de productos
                .requestMatchers("/", "/cart/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/products", "/products/search", "/products/filter").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()
            ) //Configura la página de inicio de sesión, la redirección después del inicio de sesión y la configuración de cierre de sesión
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/admin", true)
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

