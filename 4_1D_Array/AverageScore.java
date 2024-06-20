import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AverageScore {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            int N = Integer.parseInt(br.readLine());
            String [] input = br.readLine().split(" ");
            int [] scores = new int[N];
            for(int i=0; i<N; i++){
                scores[i] = Integer.parseInt(input[i]);
            }
            int max = scores[0];
            for(int i=1; i<N; i++){
                if (scores[i] > max){
                    max = scores[i];
                }
            }
            double sum = 0;
            for(int i=0; i<N; i++){
                sum += (double)scores[i]/max*100;
            }
            bw.write(String.valueOf(sum/N));
            bw.flush();
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
