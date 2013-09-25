package com.dy.onlinetest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 亚马逊在线测评的题目，一个是完全背包问题，一个是约瑟夫环问题，都比较简单，但是没有抓住好机会
 * 
 * @author Administrator
 *
 */
public class AmazonTest {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		List<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++){
			a.add(cin.nextInt());
		}
		int b = cin.nextInt();
		List<Integer> result = caculate1(a, b);
		for(int i = 0; i < result.size(); i++){
			System.out.println(result.get(i));
		}
		
		//testDequeue();
		
	}

	//背包问题，给b元钱，有一系列商品，给出每件商品的价格a，求花完b元钱时总的商品数最少
	
	//这个是贪心实现，结果贪心并不能保证最终的购买数最少，大意了
    static List<Integer> caculate(List<Integer> a, int b) {
        // a : storing the prices for each good, in the same order as the input good
        // b : money
        // return value: storing the number of bought goods, in the same order as the input good

    	int length = a.size();
    	List<Integer> count = new ArrayList<Integer>(length);
    	List<Integer> temp = new ArrayList<Integer>();
    	for(int i = 0; i < length; i++){
    		temp.add(a.get(i));
    		count.add(0);
    	}
    	
    	if(!getNext(count, temp, b)){
    		List<Integer> emply = new ArrayList<Integer>();
    		emply.add(0);
    		return emply;
    	}else{
    		return count;
    	}
    }
    
    public static boolean getNext(List<Integer> count, List<Integer> price, int money){
    	while(money > 0){
    		int mostExpensive = 0;
    		int goodsNO = -1;
    		for(int i = 0; i < price.size(); i++){
    			if(price.get(i) > mostExpensive){
    				mostExpensive = price.get(i);
    				goodsNO = i;
    			}
    		}
    		if(goodsNO == -1){
    			return false;
    		} else {
    			price.set(goodsNO, 0);
    			int maxNum = money/mostExpensive;
    			if(money%mostExpensive == 0){
    				count.set(goodsNO, maxNum);
    				return true;
    			}else{
    				int i;
    				for(i = maxNum; i >= 0; i--){
    					count.set(goodsNO, i);
    					if(getNext(count, price, money - mostExpensive*i)){
    						break;
    					}
    				}
    				if(i < 0){
    					return false;
    				}else{
    					return true;
    				}
    			}
    		}
    	}
		return true;
    }
    
    //动态规划实现
    public static List<Integer> caculate1(List<Integer> a, int b) {
    	int length = a.size();
    	int[][] result = new int[a.size() + 1][b + 1];
    	int[][] number = new int[a.size() + 1][b + 1];
    	for(int i = 1; i < b + 1; i++){
    		result[0][i] = -1;
    	}
    	for(int i = 1; i <= length; i++){
    		for(int j = 1; j <= b; j++){
    			int min = Integer.MAX_VALUE;
    			int min_k = -1;
    			for(int k = j/a.get(i - 1); k >=0; k--){
    				if(result[i - 1][j - a.get(i - 1)*k] == -1){
    					continue;
    				}else{
    					if(min > result[i - 1][j - a.get(i - 1)*k] + k){
    						min = result[i - 1][j - a.get(i - 1)*k] + k;
    						min_k = k;
    					}
    				}
    			}
    			if(min_k != -1){
    				result[i][j] = min;
    				number[i][j] = min_k;
    			}else{
    				result[i][j] = -1;
    			}
    		}
    	}
    	int i = length;
    	int j = b;
    	if(result[i][j] == -1){
    		return Arrays.asList(0);
    	}
    	Integer[] quantity = new Integer[length];
    	while(i >= 1 && j >= 0){
    		quantity[i - 1] = number[i][j];
    		j -= number[i][j]*a.get(i - 1);
    		i--;
    	}
    	return Arrays.asList(quantity);
    }
    
    
    //约瑟夫环
    public static String[] dequeue(int count, String[] queue) {
    	List<String> dynamicQueue = new ArrayList<String>(queue.length);
    	for(int i = 0; i < queue.length; i++){
    		dynamicQueue.add(queue[i]);
    	}
    	int counter = 1;
    	int pos = 0;
    	String[] result = new String[queue.length];
    	int resultIndex = 0;
    	while(!dynamicQueue.isEmpty()){
    		if(counter == count){
    			result[resultIndex++] = dynamicQueue.remove(pos);
    			counter = 1;
    		}else{
    			counter++;
    			pos++;
    			if(pos >= dynamicQueue.size()){
    				pos = pos - dynamicQueue.size();
    			}
    		}
    	}
    	return result;
    }
    
    public static void testDequeue(){
    	int count = 5;
    	String[] queue = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    	String[] result = dequeue(count, queue);
    	for(int i = 0; i < result.length; i++){
    		System.out.println(result[i]);
    	}
    }

}
