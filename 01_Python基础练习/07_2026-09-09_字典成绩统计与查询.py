"""
简介：演示 Python 字典的创建、遍历、排序和按姓名查询。
功能：
- 统计学生成绩的平均分、最高分、最低分以及及格和不及格人数。
- 按姓名排序并输出全部学生成绩。
- 根据用户输入查询指定学生的成绩。
用途：练习字典、列表推导式、排序以及常用统计函数。
最后修改：2026-09-09
"""
scores = {"小明": "90", "小红": "85", "小刚": "76"}  # 字典：键是学生姓名，值是成绩（字符串）
all_scores = [int(num) for num in scores.values()]  # 列表推导式：取出所有成绩并转成整数，组成列表
pingjun = sum(all_scores) / len(all_scores)  # 总成绩除以成绩个数，得到平均分
zuigaofen = max(all_scores)  # 用 max 找出最高分
zuidifen = min(all_scores)  # 用 min 找出最低分
jigerenshu = 0  # 初始化及格人数计数器
for score in all_scores:  # 遍历每一个成绩
    if score >= 60:      # 成绩大于等于 60 算及格
        jigerenshu += 1  # 及格人数加 1
bujigerenshu = len(all_scores) - jigerenshu  # 总人数减去及格人数，得到不及格人数
print("平均成绩:", pingjun)  # 打印平均成绩
print("最高分:", zuigaofen)  # 打印最高分
print("最低分:", zuidifen)  # 打印最低分
print("及格人数:", jigerenshu)  # 打印及格人数
print("不及格人数:", bujigerenshu)  # 打印不及格人数
print("-------------")  # 打印分隔线
for name, score in sorted(scores.items()):  # 按键（姓名）排序后遍历字典的键值对
    print(name, score)  # 打印姓名和对应成绩
print("-"*20)  # 打印 20 个减号作为分隔线
name = input("请输入你想要查找的学生")  # 提示输入要查找的学生姓名
if name in scores:  # 判断该姓名是否存在于字典中
    print("查找成功")  # 存在时打印查找成功
    print("成绩:",scores[name])  # 打印该姓名对应的成绩
else:  # 姓名不存在
    print("未找到该学生")  # 提示未找到该学生
