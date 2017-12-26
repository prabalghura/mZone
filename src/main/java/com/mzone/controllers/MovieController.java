package com.mzone.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mzone.model.Comment;
import com.mzone.model.Movie;
import com.mzone.repositories.MovieRepository;
import com.mzone.utils.ServletReturnObject;

@Controller
public class MovieController {
	@Autowired
	MovieRepository movieRepository;
	
	@RequestMapping("/movies")
	public ServletReturnObject showMovies(Model model, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "genres", required = false) String genres, 
			@RequestParam(value = "textSearch", required = false) String textSearch) {
		int MOVIES_PER_PAGE = 20;
		if(page == null)
			page = 0;
		String params = "&genres=" + (genres == null ? "" : genres);
		params+="&textSearch=" + (textSearch == null ? "" : textSearch);
		String previous_page = params + "&page=" + (page - 1);
		String next_page = params + "&page=" + (page + 1);
		
		List<Movie> movies;
		List<String> all_genres = movieRepository.getAllGenres();
		Page<Movie> moviePage;
		Long total_num_entries;
		if(genres != null && genres.length()>0 && textSearch != null && textSearch.length()>0)
		{
			PageRequest request = new PageRequest(page.intValue(), MOVIES_PER_PAGE, new Sort(Sort.Direction.DESC, "score"));
			moviePage = movieRepository.findAllByAndGenres(new TextCriteria().matching(textSearch), genres, request);
			
		}
		else if(textSearch != null && textSearch.length()>0)
		{
			PageRequest request = new PageRequest(page.intValue(), MOVIES_PER_PAGE, new Sort(Sort.Direction.DESC, "score"));
			moviePage = movieRepository.findAllBy(new TextCriteria().matching(textSearch), request);
		}
		else if(genres != null && genres.length()>0)
		{
			PageRequest request = new PageRequest(page.intValue(), MOVIES_PER_PAGE, new Sort(Sort.Direction.DESC, "tomatoes.viewer.numReviews"));
			moviePage = movieRepository.findByGenres(genres, request);
		}
		else
		{
			PageRequest request = new PageRequest(page.intValue(), MOVIES_PER_PAGE, new Sort(Sort.Direction.DESC, "tomatoes.viewer.numReviews"));
			moviePage = movieRepository.findAll(request);
		}
		movies = moviePage.getContent();
		total_num_entries = moviePage.getTotalElements();
		
		model.addAttribute("movies", movies);
		model.addAttribute("page", page);
		model.addAttribute("textSearch", textSearch);
		model.addAttribute("genres", genres);
		model.addAttribute("entries_per_page", MOVIES_PER_PAGE);
		model.addAttribute("total_num_entries", total_num_entries);
		model.addAttribute("previous_page", previous_page);
		model.addAttribute("next_page", next_page);
		model.addAttribute("all_genres", all_genres);
		//forMod();
		
		ServletReturnObject result = new ServletReturnObject(model, "SUCCESS");
		return result;
	}
	
	@RequestMapping("/movie")
	public ServletReturnObject showMovie(Model model, @RequestParam String id,
			@RequestParam(value = "newComment", required = false) String newComment) {
		Movie movie = movieRepository.findOne(id);
		
		model.addAttribute("movie", movie);
		model.addAttribute("new_comment", newComment);	//TODO
		
		ServletReturnObject result = new ServletReturnObject(model, "SUCCESS");
		return result;
	}
	
	@RequestMapping("/movieWatch")
	public ServletReturnObject watchMovie(Model model, @RequestParam String id) {
		Movie movie = movieRepository.findOne(id);
		model.addAttribute("movie", movie);
		ServletReturnObject result = new ServletReturnObject(model, "SUCCESS");
		return result;
	}
	
	private void forMod() {
		List<Movie> movies = movieRepository.findAll();
		for(Movie movie: movies)
		{
			/*if(movie.getNum_mflix_comments() != null)
			{
				movie.setNum_mZone_comments(movie.getNum_mflix_comments());
				movie.setNum_mflix_comments(null);
				movieRepository.save(movie);
			}*/
		}
	}
}
