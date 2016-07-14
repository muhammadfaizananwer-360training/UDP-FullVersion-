package com.softech.ls360.dashboard.controller;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.softech.ls360.dashboard.config.spring.annotation.WebController;

@WebController
@RequestMapping(value="/dashboard")
public class DashboardController {
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String login(Map<String, Object> model) {
		//if (SecurityContextHolder.getContext().getAuthentication() instanceof LmsUserPrincipal) {
			//return new ModelAndView(new RedirectView("/resources/pages/index.html", true, false));
		//}
			
		//model.put("loginForm", new LoginForm());
		return "forward:/assets/index.html";
		//return "index.html";
		
	}

	public static class LoginForm {
		private String username;
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
