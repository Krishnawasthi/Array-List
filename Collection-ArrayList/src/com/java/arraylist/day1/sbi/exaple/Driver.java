package com.java.arraylist.day1.sbi.exaple;

import java.util.ArrayList;
import com.java.arraylist.day1.sbi.exaple.SBIUSer;
public class Driver {

	public static void main(String[] args) {
		
	ArrayList<SBIUSer> defaultUser = new ArrayList<>();
	 
	SBIUSer user1 = new SBIUSer("rahul","berlin","SBI13203JD",1236);
	SBIUSer user2 = new SBIUSer("krishna","milan itely","SBI13203JD",120556);
	SBIUSer user3 = new SBIUSer("rohit","new jersy","SBI13203JD",525);
	SBIUSer user4 = new SBIUSer("babalu","santiago","SBI13203JD",2206);
	SBIUSer user5 = new SBIUSer("carl","morocco","SBI13203JD",896);
	SBIUSer user6 = new SBIUSer("adam","luxenberg","SBI13203JD",1336);
	SBIUSer user7 = new SBIUSer("ava","jakarta","SBI13203JD",136);
	SBIUSer user8 = new SBIUSer("steven","ireland","SBI13203JD",146363);
	SBIUSer user9 = new SBIUSer("jonhson","great britain","SBI13203JD",2352);
	
	
	defaultUser.add(user1);
	defaultUser.add(user2);
	defaultUser.add(user3);
	defaultUser.add(user4);
	defaultUser.add(user5);
	defaultUser.add(user6);
	defaultUser.add(user7);
	defaultUser.add(user8);
	defaultUser.add(user9);
	
	
	for(int i=0; i<defaultUser.size();i++) {
		
		SBIUSer user = defaultUser.get(i);
		
		System.out.println(user.getName());
		System.out.println(user.getAddress());
		System.out.println(user.getIfsc());
		System.out.println(user.getBalance());
		
		System.out.println();
	
		
		
		
		if(user.getBalance() < 1000)
		System.out.println("sending email to user " + user.getName() + " his/her balance is not maintained");
	}
	
	

	
	

	}

}
