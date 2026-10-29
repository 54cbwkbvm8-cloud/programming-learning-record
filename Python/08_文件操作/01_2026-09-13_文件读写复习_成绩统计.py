# 文件用途：本文件围绕“文件读写复习 成绩统计”展开。
# 核心知识点：函数、循环、分支判断、文件读写、异常处理。
# 基本流程：根据代码中的输入或初始数据，按条件、循环和函数调用完成处理，再输出结果。
# 输入：本文件使用预设数据、初始化值或函数参数。输出：通过控制台打印结果。 文件操作：代码中包含文件读写相关逻辑，运行时需留意路径、权限和文件内容。
# 阅读提醒：异常分支是否覆盖所有错误情况，应结合具体输入和运行环境判断。
# 模块依赖：json, pathlib。
# 导入后续代码需要使用的模块或名称；第三方模块还需在当前 Python 环境安装对应依赖。
import json
# 导入后续代码需要使用的模块或名称；第三方模块还需在当前 Python 环境安装对应依赖。
from pathlib import Path
# 把表达式 `Path(__file__).resolve().parent` 的计算结果保存到 `BASE_DIR`；右侧是数据来源，左侧是接收结果的变量。
BASE_DIR = Path(__file__).resolve().parent
# 计算 `BASE_DIR / '学生成绩示例.txt'` 并将结果保存到 `TEXT_FILE`；按运算符和运算顺序逐步计算，可以验证结果是否正确。
TEXT_FILE = BASE_DIR / "学生成绩示例.txt"
# 计算 `BASE_DIR / '学生信息.json'` 并将结果保存到 `JSON_FILE`；按运算符和运算顺序逐步计算，可以验证结果是否正确。
JSON_FILE = BASE_DIR / "学生信息.json"
# 定义函数 `calculate_scores`；参数包括 scores，函数体负责具体处理，调用处应关注传入参数和返回值。
def calculate_scores(scores):
    # 判断条件 `len(scores) == 0`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    if len(scores) == 0:
        raise ValueError("列表不能为空")
    # 通过下标/键读取 `scores[0]` 指定的数据，并保存到 `max_score`；使用前需保证索引或键有效。
    max_score = scores[0]
    # 通过下标/键读取 `scores[0]` 指定的数据，并保存到 `min_score`；使用前需保证索引或键有效。
    min_score = scores[0]
    # 把累计变量 `total_score` 初始化为 0，后续通常会在循环中不断累加每一项。
    total_score = 0
    # 把表达式 `0` 的计算结果保存到 `pass_count`；右侧是数据来源，左侧是接收结果的变量。
    pass_count = 0
    # 遍历 `scores`，每轮把当前元素赋给 `score`，再执行循环体；确认容器内容与循环次数是否符合预期。
    for score in scores:
        # 判断条件 `score > max_score`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if score > max_score:
            # 把表达式 `score` 的计算结果保存到 `max_score`；右侧是数据来源，左侧是接收结果的变量。
            max_score = score
        # 判断条件 `score < min_score`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if score < min_score:
            # 把表达式 `score` 的计算结果保存到 `min_score`；右侧是数据来源，左侧是接收结果的变量。
            min_score = score
        # 把 `score` 累加到 `total_score`，相当于 `total_score = total_score + score`；这是更新原变量，而不是创建新变量。
        total_score += score
    # 用总和除以元素数量计算平均值并存入 `average_score`；需确保除数不为 0。
    average_score = total_score / len(scores)
    # 结束当前函数调用，并把 `(max_score, min_score, average_score, pass_count)` 的结果交给调用方；调用方可以继续使用这个值。
    return max_score, min_score, average_score,pass_count
