# Java switch 与 break 穿透：零基础教程

这份教程配合 `SwitchBreakDemo.java`。目标不是背 `switch` 的模板，而是脑中出现一条清楚的执行路线：先找入口，再一路向下，直到出口。

## 一、switch 像按标签进入一条通道

```java
switch (day) {
    case 1:
        System.out.println("星期一");
    case 2:
        System.out.println("星期二");
}
```

可以把每个 `case` 想成通道上的标签，`break` 想成出口。

1. 先看 `switch (day)` 的值。
2. 找到与该值相等的 `case`。
3. 从这个 `case` 开始向下执行。
4. 遇到 `break` 才离开整个 `switch`。
5. 如果一直没遇到 `break`，会执行到 `switch` 的末尾。

## 二、逐行跟踪 day = 2

源码第一段没有 `break`：

```java
case 1:
    System.out.println("星期一");
case 2:
    System.out.println("星期二");
case 3:
    System.out.println("星期三");
// 后面继续到 case 7 和 default
```

`day = 2` 时：

| 步骤 | 执行位置 | 发生什么 |
| ---: | --- | --- |
| 1 | `case 1` | 不匹配，跳过 |
| 2 | `case 2` | 匹配，输出星期二 |
| 3 | `case 3` | 没有 break，继续输出星期三 |
| 4 | `case 4` 到 `case 7` | 继续向下，全部输出 |
| 5 | `default` | 继续输出“无效的日期” |
| 6 | switch 结束 | 离开分支 |

所以“匹配 case 2”不等于“只执行 case 2”。入口是 case 2，出口却在 switch 末尾。

## 三、break 怎样截断通道

正确版本：

```java
case 2:
    System.out.println("星期二");
    break;
```

执行到 `break` 后，当前 `switch` 立即结束，不会再执行 `case 3`。因此 `day = 2` 只输出“星期二”。

`break` 后面的语句属于当前 `switch`，不影响整个方法的其他部分。

## 四、第二个例子：B 穿透到 C

源码中：

```java
case 'B':
    System.out.println("良好");
case 'C':
    System.out.println("及格");
    break;
```

`level = 'B'` 从 `case 'B'` 进入，输出“良好”。由于 B 分支没有 `break`，继续执行 C 分支，再输出“及格”，最后在 C 分支的 `break` 离开。

这个结果不是“B 同时等于 C”，而是从 B 的入口一直走到了下一个出口。

## 五、default 不是单独的密封房间

源码第三段把 `default` 放在前面：

```java
switch (x) {
    default:
        System.out.println("default：x 不是1，也不是2");
    case 1:
        System.out.println("case 1");
        break;
    // 后续 case
}
```

当 `x = 9`，没有 case 1、2、3 匹配，于是从 `default` 进入。因为 default 没有 `break`，接着执行下面的 `case 1`，输出“case 1”，然后遇到 break。

可以记成：**default 只决定从哪里开始，不决定在哪里结束。**

## 六、什么时候可以故意不写 break

相邻 case 共用代码是常见写法：

```java
case 10:
case 9:
    System.out.println("A");
    break;
```

`10` 没有自己的语句，匹配后会自然落到 `9` 的代码。这个穿透很明显、目的清楚，一般可以接受。

危险的写法是：每个 case 本来都应独立输出，却忘记 break。编译器通常无法知道你的真实意图，所以只能用 `[fallthrough]` 警告提醒。

## 七、switch 能接收什么值

初学阶段常见的是：

- `int` 和其他可安全转换到 `int` 的整数类型。
- `char`。
- `String`。
- `enum`。

`long`、`double` 等不能直接用于普通 `switch`。条件范围是“等于某个固定值”，不是“大于多少”或“在区间内”。范围判断通常更适合 `if / else if`。

## 八、执行路径练习

先写答案再运行：

### 练习 1

```java
int x = 2;
switch (x) {
    case 1:
        System.out.println("A");
    case 2:
        System.out.println("B");
    case 3:
        System.out.println("C");
        break;
    default:
        System.out.println("D");
}
```

输出是 `B`、`C`，因为从 case 2 进入，case 3 的 break 才结束。

### 练习 2

```java
int x = 9;
switch (x) {
    default:
        System.out.println("D");
    case 1:
        System.out.println("A");
        break;
    case 2:
        System.out.println("B");
}
```

输出是 `D`、`A`。如果不匹配任何 case，就从 default 开始，再向下找 break。

## 九、常见错误

1. 以为执行完匹配的 case 会自动结束。
2. 把 `break` 写到花括号外面。
3. 在需要独立分支的地方漏写 `break`。
4. 以为 default 必须放最后。
5. 拿 `switch` 处理“分数大于 60 且小于 90”这样的范围条件；这种题要先转换表达式或改用 `if`。
6. 为了消除警告直接把所有 `case` 都改成空白或删除反例，没有理解穿透条件。

## 十、掌握标准

如果能够做到以下三点，说明已经理解：

1. 给定任意 `switch` 和输入，能写出从哪个 case 进入。
2. 能沿着文本顺序指出每条输出和最终 break。
3. 能解释相邻 case 的有意复用为什么与忘记 break 不同。

下一步学习 `GradeLevel.java` 时，重点看它如何用 `score / 10` 把分数范围变成 `10、9、8、7、6` 等整数入口。
