package com.api.BlogAppApi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.BlogAppApi.model.BlogAppPostModel;
import com.api.BlogAppApi.repository.BlogAppPostRepository;

import jakarta.transaction.Transactional;

@Service
public class BlogAppServiceImpl implements BlogAppPostService {
	
	@Autowired
	BlogAppPostRepository blogAppPostRepository;

	@Override
	public List<BlogAppPostModel> findAll() {
		return blogAppPostRepository.findAll();
	}

	@Override
	public Optional<BlogAppPostModel> findById(UUID id) {
		return blogAppPostRepository.findById(id);
	}

	@Override
	@Transactional
	public BlogAppPostModel save(BlogAppPostModel post) {
		return blogAppPostRepository.save(post);
	}

	@Override
	@Transactional
	public void delete(BlogAppPostModel post) {
		blogAppPostRepository.delete(post);
	}
}