import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

// The main method must be in a class named "Main".
public class P1920_findingNumber {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Set<Integer> set = new HashSet<Integer>();
        
        int N = Integer.parseInt(br.readLine());
        int[] numbersN = new int[N];     
        String[] inputN = br.readLine().trim().split(" ");
        for (int i = 0; i < N; i++) {
        	numbersN[i] = Integer.parseInt(inputN[i]);
        	set.add(numbersN[i]);
        }
        int M = Integer.parseInt(br.readLine());
        int[] numbersM = new int[M];
        String[] inputM = br.readLine().trim().split(" ");
        for (int i = 0; i < M; i++) {
        	numbersM[i] = Integer.parseInt(inputM[i]);
        	if (set.contains(numbersM[i])) {
        		bw.write("1\n");
        	} else {
        		bw.write("0\n");
        	}
        }    
        bw.flush();
        bw.close();
        br.close();
    }
}