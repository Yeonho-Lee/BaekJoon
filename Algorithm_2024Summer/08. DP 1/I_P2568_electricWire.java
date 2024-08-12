import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class I_P2568_electricWire {
	static int N;
	static int[] dp, trace, result;
	static Line[] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st; 

		input = new Line[N];
		dp = new int[N];
		trace = new int[N];
		result = new int[N];
		int a, b;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			input[i] = new Line(a, b);
		}

		Arrays.sort(input);

		int lastIndex = 0;
		dp[0] = input[0].to;
		for (int i = 1; i < input.length; i++) {
			// dp테이블의 가장 큰 값 (마지막 값)보다 큰 값이 들어오면 뒤에 붙여준다.
			if (input[i].to > dp[lastIndex]) {
				dp[++lastIndex] = input[i].to;
				trace[i] = lastIndex;
			} else {
				// input[i] 값의 lowerbound의 dp에서의 index를 찾아 input[i]로 바꿔준다
				trace[i] = lowerBoundIndex(lastIndex, input[i].to);
				dp[lowerBoundIndex(lastIndex, input[i].to)] = input[i].to;
			}
		}	
		Arrays.fill(result, Integer.MAX_VALUE);
		int index = lastIndex;
		int index2 = 0;
		int answer = N - lastIndex - 1;
		for (int i = N - 1; i >= 0; i--) {			
			if (trace[i] == index) {
				index--;
			} else {
				result[index2++] = input[i].from;
			}
		}
		
		Arrays.sort(result);
		bw.write(answer + "\n");
		for (int i = 0; i < answer; i++) {
			bw.write(result[i] + "\n");
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

class Line implements Comparable <Line>{
	int from;
	int to;
	
	public Line(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	@Override
	public String toString() {
		return "l[f:" + from + ", t:" + to + "]";
	}

	@Override
	public int compareTo(Line l0) {
		// TODO Auto-generated method stub
		return this.from - l0.from;
	}	
}
