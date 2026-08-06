package com.java.arraylist.day1;
import java.util.ArrayList;

public class Student {

	public static void main(String[] args) {
		
		//task --> create an arraylist and store elements 
		  ArrayList<String> studentName = new ArrayList<>();
	//default capacity of array is 10 , and it will increase with the speed of 50%	
		// so next time arraylist will be 15 they 
		  studentName.add("krishna"); //adding elements here.
		  studentName.add("mohan");
		  studentName.add("raghav"); 
		  studentName.add("krishna");
		  studentName.add("harsh");
		  studentName.add("krishna");
		  studentName.add("rohit");
		  studentName.add("harsh");
		  studentName.add("raghav");
		  studentName.add("krishna");
		  studentName.add("pratyush");
		  studentName.add("raghav");  
		  
		  
		 // System.out.print("student name : " + studentName);
		 // System.out.println(studentName);
		  
		  //array list is index based.
		  String name1 = studentName.get(1);  
		  String name2 = studentName.get(5);
		  String name3 = studentName.get(6);
		  String name5 = studentName.get(9);
		  String name6 = studentName.get(11);
		 // System.out.println(name1+ " " + name2+ " " + name3+ " " + name5+ " "+ name6);
		  
		 for(int i = 0; i<studentName.size();i++) {
			 String currentElement = studentName.get(i);
			 
			 if(currentElement.startsWith("h")) {
			 
			 
			 System.out.println(currentElement);
			
			 }
			 
		 }
		 
	}
	
	
}
