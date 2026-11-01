import json

from pathlib import Path

BASE_DIR = Path(__file__).resolve().parent

TEXT_FILE = BASE_DIR / "学生成绩示例.txt"

JSON_FILE = BASE_DIR / "学生信息.json"


def find_max(numbers):
    if len(numbers) == 0:
        raise ValueError("列表不能为空")

    max_number = numbers[0]

    for number in numbers:
        if number > max_number:
            max_number = number

    return max_number


def find_min(numbers):
    if len(numbers) == 0:
        raise ValueError("列表不能为空")

    min_number = numbers[0]

    for number in numbers:
        if number < min_number:
            min_number = number

    return min_number


def average(numbers):
    if len(numbers) == 0:
        raise ValueError("列表不能为空")

    total = 0

    for number in numbers:
        total += number

    return total / len(numbers)


def count_pass(numbers):
    if len(numbers) == 0:
        raise ValueError("列表不能为空")

    pass_count = 0

    for number in numbers:
        if number >= 60:
            pass_count += 1

    return pass_count


numbers = []

for index in range(5):
    number = int(input(f"请输入第 {index + 1} 个数字："))

    numbers.append(number)


maximum = find_max(numbers)

minimum = find_min(numbers)

average_value = average(numbers)

pass_count = count_pass(numbers)

print("最大值是：", maximum)

print("最小值是：", minimum)

print(f"平均值是：{average_value:.2f}")

print("及格人数是：", pass_count)


def read_text_file(file_path):
    with open(file_path, "r", encoding="utf-8") as file:
        content = file.read()

    return content


def save_students_to_json(students, file_path):
    with open(file_path, "w", encoding="utf-8") as file:
        json.dump(students, file, ensure_ascii=False, indent=4)


text_content = read_text_file(TEXT_FILE)

print("\n题目 3：读取文本文件")

print(text_content)


students = [
    {
        "姓名": "小明",
        "年龄": 18,
        "成绩": 92.5,
    },
    {
        "姓名": "小红",
        "年龄": 19,
        "成绩": 88.0,
    },
    {
        "姓名": "小刚",
        "年龄": 18,
        "成绩": 76.5,
    },
]

save_students_to_json(students, JSON_FILE)

