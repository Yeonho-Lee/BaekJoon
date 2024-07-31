import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1339_wordMath {

	static int[] weight = new int [26];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] strings = new String[N];
		for (int i = 0; i < N; i++) {
			strings[i] = br.readLine().trim();
			for (int index = 0; index < strings[i].length(); index++){
				int c = strings[i].charAt(index) - 'A';
				weight[c] += Math.pow(10, strings[i].length() - index - 1);
			}
		}
		
		//Arrays.sort(weight, (x, y) -> y - x);
		Arrays.sort(weight);
		
		long result = 0;
		for (int i = 0; i < 10; i++) {
			result += weight[25-i]*(9-i);
		}
		System.out.println(result);
	}

}
