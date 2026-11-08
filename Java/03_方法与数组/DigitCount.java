import java.util.Scanner;

public class DigitCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int count = 0;
        for (int num = 1; num <= n; num++) {
            int temp = num;
            while (temp > 0) {
                int digit = temp % 10;
                if (digit == x) {
                    count++;
                }
                temp = temp / 10;
            }
        }
        System.out.println(count);
        sc.close();
    }
}
