package com.dy.onlinetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LongTeamName {
	public static void main(String[] args) {
		String sysInputFile = "teamname";
		try {
			File file = new File(sysInputFile);
			BufferedReader in = new BufferedReader(new FileReader(file));
			int groupNum = Integer.parseInt(in.readLine().trim());
			
			String line;
			for (int i = 0; i < groupNum; i++) {
				int nameNum = Integer.parseInt(in.readLine().trim());
				String[] names = new String[nameNum];
				for (int j = 0; j < nameNum; j++) {
					line = in.readLine().trim();
					names[j] = line;
					String temp;
					for (int k = 0; k < j; k++){
						if(names[j].compareTo(names[k]) < 0){
							temp = names[j];
							for (int l = j - 1; l >= k; l--) {
								names[l + 1] = names[l];
							}
							names[k] = temp;
						}
					}
				}
				for (int j = 0; j < nameNum; j++){
					System.out.print(names[j]);
				}
			}
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("can't parse numbers");
		}
	}
}
