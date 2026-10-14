students = []
print("========学生成绩管理系统========")
print("接下来输入 3 名学生的姓名和成绩。")
for i in range(3):
    print(f"\n第{i + 1} 名学生")
    name = input(f"请输入第 {i+1} 名学生的姓名：")
    score = float(input(f"请输入 {name} 的成绩："))
    students.append({"name": name, "score": score})
with open("students.txt", "w", encoding="utf-8") as f:
    for s in students:
        f.write(f"{s['name']},{s['score']}\n")
print("\n学生成绩已保存到 students.txt 文件中。")
print("\n从 students.txt 文件中读取学生成绩：")
with open("students.txt", "r", encoding="utf-8") as f:
    for line in f:
        name, score = line.strip().split(",")
        print(f"{name} 的成绩是 {score}")
print("\n程序结束。")
