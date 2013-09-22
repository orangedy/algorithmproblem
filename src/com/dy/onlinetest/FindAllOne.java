package com.dy.onlinetest;

import java.util.Scanner;

/**
 * @author Administrator
 * 寻找1值n中所有的1的出现次数
 * 
 * 如n=12时，包括1,10,11,12，共5个1
 * 
 * 算法不够简洁，考虑的情况过于负责，应该简化一下
 * 简单来讲，是应该按位来统计，每一位出现1的次数是有规律的，主要受三个方面的影响，比它高的位，当前位，和低位
 * 规律如下：
 * 当m位的数字为0时，它只收高于它的位的影响，出现次数为高于其为的数字乘以10的m次方，比如1201，当前位为十位，高位为12，
 * 即会出现011x, 021x....121x等，一个会出现12乘以10次，其他情况十位不可能出现1；
 * 当m位的数字为1时，它受高于它的位数的影响，同时受低于它位数的影响，比如1214，高于位数可能出现12乘以10次1，
 * 同时当前位为1时，可出现11,12，13,14等情况，所以为高位*10的m次方+低位数值+1；
 * 当m位的数字大于1时，它只受高于其位数的数值的影响，如1234，十位为3，高位数值为12，十位可能出现1的次数为
 * 001x，011x，021x....121x，一共出现12+1种，次数为高位+1乘以10的m次方
 *
 */
public class FindAllOne {
	
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		if(cin.hasNext()){
			int n = cin.nextInt();
			int i = 0;
			int sum = 0;
			while(n >= Math.pow(10, i)){
				i++;
				sum += sumForBit(n, i);
			}
			System.out.println(sum);
			System.out.println(findOneNum(n));
		}
	}
	
	/**
	 * @param n	要统计的数
	 * @param m	统计的位数，如个位时，m取1，十位取2
	 * 
	 * 思路是按位统计每位出现1的概率
	 * 当前为第m位时，该位上出现1是有规律的，按照10^m循环出现，共10^(m-1)个1，从10^(m-1)-1开始，连续10^(m-1)个
	 * 如m=2，即十位，每出现100个数，则会出现10个1，当然还要考虑剩余的非完整循环中1的个数
	 * 那么1到n个数，在m上出现1的次数包括两部分
	 * 第一部分为完整循环中1的个数，为 n/10^m *10^(m-1)
	 * 第二部分为非完整循环中1的个数，余数为 n%10^m，如果其小于10^(m-1)-1，则没有1
	 * 如果大于10^(m-1) -1,则连续出现1，最多10^(m-1)个
	 * 比如133在十位上的1的个数，循环为100，完整循环1个，出现10个1，即10,11,12……19
	 * 非完整循环为33，从101到133，从110开始连续出现10个1,33-9>10，所以是10个,110,111,112^119
	 * 如果是113，则是13-9=4个，即110,111,112,113
	 * @return	该位上1的个数
	 */
	public static int sumForBit(int n, int m){
		int divisor = (int) Math.pow(10, m);
		if(n < divisor/10){
			return 0;
		}else{
			int value = n/divisor;
			int remainder = n%divisor;
			int count = value*(divisor/10);
			if(remainder < divisor/10){
				count += 0;
			}else{
				int add = remainder - divisor/10 + 1;
				add = add > (divisor/10) ? divisor/10 : add;  
				count += add;
			}
			System.out.println("数位：" + m + " 1的个数:" + count);
			return count;
		}
	}
	
	/**
	 * @param n，输入的参数，为10的次方
	 * @return	返回10^n内的所有1的次数
	 * 
	 * do not work
	 */
	public static int findOneInN(int n){
		if(n == 10){
			return 2;
		}else{
			return 1 + n/10 + (findOneInN(n/10) - 1)*10;
		}
	}

	/**
	 * 按照规律，按位求
	 * @param n
	 * @return
	 */
	public static int findOneNum(int n){
		int num = 1;
		int count = 0;
		while(n/num > 0){
			int high = n/num/10;
			int current = n/num%10;
			int low = n%num;
			if(current == 0){
				count += high*num;
			}else if(current == 1){
				count += high*num + low + 1;
			}else{
				count += (high + 1)*num;
			}
			num*=10;
		}
		return count;
	}
	
	public static void testFindOneNum(){
		int N = 12;
		System.out.println(findOneNum(N));
	}
}
