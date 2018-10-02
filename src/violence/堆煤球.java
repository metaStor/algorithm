package violence;
/*
 *  有一堆煤球，堆成三角棱锥形。具体：
	第一层放1个，
	第二层3个（排列成三角形），
	第三层6个（排列成三角形），
	第四层10个（排列成三角形），
	....
	如果一共有100层，共有多少个煤球？
	
	1 3 6 10 ... 
	 2 3 4  5... 等差数列之和：((1+n)n)/2
*/
public class 堆煤球 {

	public static void main(String [] args){
		int sum=0;
		for(int i=1;i<=100;i++){
			sum+=((i+1)*i)/2;
		}	
		System.out.println(sum);
	}
}