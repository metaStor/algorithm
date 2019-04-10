package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * 根据N名运动员的得分，找到他们的相对等级和获得最高分前三名的人，他们将获得奖牌：“金牌”，“银牌”和“铜牌”。
 * 输入: [5, 4, 3, 2, 1]
 * 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * 说明：前三名运动员获得前三名最高分，因此获得“金牌”，“银牌”和“铜牌”。
 * 对于后两名运动员，你只需要根据他们的分数输出他们的相对等级
 */
public class 相对排名 {

    public static String[] findRelativeRanks(int[] nums) {
        // write your code here
    	int len = nums.length;
    	if (nums == null || len == 0) return null;
    	String[] res = new String[len];
    	int[] temp = Arrays.copyOf(nums, len);
    	heapSort(temp, len);
    	System.out.println(Arrays.toString(temp));
    	// hash map
    	Map<Integer, String> hash = new HashMap<Integer, String>();
    	for (int i = 0; i < len; i++) {
			if (i == 0) {
				hash.put(temp[i], "Gold Medal"); 
			} else if (i == 1) {
				hash.put(temp[i], "Silver Medal");
			} else if (i == 2) {
				hash.put(temp[i], "Bronze Medal"); 
			} else {
				hash.put(temp[i], "" + (i + 1));  
			}
		}
//    	System.err.println(hash.toString());
    	for (int i = 0; i < len; i++) res[i] = hash.get(nums[i]); 
    	return res;
    }
    
	// 堆排序
    public static void heapSort(int[] nums, int len) {
		// TODO Auto-generated method stub
		for (int i = len; i > 1; --i) {
			creatHeapOp(nums, 0, i); // 堆顶元素最小
			swap(nums, 0, i - 1); // 将最小元素放到末尾
		}
	}

	// 构建最小堆
    public static void creatHeap(int[] nums, int root, int len) {
    	int i, j;
    	// k初始为最后一个非叶子节点
    	for (int k = len / 2 - 1; k >= root; --k) {
        	i = k;
        	j = 2 * i + 1; // j is left child of i
        	while (j < len) {
        		// find the smaller between left and right children
    			while (j + 1 < len && nums[j] > nums[j + 1]) ++j;
    			// swap the i with j
    			if (nums[i] > nums[j]) {
        			swap(nums, i, j);
        			// deal with subtree
        			i = j;
        			j = 2 * i + 1;
    			} else {
					break;
				}
    		}
		}
    }
    
    // 构建最小堆, do not use swap
    public static void creatHeapOp(int[] nums, int root, int len) {
    	int i, j, t;
    	// k初始为最后一个非叶子节点
    	for (int k = len / 2 - 1; k >= root; --k) {
    		i = k;
    		t = nums[i]; // temporary
    		j = 2 * i + 1; // j is left child of i
    		while (j < len) {
    			// find the smaller between left and right children
    			while (j + 1 < len && nums[j] > nums[j + 1]) ++j;
    			// swap the i with j
    			if (t > nums[j]) {
    				nums[i] = nums[j]; 
    				// deal with subtree
    				i = j;
    				j = 2 * i + 1;
    			} else break;
    		}
    		nums[i] = t; 
    	}
    }
    
    public static void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(findRelativeRanks(new int[] {1, 2})));
	}

}
