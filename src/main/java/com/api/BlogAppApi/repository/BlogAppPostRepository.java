package com.api.BlogAppApi.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.BlogAppApi.model.BlogAppPostModel;

@Repository
public interface BlogAppPostRepository extends JpaRepository<BlogAppPostModel, UUID>{
}
