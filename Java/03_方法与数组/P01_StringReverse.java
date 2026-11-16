
import java.util.Scanner;

public class P01_StringReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        if (sc.hasNextLine()) {
            s = sc.nextLine();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        System.out.println(sb.toString());
        sc.close();
    }
}




//  // 导入 Scanner：Java 自带的"键盘输入工具"，用它才能读取键盘输入。
// // 它不在默认范围内，必须先 import，否则 Java 不认识 Scanner 这个名字。
// import java.util.Scanner;

// /**
//  * P01_StringReverse —— 把输入的一行文字倒过来输出
//  *
//  * 【输入】一行文字（可以包含空格）。
//  * 【输出】把这行文字从后往前倒过来的结果。
//  *   例如：输入 hello    → 输出 olleh
//  *         输入 a b c    → 输出 c b a   （空格也会参与倒序）
//  *         输入 你好abc   → 输出 cba好你 （汉字也可以）
//  *
//  * 【关于"为什么没有输入提示语"】
//  *   这是评测题风格的程序：评测系统会把程序的输出和标准答案逐字对比，
//  *   多打印任何一个字（比如"请输入……"）都会判错。所以这里不打印提示语。
//  *
//  * 【检查结果】
//  *   这份代码对普通文字（英文、数字、汉字、空格、标点）的处理都是正确的，
//  *   输入为空行、甚至完全没有输入时也不会崩溃，所以【逻辑一行都没有改】，只补充了详细注释。
//  *   唯一的局限：遇到 emoji（如 😀）这类特殊字符会倒成乱码。
//  *   原因是 Java 的 charAt 是按"16 位的小单元"取字符，而 emoji 占两个这样的小单元，
//  *   一个个倒着取会把它拆开。这是练习"手动倒序"的写法本身的特点，所以这里没有改，
//  *   如果以后真的需要正确处理 emoji，可以直接用 new StringBuilder(s).reverse()，
//  *   它会把这种特殊字符当作一个整体处理。
//  */
// public class P01_StringReverse {

//     // main 是程序的入口，运行程序时从这里开始一行一行往下执行
//     public static void main(String[] args) {

//         // 创建 Scanner 对象 sc，负责读取键盘输入（System.in 表示键盘）
//         Scanner sc = new Scanner(System.in);

//         // 先准备一个空的文字变量 s，初始值是 ""（空字符串，里面一个字符都没有）。
//         // 这样即使后面一行都没读到，s 也有一个确定的值，程序不会出错。
//         String s = "";

//         // sc.hasNextLine() 的意思是："输入里还有没有下一行？"，有返回 true，没有返回 false。
//         // 先检查再读取，是为了防止"完全没有输入"的情况：
//         // 如果没有任何输入，直接调用 nextLine() 会让程序崩溃（报 NoSuchElementException）。
//         if (sc.hasNextLine()) {
//             // nextLine() 读取【整整一行】，包括中间的空格，直到换行符为止（换行符本身不会包含进来）。
//             // 对比：sc.next() 遇到空格就停了，只能读到一个"单词"，所以这里必须用 nextLine()。
//             s = sc.nextLine();
//         }

//         // StringBuilder 是一个"可以修改的文字容器"，专门用来一点一点地拼接文字。
//         // 为什么不直接用 String 一次次拼接（result = result + 某个字符）？
//         // 因为 Java 的 String 一旦创建就【不能修改】，每次"拼接"其实都是新建一个完整的字符串，
//         // 把旧内容整个复制过去再加上新字符，文字很长时会非常慢。
//         // StringBuilder 是在原地追加，效率高得多。
//         StringBuilder sb = new StringBuilder();

//         // 从最后一个字符开始，一个一个往前取，依次追加到 sb 里。
//         // 【下标】字符串里每个字符都有一个编号，叫"下标"，【从 0 开始数】。
//         //   以 "abc" 为例：    a    b    c
//         //   对应的下标：      0    1    2
//         //   s.length() 是字符串的长度（这里是 3），所以最后一个字符的下标是 s.length() - 1（这里是 2）。
//         //
//         // for 的三个部分：
//         //   int i = s.length() - 1  ：起点：从最后一个字符的下标开始
//         //   i >= 0                  ：只要 i 还没有走过第一个字符（下标 0），就继续
//         //   i--                     ：每次循环后 i 减 1，也就是往前走一个字符
//         //
//         // 执行过程（输入 abc）：
//         //   i = 2：取出 'c'，追加  → sb 是 "c"
//         //   i = 1：取出 'b'，追加  → sb 是 "cb"
//         //   i = 0：取出 'a'，追加  → sb 是 "cba"
//         //   i = -1：条件 i >= 0 不成立，循环结束
//         // 如果 s 是空字符串，s.length() - 1 就是 -1，条件一开始就不成立，循环一次都不执行，sb 保持为空。
//         for (int i = s.length() - 1; i >= 0; i--) {
//             // s.charAt(i)：取出字符串 s 中下标为 i 的那个字符
//             // sb.append(...)：把这个字符追加到 sb 的末尾
//             sb.append(s.charAt(i));
//         }

//         // sb.toString()：把 StringBuilder 里的内容变回普通的 String，再打印出来。
//         // println 打印后会换行。
//         System.out.println(sb.toString());

//         // 用完 Scanner 后关闭它，释放资源。这是个好习惯。
//         sc.close();
//     }
// }
