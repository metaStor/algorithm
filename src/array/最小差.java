package array;

/*
 * 给定两个整数数组（第一个是数组 A，第二个是数组 B），在数组 A 中取 A[i]，
 * 数组 B 中取 B[j]，A[i] 和 B[j]两者的差越小越好(|A[i] - B[j]|), 返回最小差。
 * 输入: A = [1, 2, 3, 4], B = [7, 6, 5]
 * 输出: 1 解释: B[2] - A[3] = 1
 * 时间复杂度 O(n log n)
 */
public class 最小差 {

	public static int smallestDifference(int[] A, int[] B) {
		// write your code here
    	sort(A, 0, A.length - 1, new int[A.length]);
    	sort(B, 0, B.length - 1, new int[B.length]);
		// 两个数组的指针在起始位置，哪个小就将它往前移动
		int min = Integer.MAX_VALUE, p1 = 0, p2 = 0;
		while (p1 < A.length && p2 < B.length) {
			if (A[p1] == B[p2]) {
				min = 0;
				break;
			} else if (A[p1] > B[p2]) {
				min = ((A[p1] - B[p2])) < min ? (A[p1] - B[p2]) : min;
				++p2;
			} else {
				min = ((B[p2] - A[p1])) < min ? (B[p2] - A[p1]) : min;
				++p1;
			}
		}
		return min;
	}
	
    // 归并排序
    // divide
	public static void sort(int[] nums, int i, int j, int[] temp) {
		if (i < j) {
			int mid = (i + j) >> 1;
			sort(nums, i, mid, temp);
			sort(nums, mid + 1, j, temp);
			merger(nums, i, j, mid, temp);
		}
	}

	// merger
	public static void merger(int[] nums, int left, int right, int mid, int[] temp) {
		int front = left, end = mid + 1, t = 0;
		while (front <= mid && end <= right) {
			if (nums[front] > nums[end]) {
				temp[t++] = nums[end++];
			} else {
				temp[t++] = nums[front++];
			}
		}
		// duplicate the remain elements of left array
		while (front <= mid) {
			temp[t++] = nums[front++];
		}
		// duplicate the remain elements of right array
		while (end <= right) {
			temp[t++] = nums[end++];
		}
		// duplicate the temp to nums
		t = 0;
		while (left <= right) {
			nums[left++] = temp[t++]; 
		} 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(smallestDifference(new int[] { 10, 25, 55, 34 }, new int[] { 18, 47, 46, 42 }));
	}

}
