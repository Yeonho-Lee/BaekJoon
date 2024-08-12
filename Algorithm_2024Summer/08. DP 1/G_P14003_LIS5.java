import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class G_P14003_LIS5 {
	static int N;
	static int[] input, dp, trace;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		input = new int [N];
		dp = new int [N];
		trace = new int [N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int lastIndex = 0;
		dp[0] = input[0];
		for (int i = 1; i < input.length; i++) {
			// dp테이블의 가장 큰 값 (마지막 값)보다 큰 값이 들어오면 뒤에 붙여준다.
			if (input[i] > dp[lastIndex]) {
				dp[++lastIndex] = input[i];
				trace[i] = lastIndex;
			} else {
//				System.out.println(Arrays.toString(input));
//				System.out.println(Arrays.toString(dp));
//				System.out.println(Arrays.toString(trace));
				
				// input[i] 값의 lowerbound의 dp에서의 index를 찾아 input[i]로 바꿔준다
				trace[i] = lowerBoundIndex(lastIndex, input[i]);
				dp[lowerBoundIndex(lastIndex, input[i])] = input[i];
			}
		}
		
		int index = lastIndex;
		for (int i = N-1; i >= 0; i--) {
			if (index == -1)break;
			if (trace[i] == index) {
				dp[index--] = input[i];
			}
		}
		bw.write(lastIndex+1 + "\n");
		for (int i = 0; i < lastIndex + 1; i++) {
			bw.write(dp[i]+" ");
		}
		bw.flush();
		bw.close();
		br.close();
			
		
	}
	
	static int lowerBoundIndex(int lastIndex, int target) {
		int mid;
		int left = 0;
		int right = lastIndex;
		while (left < right) {
			mid = (left + right) / 2;
			if (dp[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
}

