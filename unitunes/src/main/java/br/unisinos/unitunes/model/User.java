package br.unisinos.unitunes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.unisinos.unitunes.infra.impl.GenericModel;

@Entity
@Table(name = "USERS")
public class User extends GenericModel {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;

	@NotNull
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "E-mail não é valido.")
	private String email;

	@NotNull
	private String password;

	@NotNull
	private UserType type;

	@ManyToMany
	private List<Media> publishedMedias;

	@ManyToMany
	private List<Media> purchasedMedias;

	@ManyToMany
	private List<Media> favoritesMedias;

	@OneToMany(mappedBy = "author")
	private List<Album> publishedAlbums;

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

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

}
