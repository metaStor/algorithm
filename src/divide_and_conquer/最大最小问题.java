package divide_and_conquer;

import java.util.*;

public class 最大最小问题 {

	/*
	 * 企业老板有一袋金块，他要从中挑选最重的一块给最优秀的员工， 挑选最轻的一块给一位普通员工。这个问题实际是从n个元素中查找最大值和最小值。
	 */

	// 普通方法
	public static void genate(int[] arr) {
		int max = arr[0], min = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
			if (arr[i] < min)
				min = arr[i];
		}
		System.out.println("max: " + max + "\nmin: " + min);
	}

	// 分治法
	static class MaxMin{
		
		private int max;
		private int min;
		
		public MaxMin(int max, int min) {
			this.max = max;
			this.min = min;
		}

		public int getMax() {
			return max;
		}

		public void setMax(int max) {
			this.max = max;
		}

		public int getMin() {
			return min;
		}

		public void setMin(int min) {
			this.min = min;
		}
	}

	public static MaxMin dc(int[] arr, int low, int high) {
		// 已成功分成两个元素的一组
		if (high - low <= 1) {
			int max, min;
			if (arr[high] > arr[low]) {
				max = arr[high];
				min = arr[low];
			} else {
				min = arr[high];
				max = arr[low];
			}
			return new MaxMin(max, min);
		} 
		// 可以分成多组
		else {
			int mid = (low + high) / 2;
			MaxMin left = dc(arr, low, mid);
			MaxMin right = dc(arr, mid+1, high);
			// 多组中的各自的最大最小值比较
			int max = (left.getMax() < right.getMax()) ? right.getMax(): left.getMax();
			int min = (left.getMin() < right.getMin()) ? left.getMin() : right.getMin();
			return new MaxMin(max, min);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 100000;
		int[] arr = new int[n];
		// random for values
		Random random = new Random();
		for(int i=0;i<n;i++) {
			arr[i] = random.nextInt(n);
		}
//		genate(arr);
		MaxMin mm = dc(arr, 0, n-1);
		System.out.println(mm.getMax());
		System.out.println(mm.getMin());
	}

}
