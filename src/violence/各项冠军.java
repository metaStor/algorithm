package violence;

/**
 * 校田径运动会上A、B、C、D、E分别获百米、四百米、跳高、跳远和三级跳冠军。
 * 
	观众甲说：B获三级跳冠军，D获跳高冠军。
	
	观众乙说：A获百米冠军，E获跳高冠军。
	
	观众丙说：C获跳远冠军，D获四百米冠军。
	
	观众丁说： B获跳高冠军，E获三级跳冠军。
	
	实际情况是每人说对一半。请编程求出A、B、C、D、E各获哪项冠军。
 */
public class 各项冠军 {

	public static void main(String [] args){
		//1-百米，2-四百米，3-跳高，4-跳远，5-三级跳
		for(int a=1;a<=5;a++){
			for(int b=1;b<=5;b++){
				if(a==b) continue;
				for(int c=1;c<=5;c++){
					if(a==c || b==c) continue;
					for(int d=1;d<=5;d++){
						if(a==d || b==d || c==d) continue;
						for(int e=1;e<=5;e++){
							if(a==e || b==e || c==e || d==e) continue;
							//一人说对一半，共4次
							int count = 0;
							if(b==5 || d==3) count++;
							if(a==1 || e==3) count++;
							if(c==4 || d==2) count++;
							if(b==3 || e==5) count++;
							if(count==4){
								System.out.println(a+" "+b+" "+c+" "+d+" "+e);
							}
						}
					}
				}
			}
		}
	}
}
