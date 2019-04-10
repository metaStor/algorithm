package array;

import java.util.Arrays;

/*
 * 给出一个整数数组 nums 和一个整数 k。划分数组（即移动数组 nums 中的元素），使得：
 * 所有小于k的元素移到左边
 * 所有大于等于k的元素移到右边
 * 返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k。
 * 输入:
 * [3,2,2,1],2
 * 输出:1 解释: 真实的数组为[1,2,2,3].所以返回 1
 * 使用 O(n) 的时间复杂度在数组上进行划分。
 * 你应该真正的划分数组 nums，而不仅仅只是计算比 k 小的整数数，如果数组 nums 中的所有元素都比 k 小，则返回 nums.length。
 */
public class 数组划分 {
	
    public static int partitionArray(int[] nums, int k) {
        // write your code here
    	int c = sort(nums, 0, nums.length - 1, k);
    	System.out.println(Arrays.toString(nums));
    	return c;
//    	return twoPoints(nums, k);
    }

    public static int twoPoints(int[] nums, int k) {
		// TODO Auto-generated method stub
    	int left = 0, right = nums.length - 1;
    	while (left <= right) {
			if (left <= right && nums[left] < k) ++left;
			else if (left <= right && nums[right] >= k) --right;
			else if (left <= right) swap(nums, left, right);
			else if (nums[left] < k) ++left;
		}
		return left;
	}

	// 快排思想
	public static int sort(int[] nums, int left, int right, int k) {
		// TODO Auto-generated method stub
		int i = left, j = right;
		while (i < j) {
			while (i < j && k <= nums[j]) --j;
			while (i < j && k > nums[i]) ++i;
			if (i < j) swap(nums, i, j);
			else if (nums[i] < k) i++;
		}
		return i;
	}

	public static void swap(int[] nums, int i, int j) {
		// TODO Auto-generated method stub
		int t = nums[i];
		nums[i++] = nums[j]; 
		nums[j--] = t; 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(partitionArray(new int[] { 7, 7, 9, 8, 6, 6, 8, 7, 9, 8, 6, 6 }, 10));
	}

}
