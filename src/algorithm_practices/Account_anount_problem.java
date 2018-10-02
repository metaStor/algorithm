package algorithm_practices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Account_anount_problem {

	static Set <List<Integer>> sets = new HashSet <List<Integer>>();
	
	public static void init(boolean [] vis){//初始化vis[]
		for(int i=0;i<vis.length;i++)
			vis[i] = false;
	}

	public static int sum1(int [] a){//求错误的总金额
		int sum = 0;
		for(int i=0;i<a.length;i++)
			sum+=a[i];
		return sum;
	}
	
	public static void func(int [] a,boolean [] vis,int err_sum){
		if(err_sum == 0){
			List <Integer> lis = new ArrayList<Integer>();//ArrayList适合与数组,LinkedHashSet基于链表
			for(int i=0;i<vis.length;i++){
				if(vis[i]){
					lis.add(a[i]);
				}
			}
			sets.add(lis);
			return;
		}
		if(err_sum < 0)
			return;

		for(int i=0;i<a.length;i++){
			if(!vis[i]){
				vis[i]=true;
				func(a,vis,err_sum-a[i]);
				vis[i]=false;//回溯
			}
			else
				continue;
		}
	}
	public static void print(){//打印集合
		Iterator <List<Integer>> it = sets.iterator();
		while(it.hasNext()){
			Iterator <Integer> its = it.next().iterator();
			while(its.hasNext()){
				System.out.print(its.next()+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args){
		
		Scanner input = new Scanner(System.in);
		
		int sum = input.nextInt();
		int num = input.nextInt();
		int [] a = new int [num];
		boolean [] vis = new boolean [a.length];
		
		for(int i=0;i<num;i++)
			a[i] = input.nextInt();
		
		int err_sum = sum1(a) - sum;
		
		init(vis);
		Arrays.sort(a);//排序,避免输出重复元素
		func(a,vis,err_sum);
		print();
		input.close();
	}

}
