import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P2517_running {

	static int num;
	static int[] tree;
	static Runner[] Runners;
	static int S;
	static int[]  finalResult;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input + get ready for the calculation
		num = Integer.parseInt(br.readLine());
		Runners = new Runner[num];
		S = 1;
		while (S < num) {
			S *= 2;
		}
		tree = new int[S * 2];
		finalResult = new int [num];
		for (int i = 0; i < num; i++) { // i+1 이 순서
			int input = Integer.parseInt(br.readLine());
			Runners[i] = new Runner(input, i + 1);
		}
		Arrays.sort(Runners, (o1, o2) -> o1.speed - o2.speed);
		
		// System.out.println(Arrays.toString(Runners));
		init();
		for (int i = 0; i < num; i++) {
			update(1, S, 1, Runners[i].ranking);
			int result = Runners[i].ranking - query(1, S, 1, 1, Runners[i].ranking - 1);
			finalResult[Runners[i].ranking-1] = result;
		}
		for (int i = 0; i < num; i++) {
			bw.write(finalResult[i]+ "\n");
		}
		bw.flush();
		bw.close();
		br.close();

	}

	static void init() {
		for (int i = 0; i < tree.length; i++) {
			tree[i] = 0;
		}
	}

	static int query(int left, int right, int node, int queryLeft, int queryRight) {
		if (right < queryLeft || queryRight < left) {
			// 상관 없음
			return 0;
		} else if (queryLeft <= left && right <= queryRight) {
			// 쏙 들어감
			return tree[node];
		} else {
			int mid = (left + right)/2;
			return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

	static void update(int left, int right, int node, int target) {
		if (left <= target && target <= right) {
			tree[node] ++;
			if (left != right) {
				int mid = (left + right)/2;
				update(left, mid, node * 2, target);
				update(mid + 1, right, node * 2 + 1, target);
			}
		}
	}

}

class Runner {
	int speed;
	int ranking;

	public Runner(int speed, int ranking) {
		this.speed = speed;
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "Runner [speed=" + speed + ", ranking=" + ranking + "]";
	}

	public int getSpeed() {
		return speed;
	}

	public int getRanking() {
		return ranking;
	}
}
