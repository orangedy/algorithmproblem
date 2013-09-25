package com.dy.basicAlgorithm;

import java.util.Stack;

/**
 * 入栈出栈操作，根据入栈序列，求所有的出栈序列
 * 或者根据入栈序列，判断出栈序列是否正确
 * 
 * @author Administrator
 *
 */
public class InOutStack {

	public static void main(String[] args) {
		printAllOutSequence();
	}

	public static void printAllOutSequence() {
		Stack<Integer> input = new Stack<Integer>();
		Stack<Integer> temp = new Stack<Integer>();
		Stack<Integer> output = new Stack<Integer>();
		for (int i = 0; i < 4; i++) {
			input.push(4 - i);
		}
		stackSeq(input, temp, output);
	}

	/**
	 * 递归的模拟入栈出栈过程
	 * 
	 * @param input
	 * @param temp
	 * @param output
	 */
	public static void stackSeq(Stack<Integer> input, Stack<Integer> temp,
			Stack<Integer> output) {
		if (input.isEmpty() && temp.isEmpty()) {
			getOutputSeq(output);
		} else {
			//模拟入栈，将input的元素进行入栈，可以如1，2,3,4个
			if (!input.isEmpty()) {
				temp.push(input.pop());
				stackSeq(input, temp, output);
				//回溯
				input.push(temp.pop());
			}
			//模拟出栈，入完几个后，可以选择出栈
			if (!temp.isEmpty()) {
				output.push(temp.pop());
				stackSeq(input, temp, output);
				//回溯
				temp.push(output.pop());
			}
		}
	}

	public static void getOutputSeq(Stack<Integer> output) {
		for (int i = 0; i < output.size(); i++) {
			System.out.print(output.get(i));
		}
		System.out.println();
	}
}
