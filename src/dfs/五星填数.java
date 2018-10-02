package dfs;

/**
 * 标题：五星填数

如【图1.png】的五星图案节点填上数字：1~12，除去7和11。
要求每条直线上数字和相等。

如图就是恰当的填法。

请你利用计算机搜索所有可能的填法有多少种。
注意：旋转或镜像后相同的算同一种填法。
 *
 */
public class 五星填数 {

	static int count = 0;
	static int [] map = new int [11];
	static boolean [] vis = new boolean [13];
	
	public static boolean check(){
		//五条边
		int a = map[1]+map[7]+map[9]+map[3];
		int b = map[1]+map[6]+map[8]+map[4];
		int c = map[2]+map[6]+map[7]+map[5];
		int d = map[2]+map[9]+map[10]+map[4];
		int e = map[3]+map[10]+map[8]+map[5];
		//若五条边相等，则返回true（等价交换）
		if(a==b && a==c && a==d && a==e){
			return true;
		}
		return false;
	}
	
	public static void dfs(int pos){
		
		if(pos>=11){
			if(check()){
				count++;
			}
			return;
		}
		
		for(int i=1;i<=12;i++){
			if(!vis[i]){
				map[pos]=i;
				vis[i]=true;
				dfs(pos+1);
				vis[i]=false;
			}
		}
	}
	
	public static void main(String [] args){
		//初始化，除去7,11
		vis[7]=true;
		vis[11]=true;
		dfs(1);
		System.out.println(count/5/2);//旋转五种，镜像两种
	}
}
