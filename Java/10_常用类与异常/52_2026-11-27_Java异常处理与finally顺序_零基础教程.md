# Java 异常处理：零基础教程

> 配合 [ExceptionDemo.java](ExceptionDemo.java) 与 [52 号简介](52_2026-11-27_Java异常处理与finally顺序_简介与学习建议.md) 使用。先运行程序看输出，再按本文顺序理解；每节末尾有动手任务。

## 0. 什么是异常

程序运行中出了意外，Java 不会悄悄算错，而是**抛出一个异常对象**，沿着方法调用链往上找"谁来处理"。找不到处理者，程序就打印调用栈并退出。

打个比方：你在餐厅点菜（调用方法），厨房发现食材没了（出错），就"上报"（throw）；如果经理（上层方法）提前交代过"没食材就换一道"（catch），事情就地解决；没交代，顾客直接看到事故（程序崩溃）。

**异常不是"语法错误"**：语法错误编译期就过不去；异常是语法合法、运行到某一步才发生的问题。

## 1. 异常家族：先认几个常客

```text
Throwable
├── Error（系统级错误，一般不处理）
└── Exception
    └── RuntimeException（运行时异常，编译器不强制处理）
        ├── NullPointerException        空指针
        ├── ArrayIndexOutOfBoundsException  数组越界
        ├── NumberFormatException       数字格式错误
        └── IllegalArgumentException    参数非法
```

本程序演示的全是 `RuntimeException` 家族——它们**不需要**在方法上写 `throws`，编译器也不逼你 `try-catch`，但一旦发生且没人接，就会终止线程。

## 2. 第一组：NPE 与数组越界

```java
String s = nullText();       // 返回 null
try {
    System.out.println(s.length());   // null 没有 length
} catch (NullPointerException e) {
    System.out.println("捕获到 NullPointerException: " + e.getMessage());
}
```

`null` 表示"不指向任何对象"。对 `null` 调任何实例方法都是 NPE。**注意**：`try` 里出错后，`println` 那行不会产生输出，控制流直接跳到 `catch`。

数组越界同理：`arr[5]` 对长度 3 的数组越界，抛 `ArrayIndexOutOfBoundsException`，`catch` 里可以打印 `arr.length` 说明原因。

> 34 号 `NullPointerDemo` 演示的是"**不捕获**"的版本：NPE 让程序崩溃、退出码 1。两者对照看，才能理解"捕获"到底改变了什么。

## 3. 第二组：try-catch-finally 的执行顺序（重点）

```java
try {
    System.out.println("try: 开始解析");
    int value = Integer.parseInt(text);
    System.out.println("try: 解析结果 = " + value);
    return;                                   // 成功路径提前返回
} catch (NumberFormatException e) {
    System.out.println("catch: 数字格式错误 - " + e.getMessage());
} finally {
    System.out.println("finally: 无论是否异常、是否 return 都会执行");
}
System.out.println();                          // ← 成功路径永远到不了这里
```

三条执行路径：

| 输入 | 执行顺序 | 方法末尾的空行 |
| --- | --- | --- |
| `"12"` | try（两行）→ **finally** → 返回 | **不执行**（`return` 已离开） |
| `"abc"` | try（第一行）→ catch → finally → 空行 | 执行 |
| 不抛异常且无 return | try → finally → 空行 | 执行 |

实际运行验证：`"12"` 组的 `finally` 输出后**紧跟**下一组标题，没有空行——这不是漏打印，而是 `return` 跳过了方法末尾的 `println()`。很多初学者把这当成 bug，其实是"`finally` 在 `return` 交接前执行、`return` 之后的语句被跳过"的必然结果。

**规则记法**：`finally` 是"离开方法前必做的收尾"，`try` 里的 `return` 只是把"交接时间"推迟到收尾完成之后。

## 4. 第三组：NumberFormatException

```java
String text = "12abc";
Integer.parseInt(text);   // 不是合法整数 → 抛异常
```

