# Java 学习区

这里保存 Java 课程练习和学习说明。Java 源码必须遵守类名与文件名规则，因此不能直接照搬 C/Python 的 `NN_日期_主题.java` 命名方式；首次整理顺序和日期记录在配套文档中。系统的知识点、难点和掌握标准见 [Java 学习指南](../docs/Java学习指南.md)。

## 目录一览

| 目录 | 源码数 | 学习重点 |
| --- | --- | --- |
| [01_基础语法](01_基础语法/) | 3 | 类、`main`、基本类型、类型转换、运算符、短路求值和三元表达式 |
| [02_分支与循环](02_分支与循环/) | 10 | 输入校验、条件分支、`switch`、三种循环、嵌套循环、统计、极值和持续交互 |
| [03_方法与数组](03_方法与数组/) | 10 | 方法定义与重载、值传递与数组引用、数组遍历、素数/约数/数字拆位/第二大值/数对统计、冒泡排序、线性查找和二分查找 |

当前只创建了有真实源码的目录。`03_方法与数组` 已从一份综合练习扩展为方法语义和入门算法的独立练习组，但二维数组、集合、正式自动测试、面向对象和工程构建仍没有足够资源，因此不提前建立空目录。

## 练习入口

| 顺序 | 源码 | 配套说明 | 主要目标 |
| --- | --- | --- | --- |
| 1 | [`hellojava.java`](01_基础语法/hellojava.java) | [`01_2026-11-02_Java程序入口与输出_简介与学习建议.md`](01_基础语法/01_2026-11-02_Java程序入口与输出_简介与学习建议.md) | 认识类、`main` 方法、`System.out.println` 和运行入口 |
| 2 | [`BasicTypes.java`](01_基础语法/BasicTypes.java) | [`02_2026-11-02_Java基本数据类型与类型转换_简介与学习建议.md`](01_基础语法/02_2026-11-02_Java基本数据类型与类型转换_简介与学习建议.md) | 比较八种基本类型、自动与强制转换、类型范围和溢出 |
| 2A | [`BasicTypes.java`](01_基础语法/BasicTypes.java) | [`02_2026-11-02_Java基本数据类型与类型转换_零基础教程.md`](01_基础语法/02_2026-11-02_Java基本数据类型与类型转换_零基础教程.md) | 按程序结构逐步理解变量、运算和转换 |
| 3 | [`OperatorsDemo.java`](01_基础语法/OperatorsDemo.java) | [`03_2026-11-04_Java运算符与短路求值_简介与学习建议.md`](01_基础语法/03_2026-11-04_Java运算符与短路求值_简介与学习建议.md) | 算术、关系、逻辑、短路、自增、字符串拼接和三元表达式 |
| 3A | [`OperatorsDemo.java`](01_基础语法/OperatorsDemo.java) | [`03_2026-11-04_Java运算符与短路求值_零基础教程.md`](01_基础语法/03_2026-11-04_Java运算符与短路求值_零基础教程.md) | 用变量跟踪理解短路和自增表达式 |
| 4 | [`SwitchBreakDemo.java`](02_分支与循环/SwitchBreakDemo.java) | [`04_2026-11-04_Java-switch与break穿透_简介与学习建议.md`](02_分支与循环/04_2026-11-04_Java-switch与break穿透_简介与学习建议.md) | 观察漏写 `break` 的穿透、`default` 的位置和正确出口 |
| 4A | [`SwitchBreakDemo.java`](02_分支与循环/SwitchBreakDemo.java) | [`04_2026-11-04_Java-switch与break穿透_零基础教程.md`](02_分支与循环/04_2026-11-04_Java-switch与break穿透_零基础教程.md) | 用执行路径理解先找入口、再走到出口 |
| 5 | [`LeapYear.java`](02_分支与循环/LeapYear.java) | [`05_2026-11-04_Java闰年判断与输入校验_简介与学习建议.md`](02_分支与循环/05_2026-11-04_Java闰年判断与输入校验_简介与学习建议.md) | 练习 `Scanner`、整数校验、正数校验和闰年布尔表达式 |
| 6 | [`GradeLevel.java`](02_分支与循环/GradeLevel.java) | [`06_2026-11-04_Java成绩等级与双分支写法_简介与学习建议.md`](02_分支与循环/06_2026-11-04_Java成绩等级与双分支写法_简介与学习建议.md) | 比较 `if / else if` 与 `switch (score / 10)` 的边界处理 |
| 7 | [`LoopBasics.java`](02_分支与循环/LoopBasics.java) | [`07_2026-11-05_Java循环基础与break-continue_简介与学习建议.md`](02_分支与循环/07_2026-11-05_Java循环基础与break-continue_简介与学习建议.md) | 对比 `for`、`while`、`do-while`，观察 `break` 和 `continue` |
| 7A | [`LoopBasics.java`](02_分支与循环/LoopBasics.java) | [`07_2026-11-05_Java循环基础与break-continue_零基础教程.md`](02_分支与循环/07_2026-11-05_Java循环基础与break-continue_零基础教程.md) | 用执行轨迹理解循环变量、最少执行次数和跳转 |
| 8 | [`MultiplicationTable.java`](02_分支与循环/MultiplicationTable.java) | [`08_2026-11-05_Java嵌套循环与九九乘法表_简介与学习建议.md`](02_分支与循环/08_2026-11-05_Java嵌套循环与九九乘法表_简介与学习建议.md) | 用外层与内层循环控制行和列 |
| 9 | [`StarTriangle.java`](02_分支与循环/StarTriangle.java) | [`09_2026-11-05_Java星号三角形与输入校验_简介与学习建议.md`](02_分支与循环/09_2026-11-05_Java星号三角形与输入校验_简介与学习建议.md) | 把输入校验与嵌套循环组合起来 |
| 10 | [`Sum.java`](02_分支与循环/Sum.java) | [`10_2026-11-05_Java循环求和与输入累加_简介与学习建议.md`](02_分支与循环/10_2026-11-05_Java循环求和与输入累加_简介与学习建议.md) | 维护累加器，处理固定次数的有效输入 |
| 11 | [`CountEven.java`](02_分支与循环/CountEven.java) | [`11_2026-11-05_Java偶数统计与取余边界_简介与学习建议.md`](02_分支与循环/11_2026-11-05_Java偶数统计与取余边界_简介与学习建议.md) | 用计数器统计满足条件的数，覆盖 0 和负数 |
| 12 | [`MaxMin.java`](02_分支与循环/MaxMin.java) | [`12_2026-11-05_Java循环求最大值最小值_简介与学习建议.md`](02_分支与循环/12_2026-11-05_Java循环求最大值最小值_简介与学习建议.md) | 用首个有效数初始化并在一次遍历中维护极值 |
| 13 | [`GuessNumber.java`](02_分支与循环/GuessNumber.java) | [`13_2026-11-05_Java猜数字与持续输入_简介与学习建议.md`](02_分支与循环/13_2026-11-05_Java猜数字与持续输入_简介与学习建议.md) | 用无限循环、随机范围和 `break` 完成持续交互 |
| 14 | [`ArrayAndBubbleSort.java`](03_方法与数组/ArrayAndBubbleSort.java) | [`14_2026-11-07_Java方法与数组综合练习_简介与学习建议.md`](03_方法与数组/14_2026-11-07_Java方法与数组综合练习_简介与学习建议.md) | 把数组操作拆成方法，理解引用、复制、排序、查找和固定测试 |
| 14A | [`ArrayAndBubbleSort.java`](03_方法与数组/ArrayAndBubbleSort.java) | [`14_2026-11-07_Java方法与数组综合练习_零基础教程.md`](03_方法与数组/14_2026-11-07_Java方法与数组综合练习_零基础教程.md) | 用引用图、执行轨迹和边界测试逐步理解综合程序 |
| 15 | [`MethodBasic.java`](03_方法与数组/MethodBasic.java) | [`15_2026-11-08_Java方法定义与返回值_简介与学习建议.md`](03_方法与数组/15_2026-11-08_Java方法定义与返回值_简介与学习建议.md) | 比较 `void`、参数、返回值和嵌套方法调用 |
| 16 | [`MaxOverload.java`](03_方法与数组/MaxOverload.java) | [`16_2026-11-08_Java方法重载_简介与学习建议.md`](03_方法与数组/16_2026-11-08_Java方法重载_简介与学习建议.md) | 按参数个数和类型选择同名方法 |
| 17 | [`PassByValue.java`](03_方法与数组/PassByValue.java) | [`17_2026-11-08_Java值传递与数组引用_简介与学习建议.md`](03_方法与数组/17_2026-11-08_Java值传递与数组引用_简介与学习建议.md) | 区分基本类型副本、数组引用副本和形参重新赋值 |
| 18 | [`PrimeDemo.java`](03_方法与数组/PrimeDemo.java) | [`18_2026-11-08_Java素数判定与循环边界_简介与学习建议.md`](03_方法与数组/18_2026-11-08_Java素数判定与循环边界_简介与学习建议.md) | 用布尔返回值、平方根范围和提前返回判断素数 |
| 19 | [`Narcissistic.java`](03_方法与数组/Narcissistic.java) | [`19_2026-11-08_Java三位水仙花数扫描_简介与学习建议.md`](03_方法与数组/19_2026-11-08_Java三位水仙花数扫描_简介与学习建议.md) | 练习三位拆位、区间扫描和 `found` 标记 |
| 20 | [`DigitCount.java`](03_方法与数组/DigitCount.java) | [`20_2026-11-08_Java数字出现次数统计_简介与学习建议.md`](03_方法与数组/20_2026-11-08_Java数字出现次数统计_简介与学习建议.md) | 用 `while` 逐位拆解并累计目标数字 |
| 21 | [`Divisors.java`](03_方法与数组/Divisors.java) | [`21_2026-11-08_Java约数枚举_简介与学习建议.md`](03_方法与数组/21_2026-11-08_Java约数枚举_简介与学习建议.md) | 枚举正约数并控制同行空格 |
| 22 | [`SecondLargest.java`](03_方法与数组/SecondLargest.java) | [`22_2026-11-08_Java第二大不同值_简介与学习建议.md`](03_方法与数组/22_2026-11-08_Java第二大不同值_简介与学习建议.md) | 识别读取范围和输出分支的真实逻辑错误 |
| 22A | [`SecondLargest.java`](03_方法与数组/SecondLargest.java) | [`22_2026-11-08_Java第二大不同值_零基础教程.md`](03_方法与数组/22_2026-11-08_Java第二大不同值_零基础教程.md) | 从数组默认值、变量状态到最小修复逐步调试 |
| 23 | [`PairCount.java`](03_方法与数组/PairCount.java) | [`23_2026-11-08_Java数对统计与暴力枚举_简介与学习建议.md`](03_方法与数组/23_2026-11-08_Java数对统计与暴力枚举_简介与学习建议.md) | 用双层循环统计不同下标数对 |

