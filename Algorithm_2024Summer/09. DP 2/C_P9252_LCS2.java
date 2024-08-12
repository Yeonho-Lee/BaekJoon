import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C_P9252_LCS2 {

	static int[][] LCS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		LCS = new int[s1.length() + 1][s2.length() + 1];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0 || j == 0) {
					LCS[i][j] = 0;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					LCS[i][j] = LCS[i - 1][j - 1] + 1;
				} else {
					LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
				}
			}
		}
		int i = s1.length();
		int j = s2.length();
		while (i > 0 && j > 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				sb.insert(0, s1.charAt(i - 1));
				i--;
				j--;
			} else if (LCS[i-1][j] > LCS[i][j-1]) {
				i--;
			} else {
				j--;
			}
		}

		System.out.println(LCS[s1.length()][s2.length()]);
		System.out.println(sb);
	}

}
