package algorithm;

import java.util.Scanner;

public class Graph_clockwise {
	
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		
		int x1 = input.nextInt();
		int y1 = input.nextInt();
		int x2 = input.nextInt();
		int y2 = input.nextInt();
		int x3 = input.nextInt();
		int y3 = input.nextInt();
		/* p12 = (x2-x1,y2-y1)
		 * p13 = (x3-x1,y3-y1)
		 * 然后叉乘：p12×p13 
		 * */
		int last = (x2-x1)*(y3-y1)-(x3-x1)*(y2-y1);
		if(last < 0)
			System.out.println("顺时针");
		if(last > 0)
			System.out.println("逆时针");
		if(last == 0){
			System.out.println(0);
		}
		input.close();
	}
}
