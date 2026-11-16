# Java equals 与 hashCode 重写简介

## 文件基本信息

- 源码：[EqualsDemo.java](EqualsDemo.java)、[Student.java](Student.java)、[RawStudent.java](RawStudent.java)
- 首次整理日期：2026-11-16
- 所属分类：`Java/06_Object方法与对象数组`
- 程序用途：对比基本类型、`String`、重写了 `equals` 的 `Student` 和没有重写的 `RawStudent`，说明 `==` 与 `equals` 的区别。
- 主要问题：两个内容相同的对象为什么 `==` 为 `false`；重写 `equals` 后为什么还要重写 `hashCode`。
- 核心知识点：引用比较与内容比较、字符串常量池、`Object.equals`、重写 `equals`、`getClass`、`Objects.equals`、`Objects.hash`、`equals` 与 `hashCode` 的一致性约定。
- 前置知识：类与对象、引用变量、构造方法、`String` 是引用类型、方法重写。
- 相关练习：先完成 [对象打印与 toString](32_2026-11-16_Java-toString与对象打印_简介与学习建议.md)；逐步讲解见 [零基础教程](33_2026-11-16_Java-equals与hashCode重写_零基础教程.md)。

> 本目录的 `Student` 是“姓名 + 成绩”版本，与 `Java/05_面向对象/Student.java` 不同，不能同目录编译。源码保留上传时的逻辑和 `"Tome"` 拼写，未修改。

## 程序功能说明

`EqualsDemo` 分四组对比：

```java
int x = 5, y = 5;                  // 基本类型：== 比较数值
String s1 = "abc", s2 = "abc";     // 常量池：s1 和 s2 指向同一对象
String s3 = new String("abc");     // new：新对象，内容相同但地址不同
Student a = new Student("Tome", 90);
Student b = new Student("Tome", 90);
Student c = a;
RawStudent r1 = new RawStudent("Tome", 90);
RawStudent r2 = new RawStudent("Tome", 90);
```

输出依次说明：

- `x == y` 为 `true`，基本类型比较数值。
- `s1 == s2` 为 `true`，两个字符串字面量指向常量池中的同一个对象。
- `s1 == s3` 为 `false`，`new String` 强制创建了新对象。
- `s1.equals(s3)` 为 `true`，`String` 重写了 `equals`，比较字符内容。
- `a == b` 为 `false`，两个独立 `new` 出来的对象地址不同。
- `a.equals(b)` 为 `true`，`Student` 重写后规定“姓名和成绩都相同即相等”。
- `a == c` 为 `true`，`c = a` 只是复制引用，两个变量指向同一个对象。
- `r1 == r2` 和 `r1.equals(r2)` 都为 `false`，`RawStudent` 没有重写 `equals`，默认行为等同于 `==`。

## 运行方式

三个文件一起编译：

```bash
java_dir=$(mktemp -d /tmp/java-equals.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/06_Object方法与对象数组/Student.java" \
  "Java/06_Object方法与对象数组/RawStudent.java" \
  "Java/06_Object方法与对象数组/EqualsDemo.java"
java -cp "$java_dir" EqualsDemo
```

真实输出：

```text
x == y : true
s1 == s2 : true
s1 == s3 : false
s1.equals(s3) : true
a == b : false
a.equals(b) : true
a == c : true
r1 == r2 : false
r1.equals(r2) : false
```

## 学习重点

- 基本类型变量直接保存数值，`==` 比较数值。
- 对象变量保存引用。`==` 比较两个引用是否指向同一个对象，不比较内容。
- `equals` 的行为取决于类是否重写。`Object` 默认的 `equals` 等同于 `==`；`String`、`Student` 重写后按内容比较。
- `String s = "abc"` 会复用常量池中的对象；`new String("abc")` 一定创建新对象。这个差异只影响 `==`，不影响 `equals`。
- 重写 `equals` 的标准步骤：先判断 `this == obj`，再排除 `null` 和不同类，最后强制转换并比较关键字段。
- `getClass() != obj.getClass()` 要求“必须是同一个类”。如果改用 `instanceof`，子类对象也可能通过比较，需要额外考虑对称性。
- `Objects.equals(a, b)` 能安全处理 `null`；`Objects.hash(...)` 根据参与 `equals` 的字段计算哈希码。
- 约定：如果 `a.equals(b)` 为 `true`，则 `a.hashCode() == b.hashCode()` 必须成立。重写 `equals` 通常必须同时重写 `hashCode`。

## 学习难点

### `==` 到底比什么

