public class SwitchBreakDemo {
    public static void main(String[] args) {
        int day = 2;
        System.out.println("【反例1】day = " + day + "，（缺少break语句）期望只输出：星期二");
        switch (day) {
            case 1:
                System.out.println("星期一");
            case 2:
                System.out.println("星期二");
            case 3:
                System.out.println("星期三");
            case 4:
                System.out.println("星期四");
            case 5:
                System.out.println("星期五");
            case 6:
                System.out.println("星期六");
            case 7:
                System.out.println("星期日");
            default:
                System.out.println("无效的日期");
        }
        char level = 'B';
        System.out.println("【反例2】level = " + level + "，（break语句缺少）期望只输出：良好");
        switch (level) {
            case 'A':
                System.out.println("优秀");
                break;
            case 'B':
                System.out.println("良好");
            case 'C':
                System.out.println("及格");
                break;
            case 'D':
                System.out.println("不及格");
            default:
                System.out.println("无效的等级");
                break;
        }
        int x = 9;
        System.out.println("【反例3】x = " + x + "，（break语句缺少）期望只输出：default");
        switch (x) {
            default:
                System.out.println("default：x 不是1，也不是2");
            case 1:
                System.out.println("case 1");
                break;
            case 2:
                System.out.println("case 2");
            case 3:
                System.out.println("case 3");
                break;
        }
        System.out.println("【正确写法】day = " + day + "，每个case末尾都加上break");
        switch (day) {
            case 1:
                System.out.println("星期一");
                break;
            case 2:
                System.out.println("星期二");
                break;
            case 3:
                System.out.println("星期三");
                break;
            case 4:
                System.out.println("星期四");
                break;
            case 5:
                System.out.println("星期五");
                break;
            case 6:
                System.out.println("星期六");
                break;
            case 7:
                System.out.println("星期日");
                break;
            default:
                System.out.println("输入错误");
        }
    }
}
// /*
// * ==================== 程序说明（先读这里）====================
// * 文件名：SwitchBreakDemo.java
// * 功能： 演示 switch 语句里 break 的作用，以及「忘记写 break 会发生什么」。
// * 不需要键盘输入，直接运行即可。程序一共做了 4 个实验：
// * 【反例1】switch 的每个 case 都没有 break → 从匹配处开始一路「穿透」到最后
// * 【反例2】只有部分 case 漏写 break → 只在漏写的地方穿透
// * 【反例3】default 写在最前面，并且没有 break → 没匹配上时跳到 default，再继续往下穿透
// * 【正确写法】每个 case 末尾都写 break → 只输出想要的那一项
// *
// * 先记住 switch 的工作方式（这是理解本程序的关键）：
// * 1. switch (表达式) 会先算出括号里表达式的值；
// * 2. 然后拿这个值去和每个 case 后面的值比较，找到第一个相等的 case，【从那一行开始往下执行】；
// * 3. 一直执行到遇到 break（跳出 switch）或者到整个 switch 结束为止；
// * 4. 遇到下一个 case 时【不会】重新判断，而是照样继续往下执行，这个现象叫「穿透」（fall-through）；
// * 5. 如果所有 case 都没有匹配，就跳到 default 开始执行。
// * 所以：break 就是「到此为止，离开 switch」的刹车。
// *
// * 运行后屏幕上的完整输出（每一行都是真实运行的结果）：
// * 【反例1】day = 2，（缺少break语句）期望只输出：星期二
// * 星期二
// * 星期三
// * 星期四
// * 星期五
// * 星期六
// * 星期日
// * 无效的日期
// * 【反例2】level = B，（break语句缺少）期望只输出：良好
// * 良好
// * 及格
// * 【反例3】x = 9，（break语句缺少）期望只输出：default
// * default：x 不是1，也不是2
// * case 1
// * 【正确写法】day = 2，每个case末尾都加上break
// * 星期二
// * 阅读提示：
// * 所有以 // 开头的内容都叫「注释」，是写给人看的说明，电脑运行时会完全忽略它们。
// * ==========================================================
// */

// // class 表示「类」，Java 的所有代码都要放在类里，可以把类想象成一个「装代码的盒子」。
// // public 表示「公开的」；SwitchBreakDemo 是类名，必须和文件名 SwitchBreakDemo.java
// 完全一致（包括大小写）。
// // 最后的 { 表示类的内容从这里开始，它和文件最后一行的 } 配成一对。
// public class SwitchBreakDemo {
// // 程序的「入口」：Java 运行时总是从 main 方法的第一行开始，一行一行往下执行。
// // public —— 公开，让 Java 虚拟机能找到它；
// // static —— 静态，不用先创建对象就能直接调用（程序刚启动时还没有任何对象）；
// // void —— 执行完不返回任何结果；
// // main —— 固定的名字，一个字母都不能错；
// // String[] args —— 接收命令行传进来的内容（字符串数组），本程序用不到，但必须写上。
// // 最后的 { 表示 main 方法的内容从这里开始。
// public static void main(String[] args) {
// // ==================== 实验一：【反例1】完全没有 break ====================

