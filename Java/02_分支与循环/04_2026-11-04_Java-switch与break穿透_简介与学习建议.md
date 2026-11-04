# Java switch 与 break 穿透：简介与学习建议

## 文件基本信息

- 源码：`SwitchBreakDemo.java`
- 所属分类：`Java/02_分支与循环`
- 首次整理日期：2026-11-04
- 程序用途：通过反例和正确写法理解 `switch` 的匹配、穿透和 `break`
- 核心知识点：`switch`、`case`、`default`、`break`、穿透、复合条件
- 前置知识：变量、布尔判断、`if`、输出语句
- 相关练习：`GradeLevel.java`、`OperatorsDemo.java`

源码与上传附件保持字节一致，没有为了消除警告而改写反例。

## 程序功能

程序包含四段：

1. 星期 `day = 2`，故意不在各分支写 `break`，展示从“星期二”一直执行到后面的所有分支。
2. 等级 `level = 'B'`，故意让 `B` 穿透到 `C`，展示“良好”和“及格”连续输出。
3. 整数 `x = 9` 匹配 `default`，但没有立即结束，继续执行后面的 `case 1`，直到遇到 `break`。
4. 最后给出每个 `case` 都使用 `break` 的正确版本，匹配 `2` 时只输出“星期二”。

实际运行的关键输出：

```text
【反例1】day = 2，（缺少break语句）期望只输出：星期二
星期二
星期三
星期四
星期五
星期六
星期日
无效的日期
【反例2】level = B，（break语句缺少）期望只输出：良好
良好
及格
【反例3】x = 9，（break语句缺少）期望只输出：default
default：x 不是1，也不是2
case 1
【正确写法】day = 2，每个case末尾都加上break
星期二
```

## 运行方式

在仓库根目录执行：

```bash
java_dir=$(mktemp -d /tmp/java-switch.XXXXXX)
javac -encoding UTF-8 -Xlint:all -d "$java_dir" \
  "Java/02_分支与循环/SwitchBreakDemo.java"
java -cp "$java_dir" SwitchBreakDemo
```

程序没有输入和第三方依赖。

## 关于预期警告

`-Xlint:all` 会为三组反例报告 11 个 `[fallthrough] possible fall-through into case` 警告。这正是程序要展示的现象，因此编译时应保留这些提示。

如果使用 `-Werror`，这些有意保留的警告会被当成错误，编译会失败：

```text
error: warnings found and -Werror specified
1 error
11 warnings
```

这不是源码无法运行，也不是应该通过修改原文件来掩盖的问题。普通编译 `-Xlint:all` 得到 11 条警告后，程序可以正常运行。以后写正式 `switch` 时，应补齐 `break`，让严格检查通过。

## 学习重点

### switch 先找入口

程序先计算 `switch` 括号里的值，再寻找第一个匹配的 `case`。找到入口后，不会自动在下一个 `case` 前停下，而是按文本顺序继续执行，直到遇到 `break`、方法结束或 `switch` 结束。

### break 是出口

`break` 的作用是立即结束当前 `switch`。如果没有它，后续分支的语句会继续执行，这叫穿透。

### default 也是普通位置

`default` 表示“其他值都未匹配时从这里开始”，它不是“只能执行这一块”。如果 `default` 后面还有语句且没有 `break`，仍会继续执行。源码第三段正是这个现象。

### 有意的穿透

多个相邻 `case` 可以共用同一段代码：

```java
case 10:
case 9:
    level = "A";
    break;
```

这里 `case 10` 没有语句，直接落到 `case 9`，是受控且清晰的复用方式。`GradeLevel.java` 会用到这种写法。

## 学习难点

1. 把 `case` 误认为独立代码块，以为执行完一个分支就会自动结束。
2. 把 `default` 误认为必须有 `break`，或者误认为放在前面就不可能穿透。
3. 为了消除警告而直接删掉反例，失去观察穿透的机会。
4. 没分清“有意复用相邻 case”与“忘记 break 的意外穿透”。

## 检查结果

- 使用 OpenJDK 21.0.13。
- `javac -encoding UTF-8 -Xlint:all` 编译成功，并报告 11 条预期 `[fallthrough]` 警告。
- 加上 `-Werror` 后按预期失败，因为源码故意保留穿透。
- 程序已实际运行，四段输出与源码注释中的教学目的一致。
- 程序没有输入，空输入和非法输入测试不适用。

## 建议学习流程

1. 先遮住输出，只看第一段 `day = 2`，预测会打印哪些星期。
2. 运行核对后，用箭头画出“找到 case 2 → 执行 case 2 → 继续向下”的路径。
3. 给第二段和第三段分别画执行路径，特别标出 `break` 在哪里截断。
4. 再运行最后一个正确版本，对比为什么只输出一次。
5. 把 `x` 改成 `1`、`2` 和其他值，先预测再运行。
6. 自己写一个 `switch`，只用相邻 `case` 复用一次代码，其余分支都写 `break`。

## 测试用例

| 场景 | 固定值 | 预期或已验证结果 | 状态 |
| --- | --- | --- | --- |
| 星期反例 | `day = 2` | 从星期二连续输出到默认分支 | 已实际运行验证 |
| 等级反例 | `level = 'B'` | 输出良好、及格 | 已实际运行验证 |
| default 在中间 | `x = 9` | 输出 default，再穿透到 case 1 | 已实际运行验证 |
| 正确版本 | `day = 2` | 只输出星期二 | 已实际运行验证 |
| 严格警告检查 | `-Xlint:all -Werror` | 因 11 个预期穿透警告而失败 | 已实际验证 |

## 拓展练习

1. 复制一份自己的 `SwitchPractice.java`，为每个错误例子补 `break`，比较输出。
2. 写一个月份天数程序，把 `4、6、9、11` 作为相邻 `case` 共用 30 天分支。
3. 写一个字符等级程序，让 `A`、`B` 分别输出不同内容且不互相穿透。
4. 在 `default` 后故意省略 `break`，观察它是否继续执行后面的代码，再说明为什么。
5. 把 `switch` 改写成 `if / else if`，比较两种写法适合的条件类型。

完成本练习后，再学 `GradeLevel.java`，观察 `if / else if` 和 `switch (score / 10)` 如何表达同一套成绩边界。
