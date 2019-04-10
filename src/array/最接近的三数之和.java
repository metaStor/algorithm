package array;

import java.util.Arrays;

/*
 * 给一个包含 n 个整数的数组 S, 找到和与给定整数 target 最接近的三元组，返回这三个数的和。
 * 只需要返回三元组之和，无需返回三元组本身
 * 输入:[-1,2,1,-4],1
 * 输出:2 解释:-1+2+1=2
 * O(n^2) 时间, O(1) 额外空间。
 */
public class 最接近的三数之和 {

    public static int threeSumClosest(int[] numbers, int target) {
        // write your code here
    	if (numbers == null || numbers.length < 3) return 0;
    	int sum = numbers[0] + numbers[1] + numbers[2], len = numbers.length;
    	// sort
    	Arrays.sort(numbers);
    	// two points
    	for (int k = 0; k < len; k++) {
    		int i = k + 1, j = len - 1;
    		while (i < j) {
    			int s = numbers[k] + numbers[i] + numbers[j]; 
    			if (Math.abs(target - s) < Math.abs(target - sum)) sum = s;
    			if (s > target) --j;
    			else if (s < target) ++i;
    			else return s;
			}
		}
    	return sum;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(threeSumClosest(new int[] { 1,0,-1,0,-2,2,21,-15 }, -20));
	}

}
