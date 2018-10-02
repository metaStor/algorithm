package dfs;

/**
 * 把1~16的数字填入4x4的方格中，使得行、列以及两个对角线的和都相等，满足这样的特征时称为：四阶幻方。

四阶幻方可能有很多方案。如果固定左上角为1，请计算一共有多少种方案。
比如：
  1  2 15 16
 12 14  3  5
 13  7 10  4
  8 11  6  9

以及：
  1 12 13  8
  2 14  7 11
 15  3 10  6
 16  5  4  9
 
就可以算为两种不同的方案。

请提交左上角固定为1时的所有方案数字，不要填写任何多余内容或说明文字。
*/
public class 四阶幻方 {
	
	static int [][] s = new int [4][4];
	static boolean [] vis = new boolean [17];//判断1~16是否已经使用
	static int count = 0;
	static final int value = 34;//每一行每一列以及正斜对角线相加是34
	
	public static void init(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				s[i][j]=0;
			}
		}
		for(int i=0;i<17;i++)
			vis[i]=false;
		
		s[0][0]=1;//固定左上角为1
		vis[1]=true;
	}
	
	public static void print(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				System.out.print(s[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean check_row(int n){//判断第n行

		int sum1=0, sum2=0, sum3=0, sum4=0;
		for(int i=0;i<4;i++){
			sum1+=s[n][i];
			sum2+=s[n][i];
			sum3+=s[n][i];
			sum4+=s[n][i];
		}
		if(sum1!=value || sum2!=value || sum3!=value || sum4!=value)
			return false;
		return true;
	}
	public static boolean check_col(int n){//判断第n列
		
		int sum1=0, sum2=0, sum3=0, sum4=0;
		for(int i=0;i<4;i++){
			sum1+=s[n][i];
			sum2+=s[i][n];
			sum3+=s[i][n];
			sum4+=s[i][n];
		}
		if(sum1!=value || sum2!=value || sum3!=value ||sum4!=value)
			return false;
		return true;
	}
	public static boolean check(){//判断正斜对角线
		
		int a = s[0][0]+s[1][1]+s[2][2]+s[3][3];
		int b = s[0][3]+s[1][2]+s[2][1]+s[3][0];
		if(a!=value || b!=value)
			return false;
		return true;
	}
	
	public static void dfs(int pos){
		
		if(pos%4==0 && pos!=0){//检查每一行
			if(!check_row((pos-1)/4))
				return;
		}
		
		if(pos==13 || pos==14 || pos==15 || pos==16){//检查每一列
			if(!check_col((pos-1)%4))
				return;
		}
		
		if(pos >= 16){	
			if(check()){
				//print();
				count++;	
			}
			return;
		}

		int x = pos/4;
		int y = pos%4;
		for(int i=2;i<=16;i++){
			if(!vis[i]){
				s[x][y]=i;
				vis[i]=true;	
				dfs(pos+1);
				s[x][y]=0;
				vis[i]=false;
			}
		}
	}
	
	public static void main(String [] args){
		init();
		dfs(1);
		System.out.println(count);//416
	}
}

