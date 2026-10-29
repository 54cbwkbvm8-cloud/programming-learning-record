# 文件用途：本文件围绕“四道基础题 输入版 逐行注释”展开。
# 核心知识点：列表与字典、字符串、函数、循环、分支判断、文件读写、异常处理。
# 基本流程：根据代码中的输入或初始数据，按条件、循环和函数调用完成处理，再输出结果。
# 输入：从标准输入读取数据或由调用者传入参数。输出：通过控制台打印处理结果。 文件操作：代码中包含文件读写相关逻辑，运行时需留意路径、权限和文件内容。
# 阅读提醒：检查列表/序列的下标范围、字符串处理结果及空数据情况。 异常分支是否覆盖所有错误情况，应结合具体输入和运行环境判断。
# 模块依赖：json, pathlib。
# 导入 json 模块，用它把学生信息保存为 JSON 文件。  # 导入 JSON 工具
import json  # 使用 json 模块中的功能

# 从 pathlib 模块导入 Path 类，用它处理文件路径。  # 导入路径工具
from pathlib import Path  # 使用 Path 创建和组合路径

# 取得当前 Python 文件所在的文件夹，并转换成绝对路径。  # 找到脚本所在目录
BASE_DIR = Path(__file__).resolve().parent  # __file__ 表示当前脚本文件名

# 组合出学生成绩示例文本文件的完整路径。  # 准备文本文件路径
TEXT_FILE = BASE_DIR / "学生成绩示例.txt"  # 斜杠用于连接目录和文件名

# 组合出学生信息 JSON 文件的完整路径。  # 准备 JSON 文件路径
JSON_FILE = BASE_DIR / "学生信息.json"  # 保存 JSON 时使用这个路径


# 定义求最大值的函数，numbers 用来接收数字列表。  # 定义 find_max 函数
def find_max(numbers):  # numbers 是函数参数
    # 判断列表是否为空，避免后面访问 numbers[0] 时报错。  # 检查空列表
    if len(numbers) == 0:  # len(numbers) 得到列表元素个数
        # 如果列表为空，就主动抛出错误并给出中文提示。  # 报告错误
        raise ValueError("列表不能为空")  # raise 用于主动抛出异常

    # 先把列表的第一个数字当作当前最大值。  # 设置最大值初始值
    max_number = numbers[0]  # 列表下标从 0 开始

    # 逐个取出列表中的数字进行比较。  # 遍历所有数字
    for number in numbers:  # 每次循环，number 是当前数字
        # 判断当前数字是否比目前记录的最大值更大。  # 比较两个数字
        if number > max_number:  # > 表示大于
            # 如果当前数字更大，就更新最大值的记录。  # 更新最大值
            max_number = number  # 把当前数字保存为最大值

    # 把找到的最大值返回给调用函数的位置。  # 返回最大值
    return max_number  # return 表示函数的输出结果


# 定义求最小值的函数，numbers 用来接收数字列表。  # 定义 find_min 函数
def find_min(numbers):  # numbers 是函数参数
    # 判断列表是否为空，避免后面访问 numbers[0] 时报错。  # 检查空列表
    if len(numbers) == 0:  # len(numbers) 得到列表元素个数
        # 如果列表为空，就主动抛出错误并给出中文提示。  # 报告错误
        raise ValueError("列表不能为空")  # 阻止函数继续向下执行

    # 先把列表的第一个数字当作当前最小值。  # 设置最小值初始值
    min_number = numbers[0]  # 列表的第一个元素下标是 0

    # 逐个取出列表中的数字进行比较。  # 遍历所有数字
    for number in numbers:  # 每次循环，number 是当前数字
        # 判断当前数字是否比目前记录的最小值更小。  # 比较两个数字
        if number < min_number:  # < 表示小于
            # 如果当前数字更小，就更新最小值的记录。  # 更新最小值
            min_number = number  # 把当前数字保存为最小值

    # 把找到的最小值返回给调用函数的位置。  # 返回最小值
    return min_number  # return 表示函数的输出结果


# 定义计算平均值的函数，注意 average 的正确拼写。  # 定义 average 函数
def average(numbers):  # numbers 用来接收数字列表
    # 判断列表是否为空，因为空列表不能计算平均数。  # 检查空列表
    if len(numbers) == 0:  # len(numbers) 得到列表元素个数
        # 如果列表为空，就主动抛出错误。  # 报告错误
        raise ValueError("列表不能为空")  # 停止执行并提示原因

    # 创建总和变量，并让它的初始值等于 0。  # 设置总和初始值
    total = 0  # total 用来保存所有数字相加的结果

    # 逐个取出列表中的数字。  # 遍历所有数字
    for number in numbers:  # 每次循环取出一个数字
        # 把当前数字加到总和变量中。  # 累加当前数字
        total += number  # += 表示把右边的值加到左边变量中

    # 用数字总和除以数字个数，并把结果返回。  # 计算并返回平均值
    return total / len(numbers)  # / 表示普通除法，结果是小数