建议先按 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9 → 10 → 11 → 12 → 13 → 14 打好基础，再按 15 → 16 → 17 → 18 → 19 → 20 → 21 → 22 → 23 学习方法语义和入门算法。先运行程序，再阅读简介，卡住时再看零基础教程。`hellojava.java`、`BasicTypes.java` 以及 `ArrayAndBubbleSort.java` 都保留了大量原附件注释，编译器会忽略这些注释。

## Java 文件名规则

Java 与 C/Python 不同：如果一个类声明为 `public class BasicTypes`，源文件就必须叫 `BasicTypes.java`。因此当前采用以下规则：

- 源码文件名遵守 Java 自身的类名约束，优先使用清晰的 `ClassName.java`。
- 练习序号和首次进入仓库的日期写在配套文档名、导航表格和学习顺序中。
- 不为了套用 C/Python 编号格式去改 `public class` 的文件名。
- 历史附件 `hellojava.java` 保留原命名，避免未经授权修改源码；正式新写 Java 入门程序时优先使用 `HelloJava.java`、`BasicTypes.java` 这类大小写规范的类名。

`hellojava.java` 是有效的，因为它的类没有声明为 `public`；运行时使用的是类名 `hellojava`，不是文件名或另一个猜测的类名。

## 运行方式

本机已使用 OpenJDK 21。先在仓库根目录创建一个临时输出目录，避免把 `.class` 文件留在仓库：

