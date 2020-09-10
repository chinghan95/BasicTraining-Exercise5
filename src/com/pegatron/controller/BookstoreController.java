/**
 * @Controller, Spring framework will treat it as a Controller class to handle client requests.
 * @RequestMapping, mapping web requests onto specific handler classes and methods.
 * 
 * ModelMap:
		 * A Map implementation, which saves you from old request.getAttribute/request.setAttribute. 
		 * It provides a way to set/get attributes from/to request or session.
 *
 * String values returned from method:
		 * The return string value will be suffixed and prefixed with suffix and prefix 
		 * defined in view resolver to from the real view file name. 
 */

package com.pegatron.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pegatron.model.Bookstore;
import com.pegatron.service.ExerciseService;

@Controller
@RequestMapping("/Bookstore")
public class BookstoreController {

	@Autowired
	private ExerciseService service;

	/*
	 * This method will list all existing bookstores.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listBookstore(ModelMap model) {
		List<Bookstore> bookstoreList = service.findAllBookstores(Bookstore.class);
		model.addAttribute("bookstoreList", bookstoreList);
		return "Bookstore/list";
	}

	/*
	 * This method will provide the medium to add a new bookstore
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showBookstoreForm(ModelMap model) {
		Bookstore bookstore = new Bookstore();
		model.addAttribute("bookstore", bookstore);
		return "Bookstore/form";
	}

	/*
	 * This method will provide the medium to update an existing bookstore.
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editBookstore(HttpServletRequest request, ModelMap model) {
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		Bookstore bookstore = service.getBookstore(storeId);
//		Bookstore bookstore = service.load(storeId, Bookstore.class);
		model.addAttribute("bookstore", bookstore);
		return "Bookstore/form";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving bookstore in database.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBookstore(@ModelAttribute Bookstore bookstore) {
//		service.saveOrUpdateBookstore(bookstore);
		service.saveOrUpdate(bookstore);
		return "redirect:/Bookstore/list";
	}

	/*
	 * This method will delete a bookstore.
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteBookstore(HttpServletRequest request) {
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		Bookstore bookstore = new Bookstore(storeId);
//		service.deleteBookstore(bookstore);
		service.delete(bookstore);
		return "redirect:/Bookstore/list";
	}
}
