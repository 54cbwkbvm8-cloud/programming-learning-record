"""
简介：带 JSON 持久化功能的学生成绩管理系统。
功能：
- 添加、删除、修改和查询学生及成绩。
- 校验姓名重复、空姓名和非法成绩。
- 使用 students.json 保存数据并在启动时自动加载。
用途：综合练习函数、列表、字典、文件读写、JSON 和 Path。
最后修改：2026-09-11
"""
import json
from pathlib import Path
DATA_FILE = Path(__file__).with_name("students.json")
def save_data():
    data = {
        "students": xuesheng
    }
    with DATA_FILE.open("w", encoding="utf-8") as f:
        json.dump(data,f,ensure_ascii=False, indent=2)
def load_data():
    if not DATA_FILE.exists():
        return []
    with DATA_FILE.open("r",encoding="utf-8") as f:
        data = json.load(f)
        return data.get("students", [])
xuesheng = load_data()
print("="*82)
print("-"*30,"欢迎使用学生成绩管理系统","-"*30)
print("="*82)
while True:
    print("请选择程序:")
    print("1-添加学生")
    print("2-删除学生")
    print("3-修改学生")
    print("4-查看学生")
    print("5-保存JSON数据")
    print("6-退出程序")
    gongneng = input().strip()
    if gongneng == "1":
        name = input("请输入学生姓名").strip()
        if name == "":
            print("学生姓名不能为空，请重新输入。")
            continue
        yijing_cunzai = False
        for student in xuesheng:
            if student.get("name") == name:
                yijing_cunzai = True
                break
        if yijing_cunzai:
            print("该学生已存在，无法重复添加。")
            continue
        fenshu_text = input("请输入学生成绩").strip()
        try:
            fenshu = float(fenshu_text)
            if fenshu.is_integer():
                fenshu = int(fenshu)
        except ValueError:
            print("成绩必须是数字，例如:88.5或者 77.0，请重新输入。")
            continue
        
        xuesheng.append({"name": name, "score": fenshu})
        save_data()
        print("*****添加成功*****")
        continue
    elif gongneng == "2":
        name = input("请输入要删除的学生姓名").strip()
        yijing_shanchu = False
        for student in xuesheng:
            if student.get("name") == name:
                xuesheng.remove(student)
                yijing_shanchu = True
                break
        if yijing_shanchu:
            save_data()
            print("删除成功！")
        else:
            print("未找到该学生，请检查姓名是否正确,无法删除。")
        continue
    elif gongneng == "3":
        name = input("请输入要修改的学生姓名").strip()
        target = None
        for student in xuesheng:
            if student.get("name") == name:
                target = student
                break
        if target is None:
            print("未找到该学生,暂时无法修改。")
            continue
        new_name = input("请输入新的学生姓名(直接回车表示不修改内容):").strip()
        gai_guo = False
        if new_name != "":
            chongfu = False
            for student in xuesheng:
                if student is not target and student.get("name") == new_name:
                    chongfu = True
                    break
            if chongfu:
                print("该学生已存在，没有姓名可以修改。")
            else:
                target["name"] = new_name
                gai_guo = True
        new_score_text = input("请输入新的学生成绩(直接回车表示不修改内容):").strip()
        if new_score_text != "":
            try:
                new_score = float(new_score_text)
                if new_score.is_integer():
                    new_score = int(new_score)
            except ValueError:
                print("新成绩必须是数字，成绩没有被修改。")
            else:
                target["score"] = new_score
                gai_guo = True
        if gai_guo:
            save_data()
            print("******修改成功！******")
        else:
            print("本次没有修改任何内容。")
        continue
    elif gongneng == "4":
        chaxun_name = input("请输入要查询的学生姓名").strip()
        zhaodao = False
        for student in xuesheng:
            name = student.get("name","")
            if chaxun_name == "" or chaxun_name in name:
                fenshu = student.get("score", "暂无数据")
                print("姓名:", name, "成绩:", fenshu, "分")
                zhaodao = True
        if not zhaodao:
            print("未找到符合条件的学生。")
        continue
    elif gongneng == "5":
        save_data()
        print("*****保存成功*****")
        continue
    elif gongneng == "6":
        save_data()
        print("再见！欢迎下次使用Python学生信息登记系统")
        break
    else:
        print("编号不正确，请输入 1~6 的数字。")
