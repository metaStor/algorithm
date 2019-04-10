package array;

/*
 * 给出一组非负整数，重新排列他们的顺序把他们组成一个最大的整数。
 * 最后的结果可能很大，所以我们返回一个字符串来代替这个整数。
 * 输入:[1, 20, 23, 4, 8]
 * 输出:"8423201"
 * 在 O(nlogn) 的时间复杂度内完成。
 */
public class 最大数 {

    public static String largestNumber(int[] nums) {
        // write your code here
    	String res = "";
    	sort(nums, 0, nums.length - 1);
    	for (int i : nums) {
    		if (res.equals("0") && i == 0) 	break;
    		res += i;
    	}
    	return res;
    }
    
	public static void sort(int[] nums, int low, int high) {
		// TODO Auto-generated method stub
		if (low >= high) return;
		int i = low, j = high, r = nums[low];
		while (i < j) {
			while (i < j && compare(r, nums[j]) >= 0) --j;
			if (i < j) nums[i++] = nums[j];  
			while (i < j && compare(r, nums[i]) <= 0) ++i;
			if (i < j) nums[j--] = nums[i]; 
		}
		nums[i] = r;
		sort(nums, low, i - 1);
		sort(nums, i + 1, high);
	}

	// 如: 比较23和8的组合最大值 => 823 > 238
	public static int compare(int t1, int t2) {
		// TODO Auto-generated method stub
		String s1 = "" + t1 + t2;
		String s2 = "" + t2 + t1;
		return s1.compareTo(s2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(largestNumber(new int[] { 1, 20, 23, 4, 8 }));
	}

}
