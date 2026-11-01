import json
from pathlib import Path
BASE_DIR = Path(__file__).resolve().parent
TEXT_FILE = BASE_DIR / "学生成绩示例.txt"
JSON_FILE = BASE_DIR / "学生信息.json"
def calculate_scores(scores):
    if len(scores) == 0:
        raise ValueError("列表不能为空")
    max_score = scores[0]
    min_score = scores[0]
    total_score = 0
    pass_count = 0
    for score in scores:
        if score > max_score:
            max_score = score
        if score < min_score:
            min_score = score
        total_score += score
    average_score = total_score / len(scores)
    return max_score, min_score, average_score,pass_count
def find_max(numbers):
    if len(numbers) == 0:
        raise ValueError("列表不能为空")
    max_number = numbers[0]
    for number in numbers[1:]:
        if number > max_number:
            max_number = number
    return numbers
def find_min(numbers):
    if len(numbers) == 0:
        raise ValueError("列表不能为空")
    min_number = numbers[0]
    for number in numbers[1:]:
        if number < min_number:
            min_number = number
    return min_number
def read_text_file():
    with open(TEXT_FILE, "r", encoding="utf-8") as file:
        content = file.read()
    return content
def save_students_to_json(students):
    with open(JSON_FILE, "w", encoding="utf-8") as file:
        json.dump(students, file, ensure_ascii=False, indent=4)
