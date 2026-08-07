package com.arraylist1.access.cities;

import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		
		ArrayList<String> cities = new ArrayList<String>(); //default capacity 10 ...increased by 50%.
		
		cities.add("BLR");
		cities.add("CHE");
		cities.add("HYD");
		cities.add("AHM");
		cities.add("DEL");
		
		
		
		cities.add(1, "KOL");
		
		cities.remove(4);
		cities.remove(3);
		
		
		System.out.println(cities);
		
	}
	
}
		