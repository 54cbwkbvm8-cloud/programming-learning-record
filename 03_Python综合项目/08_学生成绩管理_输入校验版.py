"""
简介：带完整输入校验的学生成绩管理系统，先确定人数再逐个录入。
功能：
- 校验学生人数必须是大于 0 的整数，不合法就要求重新输入。
- 逐个校验学生成绩必须是 0 到 100 之间的整数。
- 输出全部学生及等级、成绩最好的学生、成绩总和和平均分。
用途：练习输入校验、字典列表、max 配合 key 取最高分和字符串格式化。
最后修改：2026-10-11
"""
students = []
def get_level(score):
    if score >= 90:
        return 'A'
    elif score >= 80:
        return 'B'
    elif score >= 70:
        return 'C'
    elif score >= 60:
        return 'D'
    else:
        return 'E'
print("="*10,"学生成绩管理系统", "="*10)
while True:
    count = input("请输入学生人数：")
    if count.isdigit() and int(count) > 0:
        count = int(count)
        break
    else:
        print("输入错误，请重新输入大于 0 的整数！")
for i in range(count):
    name = input("请输入第 %d 个学生的姓名：" % (i + 1))
    while True:
        score = input("请输入第 %d 个学生的成绩：" % (i + 1))
        if score.isdigit() and int(score) >= 0 and int(score) <= 100:
            score = int(score)
            break
        else:
            print("输入错误，请重新输入 0-100 之间的整数！")
    level = get_level(score)
    student = {"name": name, "score": score, "level": level}
    students.append(student)

print("\n==========所有学生成绩==========")
for student in students:
    print("姓名：%s，成绩：%d，等级：%s" % (student["name"], student["score"], student["level"]))
print("----------------------------------")
if not students:
    print("暂无学生成绩！")
else:
    best_student = max(students, key=lambda x: x["score"])
    print("\n==========成绩最好的学生==========")
    print("姓名：%s，成绩：%d，等级：%s" % (best_student["name"], best_student["score"], best_student["level"]))
    total_score = 0
    for student in students:
        total_score += student["score"]
    average_score = total_score / len(students)
    print("\n==========所有学生成绩总和==========")
    print("所有学生成绩总和：%d" % total_score)
    print("\n==========所有学生成绩平均值==========")
    print("所有学生成绩平均值：%.2f" % average_score)
