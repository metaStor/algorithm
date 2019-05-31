package string;

import java.util.Scanner;

/*
 * 给定一个长度为N的字符串S，问所有它的子串Sl…r(1≤l≤r≤N)，去重后有多少种。
 * 时间限制：2秒
 * 空间限制：524288K
 */
public class 不重复子串个数 {
	
	static int sign = 128, MAX = 100050;
	
	static int[] sa = new int[MAX];
	static int[] rank = new int[MAX];
	static int[] tp = new int[MAX];
	static int[] height = new int[MAX];
	static int[] tax = new int[sign];
	
	public static void da(char[] s, int len) {
		int i, j, p, w, k;
		for (i = 0; i < sign; ++i) tax[i] = 0;
		for (i = 0; i < len; ++i) { rank[i] = s[i]; tax[rank[i]]++;  }
		for (i = 1; i < sign; ++i) tax[i] += tax[i - 1];
		for (i = len - 1; i >= 0; --i) sa[--tax[rank[i]]] = i;
		for (w = 1, p = 1; w < len; w <<= 1, sign = p) {
			for (p = 0, i = len - w; i < len; ++i) tp[p++] = i;
			for (i = 0; i < len; ++i) if (sa[i] >= w) tp[p++] = sa[i] - w;
			for (i = 0; i < sign; ++i) tax[i] = 0;
			for (i = 0; i < len; ++i) tax[rank[tp[i]]]++;
			for (i = 1; i < sign; ++i) tax[i] += tax[i - 1];
			for (i = len - 1; i >= 0; --i) sa[--tax[rank[tp[i]]]] = tp[i];
			swap();
			rank[sa[0]] = 0;
			for (p = 1, i = 1; i < len; ++i) rank[sa[i]] = cmp(sa[i - 1], sa[i], w) ? p - 1 : p++;
		}
		for (i = 0, k = 0; i < len; ++i) {
			if (rank[i] == 0) continue;
			if (k > 0) --k;
			j = sa[rank[i] - 1];
			while (i + k < len && j + k < len && s[i + k] == s[j + k]) ++k;
			height[rank[i]] = k; 
		}
	}
	
	public static boolean cmp(int i, int j, int w) {
		return tp[i] == tp[j] && tp[i + w] == tp[j + w];  
	}
	
	public static void swap() {
		int[] t = rank;
		rank = tp;
		tp = t;
	}
	
	// 普通方法超时，用后缀数组求解(SA)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long cnt = 0;
		Scanner input = new Scanner(System.in);
		String str = input.next();
		int len = str.length();
		char[] s = str.toCharArray();
		da(s, len);
		for (int i = 0; i < len; i++) cnt += (len - sa[i] - height[i]);
		System.out.println(cnt);
		input.close();
	}

}
