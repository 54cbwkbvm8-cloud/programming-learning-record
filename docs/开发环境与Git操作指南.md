# 开发环境与 Git 操作指南

这份指南集中说明 macOS 下运行本仓库所需的工具、安装检查、编译运行、调试和 Git/GitHub 操作。其他学习文档遇到环境问题时直接链接到这里，不再重复维护安装步骤。

## 工具优先级

| 工具 | 优先级 | 用途 |
| --- | --- | --- |
| Python 3 | 必需 | 运行仓库中的 Python 脚本 |
| C 编译器 | 必需 | 编译 C17 程序，本机可用 `clang` |
| JDK | Java 学习时必需 | 提供 `javac` 与 `java`，运行 Java 源码 |
| 终端 | 必需 | 进入目录、运行程序、执行 Git |
| Git | 必需 | 检查差异、提交和同步仓库 |
| VS Code | 推荐 | 阅读代码、终端运行、调试 C/Python/Java |
| NumPy、Matplotlib | 特定场景 | 运行 3D 莲花可视化项目 |

## macOS 环境检查

先打开“终端”，依次运行：

```bash
python3 --version
clang --version
java -version
javac -version
git --version
xcode-select -p
```

检查结果应包含 Python 版本、Apple clang 版本、Git 版本和命令行工具目录。

本文档编写时，在本机实际观察到：

```text
Python 3.14.7
Apple clang 21.0.0
openjdk version "21.0.13" 2026-10-20 LTS
javac 21.0.13
git version 2.54.0 (Apple Git-157)
macOS 27.0.1
```

这些版本只代表当前电脑，不是必须安装同样版本。

## 安装 C 编译器

### 是否必需

学习 C 语言必须能够编译 C 源文件。macOS 通常可通过 Apple Command Line Tools 获得 `clang`。

### 官方参考

