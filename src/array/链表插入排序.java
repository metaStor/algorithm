package array;

/* 用插入排序对链表排序
 * 输入:  1->3->2->0->null
 * 输出 :0->1->2->3->null
 */
public class 链表插入排序 {

	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public static ListNode insertionSortList(ListNode head) {
		// write your code here
		if (head == null) {
			return null;
		}
		// 声明一个空节点,排除其他条件
		ListNode res = new ListNode(0);
		ListNode pos, q;
		while (head != null) {
			pos = res; // 定位要插入的位置 
			q = head.next; // 保存下一个节点
			// 寻找插入位置
			while (pos.next != null && pos.next.val < head.val) {
				pos = pos.next;
			}
			head.next = pos.next; // 将要插入的节点next指针指向res的next位置
			pos.next = head; // 将res的next指针指向插入节点
			head = q; // 继续往下遍历
		}
		return res.next;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode node = new ListNode(1);
		node.next = new ListNode(3);
		node.next.next = new ListNode(2);
		node.next.next.next = new ListNode(0);
		ListNode res = insertionSortList(node);
		while (res != null) {
			System.out.print(res.val + "->");
			res = res.next;
		}
	}

}
