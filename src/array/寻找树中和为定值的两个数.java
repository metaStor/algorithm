package array;

import java.util.Vector;

// 注意题目的意思是要从头结点到叶子的路径
public class 寻找树中和为定值的两个数 {

	// 实现树节点
	static class Node {
		int data;
		Node left;
		Node right;

		public Node() {
		}

		public Node(int data) {
			this.data = data;
		}

		public void display() {
			System.out.println(this.data);
		}
	}

	// 实现二叉树
	static class Tree {
		// 根节点
		Node root;

		public Tree() {
		}

		// insert
		public boolean insert(int data) {
			Node node = new Node(data);
			// 空树, 直接作为根节点
			if (this.root == null) {
				this.root = node;
				return true;
			} else {
				Node current = this.root;
				Node parentNode = null;
				// 往最深处找
				while (current != null) {
					parentNode = current;
					// 先将新节点与根节点比较, 较小则放左子树,较大放右子树
					if (current.data >= data) {
						current = current.left;
						// 插入空左子树
						if (current == null) {
							parentNode.left = node;
							return true;
						}
					} else {
						current = current.right;
						// 插入空右子树
						if (current == null) {
							parentNode.right = node;
							return true;
						}
					}
				}
			}
			return false;
		}

		// 先序遍历
		public void prePrint(Node node) {
			if (node != null) {
				node.display();
				prePrint(node.left);
				prePrint(node.right);
			}
		}

		// 中序遍历
		public void midPrint(Node node) {
			if (node != null) {
				midPrint(node.left);
				node.display();
				midPrint(node.right);
			}
		}

		// 后序遍历
		public void lastPrint(Node node) {
			if (node != null) {
				lastPrint(node.left);
				lastPrint(node.right);
				node.display();
			}
		}
	}

	// 保存路径的vector
	static Vector<Integer> path = new Vector<>();

	// 当前节点是否是子叶节点
	public static boolean isLeaf(Node node) {
		return (node.left == null) || (node.right == null);
	}

	// 打印路径
	public static void printPath() {
		for (Integer integer : path) {
			System.out.print(integer + " ");
		}
		System.out.println();
	}

	public static void find(Node node, int cur_num, int number) {
		if (node == null) {
			return;
		}
		int data = node.data;
		path.add(data);
		cur_num += data;
		// check
		if (cur_num == number && isLeaf(node)) {
			printPath();
			// 注意, 这里打印后还需要回溯.
			// ...
		}
		// 左递归查找
		if (node.left != null) {
			find(node.left, cur_num, number);
		}
		// 右递归查找
		if (node.right != null) {
			find(node.right, cur_num, number);
		}
		// 回溯
		path.remove(path.size() - 1);  // 删除刚添加的元素
		cur_num -= data;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree tree = new Tree();
		tree.insert(10);
		tree.insert(5);
		tree.insert(12);
		tree.insert(4);
		tree.insert(7);
		tree.prePrint(tree.root);
		find(tree.root, 0, 22);
	}

}
