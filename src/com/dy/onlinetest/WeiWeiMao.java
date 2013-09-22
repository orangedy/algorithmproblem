package com.dy.onlinetest;

import java.util.Scanner;

public class WeiWeiMao {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		while(cin.hasNextLine()){
			int n = cin.nextInt();
			int m = cin.nextInt();
			int p = cin.nextInt();
			if(m <= n + p){
				System.out.println("Yes");
			}else{
				System.out.println("NO");
			}
		}
	}
}
