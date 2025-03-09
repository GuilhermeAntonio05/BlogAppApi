package com.api.BlogAppApi.model;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_POSTCOMENTARIO")
public class PostsComentsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate data;

	@Lob
	@Column(columnDefinition = "text")
	private String comentario;

	@ManyToOne
	@JsonBackReference
	private BlogAppPostModel blogAppPostModel;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public BlogAppPostModel getBlogAppPostModel() {
		return blogAppPostModel;
	}

	public void setBlogAppPostModel(BlogAppPostModel blogAppPostModel) {
		this.blogAppPostModel = blogAppPostModel;
	}

	public UUID getId() {
		return id;
	}
}