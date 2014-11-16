package br.unisinos.unitunes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Size(min = 6, max = 30, message = "A senha deve ter de 6 a 30 caracteres.")
	private String password;

	@NotNull
	private UserType type;

	@NotNull
	private Status status;

	@ManyToMany
	@JoinTable(name = "USER_PUBLISHED_MEDIAS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "MEDIA_ID") })
	private List<Media> publishedMedias;

	@ManyToMany
	@JoinTable(name = "USER_PURCHASED_MEDIAS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "MEDIA_ID") })
	private List<Media> purchasedMedias;

	@ManyToMany
	@JoinTable(name = "USER_FAVORITE_MEDIAS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "MEDIA_ID") })
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

	public boolean isAdmin() {
		return type == UserType.ADMIN;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Media> getPublishedMedias() {
		return publishedMedias;
	}

	public void setPublishedMedias(List<Media> publishedMedias) {
		this.publishedMedias = publishedMedias;
	}

	public List<Media> getPurchasedMedias() {
		return purchasedMedias;
	}

	public void setPurchasedMedias(List<Media> purchasedMedias) {
		this.purchasedMedias = purchasedMedias;
	}

	public List<Media> getFavoritesMedias() {
		return favoritesMedias;
	}

	public void setFavoritesMedias(List<Media> favoritesMedias) {
		this.favoritesMedias = favoritesMedias;
	}

	public List<Album> getPublishedAlbums() {
		return publishedAlbums;
	}

	public void setPublishedAlbums(List<Album> publishedAlbums) {
		this.publishedAlbums = publishedAlbums;
	}

}
