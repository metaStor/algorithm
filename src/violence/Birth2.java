package violence;
/*
 * 生日蜡烛
 * 某君从某年开始每年都举办一次生日party，并且每次都要吹熄与年龄相同根数的蜡烛。
 * 现在算起来，他一共吹熄了236根蜡烛。
 * 请问，他从多少岁开始过生日party的？
 * */
public class Birth2 {
	
	public static void main(String [] args){
	
		for(int i=1;i<100;i++){
			int sum=0;
			for(int j=i;j<100;j++){
				sum+=j;
				if(sum==236){
					System.out.println(i);
					return;
				}
			}
		}
	}
}
