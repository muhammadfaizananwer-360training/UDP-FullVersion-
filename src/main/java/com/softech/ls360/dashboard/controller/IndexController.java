package com.softech.ls360.dashboard.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.softech.ls360.dashboard.config.spring.annotation.WebController;

/**
 * Controllers are the top layer of the food chain in the Controller-Service-Repository pattern. In fact, this three-layer system can
 * easily be compared to a natural food chain. In this system, repositories are the plant life and absorb only nutrients from nature
 * (the database). Services are omnivores that consume repositories (plant life) or other services (omnivores). Continuing this 
 * analogy to its logical conclusion, controllers are carnivores. They consume services (the omnivores), but they never consume 
 * repositories (plant life) directly, and they never consume other controllers. Controllers, in some form or another, control the 
 * user interface and, using services for assistance, prepare the model for presentation in the view.
 * 
 * In the MVC paradigm, services and repositories are considered part of the controller (not the @Controller). A @Controller, the 
 * @Services it depends on, the @Repositorys those @Services depend on, and any caching layers that lie between these components all
 * act together to form the controller in the Model-View-Controller pattern. All these components use the model in some form or 
 * another. Ultimately, the @Controller — which could be for a web GUI or a web service API — passes the necessary parts of the model
 * to the view for rendering. This view could be a JSP (for a web GUI), or it could be a JSON or XML rendering engine (for a web GUI
 * or a web service API).
 *  
 * @author basit.ahmed
 *
 */
@WebController
public class IndexController {
    
	 /**
	  * Spring is quite flexible on which types your controller methods may return. Generally speaking, while the method parameters 
	  * are usually related to the request contents, the return type is typically related to the response. A void return type, for 
	  * example, tells Spring that your method handles writing to the response manually, and so Spring does no further request 
	  * handling after your method returns. More typically, however, a controller method returns some type (sometimes with an 
	  * annotation) indicating how Spring should respond to the request.
	  * 
	  * To instruct Spring to use a specific view to render a response, your controller methods may return a number of view types. The
	  * org.springframework.web.servlet.View interface (or any class implementing View) indicates that your method returns an explicit
	  * view object. After your method returns, request handling is passed off to that view. Spring Framework provides dozens of View 
	  * implementations (such as RedirectView, for example), or you may create your own. Your controller methods may also return a 
	  * String indicating the name of a view to resolve. Finally, your methods may return an 
	  * org.springframework.web.servlet.ModelAndView. This class provides the ability to return both a View and a model type, or a 
	  * String view name and a model type, at the same time.
	  * 
	  */
	@RequestMapping("/")
    public String index() {
		return "forward:resources/index.html";
    }
	
}
