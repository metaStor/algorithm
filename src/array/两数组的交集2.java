package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 给定两个数组，计算两个数组的交集
 * 每个元素出现次数得和在数组里一样,答案可以以任意顺序给出
 * nums1 = [1, 2, 2, 1], nums2 = [2, 2]
 * 输出: [2, 2]
 * nums1 = [1, 1, 2], nums2 = [1]
 * 输出: [1]
 * -如果给定的数组已经排序了怎么办?如何优化算法?
 * -如果nums1的大小比num2的小怎么办?哪种算法更好?
 * -如果nums2的元素存储在磁盘上，并且内存受到限制，以至于不能一次将所有元素加载到内存中，该怎么办?
 */
public class 两数组的交集2 {

    public static int[] intersection(int[] nums1, int[] nums2) {
        // write your code here
//    	return hashDetector(nums1, nums2);
    	return twoPoints(nums1, nums2);
//    	return counter(nums1, nums2);
    }

    /* 计数法, 如果nums2的元素存储在磁盘上，并且内存受到限制，以至于不能一次将所有元素加载到内存中
     * 可先将nums1计数好，再缓存读取nums2的数据
     */
	public static int[] counter(int[] nums1, int[] nums2) {
		// TODO Auto-generated method stub
		int[] counters = new int[nums1.length];
		int[] temporary = new int[nums1.length];
		int t = 0;
		for (int i : nums1) {
			counters[i]++;
		}
		for (int i : nums2) {
			if (counters[i] != 0) {
				temporary[t++] = i;
				counters[i]--; 
			}
		}
		int[] res = new int[t];
		for (int i = 0; i < t; i++) {
			res[i] = temporary[i]; 
		}
		return res;
	}

	// 双指针发，more efficient
	public static int[] twoPoints(int[] nums1, int[] nums2) {
		// TODO Auto-generated method stub
    	Arrays.sort(nums1);
    	Arrays.sort(nums2);
    	int p1 = 0, p2 = 0, t = 0;
    	int[] temporary = new int[nums1.length];
    	while (p1 < nums1.length && p2 < nums2.length) {
    		if (nums1[p1] == nums2[p2]) {
				temporary[t++] = nums1[p1];
				++p1;
				++p2;
			} else if (nums1[p1] < nums2[p2]) {
				++p1;
			} else {
				++p2;
			}
    	}
    	int[] res = new int[t];
    	for (int i = 0; i < t; i++) {
			res[i] = temporary[i]; 
		}
		return res;
	}

	// hash map
	public static int[] hashDetector(int[] nums1, int[] nums2) {
		// TODO Auto-generated method stub
		Map<Integer, Integer> hash = new HashMap<Integer, Integer>();
		List<Integer> result = new ArrayList<Integer>();
		for (int i : nums1) {
			Integer value = hash.get(i);
			hash.put(i, (value == null) ? 1 : value + 1);
		}
		for (int i : nums2) {
			if (hash.containsKey(i) && hash.get(i) != 0) {
				result.add(i);
				hash.put(i, hash.get(i) - 1);
			}
		}
		int[] res = new int[result.size()];
		int p = 0;
		for (int i : result) {
			res[p++] = i;
		}			
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(intersection(new int[] {1, 2, 2, 1}, new int[] {2,2})));
	}

}