```bash
java_dir=$(mktemp -d /tmp/java-practice.XXXXXX)
javac -encoding UTF-8 -Xlint:all -d "$java_dir" "Java/01_基础语法/hellojava.java"
java -cp "$java_dir" hellojava
```

运行基本类型练习：

```bash
java_dir=$(mktemp -d /tmp/java-practice.XXXXXX)
javac -encoding UTF-8 -Xlint:all -d "$java_dir" \
  "Java/01_基础语法/BasicTypes.java"
java -cp "$java_dir" BasicTypes
```

运行运算符练习：

```bash
java_dir=$(mktemp -d /tmp/java-practice.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/01_基础语法/OperatorsDemo.java"
java -cp "$java_dir" OperatorsDemo
```

运行成绩等级程序：

```bash
java_dir=$(mktemp -d /tmp/java-practice.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/02_分支与循环/GradeLevel.java"
printf '90\n' | java -cp "$java_dir" GradeLevel
```

运行不需要输入的循环基础程序：

```bash
java_dir=$(mktemp -d /tmp/java-loop.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/02_分支与循环/LoopBasics.java"
java -cp "$java_dir" LoopBasics
```

运行带输入重试的星号三角形：

```bash
java_dir=$(mktemp -d /tmp/java-star.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/02_分支与循环/StarTriangle.java"
printf 'abc\n0\n3\n' | java -cp "$java_dir" StarTriangle
```

运行方法与数组综合练习：

```bash
java_dir=$(mktemp -d /tmp/java-array.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/03_方法与数组/ArrayAndBubbleSort.java"
java -cp "$java_dir" ArrayAndBubbleSort
```

