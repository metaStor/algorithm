package algorithm_practices;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Stadium {
	
	public static boolean check(int[] data){
		return (data[0] + data[1] + data[2]) > data[3];
	}

	public static void deal(int[] data){
		//init
		int a = data[0];
		int b = data[1];
		int c = data[2];
		int d = data[3];
		//--------------
		int di = d - a;
		//解方程式: (di^2 - c^2 + b^2) / 2 * di = sqrt(b^2 - h^2) 
		double right = (di*di + b*b - c*c);
		double h = Math.sqrt(b * b - Math.pow((right / (2 * di)), 2));
		double result = (a + d) * h / 2;
		BigDecimal t = BigDecimal.valueOf(result);
		System.out.println(t.setScale(2, BigDecimal.ROUND_HALF_UP));
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		for(int i=1;i<=T;i++){
			int[] data = new int[4];
			for(int k=0;k<4;k++){
				data[k] = input.nextInt();
			}
			Arrays.sort(data);
			if(!check(data)){
				System.out.println("IMPOSSIBLE");
				continue;
			}
			deal(data);
		}
		input.close();
	}

}
