import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G_P3830_professorDoesntWait {

	static int N, M;
	static int[] parent;
	static int[] weightDiff;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		while (!(N == 0 && M == 0)) {
			parent = new int[N + 1];
			weightDiff = new int[N + 1]; //  i - root
			init();		

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				String work = st.nextToken();
				if (work.equals("!")) {
					// 무게를 재는 일
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					int w = Integer.parseInt(st.nextToken());
					union(a, b, w);
				} else {
					// 교수님께 대답
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					int diff = findDiff(a, b);
					if (diff == Integer.MAX_VALUE) {
						sb.append("UNKNOWN").append("\n");						
					} else {
						sb.append(diff).append("\n");
					}
				}
			}

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(sb.toString());
	}
	
	static int findDiff(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) {
			return Integer.MAX_VALUE;
		} else {
			return weightDiff[b] - weightDiff[a];
		}
	}
	
	static void init () {
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
	}
	
	static void union (int a, int b, int w) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		parent[bRoot] = aRoot;
		weightDiff[bRoot] = w + weightDiff[a]- weightDiff[b];
	}
	
	static int find (int a) {
		if (parent[a] != a) {
			int pa = parent[a];
			parent[a] = find(pa);
			weightDiff[a] += weightDiff[pa];
		}
		return parent[a];
	}
}
