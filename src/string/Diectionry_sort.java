package string;

/*
 * Êä³ö×Ö·û´®µÄ×ÖµäÐòÅÅÐò
 * 'ab' -> 'aa' 'ab' 'ba' 'bb'
 * */

public class Diectionry_sort {

	public static void dictionary_sort(char[] s, String t, int len) {
		if (t.length() == len) {
			System.out.println(t);
			return;
		}
		for (int i = 0; i < len; ++i) {
			dictionary_sort(s, t + s[i], len);
		}
	}

	public static void main(String[] args) {
		String s = "abc";
		dictionary_sort(s.toCharArray(), "", s.length());
	}
}
