import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P10845_queue {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		_Queue queue = new _Queue();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String request = st.nextToken();
			if (request.equals("push")) {
				int input = Integer.parseInt(st.nextToken());
				queue.push(input);
			} else if (request.equals("pop")) {
				bw.write(queue.pop() + "\n");
			} else if (request.equals("size")) {
				bw.write(queue.size() + "\n");
			} else if (request.equals("empty")) {
				bw.write(queue.isEmpty() + "\n");
			} else if (request.equals("front")) {
				bw.write(queue.front() + "\n");
			} else if (request.equals("back")) {
				bw.write(queue.back() + "\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

}

class _Queue {
	LinkedList<Integer> list;

	public _Queue() {
		list = new LinkedList<Integer>();
	}

	public void push(int val) {
		list.add(val);
	}

	public int pop() {
		if (list.size() == 0)
			return -1;
		int val = list.poll();
		return val;
	}

	public int size() {
		return list.size();
	}

	public int isEmpty() {
		if (list.size() == 0)
			return 1;
		return 0;
	}

	public int front() {
		if (list.size() == 0)
			return -1;
		return list.get(0);
	}
	
	public int back() {
		if (list.size() == 0) {
			return -1;
		}
		return list.get(list.size()-1);
	}
}