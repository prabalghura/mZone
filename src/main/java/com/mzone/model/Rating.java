package com.mzone.model;

public class Rating {
	private Integer meter;
	
	private Integer numReviews;
	
	private Double rating;

	public Rating(Integer meter, Integer numReviews, Double rating) {
		super();
		this.meter = meter;
		this.numReviews = numReviews;
		this.rating = rating;
	}

	public Rating() {
	}

	public Integer getMeter() {
		return meter;
	}

	public void setMeter(Integer meter) {
		this.meter = meter;
	}

	public Integer getNumReviews() {
		return numReviews;
	}

	public void setNumReviews(Integer numReviews) {
		this.numReviews = numReviews;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
}
