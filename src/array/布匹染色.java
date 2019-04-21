package array;

import java.util.*;

/*
 * 小y有一块长度为n的布匹。颜色全部为0。他要给这个布匹染色。他总共有m种染料。小y认为一种染料用多次是不和谐的。所以每种染料会被用刚好一次。
 * 也就是说小y要给这块布匹染m次色。第i次会把Li到Ri这个区间染成颜色i。现在给出最终布匹每段的颜色。请你输出一种染色方案。数据保证有解
 * 输入共两行。
 * 第一行两个个正整数n,m，表示布匹的长度和染料的数量 
 * 第二行n个用空格隔开的正整数，第i个数字ai表示第i个布匹的颜色
 * Input: 
 * 3 3
 * 1 2 3
 * Output:
 * 1 3
 * 2 3
 * 3 3 
 */
public class 布匹染色 {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();
        // 两个数组存储m种颜色的左右区间
        int[] left = new int[m + 1];
        int[] right = new int[m + 1];
        for (int i = 1; i <= n; i++) {
			int color = input.nextInt();
			if (left[color] == 0) {
				left[color] = i; // color start with i
			} else {
				right[color] = i; // color end with i
			}
		}
        // processing and merger
        for (int i = 1; i <= m; i++) {
        	// 若right存在,说明第i的color肯定有左右区间
			if (right[i] == 0) {
				// 两边都没有左右点,说明当前i的color最终会被最后一个color覆盖
				if (left[i] == 0) {
					left[i] = left[m]; 
					right[i] = right[m]; 
				} 
				// 只有left point, 说明只用了一个长度
				else {
					right[i] = left[i]; 
				}
			} 
		}
        // output
        for (int i = 1; i <= m; i++) {
			System.out.println(left[i] + " " + right[i]);
		}
        input.close();
    }
}