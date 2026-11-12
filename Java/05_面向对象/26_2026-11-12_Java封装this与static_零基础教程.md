# Java 封装、this 与 static 零基础教程

本教程配合 [EncapsulationStaticDemo.java](EncapsulationStaticDemo.java) 使用。四个主题共用一条主线：对象的数据应该怎样被保护，类共享的状态应该放在哪里，对象引用进入方法后会发生什么。

## 一、先理解四个关键词

### public

公开成员可以在类外访问：

```java
openStu.score = -999;
```

`OpenStudent.score` 没有访问限制，所以能直接改。

### private

私有成员只能在当前类内部访问：

```java
private int score;
```

类外不能写 `s1.score`，编译会报错。

### static

静态成员属于类本身，所有对象共享一份。

### this

`this` 表示当前正在执行方法的对象。

## 二、封装是什么

封装包含两层意思：

1. 把内部数据隐藏起来。
2. 只通过受控方法读写。

```text
外部代码
  -> setScore(90)
  -> 方法检查 0~100
  -> 合法才修改 private score
```

封装不是不允许修改，而是不允许绕过规则修改。

## 三、没有封装会怎样

```java
class OpenStudent {
    String id;
    String name;
    int score;
}
```

外部可以：

```java
openStu.score = -999;
```

类完全没有机会检查。输出：

```text
没有封装时，成绩被改成：-999
```

这不是业务允许的“特殊分数”，而是数据失去约束。

## 四、SafeStudent 的字段

```java
private String id;
private String name;
private int score;
private static int count = 0;
```

前三项是实例字段，每个对象一份。

`count` 是静态字段，整个 `SafeStudent` 类共享一份。

关系：

```text
对象 s1: id, name, score
对象 s2: id, name, score
对象 s3: id, name, score

SafeStudent.count: 所有对象共用
```

## 五、getter 和 setter

### getter

```java
public int getScore() {
    return score;
}
```

只读访问。

### setter

```java
public boolean setScore(int score) {
    if (!isValidScore(score)) {
        return false;
    }
    this.score = score;
    return true;
}
```

返回布尔值后，调用者能知道修改是否成功：

```text
true  -> 已修改
false -> 拒绝修改，旧值保留
```

## 六、构造方法也必须校验

只保护 setter 不够。考虑下面调用：

```java
new SafeStudent("1", "A", 150);
```

如果构造方法直接：

```java
this.score = score;
```

对象一创建就拥有非法成绩，封装仍然被绕过。

当前源码在构造方法中先检查：

```java
if (!isValidScore(score)) {
    throw new IllegalArgumentException(...);
}
```

`IllegalArgumentException` 表示传入参数不合法。抛出后构造没有完成，后面的字段赋值和 `count++` 不执行。

## 七、this 遮罩实验

### 错误写法

```java
class NoThisStudent {
    String name;
    int score;

    NoThisStudent(String name, int score) {
        name = name;
        score = score;
    }
}
```

对象创建：

```java
new NoThisStudent("错误示范", 50)
```

每个赋值左右两边都是参数：

```text
参数 name = 参数 name
参数 score = 参数 score
```

字段从未改变，保持默认值：

```text
name = null
score = 0
```

### 正确写法

```java
this.name = name;
this.score = score;
```

```text
this.name  -> 当前对象字段
name       -> 构造参数
```

所以 `SafeStudent s2` 打印“李四”和 90。

## 八、普通字段计数为什么失败

```java
class CounterWrong {
    int count = 0;

    CounterWrong() {
        count++;
    }
}
```

创建三个对象：

```text
c1 -> [count=1]
c2 -> [count=1]
c3 -> [count=1]
```

每个对象创建时，先有自己的 `count=0`，然后自己的构造方法加 1。三个字段互不相干。

## 九、static 为什么能统计总数

```java
private static int count = 0;
```

静态字段只初始化一次，所有 `SafeStudent` 对象共享：