```java
Student a = new Student("Tome", 90);
Student b = new Student("Tome", 90);
```

堆里有两个独立对象，`a` 和 `b` 分别保存两个地址。`a == b` 问的是“地址相同吗”，答案是否定的。`a.equals(b)` 问的是“按 `Student` 定义的规则，内容相同吗”，答案是肯定的。

### 字符串常量池

```java
String s1 = "abc";
String s2 = "abc";
String s3 = new String("abc");
```

Java 会把直接写出的字符串字面量放进常量池，相同内容只保留一份，所以 `s1` 和 `s2` 指向同一个对象。`new String("abc")` 明确要求新建对象，`s3` 指向另一块内存。比较字符串内容应始终使用 `equals`，不要依赖 `==` 的偶然结果。

### `equals` 的参数为什么是 `Object`

`Object.equals(Object obj)` 的参数类型是 `Object`。重写时参数类型必须一致，否则就是重载。因为参数是 `Object`，进入方法后要先判断类型，再强制转换成 `Student`，才能访问 `score` 和 `name`。

### 为什么先判断 `null`

`obj == null || getClass() != obj.getClass()` 中，`||` 是短路的：左边为 `true` 时右边不执行。如果先调用 `obj.getClass()`，当 `obj` 是 `null` 时会立刻抛出 `NullPointerException`。顺序不能颠倒。

### 为什么重写 `equals` 还要重写 `hashCode`

哈希类集合（如 `HashSet`、`HashMap`）先用 `hashCode` 找桶，再用 `equals` 精确比较。如果两个对象 `equals` 为 `true` 但 `hashCode` 不同，它们会被放进不同的桶，导致“内容相同却重复存入”的错误。`Objects.hash(name, score)` 使用与 `equals` 相同的字段，保证内容相同则哈希码相同。

### `"Tome"` 拼写

源码中两个学生都写成 `"Tome"`，不是 `"Tom"`。这是原附件的拼写，不影响比较结果，因为两边一致。保留原文，不擅自修改；阅读时知道它是姓名即可。

## 建议学习流程

1. 先画出 `a`、`b`、`c` 三个变量和两个对象的引用图。
2. 不运行，写出 9 行输出的预测。
3. 运行程序，对照预测，重点解释 `a == b` 与 `a.equals(b)` 为什么不同。
4. 把 `Student.equals` 中的 `score == student.score` 改成 `false`，观察 `a.equals(b)` 的变化，再改回。
5. 删除 `hashCode` 方法，先不运行，思考如果把 `Student` 放进 `HashSet` 会怎样；本练习尚未引入集合，可作为预习。
6. 关闭源码，自己写一个 `Point` 类（`x`、`y`），正确重写 `equals` 和 `hashCode`。

## 易错点与测试用例

| 比较 | 真实结果 | 原因 |
| --- | --- | --- |
| `5 == 5` | `true` | 基本类型比数值 |
| `"abc" == "abc"` | `true` | 常量池复用 |
| `"abc" == new String("abc")` | `false` | 两个不同对象 |
| `"abc".equals(new String("abc"))` | `true` | `String` 按内容比较 |
| 两个内容相同的 `Student` 用 `==` | `false` | 引用不同 |
| 两个内容相同的 `Student` 用 `equals` | `true` | 重写后按字段比较 |
| 两个内容相同的 `RawStudent` 用 `equals` | `false` | 未重写，等同 `==` |
| `a == c`（`c = a`） | `true` | 同一个引用 |

以上结果已于 2026-11-16 实际运行验证。

常见错误包括：用 `==` 比较字符串内容；重写 `equals` 时参数写成 `Student` 导致重载；忘记 `null` 检查；用 `==` 比较 `String` 字段；只重写 `equals` 不重写 `hashCode`；`hashCode` 使用了 `equals` 没有参与的字段；把 `getClass()` 和 `instanceof` 混用后破坏对称性。

## 拓展练习

1. 给 `Student` 增加 `id` 字段，规定“学号相同即相等”，同步修改 `equals` 和 `hashCode`。
2. 写一个 `Point` 类，比较两个坐标相同的点。
3. 在 `EqualsDemo` 中增加 `a.equals(null)` 和 `a.equals("abc")`，确认都返回 `false` 且不崩溃。
4. 预习集合：创建 `HashSet<Student>`，分别放入两个内容相同的对象，观察去重是否生效；这需要先学习 `java.util` 集合。
5. 思考：如果 `equals` 用 `instanceof` 判断，子类对象与父类对象互相比较时可能出现什么不对称结果？