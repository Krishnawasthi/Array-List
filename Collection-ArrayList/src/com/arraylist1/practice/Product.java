package com.arraylist1.practice;

import java.util.ArrayList;

public class Product {
	
	public static void main(String args[]) {
		
		
		ArrayList<String> products = new ArrayList<>();

		products.add("IPhone 17");
		products.add("Samsung S25");
		products.add("OnePlus 13");
		products.add("Google Pixel 10");
		products.add("MacBook Air");
		products.add("Dell Laptop");
		products.add("HP Laptop");
		products.add("Sony Headphones");
		products.add("Apple Watch");
		products.add("iPad Pro");
		
		//add(index, Object) insert element and shift the element  
		//set(index , object) replace the element from the perticular index
		System.out.println(products.size());
		System.out.println("insert element at the start");
		products.add(0, "intel processor");
		System.out.println();
		System.out.println("insert element in between");
		products.add(4, "nvidia");
		products.add(products.size(), "macbook");
		System.arraycopy(12, 2, products, 3, 4);
		
		System.out.println(products);
		System.out.println(products.size());
	
		
		
	}

}
