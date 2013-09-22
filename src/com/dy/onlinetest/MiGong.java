package com.dy.onlinetest;

import java.util.Scanner;

/**
 * @author orangedy
 * @version 2013-3-31 下午10:05:53
 * 输入一个数组，每个数组都有一个大于等于0的整数，现在任意选择一个数减1，同时将它右边的数字也减一
 * 如果最终能够将所有的数字都变成0，则输出yes，否则输出no
 * 
 * 突破点是最左边的一个数字，它要变成0，那么只能通过它右边一个数字来配合，因为它左边没有元素
 * 那么它要能变成0，它右边的数必须大于等于它
 * 比如 3,4,5……
 * 数字3只能通过4来消除，变成0,1，如果是3,2，那么3永远不能变成0，直接返回no
 * 同理，第一个变成0后考虑第二个，直到所有都变成0
 */
public class MiGong {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		if(cin.hasNext()){
			int caseNum = cin.nextInt();
			while(caseNum-- > 0){
				int boxNum = cin.nextInt();
				int[] boxValue = new int[boxNum];
				for(int i = 0; i < boxNum; i++){
					boxValue[i] = cin.nextInt();
				}
				int i;
				for(i = 0; i < boxNum - 1; i++){
					if(boxValue[i] > boxValue[i + 1]){
						break;
					}else{
						boxValue[i + 1] -= boxValue[i];
						boxValue[i] = 0;
					}
				}
				if(i == boxNum - 1 && boxValue[boxNum - 1] == 0){
					System.out.println("yeah~ I escaped ^_^");
				}else{
					System.out.println("I will never go out T_T");
				}
			}
		}
	}
}
