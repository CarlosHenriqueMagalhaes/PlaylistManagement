package br.inatel.project.playlist.management.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Logon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(unique = true) // essa anotação garante que esse campo não tera repetição
	private String email;

	private String password;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	// Constructors

	public Logon() {
		super();
	}

	public Logon(Integer id, User user, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.user=user;
	}

	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Hash Code and Equals

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Logon other = (Logon) obj;
		return Objects.equals(id, other.id);
	}

}
