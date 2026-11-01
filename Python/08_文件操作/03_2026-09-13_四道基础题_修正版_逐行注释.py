import json

from pathlib import Path

BASE_DIR = Path(__file__).resolve().parent

TEXT_FILE = BASE_DIR / "学生成绩示例.txt"

JSON_FILE = BASE_DIR / "学生信息.json"


def find_max(numbers):
    if len(numbers) == 0:
        raise ValueError("列表不能为空")

    max_number = numbers[0]

    for number in numbers[1:]:
        if number > max_number:
            max_number = number

    return max_number


def find_min(numbers):
    if len(numbers) == 0:
        raise ValueError("列表不能为空")

    min_number = numbers[0]

    for number in numbers[1:]:
        if number < min_number:
            min_number = number

    return min_number


def calculate_scores(scores):
    maximum = find_max(scores)

    minimum = find_min(scores)

    average = sum(scores) / len(scores)

    pass_count = 0

    for score in scores:
        if score >= 60:
            pass_count = pass_count + 1

    return maximum, minimum, average, pass_count


def read_text_file():
    with open(TEXT_FILE, "r", encoding="utf-8") as file:
        content = file.read()

    return content


def save_students_to_json(students):
    with open(JSON_FILE, "w", encoding="utf-8") as file:
        json.dump(students, file, ensure_ascii=False, indent=4)


scores = [85, 92, 78, 90, 88]

maximum, minimum, average, pass_count = calculate_scores(scores)

print("最高分：", maximum)

print("最低分：", minimum)

print("平均分：", average)

print("及格人数：", pass_count)

text_content = read_text_file()

print(text_content)

students = [{"姓名": "张三", "年龄": 18, "成绩": 90}, {"姓名": "李四", "年龄": 19, "成绩": 85}, {"姓名": "王五", "年龄": 20, "成绩": 80}]

save_students_to_json(students)

