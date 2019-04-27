package string;

import java.util.Scanner;

public class ß£´® {

	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String target = "NowCoder";
        int n = input.nextInt();
        while (n-->0) {
            String str = input.next();
            System.out.println(check(str.toCharArray(), target.toCharArray())? "QAK" : "QIE");  
        }
        input.close();
    }

	private static boolean check(char[] str, char[] tar) {
		// TODO Auto-generated method stub
		int i = 0, j = 0;
		while (i < str.length && j < tar.length) {
			if (str[i] == tar[j]) {
				++i;
				++j;
			} else {
				++i;
			}
		}
		return (j >= tar.length) ? true : false;
	}
}
