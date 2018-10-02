package dp;

import java.util.Scanner;

public class Team {

	static int n, value, num;
	static int[] data;
	static int[] state;

	public static boolean isTeam(int x) {
		int count = 0;
		boolean flag = false;
		while (x != 0) {
			if (count == 3) {
				flag = true;
				break;
			}
			if ((x & 1) != 0) {
				count++;
			}
			x = x >> 1;
		}
		return flag;
	}
	
	public static void init(){
		num = 0;
		for(int i=1;i<state.length;i++){
			if(isTeam(i)){
				state[++num] = i;
			}
		}
	}

	public static void dp() {
		int[] dp = new int [1<<n];
		init();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		value = input.nextInt();
		data = new int[n + 1];
		state = new int[1<<n];
		for (int i = 1; i <= n; i++) {
			data[i] = input.nextInt();
		}
		dp();
		input.close();
	}

}
