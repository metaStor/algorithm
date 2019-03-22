package array;

import java.util.Arrays;

/*
 * 在数组中找到第 k 大的元素。
 * 你可以交换数组中的元素的位置
 * 要求时间复杂度为O(n) （不能使用快排，时间复杂度太高）
 * 空间复杂度为O(1)。(因此不能维护一个长度为k的数组) 
 */
public class 第k大元素 {
	
	/*
	 * 利用快排思想函数：
	 * 先找到一个基数，使比它大的都在它左边，比它小的都在它优右边，两边可以无序，最后返回这个基数的位置
	 * 然后比较这个基数的位置与n-1的大小，如果等于n-1，则这个数就是第k大的数
	 * 否则，如果基数位置小于n-1,则在其右半部分找
     * 如果基数位置大于n-1,则在其左半部分找
	 */
    public static int kthLargestElement(int n, int[] nums) {
        // write your code here
    	int a = quickSort(nums, 0, nums.length - 1, n);
    	// 无序
    	System.out.println(Arrays.toString(nums));
    	return a;
    }
    
    public static int quickSort(int[] nums, int start, int end, int n) {
    	int temp = nums[start];
    	int i = start, j = end;
    	while (i < j) {
    		// find j which less than temp
    		while (i < j && nums[j] <= temp) j--;
    		if (i < j) nums[i++] = nums[j]; 
    		// find i which more than temp
    		while (i < j && nums[i] > temp) i++;
    		if (i < j) nums[j--] = nums[i]; 
    	}
    	if (i == n - 1) return temp;
    	if (i >= n - 1) return quickSort(nums, start, i - 1, n);	
    	return quickSort(nums, i + 1, end, n);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(kthLargestElement(3, new int[] { 9, 3, 2, 4, 8 }));
	}

}
