package string;

/*
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 单词的构成：无空格字母构成一个单词，有些单词末尾会带有标点符号
 * 输入字符串是否包括前导或者尾随空格？可以包括，但是反转后的字符不能包括
 * 如何处理两个单词间的多个空格？在反转字符串中间空格减少到只含一个
 * 输入:  "the sky is blue"
 * 输出:  "blue is sky the"
 */
public class 翻转字符串中的单词 {
	
	/*
	 * 先对于每一个单词进行反转，再整个反转
	 * 如： the sky is blue 
	 *   => eht yks si eulb
	 *   => blue is sky the
	 */
    public static String reverseWords(String s) {
        // write your code here
        if (s.trim().equals("")) {
			return s;
		}
    	if (s.length() == 1) {
			return s;
		}
    	s = s.trim(); // 去首位空格
    	char[] ss = s.toCharArray();
        int len = ss.length;
    	reverse(ss, 0, len - 1);
    	// 依次反转每个单词
    	int i = 0, j = 0;
    	while (i < len) {
    		// 找到单词开头的位置i和结束位置j
    		while (i < j || i < len && ss[i] == ' ') i++;
    		while (j < i || j < len && ss[j] != ' ') j++;
    		reverse(ss, i, j - 1);
    	}
    	// 去除单词之间的空格
//    	String res = String.valueOf(ss);
//    	String[] results = res.split(" +");
//		res = "";
//		for (String item : results) {
//			res += item + " ";
//		}
//    	return res.trim();
    	return delSpace(ss, len);
    }
    
    public static String delSpace(char[] ss, int len) {
    	int i = 0, j = 0;
    	while (i < len) {
    		// 找到单词开头的位置i
    		while (i < len && ss[i] == ' ') i++;
    		// 把单词依次放在首位
    		while (i < len && ss[i] != ' ') ss[j++] = ss[i++];
			// add a space
			if (i < len) ss[j++] = ' ';
    	}
    	return String.valueOf(ss).substring(0, j);
	}

	public static void reverse(char[] s, int low, int high) {
    	while (low < high) {
			char c = s[low];
			s[low++] = s[high];
			s[high--] = c;
		}
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(reverseWords("the    sky is blue"));
	}
	
}
