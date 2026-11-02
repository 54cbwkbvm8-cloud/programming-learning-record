public class BasicTypes {
    public static void main(String[] args) {
        int age = 18;
        System.out.println("年龄：" + age + "岁");
        byte byteNum = 100;
        short shortNum = 30000;
        int intNum = 2000000000;
        long longNum = 1000000000L;
        float floatNum = 3;
        double doubleNum = 3.14159265358979;
        char letter = 'A';
        boolean isOk = true;
        String text = "Java";
        System.out.println("byte = " + byteNum);
        System.out.println("short = " + shortNum);
        System.out.println("int = " + intNum);
        System.out.println("long = " + longNum);
        System.out.println("float = " + floatNum);
        System.out.println("double = " + doubleNum);
        System.out.println("char = " + letter);
        System.out.println("boolean = " + isOk);
        System.out.println("String = " + text);
        System.out.println("byte范围：" + Byte.MIN_VALUE + "~" + Byte.MAX_VALUE);
        System.out.println("short范围：" + Short.MIN_VALUE + "~" + Short.MAX_VALUE);
        System.out.println("int范围：" + Integer.MIN_VALUE + "~" + Integer.MAX_VALUE);
        int small = 100;
        long bigger = small;
        double decimal = small;
        int letterCode = 'A' + 1;
        System.out.println("long bigger = " + bigger);
        System.out.println("double decimal = " + decimal);
        System.out.println("'A' + 1 = " + letterCode);
        System.out.println("7 / 2 = " + (7 / 2));
        System.out.println("7 / 2.0 = " + (7 / 2.0));
        double price = 3.99;
        int cut = (int) price;
        int number = 130;
        byte squeezed = (byte) number;
        char nextLetter = (char) ('A' + 1);
        System.out.println("(int)3.99 = " + cut);
        System.out.println("(byte)130 = " + squeezed);
        System.out.println("(char)66 = " + nextLetter);
        int maxInt = Integer.MAX_VALUE;
        int overflow = maxInt + 1;
        System.out.println("maxInt = " + maxInt);
        System.out.println("maxInt + 1 = " + overflow);
        System.out.println("maxInt 二进制 = " + Integer.toBinaryString(maxInt));
        System.out.println("overflow 二进制 = " + Integer.toBinaryString(overflow));
        byte maxByte = 127;
        byte wrapped = (byte) (maxByte + 1);
        System.out.println("(byte) (127 + 1) = " + wrapped);
        long wrongLong = 2147483647 + 1;
        long rightLong = 2147483647L + 1;
        System.out.println("long wrongLong = " + wrongLong);
        System.out.println("long rightLong = " + rightLong);
    }

}
// /*
// *
// ============================================================================
// * 程序名称：BasicTypes —— Java 基本数据类型演示
// *
// ============================================================================
// *
// * 【这个程序是干什么的？】
// * 它把 Java 里最基础的"数据类型"挨个演示一遍，包括：
// * 1. 有哪些数据类型，各自能存什么；
// * 2. 不同类型之间怎么互相转换；
// * 3. 数字存不下时会发生什么怪事（这叫"溢出"）。
// * 运行之后，程序会在屏幕上一行一行打印结果，你对照代码和注释看，就能明白每一行在干嘛。
// *
// * 【怎么运行？】（在终端里，先 cd 到这个文件所在的文件夹）
// * javac BasicTypes.java ← 第一步：编译。把人能看懂的代码翻译成电脑能执行的文件
// * java BasicTypes ← 第二步：运行。注意这里没有 .java 后缀
// *
// * 【关于这种注释】
// * 你现在看到的这一大段，是用 斜杠+星号 开头、星号+斜杠 结尾 的"多行注释"。
// * 注释是写给人看的说明文字，电脑会完全忽略它，所以随便写多少都不影响程序运行。
// * 另一种是以两个斜杠 // 开头的"单行注释"：从 // 开始，到这一行结束，都是注释。
// *
// * 【这个文件的说明】
// * 代码本身没有任何错误，可以正常编译和运行，下面所有的代码我一个字都没有改，只是加了注释。
// * 每一行代码上方（或右侧）的注释，就是对这行代码的解释。
// */

