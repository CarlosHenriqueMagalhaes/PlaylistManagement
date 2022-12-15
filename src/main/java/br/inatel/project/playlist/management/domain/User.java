package br.inatel.project.playlist.management.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

//	@JsonIgnore
//	@OneToMany(mappedBy = "user")
//	private List<Playlist> playlists = new ArrayList<>();

	@OneToOne(mappedBy = "user")
	private Logon logon;

	// Constructors

	public User() {
		super();
	}

	public User(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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

//	public List<Playlist> getPlaylists() {
//		return playlists;
//	}
//
//	public void setPlaylists(List<Playlist> playlists) {
//		this.playlists = playlists;
//	}

	public Logon getLogon() {
		return logon;
	}

	public void setLogon(Logon logon) {
		this.logon = logon;
	}

	// HashCode and Equals

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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
