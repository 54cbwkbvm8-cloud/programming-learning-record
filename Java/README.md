# Java 学习区

这里保存 Java 课程练习和学习说明。Java 源码必须遵守类名与文件名规则，因此不能直接照搬 C/Python 的 `NN_日期_主题.java` 命名方式；首次整理顺序和日期记录在配套文档中。系统的知识点、难点和掌握标准见 [Java 学习指南](../docs/Java学习指南.md)。

## 目录一览

| 目录 | 源码数 | 学习重点 |
| --- | --- | --- |
| [01_基础语法](01_基础语法/) | 3 | 类、`main`、基本类型、类型转换、运算符、短路求值和三元表达式 |
| [02_分支与循环](02_分支与循环/) | 10 | 输入校验、条件分支、`switch`、三种循环、嵌套循环、统计、极值和持续交互 |
| [03_方法与数组](03_方法与数组/) | 15 | 方法定义与重载、值传递与数组引用、数组遍历、字符串倒序、回文双指针、字母计数、GCD、素数/约数/数字拆位/第二大值/数对统计、冒泡排序、线性查找和二分查找 |
| [04_综合项目](04_综合项目/) | 4 | 菜单循环、多方法分工、输入校验、查找、统计和排序；过程式、面向对象重构、继承多态与集合异常四种实现 |
| [05_面向对象](05_面向对象/) | 3 | 类、对象、构造方法、引用、封装、私有字段、getter/setter、`this`、`static` 和对象传参 |
| [06_Object方法与对象数组](06_Object方法与对象数组/) | 6 | `Object` 默认方法、`toString`、`equals`、`hashCode`、空指针异常、对象数组和对象录入 |
| [07_数据结构](07_数据结构/) | 2 | 数组实现栈与括号匹配；数组实现循环队列、`head`/`tail` 取模回绕、`size` 判空满与空队异常 |
| [08_继承与多态](08_继承与多态/) | 9 | 继承、父类构造、方法重写、多态数组、向上/向下转型、`instanceof`、抽象类、接口、`default` 方法和多接口实现 |
| [09_算法练习](09_算法练习/) | 8 | 评测风格算法题（二分、冒泡、插入排序、第 k 大、去重、频次）与字符串算法专题（单词倒序、异位词、最长连续段、文本分析与命令行参数） |
| [10_常用类与异常](10_常用类与异常/) | 4 | String 核心 API、StringBuilder 与包装类缓存、Math/Arrays/LocalDate 工具类、异常处理与 `finally` 顺序、`-DcrashMode` 崩溃演示 |

