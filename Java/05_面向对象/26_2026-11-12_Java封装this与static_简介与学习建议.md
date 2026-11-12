# Java 封装、this 与 static 简介

## 文件基本信息

- 源码：[EncapsulationStaticDemo.java](EncapsulationStaticDemo.java)
- 首次整理日期：2026-11-12
- 所属分类：`Java/05_面向对象`
- 程序用途：用一个文件里的多组对比类，演示封装、访问控制、构造校验、`this`、`static`、静态方法以及对象作为方法参数时的引用行为。
- 主要问题：为什么公开字段不安全；构造方法怎样绕过 setter 校验；`this` 怎样区分字段和参数；普通字段与静态字段有什么区别；对象传入方法后为什么能改内容，却不能通过重新赋值替换调用者变量。
- 核心知识点：`public`、`private`、封装、getter、setter、构造方法校验、`this`、`static` 字段、`static` 方法、实例字段、对象引用传参、引用重新赋值、`IllegalArgumentException`。
- 前置知识：类、对象、构造方法、成员变量、方法参数和返回值、对象引用、`==`。
- 相关练习：先完成 [25 号类、对象、构造方法与引用](25_2026-11-12_Java类对象构造方法与引用_简介与学习建议.md)，再学习本练习。
- 配套教程：[26 号零基础教程](26_2026-11-12_Java封装this与static_零基础教程.md)

## 分类理由

本文件的主要学习目标已经不是流程控制或数组，而是对象内部状态保护、实例成员与类成员的区别，以及对象引用在方法调用中的行为。因此与 25 号一起归入 `Java/05_面向对象`。

## 文件中的多个类

可执行代码为第 1～112 行，第 113 行以后是历史注释。

| 类 | 学习定位 |
| --- | --- |
| `EncapsulationStaticDemo` | 唯一的 `public` 类，提供 `main` 和两个静态演示方法 |
| `SafeStudent` | 正面示范：私有字段、构造校验、getter/setter、`this`、静态计数 |
| `OpenStudent` | 反面示范：字段公开，可直接写入非法成绩 |
| `NoThisStudent` | 反面示范：参数给自己赋值，字段仍为默认值 |
| `CounterWrong` | 反面示范：用普通字段记录对象创建次数 |

Java 一个源文件可以定义多个类，但最多一个 `public` 类，且 `public` 类名必须与文件名一致。这里只有 `EncapsulationStaticDemo` 是 `public`。

## 程序功能说明

### 第一部分：封装

`OpenStudent` 的 `score` 没有访问限制：

```java
OpenStudent openStu = new OpenStudent();
openStu.score = -999;
```

外部可以直接写入非法值，类没有机会拒绝。

`SafeStudent` 的字段是：

```java
private String id;
private String name;
private int score;
```

外部不能直接访问，只能通过公开 getter/setter：

```java
s1.getId()
s1.getName()
s1.getScore()
s1.setScore(value)
```

`setScore` 返回布尔值：

- 合法：写入并返回 `true`。
- 不合法：原成绩不变并返回 `false`。

构造方法也调用同一个 `isValidScore`：

```java
if (!isValidScore(score)) {
    throw new IllegalArgumentException(...);
}
```

这保证对象从创建时开始就处于合法状态，不能通过构造方法绕过 setter 规则。

### 第二部分：this

`NoThisStudent` 写：

```java
name = name;
score = score;
```

左右两边都是参数，成员变量没有被赋值，所以打印：

```text
name=null，score=0
```

`SafeStudent` 写：

```java
this.name = name;
this.score = score;
```

左边是当前对象字段，右边是参数。

### 第三部分：static

`CounterWrong` 的字段：

```java
int count = 0;
```

每个对象都有独立一份，构造时各自从 0 加到 1，所以三个对象都显示 `count=1`。

`SafeStudent` 使用：

```java
private static int count = 0;
```

整个类只有一份 `count`，每次成功创建对象都 `count++`。

静态方法：

```java
public static int getCount()
```

直接通过类名调用：

```java
SafeStudent.getCount()
```

### 第四部分：对象作为参数

```java
changeScore(s1)
```

方法参数 `s` 收到的是 `s1` 中引用值的副本，两个变量指向同一个对象。因此 `s.setScore(60)` 会修改 `s1` 指向对象的成绩。

```java
reassign(s1)
```

方法内执行：

```java
s = new SafeStudent("99999999", "临时", 1);
```

只是让局部参数 `s` 改指向一个新对象。`s1` 仍指向原对象，所以外部姓名仍是“张三”。

临时对象创建成功，因此静态计数器最终从 3 增加到 4。`count` 统计“创建过多少次”，不是“当前还存活多少个对象”。

## 运行方式

本程序不需要输入。在仓库根目录执行：

```bash
java_dir=$(mktemp -d /tmp/java-encapsulation.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/05_面向对象/EncapsulationStaticDemo.java"
java -cp "$java_dir" EncapsulationStaticDemo
```

已在 OpenJDK 21.0.13 下实际通过严格编译并运行，真实输出为：

