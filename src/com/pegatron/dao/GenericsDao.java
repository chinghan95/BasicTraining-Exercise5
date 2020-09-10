package com.pegatron.dao;

import java.io.Serializable;
import java.util.List;

import com.pegatron.model.BaseModel;
import com.pegatron.model.Book;
import com.pegatron.model.Bookstore;

public interface GenericsDao {

	public <T extends BaseModel> List<T> findAll(Class<T> persistClass);
	
	public <T extends BaseModel> void saveOrUpdate(T t);

	public <T extends BaseModel> void delete(T t);

	public <T extends BaseModel> T load(Serializable pk, Class<T> entityClass);

//	public <T extends Book> List<T> findAllByQuery(String query);

}
