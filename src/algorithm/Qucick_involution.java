package algorithm;

import java.util.Scanner;

public class Qucick_involution {//快速乘方，二分思想
	
	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		int a ,k ,sum,temp;
		while(sc.hasNext()){
			a = sc.nextInt(); 
			k = sc.nextInt();
			sum = 1 ;
			temp = a;
			while(k > 0){
				if(k%2 == 1)
					sum*=temp;
				temp*=temp;
				k/=2;
			}
			System.out.println(sum);
		}
		sc.close();
	}

}
