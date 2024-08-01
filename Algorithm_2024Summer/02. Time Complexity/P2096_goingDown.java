import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P2096_goingDown {

	static int[][] map;
	static int N;
	static int[][] highScore;
	static int[][] lowScore;

	public static void main(String[] args) throws Exception {
		// inputs
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][3];
		highScore = new int[N][3];
		lowScore = new int[N][3];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().trim().split(" ");
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		// update scores
		// 1st row
		for (int j = 0; j < 3; j++) {
			highScore[0][j] = map[0][j];
			lowScore[0][j] = map[0][j];
		}
		// 2nd row to the last row
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				int lastRowMax = highScore[i - 1][1];
				int lastRowMin = lowScore[i - 1][1];
				if (j == 0) {
					lastRowMax = Math.max(lastRowMax, highScore[i - 1][0]);
					lastRowMin = Math.min(lastRowMin, lowScore[i - 1][0]);
				} else if (j == 1) {
					lastRowMax = Math.max(lastRowMax, highScore[i - 1][0]);
					lastRowMax = Math.max(lastRowMax, highScore[i - 1][2]);
					lastRowMin = Math.min(lastRowMin, lowScore[i - 1][0]);
					lastRowMin = Math.min(lastRowMin, lowScore[i - 1][2]);
				} else { // j == 2
					lastRowMax = Math.max(lastRowMax, highScore[i - 1][2]);
					lastRowMin = Math.min(lastRowMin, lowScore[i - 1][2]);
				}
				highScore[i][j] = map[i][j] + lastRowMax;
				lowScore[i][j] = map[i][j] + lastRowMin;
			}
		}
		int maxScore = Math.max(highScore[N - 1][0], highScore[N - 1][1]);
		maxScore = Math.max(maxScore, highScore[N - 1][2]);
		int minScore = Math.min(lowScore[N - 1][0], lowScore[N - 1][1]);
		minScore = Math.min(minScore, lowScore[N - 1][2]);

		System.out.println(maxScore + " " + minScore);
	}
}
