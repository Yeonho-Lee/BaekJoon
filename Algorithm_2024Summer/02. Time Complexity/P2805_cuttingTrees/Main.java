package P2805_cuttingTrees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long TargetLength;
	static int[] trees;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		TargetLength = Long.parseLong(st.nextToken());
		trees = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(trees);
		int start = 0;
		int end = trees[N-1];
		long totalLength = 0;
		int answer = 0;
		while (start <= end) {
			// mid 갱신, result 갱신
			int mid = (start+end)/2;
			totalLength = getTotalLength(mid);
			// targetLength와 length 비교
			if (totalLength > TargetLength) {
				// sum > targetLength? 저장해놓고 start update
				answer = mid;
				start = mid + 1;
			} else if (totalLength == TargetLength) {
				// sum == targetLength? result 저장하고 그냥 break
				answer = mid;
				break;
			} else {
				// sum < targetLength? 그냥 end update
				end = mid-1;
			}
		}
		
		bw.write(answer+"");
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static long getTotalLength(int height) {
		long totalLength = 0;
		for (int idx=0; idx<N; idx++) {
			if (trees[idx] > height) {
				totalLength += trees[idx] - height;
			}
		}
		return totalLength;
	}

}
