package init;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	//definición usuarios/roles
	@Bean
	public InMemoryUserDetailsManager  usersDetailsMemory() throws Exception {
		List<UserDetails> users=List.of( 
				User.withUsername("user1")		          
					  .password("{noop}user1")    //lo de {noop} se pone para indicar que no está encriptada  
					  .roles("USERS")
			          .build(),
			    User.withUsername("admin")
			          .password("{noop}admin") 
			          .roles("USERS", "ADMINS")
			          .build(),
			    User.withUsername("user2")
			          .password("{noop}user2")
			          .roles("OPERATORS")
			          .build()
          );
		return new InMemoryUserDetailsManager(users);		
	}
	
	//restricciones de acceso a recursos
	@Bean
	public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception  {		
		http.csrf(cr->cr.disable())
		.authorizeHttpRequests(aut->
				aut.requestMatchers(HttpMethod.POST,"/alumnos").hasRole("ADMINS")
				.requestMatchers(HttpMethod.DELETE,"/alumnos").hasAnyRole("ADMINS","OPERATORS")
				.requestMatchers(HttpMethod.GET,"/alumnos").authenticated()
				.anyRequest().permitAll())
		.httpBasic(Customizer.withDefaults());
		return http.build();
		
	}
	
}
