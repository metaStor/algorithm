package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 给一个包含n个数的整数数组S，在S中找到所有使得和为给定整数target的四元组(a, b, c, d)。
 * 四元组(a, b, c, d)中，需要满足a <= b <= c <= d
 * 答案中不可以包含重复的四元组
 * 输入:[1,0,-1,0,-2,2],0
 * 输出:
 * [[-1, 0, 0, 1]
 * ,[-2, -1, 1, 2]
 * ,[-2, 0, 0, 2]]
 */
public class 四数之和 {

    public static List<List<Integer>> fourSum(int[] numbers, int target) {
        // write your code here
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (numbers == null || numbers.length == 0) return res;
		// sort
		Arrays.sort(numbers);
		detectorOp(numbers, target, res);
		return res;
    }
    
	public static void detectorOp(int[] numbers, int target, List<List<Integer>> res) {
		// TODO Auto-generated method stub
		int len = numbers.length;
		List<Integer> temp;
		for (int n = 0; n < len - 1; n++) {
			for (int m = n + 1; m < len; m++) {
				int i = m + 1, j = len - 1;
				int remain = target - numbers[n] - numbers[m];
				while (i < j) {
					if (numbers[i] + numbers[j] == remain) {
						temp = new ArrayList<Integer>();
						temp.add(numbers[n]);
						temp.add(numbers[m]);
						temp.add(numbers[i]);
						temp.add(numbers[j]);
						if (!res.contains(temp)) res.add(temp);
						// 去重
						while (i < j && numbers[i] == numbers[i + 1]) ++i;
						while (i < j && numbers[j] == numbers[j - 1]) --j;
						++i;
						--j;
					} else if (numbers[i] + numbers[j] < remain) {
						++i;
					} else {
						--j;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, -2));
	}

}
