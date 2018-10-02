package violence;

/**
 * 立方变自身

观察下面的现象,某个数字的立方，按位累加仍然等于自身。
1^3 = 1 
8^3  = 512    5+1+2=8
17^3 = 4913   4+9+1+3=17
...

请你计算包括1,8,17在内，符合这个性质的正整数一共有多少个？
 */
public class 立方变自身 {

	public static void main(String [] args){
		int count = 0;
		for(int i=1;i<1000;i++){
			int value = (int) Math.pow(i, 3);
			double temp = value;
			int sum = 0;
			while(temp>0){
				sum += temp%10;
				temp /=10;
			}
			/*
			int value = (int) Math.pow(i, 3);
			String s1 = value + "";
			char [] s2 = s1.toCharArray();
			int sum = 0;
			for(int j=0;j<s2.length;j++){
				sum += s2[j]-'0';
			}*/
			if(sum == i){
				count++;
				System.out.print(i+" ");
			}
		}
		System.out.println("\n"+count);
	}
}
