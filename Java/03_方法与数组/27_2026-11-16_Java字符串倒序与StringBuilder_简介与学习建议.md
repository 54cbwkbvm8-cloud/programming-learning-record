# Java 字符串倒序与 StringBuilder 简介

## 文件基本信息

- 源码：[P01_StringReverse.java](P01_StringReverse.java)
- 首次整理日期：2026-11-16
- 所属分类：`Java/03_方法与数组`
- 程序用途：读取一行文字，从后往前逐字输出，得到倒序结果。
- 主要问题：怎样用下标从字符串末尾走到开头；为什么用 `StringBuilder` 而不是反复拼接 `String`。
- 核心知识点：`Scanner` 读取整行、字符串下标、`charAt`、`length()`、`for` 循环、`StringBuilder.append`、空输入处理。
- 前置知识：`main` 方法、`Scanner`、`for` 循环、字符串与字符的区别。
- 相关练习：先完成 [循环基础](../02_分支与循环/07_2026-11-05_Java循环基础与break-continue_简介与学习建议.md)；下一题是 [回文判断](28_2026-11-16_Java回文判断与双指针_简介与学习建议.md)。

> 源码保留了上传时的类名 `P01_StringReverse`，`P01` 只是原附件的题号。仓库的 Java 练习序号和日期记录在本文档名与导航中，没有为了套用 C/Python 格式去修改 `public class` 的类名。

## 程序功能说明

程序先创建 `StringBuilder`，然后让下标 `i` 从 `s.length() - 1` 开始，每次减一，直到 `i >= 0` 不再成立。循环体把 `s.charAt(i)` 追加到 `StringBuilder` 末尾，最后用 `toString()` 转回普通字符串并打印。

输入 `hello` 时，下标依次是 `4、3、2、1、0`，取出的字符依次是 `o、l、l、e、h`，所以输出 `olleh`。空格和汉字也会作为一个字符参与倒序，例如 `a b c` 输出 `c b a`，`你好abc` 输出 `cba好你`。

程序没有打印“请输入”之类的提示语。这是评测题写法：评测系统会把输出与标准答案逐字比较，多打印一个字都会判错。

```java
for (int i = s.length() - 1; i >= 0; i--) {
    sb.append(s.charAt(i));
}
```

## 运行方式

在仓库根目录编译。编译结果放进临时目录，避免 `.class` 文件留在仓库：

```bash
java_dir=$(mktemp -d /tmp/java-reverse.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/03_方法与数组/P01_StringReverse.java"
```

运行并送入测试数据：

```bash
printf 'hello\n' | java -cp "$java_dir" P01_StringReverse
```

真实输出：

```text
olleh
```

## 学习重点

- `length()` 返回字符个数，最后一个字符的下标是 `length - 1`，不是 `length`。
- 循环条件是 `i >= 0`，因为下标 0 对应的第一个字符也要取到；写成 `i > 0` 会漏掉首字符。
- `String` 创建后不能修改。`result = result + ch` 每次都会新建字符串并复制旧内容；`StringBuilder` 在原地追加，适合多次拼接。
- `sc.hasNextLine()` 先判断有没有下一行，再调用 `nextLine()`，可以避免“完全没有输入”时抛出 `NoSuchElementException`。
- `nextLine()` 读取整行，保留中间空格；`next()` 遇到空格就停止，不能用于本题。

## 学习难点

### 下标与长度的关系

以 `abc` 为例，字符是 `a`、`b`、`c`，下标是 `0`、`1`、`2`。长度是 3，最后下标是 2。循环从 2 开始，到 0 结束，一共执行 3 次。空字符串的长度是 0，`length - 1` 是 -1，循环条件一开始就不成立，循环执行 0 次，输出一个空行。

### 为什么空输入不会崩溃

`String s = "";` 先给了确定值。即使 `hasNextLine()` 返回 `false`，`s` 仍是空字符串，后面的 `s.length() - 1` 得到 -1，循环不执行，程序正常结束。如果没有这层保护，直接调用 `nextLine()` 在无输入时会抛出异常。

### 特殊字符的局限

`charAt` 按 Java 的 16 位 `char` 单元取字符。常见汉字在基本多文种平面内，可以正常倒序；emoji 等补充平面字符占两个 `char` 单元，逐个倒取会被拆开，出现乱码。若题目要求正确处理这类字符，可以改用 `new StringBuilder(s).reverse()`，它按码点处理。本练习的目标是手写下标循环，因此保留当前写法。

## 建议学习流程

1. 在纸上写出 `hello` 的 5 个下标和对应字符。
2. 按循环顺序写出每次 `i`、取出的字符、`sb` 的当前内容。
3. 运行 `hello`，对照纸上的结果。
4. 把输入改成 `a b c`，先预测空格的位置，再运行。
5. 把循环条件故意改成 `i > 0`，观察首字符丢失，再改回。
6. 关闭源码，自己写一个只用一个 `String` 变量完成倒序的版本，并与 `StringBuilder` 版本比较效率。

## 易错点与测试用例

| 输入 | 真实输出 | 检查目标 |
| --- | --- | --- |
| `hello` | `olleh` | 普通英文 |
| `a b c` | `c b a` | 空格参与倒序 |
| `你好abc` | `cba好你` | 汉字与英文混合 |
| 空行 | 一个空行 | 空字符串不崩溃 |
| 完全没有输入 | 一个空行 | `hasNextLine()` 保护 |
| `😀ab` | 可能出现乱码 | `charAt` 的代理对局限 |

以上前五行已于 2026-11-16 实际运行验证；emoji 行为是按 `charAt` 的代理对规则推导的预期结果，未单独运行。

常见错误包括：循环从 `length` 开始导致越界；条件写成 `i > 0` 漏掉首字符；用 `next()` 读取导致空格后的内容丢失；在循环里反复 `result = result + ch` 导致长文本变慢；忘记关闭 `Scanner`（本题已关闭）。

## 拓展练习

1. 用 `new StringBuilder(s).reverse()` 重写，比较两种写法的可读性和特殊字符处理差异。
2. 不创建新字符串，改用 `char[]` 和双指针在原数组内交换，输出倒序结果。
3. 增加一个方法 `static String reverse(String s)`，把倒序逻辑从 `main` 中拆出去。
4. 统计倒序后每个字符出现的次数，与 [字母统计](29_2026-11-16_Java字母统计与数组计数_简介与学习建议.md) 组合成一个小程序。
5. 思考：如果输入有多行，题目要求逐行倒序输出，程序应该怎样改？