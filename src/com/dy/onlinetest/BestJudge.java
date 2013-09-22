package com.dy.onlinetest;

import java.util.Scanner;

/**
 * @author Administrator
 * 
 * 输入一系列成绩，去掉一个最高分，一个最低分，得到平均分
 * 返回最接近平均分的预测数字及序号，即预测最准的裁判
 *
 */
public class BestJudge {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		while(cin.hasNextInt()){
			
			int num = 0;
			num = cin.nextInt();
			if(num == 0){
				break;
			}
			double[] P = new double[num];
			int max = 0;
			int min = 0;
			for(int i = 0; i < num; i++){
				P[i] = cin.nextDouble();
				if(P[i] > P[max]){
					max = i;
				}
				if(P[i] < P[min]){
					min = i;
				}
			}
			double sum = 0;
			for(int i = 0; i < num; i++){
				if(i != max && i != min){
					sum += P[i];
				}
			}
			double average = sum/(num - 2);
			double minErr = 10;
			int win = 0;
			for(int i = 0; i < num; i++){
				if(i != max && i != min){
					double err = Math.abs(P[i] - average);
					if(err < minErr){
						minErr = err;
						win = i;
					}
				}
			}
			System.out.println(win + 1);
		}
	}
}
