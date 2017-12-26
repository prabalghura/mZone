package com.mzone.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mzone.model.User;
import com.mzone.repositories.UserRepository;
import com.mzone.utils.InvalidAccessException;
import com.mzone.utils.ServletReturnObject;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/login")
	public ServletReturnObject login(HttpServletRequest request, Model model) {
		String[] params = request.getQueryString().split("loginPath=");
		String loginPath = null;
		if(params.length>1)
			loginPath = params[1];
		if(loginPath == null || loginPath.length() == 0)
			loginPath="/movies";
		loginPath = loginPath.replace("&", "%#%");
		model.addAttribute("loginPath", loginPath);
		ServletReturnObject result = new ServletReturnObject(model, "SUCCESS");
		return result;
	}
	
	@RequestMapping("/userProfile")
	public ServletReturnObject userProfile(HttpServletRequest request, Model model) {
		Object user = request.getSession().getAttribute("currentUser");
		if(user==null)
			throw new InvalidAccessException("", "");
		ServletReturnObject result = new ServletReturnObject(model, "SUCCESS");
		return result;
	}
	
	@RequestMapping("/changePassword")
	public ModelAndView changePassword(HttpServletRequest request, @RequestParam String email, @RequestParam String password, 
			@RequestParam String confirmpassword) throws Exception {
		List<User> userList = userRepository.findByEmail(email);
		if(userList.isEmpty())
			throw new Exception("User does not exists");
		User user = userList.get(0);
		user.setPassword(password);
		
		Map<String, Object> loginMap = new HashMap<String, Object>();
		String validatePassword = validatePassword(password, confirmpassword);
		if(validatePassword.length()>0)
		{
			loginMap.put("passwordError", validatePassword);
			return new ModelAndView("/userProfile", loginMap);
		}
		loginMap.put("passwordSuccess", "SUCCESS");
		
		userRepository.save(user);
		loginUser(request, user);
		return new ModelAndView("/userProfile", loginMap);
	}
	
	@RequestMapping("/signup")
	public ModelAndView signup(HttpServletRequest request, @RequestParam(value = "loginPath", required = false) String loginPath, 
			@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String confirmpassword) {
		if(loginPath == null || loginPath.length() == 0)
			loginPath="/movies";
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		Map<String, Object> loginMap = new HashMap<String, Object>();
		String validatePassword = validatePassword(password, confirmpassword);
		if(validatePassword.length()>0)
		{
			loginMap.put("signupError", validatePassword);
			loginMap.put("loginPath", loginPath);
			return new ModelAndView("/login", loginMap);
		}
		else if(!userRepository.findByEmail(email).isEmpty())
		{
			loginMap.put("signupError", "A user with the given email already exists.");
			loginMap.put("loginPath", loginPath);
			return new ModelAndView("/login", loginMap);
		}
		
		userRepository.save(user);
		loginUser(request, user);
		return new ModelAndView("redirect:"+loginPath.replace("%#%", "&"), loginMap);
	}
	
	@RequestMapping("/logup")
	public ModelAndView logup(HttpServletRequest request, @RequestParam(value = "loginPath", required = false) String loginPath, @RequestParam String email, 
			@RequestParam String password, Model model) {
		if(loginPath == null || loginPath.length() == 0)
			loginPath="/movies";
		List<User> userList = userRepository.findByEmail(email);
		Map<String, Object> loginMap = new HashMap<String, Object>();
		if(userList.isEmpty())
		{
			loginMap.put("loginError", "Make sure your email is correct.");
			loginMap.put("loginPath", loginPath);
			return new ModelAndView("/login", loginMap);
		}
		User user = userList.get(0);
		if(!password.equals(user.getPassword()))
		{
			loginMap.put("loginError", "Make sure your password is correct.");
			loginMap.put("loginPath", loginPath);
			return new ModelAndView("/login", loginMap);
		}
		loginUser(request, user);
		return new ModelAndView("redirect:"+loginPath.replace("%#%", "&"), loginMap);
	}
	
	private String validatePassword(String password, String confirmpassword)
	{
		if(password.length()<8)
			return "Make sure that your password is at least 8 characters!";
		else if(!password.equals(confirmpassword))
			return "Make sure that the passwords you enter match!";
		return "";
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		String[] params = request.getQueryString().split("loginPath=");
		String loginPath = null;
		if(params.length>1)
			loginPath = params[1];
		if(loginPath == null || loginPath.length() == 0)
			loginPath="/movies";
		request.getSession().invalidate();
		Map<String, Object> loginMap = new HashMap<String, Object>();
		return new ModelAndView("redirect:"+loginPath, loginMap);
	}
	
	private void loginUser(HttpServletRequest request, User user)
	{
		request.getSession().setAttribute("currentUser", user);
	}
	
	@ExceptionHandler(InvalidAccessException.class)
	public ModelAndView handleCustomException(InvalidAccessException ex) {

		ModelAndView model = new ModelAndView("/invalidUserAccess");
		return model;

	}
}
