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
	@Length(min = 2, max = 40, message = "The length of your name must be between 1 and 40 characters")
	private String name;

	@NotEmpty(message = "filling in this field is mandatory")
	@Email(message = "Invalid email")
	private String email;

	@NotEmpty(message = "filling in this field is mandatory")
	@Length(min = 4, max = 20, message = "The length of your password must be between 4 and 20 characters")
	private String password;

	private String role;

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
		role = obj.getRoles().getDescricao();
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}