// // 【类】Java 的代码必须写在"类"里面，class 就是"类"的意思，可以先把它理解成"一个装代码的盒子"。
// // public 表示这个盒子是公开的；BasicTypes 是盒子的名字。
// // 【重要规则】文件名必须和 public 类的名字完全一样（包括大小写），
// // 所以这个文件必须叫 BasicTypes.java，改了名字就会编译报错。
// // 大括号 { } 表示"从这里开始，到对应的 } 结束"，括起来的内容都属于这个盒子。
// public class BasicTypes {

// // 【main 方法】程序的"大门"。你运行程序时，Java 一定是从这个 main 开始，一行一行往下执行。
// // 现在不用深究每个单词的含义，先记住：想让程序跑起来，就把代码写在 main 后面的大括号里。
// // public static void main(String[] args) 这一整行写法是固定的，一个字母都不要改。
// // （args 是运行程序时可以从外面传进来的参数，目前用不到，先忽略。）
// public static void main(String[] args) {

// // ====================================================================
// // 第一部分：变量入门
// // ====================================================================

// // 【变量】可以把变量想成"贴了标签的盒子"，盒子里能放一个数据，标签就是变量的名字。
// // 创建变量的写法： 类型 变量名 = 值;
// // int → 类型：整数。表示"这个盒子只能放整数"
// // age → 变量名：盒子上的标签，以后用 age 就能找到这个盒子里的数据
// // = → 注意！在编程里它不叫"等于"，而叫"赋值"：把右边的值放进左边的盒子
// // 18 → 要放进去的值
// // ; → 分号：每一条语句结束都要写英文分号，就像一句话结尾要写句号
// int age = 18;

// // 【打印】System.out.println(...) 的作用：把括号里的内容显示在屏幕上，显示完自动换行。
// // 双引号 "" 括起来的是文字，原样显示，比如 "年龄：" 就会显示成 年龄：
// // 没有引号的 age 是变量名，程序会去盒子里把里面的值（18）取出来显示。
// // 【+ 号】当 + 的旁边有文字时，它的作用是"拼接"：把几段内容首尾连起来变成一段文字。
// // 所以这一行最终显示：年龄：18岁
// System.out.println("年龄：" + age + "岁");

// // ====================================================================
// // 第二部分：8 种基本数据类型 + String
// // ====================================================================
// // Java 一共有 8 种"基本数据类型"，分成四组：
// // 整数 4 种：byte、short、int、long （区别只是"盒子大小"不同，能装的数字范围不同）
// // 小数 2 种：float、double
// // 字符 1 种：char
// // 真假 1 种：boolean
// // 盒子越大能装的范围越大，但占用的内存也越多。这里的"字节"是内存的计量单位。

// // 【byte】占 1 个字节，只能装 -128 ~ 127 之间的整数。最小的整数盒子。
// // 100 在这个范围内，所以可以放进去。（如果写 byte x = 200; 就会编译报错，因为装不下。）
// byte byteNum = 100;

// // 【short】占 2 个字节，能装 -32768 ~ 32767。比 byte 大一点，日常用得很少。
// short shortNum = 30000;

// // 【int】占 4 个字节，能装大约 -21 亿 ~ 21 亿。这是最常用的整数类型。
// // 默认情况下，你直接写出来的整数（比如 18、2000000000），Java 都当作 int。
// int intNum = 2000000000;

// // 【long】占 8 个字节，能装大约 ±922 亿亿（-9223372036854775808 ~ 9223372036854775807）。
// // 当数字超过 int 的 21 亿上限时，就要用 long。
// // 【L 后缀】数字末尾加上大写 L，是告诉 Java："这个数字是 long 类型的"。
// // 这里的 10 亿其实 int 也装得下，加 L 是明确标出它是 long（推荐用大写 L，因为小写 l 太像数字 1）。
// // 但如果数字超过了 21 亿（比如 10000000000，100 亿），就必须加 L，
// // 否则 Java 会先把它当 int，装不下，直接编译报错：integer number too large。
// long longNum = 1000000000L;

