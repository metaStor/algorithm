package algorithm;

import java.util.Scanner;
/*
 * 判断点是否在线段上
 * 满足两个条件：
 * ①(Q-P1)*(P2-P1)=0; 
 * 	  x轴:(Q.x-P1.x) (P2.x-P1.x)
 *    y轴:(Q.y-P1.y) (P2.y-P1.y)
 *    进行叉乘:(Q.x-P1.x)*(P2.y-P1.y)==(Q.y-P1.y)*(P2.x-P1.x)
 * 或者,
 *    斜率p2p1 == 斜率p1Q 
 *    即: (P2.y-P1.y)/(P2.x-P1.x) == (Q.y-P1.y)/(Q.x-P1.x) 
 * ② Q 在以 P1,P2 为对角顶点的矩形内。 
 */
public class Point_whether_on_Line {
	
	int x;
	int y;
	
	public static boolean judge(Point_whether_on_Line Q,Point_whether_on_Line P1,Point_whether_on_Line P2){
		if((Q.x-P1.x)*(P2.y-P1.y)==(Q.y-P1.y)*(P2.x-P1.x)
				&& (Math.min(P1.x, P2.x)<=Q.x && Math.min(P1.y, P2.y)<=Q.y)
				&& (Math.max(P1.x, P2.x)>=Q.x && Math.max(P1.y, P2.y)>=Q.y))
			return true;
		
		return false;
	}
	
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		Point_whether_on_Line Q = new Point_whether_on_Line();
		Point_whether_on_Line P1 = new Point_whether_on_Line();
		Point_whether_on_Line P2 = new Point_whether_on_Line();

			System.out.print("请输入点Q：");
			Q.x = input.nextInt();
			Q.y = input.nextInt();	
			System.out.print("请输入端点P1：");
			P1.x = input.nextInt();
			P1.y = input.nextInt();
			System.out.print("请输入端点P2：");
			P2.x = input.nextInt();
			P2.y = input.nextInt();
			
			if(judge(Q,P1,P2))
				System.out.println("Q点在线段内");
			else
				System.out.println("Q点不在线段内");

			input.close();
	}
}