当前只创建了有真实源码的目录。`03_方法与数组` 提供方法语义和入门算法，`09_算法练习` 用评测题与字符串算法专题独立练习查找、排序、序统计、去重、计数和字符串处理，`04_综合项目` 组合菜单与数组（24 号过程式、36 号面向对象重构、48 号继承多态的校园人员管理、56 号集合与异常版），`05_面向对象` 第一次正式引入类对象、构造方法、引用别名、封装和静态成员，`06_Object方法与对象数组` 继续进入 `Object` 通用方法、内容比较和对象数组，`07_数据结构` 用类封装栈与循环队列两种线性容器，`08_继承与多态` 用五组独立练习进入继承、重写、多态、抽象类和接口，`10_常用类与异常` 集中学习 JDK 常用类与异常机制。集合仍只在 56 号项目内出现，暂不建立独立目录；正式自动测试和工程构建仍没有足够资源。

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
| 27 | [`P01_StringReverse.java`](03_方法与数组/P01_StringReverse.java) | [`27_2026-11-16_Java字符串倒序与StringBuilder_简介与学习建议.md`](03_方法与数组/27_2026-11-16_Java字符串倒序与StringBuilder_简介与学习建议.md) | 用下标倒序遍历和 `StringBuilder` 完成字符串倒序 |
| 28 | [`P02_Palindrome.java`](03_方法与数组/P02_Palindrome.java) | [`28_2026-11-16_Java回文判断与双指针_简介与学习建议.md`](03_方法与数组/28_2026-11-16_Java回文判断与双指针_简介与学习建议.md) | 用双指针逐对比较判断回文，处理空串和提前结束 |
| 29 | [`P03_CountLetters.java`](03_方法与数组/P03_CountLetters.java) | [`29_2026-11-16_Java字母统计与数组计数_简介与学习建议.md`](03_方法与数组/29_2026-11-16_Java字母统计与数组计数_简介与学习建议.md) | 用 26 格计数数组和 `ch - 'a'` 映射统计字母 |
| 30 | [`P04_GCD.java`](03_方法与数组/P04_GCD.java) | [`30_2026-11-16_Java最大公约数与辗转相除_简介与学习建议.md`](03_方法与数组/30_2026-11-16_Java最大公约数与辗转相除_简介与学习建议.md) | 用辗转相除法求 GCD，处理负数、0 和 `long` 输入 |
| 31 | [`P05_IsPrime.java`](03_方法与数组/P05_IsPrime.java) | [`31_2026-11-16_Java质数判定与long试除边界_简介与学习建议.md`](03_方法与数组/31_2026-11-16_Java质数判定与long试除边界_简介与学习建议.md) | 用 `i <= n / i` 避免溢出，判断 `long` 范围的质数 |
| 42 | [`P1_BinarySearch.java`](09_算法练习/P1_BinarySearch.java) | [`42_2026-11-21_Java二分查找评测版_简介与学习建议.md`](09_算法练习/42_2026-11-21_Java二分查找评测版_简介与学习建议.md) | 在评测格式下独立实现二分查找，对照 14 号教学版 |
| 43 | [`P2_BubbleSort.java`](09_算法练习/P2_BubbleSort.java) | [`43_2026-11-21_Java冒泡排序评测版_简介与学习建议.md`](09_算法练习/43_2026-11-21_Java冒泡排序评测版_简介与学习建议.md) | 评测格式冒泡排序与行末空格控制，对照 14 号 `swapped` 优化版 |
| 44 | [`P3_KthLargest.java`](09_算法练习/P3_KthLargest.java) | [`44_2026-11-21_Java第k大与降序插入排序_简介与学习建议.md`](09_算法练习/44_2026-11-21_Java第k大与降序插入排序_简介与学习建议.md) | 首次练习降序插入排序，理解重复占位的第 k 大与短路与防越界 |
| 45 | [`P4_DistinctCount.java`](09_算法练习/P4_DistinctCount.java) | [`45_2026-11-21_Java去重计数与排序相邻比较_简介与学习建议.md`](09_算法练习/45_2026-11-21_Java去重计数与排序相邻比较_简介与学习建议.md) | 用 `Arrays.sort` 和相邻比较统计不同值的个数 |
| 46 | [`P5_Frequency.java`](09_算法练习/P5_Frequency.java)、[`P5_FrequencyFixed.java`](09_算法练习/P5_FrequencyFixed.java) | [`46_2026-11-21_Java频次统计与计数数组_简介与学习建议.md`](09_算法练习/46_2026-11-21_Java频次统计与计数数组_简介与学习建议.md) | 计数数组频次统计；原始版含两处语法错误，练习读报错定位并对照修正版 |
| 53 | [`StringAlgorithms.java`](09_算法练习/StringAlgorithms.java) | [`53_2026-11-27_Java字符串算法四题_简介与学习建议.md`](09_算法练习/53_2026-11-27_Java字符串算法四题_简介与学习建议.md) | 单词倒序、异位词计数抵消、最长连续段和空白规范化 |
| 54 | [`TextAnalyzer.java`](09_算法练习/TextAnalyzer.java) | [`54_2026-11-27_Java文本分析与命令行参数_简介与学习建议.md`](09_算法练习/54_2026-11-27_Java文本分析与命令行参数_简介与学习建议.md) | 正则分词、26 字母频次、最长词、回文词与命令行参数校验 |
| 24 | [`GradeManager.java`](04_综合项目/GradeManager.java) | [`24_2026-11-09_Java成绩管理系统_简介与学习建议.md`](04_综合项目/24_2026-11-09_Java成绩管理系统_简介与学习建议.md) | 用菜单组合录入、查询、统计、排序和多层输入校验 |
| 24A | [`GradeManager.java`](04_综合项目/GradeManager.java) | [`24_2026-11-09_Java成绩管理系统_零基础教程.md`](04_综合项目/24_2026-11-09_Java成绩管理系统_零基础教程.md) | 用数据模型、方法调用链和排序轨迹读懂完整项目 |
| 25 | [`Student.java`](05_面向对象/Student.java)、[`StudentTest.java`](05_面向对象/StudentTest.java) | [`25_2026-11-12_Java类对象构造方法与引用_简介与学习建议.md`](05_面向对象/25_2026-11-12_Java类对象构造方法与引用_简介与学习建议.md) | 定义类、创建对象、比较构造方法并理解引用别名 |
| 25A | 同上 | [`25_2026-11-12_Java类对象构造方法与引用_零基础教程.md`](05_面向对象/25_2026-11-12_Java类对象构造方法与引用_零基础教程.md) | 用内存关系理解 `new`、`this` 和对象比较 |
| 26 | [`EncapsulationStaticDemo.java`](05_面向对象/EncapsulationStaticDemo.java) | [`26_2026-11-12_Java封装this与static_简介与学习建议.md`](05_面向对象/26_2026-11-12_Java封装this与static_简介与学习建议.md) | 对比无封装与私有字段、构造校验、静态计数和对象传参 |
| 26A | [`EncapsulationStaticDemo.java`](05_面向对象/EncapsulationStaticDemo.java) | [`26_2026-11-12_Java封装this与static_零基础教程.md`](05_面向对象/26_2026-11-12_Java封装this与static_零基础教程.md) | 逐步理解 getter/setter、`this`、`static` 和引用副本 |
| 32 | [`Student.java`](06_Object方法与对象数组/Student.java)、[`RawStudent.java`](06_Object方法与对象数组/RawStudent.java)、[`ToStringPredictDemo.java`](06_Object方法与对象数组/ToStringPredictDemo.java) | [`32_2026-11-16_Java-toString与对象打印_简介与学习建议.md`](06_Object方法与对象数组/32_2026-11-16_Java-toString与对象打印_简介与学习建议.md) | 对比默认与重写的 `toString`，理解 `println` 的自动调用 |
| 33 | [`Student.java`](06_Object方法与对象数组/Student.java)、[`RawStudent.java`](06_Object方法与对象数组/RawStudent.java)、[`EqualsDemo.java`](06_Object方法与对象数组/EqualsDemo.java) | [`33_2026-11-16_Java-equals与hashCode重写_简介与学习建议.md`](06_Object方法与对象数组/33_2026-11-16_Java-equals与hashCode重写_简介与学习建议.md) | 区分 `==` 与 `equals`，按字段重写 `equals` 和 `hashCode` |
| 33A | 同上 | [`33_2026-11-16_Java-equals与hashCode重写_零基础教程.md`](06_Object方法与对象数组/33_2026-11-16_Java-equals与hashCode重写_零基础教程.md) | 用引用图、字符串常量池和判断顺序逐步理解对象相等 |
| 34 | [`NullPointerDemo.java`](06_Object方法与对象数组/NullPointerDemo.java)、[`Student.java`](06_Object方法与对象数组/Student.java) | [`34_2026-11-16_Java空指针与对象数组判空_简介与学习建议.md`](06_Object方法与对象数组/34_2026-11-16_Java空指针与对象数组判空_简介与学习建议.md) | 观察对象数组默认 `null`、NPE 和判空保护 |
| 35 | [`StudentArrayDemo.java`](06_Object方法与对象数组/StudentArrayDemo.java)、[`Student.java`](06_Object方法与对象数组/Student.java) | [`35_2026-11-16_Java对象数组录入与平均分_简介与学习建议.md`](06_Object方法与对象数组/35_2026-11-16_Java对象数组录入与平均分_简介与学习建议.md) | 录入对象数组、校验整数成绩并计算平均分 |
| 36 | [`StudentManagementSystem.java`](04_综合项目/StudentManagementSystem.java) | [`36_2026-11-19_Java成绩管理系统面向对象重构_简介与学习建议.md`](04_综合项目/36_2026-11-19_Java成绩管理系统面向对象重构_简介与学习建议.md) | 把 24 号的平行数组项目重构成 `Student`、`StudentManager`、菜单类三个类 |
| 36A | [`StudentManagementSystem.java`](04_综合项目/StudentManagementSystem.java) | [`36_2026-11-19_Java成绩管理系统面向对象重构_零基础教程.md`](04_综合项目/36_2026-11-19_Java成绩管理系统面向对象重构_零基础教程.md) | 用对象数组、管理器和输入重试逐步理解面向对象重构 |
| 37 | [`StackDemo.java`](07_数据结构/StackDemo.java) | [`37_2026-11-19_Java数组实现栈与括号匹配_简介与学习建议.md`](07_数据结构/37_2026-11-19_Java数组实现栈与括号匹配_简介与学习建议.md) | 用数组和 `top` 实现栈，并用栈解决括号匹配（力扣 20） |
| 37A | [`StackDemo.java`](07_数据结构/StackDemo.java) | [`37_2026-11-19_Java数组实现栈与括号匹配_零基础教程.md`](07_数据结构/37_2026-11-19_Java数组实现栈与括号匹配_零基础教程.md) | 用 `top` 变化表、异常流程和括号案例逐步理解栈 |
| 55 | [`MyQueue.java`](07_数据结构/MyQueue.java) | [`55_2026-11-27_Java循环队列_简介与学习建议.md`](07_数据结构/55_2026-11-27_Java循环队列_简介与学习建议.md) | 数组实现循环队列：`head`/`tail` 取模回绕、`size` 判空满、满队返回 false |
| 55A | 同上 | [`55_2026-11-27_Java循环队列_零基础教程.md`](07_数据结构/55_2026-11-27_Java循环队列_零基础教程.md) | 用物理/逻辑顺序图理解循环队列与栈的对照 |
| 38 | [`InheritanceDemo.java`](08_继承与多态/01_继承与重写演示/InheritanceDemo.java) | [`38_2026-11-21_Java继承构造顺序与重写_简介与学习建议.md`](08_继承与多态/01_继承与重写演示/38_2026-11-21_Java继承构造顺序与重写_简介与学习建议.md) | 观察父类构造顺序、方法继承、重载/重写、`toString` 与 `Object` |
| 38A | 同上 | [`38_2026-11-21_Java继承构造顺序与重写_零基础教程.md`](08_继承与多态/01_继承与重写演示/38_2026-11-21_Java继承构造顺序与重写_零基础教程.md) | 用构造轨迹和继承图理解父类部分与子类部分 |
| 39 | [`PolymorphismDemo.java`](08_继承与多态/02_多态与类型转换/PolymorphismDemo.java)、[`CastErrorDemo.java`](08_继承与多态/02_多态与类型转换/CastErrorDemo.java) | [`39_2026-11-21_Java多态数组与安全类型转换_简介与学习建议.md`](08_继承与多态/02_多态与类型转换/39_2026-11-21_Java多态数组与安全类型转换_简介与学习建议.md) | 区分声明类型与真实类型，练习多态数组、`instanceof` 和异常反例 |
| 39A | 同上 | [`39_2026-11-21_Java多态数组与安全类型转换_零基础教程.md`](08_继承与多态/02_多态与类型转换/39_2026-11-21_Java多态数组与安全类型转换_零基础教程.md) | 用类型表和异常轨迹理解安全向下转型 |
| 40 | [`Person.java`](08_继承与多态/03_多文件学生继承/Person.java)、[`Student.java`](08_继承与多态/03_多文件学生继承/Student.java) | [`40_2026-11-21_Java多文件学生继承与构造顺序_简介与学习建议.md`](08_继承与多态/03_多文件学生继承/40_2026-11-21_Java多文件学生继承与构造顺序_简介与学习建议.md) | 把父子类拆成两个文件，复用父类构造与介绍方法 |
| 41 | [`Person.java`](08_继承与多态/04_师生继承与重写/Person.java)、[`Student.java`](08_继承与多态/04_师生继承与重写/Student.java)、[`Teacher.java`](08_继承与多态/04_师生继承与重写/Teacher.java) | [`41_2026-11-21_Java师生继承与多文件重写_简介与学习建议.md`](08_继承与多态/04_师生继承与重写/41_2026-11-21_Java师生继承与多文件重写_简介与学习建议.md) | 用一个父类和两个子类练习多文件继承、重写和特有方法 |
| 47 | [`AbstractAndInterface.java`](08_继承与多态/05_抽象类与接口/AbstractAndInterface.java) | [`47_2026-11-21_Java抽象类与接口_简介与学习建议.md`](08_继承与多态/05_抽象类与接口/47_2026-11-21_Java抽象类与接口_简介与学习建议.md) | 理解抽象类与抽象方法、接口、`default` 方法、多接口实现和转型调用 |
| 47A | 同上 | [`47_2026-11-21_Java抽象类与接口_零基础教程.md`](08_继承与多态/05_抽象类与接口/47_2026-11-21_Java抽象类与接口_零基础教程.md) | 用图形与飞行两组案例逐步理解抽象类与接口的分工 |
| 48 | [`CampusManager.java`](04_综合项目/CampusManager.java) | [`48_2026-11-21_Java校园人员管理系统_简介与学习建议.md`](04_综合项目/48_2026-11-21_Java校园人员管理系统_简介与学习建议.md) | 用 `Person` 数组混装学生、教师和普通人员，把继承多态放进菜单项目 |
| 48A | 同上 | [`48_2026-11-21_Java校园人员管理系统_零基础教程.md`](04_综合项目/48_2026-11-21_Java校园人员管理系统_零基础教程.md) | 从类骨架、全局状态到录入分化与显示统一的完整拆解 |
| 49 | [`StringLesson.java`](10_常用类与异常/StringLesson.java) | [`49_2026-11-27_Java字符串核心API_简介与学习建议.md`](10_常用类与异常/49_2026-11-27_Java字符串核心API_简介与学习建议.md) | String 常用 API 全景、`==` 与 `equals`、字符分类统计 |
| 50 | [`BuilderLesson.java`](10_常用类与异常/BuilderLesson.java) | [`50_2026-11-27_Java可变字符串与包装类_简介与学习建议.md`](10_常用类与异常/50_2026-11-27_Java可变字符串与包装类_简介与学习建议.md) | StringBuilder 增删改、包装类缓存 127/128、装箱拆箱与 parseInt |
| 51 | [`CommonClasses.java`](10_常用类与异常/CommonClasses.java) | [`51_2026-11-27_Java数学数组与日期工具类_简介与学习建议.md`](10_常用类与异常/51_2026-11-27_Java数学数组与日期工具类_简介与学习建议.md) | Math 取整区别、Arrays 排序查找复制、LocalDate 天数差与时间戳 |
| 52 | [`ExceptionDemo.java`](10_常用类与异常/ExceptionDemo.java) | [`52_2026-11-27_Java异常处理与finally顺序_简介与学习建议.md`](10_常用类与异常/52_2026-11-27_Java异常处理与finally顺序_简介与学习建议.md) | NPE/越界/finally 顺序/throw 与 `-DcrashMode` 未捕获异常调用栈 |
| 52A | 同上 | [`52_2026-11-27_Java异常处理与finally顺序_零基础教程.md`](10_常用类与异常/52_2026-11-27_Java异常处理与finally顺序_零基础教程.md) | 用执行路径表理解 try-catch-finally 与 return 的关系 |
| 56 | [`CampusPersonManager.java`](04_综合项目/CampusPersonManager.java) | [`56_2026-11-27_Java校园人员管理集合与异常版_简介与学习建议.md`](04_综合项目/56_2026-11-27_Java校园人员管理集合与异常版_简介与学习建议.md) | `List`/`ArrayList` 集合、异常驱动校验、`finally` 关闭资源，对照 48 号 |
| 56A | 同上 | [`56_2026-11-27_Java校园人员管理集合与异常版_零基础教程.md`](04_综合项目/56_2026-11-27_Java校园人员管理集合与异常版_零基础教程.md) | 从数组到集合、从返回提示到抛异常的完整演进拆解 |