// // 【float】单精度小数，占 4 个字节，大约能保证 7 位有效数字。
// // 这里给它的值是 3，是一个整数（int）。int 放进 float 属于"小盒子放进大盒子"，
// // Java 会自动转换，所以不会报错，转换后 3 变成小数 3.0，打印出来是 3.0（见下面的打印）。
// // 【F 后缀】如果想直接给 float 写一个小数，要在末尾加 F，例如 3.14F。
// // 因为 Java 里直接写的小数（如 3.14）默认是 double，比 float 大，
// // 硬塞进 float 会报错；加了 F，才是告诉 Java："这个小数我就是要当 float 用"。
// float floatNum = 3;

// // 【double】双精度小数，占 8 个字节，大约能保证 15~16 位有效数字。
// // 它是 Java 里小数的默认类型，写小数不加任何后缀就是 double。
// // 精度要求高、或者不确定用哪个时，用 double 就对了。
// double doubleNum = 3.14159265358979;

// // 【char】字符类型，只能装"一个"字符（一个字母、一个数字、一个汉字、一个符号都行）。
// // 【重要】char 的值要用"单引号"括起来：'A'。
// // 双引号和单引号不能混用：'A' 是一个字符，"A" 是一段文字（字符串），它们是两种不同的东西。
// char letter = 'A';

// // 【boolean】布尔类型，只有两个值：true（真）和 false（假）。
// // 常用来表示"是不是""对不对"，以后写 if 判断、循环时会大量用到。
// // 注意：true 和 false 全是小写，而且不用加引号。
// boolean isOk = true;

// // 【String】字符串，用来存一段文字。注意：String 的 S 是大写的！
// // 【重要】String 不属于 8 种基本数据类型，它是 Java 提供的一个"类"（可以先理解成"一种更复杂的类型"）。
// // 文字要用"双引号"括起来："Java"。
// String text = "Java";

// // ---------- 把上面每个变量里的值打印出来 ----------
// // 下面每一行的写法都一样："说明文字" + 变量名，意思是：先显示说明文字，再显示变量里的值。
// System.out.println("byte = " + byteNum); // 显示：byte = 100
// System.out.println("short = " + shortNum); // 显示：short = 30000
// System.out.println("int = " + intNum); // 显示：int = 2000000000
// System.out.println("long = " + longNum); // 显示：long = 1000000000
// System.out.println("float = " + floatNum); // 显示：float = 3.0（整数 3 自动转成了小数
// 3.0）
// System.out.println("double = " + doubleNum); // 显示：double = 3.14159265358979
// System.out.println("char = " + letter); // 显示：char = A
// System.out.println("boolean = " + isOk); // 显示：boolean = true
// System.out.println("String = " + text); // 显示：String = Java

// // ====================================================================
// // 第三部分：查看各类型的取值范围
// // ====================================================================
// // Java 给每种整数类型都准备了两个"现成的值"：MIN_VALUE（最小值）和 MAX_VALUE（最大值），
// // 写法是： 类型的包装名.MIN_VALUE / 类型的包装名.MAX_VALUE
// // 包装名：byte 对应 Byte，short 对应 Short，int 对应 Integer，long 对应 Long。
// // 中间的小数点 . 可以读作"的"：Byte.MAX_VALUE 就是 "Byte 的最大值"。
// System.out.println("byte范围：" + Byte.MIN_VALUE + "~" + Byte.MAX_VALUE); //
// 显示：-128~127
// System.out.println("short范围：" + Short.MIN_VALUE + "~" + Short.MAX_VALUE); //
// 显示：-32768~32767

// // 【易错点】int 的包装名是 Integer（不是 Int），而且左右两边要用同一种类型：
// // 这里 MIN_VALUE 和 MAX_VALUE 都来自 Integer，所以打印出来的是真正的 int 范围。
// // 如果右边误写成 Long.MAX_VALUE，就会把 long 的最大值（9223372036854775807）混进来，范围就错了。
// System.out.println("int范围：" + Integer.MIN_VALUE + "~" + Integer.MAX_VALUE);
// // 显示：-2147483648~2147483647