// // 声明一个 int（整数类型）的变量 day，把 2 存进去，表示「星期二」。
// // int —— 整数类型；day —— 变量名；= —— 赋值号（把右边的值存进左边的变量）；; —— 语句结束。
// int day = 2;
// // System.out.println(...)：在屏幕上打印括号里的内容，并自动换行。
// // System 是系统类，out 表示标准输出（屏幕），println 是 print line（打印一行）的缩写。
// // 双引号 "..." 包起来的是「字符串」，会原样显示。
// // 字符串旁边的 + 号表示「拼接」：把变量 day 的值（2）变成文字，和前后的字符串连成一整句。
// // 屏幕输出：【反例1】day = 2，（缺少break语句）期望只输出：星期二
// System.out.println("【反例1】day = " + day + "，（缺少break语句）期望只输出：星期二");
// // switch 语句开始：先算出括号里 day 的值（是 2），然后去找匹配的 case。
// // 最后的 { 表示 switch 的内容从这里开始，和后面对应的 } 配成一对。
// switch (day) {
// // 「case 1:」的意思是：如果 day 等于 1，就从这里开始执行。day 是 2，不等于 1，所以这里不匹配，跳过。
// case 1:
// // 这行属于 case 1，因为没有匹配，所以不会执行。（但如果执行了，会打印「星期一」。）
// System.out.println("星期一");
// // 「case 2:」：day 等于 2，匹配成功！从这一行开始往下执行，一直执行下去。
// // 注意：case 1 里面也没有 break，但它没匹配，所以只是被跳过，不影响匹配 case 2。
// case 2:
// // 匹配成功后执行的第一行，打印「星期二」。这正是我们想要的结果。
// // 屏幕输出：星期二
// System.out.println("星期二");
// // 问题出在这里：上面 case 2 的末尾【没有 break】，所以程序不会停，也不会重新判断 case 3 是否匹配，
// // 而是「穿透」进来，把 case 3 里的代码也执行了。
// case 3:
// // 因为穿透，这一行被执行了。
// // 屏幕输出：星期三
// System.out.println("星期三");
// // 还是没有 break，继续穿透进 case 4。
// case 4:
// // 屏幕输出：星期四
// System.out.println("星期四");
// // 继续穿透进 case 5。
// case 5:
// // 屏幕输出：星期五
// System.out.println("星期五");
// // 继续穿透进 case 6。
// case 6:
// // 屏幕输出：星期六
// System.out.println("星期六");
// // 继续穿透进 case 7。
// case 7:
// // 屏幕输出：星期日
// System.out.println("星期日");
// // 连 default 也会被穿透进来！default 的意思是「所有 case 都没匹配时才执行」，
// // 但穿透是「无视条件继续往下执行」，所以即使 day 明明匹配了 case 2，default 里的代码也照样被执行了。
// default:
// // 这行本来只该在「日期无效」时执行，现在却被穿透执行了，结果显然不对。
// // 屏幕输出：无效的日期
// System.out.println("无效的日期");
// } // 结束第一个 switch。小结：缺少 break，导致输出了 7 行，而不是期望的 1 行

// // ==================== 实验二：【反例2】部分 case 漏写 break ====================

// // 声明一个 char（字符类型）的变量 level，存放等级。
// // char —— 字符类型，只能存【一个】字符；
// // 'B' —— 字符要用【单引号】括起来（字符串才用双引号），这里的 'B' 就是一个字符 B。
// char level = 'B';
// // 打印实验标题，把 level 的值拼接进去。因为是和字符串拼接，所以 level 显示为字母 B。
// // 屏幕输出：【反例2】level = B，（break语句缺少）期望只输出：良好
// System.out.println("【反例2】level = " + level + "，（break语句缺少）期望只输出：良好");
// // switch 同样可以判断 char 类型。
// // （switch 括号里能放的类型有：byte、short、char、int，它们对应的包装类，以及 String 和枚举；
// // long、float、double、boolean 不可以。）
// // 这里先算出 level 的值是 'B'，然后去找匹配的 case。
// switch (level) {
// // 如果 level 等于 'A'，从这里开始执行。level 是 'B'，不匹配，跳过。
// case 'A':
// // 没有匹配，不执行。
// System.out.println("优秀");
// // 这里有 break：如果匹配了 'A'，打印完就跳出 switch。现在没有匹配，所以也不执行。
// break;
// // 如果 level 等于 'B'：匹配成功！从这里开始往下执行。
// case 'B':
// // 打印「良好」，这是我们想要的结果。
// // 屏幕输出：良好
// System.out.println("良好");
// // 问题出在这里：case 'B' 的末尾【漏写了 break】，所以程序会穿透进下一个 case 'C'。
// case 'C':
// // 因为穿透，这一行也被执行了。
// // 屏幕输出：及格
// System.out.println("及格");
// // 这里有 break：执行到这里，跳出整个 switch，穿透到此为止。
// // 所以后面的 case 'D' 和 default 不会再执行。
// break;
// // 下面的 case 'D' 和 default 在这次运行中不会被执行到，因为上面已经 break 跳出去了。
// case 'D':
// // 不执行。
// System.out.println("不及格");
// // default 的意思是「没有任何 case 匹配时执行」。
// default:
// // 不执行。
// System.out.println("无效的等级");
// // 跳出 switch。default 在最后，这个 break 其实可以省略，但写上是好习惯。
// break;
// } // 结束第二个 switch。小结：只漏写一个 break，就多输出了一行「及格」

