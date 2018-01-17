package com.arslan.asaStarter.module.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {
	
	@ModelAttribute("server")
	public String getContextPath(HttpServletRequest request){
		return request.getSession().getServletContext().getContextPath();
	}
}
