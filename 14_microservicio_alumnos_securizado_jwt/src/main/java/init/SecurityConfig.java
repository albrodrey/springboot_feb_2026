package init;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import init.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Value("${security.db.driver}")
	private String driver;
	@Value("${security.db.url}")
	private String url;
	@Value("${security.db.user}")
	private String user;
	@Value("${security.db.password}")
	private String pass;
	@Value("${jwt.properties.clave}")
	String clave; 
	
	AuthenticationManager auth; 
	@Bean 
	public AuthenticationManager authManager(AuthenticationConfiguration conf) throws Exception{ 
		auth=conf.getAuthenticationManager(); 
		return auth;
	}
	
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
	/*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Bean
	public JdbcUserDetailsManager usersDetailsJdbc() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pass);
		JdbcUserDetailsManager jdbcDetails=new JdbcUserDetailsManager(ds);
		
		jdbcDetails.setUsersByUsernameQuery("select user, pwd, enabled"
           	+ " from users where user=?");
		jdbcDetails.setAuthoritiesByUsernameQuery("select user, rol "
           	+ "from roles where user=?");
		return jdbcDetails;
	}*/
	
	//restricciones de acceso a recursos
	@Bean
	public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception  {		
		http.csrf(cr->cr.disable())
		.authorizeHttpRequests(aut->
				aut.requestMatchers(HttpMethod.POST,"/alumnos").hasRole("ADMINS")
				.requestMatchers(HttpMethod.DELETE,"/alumnos").hasAnyRole("ADMINS","OPERATORS")
				.requestMatchers(HttpMethod.GET,"/alumnos").authenticated()
				.anyRequest().permitAll())
		.addFilter(new JwtAuthenticationFilter(auth, clave));
		return http.build();
		
	}
	
}
