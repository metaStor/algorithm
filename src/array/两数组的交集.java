package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 给出两个数组，写出一个方法求出它们的交集
 * 结果中的每个元素必须是唯一的。
 * 结果可以是任意顺序的。
 */
public class 两数组的交集 {

    public static int[] intersection(int[] nums1, int[] nums2) {
        // write your code here
    	if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
			return new int[] {};
		}
//    	return hashDetctor(nums1, nums2);
//    	return binaryDetctor(nums1, nums2); 
    	return pointDetctor(nums1, nums2);
    }
    
	/*
     * 双指针扫描，先将两数组排序。通过两个数组的指针选出交集并去重
     */
	public static int[] pointDetctor(int[] nums1, int[] nums2) {
		// TODO Auto-generated method stub
		// above all, both arrays were sorted
		quickSort(nums1, 0, nums1.length - 1);
		quickSort(nums2, 0, nums2.length - 1);
		int p1 = 0, p2 = 0, index = 0;
		int[] res = new int[nums1.length];
		while (p1 < nums1.length && p2 < nums2.length) {
			if (nums1[p1] == nums2[p2]) {
				// 去重
				if (index == 0 || nums1[p1] != res[index - 1]) {
					res[index++] = nums1[p1];
				}
				++p1;
				++p2;
			} else if (nums1[p1] < nums2[p2]) {
				++p1;
			} else {
				++p2;
			}
			
		}
		int[] result = new int[index];
		for (int i = 0; i < index; i++) {
			result[i] = res[i]; 
		}
		return result;
	}

	// 哈系映射
	public static int[] hashDetctor(int[] nums1, int[] nums2) {
		// TODO Auto-generated method stub
		Set<Integer> hash = new HashSet<Integer>();
		Set<Integer> res = new HashSet<Integer>();
		for (int i : nums1) {
			hash.add(i);
		}
		for (int i : nums2) {
			if (hash.contains(i) && !res.contains(i)) {
				res.add(i);
			}
		}
		int[] result = new int[res.size()];
		int p = 0;
		for (int i : res) {
			result[p++] = i;
		}
		return result;
	}
	
	public static void quickSort(int[] arr, int low, int high) {
		if (low >= high) return;
		int i = low, j = high, r = arr[low];
		while (i < j) {
			while (i < j && r <= arr[j]) --j;
			if (i < j) arr[i] = arr[j]; 
			while (i < j && r >= arr[i]) ++i;
			if (i < j) arr[j] = arr[i]; 
		}
		arr[i] = r;
		quickSort(arr, low, i - 1);
		quickSort(arr, i + 1, high);
	}
	
    // 排序+二分查找
    public static int[] binaryDetctor(int[] nums1, int[] nums2) {
		// TODO Auto-generated method stub
		Set<Integer> hash = new HashSet<Integer>();
		quickSort(nums1, 0, nums1.length - 1);
		quickSort(nums2, 0, nums2.length - 1);
		int pos = 0;
		for (int i = 0; i < nums1.length; i++) {
			if (pos > nums1.length - 1) break;
			int index = binaryFind(nums2, nums1[i], pos);
			if (index != -1) {
				hash.add(nums1[i]);
				pos = index;
			}
		}
		int[] result = new int[hash.size()];
		int p = 0;
		for (int i : hash) {
			result[p++] = i;
		}
		return result;
	}

	public static int binaryFind(int[] arr, int targer, int pos) {
		// TODO Auto-generated method stub
		int i = pos, j = arr.length - 1;
		while (i < j) {
			int mid = (i + j) >> 1;
			if (arr[mid] < targer) {
				i = mid;
			} else if (arr[mid] > targer) {
				j = mid;
			} else {
				return mid;
			}
		}
		if (arr[pos] == targer) {
			return pos;
		}
		if (arr[j] == targer) {
			return j;
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(intersection(new int[] { 61,24,20,58,95,53,17,32,45,85,70,20,83,62,35,89,5,95,12,86,58,77,30,64,46,13,5,92,67,40,20,38,31,18,89,85,7,30,67,34,62,35,47,98,3,41,53,26,66,40,54,44,57,46,70,60,4,63,82,42,65,59,17,98,29,72,1,96,82,66,98,6,92,31,43,81,88,60,10,55,66,82,0,79,11,81 }
		, new int[] { 5,25,4,39,57,49,93,79,7,8,49,89,2,7,73,88,45,15,34,92,84,38,85,34,16,6,99,0,2,36,68,52,73,50,77,44,61,48 })));
	}

}
