package com.arraylist1.access.students1;

import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {

		ArrayList<String> studentName = new ArrayList<>();

		studentName.add("abhijit pradhan");
		studentName.add("kausik");
		studentName.add("kamlesh");
		studentName.add("kamlesh");
		studentName.add("kamlesh");
		studentName.add("surya");
		studentName.add("surya");
		studentName.add("kamlesh");
		
	
		
		for(int i = 0; i < studentName.size(); i++) 
		{
           String name = studentName.get(i);
           if(name.startsWith("s"))
           {
           
          System.out.println(name +" "+ i);
		}

		}
    for(String name : studentName  ) {
    	
    	System.out.println(name );
    }
	}

}
