import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P1275_coffeeShop {

	static int N;
	static int S;
	static int turns;
	static long[] tree;
	static int[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		turns = Integer.parseInt(st.nextToken());
		S = 1;
		while (S < N) {
			S *= 2;
		}
		tree = new long[S * 2];
		input = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		// initialize indexed tree
		init();
		// use tree to get sum
		for (int i = 0; i < turns; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int element = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
			
			int from = Math.min(x, y);
			int to = Math.max(x, y);
			
			bw.write(query(1, S, 1, from, to) + "\n");
			long diff = number - tree[S + element -1];
			update(1, S, 1, element, diff);
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	static void init() {
		for (int i = 0; i < N; i++) {
			tree[S + i] = input[i];
		}
		for (int i = S - 1; i > 0; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}

	static long query(int left, int right, int node, int queryLeft, int queryRight) {
		if (right < queryLeft || queryRight < left) {
			return 0;
		} else if (queryLeft <= left && right <= queryRight){
			return tree[node];
		} else {
			int mid = (left + right)/2;
			return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

	static void update(int left, int right, int node, int target, long diff) {
		if (target < left || right < target) {
			// nothing
		} else {
			tree[node] += diff;
			if (left != right) {
				int mid = (left + right) / 2;
				update(left, mid, node * 2, target, diff);
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}

}

