import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class E_P1915_biggestSquare {

	static int[][] rect, dp;
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		rect = new int[n+1][m+1];
		dp = new int[n+1][m+1];
		for (int i = 1; i <= n; i++) {
			String[] s = br.readLine().split("");
			for (int j = 1; j <= m; j++) {
				rect[i][j] = Integer.parseInt(s[j-1]);
			}
		}

		int max = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (rect[i][j] == 0) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
					if (dp[i][j] > max) {
						max = dp[i][j];
					}
				}
			}
		}
		
		System.out.println(max*max);
	}
}
