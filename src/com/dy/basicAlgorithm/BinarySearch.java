package com.dy.basicAlgorithm;

/**
 * @author orangedy
 * @version 2013-4-2 下午9:18:59
 * 
 * 二分查找，要注意边界问题
 */

public class BinarySearch {

	public static void main(String[] args) {
		int test1[] = { 1, 3, 4, 7 };
		System.out.println(search(test1, 4, 10));
		System.out.println(search3(test1, 4, 10));
		System.out.println(search4(test1, 4, 10));
	}

	/**
	 * 左闭右闭区间，即每次循环时，可能值包括left和right本身，[left, right]
	 * 那么下次循环也必须是左闭右闭区间，即[left, middle-1]或者[middle+1, right]
	 * 而且结束条件必须是left<=right，必须包含等于，因为最终结果可能在[a,a]区间里
	 * 
	 * @param array
	 * @param n
	 * @param v
	 * @return
	 */
	static int search(int array[], int n, int v) {
		int left, right, middle;

		left = 0;
		right = n - 1;

		while (left <= right) {
			middle = (left + right) / 2;
			if (array[middle] > v) {
				right = middle - 1;
			} else if (array[middle] < v) {
				left = middle + 1;
			} else {
				return middle;
			}
		}

		return -1;
	}

	/**
	 * 左闭右开区间，循环时，可能值包括[left, right)，只包括left，不包括right
	 * 那么进入下次循环后，也必须保持[middle+1, right)，或者[left, middle)
	 * 而且结束条件必须为left<right，不能包括等于，因为结果不可能在[a,a)中
	 * 
	 * @param array
	 * @param n
	 * @param v
	 * @return
	 */
	static int search3(int array[], int n, int v) {
		int left, right, middle;

		left = 0;
		right = n;

		while (left < right) {
			middle = (left + right) / 2;

			if (array[middle] > v) {
				right = middle;
			} else if (array[middle] < v) {
				left = middle + 1;
			} else {
				return middle;
			}
		}

		return -1;
	}
	
	/**
	 * 左开右开区间，每次循环可能的结果是，(left, right)，left和right都不包括
	 * 那么，进入下次循环也必须保证为，(middle, right)或者(left, middle)
	 * 而且，结束条件必须是left<right-1，因为结果不可能在区间(a, a+1)中
	 * 
	 * @param array
	 * @param n
	 * @param v
	 * @return
	 */
	static int search4(int array[], int n, int v) {
		int left, right, middle;

		left = -1;
		right = n;

		while (left < right - 1) {
			middle = (left + right) / 2;

			if (array[middle] > v) {
				right = middle;
			} else if (array[middle] < v) {
				left = middle;
			} else {
				return middle;
			}
		}

		return -1;
	}
}
