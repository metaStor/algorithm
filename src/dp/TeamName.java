package dp;

import java.util.Scanner;

public class TeamName {

	public static boolean check(String[] data, String name) {
		for (int i = 0; i < data.length; i++) {
			if (data[i].contains(name)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		for (int i = 0; i < T; i++) {
			int n = input.nextInt();
			int min = Integer.MAX_VALUE;
			String[] data = new String[n];
			for (int j = 0; j < n; j++) {
				data[j] = input.nextLine();
				min = Math.min(min, data[j].length());
			}
			String name = "";
			boolean flag = true;
			for (char c = 'a'; c <= 'z'; c++) {
				if(check(data, name+c)){
					name += c;
				}
			}
			
		}
		input.close();
	}

}
