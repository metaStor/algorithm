package algorithm;

/**
 * 短跑比赛，8条跑道。
 * 运动员多的时候，要分组，要求：
 * 1.组数尽可能的少
 * 2.每组间的人数差异尽可能少
 * 比如：9人，分组5,4
 *      26人，分组7,7,6,6，
 */

import java.util.*;

public class 运动员分组 {
	
	static final int N = 8;

	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		while(input.hasNext()){
			int x = input.nextInt();
			int sum = x/N+1;//分组数
			int n = x/sum;//每组平均分到的人
			int m = x%sum;//平均之后的余数
			//输出
			for(int i=0;i<m;i++){
				System.out.print(n+1+" ");
			}
			//输出剩下的
			for(int i=0;i<sum-m;i++){
				System.out.print(n+" ");
			}
			System.out.println();
		}
		input.close();
	}

}
