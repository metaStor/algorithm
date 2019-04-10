package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
 * 给定一组字符串，你需要找到这组字符串中最长的非公共子序列。最长的非公共子序列被定义为这些字符串之一的最长子序列，并且此子序列不应该是其他字符串的子序列。
 * 子序列是可以通过删除一些字符而不改变其余元素的顺序从一个序列导出的序列。可以说，任何字符串都是自身的子序列，空字符串是任何字符串的子序列。
 * 输入将是字符串列表，输出需要是最长的非公共子序列的长度。 如果最长的非公共子序列不存在，则返回-1。
 */
public class 最长非公共子序列之2 {

    public static int findLUSlength(String[] strs) {
        // write your code here
    	if (strs == null || strs.length < 2) return -1;
    	// establish hash
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for (String item : strs) {
    		if (!map.containsKey(item)) {
    			map.put(item, 1);
			} else {
				map.put(item, map.get(item) + 1);
			}
		}
    	// order by length of strings
    	Arrays.sort(strs, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o2.length() - o1.length();
			}
		});
    	System.out.println(Arrays.toString(strs));
    	int max = -1;
    	for (int i = 1; i < strs.length; i++) {
    		// 此处的去重不能单独过滤
			if (map.get(strs[i]) <= 1 && check(strs, i)) {
				max = (strs[i].length() > max) ? strs[i].length() : max;
			} 
			// 取第一个
			else if (i == 1 && map.get(strs[0]) <= 1) {
				max = strs[0].length();
			}
		}
    	return max;
    }
    
    // check subsequence
	public static boolean check(String[] strs, int i) {
		// TODO Auto-generated method stub
		int p1, p2;
		for (int k = i - 1; k >= 0; --k) {
			p1 = 0;
			p2 = 0;
			while (p1 < strs[i].length() && p2 < strs[k].length()) {
				if (strs[i].charAt(p1) == strs[k].charAt(p2)) {
					++p1;
					++p2;
				} else {
					p2++;
				}
			}
			if (p1 >= strs[i].length()) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(findLUSlength(new String[] { "aabbcc","aabbcc","cb" }));
	}

}