# # ====================================================================
# # 文件名：06_2026-08-31_学生成绩管理系统_JSON持久化版.py
# # 作用：带"数据持久化"的学生成绩管理系统：学生数据用 JSON 文件 students.json 保存，
# #       程序启动时自动读取，增、删、改之后自动保存，所以关掉程序再打开，数据还在。
# #       菜单：1 添加学生  2 删除学生  3 修改学生  4 查看（可按姓名模糊查询）  5 保存数据  6 退出
# #       说明：students.json 会生成在这个 .py 文件所在的文件夹里。
# #
# # 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
# #   运行：python3 "06_2026-08-31_学生成绩管理系统_JSON持久化版.py"
# #   说明：Python 是边读边执行的，不需要先编译。
# # ====================================================================
# # 文件开头用三引号包起来的文字叫"文档字符串"（docstring），用来说明这个文件做什么。运行时不会输出它。
# """
# 简介：带 JSON 持久化功能的学生成绩管理系统。
# 功能：
# - 添加、删除、修改和查询学生及成绩。
# - 校验姓名重复、空姓名和非法成绩。
# - 使用 students.json 保存数据并在启动时自动加载。
# 用途：综合练习函数、列表、字典、文件读写、JSON 和 Path。
# 最后修改：2026-09-11
# """
#
#
# # --------------------------------------------------------------------
# # 第一部分：引入工具，设置数据文件
# # --------------------------------------------------------------------
# # import json：引入 Python 自带的 json 工具箱，用来把数据保存成 JSON 文件（一种常见的、人也能读的数据格式）。
# import json
#
# # Path 是"路径"工具，用来方便地表示文件和文件夹的位置。
# from pathlib import Path
#
# # __file__ 是"当前这个 .py 文件的路径"；.with_name("students.json") 表示"同一个文件夹里，名字换成 students.json 的那个路径"。
# # 所以数据文件总是和程序放在一起，不管你在哪里运行程序。全部大写的变量名，是"常量"的习惯写法。
# DATA_FILE = Path(__file__).with_name("students.json")
#
#
# # --------------------------------------------------------------------
# # 第二部分：保存和读取数据的函数
# # --------------------------------------------------------------------
# # def 函数名(): 用来定义函数 = 一段有名字、可以反复使用的代码。save_data 把学生数据写进文件。
# def save_data():
#     # {...} 是字典：用"键: 值"成对保存数据。这里把学生列表 xuesheng 放在键 "students" 下面，外面再包一层字典，
#     # 以后想加别的数据（比如设置），也方便扩展。
#     data = {
#         "students": xuesheng
#     }
#
#     # 路径对象.open(模式, encoding=...)：打开文件。"w" 表示写入：文件不存在就新建，存在就清空后重写。
#     # encoding="utf-8" 指定文字编码，写中文必须写对。
#     # with ... as f: 是"上下文管理"写法：代码块结束后，Python 会自动关闭文件。
#     with DATA_FILE.open("w", encoding="utf-8") as f:
#         # json.dump(数据, 文件, ...)：把数据写进文件，格式是 JSON。ensure_ascii=False：中文原样写入；indent=2：每层缩进 2 个空格，方便阅读。
#         json.dump(data,f,ensure_ascii=False, indent=2)
#
# # 读取数据：程序启动时调用，返回学生列表。
# def load_data():
#     # 路径对象.exists()：判断这个文件是否存在。not 是"取反"：文件不存在（第一次运行）时，进入这个 if。
#     if not DATA_FILE.exists():
#         # 返回空列表 []，表示"还没有任何学生"。return 会立刻结束函数。
#         return []
#
#     # "r" 表示只读（read）：打开已有的数据文件。
#     with DATA_FILE.open("r",encoding="utf-8") as f:
#         # json.load(文件)：从文件里读出 JSON 数据，还原成 Python 的字典 / 列表。
#         data = json.load(f)
#
#         # 字典.get(键, 默认值)：取出这个键对应的值；如果键不存在，不报错，而是返回默认值（这里是空列表）。
#         # 比 data["students"] 更安全：文件内容不完整时也不会报错。
#         return data.get("students", [])
#
#
# # --------------------------------------------------------------------
# # 第三部分：启动时读取数据，输出欢迎标题
# # --------------------------------------------------------------------
# # 调用函数读取数据，保存到变量 xuesheng（学生）。xuesheng 是一个列表，每个元素是一个字典，例如 {"name": "小明", "score": 88.5}。
# xuesheng = load_data()
#
# # 字符串乘整数 = 把字符串重复若干次："=" * 82 得到 82 个等号，用来画分隔线。
# print("="*82)
# print("-"*30,"欢迎使用学生成绩管理系统","-"*30)
# print("="*82)
#
#
# # --------------------------------------------------------------------
# # 第四部分：菜单循环
# # --------------------------------------------------------------------
# # while 条件: 的意思是"条件为真，就一直重复缩进里的代码"。True 永远为真，是"无限循环"，要靠 break 退出。
# while True:
#     # 每一轮先把菜单显示出来。
#     print("请选择程序:")
#     print("1-添加学生")
#     print("2-删除学生")
#     print("3-修改学生")
#     print("4-查看学生")
#     print("5-保存JSON数据")
#     print("6-退出程序")
#
#     # input() 等用户输入并按回车，得到字符串。.strip() 去掉字符串两端的空白（空格、换行等），避免多输入的空格影响判断。
#     # gongneng（功能）保存用户选的编号。
#     gongneng = input().strip()
#
#     # if ... elif ... else 是"多选一"：从上往下检查，遇到第一个成立的就执行，其余跳过。注意 gongneng 是字符串，所以和 "1" 比较。
#     if gongneng == "1":
#
#         # --------------------------------------------------------------------
#         # 功能 1：添加学生
#         # --------------------------------------------------------------------
#         name = input("请输入学生姓名").strip()
#
#         # "" 是空字符串（什么都没输入）。姓名不能为空。
#         if name == "":
#             print("学生姓名不能为空，请重新输入。")
#
#             # continue 的意思是"跳过本轮循环剩下的代码，回到 while 的开头"，也就是重新显示菜单。
#             continue
#
#         # yijing_cunzai（已经存在）是"布尔值"变量（只有 True 和 False 两种取值），先假设"不存在"。
#         yijing_cunzai = False
#
#         # for 变量 in 列表: 让 student 依次取列表里的每一个学生（一个字典），每取一个，把缩进的代码执行一遍。
#         for student in xuesheng:
#             # student.get("name")：取出这个学生的姓名（键不存在时返回 None，不会报错）。和输入的姓名比较，相同就是重名。
#             if student.get("name") == name:
#                 yijing_cunzai = True
#
#                 # break 的意思是"立刻跳出整个 for 循环"：已经发现重名，不用再往后找。
#                 break
#
#         # 循环结束后，根据标志变量判断：重名就提示并 continue，不再添加。
#         if yijing_cunzai:
#             print("该学生已存在，无法重复添加。")
#             continue
#
#         # 先把成绩当文字读进来，下面再转成数字。
#         fenshu_text = input("请输入学生成绩").strip()
#
#         # try / except 是"异常处理"：把可能出错的代码放进 try 里；真的出错了，程序不会崩溃，而是跳到 except 里处理。
#         try:
#             # float(...) 把字符串转成小数。输入的不是数字（比如 abc），会产生 ValueError，跳到 except。
#             fenshu = float(fenshu_text)
#
#             # 小数.is_integer()：判断这个小数是不是"整数值"（比如 90.0）。是的话，下一行把它转成整数 90，保存时显示成 90 而不是 90.0。
#             if fenshu.is_integer():
#                 fenshu = int(fenshu)
#
#         # 成绩不是数字：提示后 continue，重新开始，学生不会被添加。
#         except ValueError:
#             print("成绩必须是数字，例如:88.5或者 77.0，请重新输入。")
#             continue
#         
#         # 列表.append(元素)：把一个新的学生字典添加到列表末尾。
#         xuesheng.append({"name": name, "score": fenshu})
#
#         # 调用函数，把数据保存到文件：每次添加之后立刻保存，这样数据不会丢。
#         save_data()
#         print("*****添加成功*****")
#         continue
#
#     # --------------------------------------------------------------------
#     # 功能 2：删除学生
#     # --------------------------------------------------------------------
#     elif gongneng == "2":
#         name = input("请输入要删除的学生姓名").strip()
#
#         # yijing_shanchu（已经删除）：标志变量，先假设"没删除"。
#         yijing_shanchu = False
#         for student in xuesheng:
#             if student.get("name") == name:
#                 # 列表.remove(元素)：从列表里删除这个元素（第一个相等的）。然后马上 break，所以在循环里删除元素是安全的。
#                 xuesheng.remove(student)
#                 yijing_shanchu = True
#                 break
#
#         # 删除成功才保存并提示；否则提示没找到。
#         if yijing_shanchu:
#             save_data()
#             print("删除成功！")
#         else:
#             print("未找到该学生，请检查姓名是否正确,无法删除。")
#         continue
#
#     # --------------------------------------------------------------------
#     # 功能 3：修改学生
#     # --------------------------------------------------------------------
#     elif gongneng == "3":
#         name = input("请输入要修改的学生姓名").strip()
#
#         # target 保存"要修改的那个学生"。None 是 Python 里"什么都没有"的特殊值，表示还没找到。
#         target = None
#         for student in xuesheng:
#             if student.get("name") == name:
#                 # 找到了，让 target 指向这个学生字典。字典是"可变"的：之后通过 target 修改，列表里的那个学生也会跟着变（它们是同一个对象）。
#                 target = student
#                 break
#
#         # is None 用来判断"是不是 None"。没找到就提示并 continue。
#         if target is None:
#             print("未找到该学生,暂时无法修改。")
#             continue
#
#         # 直接按回车，得到的就是空字符串，表示"不修改"。
#         new_name = input("请输入新的学生姓名(直接回车表示不修改内容):").strip()
#
#         # gai_guo（改过）：标志变量，记录这次有没有真的修改了内容。
#         gai_guo = False
#         if new_name != "":
#             chongfu = False
#             for student in xuesheng:
#                 # 检查新姓名是否和"别的学生"重名。is not 表示"不是同一个对象"：要排除 target 自己（自己的名字当然和自己一样）。
#                 if student is not target and student.get("name") == new_name:
#                     chongfu = True
#                     break
#             if chongfu:
#                 print("该学生已存在，没有姓名可以修改。")
#             else:
#                 # 字典[键] = 值：修改字典里这个键对应的值。
#                 target["name"] = new_name
#                 gai_guo = True
#         new_score_text = input("请输入新的学生成绩(直接回车表示不修改内容):").strip()
#         if new_score_text != "":
#             # 同样用 try / except 保护：输入的不是数字，成绩就不修改。
#             try:
#                 new_score = float(new_score_text)
#                 if new_score.is_integer():
#                     new_score = int(new_score)
#             except ValueError:
#                 print("新成绩必须是数字，成绩没有被修改。")
#
#             # try 后面的 else：只有 try 里没有出错时才执行。这里表示"成绩转换成功，才把新成绩写进去"。
#             else:
#                 target["score"] = new_score
#                 gai_guo = True
#
#         # 有任何一项被修改，才保存并提示修改成功；否则提示没有修改。
#         if gai_guo:
#             save_data()
#             print("******修改成功！******")
#         else:
#             print("本次没有修改任何内容。")
#         continue
#
#     # --------------------------------------------------------------------
#     # 功能 4：查看学生（可以模糊查询）
#     # --------------------------------------------------------------------
#     elif gongneng == "4":
#         # chaxun_name（查询姓名）：输入姓名的一部分也可以；直接回车表示查看所有学生。
#         chaxun_name = input("请输入要查询的学生姓名").strip()
#         zhaodao = False
#         for student in xuesheng:
#             # .get(键, 默认值)：取姓名，如果没有这个键，就用空字符串。
#             name = student.get("name","")
#
#             # or 读作"或者"：查询词是空的（查看全部），或者 查询词 in 姓名。
#             # 字符串 in 字符串 判断"一个字符串是不是另一个字符串的一部分"，所以输入"红"能查到"小红"。
#             if chaxun_name == "" or chaxun_name in name:
#                 # 取成绩，如果这个学生没有成绩，就显示"暂无数据"。
#                 fenshu = student.get("score", "暂无数据")
#                 print("姓名:", name, "成绩:", fenshu, "分")
#                 zhaodao = True
#
#         # zhaodao（找到）是标志变量。循环结束后一个都没找到，就提示没有。
#         if not zhaodao:
#             print("未找到符合条件的学生。")
#         continue
#
#     # --------------------------------------------------------------------
#     # 功能 5：手动保存
#     # --------------------------------------------------------------------
#     elif gongneng == "5":
#         save_data()
#         print("*****保存成功*****")
#         continue
#
#     # --------------------------------------------------------------------
#     # 功能 6：退出
#     # --------------------------------------------------------------------
#     # 退出之前再保存一次，确保数据没丢。
#     elif gongneng == "6":
#         save_data()
#         print("再见！欢迎下次使用Python学生信息登记系统")
#
#         # break 立刻跳出整个 while 循环，程序结束。
#         break
#
#     # 前面的编号都不是，提示编号不正确，然后回到循环开头。
#     else:
#         print("编号不正确，请输入 1~6 的数字。")
#
# # ====================================================================
# # 测试时依次输入（每行输完按一次回车）：
# #   1
# #   小明
# #   88.5
# #   1
# #   小红
# #   90
# #   1
# #   
# #   1
# #   小明
# #   1
# #   小刚
# #   abc
# #   4
# #   
# #   3
# #   小红
# #   
# #   95
# #   3
# #   小刚
# #   4
# #   红
# #   2
# #   小明
# #   2
# #   小明
# #   5
# #   9
# #   6
# #
# # 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
# # （第一次运行时 students.json 还不存在，所以一开始没有学生；之后每次运行都会读取上一次保存的数据）
# #   ==================================================================================
# #   ------------------------------ 欢迎使用学生成绩管理系统 ------------------------------
# #   ==================================================================================
# #   请选择程序:
# #   1-添加学生
# #   2-删除学生
# #   3-修改学生
# #   4-查看学生
# #   5-保存JSON数据
# #   6-退出程序
# #   请输入学生姓名请输入学生成绩*****添加成功*****
# #   请选择程序:
# #   1-添加学生
# #   2-删除学生
# #   3-修改学生
# #   4-查看学生
# #   5-保存JSON数据
# #   6-退出程序
# #   请输入学生姓名请输入学生成绩*****添加成功*****
# #   请选择程序:
# #   1-添加学生
# #   2-删除学生
# #   3-修改学生
# #   4-查看学生
# #   5-保存JSON数据
# #   6-退出程序
# #   请输入学生姓名学生姓名不能为空，请重新输入。
# #   请选择程序:
# #   1-添加学生
# #   2-删除学生
# #   3-修改学生
# #   4-查看学生
# #   5-保存JSON数据
# #   6-退出程序
# #   请输入学生姓名该学生已存在，无法重复添加。
# #   请选择程序:
# #   ……（中间省略 70 行）
# #   6-退出程序
# #   编号不正确，请输入 1~6 的数字。
# #   请选择程序:
# #   1-添加学生
# #   2-删除学生
# #   3-修改学生
# #   4-查看学生
# #   5-保存JSON数据
# #   6-退出程序
# #   再见！欢迎下次使用Python学生信息登记系统
# # ====================================================================
