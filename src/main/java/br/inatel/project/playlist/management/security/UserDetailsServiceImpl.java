package br.inatel.project.playlist.management.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.inatel.project.playlist.management.domain.Client;
import br.inatel.project.playlist.management.repository.ClientRepository;

//classe de serviço padrão do spring security para consulta e validação da senha do usuario
@Component
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private ClientRepository repo;
	
	public UserDetailsServiceImpl(ClientRepository repo) {
		this.repo = repo;
	}

	//faz a consulta do usuario
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Client>user = repo.findByEmail(userName);
		if(user.isEmpty()) {
			throw new UsernameNotFoundException ("Login email " + userName +"  not found");
		}
		
		return new UserDataDetails(user);
	}

}
