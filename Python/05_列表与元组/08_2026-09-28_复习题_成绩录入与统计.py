students = []  # 创建一个空列表，用来保存所有学生的信息
scores = []  # 创建一个空列表，用来保存所有学生的成绩
while True:  # 创建一个无限循环，让我们可以连续输入多个学生
    name = input("请输入学生姓名：")  # 让用户输入学生姓名，并把姓名保存到name变量中
    score = float(input("请输入学生成绩："))  # 让用户输入成绩，并把输入的内容转换成数字保存到score变量中
    if score < 0 or score > 100:  # 判断成绩是否小于0或者大于100
        print("成绩必须在0到100之间，请重新输入！")  # 如果成绩不合法，就输出错误提示
        continue
    if score >= 90:  # 判断成绩是否大于等于90
        level = 'A'
    elif score >= 80:  # 如果成绩没有达到90，再判断是否大于等于80
        level = 'B'
    elif score >= 70:  # 如果成绩没有达到80，再判断是否大于等于70
        level = 'C'
    elif score >= 60:  # 如果成绩没有达到70，再判断是否大于等于60
        level = 'D'
    else:  # 如果成绩没有达到60
        level = 'E'
    students.append([name, score, level])  # 把学生信息添加到students列表中
    print("学生姓名：", name, "学生成绩：", score, "学生等级：",level)
    answer = input("是否继续输入学生信息？(y/n)")
    if answer == 'n':
        break
    elif answer == 'y':
        continue
    else:
        print("输入错误，请重新输入！")
        continue
print("**********所有学生的成绩统计表**********")
scores = []
for student in students:  # 遍历students列表中的每一个学生
    scores.append(student[1])  # 把当前学生的成绩添加到scores列表中

print("\n所有学生成绩：")  # 输出所有学生成绩的标题
for student in students:
    print("学生姓名：", student[0], "学生成绩：", student[1], "学生等级：", student[2])
if len(students) > 0:
    highest_score = max(students, key=lambda x: x[1])
    print("最高分学生姓名：", highest_score[0], "最高分学生成绩：", highest_score[1], "最高分学生等级：", highest_score[2])
    average = sum([student[1] for student in students]) / len(students)
    print("平均分：", average)
else:
    print("没有学生信息")
print("程序结束")
