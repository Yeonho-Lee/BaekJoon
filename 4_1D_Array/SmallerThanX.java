import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SmallerThanX {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputLine = br.readLine().trim();
        String [] input = inputLine.split(" ");
        int N = Integer.parseInt(input[0]);
        int X = Integer.parseInt(input[1]);

        inputLine = br.readLine().trim();
        input = inputLine.split(" ");
        for(int i=0; i<N; i++){
            if(Integer.parseInt(input[i]) < X){
                bw.write(input[i] + " ");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}