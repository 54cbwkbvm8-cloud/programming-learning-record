# 文件用途：本文件围绕“两数之和 哈希优化 结果展示版”展开。
# 核心知识点：列表与字典、函数、循环、分支判断、算法分析。
# 基本流程：根据代码中的输入或初始数据，按条件、循环和函数调用完成处理，再输出结果。
# 输入：本文件使用预设数据、初始化值或函数参数。输出：通过控制台打印结果。
# 阅读提醒：阅读算法时注意边界情况、重复数据，以及关键变量在每轮处理后的变化。
# 定义函数 `two_sum`，用于计算总和或平均值；参数包括 nums、target，函数体负责具体处理，调用处应关注传入参数和返回值。
def two_sum(nums,target):
    # 创建空字典 `num_to_index`，后续把已经遍历过的值及其下标记录下来，以便快速查找是否出现过补数。
    num_to_index = {}
    # 遍历 `enumerate(nums)`，每轮把当前元素赋给 `(index, num)`，再执行循环体；确认容器内容与循环次数是否符合预期。
    for index,num in enumerate(nums):
        # 计算当前值所需的补数 `target - num` 并存入 `complement`；这类变量常用于判断两个数能否凑成目标值。
        complement = target - num
        # 判断条件 `complement in num_to_index`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if complement in num_to_index:
            # 返回一个新列表 `[num_to_index[complement], index]`；列表中的值由当前计算得到，函数执行到这里就会立即结束。
            return [num_to_index[complement],index]
        # 把表达式 `index` 的计算结果保存到 `num_to_index[num]`；右侧是数据来源，左侧是接收结果的变量。
        num_to_index[num] = index
    # 返回一个新列表 `[]`；列表中的值由当前计算得到，函数执行到这里就会立即结束。
    return[]
# 定义函数 `run_test`；参数包括 nums、target，函数体负责具体处理，调用处应关注传入参数和返回值。
def run_test(nums,target):
    # 调用函数 `two_sum()`，把它返回的结果保存到 `result`；函数具体做什么要看函数定义。
    result = two_sum(nums,target)
    # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
    print(f"nums = {nums},target = {target}")
    # 判断条件 `len(result) == 2`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    if len(result) == 2:
        # 通过下标/键读取 `result[0]` 指定的数据，并保存到 `first_index`；使用前需保证索引或键有效。
        first_index = result[0]
        # 通过下标/键读取 `result[1]` 指定的数据，并保存到 `second_index`；使用前需保证索引或键有效。
        second_index = result[1]
        # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
        print(f"找到下标：{result}")
        # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
        print(f"对应数值：{nums[first_index]} + {nums[second_index]} = {target}")
    # 当前条件分支的前置条件均未成立时，执行这个 else 代码块。
    else:
        # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
        print("没有找到两个不同下标的数字，使它们的和等于target。")
    # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
    print("-"*50)
# 定义函数 `main`；参数为空，函数体负责具体处理，调用处应关注传入参数和返回值。
def main():
    # 创建列表 `test_case` 并放入初始元素；列表有顺序且可修改，元素下标从 0 开始。
    test_case = [
        ([3,3],6),
        ([3,2,4],6),
        ([-1,-2,-3,-4],-6),
        ([1,2,3],7),
        ([0,4,3,0],0),
    ]
    # 遍历 `test_case`，每轮把当前元素赋给 `(nums, target)`，再执行循环体；确认容器内容与循环次数是否符合预期。
    for nums,target in test_case:
        # 调用 `run_test` 执行操作；如果它有返回值而这里没有接收，通常表示代码只需要它产生的副作用。
        run_test(nums,target)
# 检查本文件是否被直接运行；作为模块被其他文件导入时，这个分支不会执行。
if __name__ == '__main__':
    # 调用 `main` 执行操作；如果它有返回值而这里没有接收，通常表示代码只需要它产生的副作用。
    main()
