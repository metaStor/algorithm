package algorithm;

import java.util.*;

public class 错误票据 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		LinkedList <Integer> list = new LinkedList<Integer>();
		int break_p = 0;//断点
		int repeat_p = 0;//重点
		int n = input.nextInt();
		String [] str = new String [n];
		/*
		 * nextLine()扫描当前行，遇到回车后返回回车前的字符串，不包括末尾的换行符。
		 * nextInt()将输入的下一个标记返回为一个int数据，
		 * 而标记指分隔符（相邻空格或者回车）之间的字符串（不包括分隔符）。
		 * 所以在第一次调用nextLine()输入字符串前调用一次nextLine()清除缓存
		 * */
		input.nextLine();
		for(int i=0;i<n;i++){
			str[i] = input.nextLine();
		}	
		for(int i=0;i<n;i++){	
			//先去掉首位空格，然后正则式匹配空格为单个空格,最后用单个空格分割
			String [] temp = str[i].trim().replaceAll("\\s+", " ").split(" ");//str[i].replaceAll("\\D+", " ");
			//将数字添加到数组中
			for(int j=0;j<temp.length;j++){
				int t = Integer.valueOf(temp[j]).intValue();
				list.push(t);
			}
		}
		//排序
		Collections.sort(list);
		//找断点
		for(int i=1;i<list.size();i++){
			if(list.get(i)-list.get(i-1) > 1){
				break_p = list.get(i) - 1; 
			}
		}
		//找重点
		for(int i=0;i<list.size()-1;i++){
			for(int j=i+1;j<list.size();j++){
				if(list.get(i) == list.get(j)){
					repeat_p = list.get(i);
				}
			}
		}
		System.out.println(break_p+" "+repeat_p);
		input.close();
	}
}
