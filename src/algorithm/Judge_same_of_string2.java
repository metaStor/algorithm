package algorithm;

import java.util.Scanner;

public class Judge_same_of_string2 {
	
	public static boolean judge(String [] str,int n){
		boolean flag = false;
		if(n == str.length)
			flag = true;
		else{
			for(int i=n;i<str.length-1;i++){
				if(str[n].equals(str[i+1]))
					return false;	
			}
			judge(str,n+1);
		}
		return flag;
	}
	
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		
		String [] str = {"ab","bc","ga","jk","lk"};
		
		System.out.println(judge(str,0));
		
		input.close();
	}
}
