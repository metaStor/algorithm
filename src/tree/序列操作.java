package tree;
import java.util.Scanner;

/*
 * 给你一个长度为n的序列，初始为1,2,3...n，对其进行m次操作。
 * 操作有两种：
 * 1 l r  表示将区间[l,r]用 [1,2...r-l+1] 覆盖
 * 2 l r 查询[l,r]的区间和
 * 输入描述: 第一行包含2个数字，n,m（1 <= n,m <= 1e5） 接下来包含m行，格式如题面所示
 * 输出描述: 对于每个操作2，输出一行一个整数表示答案
 * 输入
 * 10 5
 * 2 1 10
 * 1 3 6
 * 2 1 10
 * 1 1 10
 * 2 1 10
 * 输出 55 47 55
 */
public class 序列操作 {
    
    static int n, m;
    static int[] arr = new int[1000005];
    
    // 前缀和 (TLE)
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        m = input.nextInt();
        for (int i = 1; i <= n; ++i) arr[i] = arr[i - 1] + i;
        while (m-- > 0) {
            int op = input.nextInt();
            int l = input.nextInt();
            int r = input.nextInt();
            if (op == 1) {
                int p = 1;
                for (int i = l; i <= n; ++i) {
                	if (i <= r) arr[i] = arr[i - 1] + (p++);
                	else arr[i] -= (r - l + 1);
                }
            } else if (op == 2) {
                System.out.println(arr[r] - arr[l - 1]);
            }
        }
        input.close();
    }
}