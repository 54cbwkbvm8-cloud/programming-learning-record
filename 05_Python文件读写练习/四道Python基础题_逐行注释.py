# 导入 json 模块，用它把 Python 数据保存成 JSON 格式。  # 导入 JSON 工具
import json  # 使用 json 模块提供的功能

# 从 pathlib 模块导入 Path，用它表示文件和文件夹路径。  # 导入路径工具
from pathlib import Path  # 使用 Path 类处理路径

# 找到当前这个 Python 文件所在的文件夹，并转换成绝对路径。  # 取得脚本目录
BASE_DIR = Path(__file__).resolve().parent  # __file__ 是当前脚本的文件名

# 组合出“学生成绩示例.txt”的完整路径。  # 准备文本文件路径
TEXT_FILE = BASE_DIR / "学生成绩示例.txt"  # / 可以连接文件夹和文件名

# 组合出“学生信息.json”的完整路径。  # 准备 JSON 文件路径
JSON_FILE = BASE_DIR / "学生信息.json"  # 保存 JSON 时使用这个路径


# 定义题目 1 的函数，numbers 表示调用函数时传进来的列表。  # 定义求最大值函数
def find_max(numbers):  # numbers 是函数的形式参数
    # 判断列表中是否一个元素都没有。  # 检查空列表
    if len(numbers) == 0:  # len(numbers) 得到列表长度
        # 如果列表为空，就主动报错，防止后面访问第一个元素时出错。  # 报告错误
        raise ValueError("列表不能为空")  # ValueError 表示数值或数据不合适

    # 先把第一个数字当成目前看到的最大值。  # 设置初始最大值
    max_number = numbers[0]  # 列表下标从 0 开始

    # 从第二个数字开始，逐个取出列表中的数字。  # 遍历剩余数字
    for number in numbers[1:]:  # numbers[1:] 表示从第 2 个元素到末尾
        # 判断当前数字是否比已经记录的最大值更大。  # 比较大小
        if number > max_number:  # > 表示“大于”
            # 如果更大，就更新目前记录的最大值。  # 更新最大值
            max_number = number  # 把更大的数字保存起来

    # 把找到的最大值交还给调用函数的地方。  # 返回结果
    return max_number  # return 表示函数的结果


# 定义题目 2 的函数，scores 表示成绩列表。  # 定义成绩统计函数
def count_scores(scores):  # scores 是函数的形式参数
    # 调用题目 1 的函数，找出成绩最大值。  # 计算最大值
    maximum = find_max(scores)  # 把函数结果保存到 maximum

    # 使用 Python 内置的 min 函数找出最小值。  # 计算最小值
    minimum = min(scores)  # min 会返回列表中的最小元素

    # 计算所有成绩的总和，再除以成绩个数，得到平均值。  # 计算平均值
    average = sum(scores) / len(scores)  # / 表示普通除法

    # 及格人数一开始是 0 人。  # 设置及格人数初始值
    pass_count = 0  # 计数器从 0 开始

    # 逐个取出每一个成绩。  # 遍历全部成绩
    for score in scores:  # 每循环一次，score 就是其中一个成绩
        # 判断这个成绩是否大于或等于 60 分。  # 判断是否及格
        if score >= 60:  # >= 表示“大于或等于”
            # 如果及格，就让及格人数增加 1。  # 增加及格人数
            pass_count = pass_count + 1  # 把原人数加 1 后保存回去

    # 一次返回四个统计结果，外层会用四个变量分别接收。  # 返回全部统计结果
    return maximum, minimum, average, pass_count  # 逗号隔开后形成元组


# 定义题目 3 的函数，file_path 表示要读取的文件路径。  # 定义读取文本函数
def read_text_file(file_path):  # file_path 是文件所在位置
    # 打开文件："r" 表示只读，encoding="utf-8" 表示按 UTF-8 编码读取中文。  # 打开文本文件
    with open(file_path, "r", encoding="utf-8") as file:  # with 会在用完后自动关闭文件
        # 读取文件里的全部文字，并保存到 content 变量中。  # 读取全部内容
        content = file.read()  # read() 返回整个文件的字符串

    # 把读取到的文字返回给调用函数的地方。  # 返回文件内容
    return content  # 函数外部可以接收这段文字


# 定义题目 4 的函数，students 是学生列表，file_path 是保存位置。  # 定义保存 JSON 函数
def save_students_to_json(students, file_path):  # students 保存多个学生字典
    # 以写入模式打开 JSON 文件，"w" 表示写入；文件不存在时会自动创建。  # 打开 JSON 文件
    with open(file_path, "w", encoding="utf-8") as file:  # 每次运行都会重写这个文件
        # 把 students 数据写成 JSON；ensure_ascii=False 可以保留中文。  # 写入 JSON 内容
        json.dump(students, file, ensure_ascii=False, indent=4)  # indent=4 让内容更易读


# 准备题目 2 要统计的成绩列表。  # 创建成绩列表
scores = [78, 90, 45, 88, 92, 56]  # 方括号表示列表

# 调用统计函数，并用四个变量接收返回的四个结果。  # 调用统计函数
maximum, minimum, average, pass_count = count_scores(scores)  # 左边按顺序接收四个结果

# 显示题目 2 标题。  # 输出题目 2 标题
print("题目 2：成绩统计")  # print 会把括号里的内容显示到屏幕

# 显示最大值。  # 输出最大值
print(f"最大值：{maximum}")  # f 字符串可以把变量放进大括号中

# 显示最小值。  # 输出最小值
print(f"最小值：{minimum}")  # 花括号里的变量会被替换成实际数值

# 显示平均值，并保留两位小数。  # 输出平均值
print(f"平均值：{average:.2f}")  # .2f 表示保留两位小数

# 显示及格人数。  # 输出及格人数
print(f"及格人数：{pass_count}")  # 把计数结果显示出来

# 显示题目 3 标题。  # 输出题目 3 标题
print("\n题目 3：读取文本文件")  # \n 表示先换一行

# 调用读取函数，读取同目录下的示例文本文件。  # 调用读取文件函数
text_content = read_text_file(TEXT_FILE)  # 把读取结果保存到 text_content

# 显示从文件中读到的全部内容。  # 输出文件内容
print(text_content)  # 打印文本字符串

# 准备题目 4 要保存的学生信息列表。  # 创建学生信息列表
students = [  # 列表里有多个学生字典
    {  # 创建第一个学生字典
        "姓名": "小明",  # 保存姓名
        "年龄": 18,  # 保存年龄
        "成绩": 92.5,  # 保存成绩
    },  # 第一个学生结束
    {  # 创建第二个学生字典
        "姓名": "小红",  # 保存姓名
        "年龄": 19,  # 保存年龄
        "成绩": 88.0,  # 保存成绩
    },  # 第二个学生结束
    {  # 创建第三个学生字典
        "姓名": "小刚",  # 保存姓名
        "年龄": 18,  # 保存年龄
        "成绩": 76.5,  # 保存成绩
    },  # 第三个学生结束
]  # 学生列表结束

# 调用保存函数，把学生信息写入 JSON 文件。  # 调用保存 JSON 函数
save_students_to_json(students, JSON_FILE)  # 传入学生数据和保存路径

# 显示题目 4 的完成提示和保存位置。  # 输出保存成功信息
print(f"\n题目 4：学生信息已保存到 {JSON_FILE}")  # \n 表示先换行
