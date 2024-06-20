import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Assignment {
    public static void main(String[] args) throws Exception{
        int [] students = new int [30];
        for (int i = 0; i < students.length; i++){
            students[i] = i+1;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 28; i++){
            int index = Integer.parseInt(br.readLine().strip())-1;
            students[index] = 0;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < students.length; i++){
            if (students[i] != 0){
                bw.write(students[i]+ "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }
}