建议先按 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9 → 10 → 11 → 12 → 13 → 14 打好基础，再按 15 → 16 → 17 → 18 → 19 → 20 → 21 → 22 → 23 学习方法语义和入门算法。23 号之后用 27 → 28 → 29 → 30 → 31 补一组字符串与数论练习，再用 42 → 43 → 44 → 45 → 46 做一组评测风格算法题，接着用 53 → 54 进入字符串算法与文本分析（42～46、53～54 号是后补编号，学习位置仍在综合项目之前；46 号先读原始版报错、再运行修正版），完成 24 号综合项目后进入 25 → 26 的面向对象阶段，按 32 → 33 → 34 → 35 学习 `Object` 通用方法、对象比较和对象数组，再用 36 号把 24 号的成绩管理系统重构成三个类，按 38 → 39 → 40 → 41 学习继承与多态，用 47 号进入抽象类与接口，用 48 号把继承多态落进完整项目，用 37 → 55 进入栈与循环队列两种线性结构，用 49 → 50 → 51 → 52 学习常用类与异常处理，最后用 56 号集合与异常版校园管理收尾。先运行程序，再阅读简介，卡住时再看零基础教程；40、41 号没有 `main`，先编译并自建入口。`Student`、`StudentTest`、`EncapsulationStaticDemo` 和新一批源码保留了大量原附件注释，编译器会忽略这些注释。

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

