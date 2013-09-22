package com.dy.onlinetest;

import java.util.Scanner;

/**
 * @author orangedy
 * @version 2013-4-14 下午10:49:08
 * 
 * 火星码，即一串数字，数位i的进制为第i个素数，如2，3，5，7等
 * 素数求法？怎么用比较高效的算法求素数
 */
public class HuoxingNum {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int[] prime = {1,2,3,5,7,11,13,17,19,23,29};
		int marsNum = 0;
		if(cin.hasNextInt()){
			marsNum = cin.nextInt();
		}
		int sum = 0;
		int i = 0;
		int temp = 1;
		while(marsNum > 0){
			int reminder = marsNum%10;
			marsNum = marsNum/10;
			temp = temp*prime[i];
			sum += reminder*temp;
			i++;
		}
		System.out.println(sum);
	}
}
