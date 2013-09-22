package com.dy.onlinetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class MusicPlayer {
	public static void main(String args[]) {
		String sysInputFile = "musicplayer";
		try {
			File file = new File(sysInputFile);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			int groupNum = Integer.parseInt(in.readLine().trim());
			int singNum, operateNum;
			String line2 = in.readLine().trim();
			singNum = Integer.parseInt(line2.split(" ")[0]);
			operateNum = Integer.parseInt(line2.split(" ")[1]);

			for (int i = 0; i < groupNum; i++) {
				Stack<Integer> stack = new Stack<Integer>();
				stack.push(1);
				int currentSing = 1;
				for (int j = 0; j < operateNum; j++) {
					line = in.readLine().trim();
					if (line.equals("PRE")) {
						if (stack.isEmpty()) {
							currentSing = 1;
						} else {
							stack.pop();
							if (stack.isEmpty()) {
								currentSing = 1;
							} else {
								currentSing = stack.peek();
							}
						}
					} else if (line.equals("NEXT")) {
						currentSing += 1;
						if (currentSing >= singNum) {
							currentSing = singNum;
						}
					} else {
						currentSing = Integer.parseInt(line.split(" ")[1]);
					}
					System.out.println(currentSing);
					if (stack.isEmpty() || currentSing != stack.peek()) {
						stack.push(currentSing);
					}
				}
				in.readLine();
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("can't parse input data");
		}
	}
}
