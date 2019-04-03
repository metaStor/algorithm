package array;

import java.util.Arrays;

/*
 * 给定一个未排序的整数数组，找到其中位数。
 * 中位数是排序后数组的中间值，如果数组的个数是偶数个，则返回排序后数组的第N/2个数
 * 数组大小不超过10000
 */
public class 中位数 {
	
    public static int median(int[] nums) {
        // write your code here
    	int len = nums.length;
    	if (len <= 2) return nums[0];
    	int mid = len / 2; 
    	// 中位数的位置
    	mid += ((len & 1) == 1) ? 1 : 0;
    	return quickSort(nums, 0, len - 1, mid - 1);
    }
    
	public static int quickSort(int[] nums, int low, int high, int mid) {
		int i = low, j = high, r = nums[low];
		while (i < j) {
			while (i < j && r <= nums[j]) --j;
			if (i < j) nums[i++] = nums[j];
			while (i < j && r > nums[i]) ++i;
			if (i < j) nums[j--] = nums[i];
		}
		if (i > mid) return quickSort(nums, low, i - 1, mid);
		else if (i < mid) return quickSort(nums, i + 1, high, mid);
		return r;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] { -1,-2,-3,-100,-1,-50 };
		System.out.println(median(arr));
		System.out.println(Arrays.toString(arr));
	}

}
