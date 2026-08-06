package com.java.arraylist.day1.sbi.exaple;

public class SBIUSer {

	private String name;
	private String address;
	private String ifsc;
	private int balance;

	public SBIUSer(String name, String address, String ifsc, int balance) {
		super();
		this.name = name;
		this.address = address;
		this.ifsc = ifsc;
		this.balance = balance;
	}

	

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

}
