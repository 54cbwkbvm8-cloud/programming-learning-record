# Java 学习区

这里保存 Java 课程练习和学习说明。Java 源码必须遵守类名与文件名规则，因此不能直接照搬 C/Python 的 `NN_日期_主题.java` 命名方式；首次整理顺序和日期记录在配套文档中。系统的知识点、难点和掌握标准见 [Java 学习指南](../docs/Java学习指南.md)。

## 目录一览

| 目录 | 源码数 | 学习重点 |
| --- | --- | --- |
| [01_基础语法](01_基础语法/) | 2 | 类、`main` 方法、控制台输出、基本数据类型、类型转换和整数溢出 |

当前只有 Java 入门阶段的真实练习，因此没有提前创建分支、循环、类与对象、集合、异常等空目录。

## 练习入口

| 顺序 | 源码 | 配套说明 | 主要目标 |
| --- | --- | --- | --- |
| 1 | [`hellojava.java`](01_基础语法/hellojava.java) | [`01_2026-11-02_Java程序入口与输出_简介与学习建议.md`](01_基础语法/01_2026-11-02_Java程序入口与输出_简介与学习建议.md) | 认识类、`main` 方法、`System.out.println` 和运行入口 |
| 2 | [`BasicTypes.java`](01_基础语法/BasicTypes.java) | [`02_2026-11-02_Java基本数据类型与类型转换_简介与学习建议.md`](01_基础语法/02_2026-11-02_Java基本数据类型与类型转换_简介与学习建议.md) | 比较八种基本类型、自动与强制转换、类型范围和溢出 |
| 2A | [`BasicTypes.java`](01_基础语法/BasicTypes.java) | [`02_2026-11-02_Java基本数据类型与类型转换_零基础教程.md`](01_基础语法/02_2026-11-02_Java基本数据类型与类型转换_零基础教程.md) | 按程序结构逐步理解变量、运算和转换 |

建议先学 1，再学 2；先运行程序，再阅读简介，卡住时再看零基础教程。`hellojava.java` 的源码里已经保留了一段较长的注释；`BasicTypes.java` 后半部分也是原附件带来的学习注释，编译器会把它们全部忽略。

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

运行第二个程序：

```bash
java_dir=$(mktemp -d /tmp/java-practice.XXXXXX)
javac -encoding UTF-8 -Xlint:all -d "$java_dir" "Java/01_基础语法/BasicTypes.java"
java -cp "$java_dir" BasicTypes
```

`javac` 编译源文件，`java` 启动指定类。`-d` 把编译结果放进临时目录，`-cp` 告诉 Java 去哪里查找编译后的类。完整环境说明见 [开发环境与 Git 操作指南](../docs/开发环境与Git操作指南.md)。

## 推荐学习顺序

1. 先能独立写出一个类和一个 `main` 方法。
2. 能解释 `System.out.println` 为什么必须写在 `main` 的调用路径中。
3. 能列出八种基本类型，以及 `String` 为什么不属于基本类型。
4. 能解释自动类型转换、强制类型转换和整数除法。
5. 能手工说明为什么 `2147483647 + 1` 在表达式中会溢出，以及为什么加 `L` 后结果不同。
6. 合上源码，自己写一个 `StudentInfo.java`：声明姓名、年龄、成绩和是否通过，并打印出来。

当前只覆盖 Java 的最初两级入口。分支、循环、方法、数组、面向对象、集合和异常还没有足够的独立练习，不把它们写成“已经掌握”的结论。

## 维护规则

新增 Java 练习时：

1. 根据主要学习目标建立或选择二级目录，例如后续真实需要时再新增分支与循环、方法与数组、面向对象。
2. 源码文件名遵守 `public` 类的文件名要求。
3. 在源码旁增加带序号和日期的 `_简介与学习建议.md`；内容复杂时再增加 `_零基础教程.md`。
4. 编译时使用 UTF-8，并把 `.class` 输出到临时目录。
5. 未经明确授权，不修改原始学习代码；发现注释或代码问题时先在学习说明中记录。
