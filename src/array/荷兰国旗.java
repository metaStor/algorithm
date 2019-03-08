package array;

import java.util.Arrays;

/*
 * 有红白蓝三种颜色的球共n个随机排列，而我们的任务就是将这n个球按红、白、蓝顺序排好。
 * */
public class 荷兰国旗 {

	static int[] flags = { 1, 2, 0, 1, 0, 0, 2, 1, 1, 2 };
	static int len;

	static {
		len = flags.length;
	}

	public static void violent() {
		int zero = 0, one = 0, two = 0;
		for (int i = 0; i < len; i++) {
			if (flags[i] == 0) {
				++zero;
			} else if (flags[i] == 1) {
				++one;
			} else {
				++two;
			}
		}
		int pos = 0, sum;
		while (true) {
			sum = zero + one + two;
			if (sum <= 0) {
				break;
			}
			if (zero > 0) {
				flags[pos++] = 0;
				--zero;
			} else if (one > 0) {
				flags[pos++] = 1;
				--one;
			} else if (two > 0) {
				flags[pos++] = 2;
				--two;
			}
		}
		System.out.println(Arrays.toString(flags));
	}
	
	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j]; 
		arr[j] = t; 
	}
	
	/*
	 * 利用快速排序的思想, 用三个指针只遍历一遍就完成任务(时间复杂度O(1))
	 * 指针begin: 指向开头
	 * 指针current: 遍历数组,完成交换
	 * 指针end: 指向末尾
	 * 当current为0, 与begin交换并且++begin, ++current
	 * 当current为1, 不交换, ++current
	 * 当current为2, 与end交换并且--current, --end (--current是因为交换之前end是0的话,交换之后current位置就变成0,current要回去遍历)
	 * */
	public static void mySort() {
		int begin = 0, current = 0, end = len - 1;
		while(current <= end) {
			if (flags[current] == 0) {
				swap(flags, begin, current);
				++begin;
				++current;
			}else if (flags[current] == 1) {
				++current;
			}else if (flags[current] == 2) {
				swap(flags, current, end);
				--current;
				--end;
			}
		}
		System.out.println(Arrays.toString(flags));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		violent();
		mySort();
	}

}
