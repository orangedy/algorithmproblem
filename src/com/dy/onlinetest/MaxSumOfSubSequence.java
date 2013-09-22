package com.dy.onlinetest;

import java.util.Scanner;

/**
 * @author orangedy
 * @version 2013-3-31 下午9:43:31
 * 
 * 寻找数组中，和最大的子数组(连续)，并求出其和
 * 比如 -1 1 2 -1，最大的子数组为 1 2，和为3
 * 难点是出现负数时怎么考虑
 * 使用的是动态规划的思想，通过备用数组记录已经计算过的值
 * 例如，输入的数组为 1，-2，3，10，-4，7，2，-5
 * 从i= 0开始，考虑每一位数，当最大和串包含它时，有两种情况，包含它前面的字串+它自己和只包含它自己的情况
 * 如果它前面的字串和小于0，那么最大字串即它自己，否则和为前者加上它
 * 上例求得：0(第一个数前的字串为0），1，-1，3，13，9，16，18，13
 * 所以最大字串的和是18，从2,7,-4,10,3，即连续的不为零的位置
 */
public class MaxSumOfSubSequence {
	
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int caseSize = 0;
		if(cin.hasNext()){
			caseSize = cin.nextInt();
		}
		for(int i = 0; i < caseSize; i++){
			int length = cin.nextInt();
			int[] array = new int[length];
			for(int j = 0; j < length; j++){
				array[j] = cin.nextInt();
			}
			getSubSequenceByAdd(array);
		}
	}
	
	public static void getSubSequence(int [] array){
		int leftSum = 0;
		int rightSum = 0;
		int length = array.length;
		for(int i = 0; i < array.length; i++){
			for(int m = i; m >= 0; m--){
				leftSum += array[m];
				if(leftSum < 0){
					break;
				}else{
					
				}
			}
		}
	}
	
	//don't work in some case
	public static void getSubSequenceByAdd(int[] array){
		int length = array.length;
		int[] sum = new int[length + 1];
		sum[0] = 0;
		for(int i = 0; i < length; i++){
			sum[i + 1] = sum[i] + array[i];
		}
		int max = 1;
		int min = 0;
		for(int i = 2; i < length + 1; i++){
			if(sum[i] > sum[max]){
				max = i;
			}
		}
		
		for(int i = 1; i < max; i++){
			if(sum[i] < sum[min]){
				min = i;
			}
		}
		int maxSum = 0;
		maxSum = sum[max] - sum[min];
		System.out.println(maxSum + " " + (min + 1) + " " + max);
	
	}

}
