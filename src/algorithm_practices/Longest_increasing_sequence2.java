package algorithm_practices;

import java.util.Scanner;

public class Longest_increasing_sequence2 {

	public static void main(String [] args){
		
		Scanner input = new Scanner(System.in);
			
			int n = input.nextInt();
			int top,temp,low,hight,middle;
			int [] stack = new int [100];
			
			top=0;
			stack[top]=-1;//模拟栈
			
			for(int i=0;i<n;i++){
				temp=input.nextInt();
				if(stack[top]<temp){//如果输入的数大于栈顶的元素就入栈
					top++;
					stack[top]=temp;
				}
				else{
					low=1;
					hight=top;
					while(low<=hight){
						middle=(low+hight)/2;
						if(stack[middle]==temp)
							low=middle;
						if(stack[middle]<temp)
							low=middle+1;
						if(stack[middle]>temp)
							hight=middle-1;
					}
					stack[low]=temp;
				}
			}
			System.out.println(top);
			input.close();
		}	
}
