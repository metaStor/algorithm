package greedy;

import java.util.Scanner;

public class Rank_LED {
								 //0  1  2  3  4  5  6  7  8  9
	static int[] data = new int[] {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
	
	public static int check(int n, int number){
		int sum = 0;
		String temp = String.valueOf(number);
		int len = temp.length();
		if(len != n){
			return -1;
		}
		for(int i=0;i<temp.length();i++){
			int count = Integer.parseInt(String.valueOf(temp.charAt(i)));
			sum += data[count];
		}
		return sum;
	}

	//贪心算法
	public static int greedy(int sum){
		int value = 0;
		boolean lead = false;
		while(true){
			if(sum == 0){
				return value;
			}
			if(sum < 8){
				for(int i=0;i<data.length;i++){
					if(sum == data[i]){
						if(!lead && i == 0) continue;
						else{
							value = value * 10 + i;
							sum -= data[i];
						}
					}
				}
			}
			else{
				int num = sum % 7;
				if(num == 0){
					value = value * 10 + 8;
					sum -= 7;
					lead = true;
				}
				else if(num >= 1 && num <= 2){
					value = value * 10 + 1;
					sum -= 2;
					lead = true;
				}
				
				else if(num >= 3 && num <= 4){
					value = value * 10 + 2;
					sum -= 5;
					lead = true;
				}
				else if(num == 5 && !lead){
					value = value * 10 + 2;
					sum -= 5;
					lead = true;
				}
				else if(num == 5 && lead){
					value = value * 10 + 0;
					sum -= 5;
					lead = true;
				}
				else if(num == 6 && !lead){
					value = value * 10 + 6;
					sum -= 6;
					lead = true;
				}
				else if(num == 6 && lead){
					value = value * 10 + 0;
					sum -= 6;
					lead = true;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		for(int t=1;t<=T;t++){
			int n = input.nextInt();
			int number = input.nextInt();
			int sum = check(n, number);
			System.out.println((sum == -1) ? "Error" : greedy(sum));
		}
		input.close();
	}

}
