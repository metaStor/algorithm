package string;

import java.util.Arrays;

/*
 * author: shenhao
 * date: 2019-5-31 14:00:46
 */
public class SA后缀数组 {

    /* SA和RANK，两者互逆关系。SA[排名]=坐标(前缀下标)，RANK[下标]=排名
     * HEIGHT[RANK[i]] >= HEIGHT[RANK[i - 1]] - 1
     * tax[i] 基数排序的辅助数组
     * tp[i] RANK的辅助数组(基数排序中的第二关键字)，于SA意义一样
     * HEIGHT[RANK[i]]排名为RANK[i]的后缀i与排名为RANK[i-1]的后缀i-1的LCP(最长公共前缀)
     * 即HEIGHT[i]为SA[i-1]与SA[i]的LCP
     */
    static int[] SA, RANK, HEIGHT, tax, tp;
    static int sign = 127; // 字符中最大的ascii码是127

    public static void init(int len) {
        // initialize
        RANK = new int[2 * len];
        SA = new int[2 * len];
        tax = new int[sign + 1];
        tp = new int[2 * len];
        HEIGHT = new int[2 * len];
    }

	/* 基数排序
	 * 假设已经得到了w长度的排名，要更新2w长度的排名
	 * tax: 记录元素出现的个数
	 * SA[i]: 表示长度为w的后缀中，排名为i的后缀下标
	 * RANK[i]: 表示长度为w的后缀中，第i个位置开始的后缀的排名
	 * tp[i]: 表示长度为2w的后缀中，第二关键字排名为i的后缀下标
	 */

    public static void suffix(char[] str, int len) {
        int i, w, p;
        init(len); // initialize
        // 首先对第一个字符进行基数排序
        for (i = 0; i <= sign; ++i) tax[i] = 0;
        for (i = 0; i < len; ++i) { RANK[i] = str[i]; tax[RANK[i]]++; }
        for (i = 1; i <= sign; ++i) tax[i] += tax[i - 1];
        for (i = len - 1; i >= 0; --i) SA[--tax[RANK[i]]] = i;
		// 子串长度w倍增，更新RANK
        // w为当前子串的长度，ascii为映射后的排名种类数
        // 当前tp(第二关键字)可直接由上一次的SA得到
        for (w = 1, p = 1; p < len; w <<= 1, sign = p) {
            // 下标超过w的时候，将不存在第二关键字，所以len-w+1~len的第二关键字为0,排序的时候应排在前面
            for (p = 0, i = len - w; i < len; ++i) tp[p++]= i;
            // 其余部分可以利用第一关键字的排序进行(看论文上的经典图)。但是第二关键字的下标和第一关键字的下标是不一样的，
            // 第一关键字的值对应下标(SA[i])就是该关键字的下标，第二关键字的值的下标减去w才是对应关键字的下标(SA[i]-w)。
            for (i = 0; i < len; ++i) if (SA[i] >= w) tp[p++] = SA[i] - w;
			/* 第一关键字的相对位置是不会改变的，唯一有变化的是RANk值相同的那些后缀，我们需要根据tp的值来确定他们的相对位置
			 * 合并第一第二关键字，然后排序。先根据第一关键字排序，第一关键字相等时根据第二关键字大小排序
			 * 但事实上，是根据第二关键字的顺序重新改变了第一关键字的顺序，也就是说在本次基数排序中，出现先后次序排序=第二关键字大小排序
			 * 换句话说，我们先单独对第二关键字排序，根据这个顺序改变第一关键字的顺序，由于在基数排序时首先按照第一关键字的值排序，
			 * 而第一关键字的值没有改变所以首先还是根据第一关键字排序，改变的是第一关键字相同的时候，出现在前面的第二关键字排在前面。
			 * 如:RANk相同，tp[1]=2,tp[2]=4，那么从4开始的后缀排名比从2开始的后缀排名靠后
			 * 首先倒着枚举i，那么SA[tax[RANK[tp[i]]]--]的意思就是说：
			 * 我从大到小枚举第二关键字，再用RANK[i]定位到第一关键字的大小
			 * 那么tax[RANK[tp[i]]]就表示当第一关键字相同时，第二关键字较大的这个后缀的排名是啥
			 * 得到了排名，我们也就能更新SA了
			 */
            for (i = 0; i <= sign; ++i) tax[i] = 0;
            for (i = 0; i < len; ++i) tax[RANK[tp[i]]]++;
            for (i = 1; i <= sign; ++i) tax[i] += tax[i - 1];
            for (i = len - 1; i >= 0; --i) SA[--tax[RANK[tp[i]]]] = tp[i];
            // 上面得到SA数组之后，然后就是要更新RANK排名
            // 这时候又必须用到上一层的rank值，如果我既要从rank里面拿，又要向rank里面放，怎么办？
            // 当然是先把rank的东西放到另外一个数组里面, 由于tp已经没有用了，所以可以rank和tp交换一下，省得开辟新数组
            swap();
            RANK[SA[0]] = 0; // 老大就是老大
            // 有了已完成的SA，要得到rank数组也很容易。但是对于相同的值，rank应该相同，所以要判断一下合并以后的关键字是否相同
            // 用到上一层的rank值,前面计算SA值的时候如果字符串相同是默认前面的更小的，
            // 但这里计算rank的时候必须将相同的字符串看作有相同的rank，不然p==n之后就停止循环了
            for (i = 1, p = 1; i < len; ++i) RANK[SA[i]] = cmp(SA[i - 1], SA[i], w) ? p - 1 : p++;
        }
        calcHeight(str, len);
    }
    
