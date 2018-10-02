package algorithm_practices;

public class Take_ball {

	public static int func(int n,int m){
		if(n < m)
			return 0;
		if(n == m)
			return 1;
		if(m == 0)
			return 1;
		return func(n-1,m-1)+func(n-1,m);
	}
	
	public static void main(String [] args){
		//10个中选3个
		System.out.println(func(10,3));
	}
}
