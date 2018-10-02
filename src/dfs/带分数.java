package dfs;

import java.util.Scanner;

public class 带分数 {

	static int n;
	static int count = 0;
	static int [] data = new int [10];
	static boolean [] vis = new boolean [10]; 
	
	public static int put(int a, int b){
		int number = 0;
		for(int i=a;i<=b;i++){
			number = number * 10 + data[i];
		}
		return number;
	}
	public static void use_data() {
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		//用两个指针i，j把data分成3部分，分别分给num1 num2 num3
		for(int i=1;i<=data.length-3;i++){
			for(int j=i+1;j<=data.length-2;j++){
				num1 = put(1, i);
				num2 = put(i+1, j);
				num3 = put(j+1, data.length-1);
				//满足条件且分子能整除分母
				if((num1 + num2 / num3 == n) && (num2 % num3 == 0)){
					count++;
					System.out.println(num1+" + "+num2+" / "+num3);
				}
			}
		}
	}
	public static void dfs(int len){
		if(len > 9){
			use_data();
			return;
		}
		for(int i=1;i<=9;i++){
			if(!vis[i]){
				data[len] = i;
				vis[i] = true;
				dfs(len+1);
				vis[i] = false;
			}
		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		dfs(1);
		System.out.println(count);
		input.close();
	}
}
