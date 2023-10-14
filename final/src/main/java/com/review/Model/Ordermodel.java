package com.review.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Ordermodel {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;	    
	    private String foodName;
	    private String type; // e.g., "Main Course", "Appetizer", "Dessert"
	    private Double price;
	    @ManyToOne
	    private Customermodel customer;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getFoodName() {
			return foodName;
		}
		public void setFoodName(String foodName) {
			this.foodName = foodName;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public Customermodel getCustomer() {
			return customer;
		}
		public void setCustomer(Customermodel customer) {
			this.customer = customer;
		}
}
