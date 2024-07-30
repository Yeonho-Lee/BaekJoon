import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P1806_subSum {
    static int[] numbers;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
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
		int min = 100000001;
		while (right < N) {
			if (sum < target) {
				right++;
				sum += numbers[right];
			} else {
				min = Math.min(min, right-left+1);
				sum -= numbers[left];
				left++;
			}
		}
		if (min == 100000001) {
			bw.write("0");
		} else {
			bw.write(min+"");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}