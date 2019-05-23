package algorithm;

import java.util.Scanner;

/*
 * In programming contests, the scoring board shows each team¡¯s judge results. Assume there is only one
 * problem in the contest, the scoring board will show according to the rules below:
 * When there is no submissions, the scoring board will show ¡°-¡±.
 * When there is no ¡°WA¡± before ¡°AC¡±, the scoring board will show ¡°+¡±.
 * When the team failed to solve the problem, the scoring board will show ¡°-k¡±, where k is the numberof ¡°WA¡±.
 * When the team passed the problem with several tries, the scoring board will show ¡°+k¡±, where k is
 * the number of ¡°WA¡± before ¡°AC¡±. Please write a program to show the scoring board.
 * Input
 * The first line of the input contains an integer T (1 ¡Ü T ¡Ü 50), denoting the number of test cases.
 * In each test case, there is one integer n(0 ¡Ü n ¡Ü 50) in the first line, denoting the number of submissions.
 * In the second line, there are n strings s 1 , s 2 , . . . , s n (s i ¡Ê {¡°AC¡±, ¡°WA¡±}), denoting the judge results of each
 * submission. The submissions have already been sorted by time.
 * Output 
 * For each test case, print a single line, denoting what the scoring board shows.
 */
public class ScoreBoard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		while(T-- > 0) {
			int n = input.nextInt();
			if (n == 0) {
				System.out.println("-");
				continue;
			}
			boolean pn = false; // default is negative
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				String res = input.next();
				if (!pn && res.equals("WA")) ++cnt;
				else pn = true;
			}
			if (cnt == 0 && pn) System.out.println("+");
			else if (pn) System.out.println("+" + cnt);
			else System.out.println("-" + cnt);
		}
		input.close();
	}

}
