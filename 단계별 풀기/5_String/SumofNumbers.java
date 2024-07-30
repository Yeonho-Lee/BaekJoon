import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SumofNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 숫자의 개수 N 입력 받기
        int N = Integer.parseInt(br.readLine());

        // 숫자들을 공백 없이 입력받기
        String numbers = br.readLine();

        // 숫자들을 모두 합산하기
        int totalSum = 0;
        for (int i = 0; i < N; i++) {
            char digitChar = numbers.charAt(i); // 문자열에서 i번째 문자(숫자) 가져오기
            int digit = Character.getNumericValue(digitChar); // 문자를 정수로 변환
            totalSum += digit;
        }

        // 결과 출력
        bw.write(String.valueOf(totalSum));
        bw.newLine();

        // BufferedWriter를 닫아줌
        bw.close();
        // BufferedReader를 닫아줌
        br.close();
    }
}
