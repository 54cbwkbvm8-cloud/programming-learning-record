import java.util.Arrays;
import java.util.Scanner;

public class P4_DistinctCount {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        if (n == 0) {
            System.out.println(0);
            return;
        }

        Arrays.sort(arr);

        int count = 1;

        for (int i = 1; i < n; i++) {

            if (arr[i] != arr[i - 1]) {
                count++;
            }
        }

        System.out.println(count);
    }
}

// // 导入 Arrays 工具类：Java 自带的"数组工具箱"，里面有很多现成的功能，比如排序 Arrays.sort。
// // 它不在默认范围内，必须先 import，否则 Java 不认识 Arrays 这个名字。
// import java.util.Arrays;
// // 导入 Scanner：Java 自带的"键盘输入工具"，用它才能读取键盘输入。
// import java.util.Scanner;

// /**
// * P4_DistinctCount —— 统计一组数里有多少个"不同的数"
// *
// * 【输入】第一个整数 n：数的个数；接下来 n 个整数。
// * 【输出】这组数里不同的数有几个（重复的只算一个）。
// * 例：3 1 3 2 1 → 去掉重复后是 1、2、3，共 3 个，输出 3。
// *
// * 【思路：先排序，再数"变化了几次"】
// * 把数组排好序之后，相同的数一定会挨在一起，例如 3 1 3 2 1 排序后是 1 1 2 3 3。
// * 这时只需要从左往右看：
// * · 第一个数一定算一个新的数
// * · 之后每遇到一个数和它【前面那个数不一样】，说明遇到了一个新的数
// * 上面的例子：1（第一个，算 1 个）、1（和前面一样，不算）、2（和前面不一样，新增）、
// * 3（新增）、3（和前面一样，不算），一共 3 个。
// *
// * 【关于"为什么没有输入提示语"】
// * 这是评测题风格的程序：评测系统会把程序的输出和标准答案逐字对比，
// * 多打印任何一个字（比如"请输入……"）都会判错。所以这里不打印提示语。
// *
// * 【检查结果】
// * 这份代码没有错误。我测试了有重复、全部相同、n = 0、n = 1、含负数等情况，结果都正确，
// * 所以【逻辑一行都没有改】，只补充了详细注释。
// * 说明：程序结束时没有调用 sc.close() 关闭 Scanner，对这种运行完就结束的小程序没有影响，
// * 但在长期运行的程序里，用完关闭是个好习惯。
// */
// public class P4_DistinctCount {

// // main 是程序的入口，运行程序时从这里开始一行一行往下执行
// public static void main(String[] args) {

// // 创建 Scanner 对象 sc，负责读取键盘输入
// Scanner sc = new Scanner(System.in);

// int n = sc.nextInt(); // 数的个数

// // 创建一个能放 n 个整数的数组。数组下标从 0 开始：n 个格子的下标是 0, 1, ..., n-1。
// int[] arr = new int[n];
// for (int i = 0; i < n; i++) {
// arr[i] = sc.nextInt();
// }

// // 特殊情况：一个数都没有（n = 0），不同的数当然是 0 个。
// // 必须单独处理：因为下面的计数器 count 是从 1 开始的（"第一个数算一个"），
// // 但 n = 0 时根本没有"第一个数"，如果不处理，会错误地输出 1。
// // return 的作用是"立刻结束 main 方法"，也就是结束整个程序，后面的代码不会再执行。
// if (n == 0) {
// System.out.println(0);
// return;
// }

// // Arrays.sort(数组)：把数组从小到大排好序。这是 Java 现成的工具，不用自己写排序。
// // 排序后，相同的数会挨在一起。
// Arrays.sort(arr);

// // count 是"不同的数"的计数器。初始值是 1：因为 n >= 1 时，第一个数（下标 0）一定算一个新的数。
// int count = 1;

// // 从第二个数（下标 1）开始，依次和它前面的那个数（下标 i - 1）比较。
// // i 从 1 开始而不是 0，是因为下标 0 前面没有数可以比较（arr[-1] 不存在，会越界）。
// for (int i = 1; i < n; i++) {

// // != 是"不等于"。和前面的数不一样，说明遇到了一个新的数，计数器加 1。
// // 和前面的数一样的，是重复的，不计。
// if (arr[i] != arr[i - 1]) {
// count++; // count++ 等价于 count = count + 1
// }
// }

// // 输出不同的数的个数
// System.out.println(count);
// }
// }
