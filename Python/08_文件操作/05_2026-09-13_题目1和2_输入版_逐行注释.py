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

    count = 0

    for number in numbers:
        if number >= 60:
            count += 1

    return count


numbers = []

for index in range(5):
    number = int(input(f"请输入第 {index + 1} 个数字："))

    numbers.append(number)


print("最小值是：", find_min(numbers))

print("最大值是：", find_max(numbers))

print("及格人数是：", count_pass(numbers))

print("平均分是：", average(numbers))
# # ====================================================================
# # 文件名：05_2026-09-13_题目1和2_输入版_逐行注释.py
# # 作用：题目 1 和题目 2 的"输入版"：从键盘输入 5 个整数，用函数求出：
# #       最小值、最大值、及格人数（≥ 60 分）、平均分。
# #       说明：文件开头引入的 json、Path 以及 BASE_DIR、TEXT_FILE、JSON_FILE，
# #             在这个文件里并没有用到（是从别的练习里带过来的），不影响运行。
# #
# # 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
# #   运行：python3 "05_2026-09-13_题目1和2_输入版_逐行注释.py"
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
# # 这一行同样定义了一个文件路径，但本文件没有用到它。
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
#     # for 变量 in 列表: 让 number 依次取列表里的每一个元素。
#     # 这里从第一个元素开始比（和自己比一次也没关系，结果不变）。
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
# # 第四部分：函数 —— 求平均分
# # --------------------------------------------------------------------
# def average(numbers):
#     if len(numbers) == 0:
#         raise ValueError("列表不能为空")
#
#     # total 存总和，从 0 开始累加。
#     total = 0
#
#     # 把每个数都加进 total。
#     for number in numbers:
#         # += 是"加上再赋值"：total += number 等价于 total = total + number。
#         total += number
#
#     # 总和 ÷ 个数 = 平均分。/ 是除法，结果是小数。
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
#     # count 是"计数器"，从 0 开始。
#     count = 0
#
#     for number in numbers:
#         # >= 是"大于或等于"：60 分及以上算及格。
#         if number >= 60:
#             # 及格就让计数器加 1。
#             count += 1
#
#     return count
#
#
# # --------------------------------------------------------------------
# # 第六部分：主程序 —— 输入 5 个数，调用函数输出结果
# # --------------------------------------------------------------------
# # [] 是"空列表"，numbers 用来存输入的 5 个整数。
# numbers = []
#
# # range(5) 生成 0、1、2、3、4，所以循环执行 5 次，index 依次取这 5 个值。
# for index in range(5):
#     # input(提示语)：显示提示语，等用户输入并按回车，得到字符串；int(...) 把它转成整数。输入的不是整数会报错中断。
#     # f"..." 叫 f-string：里面的 {index + 1} 会被替换成值：index 从 0 开始，显示给人看的编号从 1 开始，所以加 1。
#     number = int(input(f"请输入第 {index + 1} 个数字："))
#
#     # 列表.append(元素)：把元素添加到列表末尾。
#     numbers.append(number)
#
#
# # 调用函数，把返回值直接交给 print 输出。print 里用逗号隔开多个内容，会依次输出，中间自动加一个空格。
# print("最小值是：", find_min(numbers))
#
# print("最大值是：", find_max(numbers))
#
# # 例：85、92、56、78、60 里，56 不及格，其余 4 个及格，所以是 4。
# print("及格人数是：", count_pass(numbers))
#
# print("平均分是：", average(numbers))
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
# #   请输入第 1 个数字：请输入第 2 个数字：请输入第 3 个数字：请输入第 4 个数字：请输入第 5 个数字：最小值是： 56
# #   最大值是： 92
# #   及格人数是： 4
# #   平均分是： 74.2
# # ====================================================================