print(f"\n学生信息已保存到{JSON_FILE}")
# # ====================================================================
# # 文件名：03_2026-09-13_四道基础题_修正版_逐行注释.py
# # 作用：01 号"文件读写复习"的修正版（把统计成绩、找最大最小值等写得更规范）：
# #       1. 用函数统计成绩：最高分、最低分、平均分、及格人数（≥ 60 分）
# #       2. 读取同文件夹里的文本文件"学生成绩示例.txt"并输出
# #       3. 把学生信息保存成 JSON 文件"学生信息.json"
# #       ⚠ 注意：运行这个程序会覆盖同文件夹里原来的"学生信息.json"。
# #
# # 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
# #   运行：python3 "03_2026-09-13_四道基础题_修正版_逐行注释.py"
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
#         # raise 的意思是"主动抛出一个错误"：遇到不合理的数据，直接报错停下，比悄悄给出错误结果更好。
#         raise ValueError("列表不能为空")
#
#     # "打擂台"：先假设第一个数最大（下标从 0 开始）。
#     max_number = numbers[0]
#
#     # numbers[1:] 叫"切片"：取从下标 1 开始到末尾的所有元素（跳过第一个，它已经当作最大值了）。
#     # for 变量 in 列表: 让 number 依次取每一个元素，每取一个，把缩进的代码执行一遍。
#     for number in numbers[1:]:
#         # 比当前最大值大，就更新。
#         if number > max_number:
#             max_number = number
#
#     # return 把结果交回给调用者。（这个版本返回的是 max_number，是对的。）
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
#     for number in numbers[1:]:
#         if number < min_number:
#             min_number = number
#
#     return min_number
#
#
# # --------------------------------------------------------------------
# # 第四部分：函数 —— 统计成绩
# # --------------------------------------------------------------------
# # 统计成绩，一次返回 4 个结果。
# def calculate_scores(scores):
#     # 调用上面写好的函数：函数可以调用别的函数，不用重复写代码。
#     maximum = find_max(scores)
#
#     minimum = find_min(scores)
#
#     # sum(列表) 求总和，len(列表) 求个数，相除得平均分。/ 是除法，结果是小数。
#     average = sum(scores) / len(scores)
#
#     # pass_count 是"及格人数"的计数器，从 0 开始。
#     pass_count = 0
#
#     # 遍历每个成绩。
#     for score in scores:
#         # >= 是"大于或等于"：60 分及以上算及格。
#         if score >= 60:
#             # 及格就让计数器加 1。（01 号文件里没有这一步，所以及格人数一直是 0。）
#             pass_count = pass_count + 1
#
#     # return 后面用逗号隔开多个值，Python 会把它们打包成一个"元组"一起返回，调用的地方可以一次接收多个。
#     return maximum, minimum, average, pass_count
#
#
# # --------------------------------------------------------------------
# # 第五部分：函数 —— 读文本文件
# # --------------------------------------------------------------------
# def read_text_file():
#     # open(文件路径, 模式, encoding=...)：打开文件。"r" 表示只读（read）；encoding="utf-8" 指定文字编码，读写中文必须写对，否则会乱码。
#     # with ... as file: 是"上下文管理"写法：代码块结束后，Python 会自动关闭文件，不用自己写 close()。
#     with open(TEXT_FILE, "r", encoding="utf-8") as file:
#         # file.read() 一次读出文件的全部内容，返回一个字符串。
#         content = file.read()
#
#     return content
#
#
# # --------------------------------------------------------------------
# # 第六部分：函数 —— 保存 JSON 文件
# # --------------------------------------------------------------------
# def save_students_to_json(students):
#     # "w" 表示写入（write）：文件不存在就新建，已经存在就清空后重写（所以会覆盖原来的内容）。
#     with open(JSON_FILE, "w", encoding="utf-8") as file:
#         # json.dump(数据, 文件, ...)：把数据（列表、字典等）写进文件，格式是 JSON。
#         # ensure_ascii=False：中文原样写入，不转成 \u 开头的编码；indent=4：每一层缩进 4 个空格，方便阅读。
#         json.dump(students, file, ensure_ascii=False, indent=4)
#
#
# # --------------------------------------------------------------------
# # 第七部分：主程序 —— 调用上面的函数
# # --------------------------------------------------------------------
# # 列表：用方括号括起来的一串数据。这是 5 个成绩。
# scores = [85, 92, 78, 90, 88]
#
# # 调用函数，一次用 4 个变量接收 4 个返回值（叫"解包"），按顺序一一对应。
# maximum, minimum, average, pass_count = calculate_scores(scores)
#
# # print 里用逗号隔开多个内容，会依次输出，中间自动加一个空格。
# print("最高分：", maximum)
#
# print("最低分：", minimum)
#
# print("平均分：", average)
#
# # 这一次能得到正确的及格人数（85、92、78、90、88 都及格，是 5）。
# print("及格人数：", pass_count)
#
# # 调用函数，读出文本文件的内容。
# text_content = read_text_file()
#
# # 输出文件内容。文件末尾本来就有换行，print 又会加一个换行，所以后面会多出一个空行。
# print(text_content)
#
# # 列表里放字典：每个字典 {"键": 值, ...} 表示一个学生，键是"姓名""年龄""成绩"，用键就能取到对应的值。
# students = [{"姓名": "张三", "年龄": 18, "成绩": 90}, {"姓名": "李四", "年龄": 19, "成绩": 85}, {"姓名": "王五", "年龄": 20, "成绩": 80}]
#
# # 调用函数，把学生列表保存成 JSON 文件（会覆盖原来的"学生信息.json"）。
# save_students_to_json(students)
#
# # f"..." 叫 f-string（格式化字符串）：字符串前面加 f，里面的 {} 会被替换成变量的值。这里显示保存的文件路径。\n 表示换行。
# print(f"\n学生信息已保存到{JSON_FILE}")
#
# # ====================================================================
# # 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
# # （路径因电脑而异，<本文件所在文件夹> 指这个 .py 文件所在的文件夹）
# #   最高分： 92
# #   最低分： 78
# #   平均分： 86.6
# #   及格人数： 5
# #   张三 85
# #   李四 92
# #   王五 76
# #   赵六 58
# #
# #
# #   学生信息已保存到<本文件所在文件夹>/学生信息.json
# # ====================================================================
