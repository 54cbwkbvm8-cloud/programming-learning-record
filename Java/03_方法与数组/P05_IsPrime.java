
import java.util.Scanner;

public class P05_IsPrime {
    public static boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        for (long i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        System.out.println(isPrime(n) ? "Yes" : "No");
        sc.close();
    }
}
        
        
        
            

            // // 导入 Scanner：Java 自带的"键盘输入工具"，用它才能读取键盘输入。
            // import java.util.Scanner;

            // /**
            // * P05_IsPrime —— 判断一个整数是不是质数（素数）
            // *
            // * 【什么是质数】
            // * 只能被 1 和它自己整除的、大于 1 的整数。例如 2、3、5、7、11 是质数；4、9 不是。
            // * 特别注意：1 不是质数；0 和负数也不是质数。
            // *
            // * 【输入】一个整数 n。
            // * 【输出】是质数输出 Yes，不是输出 No（注意只有首字母大写）。
            // *
            // * 【关于"为什么没有输入提示语"】
            // * 这是评测题风格的程序：评测系统会把程序的输出和标准答案逐字对比，
            // * 多打印任何一个字（比如"请输入……"）都会判错。所以这里不打印提示语。
            // *
            // * 【本次修复的问题】
            // * 原代码的循环条件写的是 i * i <= n（i 的平方不超过 n）。
            // * 当 n 非常大、接近 long 能表示的最大值（约 922 亿亿）时，i * i 本身会超出 long 的范围，
            // * 这叫"溢出"：结果会变成一个错误的负数。例如 3037000500 * 3037000500 的结果是 -9223372036709301616，
            // * 负数永远 <= n，条件一直成立，循环就会跑过头，判断出错。
            // * 修复：把条件改写成 i <= n / i。它和 i * i <= n 在数学上是同一个意思，
            // * 但只做除法，数字只会变小，永远不会溢出。
            // * （这个问题只在 n 非常大时才会出现，所以普通的测试数据是发现不了的。
            // * 另外，n 越大，循环越慢；n 到十几位数时仍然很快，到十八九位数时无论怎么写都会很慢，
            // * 因为需要试除的次数大约是 n 的平方根，那是算法本身的限制。）
            // */
            // public class P05_IsPrime {

            // // ============================================================
            // // 方法 isPrime：判断 n 是不是质数
            // //
            // // 方法头怎么读：public static boolean isPrime(long n)
            // // boolean ：返回值类型，只有 true（是）和 false（否）两个值
            // // isPrime ：方法名，以 is 开头是程序员的习惯，表示"是不是……"
            // // (long n)：一个参数，要判断的数，类型是 long（比 int 能存更大的整数）
            // // return 的作用：把结果交还给调用者，并且立刻结束这个方法。
            // // ============================================================
            // public static boolean isPrime(long n) {

            // // 第一步：排除小于 2 的数。1、0、负数都不是质数，直接返回 false。
            // if (n < 2) {
            // return false;
            // }

            // // 第二步：试除。依次检查 2, 3, 4, ... 能不能整除 n，只要有一个能整除，n 就不是质数。
            // //
            // // 【为什么只需要试到"平方根"为止？】
            // // 如果 n 能分解成 a × b（a、b 都大于 1），那么 a 和 b 里一定有一个不超过 n 的平方根。
            // // 所以只要试到平方根就够了：小的那个因数没有整除，大的那个也不必再试。
            // // 例如判断 97：试到 9 就够了（9 的平方是 81，10 的平方是 100 已经超过 97）。
            // //
            // // 【修复点】原来的条件是 i * i <= n，现在写成 i <= n / i。
            // // n / i 是"整数除法"，只保留整数部分。i <= n / i 与 i * i <= n 等价，且不会溢出。
            // // 这里的 i 也是 long 类型，因为 n 可能很大，i 也可能超过 int 的范围。
            // for (long i = 2; i <= n / i; i++) {

            // // n % i 是"n 除以 i 的余数"。余数是 0 就说明 i 能整除 n，n 就不是质数。
            // if (n % i == 0) {
            // return false; // 找到一个因数，立刻返回 false，后面不用再试了
            // }
            // }

            // // 能走到这里，说明没有找到任何能整除 n 的数，n 是质数。
            // // 例如 n = 2：i 从 2 开始，2 <= 2 / 2 = 1 不成立，循环一次都不执行，直接返回 true（2 是质数）。
            // return true;
            // }

            // // main 是程序的入口，运行程序时从这里开始一行一行往下执行
            // public static void main(String[] args) {

            // // 创建 Scanner 对象 sc，负责读取键盘输入
            // Scanner sc = new Scanner(System.in);

            // // nextLong() 读取下一个 long 类型的整数
            // long n = sc.nextLong();

            // // 三元运算符：条件 ? 值1 : 值2。如果条件成立，结果是值1，否则是值2。
            // // isPrime(n) ? "Yes" : "No" 读作："如果 n 是质数，就是 "Yes"，否则是 "No""。
            // // 等价于：
            // // if (isPrime(n)) {
            // // System.out.println("Yes");
            // // } else {
            // // System.out.println("No");
            // // }
            // System.out.println(isPrime(n) ? "Yes" : "No");

            // // 用完 Scanner 后关闭它，释放资源。这是个好习惯。
            // sc.close();
            // }
            // } 
