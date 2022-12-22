//package br.inatel.project.playlist.management.security;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import br.inatel.project.playlist.management.domain.Client;
//import br.inatel.project.playlist.management.repository.ClientRepository;
//
//public class AuthenticationService implements UserDetailsService {
//	
//	@Autowired
//	private ClientRepository repository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional <Client> usuario = repository.findByListEmail(username);
//		if (usuario.isPresent()) {
//			return usuario.get();
//		}
//		
//		throw new UsernameNotFoundException("Dados inválidos!");
//	}
//
//}
