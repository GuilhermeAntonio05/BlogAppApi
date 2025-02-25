package com.api.BlogAppApi.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.BlogAppApi.model.PostsComentsModel;

@Repository
public interface PostsComentsRepository extends JpaRepository<PostsComentsModel, UUID>{
}
