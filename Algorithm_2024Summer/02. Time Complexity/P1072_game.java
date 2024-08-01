import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1072_game {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int X = Integer.parseInt(input[0]);
		int Y = Integer.parseInt(input[1]);
		// 승률 - % 단위
		int percent = (int) (((double) Y * 100) / X);

		if (percent >= 99) {
			System.out.println(-1);
			return;
		}

		int low = 1;
		int high = X;
		int mid;
		int res = -1;
		while (low <= high) {
			mid = (low + high) / 2;
			// System.out.println(mid);
			int changedPercent = (int) ((((double) Y + mid) * 100) / (X + mid));
			if (percent < changedPercent) {
				res = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		System.out.println(res);
	}

}
