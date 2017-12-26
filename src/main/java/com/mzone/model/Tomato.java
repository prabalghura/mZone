package com.mzone.model;

import java.time.LocalDateTime;

public class Tomato {
	private LocalDateTime lastUpdated;
	
	private Rating viewer;
	
	private String boxOffice;
	
	private String consesnsus;
	
	private Rating critic;
	
	private LocalDateTime dvd;
	
	private Integer fresh;
	
	private String production;
	
	private Integer rotten;

	public Tomato(LocalDateTime lastUpdated, Rating viewer, String boxOffice, String consesnsus, Rating critic,
			LocalDateTime dvd, Integer fresh, String production, Integer rotten) {
		this.lastUpdated = lastUpdated;
		this.viewer = viewer;
		this.boxOffice = boxOffice;
		this.consesnsus = consesnsus;
		this.critic = critic;
		this.dvd = dvd;
		this.fresh = fresh;
		this.production = production;
		this.rotten = rotten;
	}

	public Tomato() {
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Rating getViewer() {
		return viewer;
	}

	public void setViewer(Rating viewer) {
		this.viewer = viewer;
	}

	public String getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(String boxOffice) {
		this.boxOffice = boxOffice;
	}

	public String getConsesnsus() {
		return consesnsus;
	}

	public void setConsesnsus(String consesnsus) {
		this.consesnsus = consesnsus;
	}

	public Rating getCritic() {
		return critic;
	}

	public void setCritic(Rating critic) {
		this.critic = critic;
	}

	public LocalDateTime getDvd() {
		return dvd;
	}

	public void setDvd(LocalDateTime dvd) {
		this.dvd = dvd;
	}

	public Integer getFresh() {
		return fresh;
	}

	public void setFresh(Integer fresh) {
		this.fresh = fresh;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public Integer getRotten() {
		return rotten;
	}

	public void setRotten(Integer rotten) {
		this.rotten = rotten;
	}
}
