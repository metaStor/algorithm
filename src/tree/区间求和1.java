package tree;

import java.util.ArrayList;
import java.util.List;

/*
 * 给定一个整数数组（下标由 0 到 n-1，其中 n 表示数组的规模），以及一个查询列表。每一个查询列表有两个整数 [start, end] 。 
 * 对于每个查询，计算出数组中从下标 start 到 end 之间的数的总和，并返回在结果列表中。
 */
public class 区间求和1 {

    static Tree[] trees;

    public static class Tree {
        long l, r, sum;
        public Tree(long l, long r, long sum) {
            this.l = l;
            this.r = r;
            this.sum = sum;
        }
    }

    public static class Interval {
        int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void init(int n) {
        trees = new Tree[(n << 2) + 1];
        for (int i = 1; i <= (n << 2); i++) {
            trees[i] = new Tree(0, 0, 0);
        }
    }

    public static int left(long k) {
        return (int) (k << 1);
    }

    public static int right(long k) {
        return (int) ((k << 1) | 1);
    }

    public static void merge(int k) {
        trees[k].sum = trees[left(k)].sum + trees[right(k)].sum;
    }

    public static void build(int k, long start, long end, int[] A) {
        trees[k].l = start;
        trees[k].r = end;
        if (start == end) {
            trees[k].sum = A[(int) start];
            return;
        }
        long mid = (start + end) >> 1;
        build(left(k), start, mid, A);
        build(right(k), mid + 1, end, A);
        merge(k);
    }

    public static long query(int k,  int start, int end) {
        long l = trees[k].l, r = trees[k].r;
        if (start <= l && r <= end) {
            return trees[k].sum;
        }
        int mid = (int) ((l + r) >> 1);
        long sum = 0;
        if (start <= mid) sum += query(left(k), start, end);
        if (end > mid) sum += query(right(k), start, end);
        return sum;
    }

    public static List<Long> intervalSum(int[] A, List<Interval> queries) {
        // write your code here
        int n = A.length;
        List<Long> ant = new ArrayList<>();
        if (A == null || n == 0 || queries == null || queries.isEmpty()) {
            return ant;
        }
        init(n);
        build(1, 0, n - 1, A);
        for (Interval interval : queries) {
            ant.add(query(1, interval.start, interval.end));
        }
        return ant;
    }


    public static void main(String[] args) {
        int[] arr = { 2, 3, 1, 4, 5 };
        List<Interval> queries = new ArrayList<>();
        queries.add(new Interval(0, 4));
        queries.add(new Interval(0, 0));
        queries.add(new Interval(3, 4));
        queries.add(new Interval(0, 3));
        System.out.println(intervalSum(arr, queries));
    }
}
