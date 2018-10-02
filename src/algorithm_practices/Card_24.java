package algorithm_practices;
/*
 * 输入四张扑克牌，1~10
 * 用 + - * / 运算，结果正好为24
 * */
import java.util.*;

public class Card_24 {
	
	final static int N = 1000*100;
	
	public static String rand(int r){
		if(r == 1)
			return "+";
		if(r == 2)
			return "-";
		if(r == 3)
			return "*";
		
		return "/";
	}

	// 4 3 5 7 + - * 分两部分,前面4个为输入的数字,后面3个随机运算符
	public static void func(Deque <Integer> date){
		int r;
		Deque <String> ch = new ArrayDeque<String>();
		for(int i=0;i<3;i++){
			r = (int) (Math.random()*4+1);//1~4
			ch.offer(rand(r));//依次进队随机运算符
		}
		int sum=0;
		while(ch.isEmpty() || date.isEmpty()){
			String c = ch.pollFirst();//出队的第一个元素
			int a = date.removeFirst();//出栈date中的元素
			int b = date.removeFirst();//再出栈一个
			if(c.equals("+"))	sum +=b;
			if(c.equals("-"))	sum -=b;
			if(c.equals("*"))	sum *=b;
			if(c.equals("/"))	sum /=b;
		}
	}
	
	public static void main(String [] args){
		Scanner input = new Scanner (System.in);
		Deque <Integer> date = new ArrayDeque<Integer>();
		
		for(int i=0;i<4;i++)
			date.push(input.nextInt());//入栈1~10

		for(int k=0;k<N;k++)
			func(date);
		
		input.close();
	}
}
