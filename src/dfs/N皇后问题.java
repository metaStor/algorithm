package dfs;

import java.util.ArrayList;
import java.util.List;

/*
  * n皇后问题是将n个皇后放置在n*n的棋盘上，皇后彼此之间不能相互攻击。
  * 给定一个整数n，返回所有不同的n皇后问题的解决方案。
  * 每个解决方案包含一个明确的n皇后放置布局，其中“Q”和“.”分别表示一个女王和一个空位置。 
 * 输入:4
 * 输出:
[
  // Solution 1
  [".Q..",
   "...Q",
   "Q...",
   "..Q."
  ],
  // Solution 2
  ["..Q.",
   "Q...",
   "...Q",
   ".Q.."
  ]
]
  */
public class N皇后问题 {
	
	static int count = 0;
	
    public static List<List<String>> solveNQueens(int n) {
    	List<List<String>> result = new ArrayList<List<String>>();
    	int[] queens = new int[n + 1]; // 坐标为行, 值为列
    	solve(result, queens, n, 1);
    	return result;
    }
    
    public static void solve(List<List<String>> result, int[] queens, int n, int pos) {
    	if (pos > n) {
			// add to result
    		List<String> tmp = new ArrayList<String>();
    		String str;
    		for (int i = 1; i <= n; i++) {
    			str = "";
				for (int j = 1; j <= n; j++) {
					str += (j == queens[i]) ? "Q" : "."; 
				}
				tmp.add(String.valueOf(str));
			}
    		result.add(tmp);
    		count++;
			return;
		}
    	// 放置到列
    	for (int j = 1; j <= n; j++) {
    		// 判断是否可以放置
			if (check(queens, pos, j)) {
				queens[pos] = j;
				solve(result, queens, n, pos + 1);
				queens[pos] = 0; // 回溯 
			}
		}
    }
    
    // i为将要放置到的行, j为将要放置到的列
    public static boolean check(int[] queens, int i, int j) {
    	// k < i : 遍历已放置的queens
    	for (int k = 1; k < i; k++) {
    		// 同列
			if (queens[k] == j) {
				return false;
			}
			// 同对角线
			if (Math.abs(k - i) == Math.abs(queens[k] - j)) {
				return false;
			}
		}
    	return true;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solveNQueens(4).toString());
		System.out.println(count);
	}

}
