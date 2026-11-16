# Java equals 与 hashCode 重写零基础教程

这份教程配合 [EqualsDemo.java](EqualsDemo.java)、[Student.java](Student.java) 和 [RawStudent.java](RawStudent.java) 使用。建议先运行一遍程序，再按本文顺序理解；不要一开始就背 `equals` 模板。

## 一、先记住三句话

1. 基本类型（`int`、`double`、`char`、`boolean` 等）的变量里直接放着数值，`==` 比数值。
2. 对象类型的变量里放的是“找到对象的线索”（引用），`==` 比的是“是不是同一个对象”。
3. 想比较两个对象的内容，要用 `equals`；`equals` 到底怎么比，取决于这个类有没有重写它。

`Student` 重写了 `equals`，所以两个内容相同的学生算相等；`RawStudent` 没重写，所以即使内容相同也判为不相等。这就是整份程序的主线。

## 二、变量里到底存了什么

### 基本类型

```java
int x = 5;
int y = 5;
```

可以想成两个盒子里各放一张写着 5 的纸条。`x == y` 比较纸条上的数字，得到 `true`。

### 对象类型

```java
Student a = new Student("Tome", 90);
Student b = new Student("Tome", 90);
```

`new` 在内存中创建一个对象。变量 `a` 里放的不是姓名和成绩本身，而是“对象在哪里”的引用。可以画成：

```text
a ──────> [ name="Tome", score=90 ]   （对象 1）
b ──────> [ name="Tome", score=90 ]   （对象 2）
```

两个对象的内容一样，但它们是两次 `new` 创建的，位置不同。`a == b` 问“a 和 b 指向同一个对象吗”，答案是 `false`。

```java
Student c = a;
```

这行没有 `new`，只是把 `a` 里的引用复制给 `c`：

```text
a ──┐
    ├──> [ name="Tome", score=90 ]   （对象 1）
c ──┘

b ──────> [ name="Tome", score=90 ]   （对象 2）
```

所以 `a == c` 是 `true`。通过 `c` 修改对象，`a` 看到的内容也会变，因为它们是同一个对象。

## 三、字符串为什么特殊

```java
String s1 = "abc";
String s2 = "abc";
String s3 = new String("abc");
```

直接写出的字符串字面量会放进“字符串常量池”。相同内容只保留一份，`s1` 和 `s2` 指向池中的同一个对象。`new String("abc")` 则明确要求创建一个新对象：

```text
s1 ──┐
     ├──> [ "abc" ]   （常量池）
s2 ──┘

s3 ──────> [ "abc" ]   （new 出来的新对象）
```

因此：

- `s1 == s2` 是 `true`，同一个对象。
- `s1 == s3` 是 `false`，不同对象。
- `s1.equals(s3)` 是 `true`，`String` 的 `equals` 比较字符内容。

实际写程序时，比较字符串内容一律用 `equals`。不要依赖 `==` 的结果，因为字符串可能来自输入、拼接或 `new`，是否共用常量池并不由你控制。

## 四、Object 给了每个类什么

Java 中所有类都自动继承 `Object`。即使这样写：

```java
public class RawStudent {
    private String name;
    private int score;
    public RawStudent(String name, int score) { ... }
}
```

`RawStudent` 也拥有 `Object` 提供的 `toString()`、`equals()`、`hashCode()`。

默认的 `equals` 等价于 `==`：

```java
// Object 默认行为（示意）
public boolean equals(Object obj) {
    return this == obj;
}
```

所以 `r1.equals(r2)` 为 `false`，即使两个 `RawStudent` 的姓名和成绩完全一样。

默认的 `hashCode` 根据对象身份生成一个整数，不同对象通常不同。默认的 `toString` 返回“类名@哈希码十六进制”，这就是 `RawStudent@6f2b958e` 的来源。

## 五、逐组看 EqualsDemo 的输出

### 第 1 组：基本类型

```java
int x = 5;
int y = 5;
System.out.println("x == y : " + (x == y));   // true
```

括号不能省。`"x == y : " + x == y` 会先做字符串拼接，再拿字符串和 `y` 比较，既不符合意图，也可能编译失败。

### 第 2 组：String

| 表达式 | 结果 | 原因 |
| --- | --- | --- |
| `s1 == s2` | `true` | 常量池中同一个对象 |
| `s1 == s3` | `false` | `new` 创建了新对象 |
| `s1.equals(s3)` | `true` | 内容都是 `abc` |

### 第 3 组：重写了 equals 的 Student

| 表达式 | 结果 | 原因 |
| --- | --- | --- |
| `a == b` | `false` | 两个不同对象 |
| `a.equals(b)` | `true` | 姓名和成绩都相同 |
| `a == c` | `true` | `c = a`，同一个对象 |

### 第 4 组：没有重写 equals 的 RawStudent

| 表达式 | 结果 | 原因 |
| --- | --- | --- |
| `r1 == r2` | `false` | 两个不同对象 |
| `r1.equals(r2)` | `false` | 默认 `equals` 等同 `==` |

把第 3 组和第 4 组并排看：数据完全相同，结果却不同。差别不在数据，而在类有没有重写 `equals`。

