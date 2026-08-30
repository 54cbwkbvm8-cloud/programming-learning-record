print("="*40)
print("欢迎使用Python计算机")
print("加法输入1，减法输入2，乘法输入3，除法输入4，退出输入5")
def add(a,b):
    return a + b
def jian(a,b):
    return a - b
def cheng(a,b):
    return a * b
def chu(a,b):
    return a / b
while True:
    try:
        choice = float(input("请选择你的计算方式"))
        if choice not in (1,2,3,4,5):
            print("输入无效!!!请输入正确数字才能够开始计算")
            continue
        elif choice == 5:
            print("再见，欢迎下次使用Python计算机")
            break
    except ValueError:
        print("请输入有效数字!!!")
        continue
    try:
        a = float(input("请输入第一个数字"))
        b = float(input("请输入第二个数字"))
    except ValueError:
        print("请输入有效数字!!!")
        continue
    if choice == 1:
        print("结果:",add(a,b))
    elif choice == 2:
        print("结果:",jian(a,b))
    elif choice == 3:
        print("结果:",cheng(a,b))
    elif choice == 4:
        if b == 0:
            print("不能除以0")
            continue
        print("结果:",chu(a,b))


