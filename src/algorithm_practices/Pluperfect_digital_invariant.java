package algorithm_practices;
/**
 * 水仙花数（Narcissistic number）也被称为
 * 超完全数字不变数（ PPDI）、
 * 自恋数、自幂数、阿姆斯壮数或阿姆斯特朗数（Armstrong number），
 * 水仙花数是指一个 n 位数（n≥3 ），它的每个位上的数字的 n 次幂之和等于它本身
 * （例如：1^3 + 5^3+ 3^3 = 153）
 * */
import java.math.*;
import java.util.*;

public class Pluperfect_digital_invariant {
	
	static BigInteger [] array = new BigInteger [10];//用来存0~9的21次方
	
	public static BigInteger pow(int m,int n){
		BigInteger a = BigInteger.ONE;
		for(int i=0;i<n;i++)
			a = a.multiply(BigInteger.valueOf(m));//n的21次方
		return a;
	}
	
	//测试当前出现次数是否满足
	public static void text(int [] vis,int n){
		BigInteger sum = BigInteger.ZERO;
		
		for(int i=0;i<vis.length;i++){
			//array中有0~9的n次方,用array中的数乘上对应vis中出现的次数得到总数
			sum=sum.add(array[i].multiply(BigInteger.valueOf(vis[i])));
		}

		String str = sum.toString();//将得到的总数转化为字符串
		
		if(str.length()!=n)//长度不对直接跳过
			return;
		
		int [] record = new int [10];//记录总数对应位置的出现次数

		for(int j=0;j<str.length();j++)
			record[str.charAt(j)-'0']++;//总数中对应位置出现的次数
		
		for(int k=0;k<vis.length;k++){
			if(vis[k]!=record[k])//如果vis中的次数和总数中出现的次数对不上就pass
				return;
		}
		System.out.println(sum);
	}
	
	//vis记录对应array位置的数出现的次数,pos为当前位置,n为次方,sum为次方数(总共出现n次)
	public static void method(int [] vis,int pos,int sum,int n){
		
		if(pos == vis.length-1){
			vis[pos]=sum;//当超出长度时，将未分配完的sum赋值
			text(vis,n);
			return;
		}
		if(sum == 0){
			text(vis,n);
			return;
		}
		for(int i=0;i<=sum;i++){//循环至sum完为止
			vis[pos]=i;
			method(vis,pos+1,sum-i,n);
			vis[pos]=0;
		}
	}
		
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();//1-21
		
		for(int i=0;i<array.length;i++)//算出0~9的num次方
			array[i]=pow(i,n);

		int [] vis = new int [10];
		
		//思路:当每个数的21次方出现的次数和等于21时，就可以判断
		//例如:1^3 + 5^3 + 3^3 = 153
		method(vis,0,n,n);
		
		input.close();
	}
}
