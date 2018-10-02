package violence;

import java.util.Scanner;

public class 排列数 {
	
	public static boolean check(String temp){
		int sum = 0;
		int number = Integer.parseInt(temp);
		while(number != 0){
			sum += number%10;
			number /= 10;
		}
		return (sum == 45) ? true : false;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int count = 0;//计数
		for(int a=0;a<=9;a++){			
			for(int b=0;b<=9;b++){
				if(a==b) continue;
				for(int c=0;c<=9;c++){
					if(c==a || b==c) continue;
					for(int d=0;d<=9;d++){
						if(a==d || d==b || d==c ) continue;
						for(int e=0;e<=9;e++){
							if(a==e || e==b || e==c || e==d) continue;
							for(int f=0;f<=9;f++){
								if(a==f || f==b || f==c || f==d || f==e) continue;
								for(int g=0;g<=9;g++){
									if(a==g || g==b || g==c || g==d || g==e || g==f) continue;
									for(int h=0;h<=9;h++){
										if(a==h || h==b || h==c || h==d || h==e || h==f || h==g) continue;	
										for(int i=0;i<=9;i++){
											if(a==i || i==b || i==c || i==d || i==e || i==f || i==g || i==h)
												continue;
											for(int j=0;j<=9;j++){
												if(a==j || j==b || j==c || j==d || j==e || j==f || j==g || j==h || i==j)
													continue;
												String temp = ""+a+b+c+d+e+f+g+h+i+j;
												if(check(temp)){
													count++;
													if(count == n){
														System.out.println(temp);
														System.exit(0);
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
			}
		}
		input.close();
	}
}
