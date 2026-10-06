package com.arraylist1.access.amazon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Amazon {

	public static void main(String[] args) {
		
		List<Product> products = new ArrayList<Product>();
		
		Product p1 = new Product("p101", "IPhone 17", 2, 1750000);
		Product p2 = new Product("p102", "Samsung Galaxy S26", 5, 95000);
		Product p3 = new Product("p103", "OnePlus 13", 3, 65000);
		Product p4 = new Product("p104", "MacBook Air M4", 4, 120000);
		Product p5 = new Product("p105", "Dell Inspiron 15", 6, 75000);
		Product p6 = new Product("p106", "Sony Bravia TV", 2, 85000);
		Product p7 = new Product("p107", "Apple AirPods Pro", 8, 25000);
		Product p8 = new Product("p108", "Samsung Washing Machine", 3, 55000);
		Product p9 = new Product("p109", "HP Laser Printer", 5, 18000);
		Product p10 = new Product("p110", "Boat Smart Watch", 10, 5000);
		
		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);
		products.add(p5);
		products.add(p6);
		products.add(p7);
		products.add(p8);
		products.add(p9);
		products.add(p10);
		
		Iterator<Product> p = products.iterator();
		while(p.hasNext()) {
			
			
			Product prod = p.next();
			System.out.println(prod);
			

			
		}
		
		
	}

}
