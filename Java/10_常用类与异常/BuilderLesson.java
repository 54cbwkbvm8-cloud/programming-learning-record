import java.util.Scanner;
public class BuilderLesson {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        System.out.println("append=" + sb);
        sb.insert(1, "x");
        System.out.println("insert=" + sb);
        sb.delete(1, 2);
        System.out.println("delete=" + sb);
        sb.reverse();
        String reverse = sb.toString();
        System.out.println("reverse=" + reverse);
        Integer a = 127, b = 127;
        Integer c = 128, d = 128;
        System.out.println("127 == " + (a == b));
        System.out.println("128 == " + (c == d));
        System.out.println("128 equals " + c.equals(d));
        int number = a;
        Double price = Double.valueOf("3.5");
        Character ch = 'a';
        System.out.println("unbox=" + number + ",double=" + price + ",letter=" + Character.isLetter(ch));
        Scanner sc = new Scanner(System.in);
        String text = sc.hasNextLine() ? sc.nextLine() : "";
        System.out.println("inputReverse=" + new StringBuilder(text).reverse().toString());
        try {
            int value = Integer.parseInt(text.trim());
            System.out.println("int=" + value);
            Integer boxed = value;
            System.out.println("boxed=" + boxed);
        } catch (NumberFormatException e) {
            System.out.println("invalid integer");
        }
        sc.close();
    }
}

// // import 导入 java.util.Scanner，即Scanner 输入扫描器，用于从标准输入读行。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.Scanner;
// // public class 定义公开类 BuilderLesson；类是组织字段与方法的容器。
// // 公开类名必须与 .java 文件名一致；左大括号开启类体。
// public class BuilderLesson {
// // main 是 Java 程序的启动入口；public 允许 JVM 调用，static 表示无需创建类实例，void 表示不返回值。
// // String[] args 是命令行参数字符串数组，左大括号开始方法体。
// public static void main(String[] args) {
// // StringBuilder 是可修改字符序列的类；new 调用无参构造方法创建空对象，sb 保存对象引用。
// // 不同于 String，每次追加不必产生新的 String；后续方法都操作同一个 sb。
// StringBuilder sb = new StringBuilder();
// // append 表示追加，把双引号内的五个字符放到末尾；sb 当前为 Hello。
// // 点号访问对象方法，括号传入字符串，分号结束语句。
// sb.append("Hello");
// // println 输出一行并换行；+ 在有字符串时执行拼接，sb 会自动转换为文本 Hello。
// System.out.println("append=" + sb);
// // insert 表示插入；下标从 0 开始，1 是 H 后面的位置，插入 x 后为 Hxello。
// // 插入位置必须在 0 到长度之间，否则抛异常。
// sb.insert(1, "x");
// // 输出插入后的结果 Hxello，验证 insert 对原对象的修改。
// System.out.println("insert=" + sb);
// // delete 删除下标范围 [1,2)，包含 1 不包含 2，因此只删 x；恢复 Hello。
// // 结束下标不是删除的字符数量。
// sb.delete(1, 2);
// // 输出删除后的 Hello，核对范围删除是否正确。
// System.out.println("delete=" + sb);
// // reverse 原地反转字符顺序，Hello 变成 olleH；不用重新赋值给 sb。
// sb.reverse();
// // toString 将可修改的 builder 转成不可修改的 String；reverse 变量保存此刻的字符串快照。
// // 变量名 reverse 与方法同名并不冲突。
// String reverse = sb.toString();
// // 输出反转后的字符串 olleH，展示 String 与 StringBuilder 的转换。
// System.out.println("reverse=" + reverse);
// // Integer 是 int 的包装类；自动装箱把两个基本整数 127 转换成对象引用。
// // Java 保证 -128 到 127 的常量装箱共享对应对象；逗号分隔同类型变量声明。
// Integer a = 127, b = 127;
// // 128 超出必须缓存的范围；c 和 d 数值相同，但引用不保证相同。
// // 实现可以扩大缓存范围，所以不能用 == 比较包装类数值。
// Integer c = 128, d = 128;
// // 括号先执行 a == b，比较两个 Integer 引用是否指向同一对象；127 的常量装箱保证 true。
// System.out.println("127 == " + (a == b));
// // 比较 128 的两个引用；默认 OpenJDK 通常 false，但缓存设置可能使其 true。
// System.out.println("128 == " + (c == d));
// // equals 比较 Integer 对象的数值；c、d 都是 128，结果 true。
// // 原来比较 b（127）会误导学习者。
// System.out.println("128 equals " + c.equals(d));
// // 自动拆箱：把 Integer 对象 a 中的 127 取出并赋给基本类型 int；若引用是 null 会抛空指针异常。
// int number = a;
// // Double 是 double 包装类；valueOf 解析字符串 3.5 并返回包装对象。
// // 非法文本会抛 NumberFormatException，这里使用合法常量。
// Double price = Double.valueOf("3.5");
// // Character 是 char 包装类；单引号表示单个字符，a 通过自动装箱得到对象。
// Character ch = 'a';
// // 拼接输出拆箱数值、浮点数和字符分类；Character.isLetter 判断 ch 是否是字母，会自动拆箱。
// // 这里 a 是字母，结果 true；标签 letter= 只是文字。
// System.out.println("unbox=" + number + ",double=" + price + ",letter=" + Character.isLetter(ch));
// // Scanner 是输入扫描器；new Scanner(System.in) 创建从标准输入读取数据的对象。
// Scanner sc = new Scanner(System.in);
// // hasNextLine 检查是否仍有一整行可读；?: 三元运算符在有行时调用 nextLine，否则使用空串。
// // 避免输入结束时直接 nextLine 抛异常。
// String text = sc.hasNextLine() ? sc.nextLine() : "";
// // 先用输入文本构造新的 StringBuilder，再 reverse，最后 toString；链式调用从左到右执行。
// // 这不会修改原来的 text，下一步仍解析原输入。
// System.out.println("inputReverse=" + new StringBuilder(text).reverse().toString());
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // trim 去除文本两端的普通空白；parseInt 把十进制整数文本转换成 int。
// // 空串、非整数、超过 int 范围都会抛 NumberFormatException。
// int value = Integer.parseInt(text.trim());
// // 输出解析成功的整数 value；异常时本行不会执行。
// System.out.println("int=" + value);
// // 把 int value 自动装箱为 Integer 引用 boxed，演示基本类型到对象。
// Integer boxed = value;
// // 打印 boxed 的数值表示；println 拼接时使用其字符串表示。
// System.out.println("boxed=" + boxed);
// // 右大括号结束 try 块；catch 捕获 NumberFormatException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (NumberFormatException e) {
// // 解析失败时输出 invalid integer（无效整数），不让异常中断主程序。
// System.out.println("invalid integer");
// // 右大括号结束「catch (NumberFormatException e)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // close 关闭扫描器并关闭底层标准输入；本程序随后结束，不再读取。
// sc.close();
// // 右大括号结束「public static void main(String[] args)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「public class BuilderLesson」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
