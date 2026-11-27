public class ExceptionDemo {
    /** 0 = 正常演示；1 = 空指针；2 = 数组越界；3 = 主动抛出异常；可用 -DcrashMode=N 覆盖 */
    private static int crashMode = Integer.getInteger("crashMode", 0);
    public static void main(String[] args) {
        if (crashMode != 0) {
            crashOnPurpose(crashMode);
        } else {
            demoNullPointer();
            demoArrayIndexOutOfBounds();
            demoFinallyOrder("12");
            demoFinallyOrder("abc");
            demoNumberFormatException();
            demoThrow();
        }
    }
    /** 根据模式故意制造一个未被捕获的异常，用于演示异常调用栈 */
    private static void crashOnPurpose(int mode) {
        switch (mode) {
            case 1:
                String s = nullText();
                System.out.println(s.length());
                break;
            case 2:
                int[] arr = new int[1];
                System.out.println(arr[5]);
                break;
            case 3:
                throw new IllegalStateException("crashOnPurpose: 主动抛出的异常");
            default:
                System.out.println("未知的 CRASH_MODE: " + mode);
        }
    }
    /** 固定返回 null，保证空指针教学示例不受系统属性影响。 */
    private static String nullText() {
        return null;
    }
    /** NullPointerException 演示 */
    private static void demoNullPointer() {
        System.out.println("== demoNullPointer ==");
        String s = nullText();
        try {
            System.out.println(s.length());
        } catch (NullPointerException e) {
            System.out.println("捕获到 NullPointerException: " + e.getMessage());
        }
        System.out.println();
    }
    /** ArrayIndexOutOfBoundsException 演示 */
    private static void demoArrayIndexOutOfBounds() {
        System.out.println("== demoArrayIndexOutOfBounds ==");
        int[] arr = { 1, 2, 3 };
        try {
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获到 ArrayIndexOutOfBoundsException: 下标 5, 数组长度 " + arr.length);
        }
        System.out.println();
    }
    /** try-catch-finally 的执行顺序演示 */
    private static void demoFinallyOrder(String text) {
        System.out.println("== demoFinallyOrder(\"" + text + "\") ==");
        try {
            System.out.println("try: 开始解析");
            int value = Integer.parseInt(text);
            System.out.println("try: 解析结果 = " + value);
            return;
        } catch (NumberFormatException e) {
            System.out.println("catch: 数字格式错误 - " + e.getMessage());
        } finally {
            System.out.println("finally: 无论是否异常、是否 return 都会执行");
        }
        System.out.println();
    }
    /** NumberFormatException 演示 */
    private static void demoNumberFormatException() {
        System.out.println("== demoNumberFormatException ==");
        String text = "12abc";
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("捕获到 NumberFormatException: " + text + " 不是合法整数");
        }
        System.out.println();
    }
    /** throw / throws 用法演示 */
    private static void demoThrow() {
        System.out.println("== demoThrow ==");
        try {
            checkAge(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获到 IllegalArgumentException: " + e.getMessage());
        }
        System.out.println();
    }
    /** 参数非法时主动抛出异常 */
    private static void checkAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("年龄不能为负数: " + age);
        }
        System.out.println("年龄 = " + age);
    }
}

