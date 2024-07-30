import java.util.Scanner;

public class AlarmClock {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int H = scanner.nextInt();
        int M = scanner.nextInt();

        if (M < 45) {
            H--;
            M += 15;
            if (H < 0) {
                H = 23;
            }
        } else {
            M -= 45;
        }

        System.out.println(H + " " + M);
    }
}
