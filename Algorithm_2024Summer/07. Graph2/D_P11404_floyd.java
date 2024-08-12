import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D_P11404_floyd {

	static int N, B;
	static int[][] buses;

	static final int INF = 100 * 100_000 + 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		B = Integer.parseInt(br.readLine());
		buses = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(buses[i], INF);
			buses[i][i] = 0;
		}

		int a, b, c;
		for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			buses[a][b] = Math.min(buses[a][b], c);
		}

		floydWarshall();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (buses[i][j] == INF) sb.append("0 ");
				else sb.append(buses[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (buses[i][k] == INF || buses[k][j] == INF) {
						continue;
					}
					buses[i][j] = Math.min(buses[i][j], buses[i][k] + buses[k][j]);
				}
			}
		}
	}
}
