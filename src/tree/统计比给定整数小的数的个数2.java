package tree;

import java.util.ArrayList;
import java.util.List;

/*
 * 给定一个整数数组（下标由 0 到 n-1， n 表示数组的规模，取值范围由 0 到10000）。
 * 对于数组中的每个 ai 元素，请计算 ai 前的数中比它小的元素的数量。
 * 输入: array =[1,2,7,8,5] 输出:[0,1,2,3,2]
 */
public class 统计比给定整数小的数的个数2 {

	static final int MAX = 10002;
    static Tree[] tree = new Tree[MAX << 2];

	// 区间为0~最大值(源数组)，完成计数+搜索
    public static class Tree {
        int l, r, count;
        public Tree(int l, int r) {
            this.l = l;
            this.r = r;
            this.count = 0;
        }
    }

    public static void init(int n) {
        for (int i = 0; i < (n << 2); ++i) {
            tree[i] = new Tree(0, 0);
        }
    }

    public static int left(int k) {
        return k << 1;
    }

    public static int right(int k) {
        return (k << 1) | 1;
    }

    public static void build(int k, int start, int end) {
        tree[k].l = start;
        tree[k].r = end;
        if (start == end) return;
        int mid = (tree[k].l + tree[k].r) >> 1;
        build(left(k), start, mid);
        build(right(k), mid + 1, end);
    }

    // 单点插入源数组并更新count
    public static void update(int k, int value) {
        if (tree[k].l == tree[k].r && tree[k].l == value) {
            tree[k].count++;
            return;
        }
        int mid = (tree[k].l + tree[k].r) >> 1;
        if (value <= mid) update(left(k), value);
        else update(right(k), value);
        // merger
        tree[k].count = tree[left(k)].count + tree[right(k)].count;
    }

    public static int query(int k, int start, int end) {
        if (start > end) return 0;
        if (start == tree[k].l && tree[k].r == end) {
            return tree[k].count;
        }
        int mid = (tree[k].l + tree[k].r) >> 1;
        int res = 0;
        if (start <= mid) res += query(left(k), start, Math.min(mid, end)); 
        if (end > mid) res += query(right(k), Math.max(mid + 1, start), end);
        return res;
    }

    public static List<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
        List<Integer> ant = new ArrayList<Integer>();
        if (A == null || A.length == 0) return ant;
        // get maximum n
        int n = 0;
        for (int i : A) n = Math.max(i, n);
        init(n);
        build(1, 0, n);
        for (int val : A) { 
            // 小于val的个数: 0 ~ val-1区间内的个数
        	ant.add(query(1, 0, val - 1));
        	// 值插入，完成计数功能
            update(1, val);
        }
        return ant;
    }
    
    public static void main(String[] args) {
    	System.out.println(countOfSmallerNumberII(new int[] { 1, 2, 7, 8, 5 }).toString());
    }

}
