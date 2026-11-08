import java.util.Arrays;

public class PassByValue {
    public static void changeInt(int x) {
        x = 100;
        System.out.println("x in changeInt: " + x);
    }

    public static void changeArray(int[] arr) {
        arr[0] = 100;
    }

    public static void reassignArray(int[] arr) {
        arr = new int[] { 9, 9, 9 };
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    public static void main(String[] args) {
        int num = 5;
        changeInt(num);
        System.out.println("调用后 num = " + num);
        int[] data = { 1, 2, 3 };
        changeArray(data);
        System.out.println("changeArray 后 data = " + Arrays.toString(data));
        reassignArray(data);
        System.out.println("reassignArray 后 data = " + Arrays.toString(data));
        int x = 1, y = 2;
        swap(x, y);
        System.out.println("swap 后 x = " + x + ", y = " + y);
    }
}
