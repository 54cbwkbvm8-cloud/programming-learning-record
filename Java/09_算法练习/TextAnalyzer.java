import java.util.Locale;
import java.util.Scanner;
public class TextAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.hasNextLine() ? sc.nextLine() : "";
        String lower = text.toLowerCase(Locale.ROOT);
        int[] counts = new int[26];
        for (int i = 0; i < lower.length(); i++) {
            char c = lower.charAt(i);
            if (c >= 'a' && c <= 'z') {
                counts[c - 'a']++;
            }
        }
        String clean = lower.replaceAll("[^a-z]+", " ").trim();
        String[] words = clean.isEmpty() ? new String[0] : clean.split(" +");
        String longest = "";
        StringBuilder palindromes = new StringBuilder();
        for (String w : words) {
            if (w.length() > longest.length())
                longest = w;
            if (w.length() > 1 && isPalindrome(w)) {
                if (palindromes.length() > 0)
                    palindromes.append(", ");
                palindromes.append(w);
            }
        }
        System.out.println("words=" + words.length);
        for (int i = 0; i < counts.length; i++) {
            System.out.print((char) ('a' + i) + ":" + counts[i] + (i == 25 ? "\n" : " "));
        }
        System.out.println("longest=" + (longest.isEmpty() ? "(none)" : longest));
        System.out.println("palindromes=" + (palindromes.length() == 0 ? "(none)" : palindromes.toString()));
        int limit = words.length;
        if (args.length > 0) {
            try {
                int parsed = Integer.parseInt(args[0]);
                if (parsed < 0 || parsed > words.length) {
                    System.out.println("invalid limit");
                    limit = 0;
                } else {
                    limit = parsed;
                }
            } catch (NumberFormatException e) {
                System.out.println("invalid integer limit");
                limit = 0;
            }
        }
        for (int i = 0; i < limit; i++) {
            System.out.println("word[" + i + "]=" + words[i]);
        }
        sc.close();
    }
    private static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
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

