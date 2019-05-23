package algorithm;
/*
 * 算出第100002（十万零二）个素数
 * 2是第一个素数，3是第二个素数......
 * */
public class The_N_primeNumber {
			
	static int x = 1000*1000*10;
	static int num = 1000*10*10+2;

	public static void screen(byte [] a){//筛选
		for(int i=2;i<=x/2;i++){
			if(a[i]==1)	continue;//第一层for已经判断过的直接跳过
			for(int j=2;j<=x/i;j++){
				if(i*j<x)
					a[i*j]=1;//是合数标记1，素数标记0
			}
		}
	}
	
	public static void main(String [] args){
		byte [] a = new byte [x];
		screen(a);
		int count=0;
		for(int i=2;i<x;i++){
			if(a[i]==0)
				count++;
			if(count == num){
				System.out.println(i);
				break;
			}
		}
	}

}
