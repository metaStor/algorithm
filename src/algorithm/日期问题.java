package algorithm;

import java.util.*;

/**
 * 标题：日期问题

	小明正在整理一批历史文献。这些历史文献中出现了很多日期。小明知道这些日期都在1960年1月1日至2059年12月31日。
	令小明头疼的是，这些日期采用的格式非常不统一，有采用年/月/日的，有采用月/日/年的，还有采用日/月/年的。
	更加麻烦的是，年份也都省略了前两位，使得文献上的一个日期，存在很多可能的日期与其对应。  
	
	比如02/03/04，可能是2002年03月04日、2004年02月03日或2004年03月02日。  
	
	给出一个文献上的日期，你能帮助小明判断有哪些可能的日期对其对应吗？
	
	输入
	----
	一个日期，格式是"AA/BB/CC"。  (0 <= A, B, C <= 9)  
	
	输入
	----
	输出若干个不相同的日期，每个日期一行，格式是"yyyy-MM-dd"。多个日期按从早到晚排列。  
	
	样例输入
	----
	02/03/04  
	
	样例输出
	----
	2002-03-04  年/月/日
	2004-02-03  月/日/年
	2004-03-02  日/月/年
 *
 */
public class 日期问题 {
	
	static class day{
		int year;
		int month;
		int date;
	}
	public static boolean check(day data){
		
		int year = data.year;
		int month = data.month;
		int date = data.date;
		//一年的12个月
		int [] date_max = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		//闰年判断
		if(year%4==0 && year%100!=0 || year%100==0 && year%400==0){
			date_max[2]=29;
		}
		else{
			date_max[2]=28;
		}
		//是否在范围之内
		if(year<1960 || year>2059)
			return false;
		if(month<1 || month>12)
			return false;
		if(date<1 || date>date_max[month])
			return false;
		
		return true;
	}
	//实现Comparator并重写compare方法
	static class compare implements Comparator <Object>{
		public int compare(Object o1, Object o2) {

			day a = (day) o1;
			day b = (day) o2;
			
			if(a.year != b.year){
				return a.year > b.year ? 1 : -1;//a.year > b.year
			}
			if(a.month != b.month){
				return a.month > b.month ? 1 : -1;
			}
			return a.date > b.date ? 1 : -1;
		}
	}

	public static void func(String [] st){

		day [] data = new day [6];
		
		int a = Integer.parseInt(st[0]);
		int b = Integer.parseInt(st[1]);
		int c = Integer.parseInt(st[2]);
		
		for(int i=0;i<data.length;i++){
			data[i] = new day();//实例化数组
		}
		//赋值
		data[0].year=1900+a; data[0].month=b; data[0].date=c;
		data[1].year=2000+a; data[1].month=b; data[1].date=c;
		data[2].year=1900+c; data[2].month=a; data[2].date=b;
		data[3].year=2000+c; data[3].month=a; data[3].date=b;
		data[4].year=1900+c; data[4].month=b; data[4].date=a;
		data[5].year=2000+c; data[5].month=b; data[5].date=a;
		
		//排序
		Arrays.sort(data, new compare ());
		//删除重复
	
		//输出符合的日期
		for(int i=0;i<data.length;i++){
			if(check(data[i])){
				System.out.println(data[i].year+"-"+data[i].month+"-"+data[i].date);
			}
		}
	} 

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String str = "";
		
		while(input.hasNext()){
			str = input.nextLine();
			String [] st = str.split("/");
			func(st);
		}
		input.close();
	}
}
