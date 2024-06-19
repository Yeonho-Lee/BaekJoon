import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AplusB_5 {
    public static void main (String args[]) throws Exception{
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out));
        while(true) {
            String inputLine = br.readLine();
            if (inputLine == null|| inputLine.trim().isEmpty()) {
                break;
            }
            String []input = inputLine.split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            
            bw.write(A+B+ "\n");
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
