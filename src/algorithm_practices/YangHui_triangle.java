package algorithm_practices;

import java.util.Scanner;

public class YangHui_triangle {

	public static int func(int m,int n){

		if(m == 0)
			return 1;
		if(m == n || n == 0)
			return 1;
		return func(m-1,n-1)+func(m-1,n);
	}
	public static void main(String [] args){
		
		Scanner input = new Scanner(System.in);

		int num = input.nextInt();
		
		for(int i=0;i<num;i++){
			for(int j=0;j<=i;j++){
				System.out.print(func(i,j)+" ");
			}
			System.out.println();
		}
		input.close();
	}
}
/*	利用循环，效率高
 * int [][] a = new int [n][n];
			//init
			for(int i=0;i<n;i++){	
				a[i][0] = 1;
				a[i][i] = 1;
			}
			for(int i=2;i<n;i++){
				for(int j=1;j<i;j++){
					a[i][j] = a[i-1][j-1] + a[i-1][j];
				}
			}
			//print
			for(int i=0;i<n;i++){
				for(int j=0;j<=i;j++){
					System.out.print(a[i][j]+" ");
				}
				System.out.println();
			}
		}*/
