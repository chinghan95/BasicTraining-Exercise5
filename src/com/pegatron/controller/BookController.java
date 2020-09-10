package com.pegatron.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pegatron.model.Book;
import com.pegatron.model.Bookstore;
import com.pegatron.service.ExerciseService;

@Controller
@RequestMapping("Book")
public class BookController {

	@Autowired
	private ExerciseService service;

	/*
	 * This method will list all existing books.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listBook(HttpServletRequest request, ModelMap model) {
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		Bookstore bookstore = service.getBookstore(storeId);
//		Bookstore bookstore = service.load(storeId, Bookstore.class);
		Set<Book> bookList = bookstore.getBooks();
//		List<Book> bookList = service.findAllBooks(storeId);
		model.addAttribute("bookstore", bookstore);
		model.addAttribute("bookList", bookList);
		return "Book/list";
	}

	/*
	 * This method will provide the medium to add a new book.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showBookForm(HttpServletRequest request, ModelMap model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "Book/form";
	}

	/*
	 * This method will provide the medium to update an existing book.
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editBook(HttpServletRequest request, ModelMap model) {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
//		Book book = service.getBook(bookId);
		Book book = service.load(bookId, Book.class);
		model.addAttribute("book", book);
		return "Book/form";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving book in database.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@RequestParam("storeId") Integer storeId, @ModelAttribute Book book,
			HttpServletRequest request) {

		Bookstore bookstore = service.getBookstore(storeId); //// Integer storeId
//		Bookstore bookstore = service.getBookstore(Integer.valueOf(storeId));
//		Bookstore bookstore = service.load(storeId, Bookstore.class);
		book.setBookstore(bookstore);
//		service.saveOrUpdateBook(book);
		service.saveOrUpdate(book);
		return "redirect:/Book/list?storeId=" + storeId;
	}

	/*
	 * This method will delete a book.
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteBook(HttpServletRequest request) {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		Book book = new Book(bookId);
//		service.deleteBook(book);
		service.delete(book);
		return "redirect:/Book/list?storeId=" + storeId;
	}
}
