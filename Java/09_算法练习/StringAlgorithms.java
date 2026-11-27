import java.util.Locale;
import java.util.Scanner;
public class StringAlgorithms {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.hasNextLine() ? sc.nextLine() : "";
        String t = sc.hasNextLine() ? sc.nextLine() : "";
        System.out.println("reverseWords=" + reverseWords(s));
        System.out.println("anagram=" + isAnagram(s, t));
        System.out.println("longestRun=" + longestRun(s));
        System.out.println("normalized=" + normalize(s));
        sc.close();
    }
    public static String reverseWords(String s) {
        String[] words = normalize(s).split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i].isEmpty()) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(words[i]);
        }
        return sb.toString();
    }
    public static boolean isAnagram(String a, String b) {
        String x = normalize(a).replace(" ", "").toLowerCase(Locale.ROOT);
        String y = normalize(b).replace(" ", "").toLowerCase(Locale.ROOT);
        if (x.length() != y.length()) {
            return false;
        }
        int[] counts = new int[26];
        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(i);
            if (c < 'a' || c > 'z') {
                return false;
            }
            counts[c - 'a']++;
        }
        for (int i = 0; i < y.length(); i++) {
            char c = y.charAt(i);
            if (c < 'a' || c > 'z') {
                return false;
            }
            counts[c - 'a']--;
        }
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
    public static int longestRun(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int best = 1;
        int current = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                current++;
            } else {
                current = 1;
            }
            if (current > best) {
                best = current;
            }
        }
        return best;
    }
    public static String normalize(String s) {
        if (s == null) {
            return "";
        }
        return s.trim().replaceAll("\\s+", " ");
    }
}