print(f"\n题目 4：学生信息已经保存到：{JSON_FILE}")
# # ====================================================================
# # 文件名：04_2026-09-13_四道基础题_输入版_逐行注释.py
# # 作用：四道基础题的"输入版"：
# #       题目 1、2：从键盘输入 5 个整数，用函数求最大值、最小值、平均值、及格人数（≥ 60 分）
# #       题目 3：读取同文件夹里的文本文件"学生成绩示例.txt"并输出
# #       题目 4：把学生信息保存成 JSON 文件"学生信息.json"
# #       ⚠ 注意：运行这个程序会覆盖同文件夹里原来的"学生信息.json"。
# #
# # 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
# #   运行：python3 "04_2026-09-13_四道基础题_输入版_逐行注释.py"
# #   说明：Python 是边读边执行的，不需要先编译。
# # ====================================================================
#
#
# # --------------------------------------------------------------------
# # 第一部分：引入工具，设置文件路径
# # --------------------------------------------------------------------
# # import json：引入 Python 自带的 json 工具箱，用来保存 / 读取 JSON 文件（一种常见的、人也能读的数据格式）。
# import json
#
# # Path 是"路径"工具，用来方便地表示文件和文件夹的位置。
# from pathlib import Path
#
# # __file__ 是"当前这个 .py 文件的路径"；.resolve() 变成完整路径；.parent 取它所在的文件夹。
# # 所以 BASE_DIR 就是"这个程序所在的文件夹"，不管你在哪里运行程序，都能找到和它放在一起的数据文件。
# BASE_DIR = Path(__file__).resolve().parent
#
# # 路径对象可以用 / 拼接："文件夹 / 文件名"就得到文件的完整路径。全部大写的变量名，是"常量"的习惯写法。
# TEXT_FILE = BASE_DIR / "学生成绩示例.txt"
#
# # 同样地，JSON_FILE 是保存 JSON 的文件路径。
# JSON_FILE = BASE_DIR / "学生信息.json"
#
#
# # --------------------------------------------------------------------
# # 第二部分：函数 —— 求最大值
# # --------------------------------------------------------------------
# # def 函数名(参数): 用来定义函数 = 一段有名字、可以反复使用的代码。numbers 是参数：调用时传进来的数字列表。
# def find_max(numbers):
#     # len(列表) 返回元素个数。是 0 说明列表为空，没有最大值可言。
#     if len(numbers) == 0:
#         # raise 的意思是"主动抛出一个错误"：遇到不合理的数据，直接报错停下。
#         raise ValueError("列表不能为空")
#
#     # "打擂台"：先假设第一个数最大（下标从 0 开始）。
#     max_number = numbers[0]
#
#     # for 变量 in 列表: 让 number 依次取列表里的每一个元素，每取一个，把缩进的代码执行一遍。
#     for number in numbers:
#         # 比当前最大值大，就更新。
#         if number > max_number:
#             max_number = number
#
#     # return 把结果交回给调用者。
#     return max_number
#
#
# # --------------------------------------------------------------------
# # 第三部分：函数 —— 求最小值
# # --------------------------------------------------------------------
# # 和 find_max 对称：先假设第一个最小，遇到更小的就更新。
# def find_min(numbers):
#     if len(numbers) == 0:
#         raise ValueError("列表不能为空")
#
#     min_number = numbers[0]
#
#     for number in numbers:
#         if number < min_number:
#             min_number = number
#
#     return min_number
#
#
# # --------------------------------------------------------------------
# # 第四部分：函数 —— 求平均值
# # --------------------------------------------------------------------
# def average(numbers):
#     if len(numbers) == 0:
#         raise ValueError("列表不能为空")
#
#     # total 存总和，从 0 开始累加。
#     total = 0
#
#     for number in numbers:
#         # += 是"加上再赋值"：total += number 等价于 total = total + number。
#         total += number
#
#     # 总和 ÷ 个数 = 平均值。/ 是除法，结果是小数。
#     return total / len(numbers)
#
#
# # --------------------------------------------------------------------
# # 第五部分：函数 —— 统计及格人数
# # --------------------------------------------------------------------
# def count_pass(numbers):
#     if len(numbers) == 0:
#         raise ValueError("列表不能为空")
#
#     # pass_count 是"计数器"，从 0 开始。
#     pass_count = 0
#
#     for number in numbers:
#         # >= 是"大于或等于"：60 分及以上算及格。
#         if number >= 60:
#             # 及格就让计数器加 1。
#             pass_count += 1
#
#     return pass_count
#
#
# # --------------------------------------------------------------------
# # 第六部分：主程序 —— 输入 5 个数，求出统计结果
# # --------------------------------------------------------------------
# # [] 是"空列表"，numbers 用来存输入的 5 个整数。
# numbers = []
#
# # range(5) 生成 0、1、2、3、4，所以循环执行 5 次。
# for index in range(5):
#     # input(提示语)：显示提示语，等用户输入并按回车，得到字符串；int(...) 把它转成整数。输入的不是整数会报错中断。
#     # f"..." 叫 f-string（格式化字符串）：{index + 1} 会被替换成值：index 从 0 开始，显示给人看的编号从 1 开始，所以加 1。
#     number = int(input(f"请输入第 {index + 1} 个数字："))
#
#     # 列表.append(元素)：把元素添加到列表末尾。
#     numbers.append(number)
#
#
# # 调用上面写好的函数，把返回值存进变量。函数可以反复调用，不用重复写代码。
# maximum = find_max(numbers)
#
# minimum = find_min(numbers)
#
# # 变量不能叫 average，否则会把上面的函数 average "盖住"，所以换了个名字 average_value。
# average_value = average(numbers)
#
# pass_count = count_pass(numbers)
#
# # print 里用逗号隔开多个内容，会依次输出，中间自动加一个空格。
# print("最大值是：", maximum)
#
# print("最小值是：", minimum)
#
# # {average_value:.2f}：冒号后面是格式，.2f 表示"保留 2 位小数"。例：74.2 显示成 74.20。
# print(f"平均值是：{average_value:.2f}")
#
# # 例：85、92、56、78、60 里，56 不及格，其余 4 个及格，所以是 4。
# print("及格人数是：", pass_count)
#
#
# # --------------------------------------------------------------------
# # 第七部分：函数 —— 读文本文件
# # --------------------------------------------------------------------
# # 参数 file_path 是要读的文件路径。
# def read_text_file(file_path):
#     # open(文件路径, 模式, encoding=...)：打开文件。"r" 表示只读（read）；encoding="utf-8" 指定文字编码，读写中文必须写对，否则会乱码。
#     # with ... as file: 是"上下文管理"写法：代码块结束后，Python 会自动关闭文件，不用自己写 close()。
#     with open(file_path, "r", encoding="utf-8") as file:
#         # file.read() 一次读出文件的全部内容，返回一个字符串。
#         content = file.read()
#
#     return content
#
#
# # --------------------------------------------------------------------
# # 第八部分：函数 —— 保存 JSON 文件
# # --------------------------------------------------------------------
# def save_students_to_json(students, file_path):
#     # "w" 表示写入（write）：文件不存在就新建，已经存在就清空后重写（所以会覆盖原来的内容）。
#     with open(file_path, "w", encoding="utf-8") as file:
#         # json.dump(数据, 文件, ...)：把数据（列表、字典等）写进文件，格式是 JSON。
#         # ensure_ascii=False：中文原样写入，不转成 \u 开头的编码；indent=4：每一层缩进 4 个空格，方便阅读。
#         json.dump(students, file, ensure_ascii=False, indent=4)
#
#
# # --------------------------------------------------------------------
# # 第九部分：主程序 —— 读文件、保存文件
# # --------------------------------------------------------------------
# # 调用函数，读出文本文件的内容。
# text_content = read_text_file(TEXT_FILE)
#
# # \n 表示换行。
# print("\n题目 3：读取文本文件")
#
# # 输出文件内容。文件末尾本来就有换行，print 又会加一个换行，所以后面会多出一个空行。
# print(text_content)
#
#
# # 列表里放字典：每个字典 {"键": 值, ...} 用"键: 值"成对保存一个学生的信息。这个列表包含 3 个学生。
# # 写成多行是为了好读；每一项后面多写一个逗号（"尾逗号"）是允许的。
# students = [
#     {
#         "姓名": "小明",
#         "年龄": 18,
#         "成绩": 92.5,
#     },
#     {
#         "姓名": "小红",
#         "年龄": 19,
#         "成绩": 88.0,
#     },
#     {
#         "姓名": "小刚",
#         "年龄": 18,
#         "成绩": 76.5,
#     },
# ]
#
# # 调用函数，把学生列表保存成 JSON 文件。
# save_students_to_json(students, JSON_FILE)
#
# # f"..." 叫 f-string：里面的 {} 会被替换成变量的值，这里显示保存的文件路径。
# print(f"\n题目 4：学生信息已经保存到：{JSON_FILE}")
#
# # ====================================================================
# # 测试时依次输入（每行输完按一次回车）：
# #   85
# #   92
# #   56
# #   78
# #   60
# #
# # 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
# # （路径因电脑而异，<本文件所在文件夹> 指这个 .py 文件所在的文件夹）
# #   请输入第 1 个数字：请输入第 2 个数字：请输入第 3 个数字：请输入第 4 个数字：请输入第 5 个数字：最大值是： 92
# #   最小值是： 56
# #   平均值是：74.20
# #   及格人数是： 4
# #
# #   题目 3：读取文本文件
# #   张三 85
# #   李四 92
# #   王五 76
# #   赵六 58
# #
# #
# #   题目 4：学生信息已经保存到：<本文件所在文件夹>/学生信息.json
# # ====================================================================
