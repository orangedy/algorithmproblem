package com.dy.basicAlgorithm;

public class DP {
	
	public static void main(String[] args) {
		lisTest();
		testLongestSubSum();
	}

	/**
	 * 最长递增子序列
	 * 
	 * @param L
	 */
	public static void lis(float[] L) {
		int n = L.length;
		int[] f = new int[n];// 用于存放f(i)值；
		f[0] = 1;// 以第a1为末元素的最长递增子序列长度为1；
		for (int i = 1; i < n; i++)// 循环n-1次
		{
			f[i] = 1;// f[i]的最小值为1；
			for (int j = 0; j < i; j++)// 循环i 次
			{
				if (L[j] < L[i] && f[j] > f[i] - 1)
					f[i] = f[j] + 1;// 更新f[i]的值。
			}
		}
		int max = 0;
		for(int i = 1; i < n; i++){
			if(f[i] > max){
				max = f[i];
			}
		}
		System.out.println("max increace sub element length is " + max);
	}
	
	public static void lisTest(){
		float[] L = {1, 3, 4, -1};
		lis(L);
	}
	
	public static void longestSubSum(int[] arr){
		int[] subSum = new int[arr.length];
		subSum[0] = arr[0];
		for(int i = 1; i < arr.length; i++){
			if(subSum[i - 1] > 0){
				subSum[i] = subSum[i - 1] + arr[i];
			}else{
				subSum[i] = arr[i];
			}
		}
		int maxSubSum = Integer.MIN_VALUE;
		int lastElement = -1;
		for(int i = 0; i < arr.length; i++){
			if(subSum[i] > maxSubSum){
				maxSubSum = subSum[i];
				lastElement = i;
			}
		}
		System.out.println("last element is a[" + lastElement + "], and max sub sum is "+ maxSubSum);
	}
	
	public static void testLongestSubSum(){
		int[] arr = { 1, 3, 2, -2, 3, 4, -5};
		longestSubSum(arr);
	}
}
