package dp;//递归经典+动态规划
/*
 * such as : 6
 * = 6+0
 * = 5+1
 * = 4+2,4+1+1
 * = 3+3,3+2+1,3+1+1+1
 * ......*/
import java.util.Scanner;

public class Integer_partion1_a {
	
	public static int partion(int n,int m){//将n划成最大为m的划分个数,比如(6,6)，划成6+0
		if(n == 1 || m == 1)//当是1时，必然都是1+1+1...
			return 1;
		if(n == m)
			return partion(n,m-1)+1;//当(6,6)时，等于(6,5)+1,即5+1
		if(n < m)
			return partion(n,n);//比如(2,4)，最大加数是4，由于不能超过2，所以是(2,2)
		/* 其他情况，
		 * (6,3) = 3+2+1,3+1+1+1
		 * 同时约掉共有的3(或者3+2+1每项都减去1)，得2+1,1+1+1,即(3,2)
		 * 同时除掉公共的1，得3+2，3+1+1，即(5,3)
		 * 即(6,3) = (5,3)+(3,2)*/
		return partion(n-m,m)+partion(n,m-1);
	}
	
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		System.out.println(partion(num,num));
		input.close();
	}
}
