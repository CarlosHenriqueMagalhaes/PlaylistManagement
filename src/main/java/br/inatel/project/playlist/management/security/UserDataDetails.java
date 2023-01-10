//package br.inatel.project.playlist.management.security;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Optional;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import br.inatel.project.playlist.management.domain.Client;
//
////Aqui estão os dados do usuário no padrao do spring security
//public class UserDataDetails implements UserDetails{
//	private static final long serialVersionUID = 1L;
//	
//	//recebe o usuário da classe domain
//	private final Optional<Client> user;
//	
//	public UserDataDetails(Optional<Client> user) {
//		this.user = user;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return new ArrayList<>();
//	}
//
//	@Override
//	public String getPassword() {
//		return user.orElse(new Client()).getPassword();
//		//or.Else para se o usuario for vazio sempre retornar um usuario vazio (impede o nullpointexception)
//	}
//	
//	@Override
//	public String getUsername() {
//		return user.orElse(new Client()).getEmail();
//		//or.Else para se o usuario for vazio sempre retornar um usuario vazio (impede o nullpointexception)
//	}
//
//	// Verifica se a conta do usuário não expirou
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	// Verifica se a conta do usuário não esta bloqueada
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	// Verifica se a credencial do  usuário não foi expirada
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	// Verifica se o usuário esta ativo
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//}