运行本批字符串、字母统计与数论练习：

```bash
java_dir=$(mktemp -d /tmp/java-algorithm2.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/03_方法与数组/P01_StringReverse.java" \
  "Java/03_方法与数组/P02_Palindrome.java" \
  "Java/03_方法与数组/P03_CountLetters.java" \
  "Java/03_方法与数组/P04_GCD.java" \
  "Java/03_方法与数组/P05_IsPrime.java"
printf 'hello\n' | java -cp "$java_dir" P01_StringReverse
printf 'level\n' | java -cp "$java_dir" P02_Palindrome
printf 'Hello, World! 123\n' | java -cp "$java_dir" P03_CountLetters
printf '12 18\n' | java -cp "$java_dir" P04_GCD
printf '97\n' | java -cp "$java_dir" P05_IsPrime
```

预期依次输出 `olleh`、`YES`、`10` 及 7 行字母明细、`6`、`Yes`。源码类名保留了原附件的 `P01`～`P05` 前缀，所以 `java` 后面必须写完整类名。

运行 09_算法练习 的评测题（42～46 号）：

```bash
java_dir=$(mktemp -d /tmp/java-algo3.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/09_算法练习/P1_BinarySearch.java" \
  "Java/09_算法练习/P2_BubbleSort.java" \
  "Java/09_算法练习/P3_KthLargest.java" \
  "Java/09_算法练习/P4_DistinctCount.java" \
  "Java/09_算法练习/P5_FrequencyFixed.java"
printf '5\n1 3 5 7 9\n5\n' | java -cp "$java_dir" P1_BinarySearch
printf '5\n5 2 9 1 5\n' | java -cp "$java_dir" P2_BubbleSort
printf '5 2\n3 9 4 9 7\n' | java -cp "$java_dir" P3_KthLargest
printf '5\n3 1 3 2 1\n' | java -cp "$java_dir" P4_DistinctCount
printf '6\n3 1 3 100 0 1\n' | java -cp "$java_dir" P5_FrequencyFixed
```

