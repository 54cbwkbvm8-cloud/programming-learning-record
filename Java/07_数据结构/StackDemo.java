import java.util.Arrays;

class MyStack {
    private int[] data;
    private int top;

    public MyStack(int capacity) {
        data = new int[capacity];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == data.length - 1;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈已满，无法 push");
        }
        data[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法 pop");
        }
        int value = data[top];
        top--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法 peek");
        }
        return data[top];
    }

    public int size() {
        return top + 1;
    }

    public void show() {
        System.out.print("栈（底->顶）：[");
        for (int i = 0; i <= top; i++) {
            System.out.print(data[i]);
            if (i < top) {
                System.out.print(", ");
            }
        }
        System.out.println("] top=" + top + " size=" + size() + " 底层数组=" + Arrays.toString(data));
    }
}

public class StackDemo {
    static int total = 0;
    static int passed = 0;

    static boolean isPair(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}');
    }

    static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        MyStack stack = new MyStack(s.length() + 1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char left = (char) stack.pop();
                if (!isPair(left, c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    static void check(String s, boolean expected) {
        total++;
        boolean actual = isValid(s);
        String shown = (s == null) ? "null" : "\"" + s + "\"";
        if (actual == expected) {
            passed++;
            System.out.println("[通过] 输入 " + shown + " 期望 " + expected + " 实际 " + actual);
        } else {
            System.out.println("[失败] 输入 " + shown + " 期望 " + expected + " 实际 " + actual);
        }
    }

    public static void main(String[] args) {
        System.out.println("====== 演示 1：push / pop / peek 与 top 的变化 ======");
        MyStack stack = new MyStack(4);
        System.out.print("初始状态 -> ");
        stack.show();
        stack.push(10);
        System.out.print("push(10) 之后 -> ");
        stack.show();
        stack.push(20);
        System.out.print("push(20) 之后 -> ");
        stack.show();
        stack.push(30);
        System.out.print("push(30) 之后 -> ");
        stack.show();
        System.out.println("peek() 返回 " + stack.peek() + "（只看不取，top不变）");
        int x = stack.pop();
        System.out.print("pop() 返回 " + x + " 之后 -> ");
        stack.show();
        int y = stack.pop();
        System.out.print("pop() 返回 " + y + " 之后 -> ");
        stack.show();
        System.out.println("isEmpty() = " + stack.isEmpty() + "，isFull() = " + stack.isFull());
        System.out.println();
        System.out.println("====== 演示 2：空栈 pop、空栈 peek、满栈 push ======");
        MyStack emptyStack = new MyStack(2);
        try {
            emptyStack.pop();
            System.out.println("没有报错（这一行不应该出现）");
        } catch (RuntimeException e) {
            System.out.println("空栈 pop 抛出异常: " + e.getMessage());
        }
        try {
            emptyStack.peek();
            System.out.println("没有报错（这一行不应该出现）");
        } catch (RuntimeException e) {
            System.out.println("空栈 peek 抛出异常: " + e.getMessage());
        }
        MyStack fullStack = new MyStack(2);
        fullStack.push(1);
        fullStack.push(2);
        System.out.println("放入 2 个元素后 isFull() = " + fullStack.isFull());
        try {
            fullStack.push(3);
            System.out.println("没有报错（这一行不应该出现）");
        } catch (RuntimeException e) {
            System.out.println("满栈 push 抛出异常: " + e.getMessage());
        }
        System.out.println();
        System.out.println("====== 演示 3：括号匹配（力扣 20） ======");
        check("()", true);
        check("()[]{}", true);
        check("(]", false);
        check("([)]", false);
        check("{[]}", true);
        check("())", false);
        check("", true);
        check("(", false);
        check("]", false);
        check(")(", false);
        check("(((((((((())))))))))", true);
        check("(((((((((()))))))))))", false);
        check(null, false);
        System.out.println("共 " + total + " 项测试，通过 " + passed + " 项");
    }
}
// // 导入 Arrays 工具类：下面要用 Arrays.toString(数组) 把数组的内容变成方便阅读的文字，如 [1, 2, 3]。
// // 它不在 Java 默认范围内，必须先 import，否则 Java 不认识 Arrays 这个名字。
// import java.util.Arrays;

// /**
// * StackDemo —— 用数组实现"栈"，并用栈解决经典的"括号匹配"问题
// *
// * 【什么是栈（Stack）】
// * 栈是一种"后进先出"（Last In First Out，简称 LIFO）的数据结构：最后放进去的，最先拿出来。
// * 最形象的比喻：一摞盘子。你只能把新盘子放在最上面，也只能从最上面取盘子，
// * 不能从中间或下面抽。最后放上去的盘子，一定是最先被取走的。
// * 栈有几个最基本的操作：
// * push(值) ：入栈，把一个值放到栈顶
// * pop() ：出栈，取走并返回栈顶的值
// * peek() ：只看栈顶是什么，但不取走
// * isEmpty() ：栈是不是空的
// * isFull() ：栈是不是满了（用数组实现的栈容量是固定的）
// * size() ：栈里现在有几个元素
// *
// * 【程序分三部分】
// * MyStack 类 ：用一个 int 数组实现的栈
// * 演示 1 和 2 ：看 push、pop、peek 时数组和 top 是怎么变化的；以及出错时（空栈 pop、满栈 push）会怎样
// * 演示 3 ：用栈判断括号是否匹配（这是力扣第 20 题，栈最经典的应用）
// *
// * 【本次修复的问题】
// * 1. 文件名不对：你上传的文件名是 StackDemo_1.java，但里面写的是 public class StackDemo。
// * Java 规定：被 public 修饰的类，文件名必须和类名完全一致（区分大小写）。
// * 所以 StackDemo_1.java 直接编译会报错：
// * class StackDemo is public, should be declared in a file named
// StackDemo.java
// * 修复：文件名必须是 StackDemo.java（已按这个名字保存）。
// * 2. 测试统计没有输出：代码里定义了 total（一共测了几项）和 passed（通过了几项），
// * 每次 check 都在累加，但程序结束时一直没有把它们打印出来，等于白统计了。
// * 修复：在 main 的最后补上一行汇总。
// * 3. show() 方法打印出来的格式很别扭：[10 ,20 ,30 ]（逗号前面有空格，后面没有），
// * 现在改成了规范的 [10, 20, 30]。同时栈标题里括号一半中文一半英文，也已统一。
// * 4. 测试 null 时，输出的是 输入 "null"（带着双引号），看起来像是输入了文字 null，
// * 其实输入的是"空引用"（什么都没有），容易让人误会。现在不带双引号显示。
// * 5. 演示标题的小问题：三个标题的等号数量不一致；演示 2 的标题写的是
// * "空栈 pop 栈满 push 空栈 peek"，和代码里实际的顺序（空栈 pop、空栈 peek、满栈 push）对不上。已整理。
// * （栈本身的实现和括号匹配的逻辑都是正确的，13 组括号测试全部通过，没有改动。）
// */

// // ============================================================
// // MyStack —— 用数组实现的栈
// //
// // 【思路】
// // 用一个 int 数组 data 存放栈里的元素；
// // 用一个整数 top（"栈顶指针"）记录【栈顶元素在数组里的下标】。
// // 数组的下标从 0 开始，所以：
// // 栈是空的时候，没有任何元素，top 取 -1（因为 -1 不是合法下标，正好表示"什么都没有"）
// // 放进第 1 个元素后，它存放在 data[0]，top 变成 0
// // 放进第 2 个元素后，它存放在 data[1]，top 变成 1
// // 以此类推。数组左边（下标 0）是栈底，右边（下标 top）是栈顶。
// // ============================================================
// class MyStack {

// // private：私有，只有 MyStack 这个类自己里面的代码能访问，类的外面不能直接读写。
// // 这叫"封装"：把内部细节藏起来，外面只能通过 push、pop 等公开的方法来使用栈，
// // 这样就不会有人绕过规则，随便去乱改 top 或数组内容。
// private int[] data; // 存放元素的数组
// private int top; // 栈顶元素的下标；-1 表示栈是空的

// // 构造方法：用 new MyStack(容量) 创建栈时自动执行。
// // · 名字必须和类名完全相同，没有返回值类型
// // 参数 capacity 是"容量"，也就是最多能放多少个元素。数组一旦创建，长度就不能再改变。
// public MyStack(int capacity) {
// data = new int[capacity]; // 创建一个能放 capacity 个整数的数组，里面的值默认全是 0
// top = -1; // 一开始是空栈
// }

// // 判断栈是不是空的：top 等于 -1 就是空的。
// // == 是"判断相等"（两个等号）；一个等号 = 是"赋值"，不要写混。
// public boolean isEmpty() {
// return top == -1;
// }

// // 判断栈是不是满了：栈顶下标已经等于"数组最后一个格子的下标"。
// // 数组长度是 data.length，最后一个格子的下标是 data.length - 1（因为下标从 0 开始）。
// public boolean isFull() {
// return top == data.length - 1;
// }

// // ---------- push：入栈 ----------
// // 把一个值放到栈顶。放之前必须先检查满了没有，满了再放就会超出数组范围。
// public void push(int value) {
// if (isFull()) {
// // throw new RuntimeException("...")：主动"抛出"一个错误。
// // throw 的意思是"扔出去"，抛出之后这个方法会【立刻结束】，不再往下执行。
// // 如果调用的地方没有用 try-catch 接住这个错误，整个程序会停止并显示错误信息。
// // RuntimeException 是 Java 自带的一种错误类型，括号里的文字是错误说明。
// throw new RuntimeException("栈已满，无法 push");
// }

// // data[++top] = value; 这一句做了两件事，顺序是：
// // ① ++top：先让 top 加 1（栈顶往上移一格）
// // ② 再把 value 放进 data[top] 这个格子
// // 例如 top 是 -1（空栈）时：先变成 0，再把值放进 data[0]。
// // 注意 ++top（先加后用）和 top++（先用后加）的区别：
// // 如果写成 data[top++] = value，会先用旧的 top 当下标，再加 1，下标就错位了。
// data[++top] = value;
// }

// // ---------- pop：出栈 ----------
// // 取走并返回栈顶的值。栈是空的时候没有东西可取，必须报错。
// public int pop() {
// if (isEmpty()) {
// throw new RuntimeException("栈为空，无法 pop");
// }
// int value = data[top]; // ① 先把栈顶的值取出来，存进变量 value
// top--; // ② 再让 top 减 1（栈顶往下移一格）。top-- 等价于 top = top - 1
// return value; // ③ 把取出的值交还给调用者

// // 【重要的细节】pop 只是把 top 往下移，并【没有】把数组里原来的值擦掉。
// // 比如 [10, 20, 30] 弹出 30 之后，data[2] 里仍然存着 30，只是 top 变成了 1，
// // 栈"认为"下标 2 的位置已经不属于栈了。下次 push 时，新值会直接覆盖它。
// // 运行演示 1 时，从"底层数组"那一栏可以亲眼看到这一点。
// }

// // ---------- peek：查看栈顶 ----------
// // 只返回栈顶的值，不取走（top 不变）。
// public int peek() {
// if (isEmpty()) {
// throw new RuntimeException("栈为空，无法 peek");
// }
// return data[top];
// }

// // ---------- size：栈里有几个元素 ----------
// // top 是下标（从 0 开始），个数 = 下标 + 1。
// // 例如 top = 2，说明下标 0、1、2 三个位置有元素，共 3 个。空栈 top = -1，个数 = 0。
// public int size() {
// return top + 1;
// }

// // ---------- show：把栈的状态打印出来（教学用）----------
// public void show() {
// // 【修复点】括号原来是 "（底->顶)"，一半中文一半英文，已统一成中文括号。
// System.out.print("栈（底->顶）：[");

// // 只打印下标 0 到 top 这部分（真正属于栈的元素），不会打印后面那些空闲的格子。
// for (int i = 0; i <= top; i++) {
// // 【修复点】原来写的是 System.out.print(data[i] + " ") 然后再打印 ","，
// // 结果是 [10 ,20 ,30 ]：每个数字后面带一个空格，逗号反而紧贴着数字后面，很别扭。
// // 现在改成：先打印数字本身，再在"不是最后一个"的时候补上 ", "（逗号加空格），
// // 得到规范的 [10, 20, 30]。
// System.out.print(data[i]);
// if (i < top) { // 只有不是最后一个元素时，才需要加分隔符
// System.out.print(", ");
// }
// }

// // 再打印当前的 top、size，以及整个底层数组（Arrays.toString 会把整个数组原样显示出来，
// // 包括已经"出栈"但没被擦掉的旧值，方便观察）
// System.out.println("] top=" + top + " size=" + size() + " 底层数组=" +
// Arrays.toString(data));
// }
// }

// // ============================================================
// // StackDemo —— 演示程序（含括号匹配）
// //
// // 注意：这个类是 public 的，所以文件名必须叫 StackDemo.java。
// // ============================================================
// public class StackDemo {

// // static 变量：属于"类"本身，所有方法共用同一份。
// // 这里用来在多次调用 check 的过程中，累计"测试了几项"和"通过了几项"。
// // （如果写成 check 方法里的普通局部变量，每次调用结束就消失了，没法累计。）
// static int total = 0; // 一共测试了几项
// static int passed = 0; // 其中通过了几项

// // ============================================================
// // isPair：判断 a 和 b 是不是"一对括号"（a 是左括号，b 是右括号）
// //
// // 方法头怎么读：static boolean isPair(char a, char b)
// // boolean ：返回值类型，只有 true（是）和 false（否）
// // char ：单个字符的类型，写字符常量时用英文【单引号】，如 '('
// //
// // && 表示"并且"（两边都成立才成立），|| 表示"或者"（任意一边成立就成立）。
// // 运算的优先级：&& 比 || 先算，而且每一组 && 都用括号括起来了，所以意思很清楚：
// // (a 是 '(' 并且 b 是 ')') 或者 (a 是 '[' 并且 b 是 ']') 或者 (a 是 '{' 并且 b 是 '}')
// // ============================================================
// static boolean isPair(char a, char b) {
// return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b ==
// '}');
// }

// // ============================================================
// // isValid：判断一个括号字符串是否"有效"（括号匹配）
// //
// // 【什么叫有效】
// // ① 每个左括号，都要有一个同类型的右括号与它配对
// // ② 括号必须按正确的顺序关闭：后打开的要先关闭
// // 例如：()、()[]{}、{[]} 是有效的；(]、([)]、())、( 都是无效的。
// //
// // 【为什么用栈】
// // "后打开的要先关闭"，正是"后进先出"的特点。做法：
// // · 遇到左括号：先"记下来"，放进栈里等着配对
// // · 遇到右括号：看看栈顶那个最近的、还没配对的左括号，是不是和它一对
// // - 栈是空的（没有左括号可以配）→ 无效
// // - 栈顶的左括号和它不是一对 → 无效
// // - 是一对 → 把这个左括号弹出，配对成功，继续往后看
// // · 全部字符处理完后，栈必须是空的，说明所有左括号都配好了对；如果栈里还剩东西，说明有左括号没被关闭 → 无效
// //
// // 以 "([)]" 为例，一步步看：
// // '(' 入栈 → 栈：(
// // '[' 入栈 → 栈：( [
// // ')' 是右括号 → 栈顶是 '['，'[' 和 ')' 不是一对 → 返回 false（无效）
// //
// // 【说明】这里默认字符串里只有 ( ) [ ] { } 这六种括号（这是力扣第 20 题的约定）。
// // 代码把"不是左括号的字符"都当成右括号来处理，所以如果字符串里混有字母之类的其他字符，
// // 会直接判为无效。
// // ============================================================
// static boolean isValid(String s) {

// // s 是 null（"什么都没有"，连空字符串都不是）时，对它调用 s.length() 会让程序崩溃，
// // 所以先检查。这里约定：null 视为无效。
// if (s == null) {
// return false;
// }

// // 创建一个栈。容量设成 s.length() + 1：最坏情况下整个字符串全是左括号，
// // 栈里最多会同时放下 s.length() 个元素，多留 1 个格子保证绝对不会满。
// MyStack stack = new MyStack(s.length() + 1);

// // 逐个检查字符串里的每个字符。i 是下标，从 0 到 s.length() - 1。
// for (int i = 0; i < s.length(); i++) {

// // s.charAt(i)：取出下标为 i 的那个字符
// char c = s.charAt(i);

// if (c == '(' || c == '[' || c == '{') {
// // 是左括号：入栈。
// // 栈里存的是 int，而 c 是 char。char 可以自动转换成 int（字符对应的编号，如 '(' 是 40），
// // 所以可以直接 push(c)。
// stack.push(c);
// } else {
// // 不是左括号，就当作右括号来处理

// // 栈是空的，说明前面没有等着配对的左括号 → 无效。例如 ")" 或 "())"。
// if (stack.isEmpty()) {
// return false;
// }

// // 弹出栈顶的左括号。pop 返回的是 int，要用 (char) 强制转换成字符，才能和字符比较。
// // "强制类型转换"的写法：(目标类型) 要转换的值。
// char left = (char) stack.pop();

// // 弹出的左括号和当前的右括号不是一对，例如 "(]" → 无效
// if (!isPair(left, c)) { // ! 表示"取反"，读作"不是一对"
// return false;
// }
// }
// }

// // 全部字符处理完。只有栈是空的，才说明所有左括号都配好对了。
// // 例如 "(" 处理完后，栈里还剩一个 '('，栈不空 → 返回 false。
// // 空字符串 ""：循环一次都不执行，栈是空的 → 返回 true（空字符串按"有效"处理）。
// return stack.isEmpty();
// }

// // ============================================================
// // check：一个简单的"测试裁判"
// //
// // 参数 s ：要测试的括号字符串
// // 参数 expected ：这个字符串"应该得到的结果"（期望值）
// // 做法：调用 isValid 得到实际结果，和期望值比较，一样就是通过，不一样就是失败。
// // ============================================================
// static void check(String s, boolean expected) {
// total++; // 测试的项数加 1
// boolean actual = isValid(s); // 实际结果

// // 【修复点】原来无论 s 是什么，都在两边加双引号，s 是 null 时就显示成 "null"，
// // 看起来像是字符串 null。现在 s 是 null 时，直接显示不带引号的 null。
// // 三元运算符：条件 ? 值1 : 值2 —— 条件成立取值1，否则取值2。
// // \" 表示"一个双引号字符"（因为双引号本身是字符串的边界符号，要表示它自己，前面要加反斜杠转义）。
// String shown = (s == null) ? "null" : "\"" + s + "\"";

// if (actual == expected) {
// passed++; // 通过的项数加 1
// System.out.println("[通过] 输入 " + shown + " 期望 " + expected + " 实际 " + actual);
// } else {
// System.out.println("[失败] 输入 " + shown + " 期望 " + expected + " 实际 " + actual);
// }
// }

// // main 是程序的入口：运行程序时，Java 从这里的第一行开始，一行一行往下执行。
// public static void main(String[] args) {

// // ==================== 演示 1：观察 push / pop / peek 时 top 的变化
// ====================
// // 【修复点】三个标题的等号数量统一成了 6 个。
// System.out.println("====== 演示 1：push / pop / peek 与 top 的变化 ======");

// MyStack stack = new MyStack(4); // 创建一个容量为 4 的栈

// System.out.print("初始状态 -> ");
// stack.show(); // 预期：[] top=-1 size=0 底层数组=[0, 0, 0, 0]

// stack.push(10);
// System.out.print("push(10) 之后 -> ");
// stack.show(); // 预期：[10] top=0 size=1 底层数组=[10, 0, 0, 0]

// stack.push(20);
// System.out.print("push(20) 之后 -> ");
// stack.show(); // 预期：[10, 20] top=1

// stack.push(30);
// System.out.print("push(30) 之后 -> ");
// stack.show(); // 预期：[10, 20, 30] top=2

// // peek 只看不取，top 不会变，30 仍然在栈里
// System.out.println("peek() 返回 " + stack.peek() + "（只看不取，top不变）");

// // pop 取走栈顶。注意"后进先出"：最后放进去的 30 最先被取出来
// int x = stack.pop();
// System.out.print("pop() 返回 " + x + " 之后 -> ");
// stack.show(); // 预期：[10, 20] top=1。底层数组里 30 还在，只是 top 往下移了，被认为不属于栈了

// int y = stack.pop();
// System.out.print("pop() 返回 " + y + " 之后 -> ");
// stack.show(); // 预期：[10] top=0

// // 栈里还有 1 个元素，容量是 4：不空，也不满
// System.out.println("isEmpty() = " + stack.isEmpty() + "，isFull() = " +
// stack.isFull());
// System.out.println(); // 空一行，把两个演示分隔开

// // ==================== 演示 2：错误情况 ====================
// // 【修复点】标题里的顺序原来对不上，现在和下面代码的实际顺序一致。
// System.out.println("====== 演示 2：空栈 pop、空栈 peek、满栈 push ======");

// MyStack emptyStack = new MyStack(2);

// // try-catch：预防错误导致程序崩溃。
// // try ：把"可能出错的代码"放在里面试着运行
// // catch ：如果 try 里抛出了指定类型的错误，就跳到这里处理，程序不会崩溃，会继续往下执行
// // 空栈 pop 会抛出 RuntimeException，所以 try 里 pop() 之后的那句"没有报错"不会被执行，
// // 而是直接跳到 catch。e.getMessage() 能取出错误的文字说明，也就是上面 throw 时写的那句话。
// try {
// emptyStack.pop();
// System.out.println("没有报错（这一行不应该出现）");
// } catch (RuntimeException e) {
// System.out.println("空栈 pop 抛出异常: " + e.getMessage());
// }

// try {
// emptyStack.peek();
// System.out.println("没有报错（这一行不应该出现）");
// } catch (RuntimeException e) {
// System.out.println("空栈 peek 抛出异常: " + e.getMessage());
// }

// MyStack fullStack = new MyStack(2); // 容量只有 2
// fullStack.push(1);
// fullStack.push(2); // 已经放了 2 个，满了
// System.out.println("放入 2 个元素后 isFull() = " + fullStack.isFull()); // true

// try {
// fullStack.push(3); // 满了还要放，会抛出异常
// System.out.println("没有报错（这一行不应该出现）");
// } catch (RuntimeException e) {
// System.out.println("满栈 push 抛出异常: " + e.getMessage());
// }
// System.out.println();

// // ==================== 演示 3：括号匹配（力扣第 20 题）====================
// System.out.println("====== 演示 3：括号匹配（力扣 20） ======");

// // 每一行：check(要测试的字符串, 期望的结果)。
// // 这些数据覆盖了各种情况，其中不少是"边界情况"，最容易出错：
// check("()", true); // 最简单的一对
// check("()[]{}", true); // 三种括号并排
// check("(]", false); // 类型不匹配
// check("([)]", false); // 类型都有，但交叉了，顺序不对
// check("{[]}", true); // 正确的嵌套
// check("())", false); // 右括号多了（栈已经空了还遇到右括号）
// check("", true); // 空字符串：按有效处理
// check("(", false); // 左括号没有被关闭（处理完后栈不是空的）
// check("]", false); // 一上来就是右括号
// check(")(", false); // 顺序反了：先遇到右括号，栈是空的
// check("(((((((((())))))))))", true); // 很深的嵌套：左括号和右括号个数相等
// check("(((((((((()))))))))))", false); // 比上面多了一个右括号
// check(null, false); // null：约定视为无效

// // 【修复点】汇总。原来 total 和 passed 一直在累加，却从来没有输出。
// // 现在把测试结果统计打印出来。
// System.out.println("共 " + total + " 项测试，通过 " + passed + " 项");
// }
// }
