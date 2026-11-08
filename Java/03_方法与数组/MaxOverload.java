public class MaxOverload {
    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static int max(int a, int b, int c) {
        return max(max(a, b), c);
    }

    public static double max(double a, double b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        System.out.println("max(3,7) = " + max(3, 7));
        System.out.println("max(7,3) = " + max(7, 3));
        System.out.println("max(5,5) = " + max(5, 5));
        System.out.println("max(-3, -8) = " + max(-3, -8));
        System.out.println("max(0, -1) = " + max(0, -1));
        System.out.println("max(4, 9, 6) = " + max(4, 9, 6));
        System.out.println("max(9, 4, 6) = " + max(9, 4, 6));
        System.out.println("max(6, 4, 9) = " + max(6, 4, 9));
        System.out.println("max(2, 2, 2) = " + max(2, 2, 2));
        System.out.println("max(-1, -5, -3) = " + max(-1, -5, -3));
        System.out.println("max(3.5, 2) = " + max(3.5, 2));
        System.out.println("max(2.5, 2.5) = " + max(2.5, 2.5));
    }
}
