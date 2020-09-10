package com.pegatron.service;

import java.io.Serializable;
import java.util.List;

import com.pegatron.model.BaseModel;
import com.pegatron.model.Book;
import com.pegatron.model.Bookstore;

public interface ExerciseService {
	public <T extends Bookstore> List<T> findAllBookstores(Class<T> persistClass);

	public <T extends BaseModel> void saveOrUpdate(T t);

	public <T extends BaseModel> void delete(T t);

	public Bookstore getBookstore(Serializable pk);

	public <T extends BaseModel> T load(Serializable pk, Class<T> entityClass);

//	public List<Bookstore> findAllBookstores();

//	public void saveOrUpdateBookstore(Bookstore bookstore);

//	public void deleteBookstore(Bookstore bookstore);

//	public List<Book> findAllBooks(Serializable pk);

//	public void saveOrUpdateBook(Book book);

//	public void deleteBook(Book book);

//	public Book getBook(Serializable pk);

}
