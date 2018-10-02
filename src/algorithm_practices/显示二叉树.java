package algorithm_practices;

/**
 * 
标题：显示二叉树

排序二叉树的特征是：
某个节点的左子树的所有节点值都不大于本节点值。
某个节点的右子树的所有节点值都不小于本节点值。

 * 对于上边的测试数据，应该显示出：
                  |
   /--------------500---\
   |                    |
/--200---\           /--509---\
|        |           |        |
100   /--250---\     507   /--600\
      |        |           |     |
      220   /--450         510   650
            |
            440

请分析程序逻辑，填写划线部分缺失的代码。
 *
 */
public class 显示二叉树 {
	
	static class BiTree{
	
		private int v;
		private BiTree l;
		private BiTree r;
	
		public BiTree(int v){
			this.v = v;
		}
		
		public void add(BiTree the){
			if(the.v < v){//如果要添加的节点小于本节点
				if(l==null) l = the;
				else l.add(the);//则添加到左子节点
			}
			else{
				if(r==null) r = the;
				else r.add(the);
			}
		}
		
		public int getHeight(){
			int h = 2;
			int hl = l==null? 0 : l.getHeight();
			int hr = r==null? 0 : r.getHeight();
			return h + Math.max(hl,hr);
		}
		
		public int getWidth(){
			int w = (""+v).length();
			if(l!=null) w += l.getWidth();
			if(r!=null) w += r.getWidth();
			return w;
		}
		
		public void show(){
			char[][] buf = new char[getHeight()][getWidth()];
			printInBuf(buf, 0, 0);
			showBuf(buf);
		}
		
		private void showBuf(char[][] x){
			for(int i=0; i<x.length; i++){
				for(int j=0; j<x[i].length; j++)
					System.out.print(x[i][j]==0? ' ':x[i][j]);
				System.out.println();
			}
		}
		
		private void printInBuf(char[][] buf, int x, int y){
			String sv = "" + v;
			
			int p1 = l==null? x : l.getRootPos(x);
			int p2 = getRootPos(x);
			int p3 = r==null? p2 : r.getRootPos(p2+sv.length());
			
			buf[y][p2] = '|';
			for(int i=p1; i<=p3; i++) buf[y+1][i]='-';
			for(int i=0; i<sv.length(); i++) buf[y+1][i+p2]=sv.charAt(i);	//填空位置
			if(p1<p2) buf[y+1][p1] = '/';
			if(p3>p2) buf[y+1][p3] = '\\';
			
			if(l!=null) l.printInBuf(buf,x,y+2);
			if(r!=null) r.printInBuf(buf,p2+sv.length(),y+2);
		}
		
		private int getRootPos(int x){
			return l==null? x : x + l.getWidth();
		}
	}
	public static void main(String[] args)
	{		
		BiTree tree = new BiTree(500);
		tree.add(new BiTree(200));
		tree.add(new BiTree(509));
		tree.add(new BiTree(100));
		tree.add(new BiTree(250));
		tree.add(new BiTree(507));
		tree.add(new BiTree(600));
		tree.add(new BiTree(650));
		tree.add(new BiTree(450));
		tree.add(new BiTree(510));
		tree.add(new BiTree(440));
		tree.add(new BiTree(220));		
		tree.show();		
	}

}
