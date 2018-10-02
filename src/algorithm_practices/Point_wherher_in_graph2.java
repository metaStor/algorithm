package algorithm_practices;

import java.util.*;

public class Point_wherher_in_graph2 {//判断点是否在多边形内
	
	static class point{
		int x;
		int y;
	
		public void setx(int x){
			this.x=x;
		}
		public void sety(int y){
			this.y=y;
		}
	}

	public static int Min_x(point [] q){//取x的最小值
		int temp = q[0].x;
		for(int i=1;i<q.length;i++)
			if(temp>q[i].x)
				temp=q[i].x;
		return temp;
	}
	public static int Max_x(point [] q){//取x的最大值
		int temp = q[0].x;
		for(int i=1;i<q.length;i++)
			if(temp<q[i].x)
				temp=q[i].x;
		return temp;
	}
	public static int Min_y(point [] q){//取y的最小值
		int temp = q[0].y;
		for(int i=1;i<q.length;i++)
			if(temp>q[i].y)
				temp=q[i].y;
		return temp;
	}
	public static int Max_y(point [] q){//取y的最大值
		int temp = q[0].y;
		for(int i=1;i<q.length;i++)
			if(temp<q[i].y)
				temp=q[i].y;
		return temp;
	}
	public static boolean intersect(point [] p){//判断两直线是否相交
		int intersect_count=0;
		/*
		 * */
		
		if(intersect_count%2==0)
			return false;
		else 
			return true;
	}
	public static boolean judge(point [] p,point Q){
		int min_x=Min_x(p);
		int max_x=Max_x(p);
		int min_y=Min_y(p);
		int max_y=Max_y(p);
		int e = (max_y-min_y)/100;
		
		if(Q.x<=min_x || Q.x>=max_x)
			return false;
		if(Q.y<=min_y || Q.y>=max_y)
			return false;
		
		return true;
	}

	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();	
		point [] p = new point [num];
		point Q = new point();
			
			for(int i=0;i<p.length;i++){
				p[i]=new point();//实例化对象数组
				p[i].x=input.nextInt();
				p[i].y=input.nextInt();	
			}
				Q.x=input.nextInt();
				Q.y=input.nextInt();
				
			System.out.println(judge(p,Q));
				
		input.close();
	}
}
