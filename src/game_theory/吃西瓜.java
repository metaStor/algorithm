package game_theory;

import java.util.Scanner;

/*
 * 在ACM队暑假集训的某一天，实验室里有n个人。因为天气很热，大家都很想吃西瓜。
 * 于是Eric买了m个西瓜拿到了实验室。Eric把这n个人分别编号为1,2,3...n,他希望这n个人循环轮流来吃西瓜。
 * 也就是说从1号开始，然后2号，3号...n号依次吃西瓜，n号吃完后又轮到1号来吃，直到这m个西瓜被吃完。 
 * 而这n个人每个人有一个肚量值，第i个人的肚量值为ai。 
 * lililalala是这n个人肚量值最大的人，不仅如此，他还非常贪吃，每次轮到他吃西瓜时，都会直接吃掉等同于他的度量值数量的西瓜。
 * 如果剩余的西瓜已经不够吃了，那么他会把所有西瓜直接吃完。(是的他很能吃) 
 * 除了lililalala以外的其他人,对于第i号每次吃西瓜可以选择吃掉[1,ai]中任意整数数量个西瓜。
 * 当然，不能超过当前剩余的西瓜数量。为了使吃西瓜更有意思一些，Eric规定如果在轮到某个人吃西瓜时没有西瓜了，
 * 那么由他来打扫一次实验室。(是的大家都很能吃)其他人都觉得lililalala吃的太多了应该由他来打扫卫生。
 * 请问在其他人串通好的情况下能否合理安排每个人的行动使得吃完西瓜后由lililalala来打扫卫生?
 * 保证所有样例中肚量最大的人只有一个。这个人是lililalala。 
 */
public class 吃西瓜 {
	
	static long n, m, maximum;
	static long[] arr;
	
	/* 题目只需要求出串通好的情况下能否合理安排每个人的使得lililalala打扫，并不要求出具体方案。
	 * 从"除了lililalala以外的其他人,对于第i号每次吃西瓜可以选择吃掉[1,ai]中任意整数数量个西瓜"入手，
	 * 假设其他人都以最大的肚量吃西瓜，若最后到lililalala的时候没有剩余了，说明就可以合伙搞定他。
	 * 假设其他人都以1的肚量吃西瓜，若还没到lililalala就已经吃完了说明不能搞定他。
	 * 则对于区间[L,R]内代表所有的安排，初始L=R=m, L表示都以1的肚量吃西瓜，R则以最大的肚量吃西瓜
	 * 对于非肚量最大的人，L每次减少1, R每次减少ai，对于肚量最大的人，L和R每次都减少max
	 */
	public static boolean reasoning() {
        if (n == 1) return true;
        int p = 0;
        long L = m, R = m;
        boolean canDraw = false;
        while (true) {
            if (arr[p] == maximum) {
                if (R <= 0) {
                    canDraw = true;
                    break;
                }
                L -= maximum;
                R -= maximum;
            } else {
                if (L <= 0) break;
                --L;
                R -= arr[p];
            }
            p = (p + 1 >= n) ? 0 : p + 1;
        }
        return canDraw;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		while (T-- > 0) {
			n = input.nextLong();
			m = input.nextLong();
			arr = new long[(int) n];
			maximum = arr[0];
			for (int i = 0; i < n; i++) {
				arr[i] = input.nextLong();
				maximum = Math.max(arr[i], maximum);
			}
			System.out.println(reasoning() ? "YES" : "NO");
		}
		input.close();
	}

}
