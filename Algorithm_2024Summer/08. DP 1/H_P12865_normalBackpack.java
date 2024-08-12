import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H_P12865_normalBackpack {

	static int N, K;
	static int [] weight;
	static int [] value;
	static int[][] bag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bag = new int[N + 1][K + 1];
		weight = new int [N + 1];
		value = new int [N + 1];
		
		int w, v;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			weight[i] = w;
			value[i] = v;
		}
		
		for (int k = 0; k <= K; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == 0 || k == 0) { 
					bag[i][k] = 0;
				} else if (weight[i] > k) {
					bag[i][k] = bag[i-1][k];
				} else {
					bag[i][k] = Math.max(bag[i-1][k-weight[i]] + value[i], bag[i-1][k]);
				}
			}
		}
		System.out.println(bag[N][K]);
	}
}
