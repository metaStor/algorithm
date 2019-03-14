package dfs;

import java.util.List;

/*
 * 给出一个具有重复数字的列表，找出列表所有不同的排列
 * 输入：[1,1]
 * 输出：
 * [
   [1,1]
 * ]
 * 输入：[1,2,2]
 * 输出：
 * [
   [1,2,2],
   [2,1,2],
   [2,2,1]
 * ]
 */
public class 带重复元素的排列 {
	
    public static List<List<Integer>> permuteUnique(int[] nums) {
        // write your code here
    	return null;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1, 2, 2};
		List<List<Integer>> result = permuteUnique(arr);
		System.out.println(result.toString());
	}

}
