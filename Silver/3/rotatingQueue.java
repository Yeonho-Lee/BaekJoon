import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class rotatingQueue {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // read N, M from the first line
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        // read M numbers from the second line, and store them in an array
        // indexs of the needed numbers
        String[] numbers = br.readLine().split(" ");
        int[] neededNumbers = new int[M];
        for (int i = 0; i < M; i++) {
            neededNumbers[i] = Integer.parseInt(numbers[i]);
        }

        // create a queue
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        // count the number of operations
        int count = 0;
        for (int i = 0; i < M; i++) {
            int target = neededNumbers[i];
            int index = 0;

            // find the target in the queue and perform operations
            for (Integer num : queue) {
                if (num == target) {
                    break;
                }
                index++;
            }

            // decide the direction to minimize the number of operations
            if (index <= queue.size() / 2) { // left rotation
                for (int j = 0; j < index; j++) {
                    int first = queue.pollFirst();
                    queue.offerLast(first);
                    count++;
                }
                queue.pollFirst(); // remove the target
            } else { // right rotation
                index = queue.size() - index;
                for (int j = 0; j < index; j++) {
                    int last = queue.pollLast();
                    queue.offerFirst(last);
                    count++;
                }
                queue.pollFirst(); // remove the target
            }
        }

        bw.write(Integer.toString(count));
        
        
        bw.write(0);
        
        bw.flush();
        bw.close();
        br.close();
    }
}