- Apple Xcode 资源页：[developer.apple.com/xcode/resources](https://developer.apple.com/xcode/resources/)
- 安装命令由 macOS 自带工具提供：

```bash
xcode-select --install
```

安装完成后验证：

```bash
xcode-select -p
clang --version
```

### 编译 C 程序

在仓库根目录执行：

```bash
clang -std=c17 -Wall -Wextra -Wpedantic \
  "C/01_基础语法/01_2026-09-28_基础语法综合练习.c" \
  -o /tmp/c_practice
/tmp/c_practice
```

各选项的含义：

- `-std=c17`：使用 C17 标准。
- `-Wall -Wextra -Wpedantic`：开启常用警告，尽早发现可疑写法。
- `-o /tmp/c_practice`：把可执行文件放到临时目录，不污染仓库。

### 调试 C 程序

需要逐行调试时添加 `-g`：

```bash
clang -std=c17 -g -Wall -Wextra -Wpedantic program.c -o /tmp/program
lldb /tmp/program
```

在 `lldb` 中可以先使用：

```text
run
next
step
frame variable
quit
```

当前学习阶段优先使用编译警告、打印中间变量和边界输入定位问题。

## 安装与使用 Java

### 是否必需

运行本仓库的 Java 练习必须有 JDK。JDK 同时包含编译器 `javac` 和运行器 `java`，只安装 JRE 不足以编译源码。

当前电脑已经实际检测到 Temurin OpenJDK 21.0.13 LTS，因此不需要重复安装。其他电脑可从 [Eclipse Temurin](https://adoptium.net/temurin/releases/?os=mac) 选择 macOS 安装包，或者按课程要求安装兼容 JDK。

验证：

```bash
java -version
javac -version
which java
which javac
```

`java -version` 和 `javac -version` 两个版本最好一致。

### 编译与运行 Java 程序

在仓库根目录运行：

```bash
java_dir=$(mktemp -d /tmp/java-practice.XXXXXX)
javac -encoding UTF-8 -Xlint:all -d "$java_dir" "Java/01_基础语法/hellojava.java"
java -cp "$java_dir" hellojava
```

运行基本类型程序：

```bash
java_dir=$(mktemp -d /tmp/java-practice.XXXXXX)
javac -encoding UTF-8 -Xlint:all -d "$java_dir" "Java/01_基础语法/BasicTypes.java"
java -cp "$java_dir" BasicTypes
```

运行带输入的分支程序：

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

运行带输入重试的循环程序：

```bash
java_dir=$(mktemp -d /tmp/java-star.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/02_分支与循环/StarTriangle.java"
printf 'abc\n0\n3\n' | java -cp "$java_dir" StarTriangle
```

上面的测试依次提供非整数 `abc`、非法范围 `0` 和合法行数 `3`，可以验证输入缓冲区清理与正数校验。学习循环时，先在纸上写出每一轮变量值，再用输入管道验证预期输出。

运行方法与数组综合练习：

```bash
java_dir=$(mktemp -d /tmp/java-array.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/03_方法与数组/ArrayAndBubbleSort.java"
java -cp "$java_dir" ArrayAndBubbleSort
```

程序使用固定数据，不需要输入。先观察输出中的数组默认值、引用复制和排序过程，再查看末尾 18 条教学检查。`check()` 失败时只打印 `[失败]`，不会改变退出码，因此不能把退出码 0 单独当作所有检查通过的证据。

运行方法定义、重载和值传递练习：

```bash
java_dir=$(mktemp -d /tmp/java-method.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/03_方法与数组/MethodBasic.java" \
  "Java/03_方法与数组/MaxOverload.java" \
  "Java/03_方法与数组/PassByValue.java"
java -cp "$java_dir" MethodBasic
java -cp "$java_dir" MaxOverload
java -cp "$java_dir" PassByValue
```

`SecondLargest.java` 也通过了严格编译，但运行逻辑有已知错误。例如输入 `5 / 8 3 8 6 1` 当前输出 `NO`，题目预期是 `6`。编译成功只证明语法和静态检查通过，不代表算法结果正确；调试顺序见 [第二大不同值零基础教程](../Java/03_方法与数组/22_2026-11-08_Java第二大不同值_零基础教程.md)。

运行菜单式成绩管理系统：

```bash
java_dir=$(mktemp -d /tmp/java-grade.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/04_综合项目/GradeManager.java"
printf '1\n1001\n张三\n88.5\n2\n1001\n3\n4\n5\n0\n' |
  java -cp "$java_dir" GradeManager
```

该程序读取多轮菜单输入。自动化测试时可以用 `printf` 一次送入完整流程；如果输入提前用完，`exitIfNoInput()` 会打印退出提示并调用 `System.exit(0)`。这不同于正常选择 0 的菜单退出路径，但当前退出码同样是 0。

运行类、对象、构造方法与引用练习：

```bash
java_dir=$(mktemp -d /tmp/java-object.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/05_面向对象/Student.java" \
  "Java/05_面向对象/StudentTest.java"
java -cp "$java_dir" StudentTest
```

`StudentTest.java` 依赖 `Student.java`，两个文件必须一起编译。运行入口是带 `main` 的 `StudentTest`，不是 `Student`。

运行封装、`this` 与 static 演示：

```bash
java_dir=$(mktemp -d /tmp/java-encapsulation.XXXXXX)
javac -encoding UTF-8 -Xlint:all -Werror -d "$java_dir" \
  "Java/05_面向对象/EncapsulationStaticDemo.java"
java -cp "$java_dir" EncapsulationStaticDemo
```

这个文件还包含 `SafeStudent`、`OpenStudent`、`NoThisStudent` 和 `CounterWrong` 四个包级类。一个 `.java` 文件最多只能有一个 `public` 类，当前只有文件名相同的 `EncapsulationStaticDemo` 是公开类。

`printf '90\n'` 把 `90` 和换行送进标准输入，效果相当于手动输入后按回车。`SwitchBreakDemo.java` 故意展示漏写 `break` 的穿透，因此 `-Xlint:all` 会报告预期警告，加 `-Werror` 会失败；具体原因见 [同目录简介](../Java/02_分支与循环/04_2026-11-04_Java-switch与break穿透_简介与学习建议.md)。

参数说明：

- `-encoding UTF-8`：源文件含中文，显式按 UTF-8 读取。
- `-Xlint:all`：开启完整常用编译器提示。
- `-d "$java_dir"`：把 `.class` 写到临时目录。
- `-cp "$java_dir"`：让 Java 到同一临时目录查找类。
- `java` 后面写类名，不写 `.java` 或 `.class` 后缀。

### Java 文件名与类名

如果源码声明 `public class BasicTypes`，文件名必须是 `BasicTypes.java`。文件名、类名和 `java` 启动时使用的类名都区分大小写。

当前 `hellojava.java` 的类没有声明为 `public`，因此保留这个历史文件名仍能编译；运行时仍然写：

```bash
java -cp "$java_dir" hellojava
```

正式新写练习时，类名建议使用 `HelloJava` 这类大驼峰命名，文件相应叫 `HelloJava.java`。

### 调试 Java 程序

需要调试信息时在编译时添加 `-g`：

```bash
javac -g -encoding UTF-8 -Xlint:all -d "$java_dir" HelloJava.java
jdb -classpath "$java_dir" HelloJava
```

初学阶段更建议先读编译器给出的文件名、行号、符号和错误信息，再用临时输出验证变量。不要因为程序“能运行”就跳过逻辑核对。

## 安装与使用 Python

### 是否必需

仓库大多数脚本使用 Python 标准库。macOS 一般自带 `python3`，但版本可能较旧；也可以在 Python 官网安装新版。

官方下载页：[Python for macOS](https://www.python.org/downloads/macos/)

验证：

```bash
python3 --version
which python3
```

### 运行普通脚本

在仓库根目录：

```bash
python3 "Python/01_基础语法/01_2026-08-23_输出偶数.py"
python3 "Python/13_综合项目/02_2026-09-09_简易计算器_函数版.py"
```

### 创建虚拟环境

只有需要第三方依赖时才建议创建虚拟环境：

```bash
python3 -m venv .venv
source .venv/bin/activate
python3 -m pip install -r requirements.txt
```

`requirements.txt` 当前用于 3D 莲花动画，包含 NumPy 和 Matplotlib。

运行可视化项目：

```bash
python3 "Python/14_可视化项目/01_2026-09-09_3D莲花动画.py"
```

退出虚拟环境：

```bash
deactivate
```

### 调试 Python

查看完整异常时，先读最后一条错误和文件行号。需要交互调试时可以运行：

```bash
python3 -m pdb "Python/01_基础语法/01_2026-08-23_输出偶数.py"
```

初学阶段更常用的是：

- 在关键变量附近使用 `print()` 检查值。
- 输入非法数据验证校验分支。
- 从最小可运行片段开始缩小问题。

## 安装 VS Code

### 是否必需

不是必需工具，但推荐用于阅读多文件项目、打开终端和调试程序。

官方 macOS 安装说明：[Visual Studio Code on macOS](https://code.visualstudio.com/docs/setup/mac)

安装后验证：

```bash
code --version
```

如果终端找不到 `code`，在 VS Code 命令面板中执行 “Shell Command: Install 'code' command in PATH”。

### 推荐扩展

- Python：官方说明见 [Python in Visual Studio Code](https://code.visualstudio.com/docs/languages/python)。
- C/C++：官方说明见 [C++ programming with Visual Studio Code](https://code.visualstudio.com/docs/languages/cpp)。
- Java：可使用 Microsoft Java 扩展包，扩展 ID 为 `vscjava.vscode-java-pack`，其中包含语言支持和调试入口。

扩展 ID：

```text
ms-python.python
ms-vscode.cpptools
vscjava.vscode-java-pack
```

命令行安装示例：

```bash
code --install-extension ms-python.python
code --install-extension ms-vscode.cpptools
code --install-extension vscjava.vscode-java-pack
```

不要求安装所有主题、格式化器或其他扩展。先保证代码显示、终端运行和调试可用。

## Git 与 GitHub

### 是否必需

把学习记录保存到 GitHub 就需要 Git。Git 官方下载与安装说明：[Git Downloads](https://git-scm.com/download/mac)。

验证：

```bash
git --version
git config --get user.name
git config --get user.email
```

没有配置身份时，用你自己的信息设置：

```bash
git config --global user.name "你的名字"
git config --global user.email "你的 GitHub 邮箱"
```

不要把他人的身份信息写入仓库。

### 查看仓库状态

```bash
git status
git diff
git log --oneline -5
```

中文路径较多时，可以先设置：

```bash
git config core.quotepath false
```

### 检查后再提交

```bash
git status
git diff
git add "具体文件或目录"
git diff --cached
git commit -m "一句具体的提交说明"
git push
```

安全原则：

- 不用 `git add .` 代替检查，尤其仓库里存在临时文件时。
- 提交前确认没有密码、令牌、私钥和个人数据。
- `git diff --cached` 没看过的内容不要提交。
- 不使用 `git reset --hard` 或强制推送来掩盖问题。
- 推送失败时先保留本地修改，阅读错误信息，再处理冲突或认证。

GitHub 基础操作官方文档：[GitHub Hello World](https://docs.github.com/en/get-started/start-your-journey/hello-world)。

### 本仓库推荐检查顺序

```bash
python3 -m py_compile <修改过的 Python 文件>
clang -std=c17 -Wall -Wextra -Wpedantic -fsyntax-only <修改过的 C 文件>
javac -encoding UTF-8 -Xlint:all -d <临时目录> <修改过的 Java 文件>
git status
git diff --check
git diff --cached --stat
```

如果任务只应修改文档，再执行：

```bash
git diff --name-only
```

确认列表中没有意外的 `.py`、`.c`、数据或配置文件。

## 终端基础操作

```bash
pwd                 # 显示当前目录
ls                  # 查看当前目录内容
cd C                # 进入 C 目录
cd ..               # 返回上一级
cd "目录名"          # 目录名含空格时加引号
```

路径含中文或空格时始终加引号。编译输出建议放到 `/tmp`，避免把可执行文件提交进仓库。

## 常见问题

### `command not found: python3`

确认 Python 已安装，重新打开终端，并检查 `which python3`。如果从官网安装，按安装器提示配置 PATH。

### `command not found: clang`

运行 `xcode-select --install`，安装完成后重新打开终端，再检查 `xcode-select -p`。

### 编译提示找不到文件

先运行 `pwd` 确认当前目录，再检查路径中的中文、空格和引号。仓库根目录执行命令时，路径应以 `C/`、`Python/` 或 `Java/` 开头。

### `javac` 提示类名与文件名不匹配

检查是否出现 `public class Name`。public 类名必须和文件名大小写完全一致。例如 `BasicTypes` 不能随便改成带日期前缀的文件名；如需记录日期，写在配套 Markdown 文档和导航中。

### `java` 提示找不到主类

先确认 `javac -d` 和 `java -cp` 指向同一个临时目录，再确认启动时写的是类名。当前第一个程序应运行：

```bash
java -cp "$java_dir" hellojava
```

不是 `java hellojava.java`，也不是 `java HelloWord`。

### Java 源码中的中文乱码或编译错误

本仓库 Java 文件按 UTF-8 保存。编译时明确加入：

```bash
javac -encoding UTF-8 ...
```

不要在 Windows 与 macOS 间来回保存时混用不同编码。

### Python 找不到数据文件

文件练习可能依赖同目录的 `学生信息.json` 或 `学生成绩示例.txt`。进入脚本所在目录执行，或检查脚本是否使用 `Path(__file__)`。

### 运行后生成 `students.txt` 或 `students.json`

这是程序的输出文件，已被 `.gitignore` 忽略。不要手动提交运行产物；需要示例数据时确认文件是否本来就是仓库跟踪的数据文件。

### Git 显示很多中文路径转义

执行 `git config core.quotepath false` 后重新查看状态。

## 验证状态

- 本文引用的 Python、VS Code、Git、GitHub 和 Apple 官方链接在 2026-10-19 检查返回 HTTP 200。
- 当前机器的 `python3`、`clang`、`java`、`javac` 和 `git` 命令已实际运行验证。
- 仓库中的 Python 文件已通过 `compileall`，C 文件已通过 C17 语法检查。
- 2026-11-02 新增的两份 Java 文件已使用 OpenJDK 21.0.13、UTF-8 和 `-Xlint:all` 编译，并实际运行。
- 2026-11-04 新增的四份 Java 文件已使用同一环境检查；其余三份通过 `-Werror`，`SwitchBreakDemo.java` 按设计保留 11 条穿透警告并已实际运行。
- 2026-11-07 新增的 `ArrayAndBubbleSort.java` 已在 OpenJDK 21.0.13 下通过 `-Xlint:all -Werror` 编译并运行，18 条教学检查全部显示通过。
- 2026-11-08 新增的九份 Java 文件全部通过 OpenJDK 21.0.13 的 `-Xlint:all -Werror` 编译，并完成代表性运行；源码字节与上传附件一致。`SecondLargest.java` 的运行错误已记录为教学案例，没有把编译通过误报为功能正确。
- 2026-11-09 新增的 `GradeManager.java` 已使用同一 JDK 严格编译并运行；验证了正常流程、空名单、重复学号、非法成绩、并列最高分、降序排序、50 人容量上限和 EOF 退出。
- 2026-11-11 新增的 47 号单链表练习已通过 C17 严格编译、ASan/UBSan、Clang Static Analyzer 和 macOS `leaks --atExit`；6 组删除分支输出符合预期，泄漏检查结果为 0 leaks。
- 2026-11-12 新增的 `Student.java`、`StudentTest.java` 和 `EncapsulationStaticDemo.java` 已使用 OpenJDK 21.0.13 通过 `-Xlint:all -Werror`；`StudentTest` 输出 8 行，封装与 static 演示输出 20 行，结果符合预期。
- VS Code 扩展安装命令和可视化依赖安装未在本文档更新时重新执行，使用前应根据本机状态验证。
