package com.api.BlogAppApi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.BlogAppApi.model.PostsComentsModel;

public interface PostComentService {
	PostsComentsModel save(PostsComentsModel postsComents);
	void delete(PostsComentsModel postsComents);
	List<PostsComentsModel> findAll();
	Optional<PostsComentsModel> findById(UUID id);
}