// // ====================================================================
// // 第四部分：自动类型转换（小盒子 → 大盒子，Java 自动帮你完成）
// // ====================================================================
// // 把"小盒子"的值放进"大盒子"，是绝对安全的（大盒子肯定装得下），所以 Java 会自动转换，不用你操心。
// // 盒子从小到大的顺序：byte → short → int → long → float → double

// int small = 100;

// // int 放进 long：int 是小盒子，long 是大盒子，Java 自动转换，不需要任何额外写法。
// // 转换后值不变，还是 100，只是现在装在一个更大的 long 盒子里。
// long bigger = small;

// // int 放进 double：同样是小盒子放进大盒子，自动转换。
// // 转换后整数 100 变成小数 100.0（所以打印出来会带 .0）。
// double decimal = small;

// // 【字符也是数字】电脑内部其实只认识数字，每个字符都对应一个编号。
// // 比如字符 'A' 的编号是 65，'B' 是 66，'a' 是 97。
// // 所以当 'A' 参与数学计算时，Java 会拿它的编号 65 去算：'A' + 1 就是 65 + 1，结果是 66。
// // 计算结果是整数，所以要放进 int 变量。
// int letterCode = 'A' + 1;

// System.out.println("long bigger = " + bigger); // 显示：long bigger = 100
// System.out.println("double decimal = " + decimal); // 显示：double decimal =
// 100.0

// // 这里文字里的单引号 ' 只是普通符号，直接原样显示出来。
// System.out.println("'A' + 1 = " + letterCode); // 显示：'A' + 1 = 66

// // 【整数除法】两边都是整数时，结果也只会是整数，小数部分会被直接丢掉（不是四舍五入！）。
// // 7 ÷ 2 数学上是 3.5，但在 Java 里 7 / 2 的结果是 3。
// // 括号的作用：让 7 / 2 先算出结果，再和前面的文字拼接。
// // （如果不加括号，+ 号会先把 "7 / 2 = " 和 7 拼成文字，再拼 2，结果就乱了。）
// System.out.println("7 / 2 = " + (7 / 2)); // 显示：7 / 2 = 3

// // 只要参与计算的有一个是小数（2.0），结果就会变成小数，这样才能得到精确的 3.5。
// System.out.println("7 / 2.0 = " + (7 / 2.0)); // 显示：7 / 2.0 = 3.5

// // ====================================================================
// // 第五部分：强制类型转换（大盒子 → 小盒子，需要你亲自"下命令"）
// // ====================================================================
// // 把"大盒子"的值放进"小盒子"，可能装不下，有丢失数据的风险，所以 Java 不会自动帮你做，
// // 你必须自己在值的前面写 (目标类型)，表示"我知道有风险，我负责，就这么转"。
// // 这个写法叫"强制类型转换"，简称"强转"。

// double price = 3.99;

// // (int) price：把小数强转成整数。
// // 【注意】强转是直接砍掉小数部分，不是四舍五入！所以 3.99 变成 3，而不是 4。
// int cut = (int) price;

// int number = 130;

// // (byte) number：把 int 强转成 byte。
// // 可是 byte 最大只能装 127，130 装不下，会发生"溢出"，结果变成 -126。
// // 原因（想看的话再看）：
// // int 有 32 位二进制，130 的二进制是 00000000 00000000 00000000 10000010。
// // 转成 byte 时，只保留最后 8 位：10000010。
// // 而 byte 的最高位是"符号位"，1 表示负数，所以这 8 位被解释成了负数 -126。
// // 也可以这样记：130 比 byte 的最大值 127 多出 3，就从最小值 -128 开始"绕一圈"往回算，
// // 128 → -128，129 → -127，130 → -126。
// byte squeezed = (byte) number;

// // ('A' + 1) 先算出 66，(char) 再把整数 66 强转回字符，也就是编号 66 对应的字符 'B'。
// // 这样就实现了"字母 A 的下一个字母"。
// char nextLetter = (char) ('A' + 1);

