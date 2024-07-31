import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P1655_sayTheMid {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> right = new PriorityQueue<>(Comparator.naturalOrder());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			// add to queues
			if (left.size() > right.size()) {
				// right++
				if (left.size() != 0 && left.peek() > input) {
					// left -> right, left ++
					int move = left.poll();
					right.add(move);
					left.add(input);
				} else {
					right.add(input);
				}
			} else {				
				if (right.size() != 0 && right.peek() < input) {
					// right -> left, right++
					int move = right.poll();
					left.add(move);
					right.add(input);
				} else {
					left.add(input);
				}
			}
			// say the mid!
			if (left.size() >= right.size()) {
				bw.write(left.peek()+ "\n");
			} else {
				bw.write(right.peek()+ "\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
