public class MethodBasic {
    public static void sayHello() {
        System.out.println("Hello, method !");
    }

    public static void printSquare(int n) {
        System.out.println(n + " 的平方是 " + (n * n));
    }

    public static int add(int a, int b) {
        int sum = a + b;
        return sum;
    }

    public static String level(int score) {
        if (score >= 60) {
            return "及格";
        }
        return "不及格";
    }

    public static void main(String[] args) {
        sayHello();
        printSquare(5);
        int result = add(3, 4);
        System.out.println("add(3,4) = " + result);
        System.out.println("add(add(1, 2)), 10) = " + add(add(1, 2), 10));
        System.out.println("level(60) = " + level(60));
        System.out.println("level(59) = " + level(59));
    }
}
