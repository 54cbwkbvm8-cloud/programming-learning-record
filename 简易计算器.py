# 简易计算器

# 每个运算定义成一个小函数
def add(a, b):
    return a + b

def subtract(a, b):
    return a - b

def multiply(a, b):
    return a * b

def divide(a, b):
    if b == 0:
        return "不能除以 0"
    return a / b

# 主程序：让用户不断选择运算
def calculator():
    while True:
        print("=" * 20)
        print("1. 加法   2. 减法   3. 乘法   4. 除法   5. 退出")
        choice = input("请选择运算：")

        if choice == "5":
            print("再见！")
            break

        if choice not in ("1", "2", "3", "4"):
            print("输入无效，请重新输入")
            continue

        a = float(input("请输入第一个数："))
        b = float(input("请输入第二个数："))

        if choice == "1":
            print("结果：", add(a, b))
        elif choice == "2":
            print("结果：", subtract(a, b))
        elif choice == "3":
            print("结果：", multiply(a, b))
        elif choice == "4":
            print("结果：", divide(a, b))

# 启动计算器
calculator()