    /* O(n)计算height数组，H[i] >= H[i-1]-1
     * 性质：
     * 若RANK[i]<RANK[j],则后缀str[i..len]的LCP为min(HEIGHT[RANK[i]+1],HEIGHT[RANK[i]+2]..HEIGHT[RANK[i]+j])(这是因为是按字典顺序排列)
     * 设H[i]=HEIGHT[RANK[i]], 其中i是后缀的位置，有：H[i]>=H[i-1]-1
     * 证明：
     * 设第i-1个字符串(这里的i-1是指非排名的后缀，即原字符串中的后缀)，排名在它前面的字符串假设为第k个字符串。
     * 注意，这里的k不一定是i-2,因为i-1是非排名后缀，而k是i-1的排名的前一位排名的后缀
     * 按照HEIGHT的定义，后缀k和后缀i-1的LCP显然是HEIGHT[RANK[i-1]], 再看看k+1后缀和i后缀的关系
     * 1. 后缀k的首字符和后缀i-1的首字符不同。那么k+1后缀可能在i后缀的前面，也可能在i后缀的后面，
     *    不过都没关系，因为HEIGHT[RANK[i-1]]为0无论HEIGHT[RANK[i]]为多少，都有HEIGHT[RANK[i]]>=HEIGHT[RANK[i-1]]-1
     * 2. 后缀k的首字符和后缀i-1的首字符相同。那么发现k+1后缀是由k后缀去掉首字符得到的，i后缀也是由i-1后缀去掉首字符得到的。
     *    那么显然，k+1后缀一定要排在i后缀的前面，不然就矛盾了。同时，后缀k和后缀i-1的LCP是HEIGHT[RANK[i-1]]
     *    可知，后缀k+1和后缀i的LCP显然是HEIGHT[RANK[i-1]]-1。
     * 对于比第i个字符串的字典序排名更靠前的那些字符串，谁和第i个后缀有最长公共前缀的长度？
     * 答案显然是排名紧邻着后缀i的那个后缀 —— SA[RANK[i]-1], 也就是说，
     * SA[RANK[i]]后缀和SA[RANK[i]-1]后缀的LCP至少是HEIGHT[RANK[i-1]]-1。(注意SA[i]的值就是排名i对应的后缀)
     * 那么就有HEIGHT[RANK[i]] >= HEIGHT[RANK[i-1]]-1, 即H[i]>=H[i-1]-1
     */
    public static void calcHeight(char[] s, int len) {
    	for (int i = 0, j, k = 0; i < len; i++) {
			if (RANK[i] == 0) continue; // skip zero rank
			if (k > 0) --k;
			j = SA[RANK[i] - 1]; // j为i的上一排名的后缀
			while (i + k < len && j + k < len && s[i + k] == s[j + k]) ++k;
			HEIGHT[RANK[i]] = k;
		}
    }

    public static boolean cmp(int i, int j, int w) {
        return tp[i] == tp[j] && tp[i + w] == tp[j + w]; // 第1,2关键字合并后排名是否相同
    }

    public static void swap() {
        int[] temp = tp;
        tp = RANK;
        RANK = temp;
    }

    public static void Debug(int len) {
        System.out.print("index:\t");
        for (int i = 0; i < len; i++) System.out.print(i + " ");
        System.out.print("\nsa:\t");
        for (int i = 0; i < len; i++) System.out.print(SA[i] + " ");
        System.out.print("\nrank:\t");
        for (int i = 0; i < len; i++) System.out.print(RANK[i] + " ");
        System.out.print("\ntp:\t");
        for (int i = 0; i < len; i++) System.out.print(tp[i] + " ");
        System.out.println("\n");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		SimpleSuffixArray("aabaaaab");
//		System.out.println(Arrays.toString(SA));
        String string = "aabaaaab";
        suffix(string.toCharArray(), string.length());
        Debug(string.length());
        for (int i = 0; i < string.length(); i++) System.out.print(HEIGHT[i] + " ");
    }

    // ---------------------- 简单的创建SA和HEIGHT -----------------------

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

    // ----------------------------- end -------------------------
}
