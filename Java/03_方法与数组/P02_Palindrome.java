
import java.util.Scanner;

public class P02_Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        if (sc.hasNextLine()) {
            s = sc.nextLine();
        }
        int left = 0;
        int right = s.length() - 1;
        boolean isPalin = true;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                isPalin = false;
                break;
            }
            left++;
            right--;
        }
        if (isPalin) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        sc.close();
    }
}








// // 导入 Scanner：Java 自带的"键盘输入工具"，用它才能读取键盘输入。
// import java.util.Scanner;

// /**
//  * P02_Palindrome —— 判断输入的一行文字是不是"回文"
//  *
//  * 【什么是回文】
//  *   从左往右读和从右往左读完全一样的文字。例如 level、abba、上海自来水来自海上。
//  *
//  * 【输入】一行文字（可以包含空格）。
//  * 【输出】是回文输出 YES，不是输出 NO。
//  *
//  * 【判断规则（按字符一一对比，原样比较）】
//  *   · 区分大小写：Aba 不是回文（'A' 和 'a' 是不同的字符）。
//  *   · 空格也算一个字符：ab ba 是回文（中间的空格正好在正中间），但 "A man a plan" 不是。
//  *   · 空行（一个字符都没有）和只有一个字符，都按回文处理，输出 YES。
//  *   如果你的题目要求"忽略大小写"或"忽略空格和标点"，需要先对文字做处理再比较，
//  *   把题目原文发给我，我可以帮你调整。
//  *
//  * 【关于"为什么没有输入提示语"】
//  *   这是评测题风格的程序：评测系统会把程序的输出和标准答案逐字对比，
//  *   多打印任何一个字（比如"请输入……"）都会判错。所以这里不打印提示语。
//  *
//  * 【检查结果】
//  *   这份代码没有错误。我测试了 level、abba、abc、单个字符、空行、大小写、带空格等情况，
//  *   结果都符合上面的规则，所以【逻辑一行都没有改】，只补充了详细注释。
//  */
// public class P02_Palindrome {

//     // main 是程序的入口，运行程序时从这里开始一行一行往下执行
//     public static void main(String[] args) {

//         // 创建 Scanner 对象 sc，负责读取键盘输入
//         Scanner sc = new Scanner(System.in);

//         // 先给 s 一个初始值 ""（空字符串），保证即使没有任何输入，s 也有确定的值。
//         String s = "";

//         // 先用 hasNextLine() 检查输入里有没有下一行，有的话才读取，
//         // 防止"完全没有输入"时 nextLine() 让程序崩溃。
//         if (sc.hasNextLine()) {
//             // nextLine() 读取整整一行（包括空格）。如果用 next()，遇到空格就停了，只能读到第一个单词。
//             s = sc.nextLine();
//         }

//         // 【核心思路：双指针，一个从左往右，一个从右往左，向中间靠拢】
//         // 回文的特点是：第一个字符 = 最后一个字符，第二个 = 倒数第二个……
//         // 所以让两个"指针"（其实就是两个存放下标的变量）分别从两头出发，每次比较它们指向的字符：
//         //   · 不相等 → 马上能确定不是回文，不用再比较了
//         //   · 相等   → 两个指针各往中间走一步，继续比较下一对
//         //
//         // 下标从 0 开始：第一个字符的下标是 0，最后一个字符的下标是 长度 - 1。
//         int left = 0;                  // 左指针：从第一个字符开始
//         int right = s.length() - 1;    // 右指针：从最后一个字符开始

//         // isPalin 是一个"标记"（旗帜）：先假设"是回文"（true），
//         // 只要发现任何一对字符不相等，就把它改成 false。
//         boolean isPalin = true;

//         // 只要左指针还在右指针的左边（left < right），就说明还有没比较的"一对"字符。
//         // 当两个指针相遇（left == right，指向同一个字符，即中间那个字符）或者交错（left > right）时，
//         // 说明所有的"对"都比较完了，循环结束。
//         //
//         // 以 level 为例（下标：l=0, e=1, v=2, e=3, l=4）：
//         //   left=0, right=4：'l' 和 'l' 相等 → left=1, right=3
//         //   left=1, right=3：'e' 和 'e' 相等 → left=2, right=2
//         //   left=2, right=2：left < right 不成立，循环结束。isPalin 仍是 true → 输出 YES
//         // 以 abc 为例：
//         //   left=0, right=2：'a' 和 'c' 不相等 → isPalin = false，用 break 立刻跳出循环 → 输出 NO
//         // 空字符串：right = -1，left < right 一开始就不成立，循环一次都不执行 → 输出 YES
//         while (left < right) {

//             // s.charAt(下标)：取出字符串中指定下标的那个字符。
//             // != 是"不等于"。char 类型（单个字符）可以直接用 == 或 != 比较。
//             if (s.charAt(left) != s.charAt(right)) {
//                 isPalin = false;   // 发现一对不相等，肯定不是回文
//                 break;             // break：立刻跳出整个 while 循环，后面的字符不用再比了
//             }

//             left++;    // 左指针往右走一步（left++ 等价于 left = left + 1）
//             right--;   // 右指针往左走一步（right-- 等价于 right = right - 1）
//         }

//         // 根据标记输出结果
//         if (isPalin) {
//             System.out.println("YES");
//         } else {
//             System.out.println("NO");
//         }

//         // 用完 Scanner 后关闭它，释放资源。这是个好习惯。
//         sc.close();
//     }
// }
