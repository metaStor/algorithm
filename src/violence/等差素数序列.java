package violence;

/**
 * 2,3,5,7,11,13,....是素数序列。
 * 
类似：7,37,67,97,127,157 这样完全由素数组成的等差数列，叫等差素数数列。数列公差为30，长度为6。

2004年，格林与华人陶哲轩合作证明了：存在任意长度的素数等差数列。

这是数论领域一项惊人的成果！

有这一理论为基础，请你借助手中的计算机，满怀信心地搜索：

长度为10的等差素数列，其公差最小值是多少？

 */
public class 等差素数序列 {
	
	public static boolean isSS(int n){
		for(int i=2;i<n;i++){
			if(n%i==0)
				return false;
		}
		return true;
	}
	public static int func() {
		for(int i=2;i<1000;i++){
			for(int j=2;j<1000;j++){
				if(isSS(j)
				&& isSS(j+i)
				&& isSS(j+2*i)
				&& isSS(j+3*i)
				&& isSS(j+4*i)
				&& isSS(j+5*i)
				&& isSS(j+6*i)
				&& isSS(j+7*i)
				&& isSS(j+8*i)
				&& isSS(j+9*i)
				&& !isSS(j+10*i))
					return i;
				/*
				if(isSS(j)){
					len = 1;
					temp = j;
				}
				else
					continue;
				while(true){
					temp += i;
					if(!isSS(temp) && len==10){
						return i;
					}
					if(isSS(temp)){
						len++;
					}
					else
						break;
				}
				*/
			}
		}
		return 0;
	}
	public static void main(String [] args){
		System.out.println(func());
	}
}
