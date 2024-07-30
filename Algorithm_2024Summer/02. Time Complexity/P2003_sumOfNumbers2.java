import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P2003_sumOfNumbers2 {
    static int[] numbers;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int target = Integer.parseInt(input[1]);
		numbers = new int [N+1];
		numbers[N] = 0;
		
		input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(input[i]);
		}
		int left = 0;
		int right = 0;
		int sum = numbers[0];
		int count = 0;
		while (right < N) {
			if (sum < target) {
				right++;
				sum += numbers[right];
			} else if (sum == target) {
				count++;
				right++;
				sum += numbers[right];
			} else { // sum > target
				sum -= numbers[left];
				left++;
			}
		}
		bw.write(count+ "");
		bw.flush();
		bw.close();
		br.close();
	}
}