预期依次输出 `2`、`1 2 5 5 9`、`9`、`3` 和四行频次明细。注意 `P5_Frequency.java`（原始版）**不能编译**：第 14 行丢失 `0;`、第 20 行 `for` 误写为 `f`，两处错误就是 46 号的练习内容，修正版只改这两处。

每份源码的完整命令和真实输出见同目录 15～23、27～31 号说明。`SecondLargest.java` 的早期版本有两个逻辑错误（读取漏下标 0、输出分支写反），已于 2026-11-16 修正；同目录说明与零基础教程保留了当时的排查过程。

运行成绩管理系统：

```bash
java_dir=$(mktemp -d /tmp/java-grade.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/04_综合项目/GradeManager.java"
printf '1\n1001\n张三\n88.5\n3\n4\n5\n0\n' |
  java -cp "$java_dir" GradeManager
```

程序支持最多 50 人、学号查重、整数/小数/成绩范围校验、并列最高分和同步降序排序。数据只保存在内存中，标准输入提前结束时通过 `System.exit(0)` 退出。

运行类、对象、构造方法和引用练习：

```bash
java_dir=$(mktemp -d /tmp/java-object.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/05_面向对象/Student.java" \
  "Java/05_面向对象/StudentTest.java"
java -cp "$java_dir" StudentTest
```

