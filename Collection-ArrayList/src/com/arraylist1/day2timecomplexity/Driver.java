package com.arraylist1.day2timecomplexity;

import java.util.ArrayList;

 public class Driver {

	public static void main(String[] args) {
		
		CityService cityName = new CityService();
		
		ArrayList<String> cities = cityName.getCityName();
		
		cities.add("kanpur");

		
		for(String city : cities ) {
			System.out.println(city);
			
		}

	}

}
