package br.inatel.project.playlist.management.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.inatel.project.playlist.management.domain.Client;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Valid
	private Integer id;

	@NotEmpty(message = "filling in this field is mandatory")
	@Length(min = 2, max = 80, message = "The length of your name must be between 1 and 80 characters")
	private String name;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email (message = "Invalid email")
	private String email;

	@NotEmpty(message = "filling in this field is mandatory")
	@Length(min = 4, max = 10, message = "The length of your passwort must be between 4 and 10 characters")
	private String password;

	// Constructors

	public ClientDTO() {
		super();
	}

	public ClientDTO(Client obj) {
		super();
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
		password = obj.getPassword();
	}

	// Getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	

}