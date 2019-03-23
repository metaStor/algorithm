package dfs;

import java.util.ArrayList;
import java.util.List;

/*
 * 给一个字符串,你可以选择在一个字符或两个相邻字符之后拆分字符串,使字符串由仅一个字符或两个字符组成,输出所有可能的结果
 * 输入： "123"
 * 输出： [["1","2","3"],["12","3"],["1","23"]]
 */
public class 分割字符串 {

    public static List<List<String>> splitString(String s) {
        // write your code here
    	List<List<String>> res = new ArrayList<>();
    	if (s == null || s.length() == 0) {
    		res.add(new ArrayList<>());
			return res;
		}
    	dfs(res, new ArrayList<>(), 0, s);
    	return res;
    }
	
	public static void dfs(List<List<String>> res, ArrayList<String> temp, int index, String s) {
		if (index >= s.length()) {
			res.add(new ArrayList<>(temp));
			return;
		}
		for (int i = index; i < s.length(); i++) {
			// neglect the length which more than 2
			if ((i + 1) - index > 2) {
				continue;
			}
			String string = s.substring(index, i + 1);
			temp.add(string);
			dfs(res, temp, i + 1, s);
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(splitString("123").toString());
	}

}
