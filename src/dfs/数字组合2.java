package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 给出一组候选数字(C)和目标数字(T),找出C中所有的组合，使组合中数字的和为T。C中每个数字在每个组合中只能使用一次。
 * 所有的数字(包括目标数字)均为正整数。
 * 元素组合(a1, a2, … , ak)必须是非降序(ie, a1 ≤ a2 ≤ … ≤ ak)。
 * 解集不能包含重复的组合。
 * 给出一个例子，候选数字集合为[10,1,6,7,2,1,5] 和目标数字 8  ,
 * 解集为：[[1,7],[1,2,5],[2,6],[1,1,6]]
 */
public class 数字组合2 {
	
    public static List<List<Integer>> combinationSum2(int[] num, int target) {
        // write your code here
    	List<List<Integer>> res = new ArrayList<>();
    	if (num == null || num.length == 0) {
			return res;
		}
    	Arrays.sort(num);
    	dfs(res, new ArrayList<>(), num, 0, 0, target);
    	return res;
    }
    
    // 注意去重操作
	public static void dfs(List<List<Integer>> res, ArrayList<Integer> temp, int[] num, int index, int sum, int target) {
		if (sum > target) {
			return;
		}
		if (sum == target) {
			res.add(new ArrayList<>(temp));
			return;
		}
		for (int i = index; i < num.length; i++) {
			// 去重
			if (i != 0 && num[i - 1] == num[i] && i > index) {
				continue;
			}
			temp.add(num[i]);
			// i + 1 防止重复元素
			dfs(res, temp, num, i + 1, sum + num[i], target);
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(combinationSum2(new int[] { }, 1));
	}

}
