package dfs;

import java.util.ArrayList;
import java.util.List;

/*
 * 给一个由数字组成的字符串。求出其可能恢复为的所有IP地址。
 * (你的任务就是往这段字符串中添加三个点, 使它成为一个合法的IP地址. 返回所有可能的IP地址.)
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * 解释: ["255.255.111.35", "255.255.11.135"] 同样可以.
 * 输入: "1116512311"
 * 输出: ["11.165.123.11","111.65.123.11"]
 */
public class 恢复IP地址 {

	public static List<String> restoreIpAddresses(String s) {
		// write your code here
		List<String> res = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return res;
		}
		dfs(res, s, 0, 0, "");
		return res;
	}

	/*
	 * count => number of '.' index => char of s
	 */
	public static void dfs(List<String> res, String s, int count, int index, String str) {
		// '.'用完index还遍历完 or '.'没用完index就遍历完了
		if (count >= 4 && index < s.length() || count < 4 && index >= s.length()) {
			return;
		}
		if (count >= 4 && index >= s.length()) {
			System.out.println(str);
			res.add(str);
			return;
		}
		int value = 0;
		boolean firstZero = false;
		while (index < s.length() && !firstZero) {
			value = 10 * value + (s.charAt(index) - '0');
			index++;
			// 首位能为0, 只循环一次（加一个'.'或者结束）
			if (value == 0) {
				firstZero = true;
			}
			if (value < 256) {
				if (count == 3) {
					dfs(res, s, count + 1, index, str + value);
				} else {
					dfs(res, s, count + 1, index, str + value + ".");
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(restoreIpAddresses("010010").toString());
	}

}
