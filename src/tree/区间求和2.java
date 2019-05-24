package tree;

public class 区间求和2 {
	
	static class Tree {
		long l, r, sum;
		Tree left, right;
		public Tree(long l, long r) {
			this.l = l;
			this.r = r;
			this.sum = 0;
			this.left = this.right = null;
		}
	}
	
	public static int median(long l, long r) {
		return (int) ((l + r) >> 1);
	}
	
	public static void merger(Tree node) {
		node.sum = node.left.sum + node.right.sum;
	}
	
	public static Tree build(int[] A, int l, int r) {
		Tree node = new Tree(l, r);
		if (l == r) {
			node.sum = A[l];
			return node;
		}
		int mid = median(l, r);
		node.left = build(A, l, mid);
		node.right = build(A, mid + 1, r);
		merger(node);
		return node;
	}
	
    public static void Solution(int[] A) {
        // do intialization if necessary
    	if (A == null || A.length == 0) return;
    	Tree root = build(A, 0, A.length - 1);
    	modify(root, 2, 1);
    	System.out.println(query(root, 2, 4));
    }

    /*
     * @param start: An integer
     * @param end: An integer
     * @return: The sum from start to end
     */
    public static long query(Tree root, int start, int end) {
        // write your code here
        if (root == null || start > end || start > root.r || end < root.l) {
        	return 0;
        }
    	if (start <= root.l && root.r <= end) {
    		return root.sum;
    	}
    	int mid = median(root.l, root.r);
    	long ant = 0;
    	if (start <= mid) ant += query(root.left, start, end); 
    	if (end > mid) ant += query(root.right, start, end);
    	return ant;
    }

    /*
     * @param index: An integer
     * @param value: An integer
     * @return: nothing
     */
    public static void modify(Tree root, int index, int value) {
        // write your code here
    	if (root.l == root.r && root.l == index) {
    		root.sum = value;
    		return;
    	}
    	int mid = median(root.l, root.r);
    	if (index <= mid) modify(root.left, index, value);
    	else modify(root.right, index, value);
    	merger(root);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution(new int[] { 1, 2, 7, 8, 5 });
	}

}
