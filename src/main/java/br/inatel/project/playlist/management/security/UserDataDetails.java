package br.inatel.project.playlist.management.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.inatel.project.playlist.management.domain.Client;

public class UserDataDetails implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private final Optional<Client> user;
	
	public UserDataDetails(Optional<Client> user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return user.orElse(new Client()).getPassword();
	}

	@Override
	public String getUsername() {
		return user.orElse(new Client()).getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
