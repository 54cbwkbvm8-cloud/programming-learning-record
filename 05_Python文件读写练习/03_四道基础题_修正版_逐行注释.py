# 导入 json 模块，用它把学生信息保存成 JSON 文件。  # 导入 JSON 工具
import json  # 使用 json 模块提供的功能

# 导入 Path，用它准确定位当前 Python 文件旁边的文本和 JSON 文件。  # 导入路径工具
from pathlib import Path  # 使用 Path 类处理文件路径

# 取得当前 Python 文件所在的文件夹。  # 取得脚本目录
BASE_DIR = Path(__file__).resolve().parent  # __file__ 表示当前脚本文件

# 组合出示例文本文件的完整路径。  # 准备文本文件路径
TEXT_FILE = BASE_DIR / "学生成绩示例.txt"  # 文本文件与脚本放在同一目录

# 组合出学生信息 JSON 文件的完整路径。  # 准备 JSON 文件路径
JSON_FILE = BASE_DIR / "学生信息.json"  # JSON 文件与脚本放在同一目录


# 定义查找最大值的函数。  # 定义 find_max 函数
def find_max(numbers):  # numbers 接收一个数字列表
    # 判断列表是否为空。  # 检查空列表
    if len(numbers) == 0:  # len(numbers) 取得列表元素个数
        # 空列表没有最大值，所以主动报告错误。  # 抛出明确错误
        raise ValueError("列表不能为空")  # ValueError 表示数据不合适

    # 暂时把列表的第一个数字当成最大值。  # 设置初始最大值
    max_number = numbers[0]  # 列表下标从 0 开始

    # 依次检查列表中的剩余数字。  # 遍历剩余数字
    for number in numbers[1:]:  # numbers[1:] 表示从第二个元素到末尾
        # 判断当前数字是否比目前最大值更大。  # 比较当前数字
        if number > max_number:  # > 表示“大于”
            # 当前数字更大时，更新最大值。  # 更新最大值
            max_number = number  # 保存当前更大的数字

    # 把找到的最大值返回给调用函数的地方。  # 返回最大值
    return max_number  # return 表示函数执行结果


# 定义查找最小值的函数。  # 定义 find_min 函数
def find_min(numbers):  # numbers 接收一个数字列表
    # 判断列表是否为空。  # 检查空列表
    if len(numbers) == 0:  # 空列表无法查找最小值
        # 空列表没有最小值，所以主动报告错误。  # 抛出明确错误
        raise ValueError("列表不能为空")  # 错误信息说明不能传入空列表

    # 暂时把列表的第一个数字当成最小值。  # 设置初始最小值
    min_number = numbers[0]  # 使用列表第一个元素作为起点

    # 依次检查列表中的剩余数字。  # 遍历剩余数字
    for number in numbers[1:]:  # 从第二个元素开始比较
        # 判断当前数字是否比目前最小值更小。  # 比较当前数字
        if number < min_number:  # < 表示“小于”
            # 当前数字更小时，更新最小值。  # 更新最小值
            min_number = number  # 保存当前更小的数字

    # 把找到的最小值返回给调用函数的地方。  # 返回最小值
    return min_number  # 返回最终找到的最小值


# 定义成绩统计函数，把最大值、最小值、平均值和及格人数组合起来。  # 定义统计函数
def calculate_scores(scores):  # 【新增 1】补齐原代码中缺失的统计函数定义
    # 调用 find_max 函数取得最高分。  # 计算最高分
    maximum = find_max(scores)  # maximum 表示最高分

    # 调用 find_min 函数取得最低分。  # 计算最低分
    minimum = find_min(scores)  # minimum 表示最低分，注意拼写

    # 使用 sum 求成绩总和，再除以成绩数量，得到平均值。  # 计算平均分
    average = sum(scores) / len(scores)  # / 表示普通除法

    # 及格人数从 0 开始统计。  # 初始化及格人数
    pass_count = 0  # 还没有统计到及格学生

    # 逐个检查每一个成绩。  # 遍历全部成绩
    for score in scores:  # 每循环一次，score 保存一个成绩
        # 判断当前成绩是否达到 60 分。  # 判断是否及格
        if score >= 60:  # >= 表示“大于或等于”
            # 成绩及格时，及格人数增加 1。  # 累加及格人数
            pass_count = pass_count + 1  # 把原有人数加 1 后保存回去

    # 【新增 1】统计函数到这里结束，下面返回四个结果。  # 返回统计结果
    return maximum, minimum, average, pass_count  # 逗号隔开后形成元组


# 定义读取文本文件的函数。  # 定义 read_text_file 函数
def read_text_file():  # 这个函数不需要接收参数
    # 使用只读模式打开文本文件，并指定 UTF-8 编码。  # 打开文本文件
    with open(TEXT_FILE, "r", encoding="utf-8") as file:  # with 会自动关闭文件
        # 一次读取文件中的全部文字。  # 读取全部内容
        content = file.read()  # read() 返回文件中的字符串

    # 把读取到的文字返回给调用函数的地方。  # 返回文件内容
    return content  # 函数外部可以接收这段文字


# 定义把学生信息保存到 JSON 文件的函数。  # 定义保存 JSON 函数
def save_students_to_json(students):  # students 接收学生信息列表
    # 使用写入模式打开 JSON 文件，并指定 UTF-8 编码。  # 打开 JSON 文件
    with open(JSON_FILE, "w", encoding="utf-8") as file:  # 文件不存在时会自动创建
        # 把学生列表转换成 JSON 格式并写入文件。  # 写入 JSON 内容
        json.dump(students, file, ensure_ascii=False, indent=4)  # 保留中文并缩进 4 格


# 准备需要统计的成绩列表。  # 创建成绩列表
scores = [85, 92, 78, 90, 88]  # 方括号表示列表

# 调用统计函数，并用四个变量按顺序接收结果。  # 【修改 1】
maximum, minimum, average, pass_count = calculate_scores(scores)  # 原代码这里是 minmum，现已改为 minimum

# 输出最高分。  # 显示最高分
print("最高分：", maximum)  # 逗号会分隔并输出两个内容

# 输出最低分。  # 【修改 2】
print("最低分：", minimum)  # 原代码这里是 minmum，现已改为 minimum

# 输出平均分。  # 显示平均分
print("平均分：", average)  # 当前平均分是 86.6

# 输出及格人数。  # 显示及格人数
print("及格人数：", pass_count)  # 当前所有成绩都达到 60 分

# 调用读取函数，取得示例文本中的内容。  # 调用读取函数
text_content = read_text_file()  # 把返回内容保存到变量

# 显示读取到的文本内容。  # 输出文本内容
print(text_content)  # print 会把文件文字显示到屏幕

# 创建要保存的学生信息列表，列表中每个字典代表一个学生。  # 【修改 3】
students = [{"姓名": "张三", "年龄": 18, "成绩": 90}, {"姓名": "李四", "年龄": 19, "成绩": 85}, {"姓名": "王五", "年龄": 20, "成绩": 80}]  # student 改为 students，表示列表更清楚

# 调用保存函数，把学生列表写入 JSON 文件。  # 调用保存函数
save_students_to_json(students)  # 原代码这里是 student，现在统一使用 students

# 输出保存成功信息和 JSON 文件的完整路径。  # 显示保存结果
print(f"\n学生信息已保存到{JSON_FILE}")  # \n 表示先换一行
