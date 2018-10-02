package dp;

import java.util.*;
/**
 * 每年冬天，北大未名湖上都是滑冰的好地方。北大体育组准备了许多冰鞋，
 * 可是人太多了，每天下午收工后，常常一双冰鞋都不剩。
 * 每天早上，租鞋窗口都会排起长龙，假设有还鞋的m个，
 * 有需要租鞋的n个。现在的问题是，这些人有多少种排法，
 * 可以避免出现体育组没有冰鞋可租的尴尬场面。
 * （两个同样需求的人（比如都是租鞋或都是还鞋）交换位置是同一种排法
 */
public class 排队租鞋问题 {
	
	public static int func(int m, int n){
		
		if(m < n)//当租鞋的人比还鞋的人多时，无法满足条件
			return 0;
		if(n == 0)//没人租鞋时，（都是还鞋的人在排队）
			return 1;
		/*
		 * 当 m>=n时
		 * 比如 m m n n m|n
		 * 第五个人不管存不存在、是m还是n， 都与他无关
		 * */
		return func(m, n-1)+func(m-1, n);
	}
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
	
		int m = input.nextInt();
		int n = input.nextInt();
		System.out.println(func(m, n));
	
		input.close();
	}
}

