# Java toString 与对象打印简介

## 文件基本信息

- 源码：[Student.java](Student.java)、[RawStudent.java](RawStudent.java)、[ToStringPredictDemo.java](ToStringPredictDemo.java)
- 首次整理日期：2026-11-16
- 所属分类：`Java/06_Object方法与对象数组`
- 程序用途：对比“没有重写 `toString`”和“重写 `toString`”的对象，在直接打印和字符串拼接时分别输出什么。
- 主要问题：`System.out.println(对象)` 背后调用了什么；为什么默认输出是一串看不懂的十六进制。
- 核心知识点：`Object` 祖先类、默认 `toString`、重写、`@Override`、`println` 的自动调用、字符串拼接、`private` 字段与 getter。
- 前置知识：类与对象、构造方法、`new`、成员变量、方法调用。
- 相关练习：先完成 [类对象构造方法与引用](../05_面向对象/25_2026-11-12_Java类对象构造方法与引用_简介与学习建议.md)；下一题是 [equals 与 hashCode 重写](33_2026-11-16_Java-equals与hashCode重写_简介与学习建议.md)。

> 本目录的 `Student` 是“姓名 + 成绩”版本，与 `Java/05_面向对象/Student.java` 的“学号 + 姓名 + 成绩”版本不是同一个类。两个类都叫 `Student`，不能放进同一目录一起编译；本目录因此单独存放，详见下文“与旧版 Student 的关系”。

## 程序功能说明

`RawStudent` 只有两个私有字段和一个构造方法，没有重写任何方法。`Student` 的字段相同，但重写了 `toString`、`equals` 和 `hashCode`。`ToStringPredictDemo` 分别创建两个对象并打印：

```java
RawStudent raw = new RawStudent("Tom", 90);
System.out.println(raw);

Student stu = new Student("Tom", 90);
System.out.println(stu);
System.out.println("字符串拼接也会自动调用 toString(): " + stu);
System.out.println("手动调用 raw.toString(): " + raw.toString());
```

`RawStudent` 使用 `Object` 提供的默认 `toString`，输出形如 `RawStudent@6f2b958e`：类名、`@`、对象哈希码的十六进制形式。`Student` 重写后输出 `Student{name='Tom', score=90}`，字段值一目了然。

## 运行方式

三个文件必须一起编译：

```bash
java_dir=$(mktemp -d /tmp/java-tostring.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/06_Object方法与对象数组/Student.java" \
  "Java/06_Object方法与对象数组/RawStudent.java" \
  "Java/06_Object方法与对象数组/ToStringPredictDemo.java"
java -cp "$java_dir" ToStringPredictDemo
```

真实输出（`@` 后面的十六进制每次运行可能不同）：

```text
RawStudent@6f2b958e
Student{name='Tom', score=90}
字符串拼接也会自动调用 toString(): Student{name='Tom', score=90}
手动调用 raw.toString(): RawStudent@6f2b958e
```

## 学习重点

- 所有类都直接或间接继承 `Object`。即使 `RawStudent` 一行都没写，它也拥有 `toString`、`equals`、`hashCode` 等方法。
- `System.out.println(对象)` 会选择 `println(Object)` 重载，内部把对象转成字符串，转换过程会调用对象的 `toString()`。
- 字符串拼接 `"文字" + 对象` 也会自动调用 `toString()`，不需要手写 `.toString()`。
- 重写 `toString` 的作用是让调试输出可读。返回的字符串内容由程序员决定，但应尽量包含类名和关键字段。
- `@Override` 是编译期标记。方法名、参数或返回类型写错时，编译器会报错，避免“以为重写了，其实写了一个新方法”。
- `Student` 的字段是 `private`，外部只能通过 `getName()`、`getScore()` 读取；`toString` 在类内部可以直接访问字段。

## 学习难点

### 默认的十六进制不是地址

