package com.dy.basicAlgorithm;

public class CommonProblem {

	public static void main(String[] args) {
		System.out.println(CommonProblem.zouLouTi(10));
		System.out.println(CommonProblem.zouLouTi2(10));
		CommonProblem.testSortMatrix();
		CommonProblem.testNewtonSqrt();
	}

	/**
	 * 走楼梯算法，每次可以走一步，两步或者三步，求N阶楼梯有多少种走法 递归实现，效率较差，比较易于理解
	 * 
	 * @param N
	 * @return
	 */
	public static int zouLouTi(int N) {
		if (N == 1) {
			return 1;
		} else if (N == 2) {
			return 2;
		} else if (N == 3) {
			return 3;
		} else {
			return zouLouTi(N - 1) + zouLouTi(N - 2) + zouLouTi(N - 3);
		}
	}

	/**
	 * 走楼梯的递推实现，效率高
	 * 
	 * @param N
	 * @return
	 */
	public static int zouLouTi2(int N) {
		int[] result = new int[3];
		result[0] = 1;
		result[1] = 2;
		result[2] = 3;
		if (N < 4) {
			return result[N];
		} else {
			for (int i = 4; i <= N; i++) {
				int next = result[0] + result[1] + result[2];
				result[0] = result[1];
				result[1] = result[2];
				result[2] = next;
			}
			return result[2];
		}
	}

	/**
	 * 查找一个m*n阶的横和竖都有序的矩阵中是否存在某个元素
	 * 
	 * @param M
	 * @return
	 */
	public static boolean sortMatrix(int[][] M, int m, int n, int element) {
		int i = m;
		int j = 0;
		while (i >= 0 && j <= n) {
			if (element == M[i][j]) {
				return true;
			} else if (element > M[i][j]) {
				j++;
			} else {
				i--;
			}
		}
		return false;
	}

	public static void testSortMatrix() {
		int[][] M = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		System.out.println(sortMatrix(M, 3, 3, 19));
	}

	/**
	 * 牛顿迭代求根法，求根号N的大小，精度为precision
	 * 
	 * @param N
	 * @param precision
	 * @return
	 */
	public static double newtonSqrt(int N, double precision) {
		double x = N;
		while (x * x - N > precision) {
			x = x - (x * x - N) / (2 * x);
		}
		return x;
	}

	public static void testNewtonSqrt() {
		int N = 25;
		System.out.println(newtonSqrt(N, 0.0001));
	}
}
