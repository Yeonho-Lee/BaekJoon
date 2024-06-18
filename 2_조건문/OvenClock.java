import java.util.Scanner;

public class OvenClock {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();

        int time = A * 60 + B + C;
        int hour = time / 60 % 24;
        int minute = time % 60;

        System.out.println(hour + " " + minute);
    }
}
