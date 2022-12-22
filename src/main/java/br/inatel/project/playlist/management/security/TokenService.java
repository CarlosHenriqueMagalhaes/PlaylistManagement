//package br.inatel.project.playlist.management.security;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//
//import br.inatel.project.playlist.management.domain.Client;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//public class TokenService  {
//	
//	@Value("${forum.jwt.expiration}")
//	private String expiration;
//	
//	@Value("${forum.jwt.secret}")
//	private String secret;
//
//	public String gerarToken(Authentication authentication) {
//		Client logado = (Client) authentication.getPrincipal();
//		Date hoje = new Date();
//		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
//		
//		return Jwts.builder()
//				.setIssuer("API do Fórum da Alura")
//				.setSubject(logado.getId().toString())
//				.setIssuedAt(hoje)
//				.setExpiration(dataExpiracao)
//				.signWith(SignatureAlgorithm.HS256, secret)
//				.compact();
//	}
//	
//	public boolean isTokenValido(String token) {
//		try {
//			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
//
//	public Long getIdUsuario(String token) {
//		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
//		return Long.parseLong(claims.getSubject());
//	}
//
//}
//
