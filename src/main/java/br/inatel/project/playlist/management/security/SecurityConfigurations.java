//package br.inatel.project.playlist.management.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfigurations{
//
////	@Autowired
////	private AuthenticationService autenticacaoService ;
////	
////	@Autowired
////	private TokenService tokenService;
////	
////	@Autowired
////	private ClientRepository clientRepository;
////	
////	@Override
////	@Bean
////	protected AuthenticationManager authenticationManager() throws Exception {
////		return super.authenticationManager();
////	}
//	
//	//Configuracoes de autenticacao
//	@Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//	
//	//Configuracoes de autorizacao
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/users").permitAll()
//		.antMatchers(HttpMethod.GET, "/users/*").permitAll()
//		.antMatchers(HttpMethod.POST, "/users").permitAll()
//		.antMatchers(HttpMethod.GET, "/users").permitAll();
////		.anyRequest().authenticated()
////		.and().csrf().disable()
////		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		//.and().addFilterBefore(new AuthenticationTokenFilter(tokenService, clientRepository), UsernamePasswordAuthenticationFilter.class);
//	}
//	
//	
//	//Configuracoes de recursos estaticos(js, css, imagens, etc.)
//	public void configure(WebSecurity web) throws Exception {
//		//web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
//	}
//	
//}
