# 文件用途：本文件围绕“两数之和 哈希表优化 测试版”展开。
# 核心知识点：列表与字典、函数、循环、分支判断、算法分析、异常处理。
# 基本流程：根据代码中的输入或初始数据，按条件、循环和函数调用完成处理，再输出结果。
# 输入：本文件使用预设数据、初始化值或函数参数。输出：通过控制台打印结果。
# 阅读提醒：阅读算法时注意边界情况、重复数据，以及关键变量在每轮处理后的变化。 异常分支是否覆盖所有错误情况，应结合具体输入和运行环境判断。
# 定义函数 `two_sum`，用于计算总和或平均值；参数包括 nums、target，函数体负责具体处理，调用处应关注传入参数和返回值。
def two_sum(nums,target):
    # 创建空字典 `seen`，后续把已经遍历过的值及其下标记录下来，以便快速查找是否出现过补数。
    seen = {}
    # 遍历 `range(len(nums))` 生成的整数序列，每轮把当前值赋给 `current_index`；`range` 的终点不包含在序列中，需留意循环边界。
    for current_index in range(len(nums)):
        # 通过下标/键读取 `nums[current_index]` 指定的数据，并保存到 `current_num`；使用前需保证索引或键有效。
        current_num = nums[current_index]
        # 计算 `target - current_num` 并将结果保存到 `needed_num`；按运算符和运算顺序逐步计算，可以验证结果是否正确。
        needed_num = target - current_num
        # 检查补数是否已保存在字典中；字典查询平均情况下很快，找到时即可返回一对下标。
        if needed_num in seen:
            # 结束当前函数调用，并把 `(seen[needed_num], current_index)` 的结果交给调用方；调用方可以继续使用这个值。
            return (seen[needed_num],current_index)
        # 把表达式 `current_index` 的计算结果保存到 `seen[current_num]`；右侧是数据来源，左侧是接收结果的变量。
        seen[current_num] = current_index
    # 返回 `None`，表示当前没有提供可用结果；调用方应考虑这种情况。
    return None
# 定义函数 `run_tests`；参数为空，函数体负责具体处理，调用处应关注传入参数和返回值。
def run_tests():
    # 创建列表 `test_cases` 并放入初始元素；列表有顺序且可修改，元素下标从 0 开始。
    test_cases = [
        ("基础配对",[2,7,11,15],9,(0,1)),
        ("重复元素",[3,3],6,(0,1)),
        ("包含负数",[-3,4,3,90],0,(0,2)),
        ("配对后出现在后面",[1,5,6,4],10,(2,3)),
        ("没有符合条件的配对",[1,2,3],100,None),
        ("空列表",[],5,None),
    ]
    # 把表达式 `True` 的计算结果保存到 `all_passed`；右侧是数据来源，左侧是接收结果的变量。
    all_passed = True
    # 遍历 `test_cases`，每轮把当前元素赋给 `(name, nums, target, expected)`，再执行循环体；确认容器内容与循环次数是否符合预期。
    for name, nums, target, expected in test_cases:
        # 调用函数 `two_sum()`，把它返回的结果保存到 `actual`；函数具体做什么要看函数定义。
        actual = two_sum(nums, target)
        # 把表达式 `actual == expected` 的计算结果保存到 `passed`；右侧是数据来源，左侧是接收结果的变量。
        passed = actual == expected
        # 把表达式 `'通过✅' if passed else '失败❌'` 的计算结果保存到 `status`；右侧是数据来源，左侧是接收结果的变量。
        status = "通过✅" if passed else "失败❌"
        # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
        print(f"[{status}] {name}: nums={nums}, target={target}, 期望={expected}, 实际={actual}")
        # 判断条件 `not passed`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if not passed:
            # 把表达式 `False` 的计算结果保存到 `all_passed`；右侧是数据来源，左侧是接收结果的变量。
            all_passed = False
    # 判断条件 `all_passed`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    if all_passed:
        # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
        print("\n全部测试通过！🎉🎉🎉")
    # 当前条件分支的前置条件均未成立时，执行这个 else 代码块。
    else:
        raise AssertionError("存在失败用例，请检查！⚠️⚠️⚠️")
# 检查本文件是否被直接运行；作为模块被其他文件导入时，这个分支不会执行。
if __name__ == "__main__":
    # 调用 `run_tests` 执行操作；如果它有返回值而这里没有接收，通常表示代码只需要它产生的副作用。
    run_tests()