`parseInt` 只接受可选符号 + 纯数字（且不超 `int` 范围）。`"12abc"`、`"3.5"`、`""`、`" 12"`（未 `trim`）都会失败。`catch` 里用 `e.getMessage()` 拿到原始文本，便于提示用户。

50 号 `BuilderLesson` 用同一个 `try-catch` 结构做"输入转整数"，两处对照可以巩固。

## 5. 第四组：throw 主动抛异常

```java
private static void checkAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("年龄不能为负数: " + age);
    }
    System.out.println("年龄 = " + age);
}
```

`throw` 是"我发现问题，主动上报"。好处是**检查逻辑留在被调用方**（它最清楚什么叫非法），**提示方式留给调用方**（控制台、弹窗、日志各取所需）。`IllegalArgumentException` 表示"传进来的参数不合法"，是最常用的约定之一。

56 号项目把这条思路扩展成整个系统的校验风格：`addPerson` 里发现空姓名、非整数年龄、超范围，全部 `throw new IllegalArgumentException("原因")`，菜单层一个 `catch` 统一打印。

## 6. 第五组：故意不捕获，看调用栈

```bash
java -DcrashMode=1 -cp "$java_dir" ExceptionDemo
```

输出：

```text
Exception in thread "main" java.lang.NullPointerException: ...
    at ExceptionDemo.crashOnPurpose(ExceptionDemo.java:21)
    at ExceptionDemo.main(ExceptionDemo.java:6)
```

读调用栈的顺序是**从下往上**：`main` 第 6 行调用 `crashOnPurpose`，`crashOnPurpose` 第 21 行炸了。真实项目中定位崩溃就靠这两行信息。

`-DcrashMode=1` 是 JVM 系统属性，程序里用 `Integer.getInteger("crashMode", 0)` 读取，缺省为 0（正常模式）。这种"开关式演示"让同一份代码既能当教学示例、又能当崩溃现场，是调试教学常用的手法。

## 7. 常见错误与排查

| 现象 | 原因 | 排查 |
| --- | --- | --- |
| `catch` 写了但程序还是崩 | 异常类型不匹配（如 `IOException` 不是 `NumberFormatException`） | 看栈里的异常全名，`catch` 写对应类型或父类 |
| `finally` 没执行 | 在 `finally` 之前 `System.exit()` 或 JVM 被强杀 | 极端情况，不要依赖 `finally` 做关键持久化 |
| `return` 后的 `println` 没输出 | 正常！`return` 已离开方法 | 对照本文第 3 节表格 |
| `NullPointerException` 没有信息 | 旧 JDK 或对 `null` 做复杂链式调用 | JDK 14+ 的 helpful NPE 会指出哪个变量是 null |
| `catch` 里又抛异常 | 处理逻辑自身出错 | 栈会显示 "Caused by" 链，从最底层看起 |

## 8. 动手练习

1. 删除 `demoFinallyOrder` 中的 `return`，重跑并解释空行的出现。
2. 写 `divide(int a, int b)`：`b == 0` 时 `throw new ArithmeticException("除数不能为 0")`，`main` 捕获并打印。
3. 用 `crashMode=2` 运行，把栈里每一行的"类名.方法名(文件名:行号)"念出来。
4. 把 `catch (NumberFormatException e)` 改成 `catch (RuntimeException e)`，思考哪种写法更好、为什么。
5. 用 `try-with-resources` 改造 56 号项目的 `Scanner`，对比 `finally` 写法。

## 9. 自我检查

1. 异常和语法错误的区别？——语法错误编译期拦截；异常是运行期事件，可被捕获。
2. `finally` 一定执行吗？——常规控制流下一定执行（含 `return`、异常），只在 JVM 强杀等极端情况不保证。
3. `"12"` 组为什么没有空行？——`try` 里的 `return` 已离开方法，末尾 `println` 被跳过。
4. 为什么 `checkAge` 抛异常而不是返回 `false`？——异常把"发现错误"和"决定怎么处理"解耦；返回值容易被调用者忽略。
5. 未捕获异常的退出码是多少？——非 0（本程序为 1），可用于脚本判断失败。

学完进入 56 号：异常会从"演示"变成"系统的骨架"。