scores = [85, 92, 78, 90, 88]
maximum,minmum,average,pass_count = calculate_scores(scores)
print("最高分：", maximum)
print("最低分：", minmum)
print("平均分：", average)
print("及格人数：", pass_count)
text_content = read_text_file()
print(text_content)
student = [{"姓名": "张三", "年龄": 18, "成绩": 90}, {"姓名": "李四", "年龄": 19, "成绩": 85}, {"姓名": "王五", "年龄": 20, "成绩": 80}]
save_students_to_json(student)
print(f"\n学生信息已保存到{JSON_FILE}")
# # ====================================================================
# # 文件名：01_2026-09-13_文件读写复习_成绩统计.py
# # 作用：文件读写复习：
# #       1. 用函数统计一组成绩（最高分、最低分、平均分、及格人数）
# #       2. 读取同文件夹里的文本文件"学生成绩示例.txt"并输出内容
# #       3. 把一个学生信息列表保存成 JSON 文件"学生信息.json"
# #       ⚠ 注意：运行这个程序会覆盖同文件夹里原来的"学生信息.json"。
# #       ⚠ 这个文件里有几处缺陷（及格人数一直是 0、find_max 返回了整个列表），见下面各处的说明。
# #
# # 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
# #   运行：python3 "01_2026-09-13_文件读写复习_成绩统计.py"
# #   说明：Python 是边读边执行的，不需要先编译。
# # ====================================================================
#
#
# # --------------------------------------------------------------------
# # 第一部分：引入工具，设置文件路径
# # --------------------------------------------------------------------
# # import json：引入 Python 自带的 json 工具箱，用来把数据保存成 JSON 文件（一种常见的、人也能读的数据格式）。
# import json
#
# # from ... import ...：只引入工具箱里的一个工具。Path 是"路径"工具，用来方便地表示文件和文件夹的位置。
# from pathlib import Path
#
# # __file__ 是"当前这个 .py 文件的路径"；Path(...) 把它变成路径对象；.resolve() 变成完整的绝对路径；
# # .parent 取它所在的文件夹。所以 BASE_DIR 就是"这个程序所在的文件夹"。
# # 这样不管你在哪个位置运行程序，都能找到和程序放在一起的数据文件。
# BASE_DIR = Path(__file__).resolve().parent
#
# # 路径对象可以用 / 拼接："文件夹 / 文件名"就得到这个文件的完整路径。
# # 全部用大写字母命名的变量，是 Python 的习惯写法，表示"这是一个常量，不打算修改"。
# TEXT_FILE = BASE_DIR / "学生成绩示例.txt"
# JSON_FILE = BASE_DIR / "学生信息.json"
#
#
# # --------------------------------------------------------------------
# # 第二部分：函数 —— 统计成绩
# # --------------------------------------------------------------------
# # def 函数名(参数): 用来定义函数 = 一段有名字、可以反复使用的代码。scores 是参数：调用时传进来的成绩列表。
# # 缩进的部分是函数体。函数可以用 return 把结果交回给调用者。
# def calculate_scores(scores):
#     # len(列表) 返回元素个数。个数是 0，说明列表是空的，没法统计。
#     if len(scores) == 0:
#         # raise 的意思是"主动抛出一个错误"：函数遇到不合理的数据，直接报错并停下，比悄悄给出错误结果更好。
#         raise ValueError("列表不能为空")
#
#     # "打擂台"：先假设第一个成绩既是最高分，也是最低分（下标从 0 开始，scores[0] 是第一个元素）。
#     max_score = scores[0]
#     min_score = scores[0]
#
#     # total_score 存总分，从 0 开始累加。pass_count 本来是用来记录及格人数的，从 0 开始。
#     total_score = 0
#     pass_count = 0
#
#     # for 变量 in 列表: 让 score 依次取列表里的每一个元素，每取一个，把缩进的代码执行一遍。
#     for score in scores:
#         # 比当前最高分高，就更新最高分；下面的 if 对最低分做同样的事。
#         if score > max_score:
#             max_score = score
#         if score < min_score:
#             min_score = score
#
#         # += 是"加上再赋值"：total_score += score 等价于 total_score = total_score + score。
#         total_score += score
#
#     # 平均分 = 总分 ÷ 个数。/ 是除法，结果是小数。这一行没有缩进到 for 里面，所以循环结束后才算。
#     average_score = total_score / len(scores)
#
#     # return 后面用逗号隔开多个值，Python 会把它们打包成一个"元组"一起返回。调用的地方可以一次接收多个值。
#     # ⚠ 缺陷：pass_count 在循环里一直没有被修改（没有判断及格、没有加 1），所以返回的永远是 0。
#     # 这是原代码的写法，这里不改，只提醒你。
#     return max_score, min_score, average_score,pass_count
#
#
# # --------------------------------------------------------------------
# # 第三部分：函数 —— 找最大值、最小值（本程序没有调用）
# # --------------------------------------------------------------------
# # 和上面类似的"打擂台"。注意：这两个函数在程序最后并没有被调用。
# def find_max(numbers):
#     if len(numbers) == 0:
#         raise ValueError("列表不能为空")
#     max_number = numbers[0]
#
#     # numbers[1:] 叫"切片"：取从下标 1 开始到末尾的所有元素（跳过第一个，因为第一个已经当作最大值了）。
#     for number in numbers[1:]:
#         if number > max_number:
#             max_number = number
#
#     # ⚠ 缺陷：这里返回的是整个列表 numbers，而不是找到的最大值 max_number，所以这个函数的结果是错的。
#     # 这是原代码的写法，这里不改，只提醒你（因为程序没有调用 find_max，所以没有影响运行结果）。
#     return numbers
# def find_min(numbers):
#     if len(numbers) == 0:
#         raise ValueError("列表不能为空")
#     min_number = numbers[0]
#     for number in numbers[1:]:
#         if number < min_number:
#             min_number = number
#
#     # find_min 返回的是最小值，写法是对的。
#     return min_number
#
#
# # --------------------------------------------------------------------
# # 第四部分：函数 —— 读文本文件
# # --------------------------------------------------------------------
# def read_text_file():
#     # open(文件路径, 模式, encoding=...)：打开文件。"r" 表示只读（read）；encoding="utf-8" 指定文字编码，读写中文必须写对，否则会乱码。
#     # with ... as file: 是"上下文管理"写法：代码块结束后，Python 会自动关闭文件，不用自己写 close()，更安全。
#     with open(TEXT_FILE, "r", encoding="utf-8") as file:
#         # file.read() 一次读出文件的全部内容，返回一个字符串。
#         content = file.read()
#     return content
#
#
# # --------------------------------------------------------------------
# # 第五部分：函数 —— 保存 JSON 文件
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
# # 第六部分：主程序 —— 调用上面的函数
# # --------------------------------------------------------------------
# # 列表：用方括号括起来的一串数据。这是 5 个成绩。
# scores = [85, 92, 78, 90, 88]
#
# # 调用函数 calculate_scores，把 scores 传进去。函数返回 4 个值，这里一次用 4 个变量接收（叫"解包"），按顺序一一对应。
# maximum,minmum,average,pass_count = calculate_scores(scores)
#
# # print 里用逗号隔开多个内容，会依次输出，中间自动加一个空格。
# print("最高分：", maximum)
# print("最低分：", minmum)
# print("平均分：", average)
#
# # 由于上面说的缺陷，这里永远是 0。
# print("及格人数：", pass_count)
#
# # 调用函数，读出文本文件的内容，存进 text_content。
# text_content = read_text_file()
#
# # 输出文件内容。文件末尾本来就有换行，print 又会加一个换行，所以后面会多一个空行。
# print(text_content)
#
# # 列表里放字典：每个字典 {"键": 值, ...} 表示一个学生，键是"姓名""年龄""成绩"，用键就能取到对应的值。
# # 这一行是一个列表，包含 3 个字典（3 个学生）。
# student = [{"姓名": "张三", "年龄": 18, "成绩": 90}, {"姓名": "李四", "年龄": 19, "成绩": 85}, {"姓名": "王五", "年龄": 20, "成绩": 80}]
#
# # 调用函数，把学生列表保存成 JSON 文件（会覆盖原来的"学生信息.json"）。
# save_students_to_json(student)
#
# # f"..." 叫 f-string（格式化字符串）：字符串前面加 f，里面的 {} 会被替换成变量的值。这里把文件路径显示出来。
# # \n 表示换行。
# print(f"\n学生信息已保存到{JSON_FILE}")
#
# # ====================================================================
# # 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
# # （路径因电脑而异，<本文件所在文件夹> 指这个 .py 文件所在的文件夹）
# #   最高分： 92
# #   最低分： 78
# #   平均分： 86.6
# #   及格人数： 0
# #   张三 85
# #   李四 92
# #   王五 76
# #   赵六 58
# #
# #
# #   学生信息已保存到<本文件所在文件夹>/学生信息.json
# # ====================================================================
