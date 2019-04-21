package bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
 * 给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 (给出每条边的两个顶点), 
 * 写一个函数去判断这张｀无向｀图是否是一棵树
 * 你可以假设我们不会给出重复的边在边的列表当中. 无向边　[0, 1] 和 [1, 0]　是同一条边， 
 * 因此他们不会同时出现在我们给你的边的列表当中
 */
public class 图是否是树 {

	// bfs is more efficient than dfs. 
    public static boolean validTree(int n, int[][] edges) {
        // write your code here
        if (n - 1 != edges.length) return false;
        if ((edges == null || edges.length == 0) && n == 1) return true;
        if ((edges == null || edges.length == 0) && n >= 2) return false;
        Map<Integer, HashSet<Integer>> neighbor = initGraph(n, edges);
        // bfs
        Queue<Integer> queue = new LinkedList<>();
        boolean[] vis = new boolean[n];
        queue.offer(0); // start with 0, 如果从0开始没有，就肯定不是树
        vis[0] = true;
        while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (!neighbor.containsKey(cur)) continue;
			for (int child : neighbor.get(cur)) {
				// 由于邻接表的特性，需要去重
				if (vis[child]) continue;
				queue.offer(child);
				vis[child] = true;
			}
		}
        for (boolean b : vis) if (!b) return false;
        return true;
    }
    
    // 构造邻接矩阵
    public static Map<Integer, HashSet<Integer>> initGraph(int n, int[][] edges) {
    	Map<Integer, HashSet<Integer>> neighbor = new HashMap<>();
        for (int i = 0; i < n; ++i) {
        	neighbor.put(i, new HashSet<>());
        }
        for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			neighbor.get(u).add(v);
			neighbor.get(v).add(u);
		}
        return neighbor;
    }

    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	System.out.println(validTree(5, new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
	}

}
