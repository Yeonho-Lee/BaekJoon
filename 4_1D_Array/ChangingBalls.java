import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ChangingBalls {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String [] input = br.readLine().strip().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int [] basket = new int [N];
        for (int i = 0; i < basket.length; i++){
            basket[i] = i+1; 
        }
        for (int i = 0; i < M; i ++){
            input = br.readLine().strip().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int temp = basket[a-1];
            basket[a-1] = basket[b-1];
            basket[b-1] = temp;
        }

        for (int i = 0; i < basket.length; i++){
            bw.write(basket[i]+ " "); 
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
