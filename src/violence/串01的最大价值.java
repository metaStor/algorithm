package violence;

import java.util.Scanner;

/*
 * 你有一个长度为 n 的 01 串S，你可以执行最多 m 次操作。
 * 对于每次操作，你可以选择一个位置 i 满足 1≤i≤n，翻转这一位的值，0变成1，1变成0。
 * 定义一个 01 串的价值为其中最长连续0的个数和最长连续1的个数的较大值，求S在经过最多m次操作后的最大价值。
 * 输入描述:
 * 第一行一个整数 T ，表示接下来有 T 个样例。
 * 首先输入n,m，表示S串的长度n和操作次数m，其中1<=n<=100000，0<=m<=1000；
 * 接下来输入一个长度为n的字符串S。
 * 输出描述:
 * 一个整数，表示题面上描述的最大价值。
 */
public class 串01的最大价值 {
	
	public static void violence() {
        Scanner input = new Scanner(System.in);
        int T, n, m, p, l, r, lm, rm, cnt, max;
        T = input.nextInt();
        while (T-- > 0) {
        	n = input.nextInt();
        	m = input.nextInt();
        	char[] cs = input.next().toCharArray();
        	p = 0; max = 0;
        	while (p < n) {
				l = p; lm = m; cnt = 1;
				while (--l >= 0) {
					if (cs[p] != cs[l] && lm == 0) break;
					if (cs[p] != cs[l] && lm > 0) --lm;
					++cnt;
				}
				max = (cnt > max) ? cnt : max;
				r = p; rm = m; cnt = 1;
				while (++r < n) {
					if (cs[p] != cs[r] && rm == 0) break;
					if (cs[p] != cs[r] && rm > 0) --rm;
					++cnt;
				}
				max = (cnt > max) ? cnt : max;
				++p;
			}
        	System.out.println(max);
        }
		input.close();
	}
	
	public static void prefix() {
        Scanner input = new Scanner(System.in);
        int T, n, m;
        T = input.nextInt();
        while (T-- > 0) {
        	n = input.nextInt(); m = input.nextInt();
        	char[] t = input.next().toCharArray();
        	// 下标从1开始
        	char[] cs = new char[n + 1];
        	for (int i = 1; i <= n; i++) cs[i] = t[i - 1]; 				
        	int[] pre0 = new int[n + 1];
        	int[] pre1 = new int[n + 1];
        	for (int i = 1; i <= n; i++) {
        		if (cs[i] == '0') {
					pre0[i] = pre0[i - 1] + 1;
					pre1[i] = pre1[i - 1]; 
				} else {
					pre0[i] = pre0[i - 1];
					pre1[i] = pre1[i - 1] + 1;
				}
			}
//        	System.out.println(Arrays.toString(pre0) + "\n" + Arrays.toString(pre1));
        	System.out.println(binaryFind(pre0, pre1, 0, n, n, m));
        }
		input.close();
	}
	
	// 二分查找
	public static int binaryFind(int[] pre0, int[] pre1, int l, int r, int n, int m) {
    	int res = 0, mid;
    	while (l <= r) {
			mid = (l + r) >> 1;
    		if (judge(pre0, pre1, mid, n, m)) {
				res = mid; // 得到当前符合条件的价值长度
				l = mid + 1; // 可继续向更长的伸展
			} else {
				r = mid - 1; // 缩小长度
			}
		}
    	return res;
	}
	
	// split windows with length of len (尺取法)
	public static boolean judge(int[] pre0, int[] pre1, int len, int n, int m) {
		// 
		int l = 0;
		while (len <= n) {
			if (pre0[len] - pre0[l] <= m || pre1[len] - pre1[l] <= m) return true;
			++len;
			++l;
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		violence();
		prefix();
	}

}
