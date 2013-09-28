package com.dy.basicAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class PermutationAndCombination {

	public static void main(String[] args) {
		int[] list = { 1, 2, 3, 4, 5, 6 };
		System.out.println("********找出全部的排列************");
		int count = permutation(list, 0, 5);
		System.out.println(count);
		System.out.println("*********找出全部的组合递归法***********");
		List<Integer> temp = new ArrayList<Integer>();
		int count1 = combination(list, 0, 6, temp);
		System.out.println(count1);
		System.out.println("***********找出全部的组合01法*************");
		int count2 = combination2(list);
		System.out.println(count2);
		System.out.println("*********打印n个数中取m个的全部组合**********");
		int count3 = combination3(list, 6, 3);
		System.out.println(count3);
		System.out.println("**********从n个数中取出m个的全部组合递归法**********");
		int[] out = new int[3];
		int count4 = combination4(list, 6, 3, out, 3);
		System.out.println(count4);
	}

	/**
	 * 求全排列
	 * 
	 * @param list
	 * @param start
	 * @param length
	 * @return
	 */
	public static int permutation(int[] list, int start, int length) {
		int i;
		int count = 0;
		if (start > length) {
			// 当前已经到达最后一位，进行输出即可
			for (i = 0; i <= length; i++) {
				System.out.print(list[i]);
			}
			System.out.println();
			return ++count;
		} else {
			// 当前位为start，可以依次与后面的位交换，然后进行递归，求子数组的全排列
			for (i = start; i <= length; i++) {
				swap(list, start, i);// 当前位与第i位交换
				count += permutation(list, start + 1, length);// 求第start+1位后的数的全排列
				swap(list, start, i);// 回溯
			}
			return count;
		}
	}

	public static void swap(int[] list, int start, int i) {
		int temp = 0;
		temp = list[start];
		list[start] = list[i];
		list[i] = temp;
	}

	/**
	 * 全组合 递归的思想，从前往后，依次判断取该位数和不取该位数进行递归
	 * 
	 * @param list
	 * @param start
	 * @param length
	 * @param out
	 * @return
	 */
	public static int combination(int[] list, int start, int length,
			List<Integer> out) {
		int count = 0;
		if (start == length) {
			// 当前已经递归到了最后一位，进行输出
			int i = out.size();
			for (int j = 0; j < i; j++) {
				System.out.print(out.get(j));
			}
			System.out.println();
			return ++count;
		} else {
			// 当前为第start位
			out.add(list[start]); // 取第start位
			count += combination(list, start + 1, length, out); // 递归，判断start+1位开始的全组合
			out.remove(out.size() - 1); // 回溯，去掉第start位
			count += combination(list, start + 1, length, out); // 递归，判断start+1为开始的全组合
		}
		return count;
	}

	/**
	 * 全排列，从000000到111111进行列举，每一位为0则不包含该位的数字，为1则包含，进行输出
	 * 
	 * @param list
	 * @return
	 */
	public static int combination2(int[] list) {
		int length = list.length;
		int count = (int) Math.pow(2, list.length);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < list.length; j++) {
				if (((i >> j) & 1) == 1) {
					System.out.print(list[length - 1 - j]);
				} else {
					// System.out.print("x");
				}
			}
			System.out.println();
		}
		return count;
	}

	/**
	 * 从n个数中取m个数的全组合 从01组合的形式进行查找 首先初始化，将数组前n个元素置1，表示第一个组合为前n个数。
	 * 然后从左到右扫描数组元素值的“10
	 * ”组合，找到第一个“10”组合后将其变为“01”组合，同时将其左边的所有“1”全部移动到数组的最左端（只有第一位变为0才需要移动
	 * ，否则其左边的1本来就在最左端，无需移动）。 当第一个“1”移动到数组的m-n的位置，即n个“1”全部移动到最右端时，就得到了最后一个组合。
	 * 
	 * @param list
	 * @param m
	 * @param n
	 * @return
	 */
	public static int combination3(int[] list, int n, int m) {
		int count = 0;
		int[] temp = new int[n];
		// 将前m为置为1
		for (int i = 0; i < m; i++) {
			temp[i] = 1;
		}
		// 打印并计数
		printCombination(list, temp, n);
		count++;
		for (int i = 0; i < n - 1; i++) {
			// 从左至右查找，找到第一个10组合
			if (temp[i] == 1 && temp[i + 1] == 0) {
				// 进行交换
				swap(temp, i, i + 1);
				// 同时将左边的所有1移到最左端
				int k = 0;
				for (int j = 0; j < i; j++) {
					if (temp[j] == 1) {
						swap(temp, k, j);
						k++;
					}
				}
				printCombination(list, temp, n);
				count++;
				i = -1;
			}
		}
		return count;
	}

	public static void printCombination(int[] list, int[] temp, int length) {
		for (int i = 0; i < length; i++) {
			if (temp[i] == 1) {
				System.out.print(list[i]);
			}
		}
		System.out.println();
	}

	/**
	 * 从n个数中取m个数的全组合 递归方法实现 从数组尾到头，最后位往前依次取，直到取完m个数输出组合
	 * 这里不需要回溯是因为输出数组定长，前一次用完后，后一次直接覆盖了前一次的值，所以不用回溯
	 * 
	 * @param list
	 * @param n
	 * @param m
	 * @param out
	 * @param outLen
	 * @return
	 */
	public static int combination4(int[] list, int n, int m, int[] out,
			int outLen) {
		int count = 0;
		if (m == 0) {
			for (int i = 0; i < outLen; i++) {
				System.out.print(out[i]);
			}
			System.out.println();
			return 1;
		} else {
			// 取第m个数，有n-m种取法
			for (int i = n; i >= m; i--) {
				out[m - 1] = list[i - 1];
				// 递归的从i-1个数中取m-1个数
				count += combination4(list, i - 1, m - 1, out, outLen);
			}
		}
		return count;
	}
}
