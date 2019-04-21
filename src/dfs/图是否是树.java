package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * 给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 (给出每条边的两个顶点), 
 * 写一个函数去判断这张｀无向｀图是否是一棵树
 * 你可以假设我们不会给出重复的边在边的列表当中. 无向边　[0, 1] 和 [1, 0]　是同一条边， 
 * 因此他们不会同时出现在我们给你的边的列表当中
 */
public class 图是否是树 {

    public static boolean validTree(int n, int[][] edges) {
        // write your code here
        if (n - 1 != edges.length) return false;
        if ((edges == null || edges.length == 0) && n == 1) return true;
        if ((edges == null || edges.length == 0) && n >= 2) return false;
        // 构造邻接矩阵
        Map<Integer, ArrayList<Integer>> neighbor = new HashMap<>();
        for (int i = 0; i < edges.length; ++i) {
        	if (neighbor.containsKey(edges[i][0])) {
	        	neighbor.get(edges[i][0]).add(edges[i][1]);
			} else {
				ArrayList<Integer> t = new ArrayList<>();
				t.add(edges[i][1]);
	        	neighbor.put(edges[i][0], t);
			}
          	if (neighbor.containsKey(edges[i][1])) {
	        	neighbor.get(edges[i][1]).add(edges[i][0]);
			} else {
				ArrayList<Integer> t = new ArrayList<>();
				t.add(edges[i][0]);
	        	neighbor.put(edges[i][1], t);
			}
        }
        System.out.println(neighbor.toString());
        // 建立记忆矩阵
        boolean[] vis = new boolean[n];
        /* 
         * dfs访问邻接点，如果重复访问说明肯定环回
         * 从0开始，如果从0开始没有，就肯定不是树
         */
        if (dfs(neighbor, vis, -1, 0)) return false;
        // 剩下没有访问到的就是单独成一派的树(孤立点)
        for (boolean i : vis) if (!i) return false;			
        return true;
    }
    
    // true is cycle(not tree)
    public static boolean dfs(Map<Integer, ArrayList<Integer>> neighbor, boolean[] vis, int parent, int child) {
    	if (vis[child]) return true;
    	vis[child] = true;
    	if (!neighbor.containsKey(child)) return false;
    	for (int value : neighbor.get(child)) {
    		// 排除parent==value，这是邻接矩阵的特性
			if (parent != value && dfs(neighbor, vis, child, value)) return true;
		}
    	return false;
    }

    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	System.out.println(validTree(5, new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
	}

}
