import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_P1932_integerTriangle {

	static int n;
	static int[][] triangle, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		triangle = new int[n][n + 1];
		dp = new int[n][n + 1];

		StringTokenizer st;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		for (int k = 0; k < n; k++) {
//			System.out.println(Arrays.toString(triangle[k]));
//		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n+1; j++) {
				if (i == 0) {
					dp[i][j] = triangle[i][j];
				} else if (j == 0) {
					dp[i][j] = dp[i-1][j] + triangle[i][j];
				} else if (j == i) {
					dp[i][j] = dp[i-1][j-1] + triangle[i][j];
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j];
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i <= n; i++) {
			if (dp[n-1][i] > max) {
				max = dp[n-1][i];
			}
		}
		
		System.out.println(max);
		
	}
}
