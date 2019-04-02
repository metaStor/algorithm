package array;

import java.util.Arrays;

/*
 * 原地翻转给出的数组 nums
 * 原地意味着你不能使用额外空间
 */
public class 翻转数组 {

	public static void reverseArray(int[] nums) {
		// write your code here
		if (nums == null || nums.length <= 1) {
			return;
		}
		int front = 0, end = nums.length - 1;
		while (front < end) {
			int t = nums[front];
			nums[front++] = nums[end];
			nums[end--] = t;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] { 1, 2, 5 };
		reverseArray(arr);
		System.out.println(Arrays.toString(arr));
	}

}