```text
new SafeStudent(s1) -> count 0 -> 1
new SafeStudent(s2) -> count 1 -> 2
new SafeStudent(s3) -> count 2 -> 3
```

所以：

```java
SafeStudent.getCount()
```

返回 3。

## 十、静态方法与实例方法

静态方法：

```java
public static int getCount()
```

通过类名：

```java
SafeStudent.getCount()
```

实例方法：

```java
public String getName()
```

必须通过对象：

```java
s1.getName()
```

静态方法没有隐式的“当前对象”，因此不能直接读取某个实例的 `id`、`name`、`score`。

## 十一、对象作为参数

### 修改共同对象

```java
changeScore(s1)
```

方法定义：

```java
static void changeScore(SafeStudent s) {
    s.setScore(60);
}
```

调用时：

```text
s1 ──> 对象
s  ──> 同一对象
```

`s` 是引用值的副本，但副本指向同一个对象。因此可以修改成绩。

### 重新赋值参数

```java
static void reassign(SafeStudent s) {
    s = new SafeStudent("99999999", "临时", 1);
}
```

方法内：

```text
s1 ──> 原对象
s  ──> 新临时对象
```

只有参数 `s` 改指向。方法结束后，`s1` 仍指向原对象，姓名仍为“张三”。

## 十二、static 计数为什么最后是 4

前面已经创建：

1. `s1`
2. `s2`
3. `s3`

`reassign` 中又创建了第四个：

```java
new SafeStudent("99999999", "临时", 1)
```

虽然方法结束后没有变量继续引用这个临时对象，后续可能被垃圾回收，但“创建动作”已经发生，`count++` 已经执行。因此最终显示 4。

`count` 统计的是创建次数，不是当前仍能访问的对象数量。

## 十三、完整输出对照

```text
setScore(150) -> false，当前成绩：85
setScore(-1)  -> false，当前成绩：85
setScore(101) -> false，当前成绩：85
setScore(0)   -> true，当前成绩：0
setScore(100) -> true，当前成绩：100
```

前三个拒绝；0 和 100 是合法边界。

```text
漏写 this：name=null，score=0
写了 this：name=李四，score=90
```

同名参数遮蔽问题。

```text
普通变量计数：c1=1，c2=1，c3=1
static 计数器：共创建了 3 个学生对象
```

实例字段独立，静态字段共享。

```text
调用前 s1 的成绩：100
调用 changeScore(s1) 后，s1 的成绩：60
方法内部 s 指向的姓名：临时
调用 reassign(s1) 后，s1 的姓名：张三
此时 static 计数器：4
```

共同对象可修改，参数重新赋值不影响调用者变量。

## 十四、常见错误

- 只给 setter 校验，构造方法却直接赋值。
- 把 getter 当成复制整个对象，实际上只是返回字段或引用。
- 认为 `private` 会让字段永久无法修改；类内方法仍可修改。
- 忘记 `this`，出现 `name = name`。
- 把静态字段写成实例字段，导致每个对象各有一个计数。
- 从静态方法直接访问实例字段。
- 以为 `reassign` 修改了外部对象变量。
- 把 `count` 当作“当前存活对象数”。

## 十五、自测与练习

能完成以下检查才算理解：

1. 画出 OpenStudent 和 SafeStudent 的字段访问边界。
2. 说明 150、-1、101、0、100 五个 setter 结果。
3. 解释构造函数校验为什么不能省略。
4. 说明 NoThisStudent 的字段为什么仍是默认值。
5. 比较 CounterWrong 与 SafeStudent.count。
6. 画出 changeScore 和 reassign 两张引用图。
7. 解释最终 count=4 中第四个对象来自哪里。

建议练习：

1. 把 SafeStudent 拆成独立 `SafeStudent.java`。
2. 给 id/name 增加受控 setter。
3. 增加 `try-catch` 演示非法构造参数。
4. 设计私有余额的 `BankAccount`，覆盖存款、取款和非法金额。
5. 增加 `getCreatedCount` 和 `getLiveCount`，思考创建数、回收数和存活数的区别。
