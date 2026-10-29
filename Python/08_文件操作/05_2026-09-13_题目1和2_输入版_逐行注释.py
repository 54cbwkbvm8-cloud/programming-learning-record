# 文件用途：本文件围绕“题目1和2 输入版 逐行注释”展开。
# 核心知识点：列表与字典、函数、循环、分支判断、异常处理。
# 基本流程：根据代码中的输入或初始数据，按条件、循环和函数调用完成处理，再输出结果。
# 输入：从标准输入读取数据或由调用者传入参数。输出：通过控制台打印处理结果。
# 阅读提醒：异常分支是否覆盖所有错误情况，应结合具体输入和运行环境判断。
# 模块依赖：json, pathlib。
# 导入 json 模块，为后面保存 JSON 数据做准备。  # 导入 JSON 工具
import json  # 当前示例暂时没有使用它

# 从 pathlib 模块导入 Path，为后面处理文件路径做准备。  # 导入路径工具
from pathlib import Path  # 当前示例暂时没有使用它

# 取得当前 Python 文件所在的文件夹路径。  # 设置基础目录
BASE_DIR = Path(__file__).resolve().parent  # 注意 file 两边各有两个下划线

# 组合出学生成绩示例文本文件的完整路径。  # 设置文本文件路径
TEXT_FILE = BASE_DIR / "学生成绩示例.txt"  # / 用来连接文件夹和文件名

# 组合出学生信息 JSON 文件的完整路径。  # 设置 JSON 文件路径
JSON_FILE = BASE_DIR / "学生信息.json"  # 保存 JSON 时会使用这个路径


# 定义求最大值的函数。  # 开始定义 find_max
def find_max(numbers):  # numbers 接收数字列表
    # 判断列表是否为空。  # 检查列表长度
    if len(numbers) == 0:  # len 返回列表元素个数
        # 列表为空时主动报错。  # 抛出异常
        raise ValueError("列表不能为空")  # 避免访问不存在的第一个元素

    # 先把第一个数字当作最大值。  # 设置最大值
    max_number = numbers[0]  # 下标 0 表示第一个元素

    # 逐个检查列表中的数字。  # 遍历数字列表
    for number in numbers:  # number 是当前数字
        # 判断当前数字是否更大。  # 比较大小
        if number > max_number:  # > 表示大于
            # 保存新的最大值。  # 更新最大值
            max_number = number  # 把更大的数字保存起来

    # 返回最终找到的最大值。  # 返回结果
    return max_number  # 把结果交给函数调用处


# 定义求最小值的函数。  # 开始定义 find_min
def find_min(numbers):  # numbers 接收数字列表
    # 判断列表是否为空。  # 检查列表长度
    if len(numbers) == 0:  # len 返回列表元素个数
        # 列表为空时主动报错。  # 抛出异常
        raise ValueError("列表不能为空")  # 避免访问不存在的第一个元素

    # 先把第一个数字当作最小值。  # 设置最小值
    min_number = numbers[0]  # 下标 0 表示第一个元素

    # 逐个检查列表中的数字。  # 遍历数字列表
    for number in numbers:  # number 是当前数字
        # 判断当前数字是否更小。  # 比较大小
        if number < min_number:  # < 表示小于
            # 保存新的最小值。  # 更新最小值
            min_number = number  # 把更小的数字保存起来

    # 返回最终找到的最小值。  # 返回结果
    return min_number  # 把结果交给函数调用处


# 定义计算平均值的函数。  # 开始定义 average
def average(numbers):  # average 才是正确拼写
    # 判断列表是否为空。  # 检查列表长度
    if len(numbers) == 0:  # 空列表不能计算平均值
        # 列表为空时主动报错。  # 抛出异常
        raise ValueError("列表不能为空")  # 停止函数继续执行

    # 创建总和变量，初始值为 0。  # 设置总和
    total = 0  # total 用来累加所有数字

    # 逐个取出列表中的数字。  # 遍历数字列表
    for number in numbers:  # number 是当前数字
        # 把当前数字累加到总和里。  # 累加数字
        total += number  # += 表示在原值基础上增加

    # 用总和除以数字个数，并返回结果。  # 计算平均值
    return total / len(numbers)  # / 表示普通除法


# 定义统计及格人数的函数。  # 开始定义 count_pass
def count_pass(numbers):  # 这里必须接收 numbers 参数
    # 判断列表是否为空。  # 检查列表长度
    if len(numbers) == 0:  # 注意这里写 numbers，不是 number
        # 列表为空时主动报错。  # 抛出异常
        raise ValueError("列表不能为空")  # 停止函数继续执行

    # 创建及格人数计数器。  # 设置计数器
    count = 0  # 一开始及格人数是 0

    # 逐个检查每个数字。  # 遍历数字列表
    for number in numbers:  # number 是当前成绩
        # 判断成绩是否大于或等于 60。  # 判断是否及格
        if number >= 60:  # >= 表示大于或等于
            # 及格人数增加 1。  # 累加人数
            count += 1  # 把 count 原来的值加 1

    # 返回最终的及格人数。  # 返回统计结果
    return count  # 把人数交给函数调用处


# 创建空列表保存用户输入的数字。  # 创建数字列表
numbers = []  # [] 表示空列表

# 循环 5 次，让用户输入 5 个数字。  # 开始输入循环
for index in range(5):  # index 会依次是 0、1、2、3、4
    # 显示提示并读取用户输入的数字。  # 获取用户输入
    number = int(input(f"请输入第 {index + 1} 个数字："))  # int 把文字转换成整数

    # 把输入的数字添加到列表末尾。  # 保存输入数字
    numbers.append(number)  # append 用来添加列表元素


# 调用函数求最小值并输出。  # 输出最小值
print("最小值是：", find_min(numbers))  # 先调用 find_min，再显示结果

# 调用函数求最大值并输出。  # 输出最大值
print("最大值是：", find_max(numbers))  # 先调用 find_max，再显示结果

# 调用函数统计及格人数并输出。  # 输出及格人数
print("及格人数是：", count_pass(numbers))  # 把列表传给 count_pass

# 调用函数计算平均分并输出。  # 输出平均分
print("平均分是：", average(numbers))  # 把列表传给 average
