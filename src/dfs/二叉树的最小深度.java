package dfs;

/*
 * 给定一个二叉树，找出其最小深度。
 * 二叉树的最小深度为根节点到最近叶子节点的距离。
 * 	Input:  {1,2,3,#,#4,5}
	Output: 2
	
	Explanation: 
      1
     / \ 
    2   3
       / \
      4   5 
 */
public class 二叉树的最小深度 {

	public static class TreeNode {
		public int val;
		public TreeNode left, right;

		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	
	// 分5种情况
    public static int minDepth(TreeNode root) {
        // write your code here
    	if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		if (root.left != null && root.right == null) {
			return 1 + minDepth(root.left);
		}
		if (root.right != null && root.left == null) {
			return 1 + minDepth(root.right);
		}
		// 都存在比较左右子数的最小深度
		return Math.min(minDepth(root.left) + 1, minDepth(root.right) + 1);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2); 
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		System.out.println(minDepth(root));
		/*
		char[] input = new char[] {'1', '2', '3', '#', '#', '4', '5'};
		TreeNode root = new TreeNode(input[0] - '0');
		for (int i = 1; i < input.length; i++) {
			int val = input[i] - '0';
			if (root.left == null) {
				root.left = new TreeNode(val);	
			}
			else if (root.right == null) {
				root.right = new TreeNode(val);
			} else {
			}
		}
		*/
	}

}
