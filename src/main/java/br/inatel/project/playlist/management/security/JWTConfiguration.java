package br.inatel.project.playlist.management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

//Classe de configuração do JWT
@SuppressWarnings("deprecation")
@EnableWebSecurity
public class JWTConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private final UserDetailsServiceImpl userService;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	public JWTConfiguration(UserDetailsServiceImpl userService , PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
	
	//sobrescrita que  informa para o springsecurity para utilizar UserDetailsServiceImpl
	//e PasswordEncoder para validar nossa senha
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);	
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http.csrf()
		.disable()//protege contra ataques em desenvolvimento  disable, em produção enable
		.authorizeRequests().antMatchers(HttpMethod.POST,"/login").permitAll()//padrão para fazer o login do user
		.antMatchers(HttpMethod.POST,"/users").permitAll()
		.anyRequest().authenticated().and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
		.addFilter(new JWTValidateFilter(authenticationManager()))
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	//escopo do método acima que não esta depreciado:
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests((authz) -> authz
//                .anyRequest().authenticated()
//            )
//            .httpBasic(withDefaults());
//        return http.build();
//    }
	
	@Bean
	UrlBasedCorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source; 	
	}

	//O do curso estava assim (ele retorna erro no return source;):
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//		source.registerCorsConfiguration("/**", corsConfiguration);
//		return source; 	
//	}

}


