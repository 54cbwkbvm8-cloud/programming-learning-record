import java.util.Scanner;
public class StringLesson {
    public static void main(String[] args) {
        String s = " Hello Java ";
        String clean = s.trim();
        System.out.println("length=" + clean.length());
        System.out.println("charAt=" + clean.charAt(0));
        System.out.println("substring=" + clean.substring(0, 5));
        System.out.println("indexOf=" + clean.indexOf("Java"));
        System.out.println("contains=" + clean.contains("Java"));
        System.out.println("equals=" + clean.equals("Hello Java"));
        System.out.println("equalsIgnoreCase=" + clean.equalsIgnoreCase("hello java"));
        System.out.println("replace=" + clean.replace("Java", "World"));
        String[] parts = clean.split(" ");
        System.out.println("split length=" + parts.length);
        char[] chars = clean.toCharArray();
        System.out.println("toCharArray length=" + chars.length);
        System.out.println("toCharArray=" + chars[0]);
        String a = "Java";
        String b = new String("Java");
        System.out.println("a==b=" + (a == b));
        System.out.println("a.equals(b)=" + a.equals(b));
        Scanner sc = new Scanner(System.in);
        String text = sc.hasNextLine() ? sc.nextLine() : "";
        int letters = 0;
        int digits = 0;
        int spaces = 0;
        int others = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                letters++;
            } else if (c >= '0' && c <= '9') {
                digits++;
            } else if (c == ' ') {
                spaces++;
            } else {
                others++;
            }
        }
        System.out.println("letters=" + letters + ", digits=" + digits + ", spaces=" + spaces + ", others=" + others);
        sc.close();
    }
    static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

