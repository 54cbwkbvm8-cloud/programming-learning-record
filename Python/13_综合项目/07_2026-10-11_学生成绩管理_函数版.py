"""
简介：用函数拆分的学生成绩管理系统，录入后统计总分、平均分、最高分和最低分。
功能：
- 循环录入学生姓名和成绩，输入 exit 结束录入。
- 用字典保存每个学生，并输出成绩列表和对应等级。
- 统计总分、平均分、最高分和最低分，没有数据时给出提示。
用途：练习函数拆分、列表与字典组合、while 循环和成绩等级判断。
最后修改：2026-10-10
"""
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
def show_students(students):
    print("----学生成绩列表----")
    for student in students:
        print("姓名：",student['name'])
        print("分数：", student['score'])
        print("等级：",get_level(student['score']))
def show_summary(students):
    if len(students) == 0:
        print("没有录入学生，无法统计成绩。")
    else:
        total = 0
        max_score = students[0]["score"]
        min_score = students[0]["score"]
        for student in students:
            total += student['score']
            if student["score"] > max_score:
                max_score = student["score"]
            if student["score"] < min_score:
                min_score = student["score"]
        print("============成绩统计==========")
        print("总分：", total)
        print("平均分：", total / len(students))
        print("最高分：", max_score)
        print("最低分：", min_score)
def main():
    students = []
    print("===============欢迎使用学生成绩管理系统================")
    while True:
        name = input("请输入学生姓名：")
        if name == "exit":
            break
        score = input("请输入学生成绩：")
        score = float(score)
        student = {"name": name, "score": score}
        students.append(student)
        print("添加成功！")
    show_students(students)
    show_summary(students)
main()