# 定义统计及格人数的函数，不能用 pass 作为函数名。  # 定义 count_pass 函数
def count_pass(numbers):  # numbers 用来接收成绩列表
    # 判断列表是否为空，避免返回没有意义的统计结果。  # 检查空列表
    if len(numbers) == 0:  # len(numbers) 得到列表元素个数
        # 如果列表为空，就主动抛出错误。  # 报告错误
        raise ValueError("列表不能为空")  # 停止执行并显示提示

    # 创建及格人数计数器，初始人数为 0。  # 设置及格人数初始值
    pass_count = 0  # pass_count 专门保存及格人数

    # 逐个取出每一个成绩进行检查。  # 遍历所有成绩
    for number in numbers:  # 每次循环取出一个成绩
        # 判断当前成绩是否大于或等于 60 分。  # 判断是否及格
        if number >= 60:  # >= 表示大于或等于
            # 如果成绩及格，就让及格人数增加 1。  # 累加及格人数
            pass_count += 1  # += 1 表示把变量原来的值加 1

    # 把统计出的及格人数返回给调用函数的位置。  # 返回及格人数
    return pass_count  # return 表示函数的输出结果


# 创建空列表，准备保存用户输入的 5 个数字。  # 创建数字列表
numbers = []  # [] 表示一个空列表

# 循环 5 次，让用户连续输入 5 个数字。  # 开始输入循环
for index in range(5):  # range(5) 会依次产生 0、1、2、3、4
    # 显示提示，把用户输入的文字转换成整数并保存到 number。  # 读取用户输入
    number = int(input(f"请输入第 {index + 1} 个数字："))  # int 把文字转换成整数

    # 把这次输入的数字添加到 numbers 列表末尾。  # 保存当前数字
    numbers.append(number)  # append 表示向列表末尾增加一个元素


# 调用自定义函数，计算用户输入数字中的最大值。  # 计算最大值
maximum = find_max(numbers)  # 把函数返回值保存到 maximum

# 调用自定义函数，计算用户输入数字中的最小值。  # 计算最小值
minimum = find_min(numbers)  # 把函数返回值保存到 minimum

# 调用自定义函数，计算用户输入数字的平均值。  # 计算平均值
average_value = average(numbers)  # 保存平均值，避免和函数名冲突

# 调用自定义函数，统计大于或等于 60 的数字个数。  # 统计及格人数
pass_count = count_pass(numbers)  # 把函数返回值保存到 pass_count

# 输出最大值。  # 显示最大值
print("最大值是：", maximum)  # 逗号可以连接文字和变量

# 输出最小值。  # 显示最小值
print("最小值是：", minimum)  # 显示 find_min 返回的结果

# 输出平均值，并保留两位小数。  # 显示平均值
print(f"平均值是：{average_value:.2f}")  # .2f 表示保留两位小数

# 输出及格人数。  # 显示及格人数
print("及格人数是：", pass_count)  # 显示 count_pass 返回的结果


# 定义读取文本文件的函数，file_path 是文件路径参数。  # 定义 read_text_file 函数
def read_text_file(file_path):  # file_path 用来接收要读取的文件路径
    # 以只读模式打开文本文件，并用 UTF-8 编码读取中文。  # 打开文件
    with open(file_path, "r", encoding="utf-8") as file:  # with 会在用完后自动关闭文件
        # 读取文件中的全部内容，并保存到 content 变量中。  # 读取文件内容
        content = file.read()  # read() 的结果是字符串

    # 把读取到的全部文字返回给调用函数的位置。  # 返回文件内容
    return content  # 函数外可以用变量接收这个结果


# 定义保存学生信息的函数，students 是学生列表。  # 定义 save_students_to_json 函数
def save_students_to_json(students, file_path):  # file_path 是保存路径
    # 以写入模式打开 JSON 文件，文件不存在时会自动创建。  # 打开 JSON 文件
    with open(file_path, "w", encoding="utf-8") as file:  # w 表示写入模式
        # 把学生列表转换成 JSON 并写入文件，同时保留中文。  # 写入 JSON 数据
        json.dump(students, file, ensure_ascii=False, indent=4)  # indent 让内容更易读


# 调用函数读取同目录下的学生成绩示例文本。  # 读取题目 3 的文件
text_content = read_text_file(TEXT_FILE)  # 把读取结果保存到 text_content

# 输出换行符和题目 3 的标题。  # 显示题目 3 标题
print("\n题目 3：读取文本文件")  # \n 表示先换一行

# 输出从文本文件中读取到的全部内容。  # 显示文件内容
print(text_content)  # 打印文件中的文字


# 创建学生信息列表，每个学生用一个字典表示。  # 准备题目 4 的学生数据
students = [  # students 是一个列表
    {  # 第一个学生字典开始
        "姓名": "小明",  # 保存小明姓名
        "年龄": 18,  # 保存小明年龄
        "成绩": 92.5,  # 保存小明成绩
    },  # 第一个学生字典结束
    {  # 第二个学生字典开始
        "姓名": "小红",  # 保存小红姓名
        "年龄": 19,  # 保存小红年龄
        "成绩": 88.0,  # 保存小红成绩
    },  # 第二个学生字典结束
    {  # 第三个学生字典开始
        "姓名": "小刚",  # 保存小刚姓名
        "年龄": 18,  # 保存小刚年龄
        "成绩": 76.5,  # 保存小刚成绩
    },  # 第三个学生字典结束
]  # 学生信息列表结束

# 调用保存函数，把学生信息保存到 JSON 文件。  # 保存题目 4 的数据
save_students_to_json(students, JSON_FILE)  # 传入学生列表和保存路径

# 输出换行符、完成提示和 JSON 文件位置。  # 显示保存结果
print(f"\n题目 4：学生信息已经保存到：{JSON_FILE}")  # f 字符串可以显示变量值
