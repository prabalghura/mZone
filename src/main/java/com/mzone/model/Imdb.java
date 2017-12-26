package com.mzone.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class Imdb {
	@Field(value="id")
	private Integer id;
	
	private Integer votes;
	
	private Double rating;

	public Imdb() {
	}

	public Imdb(Integer id, Integer votes, Double rating) {
		this.id = id;
		this.votes = votes;
		this.rating = rating;
	}
	
	public String getImdbID() {
		String a="";
		for(int i=0;i<(7-id.toString().length());i++)
			a+="0";
		a = "tt"+a+id.toString();
		return a;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
}
