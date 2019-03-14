package game_theory;

import java.util.Scanner;

/*
 * 有 n 个硬币排成一条线。两个参赛者轮流从右边依次拿走 1 或 2 个硬币，直到没有硬币为止。拿到最后一枚硬币的人获胜。
 * 请判定 先手玩家 必胜还是必败? (两个参赛者都足够聪明)
 * 若必胜, 返回 true, 否则返回 false.
 * 输入: 1
 * 输出: true
 * 输入: 4
 * 输出: true
 * 解释: 
 * 先手玩家第一轮拿走一个硬币, 此时还剩三个.
 * 这时无论后手玩家拿一个还是两个, 下一次先手玩家都可以把剩下的硬币拿完.
 * O(1) 时间复杂度且O(1) 存储。
 */
public class 硬币排成线 {
	
	public static boolean game(int n) {
		return n % 3 != 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		System.out.println(game(n));
		input.close();
	}

}
