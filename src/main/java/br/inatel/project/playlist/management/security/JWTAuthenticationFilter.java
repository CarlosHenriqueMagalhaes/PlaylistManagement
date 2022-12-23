package br.inatel.project.playlist.management.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.inatel.project.playlist.management.domain.Client;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	//30 minutos
	public static final int TOKEN_EXPIRATION = 1800_000;
	
	//usado gerador no site https://guidgenerator.com
	public static final String TOKEN_KEY = "67a49ebb-186a-40cd-9cc2-56bcc8b4f787";//aberto por questões de desenvolvimento 
	//transportar para o arquivo de configuração
	

	private final AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Client user = new ObjectMapper().readValue(request.getInputStream(), Client.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),
					user.getPassword(),new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException("Failed to authenticate user", e);
		}
	}
	
	@Override
	protected void successfulAuthentication (HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult ) throws IOException, ServletException{
		UserDataDetails userData = (UserDataDetails) authResult.getPrincipal();
		
		String token = JWT.create().withSubject(userData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+ TOKEN_EXPIRATION))
				.sign(Algorithm.HMAC512(TOKEN_KEY));
		
		response.getWriter().write(token);
		response.getWriter().flush();
		
		}
}
