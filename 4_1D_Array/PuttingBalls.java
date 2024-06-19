import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PuttingBalls {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String [] input = br.readLine().strip().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int[] result = new int [N];

        for (int i = 0; i < M; i++){
            input = br.readLine().strip().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            int ballNumb = Integer.parseInt(input[2]);
            for (int j = start; j <= end; j++){
                result[j-1] = ballNumb;
            }
        }
        for (int i = 0; i < N; i ++){
            bw.write(result[i] + " ");
        }        
        bw.flush();
        bw.close();
        br.close();
    }
}
