package tree;

/*
 * 给一棵二叉树和二叉树中的两个节点，找到这两个节点的最近公共祖先LCA。
 * 两个节点的最近公共祖先，是指两个节点的所有父亲节点中（包括这两个节点），离这两个节点最近的公共的节点。
 * 每个节点除了左右儿子指针以外，还包含一个父亲指针parent，指向自己的父亲。
 * */
public class 最近公共祖先 {

	static class TreeNode {
		public int value;
		public TreeNode left, right;

		public TreeNode() {
			// TODO Auto-generated constructor stub
		}

		public TreeNode(int value) {
			// TODO Auto-generated constructor stub
			this.value = value;
			this.left = this.right = null;
		}

		public void visit() {
			System.out.print(this.value + " ");
		}

	}

	// 先序遍历
	public static void prePrint(TreeNode node) {
		if (node != null) {
			node.visit();
			prePrint(node.left);
			prePrint(node.right);
		}
	}

	// 中序遍历
	public static void midPrint(TreeNode node) {
		if (node != null) {
			midPrint(node.left);
			node.visit();
			midPrint(node.right);
		}
	}

	// 后序遍历
	public static void afterPrint(TreeNode node) {
		if (node != null) {
			afterPrint(node.left);
			afterPrint(node.right);
			node.visit();
		}
	}

	/*
	 * 当是一般的二叉树时候(非二叉查找树) 当遍历到一个root点的时候，
	 * 1.判断root是不是null如果root为null，那么就无所谓祖先节点，直接返回null就好了
	 * 2.如果root的左子树存在p，右子树存在q，那么root肯定就是最近祖先 3.如果pq都在root的左子树，那么就需要递归root的左子树，右子树同理
	 */
	public static TreeNode searchLCA1(TreeNode root, TreeNode lNode, TreeNode rNode) {
		if (root == null || root == lNode || root == rNode) {
			return root;
		}
		TreeNode left = searchLCA1(root.left, lNode, rNode);
		TreeNode right = searchLCA1(root.right, lNode, rNode);
		if (left != null && right != null) {
			return root;
		} else {
			return (left == null) ? right : left;
		}
	}

	/*
	 * 当是二叉查找树时 若当前节点都大于r, l. 则在右子树中查找 若当前节点都小于r, l. 则在左子数中查找 若当前节点大于l且小于r, 则返回当前节点
	 */
	public static TreeNode searchLCA2(TreeNode root, TreeNode lNode, TreeNode rNode) {
		// swap
		if (lNode.value > rNode.value) {
			TreeNode temp = lNode;
			lNode = rNode;
			rNode = temp;
		}
		if (root == null || root == lNode || root == rNode) {
			return root;
		}
		if (root.value < lNode.value && root.value < rNode.value) {
			return searchLCA2(root.left, lNode, rNode);
		} else if (root.value > lNode.value && root.value > rNode.value) {
			return searchLCA2(root.right, lNode, rNode);
		} else {
			return root;
		}
	}

	/*
	 * tarjan算法(dfs+并查集)
	 * 
	 */
	static int[] pre = new int[6666];
	static boolean[] vis = new boolean[6666];
	static int result = Integer.MIN_VALUE;
	
	private static void INITIALIZE() {
		// TODO Auto-generated method stub
		for (int i = 0; i < pre.length; i++) {
			pre[i] = i;
			vis[i] = false;
		}
	}

	public static void merge(int child, int parent) {
		int a = find(child);
		int b = find(parent);
		if (a != b) {
			pre[a] = b;
		}
	}

	// 找到某节点的root
	public static int find(int x) {
		int r = x;
		while (pre[r] != r) {
			r = pre[r];
		}
		// 路径压缩算法
		int i = x, j;
		while (pre[i] != r) {
			j = pre[i];
			pre[i] = r; 
			i = j;
		}
		return r;
	}

	public static void tarjan(TreeNode tree, TreeNode parent, TreeNode uNode, TreeNode vNode) {
		if (tree.left != null) {
			tarjan(tree.left, tree, uNode, vNode);
		}
		if (tree.right != null) {
			tarjan(tree.right, tree, uNode, vNode);
		}
		// 访问没有访问过的节点
		int pos = tree.value;
		if (!vis[pos]) {
			/* 
			 * 先判断当前节点是否是要查询的节点
			 * 若当前节点是要查询的节点u, 先判断对应节点v是否访问过,访问过则输出v的root节点
			 * 若当前节点是要查询的节点v, 先判断对应节点u是否访问过,访问过则输出u的root节点
			 */
			if (pos == uNode.value && vis[vNode.value]) {
				result = find(vNode.value);
//				System.out.println(find(vNode.value));
			} else if (pos == vNode.value && vis[uNode.value]) {
				result = find(uNode.value);
//				System.out.println(find(uNode.value));
			}
			// 标记当前节点已经被访问过, 更新其父节点
			vis[pos] = true;
			// 合并
			merge(pos, parent.value);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 4 / \ 3 7 / \ 5 6
		 */
		TreeNode root = new TreeNode(4);
		TreeNode l1 = new TreeNode(3);
		TreeNode r1 = new TreeNode(7);
		TreeNode l2 = new TreeNode(5);
		TreeNode r2 = new TreeNode(6);
		root.left = l1;
		root.right = r1;
		r1.left = l2;
		r1.right = r2;
//		TreeNode rTreeNode = searchLCA1(root, l2, r2);
//		rTreeNode.visit();
		INITIALIZE();
		tarjan(root, root, l1, r2);
		System.out.println((result == Integer.MIN_VALUE) ? null : result);
	}

}
