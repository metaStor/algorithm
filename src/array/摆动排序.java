package array;

import java.util.Arrays;

/*
 * 给你一个没有排序的数组，请将原数组就地重新排列满足如下性质
 * nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * 请就地排序数组，不要额外定义数组.
 * 输入: [3, 5, 2, 1, 6, 4]
 * 输出: [1, 6, 2, 5, 3, 4]
 * 解释: 这个问题可能有多种答案, [2, 6, 1, 5, 3, 4] 同样可以.
 * 输入: [1, 2, 3, 4]
 * 输出: [2, 1, 4, 3]
 */
public class 摆动排序 {
	
    public static void wiggleSort(int[] nums) {
        // write your code here
    	int len = nums.length;
    	if (len <= 1) {
    		return;
    	}
    	if (len == 2) {
    		swap(nums, 0, 1);
    		return;
    	}
//    	func1(nums, len);
    	func2(nums, len);
    }
    
    
    /* 思路2： 找规律, 奇数要比相邻的偶数小。偶数要比相邻的奇数大
     * 当len为奇数时，满足： nums[0] <= nums[1] >= nums[2] <= nums[3] >= nums[4] 
     * 当len为偶数时，满足： nums[0] => nums[1] <= nums[2] >= nums[3] 
     * 即当i为偶数时候，需要满足nums[i-1] <= nums[i]
     *   当i为奇数时候，需要满足nums[i-1] => nums[i]
     */
    public static void func2(int[] nums, int len) {
		for (int i = 1; i < len; i++) {
			if ((i & 1) == 1 && nums[i - 1] > nums[i]
					|| (i & 1) == 0 && nums[i - 1] < nums[i]) {
				swap(nums, i - 1, i);
			}
		}
	}

	// 思路1： 先排序好，再从1开始两两交换,时间复杂度为O(nlogn)
    public static void func1(int[] nums, int len) {
    	quickSort(nums, 0, len - 1);
    	// 两两交换
    	for (int i = 0; i < len - 1; i += 2) {
			swap(nums, i, i + 1);
		}
    }

	public static void quickSort(int[] nums, int low, int high) {
		if (low >= high) return;
		int i = low, j = high, r = nums[i];
		while (i < j) {
			while (i < j && r <= nums[j]) --j;
			if (i < j) nums[i++] = nums[j]; 				
			while (i < j && r > nums[i]) ++i;				
			if (i < j) nums[j--] = nums[i]; 
		}
		nums[i] = r;
		quickSort(nums, low, i - 1);
		quickSort(nums, i + 1, high);
	}
	
	public static void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j]; 
		nums[j] = t; 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] { 3, 5, 2, 1, 6, 4 };
		wiggleSort(arr);
		System.out.println(Arrays.toString(arr));
	}

}
