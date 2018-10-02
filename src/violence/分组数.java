package violence;

/**
 * 将1,2...9共9个数分成三组，分别组成三个三位数，且使这三个三位数构成1:2:3的比例，
 * 
 * 试求出所有满足条件的三个三位数。例如：三个三位数192，384，576满足以上条件。
 *
 */
public class 分组数 {


	public static boolean check(int num){
		int a = num/100;
		int b = num/10%10;
		int c = num%10;
		if(a==b || a==c || b==c){
			return false;
		}
		if(a==0 || b==0 || c==0){
			return false;
		}
		return true;
	}
	public static boolean judge(int x, int y, int z) {
		int [] a = new int [10];
		int len = 0;
		while(true){
			a[len++] = x%10;
			a[len++] = y%10;
			a[len++] = z%10;
			x /= 10;
			y /= 10;
			z /= 10;
			if(x==0 && y==0 && z==0){
				len--;
				break;
			}
		}
		for(int i=0;i<len;i++){
			for(int j=i+1;j<=len;j++){
				if(a[i]==a[j])
					return false;
			}
		}
		return true;
	}
	public static void main(String [] args){
		double start = System.currentTimeMillis();
		for(int x=100;x<=400;x++){
			if(!check(x)) continue;
			for(int y=x;y<=700;y++){
				if(!check(y)) continue;
				for(int z=y;z<=999;z++){
					if(!check(z)) continue;
					if(x*2==y && x*3==z && judge(x, y, z)){
						System.out.print(x+" "+y+" "+z);
						System.out.println();
					}
				}
			}
		}
		System.out.println((System.currentTimeMillis() - start)/1000);//程序运行时间(s)
	}
}