// // import 导入 java.util.Locale，即Locale 地区规则，用于稳定的大小写转换。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.Locale;
// // import 导入 java.util.Scanner，即Scanner 输入扫描器，用于从标准输入读行。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.Scanner;
// // public class 定义公开类 TextAnalyzer；类是组织字段与方法的容器。
// // 公开类名必须与 .java 文件名一致；左大括号开启类体。
// public class TextAnalyzer {
// // main 是 Java 程序的启动入口；public 允许 JVM 调用，static 表示无需创建类实例，void 表示不返回值。
// // String[] args 是命令行参数字符串数组，左大括号开始方法体。
// public static void main(String[] args) {
// // Scanner 从标准输入读取文本；new 构造对象，sc 保存引用。
// Scanner sc = new Scanner(System.in);
// // 有下一行就读出，否则使用空串；hasNextLine 防止输入结束时 nextLine 抛异常。
// String text = sc.hasNextLine() ? sc.nextLine() : "";
// // toLowerCase(Locale.ROOT) 返回不依赖系统语言的统一小写文本；原 text 保持不变。
// String lower = text.toLowerCase(Locale.ROOT);
// // 创建长度 26 的整数数组，默认每格为 0，对应 a-z 的出现次数。
// int[] counts = new int[26];
// // i 从 0 遍历 lower 的所有 UTF-16 代码单元；i++ 每轮增加 1。
// for (int i = 0; i < lower.length(); i++) {
// // charAt(i) 取得当前代码单元并存入 char 类型 c。
// char c = lower.charAt(i);
// // && 表示两个比较必须同时成立，只统计小写 ASCII a 到 z。
// if (c >= 'a' && c <= 'z') {
// // c-'a' 得到字母的数组下标，如 a 为 0，z 为 25；对应计数加一。
// counts[c - 'a']++;
// // 右大括号结束「if (c >= 'a' && c <= 'z')」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「for (int i = 0; i < lower.length(); i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 正则 [^a-z]+ 匹配一个或多个连续的非小写 ASCII 字母，替换成空格后 trim 去两端空格。
// // 标点、数字和其他文字在此充当分词分隔符。
// String clean = lower.replaceAll("[^a-z]+", " ").trim();
// // 三元表达式判断 clean 是否为空；空则创建长度 0 的字符串数组，否则按一个或多个空格切分。
// // 避免将空文本错误统计为一个单词。
// String[] words = clean.isEmpty() ? new String[0] : clean.split(" +");
// // longest 保存目前找到的最长单词，初值空串表示尚未找到。
// String longest = "";
// // 创建空 StringBuilder，用来保存所有符合要求的回文词及分隔符。
// StringBuilder palindromes = new StringBuilder();
// // 增强 for 遍历 words 中的每一个字符串，当前值存入 w。
// for (String w : words) {
// // 只有当前词长度严格大于已记录长度才执行下一条语句；同长度时保留最先出现的词。
// // 原代码此处没有大括号，if 只控制紧接的一条语句。
// if (w.length() > longest.length())
// // 将较长的当前单词赋给 longest；不会改变 words 数组。
// longest = w;
// // && 短路求值；仅检查长度大于 1 且 isPalindrome(w) 为真的单词，排除单字母。
// if (w.length() > 1 && isPalindrome(w)) {
// // 只有 builder 已经包含回文词时才执行下一行，避免开头出现逗号。
// if (palindromes.length() > 0)
// // 添加逗号和空格分隔回文词；无大括号的 if 只控制这一条语句。
// palindromes.append(", ");
// // 将当前回文词追加到文本末尾，保留出现顺序和重复词。
// palindromes.append(w);
// // 右大括号结束「if (w.length() > 1 && isPalindrome(w))」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「for (String w : words)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 输出 words.length，即分词后数组中的有效单词数。
// System.out.println("words=" + words.length);
// // 从 0 遍历 26 个计数槽位，包括未出现的字母。
// for (int i = 0; i < counts.length; i++) {
// // 'a'+i 先进行整数运算，再用 (char) 转回字母；拼接冒号及其出现次数。
// // 三元表达式在最后一个字母 z 后输出换行，其余输出空格，print 不自动换行。
// System.out.print((char) ('a' + i) + ":" + counts[i] + (i == 25 ? "\n" : " "));
// // 右大括号结束「for (int i = 0; i < counts.length; i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 若最长词仍为空，则显示 (none)，否则输出最长词；?: 只选择一个结果。
// System.out.println("longest=" + (longest.isEmpty() ? "(none)" : longest));
// // 若回文 builder 长度为 0 就显示 (none)，否则通过 toString 输出全部回文词。
// System.out.println("palindromes=" + (palindromes.length() == 0 ? "(none)" : palindromes.toString()));
// // limit 初始为单词总数，表示默认列出全部词。
// int limit = words.length;
// // args 是 main 的命令行参数数组；length>0 表示用户提供了至少一个参数。
// if (args.length > 0) {
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // parseInt 将第一个命令行参数 args[0] 解析为整数；无效文本或溢出会抛异常。
// int parsed = Integer.parseInt(args[0]);
// //
// //
// //  表示任一非法条件成立：负数或超过单词总数均不可作为输出上限。
// if (parsed < 0 || parsed > words.length) {
// // 输出 invalid limit（范围无效），提示用户参数不符合约定。
// System.out.println("invalid limit");
// // 将 limit 设为 0，避免非法下标访问，并停止后面的单词列表输出。
// limit = 0;
// // 结束前面的条件分支并开启 else；只有对应 if（或前面整个 if/else if 链）均不成立时执行此分支。
// } else {
// // 合法时使用解析得到的整数作为列表输出数量，包括合法的 0。
// limit = parsed;
// // 右大括号结束「else」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束 try 块；catch 捕获 NumberFormatException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (NumberFormatException e) {
// // 捕获 NumberFormatException 后提示命令行参数不是合法整数。
// System.out.println("invalid integer limit");
// // 将 limit 设为 0，后续循环不进入；此前统计仍然保留。
// limit = 0;
// // 右大括号结束「catch (NumberFormatException e)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「if (args.length > 0)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // i 从 0 到 limit-1 遍历需要列出的词；上限已被验证，不超过 words.length。
// for (int i = 0; i < limit; i++) {
// // 输出单词的数组下标及内容，word[i]=... 只是展示标签。
// System.out.println("word[" + i + "]=" + words[i]);
// // 右大括号结束「for (int i = 0; i < limit; i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 关闭标准输入扫描器；程序随后结束。
// sc.close();
// // 右大括号结束「public static void main(String[] args)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // private 限制方法只能在本类使用；static 不需要实例；boolean 返回真假。
// // s 为已分割的非 null 单词，用双指针验证回文。
// private static boolean isPalindrome(String s) {
// // 左下标 i 初始化为 0，指向首字符。
// int i = 0;
// // 右下标 j 初始化为字符串长度减一，指向末字符。
// int j = s.length() - 1;
// // 当 i<j 时仍有一对字符待比较；相遇时不必再比较中间字符。
// while (i < j) {
// // 使用 charAt 分别读取两端字符，!= 检查是否不相同。
// if (s.charAt(i) != s.charAt(j)) {
// // 任意一对不同即可返回 false，无需继续检查。
// return false;
// // 右大括号结束「if (s.charAt(i) != s.charAt(j))」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // i++ 左端下标向右移动一位。
// i++;
// // j-- 右端下标向左移动一位，逐步缩小待检查区间。
// j--;
// // 右大括号结束「while (i < j)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 所有成对字符都相等后返回 true。
// return true;
// // 右大括号结束「private static boolean isPalindrome(String s)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「public class TextAnalyzer」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
