package algorithm_practices;

/*
 * 你有两个用链表代表的整数，其中每个节点包含一个数字。数字存储按照在原来整数中相反的顺序，
 * 使得第一个数字位于链表的开头。写出一个函数将两个整数相加，用链表形式返回和。
 * 	输入: 7->1->6->null, 5->9->2->null
	输出: 2->1->9->null
	样例解释:
	617 + 295 = 912
	912 转换成链表:  2->1->9->null
 */
public class 链表求和 {
	
	public static class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) {
			this.val = x;
			this.next = null;
		}
	}
	
    public static ListNode addLists(ListNode l1, ListNode l2) {
        // write your code here
    	if (l1 == null && l2 == null) {
			return null;
		}
    	ListNode temp = new ListNode(0);
    	ListNode res = temp; // 用于记录链表，不然会丢失临时对象
    	int sum = 0;
    	while (l1 != null || l2 != null) {
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			if (sum >= 10) {
				temp.val = (sum - 10);
				sum = 1;
			} else {
				temp.val = sum;
				sum = 0;
			}
			if (l1 != null || l2 != null) {
				temp.next = new ListNode(0);
				temp = temp.next;
			}
		}
    	if (sum >= 1) {
			temp.next = new ListNode(1);
		}
    	return res;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(7), l2 = new ListNode(5);
		l1.next = new ListNode(1);
		l1.next.next = new ListNode(6);
		l2.next = new ListNode(9);
		l2.next.next = new ListNode(2);
		ListNode res = addLists(l1, l2);
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}

}
