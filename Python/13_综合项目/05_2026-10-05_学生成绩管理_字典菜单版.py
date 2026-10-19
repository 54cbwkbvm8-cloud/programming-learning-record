"""
简介：用字典保存学生成绩，并通过菜单完成增删改查的综合练习。
功能：
- 添加、删除和修改学生信息，添加时检查重名。
- 录入并校验成绩，限制在 0 到 100 之间，非数字输入会被拦截。
- 查询单个学生、显示全部学生，并按 A～E 输出等级。
- 统计所有已录入成绩学生的平均分。
用途：综合练习字典嵌套、函数拆分、菜单循环和 try/except 异常处理。
最后修改：2026-10-05
"""
students = {}
def add_student():
    name = input("请输入学生的姓名：")
    if name in students:
        print("该学生已经存在了！")
        return
    students[name] = {"score":None}
    print("学生添加成功！")
def search_student():
    name = input("请输入要查找的学生姓名：")
    if name not in students:
        print("该学生不存在！")
        return
    student = students[name]
    score = student["score"]
    if score is None:
        print(f"姓名：{name},成绩：未输入！")
    else:
        grade = get_grade(score)
        print(f"姓名：{name},成绩：{score},等级：{grade}")
def input_score():
    name = input("请输入要输入成绩的学生姓名：")
    if name not in students:
        print("该学生不存在！请先添加学生！")
        return
    while True:
        try:
            score = float(input("请输入学生成绩："))
            if score < 0 or score > 100:
                print("成绩必须在0-100之间！")
                continue
            break
        except ValueError:
            print("输入错误，请输入数字！")
    students[name]["score"] = score
    print("成绩输入成功！")
def get_grade(score):
    if score >= 90:
        return "A"
    elif score >= 80:
        return "B"
    elif score >= 70:
        return "C"
    elif score >= 60:
        return "D"
    else:
        return "E"
def calculate_average():
    if not students:
        print("没有学生信息！")
        return
    total = 0
    count = 0
    for info in students.values():
        if info["score"] is None:
            continue
        total += info["score"]
        count += 1
    if count == 0:
        print("没有学生成绩信息！")
        return
    average = total / count
    print(f"平均成绩为：{average:.2f}")
def show_average_score():
    calculate_average()
def delete_student():
    name = input("请输入要删除的学生姓名：")
    if name not in students:
        print("该学生不存在！")
        return
    del students[name]
    print("学生删除成功！")
def modify_student():
    name = input("请输入要修改的学生姓名：")
    if name not in students:
        print("该学生不存在！")
        return
    while True:
        try:
            score = float(input("请输入新的学生成绩："))
            if score < 0 or score > 100:
                print("成绩必须在0-100之间！")
                continue
            break
        except ValueError:
            print("输入错误，请输入数字！")
    students[name]["score"] = score
    print("成绩修改成功！")
def show_all_students():
    if not students:
        print("没有学生信息！")
        return
    for name, info in students.items():
        score = info["score"]
        if score is None:
            print(f"姓名：{name},成绩：未输入！")
        else:
            print(f"姓名：{name},成绩：{score},等级：{get_grade(score)}")
def show_menu():
    print("==========欢迎使用学生成绩管理系统==========")
    print("1. 添加学生信息")
    print("2. 删除学生信息")
    print("3. 修改或者录入学生成绩")
    print("4. 查询学生信息")
    print("5. 显示所有学生信息")
    print("6. 计算平均成绩")
    print("7. 输入学生成绩")
    print("8. 退出")
    print("===========================================")
while True:
    show_menu()
    choice = input("请输入你的选择：")
    if choice == "1":
        add_student()
    elif choice == "2":
        delete_student()
    elif choice == "3":
        modify_student()
    elif choice == "4":
        search_student()
    elif choice == "5":
        show_all_students()
    elif choice == "6":
        show_average_score()
    elif choice == "7":
        input_score()
    elif choice == "8":
        break
    else:
        print("输入有误，请重新输入！请输入1-8之间的数字")
