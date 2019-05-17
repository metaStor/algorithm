package algorithm;

/*
 * 翻转一个链表
 * 对于链表 1->2->3, 翻转链表为 3->2->1
 */
public class 翻转链表 {
	
	public static class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) {
			this.val = x;
			this.next = null;
		}
	}
	
	// 头插法
    public static ListNode reverse(ListNode head) {
        // write your code here
    	// 保证至少含有两个数据节点
    	if (head == null) {
			return null;
		}
    	if (head != null && head.next == null) {
			return head;
		}
    	// L为reverse后的链表，p为当前节点，q保存p的下一个节点
    	ListNode L = null, p = head, q = null; 
     	while (p != null) {
     		q = p.next; // q保存p的下一个节点
 			p.next = L; // 先将p当前的next节点指向L的next节点
 			L = p; // p节点接到L节点后
 			p = q;
 		}
     	return L;
//    	return recursion(head);
    }
    
    /*
     * 1->2->3->null => null<-1<-2<-3
     */
    public static ListNode recursion(ListNode head) {
    	if (head == null || head.next == null) {
			return head;
		}
    	ListNode p = recursion(head.next); // 倒数第二个节点
    	head.next.next = head; // 将最后一个节点指向倒数第二个节点
    	head.next = null; // 将倒数第二个节点后面剪断，防止形成环链
    	return p;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		ListNode res = reverse(l1);
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}

}
