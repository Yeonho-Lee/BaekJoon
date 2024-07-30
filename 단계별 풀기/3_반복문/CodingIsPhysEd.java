import java.util.Scanner;

public class CodingIsPhysEd {
    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        int N = s.nextInt();
        N = N/4;
        for (int i = 0; i < N; i ++){
            System.out.print("long ");
        }
        System.out.print("int");
    }
}
