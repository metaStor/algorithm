package union_find_sets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/*
 * 给定一个帐户列表，每个元素accounts [i]是一个字符串列表，其中第一个元素accounts [i] [0]是账户名称，其余元素是这个帐户的电子邮件。
 * 现在，我们想合并这些帐户。
 * 如果两个帐户有相同的电子邮件地址，则这两个帐户肯定属于同一个人。
 * 请注意，即使两个帐户具有相同的名称，它们也可能属于不同的人，因为两个不同的人可能会使用相同的名称。
 * 一个人可以拥有任意数量的账户，但他的所有帐户肯定具有相同的名称。
 * 合并帐户后，按以下格式返回帐户：每个帐户的第一个元素是名称，其余元素是按字典序排序后的电子邮件。帐户本身可以按任何顺序返回。
 * 账户个数在1~1000之间
 * 每个账户下的电子邮件在1~10之间
 * 每个字符串的长度在1~30之间
 */
public class 账户合并 {
	
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        // write your code here
    	if (accounts == null) return null;
    	if (accounts.size() == 0) return new ArrayList<List<String>>();
		// initialize
    	Map<String, Integer> id = new HashMap<>(); // email是唯一的,name不一定
    	int[] pre = new int[accounts.size()];
    	for (int i = 0; i < pre.length; i++) pre[i] = i; // 用每个账户的index作为节点
		for (int i = 0; i < accounts.size(); i++) {
			List<String> em = accounts.get(i);
			// em : name, email_1, email_2...
			for (int j = 1; j < em.size(); ++j) {
				String email = em.get(j);
				if (!id.containsKey(email)) id.put(email, i);
				// union
				else union(pre, i, id.get(email)); // 当前的i作为子节点加入到已存在的之前的id上
			}
		}
		// 根据已经合并好的index进行email归类, TreeSet自带红树性质(最大堆)
		Map<Integer, TreeSet<String>> users = new HashMap<>();
		for (int i = 0; i < pre.length; i++) {
			int root = find(pre, i); // get root
			users.putIfAbsent(root, new TreeSet<>());
			// 将当前账户的email都加到root上(start with 1, 0 is name)
			users.get(root).addAll(accounts.get(i).subList(1, accounts.get(i).size()));
		}
		// Add into res
		List<List<String>> res = new ArrayList<>();
		for (int user : users.keySet()) {
			List<String> emails = new ArrayList<>(users.get(user));
			emails.add(0, accounts.get(user).get(0)); // insert name into 0 index
			res.add(emails);
		}
		return res;
    }
    
    public static int find(int[] pre, int x) {
    	int r = x;
    	while (r != pre[r]) {
			r = pre[r];
		}
    	// 路径压缩
    	int i = x, j;
    	while (pre[i] != r) {
			j = pre[i];
			pre[i] = r; 
			i = j;
		}
    	return r;
    }
    
    public static void union(int[] pre, int x, int y) {
    	int a = find(pre, x);
    	int b = find(pre, y);
    	if (a != b) pre[a] = b; // b is root
    }

	/* ------------------ limit time -------------------------------
	// 必须已email为中心，因为name可能重复
	static Map<String, String> pre; // store email
	static Map<String, String> owner; // store name of email
	static Map<String, List<String>> res; // store result which processed
	
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        // write your code here
    	if (accounts == null) return null;
    	if (accounts.size() == 0) return new ArrayList<List<String>>();
		pre = new HashMap<>();
		owner = new HashMap<>();
    	res = new HashMap<>();
		// initialize
		for (int i = 0; i < accounts.size(); i++) {
			List<String> em = accounts.get(i);
			// em : name, email_1, email_2...
			for (int j = 1; j < em.size(); ++j) {
				owner.put(em.get(j), em.get(0));
				pre.put(em.get(j), em.get(j)); // keep self
			}
		}
//		System.out.println(pre.toString() + "\n" + owner.toString());
		// 将每个账户下的第一个email作为root
		for (int i = 0; i < accounts.size(); i++) {
			List<String> em = accounts.get(i);
			for (int j = 2; j < em.size(); ++j) {
				union(em.get(j), em.get(1)); // let email_1 be root of others emails
			}
		}
//		for (String i : pre.keySet()) System.out.println(pre.get(i) + ": " + i);
		// 将各个树下的集合
		for (int i = 0; i < accounts.size(); i++) {
			List<String> em = accounts.get(i);
			for (int j = 1; j < em.size(); ++j) {
				String root = find(em.get(j));
				res.putIfAbsent(root, new ArrayList<>());
				if (res.get(root).contains(em.get(j))) continue;
				res.get(root).add(em.get(j));
			}
		}
//		for (String key : res.keySet()) System.out.println(key + ": " + res.get(key));
		accounts.clear();
		for (String key : res.keySet()) {
			List<String> values = res.get(key);
			Collections.sort(values);
			List<String> emails = new ArrayList<>();
			emails.add(owner.get(key)); // get name
			emails.addAll(values);
			accounts.add(emails);
		}
    	return accounts;
    }
    
    public static String find(String x) {
//    	if (pre.get(x).equals(x)) return x;
//    	return pre.put(x, find(pre.get(x)));
    	String r = x;
    	while (!pre.get(r).equals(r)) {
			r = pre.get(r);
		}
    	// 路径压缩
    	String i = x, j;
    	while (!pre.get(i).equals(r)) {
			j = pre.get(i);
			pre.put(i, r);
			i = j;
		}
    	return r;
    }
    
    public static void union(String x, String y) {
    	String a = find(x);
    	String b = find(y);
    	if (!a.equals(b)) pre.put(a, b); // b is root
    }
    */
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<String>> accounts = new ArrayList<List<String>>();
		List<String> emails = new ArrayList<>();
		emails.add("David");
		emails.add("David0@m.co");
		emails.add("David1@m.co");
		accounts.add(emails);
		emails = new ArrayList<>();
		emails.add("David");
		emails.add("David3@m.co");
		emails.add("David4@m.co");
		accounts.add(emails);
		emails = new ArrayList<>();
		emails.add("David");
		emails.add("David4@m.co");
		emails.add("David5@m.co");
		accounts.add(emails);
		emails = new ArrayList<>();
		emails.add("David");
		emails.add("David2@m.co");
		emails.add("David3@m.co");
		accounts.add(emails);
		emails = new ArrayList<>();
		emails.add("David");
		emails.add("David1@m.co");
		emails.add("David2@m.co");
		accounts.add(emails);
		accountsMerge(accounts);
		for (List<String> account : accounts) System.out.println(account.toString());
	}

}
