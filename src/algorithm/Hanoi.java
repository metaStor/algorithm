package algorithm;

/*假设有3个分别命名为X、Y和Z的塔座，在塔座X上插有n个直径大小各不相同、
 * 按从小到大编号为1,2，...，n的圆盘(如图所示)。
 * 现要求将X轴上的n个圆盘移至塔座Z上并仍按同样顺序叠排，圆盘移动时必须遵循下列规则：
 *（1）每次只能移动一个圆盘；
 *（2）圆盘可以插在X、Y和Z中的任一塔座上；
 *（3）任何时刻都不能讲一个较大的圆盘压在较小的圆盘之上。
 * 请问如何移？要移多少次？*/
import java.util.Scanner;

public class Hanoi {
	
	static int count;
	
	public static void move(int num,char a,char b){
		System.out.println("第"+(++count)+"步:将"+num+"号圆盘"+a+" → "+b);
	}
	
	public static void hanoi(int num,char source,char depend_on,char target){
		if(num == 1)
			move(1,source,target);
		else{
			hanoi(num-1,source,target,depend_on);//将A上的1~n-1号圆盘借助C移到B
			move(num,source,target);			 //将最后一个圆盘直接移到C
			hanoi(num-1,depend_on,source,target);//最后将B上的1~n-1号圆盘借助A移到C
		}
	}
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		
		char source = 'A';
		char depend_on = 'B';
		char target = 'C';
		int num = input.nextInt();	
		count = 0;
		hanoi(num,source,depend_on,target);
		input.close();
	}

}