```text
========== 第一部分：封装（private + getter/setter） ==========
没有封装时，成绩被改成：-999
用 getter 读取：20260101，张三，85
setScore(150) -> false，当前成绩：85
setScore(-1) -> false，当前成绩：85
setScore(101) -> false，当前成绩：85
setScore(0) -> true，当前成绩：0
setScore(100) -> true，当前成绩：100
========== 第二部分：this 区分成员变量与参数 ==========
漏写 this：name=null，score=0
写了 this：name=李四，score=90
========== 第三部分：static 变量与 static 方法 ==========
普通变量计数：c1=1，c2=1，c3=1
static 计数器：共创建了 3 个学生对象
========== 第四部分：对象作参数，传入的是地址的副本 ==========
调用前 s1 的成绩：100
调用 changeScore(s1) 后，s1 的成绩：60
方法内部 s 指向的姓名：临时
调用 reassign(s1) 后，s1 的姓名：张三
此时 static 计数器：4
```

## 学习重点

- 封装不是只写 getter/setter，而是让所有修改入口都遵守同一套规则。
- 构造方法也是修改对象状态的重要入口，不能绕过校验。
- `private` 限制类外直接访问，公开方法提供受控接口。
- `this` 表示当前实例，同名参数遮蔽字段时必须使用。
- 普通字段每个对象一份，`static` 字段整个类共享一份。
- `static` 方法能直接访问静态成员，不能直接访问某个具体对象的实例字段。
- 对象参数复制的是引用值；通过副本能修改共同对象，重新给副本赋值不会替换调用者变量。

## 学习难点

### 为什么 OpenStudent 能写 -999

字段没有 `private`，外部语句 `openStu.score = -999` 合法。SafeStudent 把字段藏起来后，外部只能调用 `setScore`，非法值会被拒绝。

### 为什么构造方法也要检查

如果只有 setter 检查，攻击者仍能：

```java
SafeStudent s = new SafeStudent("1", "A", 150);
```

构造方法若直接赋值，对象一出生就非法。当前构造方法复用 `isValidScore`，不合法直接抛异常。

### 为什么 CounterWrong 都是 1

`int count = 0` 是实例字段。每创建对象，先执行字段初始化得到自己的 0，再在构造方法中加 1。三个独立字段互不影响。

### 为什么 `reassign` 不改变外部 s1

方法参数是引用值的副本：

```text
调用前：
s1 ──> 原对象
s  ──> 原对象

重新赋值后：
s1 ──> 原对象
s  ──> 新临时对象
```

只有 `s` 指向新对象，`s1` 没有变化。

## 已知边界与限制

- `SafeStudent` 是包级可见示范类，没有单独放入 `SafeStudent.java`。
- `id` 和 `name` 只有 getter，没有 setter，创建后不能修改。
- `count` 只增不减，统计创建次数，不统计当前对象数量。
- 构造方法抛出 `IllegalArgumentException`，当前 `main` 没有用 `try-catch`，因为只传合法成绩。
- `OpenStudent`、`NoThisStudent` 和 `CounterWrong` 是故意保留的反面教材。
- `changeScore` 没有处理 `setScore` 返回 `false`，只用于讲解对象引用。
- 本例没有继承、多态、接口、对象数组、`equals` 或自动测试。

## 建议学习流程

1. 先画出 `OpenStudent` 与 `SafeStudent` 的字段访问边界。
2. 说明 `setScore(150)` 为什么返回 false 且成绩仍为 85。
3. 解释构造函数校验与 setter 校验为什么必须使用同一规则。
4. 对 `NoThisStudent` 和 `SafeStudent` 做变量表对比。
5. 画三个 `CounterWrong` 对象各自的 `count`，再画 `SafeStudent.count` 的共享位置。
6. 跟踪 `changeScore` 与 `reassign` 的引用图。
7. 运行程序，对照 20 行真实输出。
8. 阅读 [零基础教程](26_2026-11-12_Java封装this与static_零基础教程.md)，再独立改写安全类。

## 易错点与测试用例

| 场景 | 实际结果 | 检查目标 |
| --- | --- | --- |
| `OpenStudent.score = -999` | 直接成功 | 无封装风险 |
| `SafeStudent` 初值 85 | getter 返回 85 | 创建合法对象 |
| `setScore(150)` | false，仍 85 | 上界外非法 |
| `setScore(-1)` | false，仍 85 | 负数非法 |
| `setScore(101)` | false，仍 85 | 上界外非法 |
| `setScore(0)` | true，变 0 | 下边界合法 |
| `setScore(100)` | true，变 100 | 上边界合法 |
| 漏写 this | name=null，score=0 | 参数遮蔽 |
| 三个实例字段计数 | 都是 1 | 普通字段独立 |
| 创建 3 个 SafeStudent | static count=3 | 静态字段共享 |
| `changeScore(s1)` | 成绩变 60 | 修改共同对象 |
| `reassign(s1)` 后 | s1 姓名仍为张三 | 形参重新赋值 |
| static count 最终值 | 4 | 计数创建动作，含临时对象 |

## 拓展练习

1. 增加 `setName`，检查空字符串和只含空格。
2. 增加 `try-catch`，演示非法构造参数。
3. 增加 `static int getLiveCount` 与 `decreaseCount`，讨论对象销毁和计数管理。
4. 把 SafeStudent 拆成独立文件并声明 `public class SafeStudent`。
5. 给 SafeStudent 增加 `toString()` 和 `equals()`。
6. 创建 `SafeStudent[]`，用对象数组重写成绩管理程序。
7. 设计 `BankAccount`，私有余额、存款和取款方法，理解业务校验必须覆盖所有修改入口。
8. 完成本练习后进入继承、多态和接口学习。
