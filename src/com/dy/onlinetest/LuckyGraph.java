package com.dy.onlinetest;

/*Sample code to read in test cases:*/
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LuckyGraph {
	public static void main(String args[]) throws NumberFormatException {
		String sysInputFile = "luckygraph.txt";
		try {
			File file = new File(sysInputFile);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			String info;
			while((info = in.readLine()) != null){
				break;
			}
			int edge = Integer.parseInt(info.split(" ")[1]);
			int vertex = Integer.parseInt(info.split(" ")[0]);
			int[][] graph = new int[edge][2];
			int i = 0;
			while ((line = in.readLine()) != null && i < edge) {
				line = line.trim();
				if(!line.equals("")){
					graph[i][0] = Integer.parseInt(line.split(" ")[0].trim());
					graph[i][1] = Integer.parseInt(line.split(" ")[1].trim());
					i++;
				}
			}
			int[] degreeOfVertex = new int[vertex];
//			int[][] linkOfVertex = new int[vertex][];
			List<Integer>[] linkOfVertex = new List [vertex];
			for(int j = 0; j < edge; j++){
				degreeOfVertex[graph[j][0] - 1]++;
				degreeOfVertex[graph[j][1] - 1]++;
				if(linkOfVertex[graph[j][0] - 1] == null){
					linkOfVertex[graph[j][0] - 1] = new ArrayList<Integer>();
					linkOfVertex[graph[j][0] - 1].add(graph[j][1]);
				}else{
					linkOfVertex[graph[j][0] - 1].add(graph[j][1]);
				}
				if(linkOfVertex[graph[j][1] - 1] == null){
					linkOfVertex[graph[j][1] - 1] = new ArrayList<Integer>();
					linkOfVertex[graph[j][1] - 1].add(graph[j][0]);
				}else{
					linkOfVertex[graph[j][1] - 1].add(graph[j][0]);
				}
			}
			in.close();
			boolean result = checkCycle(vertex, edge, degreeOfVertex, linkOfVertex);
			if(result == true){
				System.out.println("YES");
			}else{
				System.out.println("NO");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkCycle(int vertex, int edge, int[] degreeOfVertex, List<Integer>[] linkOfVertex){
		List<Integer> queue = new ArrayList<Integer>();
		for(int i = 0; i < degreeOfVertex.length; i++){
			if(degreeOfVertex[i] < 2){
				queue.add(i);
			}
		}
		while(!queue.isEmpty()){
			if(edge >= vertex){
				return false;
			}else{
				int i = queue.remove(0);
				if(degreeOfVertex[i] == 0){
					vertex--;
				}else{
					vertex--;
					edge--;
					//vertex j link with vertex i
					int j = linkOfVertex[i].get(0) - 1;
					degreeOfVertex[j]--;
					linkOfVertex[i].remove(0);
					linkOfVertex[j].remove(new Integer(i + 1));
					if(degreeOfVertex[j] ==1){
						queue.add(j);
					}
				}
			}
		}
		if(vertex != 0){
			return false;
		}else{
			return true;
		}
	}
}
