# 大学奖学金资格判断程序
# 用户输入姓名、平均成绩、英语成绩、是否挂科

name = input("请输入姓名：")
avg = float(input("请输入平均成绩："))
english = float(input("请输入英语成绩："))
failed = input("是否有挂科？(是/否)：")

# 把用户输入转成"是否挂科"的判断
has_failed = failed in ("是", "有", "y", "Y", "yes", "YES")

# 三个条件同时满足才算符合
if avg >= 85 and english >= 70 and not has_failed:
    print(f"{name}，符合基本奖学金申请条件")
else:
    print(f"{name}，暂不符合条件")
