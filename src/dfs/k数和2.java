package dfs;

import java.util.ArrayList;
import java.util.List;

public class kÊıºÍ2 {

    public static List<List<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
    	List<List<Integer>> res = new ArrayList<>();
    	if (A == null || A.length == 0) {
			return res;
		}
    	dfs(res, new ArrayList<>(), A, 0, 0, 0, k, target);
    	return res;
    }
    
	public static void dfs(List<List<Integer>> res, ArrayList<Integer> temp, int[] a, int index, int sum, int count, int k, int target) {
		// TODO Auto-generated method stub
		if (count > k || sum > target) {
			return;
		}
		if (count == k && sum == target) {
			res.add(new ArrayList<>(temp));
			return;
		}
		for (int i = index; i < a.length; i++) {
			temp.add(a[i]);
			dfs(res, temp, a, i + 1, sum + a[i], count + 1, k, target);
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(kSumII(new int[] { 1, 6, 3, 4 }, 3, 8));
	}
}
