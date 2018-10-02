package violence;

/*
 凑算式
 
     B      DEF
A + --- + ------- = 10
     C      GHI	 
	 
这个算式中A~I代表1~9的数字，不同的字母代表不同的数字。

比如：
6+8/3+952/714 就是一种解法，
5+3/1+972/486 是另一种解法。

这个算式一共有多少种解法？
*/

public class  凑算式 {

	public static void main(String [] args){
		
		int count=0;
	
		for(int a=1;a<=9;a++){			
			for(int b=1;b<=9;b++){
				if(a==b) continue;
				for(int c=1;c<=9;c++){
					if(c==a || b==c) continue;
					for(int d=1;d<=9;d++){
						if(a==d || d==b || d==c ) continue;
						for(int e=1;e<=9;e++){
							if(a==e || e==b || e==c || e==d) continue;
							for(int f=1;f<=9;f++){
								if(a==f || f==b || f==c || f==d || f==e) continue;
								for(int g=1;g<=9;g++){
									if(a==g || g==b || g==c || g==d || g==e || g==f) continue;
									for(int h=1;h<=9;h++){
										if(a==h || h==b || h==c || h==d || h==e || h==f || h==g) continue;	
										for(int i=1;i<=9;i++){
											if(a==i || i==b || i==c || i==d || i==e || i==f || i==g || i==h)
												continue;
											if((a+b*1.0/c+(d*100+e*10+f)*1.0/(g*100+h*10+i)) == 10.0){
												count++;
												System.out.print(a+" "+b+" "+c+" "+d+" "+e+" "
															+f+" "+g+" "+h+" "+i);
												System.out.println();
											}
										}
									}
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

