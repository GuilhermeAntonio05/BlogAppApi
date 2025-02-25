package com.api.BlogAppApi.Controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.BlogAppApi.Dtos.BlogAppRecordDto;
import com.api.BlogAppApi.Dtos.PostComentsDto;
import com.api.BlogAppApi.model.BlogAppPostModel;
import com.api.BlogAppApi.model.PostsComentsModel;
import com.api.BlogAppApi.service.BlogAppPostService;
import com.api.BlogAppApi.service.PostComentService;

import jakarta.validation.Valid;

@RestController
public class BlogAppController {
	@Autowired
	BlogAppPostService blogAppPostService;

	@Autowired
	PostComentService postComentService;

	@GetMapping(value = "/posts") // value não é necessário
	public ResponseEntity<List<BlogAppPostModel>> getAllPosts() {
		List<BlogAppPostModel> posts = blogAppPostService.findAll();

		if (posts.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(posts);
		}

		return ResponseEntity.status(HttpStatus.OK).body(posts);
	}

	@GetMapping(value = "/posts/{id}") // value não é necessário
	public ResponseEntity<Object> getPostDetails(@PathVariable("id") UUID id) {
		Optional<BlogAppPostModel> optional = blogAppPostService.findById(id);

		if (!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}

	@PostMapping(value = "/newpost")
	public ResponseEntity<Object> savePost(@RequestBody @Valid BlogAppRecordDto blogAppRecordDto) {
		var postModel = new BlogAppPostModel();
		BeanUtils.copyProperties(blogAppRecordDto, postModel);
		postModel.setData(LocalDate.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(blogAppPostService.save(postModel));
	}

	@PostMapping(value = "/posts/{id}")
	public ResponseEntity<Object> savePost(@PathVariable("id") UUID id,
			@RequestBody @Valid PostComentsDto postComentsDto) {

		var postComentario = new PostsComentsModel();

		Optional<BlogAppPostModel> blogAppPostModelOptional = blogAppPostService.findById(id);
		BlogAppPostModel post = blogAppPostModelOptional.get();

		BeanUtils.copyProperties(postComentsDto, postComentario);
		postComentario.setBlogAppPostModel(post);
		postComentario.setData(LocalDate.now());

		return ResponseEntity.status(HttpStatus.CREATED).body(postComentService.save(postComentario));
	}

	@DeleteMapping(value = "/posts/{id}") // value não é necessário
	public ResponseEntity<Object> deletePost(@PathVariable("id") UUID id) {
		Optional<BlogAppPostModel> posts = blogAppPostService.findById(id);

		if (!posts.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog not found");
		}
		blogAppPostService.delete(posts.get());
		return ResponseEntity.status(HttpStatus.OK).body("Blog deleted successfully");
	}

	@PutMapping("/posts/{id}")
	public ResponseEntity<Object> updatePost(@PathVariable("id") UUID id,
			@RequestBody @Valid BlogAppRecordDto updatedPost) {

		// Pesquisa se existe o post
		Optional<BlogAppPostModel> existingPostOptional = blogAppPostService.findById(id);

		if (existingPostOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog post not found.");
		}

		// essa linha faz com que a variável existingPost receba o post pesquisado
		BlogAppPostModel existingPost = existingPostOptional.get();

		// Estas linhas garantem que o post requisitado seja atualizado
		// com a descrição do novo post
		existingPost.setAutor(updatedPost.autor());
		existingPost.setTitulo(updatedPost.titulo());
		existingPost.setTexto(updatedPost.texto());
		existingPost.setData(LocalDate.now());
		return ResponseEntity.ok(blogAppPostService.save(existingPost));
	}
}
