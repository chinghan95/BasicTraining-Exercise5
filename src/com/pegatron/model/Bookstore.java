package com.pegatron.model;

//import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bookstore")
public class Bookstore implements BaseModel {

	@Id
	@Column(name = "Store_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Address")
	private String address;

	@Column(name = "Tel")
	private String tel;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookstore", cascade = CascadeType.REMOVE)
	private Set<Book> books;
	// private List<Book> books = new ArrayList<Book>();

	public Bookstore() {

	}

	public Bookstore(int id) {
		this.id = id;
	}

	public Bookstore(int id, String name, String address, String tel) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
	}

	public Bookstore(String name, String address, String tel) {
		this.name = name;
		this.address = address;
		this.tel = tel;
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
