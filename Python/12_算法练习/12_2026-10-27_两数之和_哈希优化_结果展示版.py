def two_sum(nums,target):
    num_to_index = {}
    for index,num in enumerate(nums):
        complement = target - num
        if complement in num_to_index:
            return [num_to_index[complement],index]
        num_to_index[num] = index
    return[]
def run_test(nums,target):
    result = two_sum(nums,target)
    print(f"nums = {nums},target = {target}")
    if len(result) == 2:
        first_index = result[0]
        second_index = result[1]
        print(f"找到下标：{result}")
        print(f"对应数值：{nums[first_index]} + {nums[second_index]} = {target}")
    else:
        print("没有找到两个不同下标的数字，使它们的和等于target。")
    print("-"*50)
def main():
    test_case = [
        ([3,3],6),
        ([3,2,4],6),
        ([-1,-2,-3,-4],-6),
        ([1,2,3],7),
        ([0,4,3,0],0),
    ]
    for nums,target in test_case:
        run_test(nums,target)
if __name__ == '__main__':
    main()
