import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MinMax {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String firstLine = br.readLine().strip();
        int N = Integer.parseInt(firstLine);
        String secondLine = br.readLine().strip();
        String [] input = secondLine.split(" ");
        int temp = Integer.parseInt(input[0]);
        int min= temp;
        int max = temp;
        for (int i = 1; i < N ; i++){
            temp = Integer.parseInt(input[i]);
                if (temp < min){
                    min = temp;
                }
                if (temp > max){
                    max = temp;
                }
        }
        bw.write(min + " " + max);
        bw.flush();
        bw.close();
        br.close();
        
        
    }
}
