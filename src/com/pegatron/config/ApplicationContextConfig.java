/**
 * (Note) It is a best practice to put all JSP files inside WEB-INF directory,
 * to hide them from direct access (e.g. via a manually entered URL).
 * If we put then inside WEB-INF directory then only controllers will 
 * be able to access them. 
 * 
 * Ex. if a user tries to access /home URL and HomeController returns "home"
 * then DispatcherServlet will consult internalResourceViewResolver and 
 * it will use prefix and suffix to find the actual physical view which
 * is integral to web application.
 * 
 * Like, if prefix is "/WEB-INF/views/" and suffix is ".jsp" then "home" will 
 * be resolved to "/WEB-INF/home.jsp" by InternalResourceViewResolver.
 */

package com.pegatron.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.pegatron")
@EnableWebMvc
public class ApplicationContextConfig {

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// Convert a logical view name returned from Spring controller to map to actual
		// JSP pages.
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
