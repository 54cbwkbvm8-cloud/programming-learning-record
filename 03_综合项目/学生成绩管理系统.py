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
