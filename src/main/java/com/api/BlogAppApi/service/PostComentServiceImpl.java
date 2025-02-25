package com.api.BlogAppApi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.BlogAppApi.model.PostsComentsModel;
import com.api.BlogAppApi.repository.PostsComentsRepository;

@Service
public class PostComentServiceImpl implements PostComentService {

	@Autowired
	PostsComentsRepository postsComentsRepository;

	@Override
	public PostsComentsModel save(PostsComentsModel postsComents) {
		return postsComentsRepository.save(postsComents);
	}

	@Override
	public List<PostsComentsModel> findAll() {
		return postsComentsRepository.findAll();
	}

	@Override
	public Optional<PostsComentsModel> findById(UUID id) {
		return postsComentsRepository.findById(id);
	}

	@Override
	public void delete(PostsComentsModel postsComents) {
		postsComentsRepository.delete(postsComents);
	}
}
