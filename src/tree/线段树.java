package tree;

import java.util.Scanner;

public class 线段树 {

	static int n;
	static Tree[] tree;
	
	public static class Tree {
		int l, r;
		int lazy;
		int sum;
		public Tree(int l, int r, int lazy, int sum) {
			super();
			this.l = l;
			this.r = r;
			this.lazy = lazy;
			this.sum = sum;
		}
		@Override
		public String toString() {
			return "Tree [l: " + l + ", r: " + r + ", lazy: "
					+ lazy + ", sum: " + sum + "]";
		}
	}
	
	public static void init() {
		// fourfold space(子叶子节点也占空间)
		tree = new Tree[(n << 2) + 10]; 
		for (int i = 1; i <= (n << 2); ++i) { 
			tree[i] = new Tree(0, 0, 0, 0);
		}
	}
	
	// 查看所有节点
	public static void show() {
		for (int i = 1; i < (n << 1); i++) { 
			System.out.println(i + ": " + tree[i].toString());
		}
	}
	
	public static int left(int k) {
		return k << 1;
	}
	
	public static int right(int k) {
		return (k << 1) | 1;
	}
	
    // 合并，当前的节点sum等于左右节点的sum之和
	public static void merge(int k) {
		tree[k].sum = tree[left(k)].sum + tree[right(k)].sum;
	}
	
	// lazy下传
	public static void pushDown(int k) {
		// 当前的lazy值累积到子节点中
		tree[left(k)].lazy += tree[k].lazy;
		tree[right(k)].lazy += tree[k].lazy;
		// 修改子节点的状态值,区间个数*传下来的lazy
		tree[left(k)].sum += (tree[left(k)].r - tree[left(k)].l + 1) * tree[k].lazy;
		tree[right(k)].sum += (tree[right(k)].r - tree[right(k)].l + 1) * tree[k].lazy;
		// 当前节点lazy清零
		tree[k].lazy = 0;
	}
	
	public static void build(int k, int ll, int rr) {
		tree[k].l = ll;
		tree[k].r = rr;
		tree[k].lazy = 0;
		if (ll == rr) {
			tree[k].sum = ll;
			return;
		}
		int mid = (ll + rr) >> 1;
		build(left(k), ll, mid); // 处理左子树(2*k)
		build(right(k), mid + 1, rr); // 处理右子树(2*k+1)
		merge(k); // 合并
	}
	
	// 单点查询(二分)
	public static int query(int k) {
		if (tree[k].l == tree[k].r) return tree[k].sum;
		if (tree[k].lazy != 0) pushDown(k); // 传lazy
		int mid = (tree[k].l + tree[k].r) >> 1;
		if (k <= mid) return query(left(k)); // 处理左子树(2*k)
		return query(right(k)); // 处理右子树(2*k+1)
	}
	
	// 单点修改(建树思路)
	public static void update(int k, int value) {
		if (tree[k].l == tree[k].r) {
			tree[k].sum += value;
			return;
		}
		int mid = (tree[k].l + tree[k].r) >> 1;
		if (k <= mid) update(left(k), value);
		else update(right(k), value);
		merge(k); // update sum of node
	}
	
	// 区间查询
	public static int queryInterval(int k, int ql, int qr) {
		int l = tree[k].l, r = tree[k].r;
		if (ql <= l && r <= qr) return tree[k].sum;
		if (tree[k].lazy != 0) pushDown(k); // 传lazy
		int sum = 0;
		int mid = (l + r) >> 1;
		if (ql <= mid) sum += queryInterval(left(k), ql, qr);
		if (qr > mid) sum += queryInterval(right(k), ql, qr);
		return sum;
	}
	
	// 区间修改，每个数都加减value
	public static void updateInterval(int k, int ul, int ur, int value) {
		int l = tree[k].l, r = tree[k].r;
		if (ul <= l && r <= ur) {
			tree[k].sum += (r - l + 1) * value; // 区间个数*value
			tree[k].lazy += value; // update lazy
			return;
		}
		// 当不再目标范围的时候下传lazy的值
		if (tree[k].lazy != 0) pushDown(k);
		int mid = (l + r) >> 1;
		if (k <= mid) updateInterval(left(k), ul, ur, value);
		if (k > mid) updateInterval(right(k), ul, ur, value);
		merge(k); // update sum of node
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		init();
		build(1, 1, n);
		show();
		update(2, -1);
		System.out.println(query(2));
		System.out.println(queryInterval(1, 1, 3));
		updateInterval(1, 1, 3, 2);
		System.out.println(queryInterval(1, 1, 3));
		show();
		input.close();
	}

}
