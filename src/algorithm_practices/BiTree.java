package algorithm_practices;

public class BiTree {

	int num;
	BiTree right;
	BiTree left;
	
	public BiTree(int num){
		this.num = num;
	}
	public void add(BiTree br){
		if(br.num < num){
			if(left == null)
				left = br;
			else
				left.add(br);
		}
		else{
			if(right == null)
				right = br;
			else
				right.add(br);
		}
	}
	public void Firstshow(){
		System.out.println(num);
		if(left!=null) left.Firstshow();
		if(right!=null) right.Firstshow();	
	}
	public int deep(){
		int hight = 1;
		
		int l = left == null ? 0 : left.deep();
		int r = right == null ? 0 : right.deep();
		
		return hight + Math.max(l, r);
	}
	public static void main(String [] args){
		
		BiTree br = new BiTree(50);
		br.add(new BiTree(40));
		br.add(new BiTree(60));
		br.add(new BiTree(30));
		br.add(new BiTree(80));
		br.add(new BiTree(70));
		br.Firstshow();
		System.out.println(br.deep());
	}
}
