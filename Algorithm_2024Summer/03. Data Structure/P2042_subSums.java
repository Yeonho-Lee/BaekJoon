import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// indexed tree
public class P2042_subSums {
	static int N, M, K;
	static long[] nums;
	static long[] tree;
	static int S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new long[N];
		// N을 담을 수 있는 N보다 크거나 같은 S
		S = 1;
		while (S < N) {
			S *= 2;
		}

		tree = new long[S * 2]; // index 0 not used

		// get the numbers
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		init();

		// M + K times of calculations
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int calType = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (calType == 1) {
				// replace the number
				long diff = c - tree[S+b-1];
				update(1, S, 1, b, diff);
			} else {
				// calculate the sum
				long sum = query(1, S, 1, b, (int)c);		
				bw.write(sum + "\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

	static void init() {
		for (int i = 0; i < N; i++) {
			// leaf 채우기
			tree[S + i] = nums[i];
		}
		for (int i = S - 1; i > 0; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}

	static long query(int left, int right, int node, int queryLeft, int queryRight) {
		if (queryRight < left || right < queryLeft) {
			// 상관 없는 경우,
			return 0;
		} else if (queryLeft <= left && right <= queryRight) {
			// query 에 포함되는 경우
			return tree[node];
		} else {
			int mid = (left + right) / 2;
			return query(left, mid, node * 2, queryLeft, queryRight)
					+ query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}

	}

	static void update(int left, int right, int node, int target, long diff) {
		if (target < left || right < target) {
			// 상관 없는 경우
			return;
		} else {
			// 걸리거나 본인인 경우
			tree[node] += diff;
			if (left != right) {
				int mid = (left + right) / 2;
				update(left, mid, node * 2, target, diff);
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}

	static long queryBU(int queryLeft, int queryRight) {
		int left = S + queryLeft - 1;
		int right = S + queryRight - 1;
		long sum = 0;
		while (left <= right) {
			// 경계가 뒤집어지지 않는 한 실행
			// 이번 레벨에서 걸렸을 경우 연산
			if (left % 2 == 1) {
				// 내가 rightChild라서 내 값을 써야 함.
				sum += tree[left++];
			}
			if (right % 2 == 0) {
				// 내가 leftChild 라서 내 값을 써야 함.
				sum += tree[right++];
			}
			left /= 2;
			right /= 2;
		}
		return sum;
	}

	static void updateBU(int target, long value) {
		int node = S + target - 1;
		tree[node] = value;
		node /= 2;
		while (node > 0) {
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
			node /= 2;
		}
	}
}
