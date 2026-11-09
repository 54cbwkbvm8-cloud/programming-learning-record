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

// /**
// * MaxOverload —— 方法的"重载"：同一个名字 max，写出多个不同的版本
// *
// * 【什么是重载（Overload）】
// * 在同一个类里，可以定义【多个名字相同】的方法，只要它们的"参数列表"不一样就行。
// * "参数列表不一样"指的是：参数的【个数】不同，或者参数的【类型】不同（或者顺序不同）。
// * 调用时，Java 会根据你传进去的参数，自动挑出最匹配的那个版本来执行。
// *
// * 本程序里有三个叫 max 的方法：
// * max(int a, int b) ：求两个整数的最大值
// * max(int a, int b, int c) ：求三个整数的最大值（参数个数不同）
// * max(double a, double b) ：求两个小数的最大值（参数类型不同）
// *
// * 注意：【只有返回值类型不同是不行的】。例如两个都是 max(int a, int b)，
// * 一个返回 int、一个返回 double，Java 无法区分你想调用哪一个，会直接报错。
// *
// * 【检查结果】
// * 这份代码没有错误，所有输出都正确，所以【逻辑一行都没有改】，只补充了详细注释。
// */
// public class MaxOverload {

// // ============================================================
// // 版本 1：求两个整数的最大值
// // ============================================================
// public static int max(int a, int b) {
// // 这里用了"三元运算符"（也叫条件运算符），格式是：条件 ? 值1 : 值2
// // 意思是：如果条件成立，结果就是 值1；否则结果就是 值2。
// // 下面这句读作："如果 a 大于 b，就返回 a；否则返回 b"，等价于：
// // if (a > b) {
// // return a;
// // } else {
// // return b;
// // }
// // 当 a 等于 b 时，条件 a > b 不成立，会返回 b；因为两者相等，返回谁都一样，结果没有问题。
// return a > b ? a : b;
// }

// // ============================================================
// // 版本 2：求三个整数的最大值
// // ============================================================
// public static int max(int a, int b, int c) {
// // 【重要思想：复用已经写好的方法】
// // 三个数的最大值 = "前两个数的最大值" 和 "第三个数" 再比较一次。
// // 最里面的 max(a, b) 参数是两个 int，所以调用的是上面的"版本 1"，得到前两个数的最大值；
// // 最外面的 max(那个结果, c) 参数还是两个 int，同样调用"版本 1"。
// // 好处：不用再重复写比较大小的代码。
// //
// // 例子：max(4, 9, 6)
// // ① 先算 max(4, 9) → 9
// // ② 再算 max(9, 6) → 9
// return max(max(a, b), c);
// }

// // ============================================================
// // 版本 3：求两个小数（double 类型）的最大值
// // ============================================================
// public static double max(double a, double b) {
// // double 是"带小数点的数"的类型（比如 3.5、2.0）。
// // 写法和整数版本完全一样，只是参数类型和返回值类型换成了 double。
// return a > b ? a : b;
// }

// // main 是程序的入口，运行程序时从这里开始一行一行往下执行
// public static void main(String[] args) {

// // ---------- 测试"两个整数"的版本 ----------
// // 传进去的是两个整数，Java 自动选择"版本 1"。下面这几组数据是故意挑选的：
// System.out.println("max(3,7) = " + max(3, 7)); // 大的在后面 → 7
// System.out.println("max(7,3) = " + max(7, 3)); // 大的在前面 → 7（交换顺序结果不变）
// System.out.println("max(5,5) = " + max(5, 5)); // 两数相等 → 5
// System.out.println("max(-3, -8) = " + max(-3, -8)); // 两个负数：-3 比 -8 大 → -3
// System.out.println("max(0, -1) = " + max(0, -1)); // 一个 0 一个负数 → 0

// // ---------- 测试"三个整数"的版本 ----------
// // 传进去的是三个整数，Java 自动选择"版本 2"。
// // 最大值分别放在第 2、1、3 个位置，确保每个位置都能被正确识别：
// System.out.println("max(4, 9, 6) = " + max(4, 9, 6)); // 最大值在中间 → 9
// System.out.println("max(9, 4, 6) = " + max(9, 4, 6)); // 最大值在最前 → 9
// System.out.println("max(6, 4, 9) = " + max(6, 4, 9)); // 最大值在最后 → 9
// System.out.println("max(2, 2, 2) = " + max(2, 2, 2)); // 三个数都相等 → 2
// System.out.println("max(-1, -5, -3) = " + max(-1, -5, -3)); // 全是负数 → -1

// // ---------- 测试"小数"的版本 ----------
// // max(3.5, 2)：一个是 double（3.5），一个是 int（2），没有任何一个版本正好是"(double, int)"。
// // 这时 Java 会做"自动类型提升"：把 int 的 2 自动升级成 double 的 2.0，
// // 变成 max(3.5, 2.0)，于是匹配到"版本 3"（两个 double），结果是 3.5。
// // 规律：小范围的类型（int）可以自动变成大范围的类型（double），反过来则不行。
// System.out.println("max(3.5, 2) = " + max(3.5, 2)); // 3.5
// System.out.println("max(2.5, 2.5) = " + max(2.5, 2.5)); // 两数相等 → 2.5
// }
// }
