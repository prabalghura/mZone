package com.mzone.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mzone.model.Comment;
import com.mzone.model.Movie;
import com.mzone.model.User;
import com.mzone.repositories.CommentRepository;
import com.mzone.repositories.MovieRepository;
import com.mzone.utils.InvalidAccessException;

@Controller
public class CommentController {
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@RequestMapping("/deleteComment")
	public ModelAndView deleteComment(@RequestParam String movieId, @RequestParam String commentId) {
		//forMod();
		deleteCommentFromMovie(movieId, commentId);
		return new ModelAndView("redirect:/movie?id="+movieId);
	}
	
	@RequestMapping("/movieComment")
	public ModelAndView movieComment(Model model, HttpServletRequest request, @RequestParam(value = "comment", required = false) String comment, 
			@RequestParam String movieId) {
		if(request.getMethod().equalsIgnoreCase("POST"))
		{
			Object user = request.getSession().getAttribute("currentUser");
			if(user==null)
				throw new InvalidAccessException("", "");
			addCommentToMovie(movieId, (User) user, comment);
			return new ModelAndView("redirect:/movie?id="+movieId+"&newComment="+comment);
		}
		Map<String, Object> commentMap = new HashMap<String, Object>();
		
		PageRequest pageRequest = new PageRequest(0, Integer.MAX_VALUE, new Sort(Sort.Direction.DESC, "date"));
		List<Comment> comments = commentRepository.findByMovieId(movieId, pageRequest).getContent();
		Integer num_comments = comments.size();
		Movie movie = movieRepository.findOne(movieId);
		
		commentMap.put("movie", movie);
		commentMap.put("older_comments", comments);
		commentMap.put("num_comments", num_comments);
		return new ModelAndView("/movieComment", commentMap);
	}
	
	@ExceptionHandler(InvalidAccessException.class)
	public ModelAndView handleCustomException(InvalidAccessException ex) {
		ModelAndView model = new ModelAndView("/invalidUserAccess");
		return model;
	}
	
	private void addCommentToMovie(String movieId, User user, String text) {
		String commentId = movieId+"-"+user.getName()+"-"+Timestamp.valueOf(LocalDateTime.now()).getTime();
		Comment comment = new Comment(commentId, LocalDateTime.now(), user.getEmail(), movieId, user.getName(), 
				text);
		Movie movie = movieRepository.findOne(movieId);
		if(movie!=null)
		{
			movie.setNum_mZone_comments(movie.getNum_mZone_comments()+1);
			List<Comment> movieComments = movie.getComments();
			movieComments.add(0, comment);
			if(movieComments.size()==11)
				movieComments.remove(10);
			movie.setComments(movieComments);
			movieRepository.save(movie);
		}
		commentRepository.save(comment);
	}
	
	private void deleteCommentFromMovie(String movieId, String commentId) {
		
		Movie movie = movieRepository.findOne(movieId);
		PageRequest request = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "date"));
		
		commentRepository.delete(commentId);
		List<Comment> comments = commentRepository.findByMovieId(movieId, request).getContent();
		movie.setComments(comments);
		movie.setNum_mZone_comments(movie.getNum_mZone_comments()-1);
		movieRepository.save(movie);
	}
	
	private void forMod() {
		/*PageRequest request = new PageRequest(0, 100, new Sort(Sort.Direction.DESC, "date"));
		List<Comment> comments = commentRepository.findByMovieIdExists(false, request).getContent();
		List<Comment> altcomments = new ArrayList<Comment>();
		for(Comment comment: comments)
		{
			//comment.setMovieId(comment.getMovie_id());
			altcomments.add(comment);
		}
		commentRepository.save(altcomments);*/
	}
}