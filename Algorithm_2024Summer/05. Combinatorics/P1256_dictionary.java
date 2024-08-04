import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1256_dictionary {
	// 파스칼 삼각형 그릴 떄, K보다 큰 값? 그냥 K로 치환해버리자.
	static int[][] c;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n= Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		int N = n+m;
		c = new int[N+1][N+1];
		for (int j = 0; j <= N; j++) {
			for (int i = j; i <= N; i++) {
				if (j == 0) {
					c[i][j] = 1;
				} else if (i == j) {
					c[i][j] = 1;
				} else {
					c[i][j] = Math.min((int)1e9, c[i-1][j-1] + c[i-1][j]);					
				}
			}
		}
		
		int total = N;
		int z = m, a = n, where = k;
		if (c[total][z] < where) {
			System.out.println(-1);
			return;
		}
		for (int i = 1; i <= N; i++) {
			
			if (a == 0) {
				sb.append("z");
			} else if (z == 0) {
				sb.append("a");
			} else {
				int combinationA = c[total-1][a-1];
				if (combinationA >= where) {
					total --;	
					sb.append("a");
					a--;
				} else {
					where -= combinationA;
					z --;
					total --;
					sb.append("z");
				}
			}
		}
		System.out.println(sb.toString());
	}
	
//	static int combination (int n, int r) {
//		if (n == r || r == 0) {
//			return 1;
//		} else if (dp[n][r] != 0) {
//			return dp[n][r];
//		} else {
//			return dp[n][r] = Math.min((int)1e9, combination(n-1, r-1) + combination(n-1, r-1));
//		}
//	}
}
