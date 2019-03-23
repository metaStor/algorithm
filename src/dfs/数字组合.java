package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 给出一个候选数字的set(C)和目标数字(T),找到C中所有的组合，使找出的数字和为T。C中的数字可以无限制重复被选取。
 * 所有的数字(包括目标数字)均为正整数。
 * 元素组合(a1, a2, … , ak)必须是非降序(ie, a1 ≤ a2 ≤ … ≤ ak)。
 * 解集不能包含重复的组合。
 * 给出候选set[2,3,6,7]和目标数字7
 * 返回 [[7],[2,2,3]]
 */
public class 数字组合 {
	
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
    	List<List<Integer>> res = new ArrayList<>();
    	if (candidates == null || candidates.length == 0) {
			return res;
		}
    	Arrays.sort(candidates);
    	dfs(res, new ArrayList<>(), candidates, 0, 0, target);
    	return res;
    }

	public static void dfs(List<List<Integer>> res, ArrayList<Integer> temp, int[] candidates, int index, int sum, int target) {
		if (sum > target) {
			return;
		}
		if (sum == target) {
			res.add(new ArrayList<>(temp));
			return;
		}
		for (int i = index; i < candidates.length; i++) {
			if (i != 0 && candidates[i - 1] == candidates[i] && i > index) {
				continue;
			}
			temp.add(candidates[i]);
			// 传入i可以使用重复元素
			dfs(res, temp, candidates, i, sum + candidates[i], target);
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(combinationSum(new int[] { 2, 2, 3 }, 5));
	}

}
