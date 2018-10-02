package algorithm_practices;

import java.util.Scanner;

public class QuickSort {
	
	public static void Swap(int [] date,int a,int b){//交换位置
		int t;
		t = date[a];
		date[a] = date[b];
		date[b] = t;
	}
	
	public static int quickSort(int [] date,int iLeft,int iRight){//对区间内的数据快排
		int i = iLeft+1,j = iRight;
		int major = date[iLeft];
		while(i <= j){
			while(date[j] >= major && j >= i)//找出比major小的数
				j--;
			while(date[i] < major && i <= j)//找出比major大的数
				i++;
			if(i < j){
				Swap(date,i,j);
				i++;
				j--;
			}
		}
		Swap(date,iLeft,j);//最后交换major和最后一个大的数
		return j;//返回基准数
	}
	
	public static void partion(int [] date,int iLeft,int iRight){
		if(iRight > iLeft){
			int k = quickSort(date,iLeft,iRight);
			partion(date,iLeft,k-1);//对左区间快排
			partion(date,k+1,iRight);//对右区间快排
		}
	}
	
	/*
	public static void quickSort(int [] date,int left,int right){
		if(left < right){
			int i = left,j = right;
			int major = date[i];//暂时记录基准数
			
			while(i != j){
				while(date[j] >= major && i<j)//找到比基准数小的数
					j--;
				date[i] = date[j];	//将小的数放在前面
				while(date[i] <= major && j>i)//找到比基准数大的数
					i++;
				date[j] = date[i];//将大的数放在前面
			}
			date[j] = major;//将最后一个重复的数用基准数代替
			quickSort(date,left,j-1);//递归左区间
			quickSort(date,j+1,right);//递归右区间
		}
	}*/
	public static void main(String [] args){
		
		Scanner input = new Scanner(System.in);
		
		int [] date = new int [100];
		int num = input.nextInt();
		
		for(int i=0;i<num;i++)
			date[i] = input.nextInt();
		
		partion(date,0,num-1);
		
		for(int i=0;i<num;i++)
			System.out.print(date[i]+" ");
		System.out.println();
		
		input.close();
	}
}
