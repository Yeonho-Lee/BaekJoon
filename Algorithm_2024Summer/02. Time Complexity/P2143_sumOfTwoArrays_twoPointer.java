import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2143_sumOfTwoArrays_twoPointer {
	static int Target, N, M;
	static int[] A, B;
	static long[] sumA, sumB;

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
		sumA = new long [(N+1)*N/2];
		sumB = new long [(M+1)*M/2];

		// save A subarrays sums
		int indexA = 0;
		for (int i = 0; i< N; i++) {
			long sum = 0;
			for (int j = i; j< N; j++) {
				sum += A[j];
                sumA[indexA++] = sum;
			}
			
		}
		// save B subarrays sums
		int indexB = 0;
		for (int i = 0; i< M; i++) {
            long sum = 0;
			for (int j = i; j< M; j++) {
                sum += B[j];
                sumB[indexB++] = sum;
			}
		}

		// Sort both arrays
        Arrays.sort(sumA);
		//System.out.println(Arrays.toString(sumA));;
        Arrays.sort(sumB);
		//System.out.println(Arrays.toString(sumB));;

		// Try Combinations & Count
		long result = 0;
		int pointerA = 0;
		int pointerB = sumB.length - 1;
		while (pointerA < sumA.length && pointerB >= 0){
			// update
			long sum = sumA[pointerA] + sumB[pointerB];
			
			if (sum == Target){
				// sum == Target
				// count same A's & same B's
				// add to result countA*countB
				int countA = 1;
				int countB = 1;
				while (pointerA + 1 < sumA.length && sumA[pointerA] == sumA[pointerA+1]){
					countA++;
					pointerA++;
				}
				while (pointerB > 0 && sumB[pointerB] == sumB[pointerB-1]){
					countB++;
					pointerB--;
				}
				
				result += countA * countB;
				pointerA++;
				pointerB--;
			} else if (sum < Target) {
				pointerA++;
			} else {// sum > Target
				pointerB--;
			}
		}
		System.out.println(result);
	}
}
