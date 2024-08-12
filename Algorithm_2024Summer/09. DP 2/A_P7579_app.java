import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_P7579_app {

	static int N, Mem;
	static int[] mem;
	static int[] cost;
	static long[][] dp;
	static final int COST = 10000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Mem = Integer.parseInt(st.nextToken());

		mem = new int[N + 1];
		cost = new int[N + 1];
		dp = new long[N + 1][COST + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			mem[i + 1] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i + 1] = Integer.parseInt(st.nextToken());
		}

		for (int c = 0; c <= COST; c++) {
			for (int i = 1; i <= N; i++) {
				if (cost[i] >c) {
					dp[i][c] = dp[i-1][c];
				} else {
					dp[i][c] = Math.max(dp[i-1][c-cost[i]] + mem[i], dp[i-1][c]);
				}
			}
		}
		for (int i = 0; i <= COST; i++) {
			if (dp[N][i] >= Mem) {
				System.out.println(i);
				return;
			}
		}
	}
}
