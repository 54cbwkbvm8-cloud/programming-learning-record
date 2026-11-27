# Java 异常处理与 finally 执行顺序：简介与学习建议

## 1. 文件基本信息

- **源码**：`ExceptionDemo.java`
- **分类**：`Java/10_常用类与异常`
- **程序用途**：把 Java 异常处理的核心场景演示一遍——空指针、数组越界、`try-catch-finally` 执行顺序（含 `return`）、数字格式异常、`throw` 主动抛异常；另提供 `-DcrashMode=N` 开关故意制造未捕获异常，用来看真实调用栈。
- **核心知识点**：异常类型层次（`RuntimeException` 及其子类）、`try-catch-finally` 的控制流、`finally` 与 `return` 的关系、`throw` 与异常消息、未捕获异常的线程终止与退出码、系统属性读取（`Integer.getInteger`）。
- **前置知识**：`try-catch` 基本写法（35 号输入重试）、方法定义与返回值、`Integer.parseInt`（50 号）。
- **相关练习**：34 号专门演示过 NPE（对象数组判空），本题把 NPE 放进完整的异常体系里对照；56 号综合项目用异常做校验主线。

## 2. 程序功能说明

`main` 按 `crashMode`（默认 0）分两条路：

- **默认模式**：依次运行五组演示，每组的异常都在方法内部被 `catch` 住，程序正常结束、退出码 0。
- **崩溃模式**：`java -DcrashMode=1/2/3` 进入 `crashOnPurpose`，故意不捕获异常，让 JVM 打印调用栈并以退出码 1 结束。

五组演示的内容：

1. `demoNullPointer`：对 `null` 调 `length()` → 捕获 `NullPointerException`。
2. `demoArrayIndexOutOfBounds`：访问 `arr[5]`（长度 3）→ 捕获越界异常。
3. `demoFinallyOrder("12")` / `("abc")`：同一方法的成功与失败路径，观察 `try → finally` 与 `try → catch → finally` 的顺序；成功路径里 `try` 中有 `return`，**`finally` 仍然执行**。
4. `demoNumberFormatException`：`Integer.parseInt("12abc")` → 捕获数字格式异常。
5. `demoThrow`：`checkAge(-1)` 主动 `throw new IllegalArgumentException(...)`，由调用者捕获。

源码后半段保留附件注释教程（逐行讲解）。

## 3. 编译与运行

在仓库根目录执行：

```bash
java_dir=$(mktemp -d /tmp/java-core.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/10_常用类与异常/ExceptionDemo.java"
java -cp "$java_dir" ExceptionDemo
java -DcrashMode=1 -cp "$java_dir" ExceptionDemo
```

已使用 OpenJDK 21.0.13 严格编译并实际运行。默认模式输出（节选，完整见源码注释）：

```text
== demoNullPointer ==
捕获到 NullPointerException: Cannot invoke "String.length()" because "<local0>" is null
...
== demoFinallyOrder("12") ==
try: 开始解析
try: 解析结果 = 12
finally: 无论是否异常、是否 return 都会执行
== demoFinallyOrder("abc") ==
try: 开始解析
catch: 数字格式错误 - For input string: "abc"
finally: 无论是否异常、是否 return 都会执行
```

注意 `"12"` 组的输出后面**没有空行**，因为 `try` 里的 `return` 跳过了方法末尾的 `println()`——这是本题最值得咀嚼的细节。

崩溃模式已逐一验证：`crashMode=1` 抛 `NullPointerException`、`crashMode=2` 抛 `ArrayIndexOutOfBoundsException`、`crashMode=3` 抛 `IllegalStateException`，三者退出码均为 1 且打印 `at ExceptionDemo.crashOnPurpose(...)` 调用栈；`crashMode=9` 输出 `未知的 CRASH_MODE: 9`，退出码 0。

## 4. 学习重点与难点

- **`finally` 几乎总会执行**：无论 `try` 正常结束、抛异常、还是 `return`，`finally` 都执行。只有在 `finally` 里再 `return`/`throw` 或 JVM 被强制终止等极端情况才不同。本题的 `"12"` 路径就是"`return` 前先执行 `finally`"的实证。
- **`return` 之后代码不执行**：`demoFinallyOrder` 末尾的 `System.out.println()` 在成功路径永远不会运行——所以两组标题之间少了空行。读输出时不要误以为程序出错。
- **异常消息因 JDK 版本而异**：NPE 的 `getMessage()` 在 JDK 14+ 有"helpful NullPointerExceptions"增强，文案与旧版本不同；不要背具体文字，要看类型。
- **未捕获异常 ≠ 程序正常结束**：JVM 打印栈后以非零退出码结束（这里是 1）。shell 脚本或 CI 可以据此判断失败。
- **`throw` 的语义**：`checkAge` 发现非法参数时主动抛出，把"检查"和"处理"分开——被调用方负责发现错误，调用方决定怎么提示。56 号项目把这条思路用成了校验主线。
- **`Integer.getInteger` 读的是系统属性**，不是环境变量也不是标准输入；默认值参数保证属性缺失时不崩溃。

## 5. 推荐学习流程

1. 先跑默认模式，逐组对照输出理解每条消息来自哪个 `catch`。
2. 只看 `demoFinallyOrder` 的代码，预测两组输出，再运行核对（重点：`"12"` 组的空行去哪了）。
3. 依次跑 `crashMode=1/2/3`，读调用栈的每一行：异常类型、消息、方法名与行号。
4. 改 `checkAge(0)` 观察正常路径，再改回 `-1`。
5. 合上源码写一个"除法计算器"：输入两个整数，捕获 `ArithmeticException`（除零）与 `NumberFormatException`，`finally` 里打印"计算结束"。

## 6. 易错点与测试用例

| 操作或场景 | 预期结果 | 状态 |
| --- | --- | --- |
| 默认运行 | 五组演示全部被捕获，输出完整，退出码 0 | 已运行验证 |
| `-DcrashMode=1` | 未捕获 NPE，打印调用栈，退出码 1 | 已运行验证 |
| `-DcrashMode=2` | 未捕获越界异常，退出码 1 | 已运行验证 |
| `-DcrashMode=3` | 未捕获 `IllegalStateException`，退出码 1 | 已运行验证 |
| `-DcrashMode=9` | `未知的 CRASH_MODE: 9`，退出码 0 | 已运行验证 |
| 把 `finally` 改为 `catch` 之后再加一行 `println` | 成功路径仍不会执行该行（`return` 已离开） | 预期结果，未修改源码测试 |
| 删除 `demoFinallyOrder` 中的 `return` | `"12"` 组输出后多一个空行 | 预期结果，未修改源码测试 |
| 在命令行把 `-DcrashMode` 写在类名之后 | 参数位置错误，JVM 视为程序参数，`crashMode` 仍为 0 | 预期结果，未运行验证 |

## 7. 拓展与后续学习

- 用 `try-with-resources` 重写 56 号项目的 `Scanner` 关闭，对比 `finally` 写法。
- 自定义异常类 `AgeOutOfRangeException extends RuntimeException`，替换 `IllegalArgumentException`。
- 进入 56 号综合项目，看异常如何组织成"校验在底层抛出、提示在菜单层统一捕获"的完整结构。
