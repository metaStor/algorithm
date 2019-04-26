package union_find_sets;

import java.util.Arrays;

/*
 * 在这个问题中，树是一个连接的、无环的无向图。
 * 给定的输入是一个图形，它以具有N个节点（具有不同的值1,2，...，N）的树开始，并添加了一条额外的边。 添加的边有两个不同的顶点（从1到N中选择），并且不是已经存在的边。
 * 得到的图形以关于边的 2D-数组的形式给出。 边的每个元素是一对[u，v]，其中u <v，这表示连接节点u和v的无向边。
 * 你需要输出可以删除的边，以便生成的图是一棵N个节点的树。 如果有多个答案，则返回给定2D数组中最后出现的答案边。 答案边[u，v]应该采用相同的格式，u <v。
 * 输入2D数组的大小将介于3和1000之间。
 * 2D数组中的每个整数都在1和N之间，其中N是输入数组的大小。
 */
public class 冗余的连接 {

    public static int[] findRedundantConnection(int[][] edges) {
        // write your code here
    	if (edges == null || edges.length == 0) return new int[] {};
    	int n = edges.length; // n个nodes
    	int[] pre = new int[n + 1];
    	int[] res = new int[2];
    	for (int i = 1; i <= n; i++) pre[i] = i;
    	for (int i = 0; i < n ; i++) {
			if (!union(pre, edges[i][0], edges[i][1])) {
				res[0] = edges[i][0];
				res[1] = edges[i][1];
			}
		}
    	return res;
    }
    
    public static int find(int[] pre, int x) {
    	if (x == pre[x]) return x;
    	return pre[x] = find(pre, pre[x]); 
    }
    
    public static boolean union(int[] pre, int x, int y) {
    	int a = find(pre, x);
    	int b = find(pre, y);
    	if (a == b) return false;
    	pre[a] = b;
    	return true;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] edges = new int[][] { {1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5} };
		System.out.println(Arrays.toString(findRedundantConnection(edges)));
	}

}
