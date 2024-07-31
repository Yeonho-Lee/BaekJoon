import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class P1927_minHeap {
	static MinHeap heap = new MinHeap();

	public static void main(String[] args) throws Exception {
		// binary tree exercise
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine().trim());
		for (int i = 1; i < N + 1; i++) {
			// insert
			int val = Integer.parseInt(br.readLine().trim());
			if (val == 0) {
				bw.write(heap.delete() + "\n");
			} else {
				heap.insert(val);
			}
			
		}
		bw.flush();
		bw.close();
		br.close();
	}
}

class MinHeap {
	List<Integer> list;
	
	public MinHeap() {
		list = new ArrayList<>();
		list.add(0);
	}
	
	public void insert (int val) {
		list.add(val);
		
		int current = list.size() - 1;
		int parent = current / 2;
		
		while (true) {
			if (parent == 0 || list.get(parent) <= list.get(current)) {
				break;
			}
			// swap
			int temp = list.get(parent);
			list.set(parent, list.get(current));
			list.set(current,temp);
			
			current = parent;
			parent = current/2;
		}
	}
	
	public int delete() {
		if (list.size() == 1) {
			return 0;
		}
		int top = list.get(1);
		list.set(1, list.get(list.size()-1));
		list.remove(list.size()-1);
		
		int currentPos = 1;
		while(true) {
			int leftPos = currentPos * 2;
			int rightPos = currentPos * 2 + 1;
			
			if (leftPos >= list.size()) {
				break;
			}
			
			int minValue = list.get(leftPos);
			int minPos = leftPos;
			
			if (rightPos < list.size() && minValue > list.get(rightPos)) {
				minValue = list.get(rightPos);
				minPos = rightPos;
			}
			
			if (list.get(currentPos) > minValue) {
				int temp = list.get(currentPos);
				list.set(currentPos, list.get(minPos));
				list.set(minPos, temp);
				currentPos = minPos;
			} else {
				break;
			}
		}
		
		return top;
	}
}