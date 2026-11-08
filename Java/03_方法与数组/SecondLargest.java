import java.util.Scanner;

public class SecondLargest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 1; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        boolean found = false;
        int second = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] < max) {
                if (!found || a[i] > second) {
                    second = a[i];
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println(second);
        } else {
            System.out.println("NO");
        }
        sc.close();
    }
}
