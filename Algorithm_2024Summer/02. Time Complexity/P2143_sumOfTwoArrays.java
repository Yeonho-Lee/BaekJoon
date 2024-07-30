import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P2143_sumOfTwoArrays {
	static Map<Long, Integer> sumMapA = new HashMap<Long, Integer>();
	static Map<Long, Integer> sumMapB = new HashMap<Long, Integer>();
	static int Target, N, M;
	static int[] A, B;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;
		// read inputs
		Target = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		B = new int [M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		
		// save A subarrays sums
		for (int i = 0; i< N; i++) {
			long sum = 0;
			for (int j = i; j< N; j++) {
				sum += A[j];
                sumMapA.merge(sum, 1, Integer::sum);
			}
			
		}
		// save B subarrays sums
		for (int i = 0; i< M; i++) {
            long sum = 0;
			for (int j = i; j< M; j++) {
                sum += B[j];
                sumMapB.merge(sum, 1, Integer::sum);
			}

		}
		// iterate sumMapA and calculate combinations
		long result = 0;
		for (long sum : sumMapA.keySet()) {
			long bTarget = Target-sum;
			if (sumMapB.containsKey(bTarget)) {
				result += sumMapA.get(sum)*sumMapB.get(bTarget);
			}
		}
		System.out.println(result);
	}
}