// System.out.println("(int)3.99 = " + cut); // 显示：(int)3.99 = 3
// System.out.println("(byte)130 = " + squeezed); // 显示：(byte)130 = -126
// System.out.println("(char)66 = " + nextLetter); // 显示：(char)66 = B

// // ====================================================================
// // 第六部分：溢出——数字存不下时会"绕圈"
// // ====================================================================
// // 【溢出】计算结果超出了类型能装下的范围，Java 不会报错，而是悄悄地"绕一圈"，得到一个意想不到的值。
// // 可以想象汽车的里程表：999999 公里再走 1 公里，数字会变成 000000，从头开始。
// // int 也是这样：到了最大值再 +1，就会跳到最小值。

// // Integer.MAX_VALUE 就是 int 的最大值 2147483647。
// int maxInt = Integer.MAX_VALUE;

// // 最大值再加 1，装不下了，于是"绕圈"变成了最小值 -2147483648。
// int overflow = maxInt + 1;

// // 注意区分这两行：第一行打印的是 maxInt 本身（没加 1），第二行打印的才是加 1 之后的 overflow。
// System.out.println("maxInt = " + maxInt); // 显示：maxInt = 2147483647
// System.out.println("maxInt + 1 = " + overflow); // 显示：maxInt + 1 =
// -2147483648

// // Integer.toBinaryString(...)：把一个整数转换成"二进制"文字（只由 0 和 1 组成，电脑内部就是这样存数字的）。
// // 注意：它不会显示最前面多余的 0。
// // maxInt 的二进制：最高位是 0，后面 31 个 1（最高位的 0 不显示，所以看到的是 31 个 1）。
// // overflow 的二进制：最高位是 1，后面 31 个 0，总共 32 位。
// // 对比一下就明白了："0111...1" 加 1 以后，每一位都向前进位，变成了 "1000...0"。
// // 最高位是"符号位"：0 代表正数，1 代表负数，所以结果就从最大的正数跳到了最小的负数。
// System.out.println("maxInt 二进制 = " + Integer.toBinaryString(maxInt));
// System.out.println("overflow 二进制 = " + Integer.toBinaryString(overflow));

// // ---------- byte 的溢出 ----------
// byte maxByte = 127; // byte 的最大值

// // 这一行有个小细节：maxByte + 1 在 Java 里会先自动提升成 int 来计算，结果是 int 型的 128（没有溢出）。
// // 但 byte 装不下 128，所以要用 (byte) 强转，强转时才发生溢出，128 绕圈变成了 -128。
// // 括号写成 (byte) (maxByte + 1)，是为了让整个加法的结果一起被强转。
// byte wrapped = (byte) (maxByte + 1);
// System.out.println("(byte) (127 + 1) = " + wrapped); // 显示：(byte) (127 + 1) =
// -128

// // ---------- 经典陷阱：放进 long 也不一定安全 ----------

// // 陷阱写法：2147483647 和 1 都是普通整数，默认是 int，
// // 所以 Java 会"先用 int 算"：2147483647 + 1 在 int 里溢出，得到 -2147483648；
// // 然后才把这个已经算错的结果放进 long 盒子。
// // 也就是说：盒子是 long 的，但是计算过程是 int 的，等溢出发生了才放进盒子，已经晚了。
// long wrongLong = 2147483647 + 1;

// // 正确写法：在其中一个数后面加 L，让它变成 long。
// // 只要参与计算的有一个 long，整个计算就按 long 来算，long 能装下 2147483648，所以结果正确。
// long rightLong = 2147483647L + 1;

// System.out.println("long wrongLong = " + wrongLong); // 显示：long wrongLong =
// -2147483648

// // 注意：这里用的是 println（打印后自动换行），而不是 print（打印后不换行）。
// // 最后一行如果用 print，程序结束后终端的提示符会紧贴在输出后面，不好看，所以用 println 更合适。
// System.out.println("long rightLong = " + rightLong); // 显示：long rightLong =
// 2147483648

// } // ← 这个大括号是 main 方法的结尾，和上面 main 后面的 { 配成一对

// } // ← 这个大括号是类 BasicTypes 的结尾，和上面 class 那一行后面的 { 配成一对
