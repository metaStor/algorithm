package bfs;

import java.util.*;

/**
 * ���⣺��Խ����

X�ǵ�̹��ս������֣������뽻��ش�Խ�������������͸��������������ܱ���������ת�����򽫱��ϡ�
ĳ̹����Ҫ��A����B��ȥ��A��B�������ǰ�ȫ����û���������������������������߲���·����̣�

��֪�ĵ�ͼ��һ��������������ĸ�����A��B�������������������Ż򸺺ŷֱ��ʾ����������������
���磺
A + - + -
- + - - +
- + + + -
+ - + - +
B + - + -

̹�˳�ֻ��ˮƽ��ֱ�������ƶ������ڵ�����

���ݸ�ʽҪ��

�����һ����һ������n����ʾ����Ĵ�С�� 4<=n<100
��������n�У�ÿ����n�����ݣ�������A��B��+��-�е�ĳһ�����м��ÿո�ֿ���
A��B��ֻ����һ�Ρ�

Ҫ�����һ����������ʾ̹�˴�A����B���������ƶ�������
���û�з����������-1

���磺
�û����룺
5
A + - + -
- + - - +
- + + + -
+ - + - +
B + - + -

�����Ӧ�������
10
 *
 */

public class 穿越雷区 {
	
	static char [][] map;
	static boolean [][] vis;
	static int [] count;
	static int [] path;
	static int [] dir_x = {1, 0, -1, 0};//�����ƶ�
	static int [] dir_y = {0, 1, 0, -1};//�����ƶ�
	//��ʼ
	public static void init(int N){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				vis[i][j] = false;
			}
		}
	}
	//Ѱ��A��
	public static int find_A(int N){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(map[i][j]=='A')
					return i*N+j;
			}
		}
		return 0;
	}
	//Ѱ��B��
	public static int find_B(int N){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(map[i][j]=='B')
					return i*N+j;
			}
		}
		return 0;
	}
	//�ж�������
	public static boolean judge(int N, int p, int np){//pΪ�ƶ�ǰ�ĵ㣬npΪ�ƶ���ĵ�
		char re_pos = map[p/N][p%N];
		char cur_pos = map[np/N][np%N];
		if(re_pos=='+' && cur_pos=='-'
				|| re_pos=='-' && cur_pos=='+'){
			return true;
		}
		if(re_pos=='A' && (cur_pos=='+' || cur_pos=='-')){
			return true;
		}
		if(cur_pos=='B'){
			return true;
		}
		return false;
	}
	//��ӡ·��
	public static void show(int N){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(vis[i][j])
					System.out.print(map[i][j]+" ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
	}
	public static void bfs(int N, int A, int B) {
		
		Queue <Integer> q = new LinkedList <Integer>();
		boolean flag = false;
		int p = 0;//�ƶ�ǰ������
		int np = 0;//�ƶ��������
		q.add(A);//������
		while(!q.isEmpty()){
			p = q.poll();
			int x = p/N;
			int y = p%N;
			for(int i=0;i<4;i++){
				int nx = x + dir_x[i];
				int ny = y + dir_y[i];
				np = nx*N+ny;
				if(nx>=0 && nx<N && ny>=0 && ny<N
						&& !vis[nx][ny] && judge(N, p, np)){
					vis[nx][ny] = true;
					count[np] = count[p] + 1;
					path[np] = p;
					if(np == B){
						flag = true;
						break;
					}
					q.offer(np);
				}
			}
			if(flag)
				break;
		}
		if(flag){
			init(N);
			while(true){
				vis[np/N][np%N] = true;
				if(np == A) break;
				np = path[np];
			}
			show(N);
		}
		System.out.println("�ܲ�����"+(flag ? count[B] : -1));
	}
	
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		//��ʼ������
		map = new char [N][N];
		vis = new boolean [N][N];
		count = new int [N*N];
		path = new int [N*N];
		//����
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				String temp = input.next();
				map[i][j]=temp.charAt(0);
			}
		}
		int A = find_A(N);
		int B = find_B(N);
		bfs(N, A, B);
		input.close();
	}

}

