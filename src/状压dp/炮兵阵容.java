package 状压dp;

import java.util.Scanner;

public class 炮兵阵容 {

	static int[] state;//记录状态
	static int[] sum;//记录炮兵个数
	static int n, m, num;
	
	//判断x周围2个是否存在其它炮兵，true即有
	public static boolean adjacent_lr(int x){
		return ((x & (x<<1)) != 0) && ((x & (x<<2)) != 0); 
	}
	
	public static void init(){
		num = 0;
		int total = 1<<m;
		for(int i=0;i<total;i++){
			if(!adjacent_lr(i)){
				state[++num] = i;
				//开始记录当前状态的炮兵个数
				int t = i;
				while(t != 0){
					sum[num] += ((t & 1) == 1) ? 1 : 0;
					t = t >> 1;
				}
			}
		}
	}
	
	public static void dp(){
		init();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		m = input.nextInt();
		dp();
		input.close();
	}

}
