package com.dy.onlinetest;

/**
 * 十六进制序列
描述：有一个十六进制序列由S1S2…Sk组成，其中Sk由1到k的十六进制数字依次组成。给定一个位置i，返回第i个位置上的字符。数字i为32位int
例如，S1S2…Sk的前200个字符为
112123123412345123456123456712345678123456789123456789A123456789AB123456789ABC123456789ABCD123456789ABCDE123456789ABCDEF123456789ABCDEF10123456789ABCDEF1011123456789ABCDEF101112123456789ABCDEF10111213
所以i=50时，字符为5，i=100时，字符为9

要求时间复杂度小于O(n)，空间复杂度O(1)
 * @author orangedy
 * @version 2013-9-22 下午4:09:11
 */
public class GetNum {

	public static void main(String[] args) {
		System.out.println(getNum(100));
	}
	
	/**
	 * 查找第i位的符号
	 * 从s1开始，计算每个sk，每个sk的长度等于1到sk的长度累加
	 * 
	 * @param i
	 * @return
	 */
	public static char getNum(int i){
		int count = 0;
		int sk = 0;
		while(true){
			sk++;
			for(int j = 1; j <= sk; j++){
				count += getLen(j);
				if(count >= i){
					count -= getLen(j);
					return getSixTeen(index(j, i - count));
				}
			}
		}
	}
	
	/**
	 * 返回num的16进制的第i位(位从1开始计数)
	 * 
	 * @param num
	 * @param i
	 * @return
	 */
	public static int index(int num, int i){
		i = getLen(num) - i + 1;
		for(int j = 1; j < i; j++){
			num/=16;
		}
		return num%16;
	}
	
	/**
	 * 得到k的十六进制的位的长度
	 * 
	 * @param k
	 * @return
	 */
	public static int getLen(int k){
		int length = 1;
		while(k/16 > 0){
			length++;
			k = k/16;
		}
		return length;
	}
	
	/**
	 * 打印0到15的十六进制符合
	 * 
	 * @param i
	 * @return
	 */
	public static char getSixTeen(int i){
		if(i < 10){
			return (char) ('0' + i);
		}else{
			return (char) ('A' + (i - 10));
		}
	}
}
