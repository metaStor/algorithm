package algorithm;

import java.util.Scanner;

public class Next_Permutation {
	
	static int count;
	
	public static void swap(int [] array,int i,int j){
		int temp;
		temp = array[i];
			array[i] = array[j];
				array[j] = temp;
	}
	public static void perm(int [] array,int begin,int end){
		if(begin == end){
			for(int i=0;i<=end;i++)
				System.out.print(array[i]+" ");
			System.out.println();
			count++;
		}
		else{
			for(int i=begin;i<=end;i++){//每个元素都与第一个元素交换
				swap(array,i,begin);
				perm(array,begin+1,end);//递归子序列的全排列
				swap(array,i,begin);//交换回去,复原，再进行下一个元素
			}
		}
	}
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		
		int n = input.nextInt();
		int [] array = new int [n];
		count = 0;
		
		for(int i = 0;i<n;i++)
			array[i] = input.nextInt();
		
		perm(array,0,n-1);
		System.out.println(count);
		
		input.close();
	}

}