// // import 导入 java.util.Locale，即Locale 地区规则，用于稳定的大小写转换。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.Locale;
// // import 导入 java.util.Scanner，即Scanner 输入扫描器，用于从标准输入读行。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.Scanner;
// // public class 定义公开类 StringAlgorithms；类是组织字段与方法的容器。
// // 公开类名必须与 .java 文件名一致；左大括号开启类体。
// public class StringAlgorithms {
// // main 是 Java 程序的启动入口；public 允许 JVM 调用，static 表示无需创建类实例，void 表示不返回值。
// // String[] args 是命令行参数字符串数组，左大括号开始方法体。
// public static void main(String[] args) {
// // 创建从标准输入读取内容的 Scanner，sc 保存它的引用。
// Scanner sc = new Scanner(System.in);
// // hasNextLine 检查是否有下一行，有则 nextLine 读取，否则使用空串；?: 表示二选一。
// String s = sc.hasNextLine() ? sc.nextLine() : "";
// // 用同样方式读取第二行 t；两个输入用于比较字母组成是否相同。
// String t = sc.hasNextLine() ? sc.nextLine() : "";
// // 调用 reverseWords(s) 并输出单词顺序反转的结果，不反转每个单词内部字符。
// System.out.println("reverseWords=" + reverseWords(s));
// // 调用 isAnagram(s,t) 判断两段文本忽略空白和大小写后的英文字母数量是否相同。
// System.out.println("anagram=" + isAnagram(s, t));
// // longestRun(s) 返回原始文本中相同 UTF-16 代码单元连续出现的最大长度。
// System.out.println("longestRun=" + longestRun(s));
// // normalize(s) 返回规范化空白的文本；不改变原始 s。
// System.out.println("normalized=" + normalize(s));
// // close 关闭扫描器，主程序随后结束。
// sc.close();
// // 右大括号结束「public static void main(String[] args)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // public static 允许直接通过类调用；String 为返回类型，s 为输入参数；该方法反转单词顺序。
// public static String reverseWords(String s) {
// // normalize 先统一空白再 split(" ") 切分为单词数组；数组下标从 0 开始。
// String[] words = normalize(s).split(" ");
// // new StringBuilder() 创建空的可修改字符序列，用来拼接反转后的单词。
// StringBuilder sb = new StringBuilder();
// // i 从最后一个单词下标开始递减，每轮检查 i>=0；反向访问实现词序反转。
// for (int i = words.length - 1; i >= 0; i--) {
// // isEmpty 检查当前单词长度是否为 0；空输入切分时可能出现空字符串。
// if (words[i].isEmpty()) {
// // continue 跳过本轮剩余代码并执行 for 的 i--，避免添加空单词。
// continue;
// // 右大括号结束「if (words[i].isEmpty())」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // sb.length()>0 说明已拼接过单词；仅在已有文本时添加分隔空格。
// if (sb.length() > 0) {
// // append(' ') 在 builder 末尾加入一个普通空格。
// sb.append(' ');
// // 右大括号结束「if (sb.length() > 0)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 将 words[i] 当前单词拼到末尾，单词内部字符保持原顺序。
// sb.append(words[i]);
// // 右大括号结束「for (int i = words.length - 1; i >= 0; i--)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // toString 将 builder 转成普通字符串并返回；空输入得到空串。
// return sb.toString();
// // 右大括号结束「public static String reverseWords(String s)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // boolean 返回真假；a、b 是两个字符串参数；这里只比较英文字母异位词。
// public static boolean isAnagram(String a, String b) {
// // normalize 统一空白，replace 删除普通空格，toLowerCase(Locale.ROOT) 使用不依赖系统语言的规则转小写。
// // 避免土耳其语环境把 ASCII I 变成非 ASCII 的 ı。
// String x = normalize(a).replace(" ", "").toLowerCase(Locale.ROOT);
// // 对第二段文本进行完全相同的预处理，保证两者按同一规则比较。
// String y = normalize(b).replace(" ", "").toLowerCase(Locale.ROOT);
// // 若预处理后长度不同，字母数量不可能完全一致；!= 表示不等。
// if (x.length() != y.length()) {
// // 直接返回 false，不再执行后面的计数循环。
// return false;
// // 右大括号结束「if (x.length() != y.length())」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // int[26] 分配 26 个初始为 0 的整数，对应 a 到 z 各一个计数槽位。
// int[] counts = new int[26];
// // 从 0 遍历 x 的所有代码单元，i++ 每轮加一。
// for (int i = 0; i < x.length(); i++) {
// // charAt(i) 获取 x 当前字符，c 为仅在循环体内使用的 char。
// char c = x.charAt(i);
// //
// //
// //  表示任一条件成立；超出 a-z 范围的字符不符合本方法的英文字母约定。
// if (c < 'a' || c > 'z') {
// // 遇到非允许字符立即返回 false，避免计算非法数组下标。
// return false;
// // 右大括号结束「if (c < 'a'
// //
// //  c > 'z')」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // c-'a' 将字母转换为 0 至 25 的下标，例如 b 对应 1；++ 将该字母次数加一。
// counts[c - 'a']++;
// // 右大括号结束「for (int i = 0; i < x.length(); i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 从头遍历第二个处理后的字符串 y，准备抵消第一个字符串的计数。
// for (int i = 0; i < y.length(); i++) {
// // 取得 y 的当前字符，供范围检测及下标计算使用。
// char c = y.charAt(i);
// // 同样验证 y 的字符是否在 a-z 之内，防止下标越界。
// if (c < 'a' || c > 'z') {
// // 无效字符立即返回 false，不进行减法操作。
// return false;
// // 右大括号结束「if (c < 'a'
// //
// //  c > 'z')」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // counts[c-'a']-- 将第二段文本中的该字母计数减一；两串相同字母数将互相抵消。
// counts[c - 'a']--;
// // 右大括号结束「for (int i = 0; i < y.length(); i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 增强 for 循环依次把数组元素读入局部变量 count，无需显式下标。
// for (int count : counts) {
// // 如果某个字母的净计数不为 0，两串该字母数量不同。
// if (count != 0) {
// // 返回 false，表示两段文本不是异位词。
// return false;
// // 右大括号结束「if (count != 0)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「for (int count : counts)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 全部 26 个净计数都为 0，且此前已完成字符验证，返回 true。
// return true;
// // 右大括号结束「public static boolean isAnagram(String a, String b)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // longestRun 返回 int 最大连续重复长度；输入 s 可以为 null。
// public static int longestRun(String s) {
// //
// //
// //  短路求值，若 s==null 为真就不调用 isEmpty，避免空指针；空串也没有重复段。
// if (s == null || s.isEmpty()) {
// // null 或空文本返回 0，符合没有任何字符的情况。
// return 0;
// // 右大括号结束「if (s == null
// //
// //  s.isEmpty())」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // best 记录历史最大连续长度；非空文本至少有一个字符，所以初值为 1。
// int best = 1;
// // current 记录当前连续段长度，起始第一个字符形成长度 1 的连续段。
// int current = 1;
// // 从第二个字符下标 1 开始遍历，保证 i-1 至少为 0。
// for (int i = 1; i < s.length(); i++) {
// // 比较当前位置与前一位置的 char；相等说明当前连续段可以延长。
// if (s.charAt(i) == s.charAt(i - 1)) {
// // current++ 将当前连续段长度增加 1。
// current++;
// // 结束前面的条件分支并开启 else；只有对应 if（或前面整个 if/else if 链）均不成立时执行此分支。
// } else {
// // 相邻字符不同时，旧连续段结束，新字符形成长度 1 的新段。
// current = 1;
// // 右大括号结束「else」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 当前长度超过历史最大时才更新 best；避免较短的后续段覆盖答案。
// if (current > best) {
// // 把新的更大长度赋给 best，保留到循环结束。
// best = current;
// // 右大括号结束「if (current > best)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「for (int i = 1; i < s.length(); i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 返回遍历所有字符后记录的最大长度 best。
// return best;
// // 右大括号结束「public static int longestRun(String s)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // normalize 返回规范化空白后的字符串；null 被定义为空字符串。
// public static String normalize(String s) {
// // 先判断 s 是否为 null，避免后续调用实例方法报空指针异常。
// if (s == null) {
// // null 输入返回空串，后续字符串算法可安全处理。
// return "";
// // 右大括号结束「if (s == null)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // trim 去除两端普通空白，replaceAll 使用正则 \\s+ 匹配连续的常见 ASCII 空白并替换为一个空格。
// // Java 字符串里的两个反斜杠用于向正则引擎传递一个反斜杠；这里不扩展到所有 Unicode 空白。
// return s.trim().replaceAll("\\s+", " ");
// // 右大括号结束「public static String normalize(String s)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「public class StringAlgorithms」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
