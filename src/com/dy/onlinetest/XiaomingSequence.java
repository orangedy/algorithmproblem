package com.dy.onlinetest;

import java.util.Scanner;

public class XiaomingSequence {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		while(cin.hasNextInt()){
			int n = cin.nextInt();
			int d = cin.nextInt() + 1;
			int[] S = new int[n];
			for(int i = 0; i < n; i++){
				S[i] = cin.nextInt();
			}
			int m = 1;
			while(d < (n/m)){

				for(int i = 0; i < n - m*d; i++){
					int mtemp = 1;
					for(int j = d; j < n; j = j + d){
						if(S[j - d] < S[j]){
							mtemp++;
						}else{
							break;
						}
					}
					if(mtemp > m){
						m = mtemp;
					}
				}
				d++;
			}
			System.out.println(m);
		}
	}
}
