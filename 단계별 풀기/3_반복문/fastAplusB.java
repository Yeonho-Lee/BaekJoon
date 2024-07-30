import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class fastAplusB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 개수를 입력받음

        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().trim().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);

            int sum = A + B;
            bw.write(sum + "\n"); // 결과를 BufferedWriter를 이용하여 출력
        }

        bw.flush(); // BufferedWriter를 비우고 버퍼에 있는 모든 내용을 출력
        bw.close();
        br.close();
    }
}
