package com.mzone.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.mzone.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String>, MovieCustomRepository {
	
	public Page<Movie> findAllBy(TextCriteria criteria, Pageable pageable);
	
	public Page<Movie> findByGenres(String genre, Pageable pageable);
	
	public Page<Movie> findAllByAndGenres(TextCriteria criteria, String genre, Pageable pageable);
}
