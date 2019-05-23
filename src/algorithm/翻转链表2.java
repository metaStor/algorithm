package algorithm;

/*
 * 翻转链表中第m个节点到第n个节点的部分,m，n满足1 ≤ m ≤ n ≤ 链表长度
 * 输入: 1->2->3->4->5->NULL, m = 2 and n = 4, 
 * 输出: 1->4->3->2->5->NULL.
 */
public class 翻转链表2 {

	public static class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) {
			this.val = x;
			this.next = null;
		}
	}
	
	// 头插法
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        // write your code here
    	if (head == null) {
			return null;
		}
    	if (m == n) {
			return head;
		}
    	// 处理m为1的情况
    	ListNode L = new ListNode(0);
    	L.next = head;
    	head = L;
    	// 找到m位置
    	for (int i = 1; i < m; ++i) {
			head = head.next;
		}
    	/* 把head拆成两部分：
    	 * pre位于翻转部分的前面，end位于翻转部分的后面
    	 */
    	ListNode pre = head, end = head.next;
    	// p为翻转部分，q为翻转部分后面的节点, t为临时变量
    	ListNode p = head.next, q = p.next, t = null;
    	for (int i = m; i < n; i++) {
    		// 头插法
    		t = q.next; // q保存p的下一个节点
    		q.next = p; // 先将q当前的next节点指向p的next节点
    		p = q; // q节点接到p节点后
    		q = t; // 往下迭代
		}
    	// 拼接
    	pre.next = p;
    	end.next = q;
    	return L.next;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		ListNode res = reverseBetween(l1, 2, 4);
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}

}
