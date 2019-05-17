package algorithm;

import java.util.Stack;

/*
 * 你有两个用链表代表的整数，其中每个节点包含一个数字。假设这两个数的数字顺序排列.
 * 写出一个函数将两个整数相加，用链表形式返回和。
 * 输入t:6->1->7   2->9->5
 * 输出 :9->1->2
 * */
public class 链表求和2 {

	public static class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) {
			this.val = x;
			this.next = null;
		}
	}
	
    public static ListNode addLists2(ListNode l1, ListNode l2) {
        // write your code here
    	if (l1 == null && l2 == null) {
			return null;
		}
    	Stack<Integer> ll1 = reverseList(l1);
    	Stack<Integer> ll2 = reverseList(l2);
    	Stack<Integer> res = new Stack<Integer>();
    	int sum = 0;
    	while (!ll1.isEmpty() || !ll2.isEmpty()) {
			if (!ll1.isEmpty()) sum += ll1.pop();
			if (!ll2.isEmpty()) sum += ll2.pop();
			if (sum >= 10) {
				res.push(sum - 10);
				sum = 1;
			}
			else {
				res.push(sum);
				sum = 0;
			}
		}
    	if (sum >= 1) res.push(1);
    	// establish ListNode
    	ListNode temp = new ListNode(0);
    	ListNode result = temp;
    	while (true) {
    		temp.val = res.pop();
    		if (res.isEmpty()) break;
    		temp.next = new ListNode(0);
    		temp = temp.next;
		}
    	return result;
    }
    
	public static Stack<Integer> reverseList(ListNode list) {
		Stack<Integer> res = new Stack<Integer>();
		while (list != null) {
			res.push(list.val);
			list = list.next;
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(6), l2 = new ListNode(3);
		l1.next = new ListNode(1);
		l1.next.next = new ListNode(7);
		l2.next = new ListNode(9);
		l2.next.next = new ListNode(5);
		ListNode res = addLists2(l1, l2);
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}

}
