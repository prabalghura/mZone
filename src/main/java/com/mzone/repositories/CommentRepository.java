package com.mzone.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mzone.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String>{
	
	public Page<Comment> findByMovieId(String movieId, Pageable pageable);
}