// // import 导入 java.util.Scanner，即Scanner 输入扫描器，用于从标准输入读行。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.Scanner;
// // public class 定义公开类 StringLesson；类是组织字段与方法的容器。
// // 公开类名必须与 .java 文件名一致；左大括号开启类体。
// public class StringLesson {
// // main 是 Java 程序的启动入口；public 允许 JVM 调用，static 表示无需创建类实例，void 表示不返回值。
// // String[] args 是命令行参数字符串数组，左大括号开始方法体。
// public static void main(String[] args) {
// // String 是不可修改的字符串类型；s 保存带前后空格的文本，双引号表示字符串字面量。
// String s = " Hello Java ";
// // trim 返回去除两端普通空白的新字符串 Hello Java；原 s 不变，clean 保存结果。
// String clean = s.trim();
// // length() 返回字符串的 UTF-16 代码单元数量；Hello Java 有 10 个（含中间空格）。
// System.out.println("length=" + clean.length());
// // charAt(0) 读取第一个字符 H；下标从 0 起，空字符串不能读取下标 0。
// System.out.println("charAt=" + clean.charAt(0));
// // substring(0,5) 截取下标 0 到 4 的五个字符 Hello；结束下标不包含在内。
// System.out.println("substring=" + clean.substring(0, 5));
// // indexOf("Java") 查找子串首次出现的起始下标 6；找不到时返回 -1。
// System.out.println("indexOf=" + clean.indexOf("Java"));
// // contains 判断 clean 是否包含 Java，返回 boolean true；不会返回具体位置。
// System.out.println("contains=" + clean.contains("Java"));
// // equals 比较字符串内容是否完全相同，区分大小写；这里结果 true。
// System.out.println("equals=" + clean.equals("Hello Java"));
// // equalsIgnoreCase 比较内容时忽略大小写，Hello Java 与 hello java 得到 true。
// System.out.println("equalsIgnoreCase=" + clean.equalsIgnoreCase("hello java"));
// // replace 返回把 Java 替换为 World 的新字符串；clean 本身不会改变。
// System.out.println("replace=" + clean.replace("Java", "World"));
// // split 按正则表达式中的一个空格切分，返回 String 数组，元素 Hello、Java。
// String[] parts = clean.split(" ");
// // 数组使用 length 属性而非 length() 方法；这里 parts 有两个元素。
// System.out.println("split length=" + parts.length);
// // toCharArray 创建 char 数组，将字符串各 UTF-16 代码单元依次复制。
// char[] chars = clean.toCharArray();
// // 输出字符数组长度 10；与 clean.length() 在这里相同。
// System.out.println("toCharArray length=" + chars.length);
// // 输出字符数组第一个元素 H；方括号通过下标访问数组。
// System.out.println("toCharArray=" + chars[0]);
// // a 引用字符串常量池中的 Java 字符串；字面量可能被多个变量共享。
// String a = "Java";
// // new String("Java") 显式创建新字符串对象 b；与 a 内容相同但对象不同。
// String b = new String("Java");
// // == 比较两个引用是否指向同一对象；这里 false，括号保证先做比较再拼接。
// System.out.println("a==b=" + (a == b));
// // equals 比较实际内容，因此 true；字符串内容比较通常用 equals 而不是 ==。
// System.out.println("a.equals(b)=" + a.equals(b));
// // Scanner 从标准输入读取文本；new 构造扫描器，sc 保存引用。
// Scanner sc = new Scanner(System.in);
// // hasNextLine 检查是否存在下一行；三元表达式在有行时读取，否则返回空串，防止 EOF 异常。
// String text = sc.hasNextLine() ? sc.nextLine() : "";
// // int 是整数类型；letters 从 0 开始累计 ASCII 英文字母的个数。
// int letters = 0;
// // digits 初始为 0，累计 ASCII 数字字符 0 至 9 的个数，不是数字的数值总和。
// int digits = 0;
// // spaces 初始为 0，只统计普通空格字符，不包括制表符。
// int spaces = 0;
// // others 初始为 0，累计不属于前三类的 UTF-16 代码单元（如标点、中文）。
// int others = 0;
// // for 从下标 0 遍历到 text.length()-1；i++ 每轮加 1，空串时不进入循环。
// for (int i = 0; i < text.length(); i++) {
// // char 是 16 位代码单元类型；charAt(i) 获取当前字符，c 仅在本轮循环块中可用。
// char c = text.charAt(i);
// // && 要求同一范围的两项比较均成立；
// //
// //  表示小写范围或大写范围任一成立即可。
// // 这是 ASCII 英文字母检测，中文不属于此范围。
// if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
// // letters++ 将字母计数加 1；只有前面的条件成立时才执行。
// letters++;
// // else if 在之前条件失败后检查字符是否在 '0' 至 '9' 范围内。
// // 单引号表示字符，比较的是字符编码而非字符串。
// } else if (c >= '0' && c <= '9') {
// // 当前字符是数字时 digits 加 1，不会同时计入字母类别。
// digits++;
// // 前两类均失败后，检查 c 是否恰好为普通空格；== 用于基本 char 的值比较。
// } else if (c == ' ') {
// // spaces++ 累加普通空格的个数。
// spaces++;
// // 结束前面的条件分支并开启 else；只有对应 if（或前面整个 if/else if 链）均不成立时执行此分支。
// } else {
// // 其他三种条件均失败，others 加 1；每个代码单元只进入一个分类。
// others++;
// // 右大括号结束「else」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「for (int i = 0; i < text.length(); i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 将四个计数拼接成一行输出；必须写 + others 才会打印实际的其他字符计数。
// // 原代码仅输出 others 标签，丢失了数值。
// System.out.println("letters=" + letters + ", digits=" + digits + ", spaces=" + spaces + ", others=" + others);
// // 关闭 Scanner，结束输入；程序随后退出。
// sc.close();
// // 右大括号结束「public static void main(String[] args)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // static 表示不需要创建对象就能调用；boolean 返回 true/false；s 是待检查字符串。
// // 这是独立的回文辅助方法，main 当前没有调用；参数应非 null。
// static boolean isPalindrome(String s) {
// // i=0 指向最左字符，j=长度-1 指向最右字符；空串时 j=-1，循环不会执行。
// int i = 0, j = s.length() - 1;
// // while 在 i<j 时继续比较；两指针相遇或交错表示检查完毕。
// while (i < j) {
// // charAt 取两端字符，!= 表示不相等；出现任何一对不同字符便不是回文。
// if (s.charAt(i) != s.charAt(j)) {
// // return false 立即结束方法，告诉调用者不是回文。
// return false;
// // 右大括号结束「if (s.charAt(i) != s.charAt(j))」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // i++ 左侧下标向右移动一格，检查下一对字符。
// i++;
// // j-- 右侧下标向左移动一格；配合 i++ 缩小检查范围。
// j--;
// // 右大括号结束「while (i < j)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 所有成对字符均相等时返回 true；空串和单字符也满足此定义。
// return true;
// // 右大括号结束「static boolean isPalindrome(String s)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「public class StringLesson」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
