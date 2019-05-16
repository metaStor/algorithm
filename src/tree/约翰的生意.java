package tree;

import java.util.Arrays;

/* TLE
 * 在一条数轴上，有n个城市，编号从0 ~ n – 1 , 约翰打算在这n个城市做点生意，他对Armani的一批货物感兴趣，
 * 每个城市对于这批货物都有一个价格prices[i]。对于城市x,约翰可从城市编号为[x - k, x + k]购买货物，
 * 然后卖到城市x,问约翰在每个城市最多能赚到多少钱？ prices.length 范围为[2, 100000], k <= 100000。
 * 输入: prices = [1, 3, 2, 1, 5] 和 k = 2
 * 输出: [0, 2, 1, 0, 4]
 * 解释: 
 * i = 0，约翰可去的城市有0~2因为1、2号城市的价格比0号城市的价格高，所以赚不了钱，即 ans[0] = 0。
 * i = 1，可去的城市有0~3，可以从0号或者3号城市购买货物赚取的差价最大，即ans[1] = 2。
 * i = 2，可去的城市有0~4，显然从3号城市购买货物赚取的差价最大，即ans[2] = 1。
 * i = 3，可去的城市有1~4，没有其他城市的价格比3号城市价格低，所以赚不了钱，ans[3] = 0。
 * i = 4，可去的城市有2~4，从3号城市购买货物赚取的差价最大，即ans[4] = 4。
 */
public class 约翰的生意 {
	
	static final int MAX = Integer.MAX_VALUE;

    public static class Tree {
        int start, end, min;
        Tree left, right;
        public Tree(int start, int end) {
            this.start = start;
            this.end = end;
            this.min = MAX;
            this.left = null;
            this.right = null;
        }
    }

    public static void merge(Tree root) {
        root.min = Math.min(root.left.min, root.right.min);
    }

    public static Tree build(int[] A, int start, int end) {
    	if (start > end) return null;
        Tree node = new Tree(start, end);
        if (start == end) {
        	node.min = A[start];
            return node;
        }
        int mid = (node.start + node.end) >> 1;
        node.left = build(A, start, mid);
        node.right = build(A, mid + 1, end);
        merge(node);
        return node;
    }

    public static int query(Tree root, int start, int end) {
        if (root == null || start > end || start > root.end || end < root.start) {
        	return MAX;
        }
        if (start <= root.start && root.end <= end) {
            return root.min;
        }
        int mid = (root.start + root.end) >> 1;
        int min = MAX;
        if (start <= mid) min = Math.min(min, query(root.left, start, end));
        if (end > mid) min = Math.min(min, query(root.right, start, end));
        return min;
    }

    public static int[] business(int[] A, int k) {
        // write your code here
        int len = A.length, p = 0;
        int[] ant = new int[len];
        if (len == 0) {
            return ant;
        }
        Tree root = build(A, 0, len - 1);
        for (int i = 0; i < len; i++) {
        	int start = (i - k) >= 0 ? (i - k) : 0;
        	int end = (i + k) < len ? (i + k) : len - 1;
            int min = query(root, start, end);
            System.out.println(start + " " + end + " " + min);
            ant[p++] = (min >= A[i]) ? 0 : A[i] - min; 
        }
        return ant;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = { 1, 3, 2, 1, 5 };
        System.out.println(Arrays.toString(business(arr, 2)));
    }

}
