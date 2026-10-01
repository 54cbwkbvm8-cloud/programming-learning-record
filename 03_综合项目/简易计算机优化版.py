"""
简介：增加输入异常处理和除零保护的优化版计算器。
功能：
- 支持整数和浮点数四则运算。
- 使用 try/except 处理无效菜单和数字输入。
- 阻止除数为 0 并允许连续计算。
用途：对比基础版练习异常处理、浮点数输入和程序健壮性。
最后修改：2026-09-09
"""
print("="*40)  # 打印 40 个等号作为标题分隔线
print("欢迎使用Python计算机")  # 打印欢迎语
print("加法输入1，减法输入2，乘法输入3，除法输入4，退出输入5")  # 打印功能说明
def add(a,b):  # 定义加法函数，接收两个参数 a 和 b
    return a + b  # 返回两个数之和
def jian(a,b):  # 定义减法函数
    return a - b  # 返回两个数之差
def cheng(a,b):  # 定义乘法函数
    return a * b  # 返回两个数之积
def chu(a,b):  # 定义除法函数
    return a / b  # 返回两个数之商
while True:  # 无限循环，直到用户选择退出
    try:  # 尝试执行可能出错的代码
        choice = float(input("请选择你的计算方式"))  # 输入运算方式并转成浮点数
        if choice not in (1,2,3,4,5):  # 输入值不在 1~5 之间
            print("输入无效!!!请输入正确数字才能够开始计算")  # 打印无效提示
            continue  # 跳过本次，回到循环开头重新输入
        elif choice == 5:  # 选择 5 表示退出
            print("再见，欢迎下次使用Python计算机")  # 打印再见语
            break  # 跳出整个 while 循环
    except ValueError:  # 若输入的不是数字，捕获异常
        print("请输入有效数字!!!")  # 提示输入有效数字
        continue  # 回到循环开头重新输入
    try:  # 尝试读取两个运算数字
        a = float(input("请输入第一个数字"))  # 输入第一个数字并转成浮点数
        b = float(input("请输入第二个数字"))  # 输入第二个数字并转成浮点数
    except ValueError:  # 输入的不是数字时
        print("请输入有效数字!!!")  # 提示输入有效数字
        continue  # 重新开始本轮
    if choice == 1:  # 选择 1 做加法
        print("结果:",add(a,b))  # 调用 add 函数并打印结果
    elif choice == 2:  # 选择 2 做减法
        print("结果:",jian(a,b))  # 调用 jian 函数并打印结果
    elif choice == 3:  # 选择 3 做乘法
        print("结果:",cheng(a,b))  # 调用 cheng 函数并打印结果
    elif choice == 4:  # 选择 4 做除法
        if b == 0:  # 除数为 0 时
            print("不能除以0")  # 提示不能除以 0
            continue  # 跳过本次，重新开始
        print("结果:",chu(a,b))  # 调用 chu 函数并打印结果
