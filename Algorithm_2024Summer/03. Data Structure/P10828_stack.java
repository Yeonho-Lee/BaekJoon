import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P10828_stack {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Stack stack = new Stack();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String request = st.nextToken();
			if (request.equals("push")) {
				int input = Integer.parseInt(st.nextToken());
				stack.push(input);
			} else if (request.equals("pop")) {
				bw.write(stack.pop()+"\n");
			} else if (request.equals("size")) {
				bw.write(stack.size()+"\n");
			} else if (request.equals("empty")) {
				bw.write(stack.isEmpty()+"\n");
			} else if (request.equals("top")) {
				bw.write(stack.top()+"\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}

class Stack {
	List<Integer> list;

	public Stack() {
		list = new ArrayList<>();
	}
	
	public void push(int val) {
		list.add(val);
	}
	
	public int pop() {
		if (list.size() == 0) return -1;
		int val = list.remove(list.size()-1);
		return val;
	}
	
	public int size() {
		return list.size();
	}
	
	public int isEmpty() {
		if (list.size() == 0) return 1;
		return 0;
	}
	
	public int top() {
		if (list.size() == 0) return -1;
		return list.get(list.size()-1);
	}
}
