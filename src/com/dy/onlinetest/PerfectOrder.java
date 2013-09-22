package com.dy.onlinetest;

import java.io.*;
import java.util.regex.Pattern;

public class PerfectOrder {
	public static void main(String args[]) {
		String sysInputFile = "perfectorder";
		try {
			File file = new File(sysInputFile);
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			int groupNum = Integer.parseInt(in.readLine().trim());
			
//			Pattern p = Pattern.compile(" +");
			
			for(int i = 0; i < groupNum; i++){
				int[] numPerType = new int[5];
				String[] firstline = in.readLine().trim().split(" +");
				numPerType[0] = Integer.parseInt((firstline[0].trim()));
				numPerType[1] = Integer.parseInt((firstline[1].trim()));
				numPerType[2] = Integer.parseInt((firstline[2].trim()));
				numPerType[3] = Integer.parseInt((firstline[3].trim()));
				numPerType[4] = Integer.parseInt((firstline[4].trim()));
				
				int[][] a = new int[5][];
				for(int j = 0; j < 5; j++){
					String line = in.readLine().trim();
					String temp[] = line.split(" +");
					a[j] = new int[numPerType[j]];
					for(int k = 0; k < numPerType[j]; k++){
						a[j][k] = Integer.parseInt(temp[k].trim());
					}
				}
				
				int min, max;
				String lastLine = in.readLine().trim();
				min = Integer.parseInt(lastLine.split(" +")[0].trim());
				max = Integer.parseInt(lastLine.split(" +")[1].trim());
				
				int[] meatDishs = getKindsOfDishs(a[3]);
				int[] vegetables = getKindsOfDishs(a[4]);
				
				int[] riceAndSoup = getKindsOfRiceAndSoup(a[0], a[1]);
				int[] noodles = sort(a[2]);
				
				int methods = 0;
				for(int j = 0; j < meatDishs.length; j++){
					int pricea = meatDishs[j];
					if(pricea < max){
						for(int k = 0; k < vegetables.length; k++){
							int priceb = vegetables[k];
							if((pricea + priceb) < max){
								for(int n = 0; n < noodles.length; n++){
									int pricec = noodles[n];
									int price = pricea + priceb + pricec;
									if(price >= min && price <= max){
										methods++;
									}else if(price > max){
										break;
									}
								}
								
								for(int n = 0; n < riceAndSoup.length; n++){
									int pricec = riceAndSoup[n]; 
									int price = pricea + priceb + pricec;
									if(price >= min && price <= max){
										methods++;
									}else if(price > max){
										break;
									}
								}
							}else{
								break;
							}
						}
					}else{
						break;
					}
				}
				
//				for(int n = 0; n < noodles.length; n++){
//					int price = noodles[n];
//					if(price >= min && price <= max){
//						methods++;
//					}
//				}
				
				System.out.println(methods);
				
			}
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			e.printStackTrace();
		}
	}
	
	public static int[] getKindsOfDishs(int[] food){
		
		int length = food.length*(food.length - 1)/2 + food.length;
		int[] prices = new int[length];
		int flag = 0;
		for(int i = 0; i < food.length; i++){
			prices[flag] = food[i];
			flag++;
			for(int j = i + 1; j < food.length; j++){
				prices[flag] = food[i] + food[j];
				flag++;
			}
		}
		sort(prices);
		return prices;
	}
	
	public static int[] getKindsOfRiceAndSoup(int[] a, int[] b){
		int[] prices = new int[a.length*b.length];
		
		int flag = 0;
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < b.length; j++){
				prices[flag] = a[i] + b[j];
				flag++;
			}
		}
		sort(prices);
		return prices;
	}
	
	public static int[] sort(int[] array){
		for(int i = 0; i < array.length; i++){
			int min = array[i];
			int flag = i;
			for(int j = i + 1; j < array.length; j++){
				if(array[j] < min){
					min = array[j];
					flag = j;
				}
			}
			int temp = array[i];
			array[i] = min;
			array[flag] = temp;
		}
		return array;
	}
	
}
