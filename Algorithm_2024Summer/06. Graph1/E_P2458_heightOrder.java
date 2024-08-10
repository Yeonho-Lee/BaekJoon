import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E_P2458_heightOrder {

	static int N, M;
	static int[][] taller;
	static final int INF = 10000_0000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		taller = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(taller[i], INF);
			taller[i][i] = 0;
		}
		
		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			taller[a][b] = 1;
		}
		
		floydWarshall();
		
		
		
		System.out.println(countStudents());
	}
	
	static int countStudents() {
		int students = 0;
		for (int i = 1; i <= N; i++) {
			boolean knows = true;
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				if (taller[i][j] == INF && taller[j][i] == INF) {
					knows = false;
					break;
				}
			}
			if (knows) {
				students ++;
			}
		}
		return students;
	}
	
	static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (taller[i][k] == INF || taller[k][j] == INF) {
						continue;
					}
					taller[i][j] = 1;
				}
			}
		}
	}
}

