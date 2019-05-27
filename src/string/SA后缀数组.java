package string;

import java.util.Arrays;

/*
 * 求字符串的子串以及重复子串
 */
public class SA后缀数组 {
	
	/* SA和RANK，两者互逆关系。SA[排名]=坐标(前缀下标)，RANK[下标]=排名
	 * HEIGHT[RANK[i]] >= HEIGHT[RANK[i - 1]] - 1
	 * tax[i] 基数排序的辅助数组
	 * tp[i] RANK的辅助数组(基数排序中的第二关键字)，于SA意义一样
	 * arr由char映射而来，方便下面的radixSort
	 * HEIGHT[RANK[i]]排名为RANK[i]的后缀与排名为RANK[i-1]的后缀的LCP(最长公共前缀)
	 * 性质：
	 * 若RANK[i]<RANK[j],则后缀str[i..len]的LCP为min(HEIGHT[RANK[i]+1],HEIGHT[RANK[i]+2]..HEIGHT[RANK[i]+j])(这是因为是按字典顺序排列)
	 */
	static int[] SA, RANK, HEIGHT, tax, tp, arr;
	
	public static void init(char[] str, int len) {
		// initialize
		RANK = new int[len * 2 + 1];
		SA = new int[len + 1];
		tax = new int[127 + 1];
		tp = new int[len * 2 + 1];
		arr = new int[len + 1];
		for (int i = 1; i <= len; i++) {
			arr[i] = str[i - 1]; // 映射为int
		}
	}
	
	/* 基数排序
	 * 假设已经得到了w长度的排名，要更新2w长度的排名
	 * tax记录元素出现的个数
	 * SA[i]: 表示长度为w的后缀中，排名为i的后缀下标
	 * RANK[i]: 表示长度为w的后缀中，第i个位置开始的后缀的排名
	 * tp[i]: 表示长度为2w的后缀中，第二关键字排名为i的后缀下标
	 */
	public static void radixSort(int ascii, int len) {
		// 初始化计数器tax
		for (int i = 0; i <= ascii; i++) tax[i] = 0; 
		// 分配，RANK[tp[i]]表示以tp[i]前缀开头的排在第几
		for (int i = 1; i <= len; i++) tax[RANK[tp[i]]]++;
		// 收集，计算当前基数所在的大小位置
		for (int i = 1; i <= ascii; i++) tax[i] += tax[i - 1]; 
		/* 第一关键字的相对位置是不会改变的，唯一有变化的是RANk值相同的那些后缀，我们需要根据tp的值来确定他们的相对位置
		 * 如:RANk相同，tp[1]=2,tp[2]=4，那么从4开始的后缀排名比从2开始的后缀排名靠后
		 * 首先倒着枚举i，那么SA[tax[RANK[tp[i]]]--]的意思就是说：
		 * 我从大到小枚举第二关键字，再用RANK[i]定位到第一关键字的大小
		 * 那么tax[RANK[tp[i]]]就表示当第一关键字相同时，第二关键字较大的这个后缀的排名是啥
		 * 得到了排名，我们也就能更新SA了
		 */
		for (int i = len; i >= 1; --i) SA[tax[RANK[tp[i]]]--] = tp[i]; // 要tax[RANK[tp[i]]]--，因为index是从1开始的
	}
	
	public static void suffix(int len) {
		// 初始化SA和RANK
		for (int i = 1; i <= len; i++) {
			RANK[i] = arr[i]; 
			tp[i] = i; // 相当与SA的意义
		}
		// 所以字符中最大的ascii码是127
		int ascii = 127;
		radixSort(ascii, len);
		Dbug();
		/* 子串长度翻倍，更新RANK
		 * w为当前子串的长度，ascii为映射后的排名种类数
		 * 当前tp(第二关键字)可直接由上一次的SA得到
		 */
		for (int w = 1, p = 1, i; p < len; w <<= 1, ascii = p) {
			// 下标超过w的时候，将不存在第二关键字，所以len-w+1~len的第二关键字为0,排序的时候应排在前面
			for (p = 0, i = len - w + 1; i <= len; ++i) tp[++p]= i; 
			/*
			 * 其余部分可以利用第一关键字的排序进行。但是第二关键字的下标和第一关键字的下标是不一样的，
			 * 第一关键字的值对应下标(SA[i])就是该关键字的下标，第二关键字的值的下标减去w才是对应关键字的下标(SA[i]-w)。
			 */
			for (i = 1; i <= len; ++i) {
				if (SA[i] > w) {
					tp[++p] = SA[i] - w; 
				}
			}
			Dbug();
			// 更新SA
			radixSort(ascii, len);
			Dbug();
			int[] temp = tp;
			tp = RANK;
			RANK = temp;
			RANK[SA[1]] = 1;
			p = 1;
			Dbug();
			// 有了已完成的SA，要得到RANK数组也很容易。但是对于相同的值，RANK应该相同，所以要判断一下合并以后的关键字是否相同
			for (i = 2; i <= len; i++) RANK[SA[i]] = cmp(tp, SA[i], SA[i - 1], w) ? p : ++p; 
			Dbug();
		}
	}

	public static boolean cmp(int[] tp, int i, int j, int w) {
		return tp[i] == tp[j] && tp[i + w] == tp[j + w];
	}
	
	public static void Dbug() {
		System.out.println("**********************");
		System.out.print("index:\t ");
		for (int i = 1; i <= SA.length; i++) System.out.print(i + ", ");
		System.out.println("\n" + "SA:\t" + Arrays.toString(SA));
		System.out.println("RANK:\t" + Arrays.toString(RANK));
		System.out.println("TP:\t" + Arrays.toString(tp));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		SimpleSuffixArray("aabaaaab");
//		System.out.println(Arrays.toString(SA));
		String string = "aabaaaab";
		init(string.toCharArray(), string.length());
		suffix(string.length());
//		System.out.println("SA: " + Arrays.toString(SA));
//		System.out.println("RANK: " + Arrays.toString(RANK));

	}
	
	// 简单的创建SA和HEIGHT
	public static void SimpleSuffixArray(String str) {
		int n = str.length();
		SA = new int[n];
		HEIGHT = new int[n];
		String[] suffixes = new String[n];
		for (int i = 0; i < n; i++) {
			suffixes[i] = str.substring(i);
		}
		Arrays.sort(suffixes);
		for (int i = 0; i < n; i++) {
			SA[i] = n - suffixes[i].length(); // SA[排名]=坐标(前缀)
		}
		// 比较LCP
		HEIGHT[0] = 0;
		for (int i = 1; i < n; i++) {
			HEIGHT[i] = compareLCP(suffixes[i - 1], suffixes[i]); 
		}
	}

	// 得到最长公共前缀
	public static int compareLCP(String str1, String str2) {
		int l = 0;
		while (l < str1.length() && l < str2.length() && str1.charAt(l) == str2.charAt(l)) ++l;
		return l;
	}

}
