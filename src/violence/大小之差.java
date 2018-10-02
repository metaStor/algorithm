package violence;

/**
 * 由6个数字（允许重复）组成的最大6位数与最小6位数之间的差值也是一个6位数，且刚好包含了这6个数字。
 * 
 * 某6位数，只包含1~9中的某些数字(可以重复包含同一数字，但不含数字0)。

    该数字重新排列数位后，可以得到最大数和最小数。最大最小之差也是6位数，并且它恰好包含了组成原6位数同样的数字。

    比如：766431 - 134667 = 631764 就是具有这样特征的数字。

    你还能找到另一个这样的6位数吗？

	请填写它重新排列数位后得到的最大数：________________  
 */
public class 大小之差 {

	public static boolean number(int number) {
		int n = 0;
		while(number!=0){
			n++;
			number /= 10;
		}
		return (n==6) ? true: false;
	}
	/* 不含重复元素
	public static boolean noRepeat(int value){
		int [] a = new int [6];
		int len = 0;
		while(value!=0){
			a[len++] = value % 10;
			value /= 10;
		}
		for(int i=0;i<a.length-1;i++){
			for(int j=i+1;j<a.length;j++){
				if(a[i]==a[j]) return false;
			}
		}
		return true;
	}*/
	public static void main(String [] args){
		int max, min, value, count = 0;
		boolean flag;
		for(int a=1;a<=9;a++){
			for(int b=1;b<=9;b++){
				for(int c=1;c<=9;c++){
					for(int d=1;d<=9;d++){
						for(int e=1;e<=9;e++){
							for(int f=1;f<=9;f++){
								max = f*100000+e*10000+d*1000+c*100+b*10+a;
								min = a*100000+b*10000+c*1000+d*100+e*10+f;
								if(!number(max) || !number(min)) continue;
								value = max - min;
								if(!number(value)) continue;
								flag = true;
								int value1 = value;
								while(value1!=0){
									int temp = value1%10;
									value1 /=10;
									if(temp!=a && temp!=b && temp!=c && temp!=d && temp!=e && temp!=f){
										flag = false;
										break;
									}
								}
								if(flag){
									count++;
									System.out.print(max+" - "+min+" = "+value+"\n");
								}
							}
						}
					}
				}
			}
		}
		System.out.println(count);
	}
}