// // ==================== 实验三：【反例3】default 写在最前面 ====================

// // 声明 int 变量 x，值是 9。下面的 switch 里没有任何 case 等于 9。
// int x = 9;
// // 打印实验标题，把 x 的值（9）拼接进去。
// // 屏幕输出：【反例3】x = 9，（break语句缺少）期望只输出：default
// System.out.println("【反例3】x = " + x + "，（break语句缺少）期望只输出：default");
// // switch 开始：先算出 x 的值是 9。
// // 重点：Java 会先检查【所有的 case】有没有和 9 相等的；全都不相等，才会跳到 default。
// // 也就是说，default 写在哪个位置都行，它只有在「其他 case 都没匹配」时才会被跳到。
// switch (x) {
// // default 写在了最前面，但它不会因为位置靠前就先执行。
// // 此处 x 是 9，case 1、2、3 都不匹配，所以程序【跳到这里】开始执行。
// default:
// // 打印这一行，这是我们期望的输出。
// // 屏幕输出：default：x 不是1，也不是2
// System.out.println("default：x 不是1，也不是2");
// // 问题出在这里：default 的末尾【没有 break】，所以会穿透进下面的 case 1。
// // 穿透的规则是「不管 case 后面的值是不是匹配，都照样往下执行」。
// case 1:
// // 因为穿透，这一行也被执行了（尽管 x 根本不等于 1）。
// // 屏幕输出：case 1
// System.out.println("case 1");
// // 这里有 break：执行到这里就跳出 switch。所以穿透只走到 case 1 就停了，不会继续执行 case 2 和 case 3。
// break;
// // case 2 和 case 3 在这次运行中不会被执行。
// case 2:
// // 不执行。
// System.out.println("case 2");
// case 3:
// // 不执行。
// System.out.println("case 3");
// // 跳出 switch。
// break;
// } // 结束第三个 switch。小结：default 不在最后时，更要注意写 break，否则会穿透进后面的 case

// // ==================== 实验四：【正确写法】每个 case 都写 break ====================

// // 打印实验标题。这里用到的 day 仍然是前面声明的那个变量，值还是 2。
// // 屏幕输出：【正确写法】day = 2，每个case末尾都加上break
// System.out.println("【正确写法】day = " + day + "，每个case末尾都加上break");
// // switch 开始：先算出 day 的值是 2。
// switch (day) {
// // 如果 day 等于 1，从这里开始执行。day 是 2，不匹配，跳过。
// case 1:
// // 不执行。
// System.out.println("星期一");
// // 如果匹配了，打印完就用 break 跳出 switch，不会穿透。
// break;
// // day 等于 2，匹配成功！从这里开始执行。
// case 2:
// // 打印「星期二」。
// // 屏幕输出：星期二
// System.out.println("星期二");
// // break：立刻跳出整个 switch，不再执行下面任何一行。这就是我们想要的「只输出一项」。
// break;
// // 下面的 case 3 ～ case 7 和 default 全部被跳过，不会执行。
// case 3:
// // 不执行。
// System.out.println("星期三");
// // 每个 case 末尾都有 break，这样每个 case 之间互不影响。
// break;
// // 星期四。
// case 4:
// // 不执行。
// System.out.println("星期四");
// // 跳出 switch。
// break;
// // 星期五。
// case 5:
// // 不执行。
// System.out.println("星期五");
// // 跳出 switch。
// break;
// // 星期六。
// case 6:
// // 不执行。
// System.out.println("星期六");
// // 跳出 switch。
// break;
// // 星期日。
// case 7:
// // 不执行。
// System.out.println("星期日");
// // 跳出 switch。
// break;
// // default：当 day 不是 1～7 中的任何一个时才会执行，用来处理「输入不合法」的情况。
// // 这次 day 是 2，已经在 case 2 里 break 出去了，所以这里不会执行。
// default:
// // 不执行。
// // 这个 default 是整个 switch 的最后一项，后面没有别的 case 了，所以不写 break 也不会穿透出问题。
// // 不过为了养成好习惯，建议最后一项也写上 break。
// System.out.println("输入错误");
// } // 结束第四个 switch
// } // 结束 main 方法（和 main 那一行末尾的 { 配成一对）
// } // 结束 SwitchBreakDemo 类（和 class 那一行末尾的 { 配成一对）
