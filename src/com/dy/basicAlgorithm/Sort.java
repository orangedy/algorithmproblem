package com.dy.basicAlgorithm;

/**
 * 基本的排序算法实现
 * 
 * @author orangedy
 * @version 2013-9-17 下午3:19:57
 */
public class Sort {

	public static void main(String[] args) {
		int[] a = { 3, 4, 7, 10, 2, 5, 6, 8, 9, 1 };
//		Sort.bubbleSort(a, a.length);
//		Sort.bubbleSortWithFlag(a, a.length);
//		Sort.bubbleSort2(a, a.length);
//		Sort.insertSort(a, a.length);
//		Sort.insertSort2(a, a.length);
//		Sort.shellSort(a, a.length);
//		Sort.selectSort(a, a.length);
//		Sort.mergeSort(a, a.length);
//		Sort.minHeapSort(a, a.length);
		Sort.quickSort(a, a.length);
	}

	/**
	 * 基本冒泡
	 * 
	 * @param a
	 * @param size
	 */
	public static void bubbleSort(int[] a, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					swap(a, j, j + 1);
				}
			}
		}
		printArray(a, size);
	}

	/**
	 * 带标识位的冒泡，当一趟没有进行任何交换时说明已经全部有序
	 * 
	 * @param a
	 * @param size
	 */
	public static void bubbleSortWithFlag(int[] a, int size) {
		boolean flag = true;
		for (int i = 0; i < size && flag == true; i++) {
			flag = false;
			for (int j = 0; j < size - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					swap(a, j, j + 1);
					flag = true;
				}
			}
		}
		printArray(a, size);
	}

	/**
	 * 带标记的冒泡，每次记住最后一次交换的位置，说明该位置之后已经有序 下一次冒泡的终点为上一轮最后的交换位置
	 * 
	 * @param a
	 * @param size
	 */
	public static void bubbleSort2(int[] a, int size) {
		int flag = size - 1;
		int k = 0;
		for (int i = 0; i < size && flag > 0; i++) {
			k = flag;
			flag = 0;
			for (int j = 0; j < k; j++) {
				if (a[j] > a[j + 1]) {
					swap(a, j, j + 1);
					flag = j;
				}
			}
		}
		printArray(a, size);
	}

	/**
	 * 直接插入排序，插入时用交换的方式，类似冒泡
	 * 
	 * @param a
	 * @param size
	 */
	public static void insertSort(int[] a, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = i; j > 0; j--) {
				if (a[j] < a[j - 1]) {
					swap(a, j - 1, j);
				} else {
					break;
				}
			}
		}
		printArray(a, a.length);
	}

	/**
	 * 直接插入排序，插入时，采用后移的方式，找到插入位置后将记录后移，不用挨个交换
	 * 
	 * @param a
	 * @param size
	 */
	public static void insertSort2(int[] a, int size) {
		for (int i = 1; i < size; i++) {
			if (a[i] < a[i - 1]) {
				int temp = a[i];
				//找到插入位置
				int flag;
				for (flag = i - 1; flag >= 0; flag--) {
					if (temp < a[flag]) {
						a[flag + 1] = a[flag];
					} else {
						break;
					}
					a[flag] = temp;
				}
			}
		}
		printArray(a, a.length);
	}

	/**
	 * 希尔排序，类似直接插入排序，进行多趟直接插入排序，当数组局部有序后直接插入排序代价较低
	 * @param a
	 * @param size
	 */
	public static void shellSort(int[] a, int size) {
		int grap = size / 2;
		while (grap > 0) {
			for (int i = 0; i < grap; i++) {
				for (int j = i + grap; j < size; j += grap) {
					if (a[j] < a[j - grap]) {
						for (int flag = j - grap; flag >= 0; flag -= grap) {
							if (a[flag + grap] < a[flag]) {
								swap(a, flag, flag + grap);
							} else {
								break;
							}
						}
					}
				}
			}
			grap = grap / 2;
		}
		printArray(a, a.length);
	}
	
	/**
	 * 直接选择排序，加标记，不用每次交换
	 * @param a
	 * @param size
	 */
	public static void selectSort(int[] a, int size){
		/*for (int i = 0; i < size; i++){
			for(int j = i + 1; j < size; j++){
				if(a[j] < a[i]){
					swap(a, i, j);
				}
			}
		}*/
		//加标记
		for (int i = 0; i < size; i++){
			int flag = i;
			for(int j = i + 1; j < size; j++){
				if(a[j] < a[flag]){
					flag = j;
				}
			}
			swap(a, i, flag);
		}
		printArray(a, a.length);
	}
	
	/**
	 * 归并排序
	 * 步骤是先用分治的思想，将规模减少到1，这样就能保证有序
	 * 然后将两个有序的数组进行归并操作
	 * 
	 * @param a
	 * @param size
	 */
	public static void mergeSort(int[] a, int size){
		int[] b = new int[size];
		mSort(a, b, 0, size - 1);
		printArray(a, b.length);
	}
	
	/**
	 * 需要注意边界问题
	 * 
	 * @param a
	 * @param b
	 * @param start
	 * @param end
	 */
	public static void mSort(int[] a, int[] b, int start, int end){
		if(start == end){
			//不需要将a复制到b
			//b[start] = a[start];
		}else{
			int m = (start + end)/2;
			mSort(a, b, start, m);
			mSort(a, b, m + 1, end);
			merge(a, b, start, m + 1, end);
		}
	}
	
	/**
	 * 合并两个子数组，分别为a[startA,startB-1]和a[startB,end]
	 * 合并后存入到b的[start, end]间，再从b写到a中
	 * 需要注意的点是，归并后需要将临时数组的内容复制到原数组，最终排好序的数据需要写回原数组
	 * @param a
	 * @param b
	 * @param start
	 * @param middle
	 * @param end
	 */
	public static void merge(int[] a, int[] b, int startA, int startB, int end){
		int i = startA;
		int j = startB;
		int k = startA;
		while(i < startB && j <= end){
			if(a[i] < a[j]){
				b[k] = a[i];
				i++;
			}else{
				b[k] = a[j];
				j++;
			}
			k++;
		}
		while(i < startB){
			b[k] = a[i];
			i++;
			k++;
		}
		while(j <= end){
			b[k] = a[j];
			j++;
			k++;
		}
		for(int m = startA; m <= end; m++){
			a[m] = b[m];
		}
		
	}
	
	/**
	 * 最小堆排序，完成后数组降序
	 * 基本步骤：
	 * 1. 建立最小堆，从非叶子节点开始调整，直到根节点，此时所有节点都符合最小堆定义
	 * 2. 输入堆顶元素，将最尾的节点替换到堆顶，再对堆顶元素进行调整，重新达到最小堆
	 * 应用：
	 * 最小堆可以实现topN，最大堆可以实现minN
	 * 
	 * @param a
	 * @param size
	 */
	public static void minHeapSort(int[] a, int size){
		for(int i = size/2 - 1; i >= 0; i--){
			minHeapAdjust(a, i, size);
		}
		for(int i = 0; i < size; i++){
			swap(a, 0, size - i - 1);
			minHeapAdjust(a, 0, size - i - 1);
		}
		printArray(a, a.length);
	}
	
	/**
	 * 类似递推的过程，调整某个节点，如果改变，则递推到被改变的子节点，一直下去
	 * @param a
	 * @param i
	 * @param size
	 */
	public static void minHeapAdjust(int[] a, int i, int size){
		for(int j = 2*i + 1; j < size; j = 2*j + 1){
			//每次与小的子节点进行比较
			if(j + 1 < size && a[j] > a[j + 1]){
				j = j + 1;
			}
			if(a[i] < a[j]){
				break;
			}else{
				swap(a, i, j);
				i = j;
			}
		}
	}
	
	public static void quickSort(int[] a, int size){
		qSort(a, 0, size - 1);
		printArray(a, a.length);
	}
	
	public static void qSort(int[] a, int start, int end){
		if(start < end){
			int position = findPosition(a, start, end);
			qSort(a, start, position - 1);
			qSort(a, position + 1, end);
		}
	}

	
	public static int findPosition(int[] a, int start, int end){
		int element = a[start];
		while(start < end){
			/*while(start < end && a[end] > element){
				end--;
			}
			if(start < end){
				swap(a, start, end);
				start++;
			}
			while(start < end && a[start] < element){
				start++;
			}
			if(start < end){
				swap(a, start, end);
				end--;
			}*/
			while(start < end && a[end] >= element){
				end--;
			}
			a[start] = a[end];
			while(start < end && a[start] <= element){
				start++;
			}
			a[end] = a[start];
		}
		a[start] = element;
		return start;
	}

	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void swap2(int a, int b){
		//当a，b相等时，将a，b变成零
		if (a != b) {
			a ^= b;
			b ^= a;
			a ^= b;
		}
	}

	public static void printArray(int[] a, int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}
