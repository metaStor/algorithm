package union_find_sets;

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
        int[] pre = new int[n];
        for (int i = 1; i < n; ++i) {
            pre[i] = i;
        }
        for (int i = 0; i < edges.length; ++i) {
            if (!union(pre, edges[i][0], edges[i][1])) {
                return false;
            }
        } 
        return true;
    }
    
    public static int find(int[] pre, int x) {
        if (pre[x] == x) return x;
        return pre[x] = find(pre, pre[x]);
    }

    private static boolean union(int[] pre, int i, int j) {
        int a = find(pre, i);
        int b = find(pre, j);
        if (a != b) {
            pre[a] = b;
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	System.out.println(validTree(5, new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
	}

}