程序不需要输入，内置 18 条教学检查。正常结果应只出现 `[通过]`；若出现 `[失败]`，即使命令退出码仍为 0 也必须继续排查，因为教学检查器没有主动改变退出状态。

运行方法基础和值传递练习：

```bash
java_dir=$(mktemp -d /tmp/java-method.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/03_方法与数组/MethodBasic.java" \
  "Java/03_方法与数组/PassByValue.java"
java -cp "$java_dir" MethodBasic
java -cp "$java_dir" PassByValue
```

带输入的算法练习各自使用 `printf` 送入测试数据，例如统计 1～100 中数字 0 的出现次数：

```bash
java_dir=$(mktemp -d /tmp/java-algorithm.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/03_方法与数组/DigitCount.java"
printf '100 0\n' | java -cp "$java_dir" DigitCount
```

每份源码的完整命令和真实输出见同目录 15～23 号说明。`SecondLargest.java` 当前保留逻辑错误，编译能通过，但典型输入会输出 `NO`；不要把它当作正确标准答案。

`SwitchBreakDemo.java` 故意展示 11 处 `[fallthrough]`，使用 `-Xlint:all` 编译会看到这些预期警告；如果加 `-Werror`，编译会按设计失败。不要为了通过严格检查而删除原始反例，详见同目录说明。

`javac` 编译源文件，`java` 启动指定类。`-d` 把编译结果放进临时目录，`-cp` 告诉 Java 去哪里查找编译后的类。完整环境说明见 [开发环境与 Git 操作指南](../docs/开发环境与Git操作指南.md)。

## 推荐学习顺序

1. 先能独立写出一个类和一个 `main` 方法。
2. 能解释 `System.out.println` 为什么必须写在 `main` 的调用路径中。
3. 能列出八种基本类型，以及 `String` 为什么不属于基本类型。
4. 能解释自动类型转换、强制类型转换和整数除法。
5. 能手工说明为什么 `2147483647 + 1` 在表达式中会溢出，以及为什么加 `L` 后结果不同。
6. 能区分 `&&` 与 `&`、`||` 与 `|`，并说明短路为什么能保护除以零的表达式。
7. 能沿着 `case` 顺序追踪 `switch`，说明 `break` 和 `default` 的作用。
8. 能写出闰年条件和成绩边界，测试 1900、2000、59、60、89、90、100、非法输入。
9. 能说明 `while` 与 `do-while` 在首次条件为假时的执行次数差异。
10. 能手工跟踪单层循环和外层、内层循环的变量变化。
11. 能写出九九乘法表和带输入校验的星号三角形。
12. 能在循环中维护累加器、计数器和当前最大值/最小值。
13. 能说明随机猜数字程序的秘密数字、有效猜测次数和循环出口。
14. 能解释数组默认值、下标、`length` 和普通 `for` 与增强 `for` 的差别。
15. 能画出数组直接赋值、`Arrays.copyOf` 和数组传参的引用关系。
16. 能手工跟踪冒泡排序、线性查找和二分查找，并说明二分查找的有序前提。
17. 能说明 Java 只有值传递，数组方法修改的是共同引用指向的元素。
18. 能根据参数个数和类型判断方法重载会选哪个版本。
19. 能分别写出素数判断、正约数枚举、数字拆位统计和不同下标数对统计。
20. 能定位“数组默认值未读入、最大值初始化、`found` 状态和输出分支”共同造成的逻辑错误。
21. 合上源码，自己写一个数组求和与冒泡排序程序，再写一个带退出条件的小游戏。

当前已经覆盖程序入口、基本类型、主要运算符、输入校验、条件分支、循环、方法拆分与重载、值传递、数组引用，以及多种入门算法。数组越界异常已有演示，但没有把一套异常处理体系当成已掌握内容；面向对象、集合、二维数组、正式自动测试和工程构建仍缺少练习。

## 维护规则

新增 Java 练习时：

1. 根据主要学习目标建立或选择二级目录。当前已有 `01_基础语法`、`02_分支与循环` 和 `03_方法与数组`；面向对象等目录等真实练习出现后再新增。
2. 源码文件名遵守 `public` 类的文件名要求。
3. 在源码旁增加带序号和日期的 `_简介与学习建议.md`；内容复杂时再增加 `_零基础教程.md`。
4. 编译时使用 UTF-8，并把 `.class` 输出到临时目录。
5. 未经明确授权，不修改原始学习代码；发现注释或代码问题时先在学习说明中记录。
6. 如果是故意演示警告的源码，在说明中写清预期警告和严格编译结果，不用修改源码来掩盖教学现象。
7. 如果源码能编译但逻辑错误，分别记录当前真实输出、题目预期、出错位置和最小修复思路；需要修正代码时另建版本，不覆盖原始附件。
