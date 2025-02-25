package com.api.BlogAppApi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_POST")
public class BlogAppPostModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, length = 70)
	private String autor;
	
	@Column(nullable = false)
	private LocalDate data;
	
	@Column(nullable = false, length = 70)
	private String titulo;
	
	@Column(columnDefinition = "text")
	private String texto;
	
	@OneToMany
	private List<PostsComentsModel> postsComents;

	public List<PostsComentsModel> getPostsComents() {
		return postsComents;
	}

	public void setPostsComents(List<PostsComentsModel> postsComents) {
		this.postsComents = postsComents;
	}

	public UUID getId() {
		return id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
