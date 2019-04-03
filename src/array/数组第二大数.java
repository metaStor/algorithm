package array;

/*
 * 在数组中找到第二大的数
 */
public class 数组第二大数 {
	
    public static int secondMax(int[] nums) {
        // write your code here
    	int max = nums[0] > nums[1] ? nums[0] : nums[1]; 
    	int secMax = nums[0] < nums[1] ? nums[0] : nums[1];
    	for (int i = 2; i < nums.length; i++) {
			if (nums[i] > max) {
				secMax = max;
				max = nums[i];
			} else if (nums[i] > secMax) {
				secMax = nums[i];
			}
		}
    	return secMax;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(secondMax(new int[] { 1, -1, -2 }));
	}

}
