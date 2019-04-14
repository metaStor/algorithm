package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * 给出一组 n 个不同的非空字符串，您需要按以下规则为每个单词生成 最小 的缩写。
 * 从第一个字符开始，然后加上中间缩写掉的字符的长度，后跟最后一个字符。
 * 如果有冲突，就是多个单词共享相同的缩写，使用较长的前缀，而不是仅使用第一个字符，直到使单词的缩写的映射变为唯一。 换句话说，最终得到的缩写不能映射到多个原始单词。
 * 如果缩写不会使单词更短，则不进行缩写，保持原样
 * n 和每个单词的长度均不会超过 400。
 * 每个单词的长度大于 1。 
 * 这些词只包括小写英文字母。
 * 返回答案应该与原始数组保持相同的顺序
 * 给出 dict = ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 * 返回 ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 */
public class 单词缩写 {
	
	/********************** 方法1 (not AC) ***********************
	 * 有冲突的情况：
	 * 1. 长度相同的单词
	 * 2. 且最后一个letter相同
	 * 首先只有长度相同的单词才会有冲突的可能，因为长度不同，缩写的数字也不同。
	 * 再者，因为最后一个字符是固定不变的
	 * 主要取决与前缀的是否一样，比如internal=>inte3l, intarval=>inta3l
	 * 解法：
	 * 先将长度相同的串拿出来，再确定这些长度相同的串的公共长度的位置k，那么就可以从k+1处开始缩写
	 * 寻找公共长度，可以使用排序，这个可以得到最大相同的长度
	 */
	// 保存单词以及下标
	public static class word {
		String str;
		int index;
		public word(String str, int index) {
			// TODO Auto-generated constructor stub
			this.index = index;
			this.str = str;
		}
		@Override
		public String toString() {
			return "('" + str + "', " + index + ")";
		}
	}
	
    public static String[] wordsAbbreviation1(String[] dict) {
        // write your code here
    	Map<Integer, ArrayList<word>> words = new HashMap<>();
    	for (int i = 0; i < dict.length; i++) {
    		int len = dict[i].length();
    		ArrayList<word> value = words.get(len);
    		if (value == null) {
				value = new ArrayList<>();
				value.add(new word(dict[i], i));
				words.put(len, value);
			} else {
				value.add(new word(dict[i], i));
				words.put(len, value);
			}
		}
//    	System.out.println(words.toString());
    	for (int item : words.keySet()) {
			if (words.get(item).size() > 1) {
				ArrayList<word> value = words.get(item);
				// 将同长度的组按照字母排序
				Collections.sort(value, new Comparator<word>() {
					@Override
					public int compare(word o1, word o2) {
						// TODO Auto-generated method stub
						return o1.str.compareTo(o2.str);
					}
				});
				int len = value.size();
				System.out.println(item + ": " + value.toString());
				for (int i = 0; i < len; i++) {
					int maxPub = 0;
					boolean canAbbr = true;
					for (int j = 0; j < len; j++) {
						if (i == j) continue;
						String iStr = value.get(i).str;
						String jStr = value.get(j).str;
						if (iStr.charAt(iStr.length() - 1) != jStr.charAt(jStr.length() - 1)) {
							continue;
						}
						// 寻找最长的公共长度
						maxPub = findPub(iStr, jStr);
						String abbr1 = abbreviate(iStr, maxPub + 1);
						String abbr2 = abbreviate(jStr, maxPub + 1);
						if (abbr1.equals(abbr2)) {
							canAbbr = false;
							break;
						}
					}
					if (canAbbr) dict[value.get(i).index] = abbreviate(dict[value.get(i).index], maxPub + 1); 
				}
			} else {
				word w = words.get(item).get(0);
				dict[w.index] = abbreviate(dict[w.index], 1);
			}
		}
    	return dict;
    }
    
    public static int findPub(String s1, String s2) {
    	int p1 = 0, p2 = 0;
    	while (p1 < s1.length() - 1 && p2 < s2.length() - 1
    			&& s1.charAt(p1) == s2.charAt(p2)) {
				++p1;
				++p2;
		}
    	return p1;
    }
    
    // 小于4不缩写
    public static String abbreviate(String str, int preLen) {
    	return (preLen >= str.length() - 2) ? str : 
    		str.substring(0, preLen) + (str.length() - 1 - preLen) + str.charAt(str.length() - 1);
    }
    
