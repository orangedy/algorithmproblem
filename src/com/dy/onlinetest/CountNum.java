package com.dy.onlinetest;

/*Sample code to read in test cases:*/
import java.io.*;
public class CountNum{
    public static void main(String args[]){
    String sysInputFile = "{sysFileUrl}";
	try{
    	File file = new File(sysInputFile);
    	BufferedReader in = new BufferedReader(new FileReader(file));
    	String line;
		int sum = 0;
    	while ((line = in.readLine()) != null) {
    		int i;
    		try {
    			i = Integer.valueOf(line.trim()).intValue();
    		}catch(NumberFormatException e){
    			continue;
    		}
			sum += i;
    	}
		in.close();
		System.out.println(sum);
		}catch(IOException e) {
			e.printStackTrace();
		}
  	}
}
