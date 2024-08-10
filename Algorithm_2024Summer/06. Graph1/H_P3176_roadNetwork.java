import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Info{
	int lca;
	int shortest;
	int longest;
	
	public Info(int lca, int shortest, int longest) {
		this.lca = lca;
		this.shortest = shortest;
		this.longest = longest;
	}
	
	@Override
	public String toString() {
		return "Info [lca=" + lca + ", shortest=" + shortest + ", longest=" + longest + "]";
	}
}

public class H_P3176_roadNetwork {

	static int N, M;
	static ArrayList<int[]>[] graph;
	static boolean[] visited;
	static int[] depth;
	static int[][] parent, shortest, longest;
	static int LOG;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		LOG = 1;
		while (1 << LOG < N) {
			LOG++;
		}
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		parent = new int[LOG + 1][N + 1];
		shortest = new int[LOG + 1][N + 1];
		longest = new int[LOG + 1][N + 1];
		graph = new ArrayList[N + 1];

		// -------------------------
		// STEP 0 - input to graph
		// -------------------------
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		StringTokenizer st;
		int n1, n2, length;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			length = Integer.parseInt(st.nextToken());
			// 양방향 간선 - 무향간선이기 때문에
			graph[n1].add(new int []{n2, length});
			graph[n2].add(new int []{n1, length});
		}

		// ----------------------------------------
		// STEP 1 - update parent[0][V] to all V
		// ----------------------------------------
		bfs(1);

		// -------------------------
		// STEP 2 - find ancestors
		// -------------------------
		findAncestors();
		// -------------------------
		// STEP 3 - find LCA
		// -------------------------
		M = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		int a, b;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			Info info = lca(a, b);
			sb.append(info.shortest).append(" ").append(info.longest).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void bfs(int root) {
		Queue<Integer> queue = new LinkedList<>();
		// root: 1번 노드로 정함.
		queue.add(root);
		depth[root] = 0;
		visited[root] = true;

		while (!queue.isEmpty()) {
			int currentNode = queue.poll();

			for (int[] next : graph[currentNode]) {
				int nextNode = next[0];
				int nextLength = next[1];
				if (!visited[nextNode]) {
					visited[nextNode] = true;
					parent[0][nextNode] = currentNode;
					shortest[0][nextNode] = nextLength;
					longest[0][nextNode] = nextLength;
					depth[nextNode] = depth[currentNode] + 1;
					queue.add(nextNode);
				}
			}
		}
	}

	static void findAncestors() {
		for (int k = 1; k <= LOG; k++) {
			for (int v = 1; v <= N; v++) {
				parent[k][v] = parent[k - 1][parent[k - 1][v]];
				shortest[k][v] = Math.min(shortest[k - 1][v], shortest[k - 1][parent[k - 1][v]]);
				longest[k][v] = Math.max(longest[k - 1][v], longest[k - 1][parent[k - 1][v]]);
			}
		}
	}

	static Info lca(int a, int b) {
		// 0. 항상 b 가 a 보다 depth가 크도록 swap
		int ta = a;
		int tb = b;
		int s = Integer.MAX_VALUE;
		int l = Integer.MIN_VALUE;
		if (depth[ta] > depth[tb]) {
			int temp = ta;
			ta = tb;
			tb = temp;
		}

		// 1. a, b의 depth 맞추기 - 항상 b를 끌어올리면 된다
		for (int k = LOG; k >= 0; k--) {
			// if depth의 차이 >= 2^k -> b끌어올림
			if (depth[tb] - depth[ta] >= (1 << k)) {
				s = Math.min(s, shortest[k][tb]);
				l = Math.max(l, longest[k][tb]);
				tb = parent[k][tb];
			}
		}

		// 2. depth를 맞추었는데 같다면, LCA를 찾은 것
		// LUCKY
		if (ta == tb) {
			return new Info(ta, s, l);
		}

		// 3. a와 b를 같이 끌어올리면서 처음으로 조상이 같지 않은 지점까지 이동
		// parent[0][a] != parent[0][b]
		// a와 b의 조상이 같으면 k --
		// a와 b의 조상이 같지 않으면 a와 b를 2^k만큼 끌어올림
		for (int k = LOG; k >= 0; k--) {
			if (parent[k][ta] != parent[k][tb]) {
				s = Math.min(s, Math.min(shortest[k][ta], shortest[k][tb]));
				l = Math.max(l, Math.max(longest[k][ta], longest[k][tb]));
				ta = parent[k][ta];
				tb = parent[k][tb];
			}
		}

		// 4. 3이 parent [k][a] == parent[k][b] 인 곳에서 끝났으므로 얘가 LCA
		s = Math.min(s, Math.min(shortest[0][ta], shortest[0][tb]));
		l = Math.max(l, Math.max(longest[0][ta], longest[0][tb]));
		return new Info(parent[0][a], s, l);
	}
}