运行封装、`this` 与 `static` 练习：

```bash
java_dir=$(mktemp -d /tmp/java-encapsulation.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/05_面向对象/EncapsulationStaticDemo.java"
java -cp "$java_dir" EncapsulationStaticDemo
```

运行 `Object` 通用方法与对象数组练习：

```bash
java_dir=$(mktemp -d /tmp/java-objectmethods.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/06_Object方法与对象数组/Student.java" \
  "Java/06_Object方法与对象数组/RawStudent.java" \
  "Java/06_Object方法与对象数组/ToStringPredictDemo.java" \
  "Java/06_Object方法与对象数组/EqualsDemo.java" \
  "Java/06_Object方法与对象数组/NullPointerDemo.java" \
  "Java/06_Object方法与对象数组/StudentArrayDemo.java"
java -cp "$java_dir" ToStringPredictDemo
java -cp "$java_dir" EqualsDemo
printf 'Tom 90\nAmy 85\n' | java -cp "$java_dir" StudentArrayDemo
```

运行面向对象重构版成绩管理系统：

```bash
java_dir=$(mktemp -d /tmp/java-sms.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/04_综合项目/StudentManagementSystem.java"
printf '3\n4\n5\n1\n1001\n张三\n88\n1\n1001\n1002\n李四\n-1\n95\n2\n1002\n4\n5\n0\n' |
  java -cp "$java_dir" StudentManagementSystem
```

本文件包含包级的 `Student` 和 `StudentManager`，必须单独编译；不要与 `Java/05_面向对象/Student.java` 或 `Java/06_Object方法与对象数组/Student.java` 放进同一次 `javac` 调用，否则会出现重复类错误。程序支持最多 100 人、学号查重、整数成绩范围校验、并列最高分和对象排序；没有处理标准输入提前结束。

运行数组实现的栈与括号匹配：

```bash
java_dir=$(mktemp -d /tmp/java-stack.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/07_数据结构/StackDemo.java"
java -cp "$java_dir" StackDemo
```

程序不需要输入，内置 3 组演示和 13 组括号测试。正常输出应以 `共 13 项测试，通过 13 项` 结束；若出现 `[失败]`，即使退出码仍为 0，也要继续排查。

运行继承与重写演示：

```bash
java_dir=$(mktemp -d /tmp/java-inheritance.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/08_继承与多态/01_继承与重写演示/InheritanceDemo.java"
java -cp "$java_dir" InheritanceDemo
```

运行多态与类型转换演示：

```bash
java_dir=$(mktemp -d /tmp/java-polymorphism.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/08_继承与多态/02_多态与类型转换/PolymorphismDemo.java" \
  "Java/08_继承与多态/02_多态与类型转换/CastErrorDemo.java"
java -cp "$java_dir" PolymorphismDemo
java -cp "$java_dir" CastErrorDemo
```

`CastErrorDemo` 会按设计抛出 `ClassCastException`，退出码为 1。40、41 号只有类定义，没有 `main`；完整编译命令见各自简介。`08_继承与多态` 的五个子目录都包含同名类（05 组为 `AbstractAndInterface.java` 单文件、内部类不与别处冲突），必须逐组编译，不能合并成一次 `javac` 调用。47 号含 `main` 可直接运行。

运行抽象类与接口演示：

```bash
java_dir=$(mktemp -d /tmp/java-abstract.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/08_继承与多态/05_抽象类与接口/AbstractAndInterface.java"
java -cp "$java_dir" AbstractAndInterface
```

程序不需要输入，输出抽象类面积与接口两组行为，其中麻雀和鸭子使用接口默认的 `land()`。

运行校园人员管理系统：

```bash
java_dir=$(mktemp -d /tmp/java-campus.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/04_综合项目/CampusManager.java"
printf '1\n张三\n20\n1\n2026001\n1\n李四\n35\n2\nT001\n1\n王五\n28\n3\n2\n张三\n3\n4\n0\n' |
  java -cp "$java_dir" CampusManager
```