## 六、逐行拆 Student.equals

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    Student student = (Student) obj;
    return score == student.score &&
            Objects.equals(this.name, student.name);
}
```

### 第 1 步：自己和自己比

```java
if (this == obj) {
    return true;
}
```

`this` 是“当前调用这个方法的对象”。如果传进来的就是它自己，内容必然相同，直接返回 `true`，省掉后面的比较。

### 第 2 步：排除 null 和不同类

```java
if (obj == null || getClass() != obj.getClass()) {
    return false;
}
```

- `obj == null`：对方什么都没有，肯定不相等。
- `getClass() != obj.getClass()`：两个对象不属于同一个类，比较没有意义。例如 `Student` 和 `RawStudent` 即使字段同名，也不能算相等。
- `||` 是短路或：左边成立时右边不执行。所以 `obj` 为 `null` 时不会执行 `obj.getClass()`，不会崩溃。顺序反过来就会出问题。

### 第 3 步：强制类型转换

```java
Student student = (Student) obj;
```

经过上一步，已经确认 `obj` 是 `Student`，但编译器只知道它是 `Object`。`(Student) obj` 告诉编译器“请把它当作 `Student` 用”，这样才能访问 `score` 和 `name`。这个转换是安全的，因为类型已经检查过。

### 第 4 步：比较字段

```java
return score == student.score &&
        Objects.equals(this.name, student.name);
```

- `score` 是 `int`，用 `==` 比较数值。
- `name` 是 `String`，必须用 `equals` 比较内容。`this.name.equals(student.name)` 在 `this.name` 为 `null` 时会崩溃；`Objects.equals(a, b)` 会先处理 `null`，两边都为 `null` 时返回 `true`，只有一个为 `null` 时返回 `false`。
- `&&` 要求两个条件都成立。

## 七、为什么必须同时重写 hashCode

`hashCode()` 返回一个整数，可以理解为对象的“数字指纹”。哈希类集合用它先决定对象放在哪个“桶”，再用 `equals` 在桶里精确查找。

Java 的规定是：

> 如果 `a.equals(b)` 为 `true`，那么 `a.hashCode()` 必须等于 `b.hashCode()`。

`Student` 用姓名和成绩定义相等，所以哈希码也必须由姓名和成绩计算：

```java
@Override
public int hashCode() {
    return Objects.hash(name, score);
}
```

`Objects.hash(...)` 会根据传入的值算出一个整数。两个内容相同的 `Student` 会得到相同的哈希码，满足规定。

如果只重写 `equals`，不重写 `hashCode`，两个“相等”的对象会得到不同的默认哈希码。把它们放进 `HashSet` 时，集合会认为它们在不同的桶里，可能同时保存两份；用 `HashMap` 以它们为键时，也可能查不到刚存进去的值。这个练习还没有使用集合，但规则现在就要建立。

反过来，`hashCode` 相同不代表 `equals` 一定为 `true`。不同内容可能算出同一个哈希码，这叫哈希冲突；集合会再用 `equals` 确认。

## 八、常见错误与排查

| 错误写法 | 后果 | 修正 |
| --- | --- | --- |
| `if (name == other.name)` | 比较引用，可能误判 | 用 `Objects.equals` |
| `public boolean equals(Student other)` | 这是重载，不是重写 | 参数写成 `Object` |
| 先 `obj.getClass()` 再判 `null` | `null` 时抛 NPE | 先判 `null`，利用 `||` 短路 |
| 只重写 `equals` | 哈希集合行为错误 | 同时重写 `hashCode` |
| `hashCode` 用了 `id`，`equals` 只比姓名 | 违反一致性约定 | 两边使用同一组字段 |
| `@Override` 下方法名拼错 | 编译报错（好事） | 按提示修正 |

排查时先问三个问题：这个类有没有重写 `equals`？重写的参数是不是 `Object`？`equals` 和 `hashCode` 用的是不是同一组字段？

## 九、动手练习

1. 运行 `EqualsDemo`，把 9 行输出抄下来，在每行后面写一句原因。
2. 把 `Student.equals` 中的 `score == student.score` 改成 `true`，预测 `a.equals(b)` 是否变化，再运行验证。
3. 增加 `System.out.println(a.equals(null));` 和 `System.out.println(a.equals("abc"));`，确认都输出 `false` 且不崩溃。
4. 给 `Student` 增加 `int id`，规定“学号相同即相等”，同步修改 `equals` 和 `hashCode`，并设计两组测试：学号相同但成绩不同、学号不同但其他相同。
5. 写一个 `Point` 类，包含 `x`、`y`，正确重写 `toString`、`equals`、`hashCode`。

## 十、怎么判断自己真的懂了

合上源码，回答下面五个问题；能不看资料说清楚，才算掌握：

1. `a == b` 和 `a.equals(b)` 分别在问什么？
2. 为什么 `s1 == s2` 是 `true`，而 `s1 == s3` 是 `false`？
3. `Student` 和 `RawStudent` 数据相同，为什么 `equals` 结果不同？
4. 重写 `equals` 时，四步判断的顺序能不能换？为什么？
5. 只重写 `equals` 不重写 `hashCode`，会在什么场景出问题？

如果第 4、5 题说不清，回到第六、七节重读，并用手画一遍引用图和哈希桶示意，不要只背代码模板。