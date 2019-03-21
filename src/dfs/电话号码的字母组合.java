package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 给一个不包含'0'和'1'的数字字符串，每个数字代表一个字母，请返回其所有可能的字母组合。
 * 下图的手机按键图，就表示了每个数字可以代表的字母。
    1    2    3
        ABC  DEF

    4    5    6
   GHI  JKL  MNO

    7    8    9 
   PQRS TUV  WXYZ

    *    0    #
  以上的答案是按照词典编撰顺序进行输出的，不过，在做本题时，你也可以任意选择你喜欢的输出顺序。
  输入: "23"
  输出: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
  解释: '2' 可以是 'a', 'b' 或 'c', '3' 可以是 'd', 'e' 或 'f'
 */
public class 电话号码的字母组合 {
	
	static Map<Character, String> board = new HashMap<Character, String>();
	
	public static void init() {
		board.put('2', "abc");
		board.put('3', "def");
		board.put('4', "ghi");
		board.put('5', "jkl");
		board.put('6', "mno");
		board.put('7', "pqrs");
		board.put('8', "tuv");
		board.put('9', "wxyz");
	}

    public static List<String> letterCombinations(String digits) {
        // write your code here
    	List<String> res = new ArrayList<>();
    	if (digits == null || digits.length() == 0) {
			return res;
		}
    	init();
    	dfs(res, digits, "", 0);
    	return res;
    }
    
    // dfs
    public static void dfs(List<String> res, String digits, String result, int pos) {
    	if (result.length() == digits.length()) {
    		res.add(result);
			return;
		}
    	// get board's values
    	String value = board.get(digits.charAt(pos));
    	for (int i = 0; i < value.length(); i++) {
			dfs(res, digits, result + value.charAt(i), pos + 1);
		}
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(letterCombinations("").toString());
	}

}
