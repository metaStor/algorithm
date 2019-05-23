package algorithm;
/*
 * 30个人的班级，同一天生日的概率
 * */
public class Birth1 {

	public static void main(String [] args){
		final int N = 1000*100;
		int p;
		double q;
		
		while(true){
			int n=0;
			for(int i=0;i<N;i++){
				int [] a = new int [365];
				for(int j=0;j<30;j++){
					p = (int) (Math.random()*365);
					if(a[p]==1){
						n++;
						break;
					}
					else
						a[p]=1;
				}
			}
			q = (double)n/N;
			System.out.println(q);
		}
	}
}
