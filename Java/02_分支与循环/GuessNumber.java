import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    public static void main(String[] args) {
        Random random = new Random();
        int secret = random.nextInt(100) + 1;
        Scanner sc = new Scanner(System.in);
        int count = 0;
        System.out.println("我想好了一个1-100之间的整数，你猜是多少？");
        while (true) {
            System.out.print("请输入你的猜测：");
            if (!sc.hasNextInt()) {
                System.out.println("你输入的不是整数，请重新输入！");
                sc.next();
                continue;
            }
            int guess = sc.nextInt();
            if (guess < 1 || guess > 100) {
                System.out.println("请输入1-100之间的整数！");
                continue;
            }
            count++;
            if (guess < secret) {
                System.out.println("你猜小了！");
            } else if (guess > secret) {
                System.out.println("你猜大了！");
            } else {
                System.out.println("恭喜你，猜对了！你一共猜了" + count + "次。");
                break;
            }
        }
        sc.close();
    }
}
// // import 的作用：告诉 Java "我要使用别人写好的工具"。
// // Random ：随机数工具，用来让电脑"想"一个随机的数字
// // Scanner ：键盘输入工具，用来读取用户在键盘上输入的内容
// // 这两个工具不在 Java 默认范围内，不 import 的话 Java 不认识它们，程序会编译失败。
// import java.util.Random;
// import java.util.Scanner;

// /**
// * GuessNumber —— 猜数字小游戏
// *
// * 游戏规则：
// * 1. 电脑随机想一个 1~100 之间的整数（"秘密数字"）
// * 2. 玩家不断输入自己的猜测
// * 3. 电脑每次提示"猜大了"或"猜小了"，直到玩家猜对为止
// * 4. 猜对后显示一共猜了几次
// *
// * 【本次修复的问题】
// * 玩家输入的不是整数（比如输入了字母 abc、小数 3.5）时，
// * 原来的 sc.nextInt() 会直接让整个程序崩溃（报 InputMismatchException 错误）。
// * 一个游戏不应该因为玩家手滑输错就崩溃，所以现在改成：
// * 先用 hasNextInt() 检查输入是不是整数，不是就提示"请重新输入"，游戏继续。
// * （其余的游戏逻辑本身是正确的，没有改动。）
// */
// public class GuessNumber {

// // main 是程序入口，运行程序时从这里开始一行一行往下执行
// public static void main(String[] args) {

// // 创建一个 Random 对象，名字叫 random，它就是我们的"随机数发生器"
// Random random = new Random();

// // random.nextInt(100) 会随机产生一个 0 到 99 之间的整数（包含 0 和 99，不包含 100）。
// // 括号里的 100 表示"有 100 种可能"：0, 1, 2, ..., 99。
// // 我们想要的是 1~100，所以结果再加 1：0~99 变成 1~100。
// int secret = random.nextInt(100) + 1; // secret 是"秘密数字"，玩家要猜的就是它

// // 创建 Scanner 对象 sc，负责读取键盘输入（System.in 就是键盘）
// Scanner sc = new Scanner(System.in);

// // count 用来记录"有效的猜测次数"，一开始还没猜，所以是 0
// int count = 0;

// System.out.println("我想好了一个1-100之间的整数，你猜是多少？");

// // while (true) 是故意写的"无限循环"：只要玩家没猜对就一直猜下去。
// // 退出的方式只有一种：猜对时执行下面的 break。
// while (true) {
// // print 不换行，所以玩家输入的内容会紧跟在冒号后面，看起来更自然
// System.out.print("请输入你的猜测：");

// // ---------- 检查 1：输入的是不是整数（本次新增，修复崩溃问题）----------
// // sc.hasNextInt()：先"看一眼"用户输入的内容能不能当成整数，
// // 只看不取，所以不会报错。! 表示"取反"，所以 !sc.hasNextInt() 读作"不是整数"。
// if (!sc.hasNextInt()) {
// System.out.println("你输入的不是整数，请重新输入！");

// // 【关键】sc.next() 把用户刚才输入的错误内容读出来并丢掉。
// // 如果不丢掉，错误内容会一直留在缓冲区里，
// // 下一轮 hasNextInt() 仍然看到它，就会不停地提示、永远无法继续。
// sc.next();

// // continue：跳过这一轮循环剩下的代码，直接回到 while 开头，让玩家重新输入
// continue;
// }

// // 能走到这里，说明输入一定是整数，可以放心读取，存进变量 guess（"猜测"）
// int guess = sc.nextInt();

// // ---------- 检查 2：整数是不是在 1~100 范围内 ----------
// // || 表示"或者"：guess < 1 或者 guess > 100，只要有一个成立就是"超出范围"
// if (guess < 1 || guess > 100) {
// System.out.println("请输入1-100之间的整数！");
// continue; // 超出范围的输入不算一次有效猜测，所以回到开头重新输入，count 不增加
// }

// // 走到这里说明是一次"有效的猜测"（是整数，且在 1~100 范围内），次数 +1
// // count++ 等价于 count = count + 1
// count++;

// // ---------- 比较大小，给出提示 ----------
// // if / else if / else 是"多选一"：从上到下依次判断，只会执行其中一个分支
// if (guess < secret) {
// System.out.println("你猜小了！");
// } else if (guess > secret) {
// System.out.println("你猜大了！");
// } else {
// // 既不小也不大，那就是正好相等 = 猜对了
// // 用 + 把文字和变量 count 拼起来显示
// System.out.println("恭喜你，猜对了！你一共猜了" + count + "次。");
// break; // 猜对了，跳出 while (true) 无限循环，游戏结束
// }
// }

// // 游戏结束，关闭 Scanner（用完工具要"收起来"，是个好习惯）
// sc.close();
// }
// }
