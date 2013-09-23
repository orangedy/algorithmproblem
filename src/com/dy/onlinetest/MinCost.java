package com.dy.onlinetest;

import java.util.Scanner;

public class MinCost {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("请输入4个数字，分别表示假期天数，最少集训天数，最大集训天数，额外餐费价格");
		int N = cin.nextInt();
		int P = cin.nextInt();
		int Q = cin.nextInt();
		int F = cin.nextInt();
		System.out.println("请输入" + N + "天的机票价格");
		int[] ticketPrice = new int[N];
		for(int i = 0; i < N; i++){
			ticketPrice[i] = cin.nextInt();
		}
		
		int arrive = 0;
		int leave = 0;
		int minCost = Integer.MAX_VALUE;
		//从到达日开始，依次搜索，记录下最小费用
		for(arrive = 0; arrive < N-P-1; arrive++){
			for(leave = arrive + P + 1; leave < N; leave++){
				int cost = 0;
				cost += ticketPrice[arrive];
				cost += ticketPrice[leave];
				if(leave -arrive - 1 > Q){
					cost += (leave - arrive - 1 -Q)*F;
				}
				if(cost < minCost){
					minCost = cost;
				}
			}
		}
		System.out.println(minCost);
	}
}
