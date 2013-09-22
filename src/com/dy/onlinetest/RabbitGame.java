package com.dy.onlinetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RabbitGame {
	public static void main(String[] args) {
		String sysInputFile = "rabbitgame";
		try{
			File file = new File(sysInputFile);
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			int groupNum = Integer.parseInt(in.readLine().trim());
			
			for (int i = 0; i < groupNum; i++) {
				String line1 = in.readLine().trim();
				int node = Integer.parseInt(line1.split(" ")[0]);
				int edge = Integer.parseInt(line1.split(" ")[1]);
				
				String line;
				Node[] nodes = new Node[node];
				for(int j = 0; j < node; j++){
					nodes[j] = new Node(j + 1);
				}
				for (int j = 0; j < edge; j++){
					line = in.readLine().trim();
					int a = Integer.parseInt(line.split(" ")[0]);
					int b = Integer.parseInt(line.split(" ")[1]);
					nodes[a - 1].linkedNodeId.add(b);
					nodes[b - 1].linkedNodeId.add(a);
				}
				int[] possible = new int[nodes.length];
				for(int j = 0; j < nodes.length; j++){
					possible[j] = j + 1;
				}
				int step = calculateTime(nodes, possible);
				System.out.println(step);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
	public static int calculateTime(Node[] nodes, int[] possible){
		if(possible.length <= 3){
			return 1;
		}else{
			for(int i = 0; i < possible.length; i++){
				for(int j = i + 1; j < possible.length; j++){
					HashSet<Integer> possibleNext = new HashSet<Integer>();
					for(int k = 0; k < possible.length; k++){
						if(k != i && k != j){
							possibleNext.addAll(nodes[possible[k] -1].linkedNodeId);
						}
					}
					if(possibleNext.size() >= possible.length){
						
					}else{
						int[] possibleNextArray = new int[possibleNext.size()];
						int temp = 0;
						for(Integer integer : possibleNext){
							possibleNextArray[temp] = (int) integer;
							temp++;
						}
						return 1 + calculateTime(nodes, possibleNextArray);
					}
				}
			}
			return -1;
		}
	}
}

class Node {
	private int nodeId;
	List<Integer> linkedNodeId;
	
	public Node(int nodeId) {
		super();
		this.nodeId = nodeId;
		this.linkedNodeId = new ArrayList<Integer>();
	}
	
	
}