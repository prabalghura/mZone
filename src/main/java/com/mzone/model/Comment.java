package com.mzone.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comments")
public class Comment {
	@Id
    private String id;
	
	private LocalDateTime date;
	
	private String email;
	
	private String movieId;
	
	private String name;
	
	private String text;

	public Comment() {
	}

	public Comment(String id, LocalDateTime date, String email, String movieId, String name, String text) {
		this.id = id;
		this.date = date;
		this.email = email;
		this.movieId = movieId;
		this.name = name;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getDateFormatted() {
		return date.format(DateTimeFormatter.ofPattern("EEEE, MM/dd/yyyy"));
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
