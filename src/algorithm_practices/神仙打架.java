package algorithm_practices;

import java.util.Scanner;

/*
 * 牛村新开了一个系列赛，一个系列赛有m场比赛，比赛规则是解题数越多排名越前，
 * 如果前几名队伍解题数一样则会并列第一。
 * 有n个神仙队伍完整参加了m场比赛，请问有几个队伍拿过第一名。
 * 输入描述:第一行输入两个整数n,m,表示队伍数量与m场比赛。
 * 接下来n行输入一个数字矩阵，每行m个数字字符表示每场比赛的解题数量1<=n,m<=100
 * 输出描述:输出一个整数表示拿过第一名的队伍数量
 * 示例1
 * 输入
 * 3 5
 * 91728
 * 11828
 * 11111
 * 输出 3
 */
public class 神仙打架 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt(), cnt = 0;
        String[] matrix = new String[n];
        int[] max = new int[m];
        boolean[] win = new boolean[n];
        for (int i = 0; i < n; i++) {
            matrix[i] = input.next();
            for (int j = 0; j < m; ++j) {
                max[j] = Math.max(max[j], matrix[i].charAt(j) - '0');
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (win[j]) continue;
                if ((matrix[j].charAt(i) - '0') == max[i]) win[j] = true;
            }
        }
        for (boolean b : win) if (b) ++cnt;
        System.out.println(cnt);
        input.close();
    }

}