`Object.toString()` 的默认实现大致返回 `getClass().getName() + "@" + Integer.toHexString(hashCode())`。它看起来像内存地址，实际上是哈希码的十六进制形式。不同对象、不同运行可能得到不同数字，所以不要把 `RawStudent@6f2b958e` 当成固定输出背下来。判断程序是否正确时，应看“是否以类名和 `@` 开头”，而不是核对具体数字。

### 直接打印 null 不会崩溃

如果变量是 `null`，`System.out.println(变量)` 会输出 `null`，不会调用 `toString`，也不会抛异常。真正会崩溃的是“对 `null` 调用方法”，例如 `students[1].getName()`，见 [空指针简介](34_2026-11-16_Java空指针与对象数组判空_简介与学习建议.md)。

### 重写不是重载

`toString()` 没有参数，返回 `String`。如果在 `Student` 里写成 `toString(String prefix)`，那是重载，不是重写；`println` 仍然会调用无参版本。`@Override` 能帮助发现这种错误。

### 字符串里的单引号

`toString` 中为了输出 `name='Tom'`，使用 `'\''` 表示一个单引号字符。单引号本身是 `char` 的边界符号，必须用反斜杠转义。`"name='"` 是普通字符串，里面的单引号不需要转义。

## 与旧版 Student 的关系

仓库里现在有两个 `Student`：

| 位置 | 字段 | 特点 |
| --- | --- | --- |
| `Java/05_面向对象/Student.java` | `id`、`name`、`score` | 字段公开，有 `printInfo()`，用于类对象入门 |
| `Java/06_Object方法与对象数组/Student.java` | `name`、`score` | 字段私有，有 getter，并重写 `toString`、`equals`、`hashCode` |

Java 要求 `public class Student` 必须放在 `Student.java`，而同一编译范围内不能有两个同名类。因此两个版本必须分目录编译，不能合并到同一个文件夹。这也说明“类名是全局标识”，不是随便起的文件名。

## 建议学习流程

1. 先不运行，写下四行预测输出，`@` 后面的数字可以写成 `???`。
2. 运行程序，对照预测；重点看第 2、3 行是否相同。
3. 把 `Student.toString()` 的返回值改成 `"学生：" + name + "，成绩：" + score`，重新编译运行，观察输出。
4. 删除 `@Override`，把方法名改成 `tostring`，观察 `println` 是否还调用它，再改回。
5. 给 `RawStudent` 增加 `getName()`，但仍然不重写 `toString`，确认打印结果不变。
6. 关闭源码，自己写一个 `Book` 类，包含书名和价格，并重写 `toString`。

## 易错点与测试用例

| 操作 | 真实输出 | 检查目标 |
| --- | --- | --- |
| 打印 `RawStudent` | `RawStudent@<十六进制>` | 默认 `toString` |
| 打印 `Student` | `Student{name='Tom', score=90}` | 重写生效 |
| 拼接字符串与 `Student` | 自动调用重写版本 | `+` 的自动转换 |
| 手动 `raw.toString()` | 与第一次打印相同的十六进制 | 同一对象哈希码稳定 |
| 方法名写成 `tostring` | 仍输出默认格式 | 大小写敏感、未真正重写 |

前四行已于 2026-11-16 实际运行验证；最后一行是按 Java 规则推导的预期结果，未单独运行。

常见错误包括：以为默认输出是内存地址并试图解析；重写时写成有参方法；忘记 `@Override` 导致拼写错误未被发现；在 `toString` 中访问未初始化字段；把 `Student` 与旧版放在同一目录导致 `duplicate class` 编译错误。

## 拓展练习

1. 为 `RawStudent` 也重写 `toString`，比较两个类的输出。
2. 把 `Student.toString` 改成 JSON 风格 `{"name":"Tom","score":90}`，注意引号转义。
3. 增加一个 `Student[]` 数组，分别打印数组变量和 `Arrays.toString(数组)`，观察两者差异。
4. 给 `Student` 增加 `getScore()` 并在 `toString` 中使用，比较直接访问字段和调用 getter 的写法。
5. 预习 [equals 与 hashCode](33_2026-11-16_Java-equals与hashCode重写_简介与学习建议.md)：为什么重写 `toString` 不会影响对象比较？