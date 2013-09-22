package com.dy.onlinetest;

import java.util.Scanner;

public class TrainLine {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		if(cin.hasNext()){
			int Q = cin.nextInt();
			for(int i = 0; i < Q; i++){
				int n = cin.nextInt();
				int t = cin.nextInt();
				String[] trains = new String[t];
				int[] k = new int[n];
				for(int j = 0; j < t; j++){
					String line = cin.nextLine();
					trains[j] = line.split(" ")[0];
					k[j] = Integer.parseInt(line.split(" ")[1]);
				}
			}
		}

	}
}
