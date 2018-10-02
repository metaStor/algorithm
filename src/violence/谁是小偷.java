package violence;

/**
 * 警察抓住A B C D四名罪犯，其中一人是小偷。
 * 
 * 审问,A说：我不是小偷。B说C是小偷。C说小偷肯定是D。D说C在冤枉人。
 * 
 * 现在已经知道四个人中三人说的是真话，一个人说假话，问小偷到底是谁？
 *
 */
public class 谁是小偷 {

	public static void main(String [] args){
		//1是小偷，0不是小偷
		for(int a=0;a<=1;a++){
			for(int b=0;b<=1;b++){
				for(int c=0;c<=1;c++){
					for(int d=0;d<=1;d++){
						//有一个是小偷
						if(a+b+c+d==1){
							//3个人说真话
							int count = 0;
							if(a==0) count++;
							if(c==1) count++;
							if(d==1) count++;
							if(d==0) count++;
							if(count == 3){
								System.out.println(a+" "+b+" "+c+" "+d);
							}
						}
					}
				}
			}
		}
	}
}
