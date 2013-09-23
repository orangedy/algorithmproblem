package com.dy.onlinetest;

public class Zifu {

	public static void main(String[] args) {
		char[] str1 = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', 'n', ' ',
				'b', 'o', 'o', 'k', '.' };
		char[] str2 = { 'b', 'a', 'b', 'k' };
		deleteChar(str1, str2);
	}

	public static void deleteChar(char[] str1, char[] str2) {
		int str1Len = str1.length;
		int str2Len = str2.length;
		char[] newStr1 = new char[str1Len];

		// 用于统计第二个字符串中出现的字符，出现为true,否则为false
		boolean[] isExit = new boolean[128];
		for (int i = 0; i < str2.length; i++) {
			// 标记第二个字符串中出现的字符，
			isExit[str2[i]] = true;
		}

		int newLen = 0;
		for (int i = 0; i < str1.length; i++) {
			// 如果该字符在第二个字符串中出现，则删除，否则添加到新串
			if (isExit[str1[i]] == true) {

			} else {
				newStr1[newLen] = str1[i];
				newLen++;
			}
		}

		// 输出新串
		for (int i = 0; i < newLen; i++) {
			System.out.println(newStr1[i]);
		}
	}

}
