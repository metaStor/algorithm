package tree;

import java.util.Scanner;

/* （线段树）
 * 给你一个长度为n的序列，初始为1,2,3...n，对其进行m次操作。
 * 操作有两种：
 * 1 l r  表示将区间[l,r]用 [1,2...r-l+1] 覆盖
 * 2 l r 查询[l,r]的区间和
 * 输入描述: 第一行包含2个数字，n,m（1 <= n,m <= 1e5） 接下来包含m行，格式如题面所示
 * 输出描述: 对于每个操作2，输出一行一个整数表示答案
 * 输入
 * 10 5
 * 2 1 10
 * 1 3 6
 * 2 1 10
 * 1 1 10
 * 2 1 10
 * 输出 55 47 55
 */
public class 线段数之序列操作 {
    
	static final int MAX = 100005;
    static int n, m;
    static Tree[] arr = new Tree[MAX << 2]; // fourfold space(子叶子节点也占空间)
    
    public static class Tree {
    	long l, r, lazyl, lazyr, sum;
		public Tree(long l, long r, long lazyl, long lazyr, long sum) {
			this.l = l; 
			this.r = r;
			this.lazyl = lazyl; 
			this.lazyr = lazyr;
			this.sum = sum;
		}
    }
    
    public static void init() {
    	for (int i = 1; i <= (n << 2); i++) arr[i] = new Tree(0, 0, 0, 0, 0); 
    }
    
    // 合并，当前的节点sum等于左右节点的sum
    public static void merge(int k) {
    	arr[k].sum = arr[k << 1].sum + arr[(k << 1) | 1].sum;
    }
    
    // 建树
    public static void build(long l, long r, int k) {
    	arr[k].l = l;
    	arr[k].r = r;
    	arr[k].lazyl = 0;
    	arr[k].lazyr = 0;
    	if (l == r) {
    		arr[k].sum = l;
    		return;
    	}
    	long mid = (l + r) >> 1;
    	build(l, mid, k << 1); // 处理左子树(2*k)
    	build(mid + 1, r, (k << 1) | 1); // 处理右子树(2*k+1)
    	merge(k); // 合并
    }
    
    // 区间[ql, qr]查询
    public static long query(int k, long ql, long qr) {
    	// 当前区间是要查询区间的子集，则全部加上
    	if (ql <= arr[k].l && arr[k].r <= qr) return arr[k].sum;
    	// 下传标记
    	if (arr[k].lazyr != 0 && arr[k].lazyl != 0) pushDown(k);
    	long sum = 0;
    	long mid = (arr[k].l + arr[k].r) >> 1;
    	// 查询的区间有一部分或都在当前区间的左边
    	if (ql <= mid) sum += query(k << 1, ql, qr);
    	// 查询的区间有一部分或都在当前区间的右边
    	if (qr > mid) sum += query((k << 1) | 1, ql, qr);
    	return sum;
    }
    
    // 计算懒标记
    public static long calc(int k) {
    	long len = (arr[k].lazyr - arr[k].lazyl + 1); // lazy的个数
    	return len * (arr[k].lazyr + arr[k].lazyl) / 2; // 等差数列求和
    }
    
    // 传递懒标记
    public static void pushDown(int k) {
    	arr[k << 1].lazyl = arr[k].lazyl;
    	arr[k << 1].lazyr = (arr[k].lazyl + arr[k].lazyr) >> 1;
    	arr[k << 1].sum = calc(k << 1);
    	arr[(k << 1) | 1].lazyl = ((arr[k].lazyl + arr[k].lazyr ) >> 1) + 1;
    	arr[(k << 1) | 1].lazyr = arr[k].lazyr;
    	arr[(k << 1) | 1].sum = calc((k << 1) | 1);
    	// 归零
    	arr[k].lazyl = 0;
    	arr[k].lazyr = 0;
    }
    
    // 区间修改
    public static void update(int k, long ul, long ur) {
    	// 递归到需要修改的节点，开始记录 lazy label
    	if (ul <= arr[k].l && arr[k].r <= ur) {
    		// 自增
    		arr[k].lazyl = arr[k].l - ul + 1;
    		arr[k].lazyr = arr[k].r - ul + 1;
    		arr[k].sum = calc(k); // 计算懒标记和
    		return;
    	}
    	// 下传标记
    	if (arr[k].lazyr != 0 && arr[k].lazyl != 0) pushDown(k);
    	long mid = (arr[k].l + arr[k].r) >> 1;
    	if (ul <= mid) update(k << 1, ul, ur);
    	if (ur > mid) update((k << 1) | 1, ul, ur);
    	merge(k); // 合并
    }
    
    // 前缀和 (TLE), 用线段树
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        m = input.nextInt();
        init();
        build(1, n, 1);
        while (m-- > 0) {
            int op = input.nextInt();
            int l = input.nextInt();
            int r = input.nextInt();
            if (op == 1) update(1, l, r);       	
            else if (op == 2) System.out.println(query(1, l, r));
        }
        input.close();
    }
}