package com.arraylist1.access.amazon;

public class Product {
	
	private String prodId;
	private String prodName;
	private int quantity;
	private double price;
	public Product(String prodId, String prodName, int quantiy, double price) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.quantity = quantiy;
		this.price = price;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getQuantiy() {
		return quantity;
	}
	public void setQuantiy(int quantiy) {
		this.quantity = quantiy;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
  
	
	public String toString() {
		
		return "product [ product Id: " + prodId + ", product name: "+ prodName + ", Qunatity: "+ quantity + ", price: "+ price +" ]";
	}

}
