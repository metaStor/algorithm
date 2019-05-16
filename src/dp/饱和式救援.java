package dp;

import java.util.Scanner;

/*
 * 在《流浪地球》电影中，虽说在引爆木星之后推动了地球离开木星，但是大爆炸摧毁了地球上大部分的行星发动机。
 * 人类再一次展开全球性救援。此时的MOSS已经被烧毁，现在告诉你每只救援队的目标发动机的编号以及这只救援队在规定时间内成功救援的概率，
 * 假如有至少k个行星发动机能够得到重启，则认为地球会被拯救。请你设计一个程序，帮助人类完成这个计算。
 * 输入描述:
 * 第一行给出N，M，K。N代表人类派出的救援队总数，M代表被摧毁的行星发动机，K代表至少需要重启的行星发动机总数。(1<=N<=1e5,K<=M<=2000)
 * 接下来N行，每行给出ai,pi，分别代表第i支救援队的目标发动机的编号是ai，救援成功的概率为pi。(1<=ai<=M,0<=pi<=1)
 * 只要有一只救援队顺利抵达该行星发动机，则认为该发动机被成功重启。
 * 输出描述: 输出地球被救援成功的概率（请严格保留3位小数）
 * 输入
 * 3 2 2
 * 1 1
 * 1 1
 * 2 0.5
 * 输出 0.500
 */
public class 饱和式救援 {
	
	static int n, k, m;
	static double[] pro = new double[2010]; // 每个发动机的不成功率
	static double[][] dp = new double[2010][2010];
	
	/*
	 * 因为一个发动机可以被多个队伍救援，所以总的成功率=1-各个队伍失败率之积
	 * dp[i][j]表示对于i个发动机，已经救援了j个, 直接输出dp[M][K]即可
	 * 递推: 假设1个发动机，已经救援了1个，那么
	 * 如果救援成功就变成2个发动机，已经救援了2个
	 * 如果救援失败，变为2个发动机，已经救援了1个
	 * 状态转移方程: dp[i][j] = dp[i-1][j-1]*pro[i] + dp[i-1][j]*(1-pro[i])
	 */
	public static void DP(Scanner input) {
		if (k == 0) {
			System.out.println("1.000");
			return;
		}
		if (n < k) {
			System.out.println("0.000");
			return;
		}
		// initialize
		for (int i = 1; i <= m; ++i) pro[i] = 1; 
		for (int i = 1; i <= n; i++) {
			int id = input.nextInt();
			double probably = input.nextDouble();
			pro[id] *= (1 - probably); // 得到发动机id的失败率
		}
		// 求出各个发动机的救援成功率
		for (int i = 1; i <= m; i++) pro[i] = 1 - pro[i];
		// initialize
		dp[0][0] = 1;
		for (int i = 1; i <= m; i++) {
			// j=0都为失败
			dp[i][0] = dp[i - 1][0] * (1 - pro[i]);
			for (int j = 1; j <= m; j++) {
				dp[i][j] = dp[i - 1][j - 1] * pro[i] + dp[i - 1][j] * (1 - pro[i]);
			}
		}
		double ant = 0;
		for (int i = k; i <= m; i++) ant += dp[m][i];
		System.out.println(String.format("%.3f", ant));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		m = input.nextInt();
		k = input.nextInt();
		DP(input);
		input.close();
	}

}
