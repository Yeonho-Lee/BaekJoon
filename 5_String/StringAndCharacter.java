import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StringAndCharacter {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String str = br.readLine();
            int index = Integer.parseInt(br.readLine().strip());
            System.out.println(str.charAt(index-1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}