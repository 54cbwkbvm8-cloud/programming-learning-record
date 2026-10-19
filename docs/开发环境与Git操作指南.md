# 开发环境与 Git 操作指南

这份指南集中说明 macOS 下运行本仓库所需的工具、安装检查、编译运行、调试和 Git/GitHub 操作。其他学习文档遇到环境问题时直接链接到这里，不再重复维护安装步骤。

## 工具优先级

| 工具 | 优先级 | 用途 |
| --- | --- | --- |
| Python 3 | 必需 | 运行仓库中的 Python 脚本 |
| C 编译器 | 必需 | 编译 C17 程序，本机可用 `clang` |
| 终端 | 必需 | 进入目录、运行程序、执行 Git |
| Git | 必需 | 检查差异、提交和同步仓库 |
| VS Code | 推荐 | 阅读代码、终端运行、调试 C/Python |
| NumPy、Matplotlib | 特定场景 | 运行 3D 莲花可视化项目 |

## macOS 环境检查

先打开“终端”，依次运行：

```bash
python3 --version
clang --version
git --version
xcode-select -p
```

检查结果应包含 Python 版本、Apple clang 版本、Git 版本和命令行工具目录。

本文档编写时，在本机实际观察到：

```text
Python 3.14.7
Apple clang 21.0.0
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

扩展 ID：

```text
ms-python.python
ms-vscode.cpptools
```

命令行安装示例：

```bash
code --install-extension ms-python.python
code --install-extension ms-vscode.cpptools
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

先运行 `pwd` 确认当前目录，再检查路径中的中文、空格和引号。仓库根目录执行命令时，路径应以 `C/` 或 `Python/` 开头。

### Python 找不到数据文件

文件练习可能依赖同目录的 `学生信息.json` 或 `学生成绩示例.txt`。进入脚本所在目录执行，或检查脚本是否使用 `Path(__file__)`。

### 运行后生成 `students.txt` 或 `students.json`

这是程序的输出文件，已被 `.gitignore` 忽略。不要手动提交运行产物；需要示例数据时确认文件是否本来就是仓库跟踪的数据文件。

### Git 显示很多中文路径转义

执行 `git config core.quotepath false` 后重新查看状态。

## 验证状态

- 本文引用的 Python、VS Code、Git、GitHub 和 Apple 官方链接在 2026-10-19 检查返回 HTTP 200。
- 当前机器的 `python3`、`clang` 和 `git` 命令已实际运行验证。
- 仓库中的 Python 文件已通过 `compileall`，C 文件已通过 C17 语法检查。
- VS Code 扩展安装命令和可视化依赖安装未在本文档更新时重新执行，使用前应根据本机状态验证。
