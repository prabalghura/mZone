package com.mzone.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
	@Override
    public void postHandle(final HttpServletRequest request,
            final HttpServletResponse response, final Object handler,
            final ModelAndView modelAndView) throws Exception {

		Object user = request.getSession().getAttribute("currentUser");
		if(modelAndView != null) {
			modelAndView.getModelMap().addAttribute("currentUser", user);
		}
    }
}