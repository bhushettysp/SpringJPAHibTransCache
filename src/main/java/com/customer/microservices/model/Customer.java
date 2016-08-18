package com.customer.microservices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="customers")
public class Customer {

	private static final long serialVersionUID = 1L;

	
	public Customer(){}
	public Customer(Long id){this.id=id;}
	public Customer(String name,String email){this.name=name;this.email=email;}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long id;

	
	@NotNull
	private String name;

	@NotNull
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [ID = " +this.getId() + ", Name = " + this.getName() + ", Email =" + this.getEmail()+"]";
	}

}
