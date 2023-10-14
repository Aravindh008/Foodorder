package com.review.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Customermodel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Ordermodel> dishes;

    @OneToMany(mappedBy = "customer")
    private List<Paymentmodel> payments;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Ordermodel> getDishes() {
		return dishes;
	}

	public void setDishes(List<Ordermodel> dishes) {
		this.dishes = dishes;
	}

	public List<Paymentmodel> getPayments() {
		return payments;
	}

	public void setPayments(List<Paymentmodel> payments) {
		this.payments = payments;
	}

}
