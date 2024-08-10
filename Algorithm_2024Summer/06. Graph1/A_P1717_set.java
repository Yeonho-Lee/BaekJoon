import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_P1717_set {

	static int n, testCases;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		// 0 이면 합집합, 1이면 부모 비교
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		testCases = Integer.parseInt(st.nextToken());
		parent = new int [n+1];
		
		for (int i= 0; i <=n; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < testCases; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (op == 0) {
				// 0 - union a, b
				union(a, b);
			} else {
				// 1 - a, b in same set?
				boolean inSameSet = (findParent(a) == findParent(b));
				if (inSameSet) {
					sb.append("YES").append("\n");					
				} else {
					sb.append("NO").append("\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
	
	static void union (int a, int b) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		parent[aRoot] = bRoot;
	}
	
	static int findParent(int a) {
		if (parent[a] == a) return a;
		else return parent[a] = findParent(parent[a]);
	}

}
