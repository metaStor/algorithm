package violence;

/**
 * 三羊献瑞

观察下面的加法算式：

            祥 瑞 生 辉
  +         三 羊 献 瑞
   -------------------
         三 羊 生 瑞 气

其中，相同的汉字代表相同的数字，不同的汉字代表不同的数字。

请你填写“三羊献瑞”所代表的4位数字（答案唯一），不要填写任何多余内容。
 *
 */
public class 三羊献瑞 {

	public static void main(String [] args){

		for(int a=1;a<=9;a++){//首位不能为0		
			for(int b=0;b<=9;b++){
				if(a==b) continue;
				for(int c=0;c<=9;c++){
					if(c==a || b==c) continue;
					for(int d=0;d<=9;d++){
						if(a==d || d==b || d==c ) continue;
						for(int e=1;e<=9;e++){//首位不能为0
							if(a==e || e==b || e==c || e==d) continue;
							for(int f=0;f<=9;f++){
								if(a==f || f==b || f==c || f==d || f==e) continue;
								for(int g=0;g<=9;g++){
									if(a==g || g==b || g==c || g==d || g==e || g==f) continue;
									for(int h=0;h<=9;h++){
										if(a==h || h==b || h==c || h==d || h==e || h==f || h==g) continue;	
										if((a*1000+b*100+c*10+d+e*1000+f*100+g*10+b)
												== (e*10000+f*1000+c*100+b*10+h)){
											System.out.print(e+" "+f+" "+g+" "+b);
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

