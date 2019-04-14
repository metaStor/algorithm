package array;

import java.util.Arrays;

/*
 * 给定一个未经排序的数组，请找出其排序表中连续两个要素的最大间距。
 * 如果数组中的要素少于 2 个，请返回 0. 可以假定数组中的所有要素都是非负整数，且最大不超过 32 位整数。
 * 给定数组 [1, 9, 2, 5]，其排序表为 [1, 2, 5, 9]，其最大的间距是在 5 和 9 之间，= 4.
 * 用排序的方法解决这个问题是比较简单的方法，但是排序的时间复杂度是O(nlogn), 
 * 能否使用线性的时间和空间复杂度的方法解决这个问题
 */
public class 最大间距 {
	
    public static int maximumGap(int[] nums) {
        // write your code here
    	if (nums == null || nums.length < 2) return 0;
    	return sortMethod(nums);
    }


	private static int sortMethod(int[] nums) {
		// TODO Auto-generated method stub
		return bucketSort(nums);
//		Arrays.sort(nums);
//		int max = 0;
//		for (int i = 1; i < nums.length; i++) {
//			int value = nums[i] - nums[i - 1];
//			max = (value > max) ? value : max;
//		}
//		return max;
	}
	
	// bucket sort, less time, more memery
	public static int bucketSort(int[] nums) {
		// find minimum and maximum
		int max = nums[0], min = nums[0], len = nums.length;
		for (int i : nums) {
			max = (max < i) ? i : max;
			min = (min > i) ? i : min;
		}
		// counts of bucket 
		int bucketNum = len - 1;
		// capacity of every bucket
		int bucketCapacity = (max - min) / bucketNum + 1;
		// create buckets
		int[] bucketMax = new int[bucketNum];
		int[] bucketMin = new int[bucketNum];
		// initialize
		Arrays.fill(bucketMax, Integer.MIN_VALUE);
		Arrays.fill(bucketMin, Integer.MAX_VALUE);
		// put number into bucket
		for (int i : nums) {
	        // skip the minimum and maximum
            if (i == min || i == max) continue;
            int index = (i - min) / bucketCapacity;
            bucketMin[index] = Math.min(i, bucketMin[index]);
            bucketMax[index] = Math.max(i, bucketMax[index]);
		}
        // 比较最大gap，因为maxGap肯定不会存在于同index的bucket中(uniform capacity)
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i = 0; i < bucketNum; i++) {
            // empty
            if (bucketMax[i] == Integer.MIN_VALUE || bucketMin[i] == Integer.MAX_VALUE) continue;
            maxGap = Math.max(maxGap, bucketMin[i] - previous);
            previous = bucketMax[i];
        }
        // last update
        maxGap = Math.max(maxGap, max - previous);
        return maxGap;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(maximumGap(new int[] { 1, 9, 2, 5 }));
	}

}