# 定义函数 `find_max`，用于查找符合条件的数据；参数包括 numbers，函数体负责具体处理，调用处应关注传入参数和返回值。
def find_max(numbers):
    # 判断条件 `len(numbers) == 0`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    if len(numbers) == 0:
        raise ValueError("列表不能为空")
    # 通过下标/键读取 `numbers[0]` 指定的数据，并保存到 `max_number`；使用前需保证索引或键有效。
    max_number = numbers[0]
    # 遍历 `numbers[1:]`，每轮把当前元素赋给 `number`，再执行循环体；确认容器内容与循环次数是否符合预期。
    for number in numbers[1:]:
        # 判断条件 `number > max_number`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if number > max_number:
            # 把表达式 `number` 的计算结果保存到 `max_number`；右侧是数据来源，左侧是接收结果的变量。
            max_number = number
    # 结束当前函数调用，并把 `numbers` 的结果交给调用方；调用方可以继续使用这个值。
    return numbers
# 定义函数 `find_min`，用于查找符合条件的数据；参数包括 numbers，函数体负责具体处理，调用处应关注传入参数和返回值。
def find_min(numbers):
    # 判断条件 `len(numbers) == 0`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    if len(numbers) == 0:
        raise ValueError("列表不能为空")
    # 通过下标/键读取 `numbers[0]` 指定的数据，并保存到 `min_number`；使用前需保证索引或键有效。
    min_number = numbers[0]
    # 遍历 `numbers[1:]`，每轮把当前元素赋给 `number`，再执行循环体；确认容器内容与循环次数是否符合预期。
    for number in numbers[1:]:
        # 判断条件 `number < min_number`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if number < min_number:
            # 把表达式 `number` 的计算结果保存到 `min_number`；右侧是数据来源，左侧是接收结果的变量。
            min_number = number
    # 结束当前函数调用，并把 `min_number` 的结果交给调用方；调用方可以继续使用这个值。
    return min_number
# 定义函数 `read_text_file`；参数为空，函数体负责具体处理，调用处应关注传入参数和返回值。
def read_text_file():
    # 进入上下文管理器；例如 `with open(...)` 能在代码块结束后自动关闭文件，减少资源未释放的风险。
    with open(TEXT_FILE, "r", encoding="utf-8") as file:
        # 把表达式 `file.read()` 的计算结果保存到 `content`；右侧是数据来源，左侧是接收结果的变量。
        content = file.read()
    # 结束当前函数调用，并把 `content` 的结果交给调用方；调用方可以继续使用这个值。
    return content
# 定义函数 `save_students_to_json`；参数包括 students，函数体负责具体处理，调用处应关注传入参数和返回值。
def save_students_to_json(students):
    # 进入上下文管理器；例如 `with open(...)` 能在代码块结束后自动关闭文件，减少资源未释放的风险。
    with open(JSON_FILE, "w", encoding="utf-8") as file:
        # 把 Python 数据编码为 JSON 并写入目标文件对象；确认数据可序列化且路径正确。
        json.dump(students, file, ensure_ascii=False, indent=4)
# 创建列表 `scores` 并放入初始元素；列表有顺序且可修改，元素下标从 0 开始。
scores = [85, 92, 78, 90, 88]
# 调用函数 `calculate_scores()`，把它返回的结果保存到 `(maximum, minmum, average, pass_count)`；函数具体做什么要看函数定义。
maximum,minmum,average,pass_count = calculate_scores(scores)
# 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
print("最高分：", maximum)
# 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
print("最低分：", minmum)
# 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
print("平均分：", average)
# 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
print("及格人数：", pass_count)
# 调用函数 `read_text_file()`，把它返回的结果保存到 `text_content`；函数具体做什么要看函数定义。
text_content = read_text_file()
# 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
print(text_content)
# 创建列表 `student` 并放入初始元素；列表有顺序且可修改，元素下标从 0 开始。
student = [{"姓名": "张三", "年龄": 18, "成绩": 90}, {"姓名": "李四", "年龄": 19, "成绩": 85}, {"姓名": "王五", "年龄": 20, "成绩": 80}]
# 调用 `save_students_to_json` 执行操作；如果它有返回值而这里没有接收，通常表示代码只需要它产生的副作用。
save_students_to_json(student)
# 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
print(f"\n学生信息已保存到{JSON_FILE}")
