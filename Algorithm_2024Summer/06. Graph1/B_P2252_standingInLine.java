import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_P2252_standingInLine {
	static int N, M;
	static ArrayList<Integer>[] graph;
	static int inDegree[];
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		inDegree = new int [N + 1];
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b); // in-degree
			inDegree[b]++;
		}

		// step 0 - put the 0 in degrees in queue
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
				inDegree[i]--;
				for (int to : graph[i]) {
					inDegree[to]--;
				}
			}
		}

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			bw.write(curr + " ");
			for (int i = 1; i <= N ; i++) {
				if (inDegree[i] == 0) {
					queue.add(i);
					inDegree[i]--;
					for (int to : graph[i]) {
						inDegree[to]--;
					}
				}
			}
		}
		bw.flush();
		bw.close();
		br.close();

	}
}