    // ********************* 方法2 (AC) ***********************
    /* 用一个pre数组记录单词的前缀长度，初始为1
     * 先将所有字符串按照pre数组对应的进行缩短，再遍历缩短后的单词，检查冲突
     * 若冲突就将冲突单词几个放入集合中，pre对应前缀长度+1,继续处理。知道没有冲突为止
     */
    public static String[] wordsAbbreviation(String[] dict) {
    	int len = dict.length;
    	int[] pre = new int[len];
    	String[] res = new String[len];
    	Arrays.fill(pre, 1);
    	for (int i = 0; i < len; i++) {
			res[i] = abbreviate(dict[i], pre[i]); 
		}
//    	System.out.println(Arrays.toString(res));
    	for (int i = 0; i < len; i++) {
			while (true) {
				Set<Integer> set = new HashSet<>();
				for (int j = i + 1; j < len; j++) {
					if (res[i].equals(res[j])) set.add(j);
				}
				if (set.isEmpty()) break;
				set.add(i);
				for (int index : set) {
					res[index] = abbreviate(dict[index], ++pre[index]);
				}
			}
		}
    	return res;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(wordsAbbreviation(new String[] {"like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"})));
		/*
		System.out.println(Arrays.toString(wordsAbbreviation(new String[] {
				"aaaaaaaaaaaaaaaaabbbbbbbbbbkjufifeudlk","aaaaaaaaaaaaaaaaabbbbbbbbbbojqbrmfsyfqqsrwhled","aaaaaaaaaaaaaaaaabbbbbbbbbbolhhvpdpkfoidvsqqpd",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbwzrnlhyendbfhepobh","aaaaaaaaaaaaaaaaabbbbbbbbbbopsdoysmslhriwwwu","aaaaaaaaaaaaaaaaabbbbbbbbbbbwjevqdzacev",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbrhweyqriepzroy","aaaaaaaaaaaaaaaaabbbbbbbbbbcnjfjigbhlklazdqgiuk","aaaaaaaaaaaaaaaaabbbbbbbbbbfasjlcdvacpfeoj",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbzrlmyegzwztrhgqm","aaaaaaaaaaaaaaaaabbbbbbbbbbsabrljwjkvad","aaaaaaaaaaaaaaaaabbbbbbbbbbblgubwcxecfis",
				"aaaaaaaaaaaaaaaaabbbbbbbbbblilftczybunuobolyt","aaaaaaaaaaaaaaaaabbbbbbbbbbbppubgbkifd","aaaaaaaaaaaaaaaaabbbbbbbbbbmobkpochkyoujbhi",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbvivbqhsnzkkuaw","aaaaaaaaaaaaaaaaabbbbbbbbbbmdfewpyryuc","aaaaaaaaaaaaaaaaabbbbbbbbbbwnengvqbcrnqvp",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbgxpgvmnmmc","aaaaaaaaaaaaaaaaabbbbbbbbbbipqsetrtzstbxbr","aaaaaaaaaaaaaaaaabbbbbbbbbbppjuokaakaur",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbliascfhjkoaoipnjfpza","aaaaaaaaaaaaaaaaabbbbbbbbbbcurqfbnwaeaecnwbzjxx","aaaaaaaaaaaaaaaaabbbbbbbbbbwerunjzxrxysrwe",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbmgiftahfve","aaaaaaaaaaaaaaaaabbbbbbbbbbvfilicjzkxjel","aaaaaaaaaaaaaaaaabbbbbbbbbbtodddhiogutkleg",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbjpdkecyqjk","aaaaaaaaaaaaaaaaabbbbbbbbbbxklpodeyvvpormm","aaaaaaaaaaaaaaaaabbbbbbbbbbepfdtqwxnseptm",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbipkimfiegpxc","aaaaaaaaaaaaaaaaabbbbbbbbbbvobtdxwkgmeihlwehyy","aaaaaaaaaaaaaaaaabbbbbbbbbbdvmonnkdkyareqprlixp",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbkocjwexxlvlaw","aaaaaaaaaaaaaaaaabbbbbbbbbbprdbqdxkna","aaaaaaaaaaaaaaaaabbbbbbbbbbqkgvantcuwhvegiddwwi",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbdyjysydfihjysnmdrg","aaaaaaaaaaaaaaaaabbbbbbbbbbvuecaamqmedpjozahb","aaaaaaaaaaaaaaaaabbbbbbbbbbvgrrtznjnoudzhjlv",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbzmhztrtxvmgujlix","aaaaaaaaaaaaaaaaabbbbbbbbbbjnlkcvgqodiaduadrr","aaaaaaaaaaaaaaaaabbbbbbbbbbhrgfqmhktqld",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbnznbubvpomh","aaaaaaaaaaaaaaaaabbbbbbbbbbbjxllcohnworrkccgz","aaaaaaaaaaaaaaaaabbbbbbbbbbvezvdzhrtiezu",
				"aaaaaaaaaaaaaaaaabbbbbbbbbblerlstmbmc","aaaaaaaaaaaaaaaaabbbbbbbbbbeukfebwmqcw","aaaaaaaaaaaaaaaaabbbbbbbbbbulatiybbzkysaco",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbcdxksbqede","aaaaaaaaaaaaaaaaabbbbbbbbbbpqyapdgwlchhsphrje","aaaaaaaaaaaaaaaaabbbbbbbbbbttvgpfeithql",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbzexpmlggutgcjkdjkuxk","aaaaaaaaaaaaaaaaabbbbbbbbbbycmbcitrmyviztp","aaaaaaaaaaaaaaaaabbbbbbbbbbccybojvvwldhu",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbtmpojutxufmzmsoktdn","aaaaaaaaaaaaaaaaabbbbbbbbbbopavgkytqmrdn","aaaaaaaaaaaaaaaaabbbbbbbbbbttabhxgcyau",
				"aaaaaaaaaaaaaaaaabbbbbbbbbbtiydxbummwn","aaaaaaaaaaaaaaaaabbbbbbbbbbytftfezlwtbxwx","aaaaaaaaaaaaaaaaabbbbbbbbbbbajuqotjrhoukeezls"
		})));
		*/
	}

}