本文件包含包级的 `Person`、`Student`、`Teacher`，与 `08_继承与多态` 各分组、`05` 和 `06` 目录的同名类冲突，必须单独编译。程序容量固定 5 人，数据只保存在内存中；容量满时 `addPerson` 在读取姓名前就返回，连续喂数据要把后续字段重新对齐菜单。

运行循环队列演示：

```bash
java_dir=$(mktemp -d /tmp/java-queue.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/07_数据结构/MyQueue.java"
java -cp "$java_dir" MyQueue
```

程序不需要输入，输出以 `[]` 结束（队列清空）。已实际运行，`full rejected=true` 出现两次说明满队拒绝生效。

运行常用类与异常演示：

```bash
java_dir=$(mktemp -d /tmp/java-core.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/10_常用类与异常/StringLesson.java" \
  "Java/10_常用类与异常/BuilderLesson.java" \
  "Java/10_常用类与异常/CommonClasses.java" \
  "Java/10_常用类与异常/ExceptionDemo.java"
printf 'Hello World 123!\n' | java -cp "$java_dir" StringLesson
printf '42\n' | java -cp "$java_dir" BuilderLesson
java -cp "$java_dir" CommonClasses
java -cp "$java_dir" ExceptionDemo
java -DcrashMode=1 -cp "$java_dir" ExceptionDemo
```

`CommonClasses` 的随机数、随机姓名、日期差与时间戳每次都不同；`ExceptionDemo` 默认模式退出码 0，`-DcrashMode=1` 故意抛出未捕获 `NullPointerException`、退出码 1（`2`、`3` 同理），这是调用栈演示，不是环境故障。

运行字符串算法与文本分析：

```bash
java_dir=$(mktemp -d /tmp/java-algo4.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/09_算法练习/StringAlgorithms.java" \
  "Java/09_算法练习/TextAnalyzer.java"
printf 'hello   world java\nworld hello java\n' | java -cp "$java_dir" StringAlgorithms
printf 'level radar test\n' | java -cp "$java_dir" TextAnalyzer 2
```

预期 `StringAlgorithms` 输出 `java world hello` / `true` / `3` / `hello world java`；`TextAnalyzer` 输出 `words=3`、26 个字母频次、`longest=level`、`palindromes=level, radar` 及前两个单词。

运行集合与异常版校园管理：

```bash
java_dir=$(mktemp -d /tmp/java-campus2.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/04_综合项目/CampusPersonManager.java"
printf '1\n张三\n20\n2\n张三\n0\n' | java -cp "$java_dir" CampusPersonManager
```

本文件只有私有静态内部类 `Person`（`CampusPersonManager$Person`），不与顶层 `Person` 冲突；但仍建议单独编译。集合自动扩容，没有容量上限；输入提前结束由 `NoSuchElementException` 兜底，退出码 0。

`NullPointerDemo` 会在最后故意抛出 `NullPointerException`，退出码为 1；这是教学结果，不是编译或运行环境故障。`Java/06_Object方法与对象数组/Student.java` 与 `Java/05_面向对象/Student.java` 同名但内容不同，必须分开目录编译，不能把两组源码放进同一个输出目录。

