package array;

import java.util.Arrays;

/*
 * 给定一个只包含字母的字符串，按照先小写字母后大写字母的顺序进行排序。
 * 小写字母或者大写字母他们之间不一定要保持在原始字符串中的相对位置。
 * 输入:  "abAcD"
 * 输出:  "acbAD"
 * 在原地扫描一遍完成
 */
public class 字符大小写排序 {
	
    public static void sortLetters(char[] chars) {
        // write your code here
    	if (chars == null || chars.length == 0) return;
    	// two Points
    	int low = 0, high = chars.length - 1;
    	char record = chars[low];
    	while (low < high) {
//    		Character.isUpperCase(chars[high]);
			while (low < high && (int) (chars[high]) >= 65 && (int) (chars[high]) <= 90) --high;
			if (low < high) chars[low++] = chars[high];
			while (low < high && (int) (chars[low]) >= 97 && (int) (chars[low]) <= 122) ++low;
			if (low < high) chars[high--] = chars[low];
		}
    	chars[low] = record;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] chars = "abAcD".toCharArray();
		sortLetters(chars);
		System.out.println(Arrays.toString(chars));
	}

}