// // public class 定义公开类 ExceptionDemo；类是组织字段与方法的容器。
// // 公开类名必须与 .java 文件名一致；左大括号开启类体。
// public class ExceptionDemo {
// /** 0 = 正常演示；1 = 空指针；2 = 数组越界；3 = 主动抛出异常；可用 -DcrashMode=N 覆盖 */
// // private static 表示类内部共享字段；Integer.getInteger 读取名为 crashMode 的 JVM 系统属性并转换为整数，缺失或不能解析时使用默认 0。
// // java -DcrashMode=1 ExceptionDemo 可选择故意崩溃演示；不是读取标准输入。
// private static int crashMode = Integer.getInteger("crashMode", 0);
// // main 是 Java 程序的启动入口；public 允许 JVM 调用，static 表示无需创建类实例，void 表示不返回值。
// // String[] args 是命令行参数字符串数组，左大括号开始方法体。
// public static void main(String[] args) {
// // != 表示不等于；非 0 代表用户请求指定的故意崩溃模式。
// if (crashMode != 0) {
// // 将模式值传给 crashOnPurpose；已知模式会故意产生未捕获异常，便于阅读调用栈。
// crashOnPurpose(crashMode);
// // 结束前面的条件分支并开启 else；只有对应 if（或前面整个 if/else if 链）均不成立时执行此分支。
// } else {
// // 调用空指针捕获演示方法；此处异常应在方法内部被捕获。
// demoNullPointer();
// // 调用数组越界捕获演示，学习有效数组下标范围。
// demoArrayIndexOutOfBounds();
// // 用可解析文本 12 演示 try 中 return 后仍执行 finally 的正常路径。
// demoFinallyOrder("12");
// // 用不可解析文本 abc 演示 catch 和 finally 的异常路径。
// demoFinallyOrder("abc");
// // 调用数字格式异常演示，查看非法整数字符串的处理。
// demoNumberFormatException();
// // 调用主动抛出参数异常的演示，展示 throw 与 catch 配合。
// demoThrow();
// // 右大括号结束「else」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「public static void main(String[] args)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// /** 根据模式故意制造一个未被捕获的异常，用于演示异常调用栈 */
// // private static void 声明类内静态无返回值方法；mode 参数决定要演示哪种未捕获异常。
// private static void crashOnPurpose(int mode) {
// // switch 根据整数 mode 选择 case；进入匹配分支，通常用 break 避免贯穿。
// switch (mode) {
// // case 1 对应空指针演示模式，冒号标记分支入口。
// case 1:
// // nullText() 返回 null；s 没有指向实际 String 对象。
// String s = nullText();
// // 故意对 null 调用 length() 触发 NullPointerException；这里没有 catch，因此程序异常退出。
// // 本行是教学示例而非待修复的实际缺陷。
// System.out.println(s.length());
// // break 结束当前 switch；此处正常情况下不会到达，因为前行已抛异常。
// break;
// // case 2 对应数组越界演示模式。
// case 2:
// // 创建长度为 1 的 int 数组，只允许下标 0，默认元素为 0。
// int[] arr = new int[1];
// // 故意访问 arr[5]，触发 ArrayIndexOutOfBoundsException；这是未捕获的教学异常。
// System.out.println(arr[5]);
// // break 防止正常执行时落入其他 case；本示例中通常不会到达。
// break;
// // case 3 对应主动抛出异常演示。
// case 3:
// // throw 创建并抛出 IllegalStateException，异常消息用于说明原因；当前方法立即异常退出。
// throw new IllegalStateException("crashOnPurpose: 主动抛出的异常");
// // default 在没有任何匹配 case 时执行，用来处理未知模式值。
// default:
// // 打印未知模式的提示，不人为制造异常。
// System.out.println("未知的 CRASH_MODE: " + mode);
// // 右大括号结束「switch (mode)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「private static void crashOnPurpose(int mode)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// /** 固定返回 null，保证空指针教学示例不受系统属性影响。 */
// // private static String 声明返回 String 引用的类内静态方法；专门用于制造确定性的 null。
// private static String nullText() {
// // return null 固定返回空引用，而不是空字符串；不读取外部属性，确保每次空指针演示都能复现。
// return null;
// // 右大括号结束「private static String nullText()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// /** NullPointerException 演示 */
// // 声明无参数无返回值的空指针演示方法；内部捕获对应异常。
// private static void demoNullPointer() {
// // println 输出演示标题并换行，便于区分多组示例。
// System.out.println("== demoNullPointer ==");
// // 将 nullText 返回的 null 保存到 String 引用 s；此赋值合法，使用时才可能出错。
// String s = nullText();
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // 对 null 调用 length() 故意触发空指针异常，println 本身尚未来得及输出数值。
// System.out.println(s.length());
// // 右大括号结束 try 块；catch 捕获 NullPointerException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (NullPointerException e) {
// // 捕获后调用 e.getMessage() 获取 JVM 提供的诊断文字，并打印提示；不同 JDK 文案可能不同。
// System.out.println("捕获到 NullPointerException: " + e.getMessage());
// // 右大括号结束「catch (NullPointerException e)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 输出空行分隔后续演示；异常已被 catch 处理，程序可以继续执行。
// System.out.println();
// // 右大括号结束「private static void demoNullPointer()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// /** ArrayIndexOutOfBoundsException 演示 */
// // 声明数组越界演示方法；它不返回数据，只展示异常处理。
// private static void demoArrayIndexOutOfBounds() {
// // 打印演示标题，帮助定位运行输出。
// System.out.println("== demoArrayIndexOutOfBounds ==");
// // 初始化数组 1、2、3；有效下标是 0、1、2，长度为 3。
// int[] arr = { 1, 2, 3 };
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // 故意读取下标 5 触发越界异常，展示 Java 运行时的数组边界保护。
// System.out.println(arr[5]);
// // 右大括号结束 try 块；catch 捕获 ArrayIndexOutOfBoundsException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (ArrayIndexOutOfBoundsException e) {
// // 捕获越界异常后输出非法下标和实际长度；说明错误原因而不终止演示。
// System.out.println("捕获到 ArrayIndexOutOfBoundsException: 下标 5, 数组长度 " + arr.length);
// // 右大括号结束「catch (ArrayIndexOutOfBoundsException e)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 输出空行，让下一部分输出更清楚。
// System.out.println();
// // 右大括号结束「private static void demoArrayIndexOutOfBounds()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// /** try-catch-finally 的执行顺序演示 */
// // 声明接收 String 参数 text 的方法，用同一逻辑演示成功和失败路径。
// private static void demoFinallyOrder(String text) {
// // 输出方法名及传入文本；\\" 是转义写法，在字符串中插入双引号。
// System.out.println("== demoFinallyOrder(\"" + text + "\") ==");
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // 先输出 try 已经开始的提示，用于观察执行先后顺序。
// System.out.println("try: 开始解析");
// // parseInt 将 text 解析成 int；合法时得到数字，非法时跳转到匹配的 catch。
// int value = Integer.parseInt(text);
// // 只有解析成功才输出 value；解析失败时本行会被跳过。
// System.out.println("try: 解析结果 = " + value);
// // return 请求提前结束 void 方法；但离开 try 前仍会执行 finally（正常 JVM 控制流）。
// return;
// // 右大括号结束 try 块；catch 捕获 NumberFormatException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (NumberFormatException e) {
// // 打印数字格式异常信息，说明失败路径进入 catch 而不是执行 try 剩余语句。
// System.out.println("catch: 数字格式错误 - " + e.getMessage());
// // 结束前一个处理块并开启 finally 清理块；常规 JVM 控制流中无论正常、异常或 return 离开 try/catch，都执行此块。
// } finally {
// // 输出 finally 的执行提示；通常在正常结束、异常或 return 离开 try/catch 时执行。
// // JVM 强制终止等特殊情况不能保证 finally 执行。
// System.out.println("finally: 无论是否异常、是否 return 都会执行");
// // 右大括号结束「finally」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 只有没有提前 return 且异常被处理后继续执行时，才到达这里输出空行。
// System.out.println();
// // 右大括号结束「private static void demoFinallyOrder(String text)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// /** NumberFormatException 演示 */
// // 声明数字格式异常演示方法，专门处理不合法的整数文本。
// private static void demoNumberFormatException() {
// // 打印当前演示标题。
// System.out.println("== demoNumberFormatException ==");
// // text 保存含英文字母的文本 12abc，它不是合法十进制整数。
// String text = "12abc";
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // 故意调用 parseInt；解析会失败，抛出 NumberFormatException。
// Integer.parseInt(text);
// // 右大括号结束 try 块；catch 捕获 NumberFormatException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (NumberFormatException e) {
// // 捕获异常后打印包含原文本的错误提示，而非中断程序。
// System.out.println("捕获到 NumberFormatException: " + text + " 不是合法整数");
// // 右大括号结束「catch (NumberFormatException e)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 输出空行分隔演示。
// System.out.println();
// // 右大括号结束「private static void demoNumberFormatException()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// /** throw / throws 用法演示 */
// // 声明主动抛出异常的演示方法，展示调用者如何捕获被调用方法的异常。
// private static void demoThrow() {
// // 打印当前演示标题。
// System.out.println("== demoThrow ==");
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // 调用 checkAge(-1)；负数年龄违反方法的约定，因此会抛出异常。
// checkAge(-1);
// // 右大括号结束 try 块；catch 捕获 IllegalArgumentException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (IllegalArgumentException e) {
// // 捕获 IllegalArgumentException 并输出它的消息；调用者可以继续运行。
// System.out.println("捕获到 IllegalArgumentException: " + e.getMessage());
// // 右大括号结束「catch (IllegalArgumentException e)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 输出空行，结束此组演示。
// System.out.println();
// // 右大括号结束「private static void demoThrow()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// /** 参数非法时主动抛出异常 */
// // checkAge 接收 int 年龄参数，无返回值；此示例仅拒绝负数，不是完整年龄验证系统。
// private static void checkAge(int age) {
// // age<0 判断年龄是否为负；条件成立代表参数无效。
// if (age < 0) {
// // throw new 创建 IllegalArgumentException 并立即抛出，带上非法年龄；此异常是非受检异常，无需 throws 声明。
// throw new IllegalArgumentException("年龄不能为负数: " + age);
// // 右大括号结束「if (age < 0)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 年龄未违反负数限制时才会执行此行，输出合法年龄值。
// System.out.println("年龄 = " + age);
// // 右大括号结束「private static void checkAge(int age)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「public class ExceptionDemo」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
