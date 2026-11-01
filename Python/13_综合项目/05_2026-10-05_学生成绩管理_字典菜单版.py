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
# # ====================================================================
# # 文件名：05_2026-10-05_学生成绩管理_字典菜单版.py
# # 作用：用"字典套字典"保存学生成绩，通过菜单完成增、删、改、查：
# #       1 添加学生（检查重名）  2 删除学生  3 修改成绩  4 查询单个学生
# #       5 显示所有学生（含等级 A～E）  6 计算平均成绩  7 录入成绩  8 退出
# #       成绩必须是 0～100 的数字，输入不合法会提示并重新输入。
# #
# # 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
# #   运行：python3 "05_2026-10-05_学生成绩管理_字典菜单版.py"
# #   说明：Python 是边读边执行的，不需要先编译。
# # ====================================================================
# # 文件开头用三引号包起来的文字叫"文档字符串"（docstring），用来说明这个文件做什么。运行时不会输出它。
# """
# 简介：用字典保存学生成绩，并通过菜单完成增删改查的综合练习。
# 功能：
# - 添加、删除和修改学生信息，添加时检查重名。
# - 录入并校验成绩，限制在 0 到 100 之间，非数字输入会被拦截。
# - 查询单个学生、显示全部学生，并按 A～E 输出等级。
# - 统计所有已录入成绩学生的平均分。
# 用途：综合练习字典嵌套、函数拆分、菜单循环和 try/except 异常处理。
# 最后修改：2026-10-05
# """
#
#
# # --------------------------------------------------------------------
# # 第一部分：保存数据的字典
# # --------------------------------------------------------------------
# # {} 是"空字典"。字典用"键: 值"成对保存数据，用键就能直接找到对应的值。
# # 这里 students 的键是学生姓名，值又是一个小字典，例如 {"score": 88.0}，保存这个学生的成绩。
# # "字典里套字典"，以后还可以往小字典里再加年龄、学号等信息。
# students = {}
#
#
# # --------------------------------------------------------------------
# # 第二部分：功能函数 —— 添加、查询
# # --------------------------------------------------------------------
# # def 函数名(): 用来定义函数 = 一段有名字、可以反复使用的代码。这些函数都没有参数，直接用上面的全局字典 students。
# def add_student():
#     # input(提示语)：显示提示语，等用户输入并按回车，把输入的内容当作字符串返回。
#     name = input("请输入学生的姓名：")
#
#     # 对字典来说，in 检查"这个键是否存在"：这个姓名是不是已经添加过了。
#     if name in students:
#         print("该学生已经存在了！")
#
#         # return 单独写（后面不带值），表示"提前结束这个函数"，下面的代码不再执行。这是"先处理出错情况，再往下写正常流程"的常见写法。
#         return
#
#     # 字典[键] = 值：往字典里添加一对数据。值是一个小字典 {"score": None}。
#     # None 是 Python 里"什么都没有"的特殊值：表示这个学生已经添加了，但成绩还没有录入。
#     students[name] = {"score":None}
#     print("学生添加成功！")
#
# # 查询一个学生。
# def search_student():
#     name = input("请输入要查找的学生姓名：")
#
#     # not in 就是"不在里面"：这个姓名不存在，提示后直接 return 结束。
#     if name not in students:
#         print("该学生不存在！")
#         return
#
#     # 用键取出这个学生对应的小字典。
#     student = students[name]
#
#     # 再用键 "score" 取出成绩。
#     score = student["score"]
#
#     # is None 用来判断"是不是 None"（比 == 更规范）。成绩是 None，说明还没录入。
#     if score is None:
#         print(f"姓名：{name},成绩：未输入！")
#     else:
#         # 调用下面的 get_grade 函数，根据成绩得到等级。函数的定义写在后面也可以，因为调用发生在程序运行时，那时函数已经定义好了。
#         grade = get_grade(score)
#
#         # f"..." 叫 f-string（格式化字符串）：字符串前面加 f，里面的 {} 会被替换成变量的值。
#         print(f"姓名：{name},成绩：{score},等级：{grade}")
#
#
# # --------------------------------------------------------------------
# # 第三部分：功能函数 —— 录入成绩
# # --------------------------------------------------------------------
# # 给一个已经存在的学生录入成绩。
# def input_score():
#     name = input("请输入要输入成绩的学生姓名：")
#
#     # 学生不存在，提示先添加，return 结束。
#     if name not in students:
#         print("该学生不存在！请先添加学生！")
#         return
#
#     # while 条件: 的意思是"条件为真，就一直重复缩进里的代码"。True 永远为真，是"无限循环"：成绩合格就 break，不合格就重新输入。
#     while True:
#         # try / except 是"异常处理"：把可能出错的代码放进 try 里；真的出错了，程序不会崩溃，而是跳到 except 里处理。
#         try:
#             # 先执行里面的 input 得到字符串，再用 float(...) 转成小数。如果输入的不是数字（比如 abc），float 会产生 ValueError，跳到下面的 except。
#             score = float(input("请输入学生成绩："))
#
#             # or 读作"或者"：成绩小于 0 或者大于 100，都不合法。
#             if score < 0 or score > 100:
#                 print("成绩必须在0-100之间！")
#
#                 # continue 的意思是"跳过本轮循环剩下的代码，回到 while 的开头"，让用户重新输入。
#                 continue
#
#             # 走到这一行说明成绩是合法的数字，break 立刻跳出 while 循环。
#             break
#
#         # except ValueError: 处理"转不成数字"的情况：提示后，while 会继续下一轮，重新输入。
#         except ValueError:
#             print("输入错误，请输入数字！")
#
#     # students[name] 取出这个学生的小字典，["score"] = score 把成绩写进去。两层方括号：先按姓名找，再按 "score" 找。
#     students[name]["score"] = score
#     print("成绩输入成功！")
#
#
# # --------------------------------------------------------------------
# # 第四部分：功能函数 —— 成绩等级
# # --------------------------------------------------------------------
# # if ... elif ... else 是"多选一"：从上往下检查，遇到第一个成立的就执行；能走到某一行，说明前面的条件都不成立，所以只写下限。
# # 90 及以上 "A"；80～89 "B"；70～79 "C"；60～69 "D"；其余 "E"。return 把结果交回给调用者。
# def get_grade(score):
#     if score >= 90:
#         return "A"
#     elif score >= 80:
#         return "B"
#     elif score >= 70:
#         return "C"
#     elif score >= 60:
#         return "D"
#     else:
#         return "E"
#
#
# # --------------------------------------------------------------------
# # 第五部分：功能函数 —— 计算平均成绩
# # --------------------------------------------------------------------
# def calculate_average():
#     # not 是"取反"。空字典在条件里相当于 False，所以 not students 表示"一个学生都没有"。
#     if not students:
#         print("没有学生信息！")
#         return
#
#     # total 存总分，count 存"已经录入成绩的人数"，都从 0 开始。
#     total = 0
#     count = 0
#
#     # 字典.values() 取出所有的值（每个学生的小字典）。for 让 info 依次取每一个。
#     for info in students.values():
#         # 这个学生还没录入成绩，跳过他。
#         if info["score"] is None:
#             # continue 在 for 循环里的意思是"跳过本轮剩下的代码，直接处理下一个学生"。
#             continue
#
#         # += 是"加上再赋值"：把这个学生的成绩累加进 total。
#         total += info["score"]
#         count += 1
#
#     # 没有任何人录入成绩，提示后返回（避免下面除以 0）。
#     if count == 0:
#         print("没有学生成绩信息！")
#         return
#
#     # 平均成绩 = 总分 ÷ 已录入成绩的人数。/ 是除法，结果是小数。
#     average = total / count
#
#     # {average:.2f}：冒号后面是格式，.2f 表示保留 2 位小数。
#     print(f"平均成绩为：{average:.2f}")
#
# # 这个函数只是调用 calculate_average，作用是给"显示平均成绩"起一个名字（多包一层）。
# def show_average_score():
#     calculate_average()
#
#
# # --------------------------------------------------------------------
# # 第六部分：功能函数 —— 删除、修改、显示
# # --------------------------------------------------------------------
# def delete_student():
#     name = input("请输入要删除的学生姓名：")
#     if name not in students:
#         print("该学生不存在！")
#         return
#
#     # del 字典[键]：删除字典里的这一对数据（这个学生就没有了）。
#     del students[name]
#     print("学生删除成功！")
#
# # 修改成绩的流程和录入成绩几乎一样：先检查学生存在，再用"无限循环 + try / except"输入合法的新成绩。
# def modify_student():
#     name = input("请输入要修改的学生姓名：")
#     if name not in students:
#         print("该学生不存在！")
#         return
#     while True:
#         try:
#             score = float(input("请输入新的学生成绩："))
#             if score < 0 or score > 100:
#                 print("成绩必须在0-100之间！")
#                 continue
#             break
#         except ValueError:
#             print("输入错误，请输入数字！")
#
#     # 把新成绩写进去，原来的成绩被覆盖。
#     students[name]["score"] = score
#     print("成绩修改成功！")
# def show_all_students():
#     if not students:
#         print("没有学生信息！")
#         return
#
#     # 字典.items() 取出所有"键值对"，每一对是 (姓名, 小字典)。for 每次把一对拆开，分别给 name 和 info（叫"解包"）。
#     for name, info in students.items():
#         # 取出成绩。下面根据成绩是不是 None，输出"未输入"或者成绩和等级。
#         score = info["score"]
#         if score is None:
#             print(f"姓名：{name},成绩：未输入！")
#         else:
#             print(f"姓名：{name},成绩：{score},等级：{get_grade(score)}")
#
#
# # --------------------------------------------------------------------
# # 第七部分：菜单和主循环
# # --------------------------------------------------------------------
# # 把菜单输出出来。每次循环开头都会调用它。
# def show_menu():
#     print("==========欢迎使用学生成绩管理系统==========")
#     print("1. 添加学生信息")
#     print("2. 删除学生信息")
#     print("3. 修改或者录入学生成绩")
#     print("4. 查询学生信息")
#     print("5. 显示所有学生信息")
#     print("6. 计算平均成绩")
#     print("7. 输入学生成绩")
#     print("8. 退出")
#     print("===========================================")
#
# # 无限循环：要靠 break 才能退出。
# while True:
#     # 先显示菜单，再读用户的选择。
#     show_menu()
#
#     # choice 是字符串，所以下面都和字符串比较（带引号）。
#     choice = input("请输入你的选择：")
#
#     # if ... elif ... else 是"多选一"：根据选项调用对应的函数，其余的跳过。
#     if choice == "1":
#         add_student()
#     elif choice == "2":
#         delete_student()
#     elif choice == "3":
#         modify_student()
#     elif choice == "4":
#         search_student()
#     elif choice == "5":
#         show_all_students()
#     elif choice == "6":
#         show_average_score()
#     elif choice == "7":
#         input_score()
#
#     # 选 8：退出。
#     elif choice == "8":
#         # break 的意思是"立刻跳出整个 while 循环"，程序结束。
#         break
#
#     # 前面的编号都不是，提示输入有误，然后回到循环开头。
#     else:
#         print("输入有误，请重新输入！请输入1-8之间的数字")
#
# # ====================================================================
# # 测试时依次输入（每行输完按一次回车）：
# #   1
# #   小明
# #   1
# #   小红
# #   1
# #   小明
# #   3
# #   小明
# #   abc
# #   120
# #   88
# #   7
# #   小红
# #   59.5
# #   7
# #   小刚
# #   4
# #   小明
# #   4
# #   小刚
# #   5
# #   6
# #   2
# #   小红
# #   5
# #   9
# #   8
# #
# # 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
# #   ==========欢迎使用学生成绩管理系统==========
# #   1. 添加学生信息
# #   2. 删除学生信息
# #   3. 修改或者录入学生成绩
# #   4. 查询学生信息
# #   5. 显示所有学生信息
# #   6. 计算平均成绩
# #   7. 输入学生成绩
# #   8. 退出
# #   ===========================================
# #   请输入你的选择：请输入学生的姓名：学生添加成功！
# #   ==========欢迎使用学生成绩管理系统==========
# #   1. 添加学生信息
# #   2. 删除学生信息
# #   3. 修改或者录入学生成绩
# #   4. 查询学生信息
# #   5. 显示所有学生信息
# #   6. 计算平均成绩
# #   7. 输入学生成绩
# #   8. 退出
# #   ===========================================
# #   请输入你的选择：请输入学生的姓名：学生添加成功！
# #   ==========欢迎使用学生成绩管理系统==========
# #   1. 添加学生信息
# #   2. 删除学生信息
# #   3. 修改或者录入学生成绩
# #   4. 查询学生信息
# #   5. 显示所有学生信息
# #   6. 计算平均成绩
# #   7. 输入学生成绩
# #   8. 退出
# #   ===========================================
# #   请输入你的选择：请输入学生的姓名：该学生已经存在了！
# #   ==========欢迎使用学生成绩管理系统==========
# #   1. 添加学生信息
# #   2. 删除学生信息
# #   ……（中间省略 111 行）
# #   1. 添加学生信息
# #   2. 删除学生信息
# #   3. 修改或者录入学生成绩
# #   4. 查询学生信息
# #   5. 显示所有学生信息
# #   6. 计算平均成绩
# #   7. 输入学生成绩
# #   8. 退出
# #   ===========================================
# #   请输入你的选择：
# # ====================================================================
