def two_sum(nums, target):  # 定义函数：nums 是列表，target 是目标和。
    seen = {}  # 创建字典，保存已经遍历过的数字及其下标。

    for i in range(len(nums)):  # 从左到右遍历列表中的每个下标。
        current = nums[i]  # 取出当前下标对应的数字。
        need = target - current  # 计算当前数字需要搭配的补数。

        if need in seen:  # 检查这个补数以前是否出现过。
            return [seen[need], i]  # 返回补数的旧下标和当前下标，并结束函数。

        seen[current] = i  # 如果没有找到补数，就记录当前数字和它的下标。

    return None  # 所有数字都检查完仍未找到时，返回 None。


if __name__ == '__main__':  # 只有直接运行这个文件时，才执行测试代码。
    nums = [2, 7, 11, 15]  # 创建用于测试的列表。
    target = 9  # 设置目标和。
    print(two_sum(nums, target))  # 调用函数并打印结果，预期为 [0, 1]。
