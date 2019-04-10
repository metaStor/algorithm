package array;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 给定一个整数数组，找到一个和最接近于零的子数组。返回第一个和最右一个指数。
 * 你的代码应该返回满足要求的子数组的起始位置和结束位置
 * 给出[-3, 1, 1, -3, 5]，返回[0, 2]，[1, 3]， [1, 1]， [2, 2] 或者 [0, 4]。
 * O(nlogn)的时间复杂度
 */
public class 最接近零的子数组和 {
	
	// 存储前index个元素的和sum
	public static class pair {
		int sum;
		int index;
		public pair(int sum, int index) {
			this.index = index;
			this.sum = sum;
		}
	}

	/* array: -3  1  1  -3  5
	 * pair : -3 -2 -1  -4  1
	 * pair[2]=-2表示前2项之和为-2, 初始pair[0]=0
	 * 若要第2～4项可用pair[4]-pair[1]表示
	 */
    public static int[] subarraySumClosest(int[] nums) {
        // write your code here
    	if (nums == null || nums.length == 0) return new int[] {};
    	int len = nums.length;
    	int[] res = new int[2]; // store the minimum index of start and end
    	pair[] pairs = new pair[len + 1];
    	// initialize
    	pairs[0] = new pair(0, 0);
    	// 算出前i项之和
    	for (int i = 1; i <= len; i++) {
			pairs[i] = new pair(pairs[i - 1].sum + nums[i - 1], i); 
		}
    	// sort by sum, ascending order
    	Arrays.sort(pairs, new Comparator<pair>() {
			@Override
			public int compare(pair o1, pair o2) {
				// TODO Auto-generated method stub
				return o1.sum - o2.sum;
			}
		});
    	// find minimum value
    	int min = Integer.MAX_VALUE;
    	for (int i = 1; i <= len; i++) {
    		// 排序后的pairs的pairs[i]-pairs[i-1]一定是正数
			if (min > pairs[i].sum - pairs[i - 1].sum) {
				// update
				min = pairs[i].sum - pairs[i - 1].sum;
				// index-1，得到真实index
				int[] temp = new int[] {pairs[i].index - 1, pairs[i - 1].index - 1};
				// 保证index是正序
				Arrays.sort(temp);
				/* 起点index需要+1得到真实起点
				 * 如：pair[4]-pair[1]表示真实的1~3下标
				 */
				res[0] = temp[0] + 1;
				res[1] = temp[1];
			}
		}
    	return res;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(subarraySumClosest(new int[] { -3, 1, 1, -3, 5 })));
	}

}
