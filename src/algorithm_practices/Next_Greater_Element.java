package algorithm_practices;

import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

/*You are given two arrays (without duplicates) nums1 and nums2 where nums1¡¯s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

1.All elements in nums1 and nums2 are unique.
2.The length of both nums1 and nums2 would not exceed 1000.
*/

public class Next_Greater_Element {
	/**
	 * @param nums1: an array
	 * @param nums2: an array
	 * @return: find all the next greater numbers for nums1's elements in the
	 *          corresponding places of nums2
	 */
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		// Write your code here
		Stack<Integer> s = new Stack<>();
		Map<Integer, Integer> m = new HashMap<>();
		int[] result = new int [nums1.length];
		for (int i = nums2.length - 1; i >= 0; i--) {
			while (!s.isEmpty() && s.peek() <= nums2[i]) {
				s.pop();
			}
        	m.put(nums2[i], s.isEmpty() ? -1 : s.peek());
        	s.push(nums2[i]);
		}
		for(int i=0;i<nums1.length;i++) {
			result[i] = m.get(nums1[i]);
		}
		return result;
	}
}