`StudentTest` 必须和 `Student` 一起编译。`EncapsulationStaticDemo.java` 内部还有 `SafeStudent` 等包级类，只有 `EncapsulationStaticDemo` 是 `public`，因此运行类名仍是 `EncapsulationStaticDemo`。

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
21. 能画出成绩管理系统中 `ids`、`names`、`scores` 三个平行数组和 `count` 的关系。
22. 能解释菜单程序从 `main` 到业务方法再返回菜单的完整调用路径。
23. 能在排序时同步交换三个数组，并设计空名单、重复学号、并列最高分和容量上限测试。
24. 能解释类、对象、构造方法、实例字段和实例方法，并画出对象引用关系。
25. 能说明封装为什么同时要求私有字段、受控 setter 和构造校验。
26. 能区分实例字段与 `static` 字段、实例方法与 `static` 方法。
27. 能判断对象传参时“修改共同对象”和“重新赋值形参”的不同结果。
28. 合上源码，自己写一个数组求和与冒泡排序程序，再写一个带退出条件的小游戏。
29. 能说明 `String` 不可变与 `StringBuilder` 的分工，独立写出字符串倒序。
30. 能用双指针判断回文，解释空串、大小写和空格的处理规则。
31. 能用 26 格数组统计字母，解释 `ch - 'a'` 和 `(char)` 转换。
32. 能手工执行辗转相除，说明负数取绝对值和两个数都是 0 时的约定。
33. 能解释 `i <= n / i` 为什么比 `i * i <= n` 更安全，并用 `long` 判断大数质数。
34. 能说明默认 `toString` 与重写后的区别，解释 `println(对象)` 和字符串拼接的自动调用。
35. 能按四步重写 `equals`，并说明为什么必须同时重写 `hashCode`。
36. 能解释对象数组默认值是 `null`、什么操作会触发 NPE，并完成对象数组的录入与平均分。
37. 能对比 24 号“平行数组 + 静态方法”和 36 号“对象 + 管理器 + 界面类”两种实现，说明职责划分、校验集中和排序交换方式的变化。
38. 能画出继承关系，预测父类与子类构造方法的执行顺序，并区分重载与重写。
39. 能分别说明引用的声明类型与对象的真实类型，预测多态数组中实际执行的方法版本。
40. 能使用 `instanceof` 保护向下转型，并解释 `ClassCastException` 为什么在运行时发生。
41. 能独立编写多文件父子类及最小 `Main`，并说明不同练习的同名类为什么必须隔离编译。
42. 能画出栈的 `top` 与底层数组变化，独立实现 `MyStack` 的入栈、出栈和查看栈顶，并用栈判断括号是否匹配。
43. 能在评测格式下独立写出二分查找和冒泡排序，说明有序前提、`-1` 约定、行末空格与 14 号教学版的差别。
44. 能手工跟踪降序插入排序的每轮移动，解释重复占位的第 k 大与 22 号"不同值"语义的区别，以及 `&&` 条件顺序为什么不能反。
45. 能分别用"排序后相邻比较"和"计数数组"两种思路完成去重计数与频次统计，并说明两种思路对数据范围的要求。
46. 能说明抽象类与接口各自的适用场景、`default` 方法的行为，并用 `instanceof` 保护接口向下的转型调用。
47. 能画出 `CampusManager` 中 `people`、`count` 的内存关系，解释录入时按类型分化、显示时 `toString` 统一的多态路线。
48. 能说明 `String` 不可变、`substring` 左闭右开、`split` 按正则切分，以及 `==` 与 `equals` 在字符串上的区别。
49. 能区分 `String` 与 `StringBuilder` 的适用场景，解释 `delete(start, end)` 的区间语义，并说明包装类 `127/128` 缓存差异与"包装类比较用 `equals`"的结论。
50. 能说出 `ceil` / `floor` / `round` 的取整方向差异，解释 `Arrays.binarySearch` 找不到时返回 `-插入点-1` 的约定，以及日期差为什么有符号。
51. 能预测 `try-catch-finally` 在正常、异常、`return` 三种路径下的执行顺序，读懂未捕获异常的调用栈并用非零退出码判断失败。
52. 能画出循环队列 `head` / `tail` / `size` 的回绕过程，解释"为什么必须有 `size`"，并说明满队返回 `false`、空队抛异常的约定。
53. 能用 `List`/`ArrayList` 替代固定数组、用 `throw` + 统一 `catch` 分层校验，并说明 `finally` 在资源关闭中的作用。

当前已经覆盖程序入口、基本类型、主要运算符、输入校验、条件分支、循环、方法拆分与重载、值传递、数组引用、多种入门算法、字符串倒序与回文、字母统计、GCD、`long` 质数判定、成绩管理项目，以及类对象、构造方法、封装、`this`、`static`、对象引用传参、`Object` 通用方法、`equals` / `hashCode`、空指针、对象数组、面向对象项目重构、数组实现栈与循环队列、继承、构造顺序、方法重写、多态类型转换、抽象类与接口、String/StringBuilder/包装类与常用工具类、异常处理与 `finally`、`List`/`ArrayList` 集合的初步使用、评测风格算法与字符串算法，以及两版校园人员管理项目。这个阶段提供面向对象、基础数据结构、常用类与异常的练习资源，不代表已经掌握 `Map`/`Set` 等集合体系、文件持久化或工程化测试。

## 维护规则

新增 Java 练习时：

1. 根据主要学习目标建立或选择二级目录。当前已有 `01_基础语法`、`02_分支与循环`、`03_方法与数组`、`04_综合项目`、`05_面向对象`、`06_Object方法与对象数组`、`07_数据结构`、`08_继承与多态`、`09_算法练习` 和 `10_常用类与异常`；后续集合、`Map`/`Set`、IO 等目录等真实练习出现后再新增。
2. 源码文件名遵守 `public` 类的文件名要求。
3. 在源码旁增加带序号和日期的 `_简介与学习建议.md`；内容复杂时再增加 `_零基础教程.md`。
4. 编译时使用 UTF-8，并把 `.class` 输出到临时目录。
5. 未经明确授权，不修改原始学习代码；发现注释或代码问题时先在学习说明中记录。
6. 如果是故意演示警告的源码，在说明中写清预期警告和严格编译结果，不用修改源码来掩盖教学现象。
7. 如果源码能编译但逻辑错误，分别记录当前真实输出、题目预期、出错位置和最小修复思路；需要修正代码时另建版本，不覆盖原始附件。
