package com.mzone.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "movies")
public class Movie {
	@Id
    private String id;
	
	private String awards;
	
	private List<String> cast;
	
	private List<Comment> comments;
	
	private List<String> countries;
	
	private List<String> directors;
	
	@Field(value="fullplot")
	private String fullPlot;
	
	private List<String> genres;
	
	private Imdb imdb;
	
	private String lastupdated;
	
	private Integer num_mZone_comments;
	
	private String plot;
	
	private String poster;
	
	private String rated;
	
	private LocalDateTime released;
	
	private Integer runtime;
	
	private String title;
	
	private Tomato tomatoes;
	
	private String type;
	
	private List<String> writers;
	
	private Integer year;
	
	private Integer metacritic;
	
	private List<String> languages;

	public Movie() {
	}

	public Movie(String awards, List<String> cast, List<Comment> comments, List<String> countries,
			List<String> directors, String fullPlot, List<String> genres, Imdb imdb, String lastupdated,
			Integer num_mZone_comments, String plot, String poster, String rated, LocalDateTime released,
			Integer runtime, String title, Tomato tomatoes, String type, List<String> writers, Integer year,
			Integer metacritic, List<String> languages) {
		this.awards = awards;
		this.cast = cast;
		this.comments = comments;
		this.countries = countries;
		this.directors = directors;
		this.fullPlot = fullPlot;
		this.genres = genres;
		this.imdb = imdb;
		this.lastupdated = lastupdated;
		this.num_mZone_comments = num_mZone_comments;
		this.plot = plot;
		this.poster = poster;
		this.rated = rated;
		this.released = released;
		this.runtime = runtime;
		this.title = title;
		this.tomatoes = tomatoes;
		this.type = type;
		this.writers = writers;
		this.year = year;
		this.metacritic = metacritic;
		this.languages = languages;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}
	
	public String getCastString() {
		String string="";
		if(cast!=null && cast.size()>0)
		{
			for(String str : cast)
			{
				string+=str+", ";
			}			
			return string.substring(0, string.length()-2);
		}
		return string;
	}

	public List<String> getCast() {
		return cast;
	}

	public void setCast(List<String> cast) {
		this.cast = cast;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public String getFullPlot() {
		return fullPlot;
	}

	public void setFullPlot(String fullPlot) {
		this.fullPlot = fullPlot;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public Imdb getImdb() {
		return imdb;
	}

	public void setImdb(Imdb imdb) {
		this.imdb = imdb;
	}

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public LocalDateTime getReleased() {
		return released;
	}

	public void setReleased(LocalDateTime released) {
		this.released = released;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Tomato getTomatoes() {
		return tomatoes;
	}

	public void setTomatoes(Tomato tomatoes) {
		this.tomatoes = tomatoes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getWriters() {
		return writers;
	}

	public void setWriters(List<String> writers) {
		this.writers = writers;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public Integer getMetacritic() {
		return metacritic;
	}

	public void setMetacritic(Integer metacritic) {
		this.metacritic = metacritic;
	}

	public Integer getNum_mZone_comments() {
		return num_mZone_comments;
	}

	public void setNum_mZone_comments(Integer num_mZone_comments) {
		this.num_mZone_comments = num_mZone_comments;
	}
}