import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true) {
            System.out.println("请输入要求和的数字个数（正整数）：");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n >= 1) {
                    break;
                }
                System.out.println("个数必须大于等于1，请重新输入！");
            } else {
                System.out.println("你输入的不是整数，请重新输入！");
                sc.next();
            }
        }
        long sum = 0;
        System.out.println("现在开始输入" + n + "个数字，最后会求这几个数字的和");
        for (int i = 1; i <= n; i++) {
            System.out.println("请输入第" + i + "个数字：");
            while (!sc.hasNextInt()) {
                System.out.println("你输入的不是整数，请重新输入第" + i + "个数字：");
                sc.next();
            }
            int x = sc.nextInt();
            sum += x;
        }
        System.out.println(n + "个数字的和为：" + sum);
        sc.close();
    }
}
// // 导入 Scanner。Scanner 是 Java 自带的"键盘输入工具"，用它才能读取用户在键盘上输入的内容。
// // 它不在默认范围内，所以必须先用 import 告诉 Java："我要用 java.util 包里的 Scanner"。
// import java.util.Scanner;

// /**
// * Sum —— 输入若干个整数，求它们的和
// *
// * 程序流程：
// * 1. 先问用户"一共要输入几个数"（记为 n）
// * 2. 然后循环 n 次，每次读入一个数，加到总和里
// * 3. 最后输出总和
// *
// * 【本次修复的问题】
// * 1. 用户输入的不是整数（比如输入了字母 abc）时，
// * 原来的 sc.nextInt() 会直接让程序崩溃（报 InputMismatchException 错误）。
// * 现在改成：先用 hasNextInt() 检查，不是整数就提示"请重新输入"。
// * 2. n 输入负数或 0 时，原来的程序会打印"现在开始输入-3个数字"，
// * 然后什么都不输入、直接输出 0，显然不合理。现在要求 n 必须 >= 1。
// * 3. 原来的第一句提示"请输入一个整数"含义不清楚（用户不知道这个整数是干什么用的），
// * 现在改成"请输入要求和的数字个数"。
// * 4. 原来最后只输出一个光秃秃的数字，现在加上文字说明。
// */
// public class Sum {

// // main 方法：程序的入口，运行程序时从这里开始一行一行执行
// public static void main(String[] args) {

// // 创建一个 Scanner 对象，名字叫 sc。
// // System.in 表示"标准输入"，也就是键盘。意思是：这个 sc 负责读取键盘输入。
// Scanner sc = new Scanner(System.in);

// // -------------------- 第一步：读取并检查个数 n --------------------

// // 先声明变量 n（整数类型 int），稍后在循环里给它赋值。
// int n;

// // while (true) 表示"条件永远成立"，是一个故意写的"无限循环"。
// // 我们靠里面的 break 来结束它：只有用户输入了合法的 n，才会 break 跳出去。
// while (true) {
// System.out.println("请输入要求和的数字个数（正整数）：");

// // sc.hasNextInt() 的意思是："用户接下来输入的内容，能不能当成一个整数？"
// // 能 → 返回 true；不能（比如输入了 abc 或 3.5）→ 返回 false
// // 它只"看一眼"，不会真的把内容取走，所以不会报错。
// if (sc.hasNextInt()) {
// n = sc.nextInt(); // 确认是整数后，才真正读取出来，存进 n

// if (n >= 1) {
// break; // n 合法（>= 1），跳出 while 循环，继续往下执行
// }
// // 如果走到这里，说明是整数但小于 1（比如 0、-3），提示后回到循环开头重新输入
// System.out.println("个数必须大于等于1，请重新输入！");
// } else {
// // 输入的不是整数
// System.out.println("你输入的不是整数，请重新输入！");

// // 【关键】sc.next() 读取并丢掉用户输入的那一段错误内容（比如 abc）。
// // 如果不丢掉，错误内容会一直留在"输入缓冲区"里，
// // 下一轮 hasNextInt() 还是看到它，就会陷入无限循环不停地提示。
// sc.next();
// }
// }

// // -------------------- 第二步：循环读取 n 个数并累加 --------------------

// // sum 用来保存总和。初始值必须是 0（相当于"还一分钱都没有"）。
// // 这里用 long 而不是 int：int 最大只能存约 21 亿，
// // 多个大数相加可能超过这个范围（叫"溢出"，结果会变成错误的负数）；
// // long 能存的范围大得多，更安全。
// long sum = 0;

// // 字符串和变量用 + 连接起来，n 会自动变成文字
// System.out.println("现在开始输入" + n + "个数字，最后会求这几个数字的和");

// // for 循环：i 从 1 开始，到 n 结束，每次 +1，所以正好循环 n 次。
// // i 在这里的作用是"数到第几个了"，也用来在提示里显示"第几个数"。
// for (int i = 1; i <= n; i++) {
// System.out.println("请输入第" + i + "个数字：");

// // 和上面同样的道理，这里用 while 反复检查：
// // !sc.hasNextInt() 读作"不是整数"（! 表示"取反"）。
// // 只要输入的不是整数，就提示并丢掉错误内容，直到用户输入了真正的整数才往下走。
// while (!sc.hasNextInt()) {
// System.out.println("你输入的不是整数，请重新输入第" + i + "个数字：");
// sc.next(); // 丢掉错误内容
// }

// int x = sc.nextInt(); // 此时能确定是整数了，放心读取，存进变量 x
// sum += x; // sum += x 等价于 sum = sum + x，意思是"把 x 加到总和里"
// }

// // -------------------- 第三步：输出结果 --------------------
// System.out.println(n + "个数字的和为：" + sum);

// // 用完 Scanner 后关闭它，释放资源。这是个好习惯。
// sc.close();
// }
// }
