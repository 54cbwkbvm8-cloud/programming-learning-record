def two_sum(nums,target):
    seen = {}
    for current_index in range(len(nums)):
        current_num = nums[current_index]
        needed_num = target - current_num
        if needed_num in seen:
            return (seen[needed_num],current_index)
        seen[current_num] = current_index
    return None
def run_tests():
    test_cases = [
        ("基础配对",[2,7,11,15],9,(0,1)),
        ("重复元素",[3,3],6,(0,1)),
        ("包含负数",[-3,4,3,90],0,(0,2)),
        ("配对后出现在后面",[1,5,6,4],10,(2,3)),
        ("没有符合条件的配对",[1,2,3],100,None),
        ("空列表",[],5,None),
    ]
    all_passed = True
    for name, nums, target, expected in test_cases:
        actual = two_sum(nums, target)
        passed = actual == expected
        status = "通过✅" if passed else "失败❌"
        print(f"[{status}] {name}: nums={nums}, target={target}, 期望={expected}, 实际={actual}")
        if not passed:
            all_passed = False
    if all_passed:
        print("\n全部测试通过！🎉🎉🎉")
    else:
        raise AssertionError("存在失败用例，请检查！⚠️⚠️⚠️")
if __name__ == "__main__":
    run_tests()
