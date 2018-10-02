package violence;

import java.util.*;

public class 四平方和 {
	
	public static void find(int n){

		double sum, m = Math.sqrt(n);
		boolean flag = false;
	
		for(int a=0;a<=m;a++){
			for(int b=a;b<=m;b++){
				for(int c=b;c<=m;c++){
					sum = Math.sqrt(n - (Math.pow(a, 2)+Math.pow(b, 2)+Math.pow(c, 2)));
					if(sum == (int)sum){
						System.out.print(a+" "+b+" "+c+" "+(int)sum);
						System.out.println();
						flag=true;
						break;
					}
				}
				if(flag)
					break;
			}
			if(flag)
				break;
		}
	}
	
	public static void main(String [] args){
	
		Scanner input = new Scanner (System.in);
		int n;
		
		while(input.hasNext()){
			n = input.nextInt();
			find(n);
		}
		
		input.close();
	}
}
