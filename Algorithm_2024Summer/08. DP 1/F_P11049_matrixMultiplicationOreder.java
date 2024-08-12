import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class F_P11049_matrixMultiplicationOreder {

	static int N;
	static int[][] matrix;
	static int[][] dp; // dp i, j는 i에서 j까지 곱했을때의 연산량

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		matrix = new int[N][2];
		dp = new int [N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r, c;
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N - 1; i++) {
			dp[i][i + 1] = matrix[i][0] * matrix[i][1] * matrix[i + 1][1];
			// matrix[i][1] = matrix[i][0]
		}

		// r + 1 - problem size
		for (int size = 2; size < N; size++) {
			for (int i = 0; i < N-size; i++) {
				int j = i + size;
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + (matrix[i][0] * matrix[k][1] * matrix[j][1]));
				}
			}
		}
		
		System.out.println(dp[0][N-1]);
	}
}
