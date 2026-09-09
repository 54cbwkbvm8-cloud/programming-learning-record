scores = {"小明": "90", "小红": "85", "小刚": "76"}
all_scores = [int(num) for num in scores.values()]
pingjun = sum(all_scores) / len(all_scores)
zuigaofen = max(all_scores)
zuidifen = min(all_scores)
jigerenshu = 0
for score in all_scores:
    if score >= 60:      
        jigerenshu += 1
bujigerenshu = len(all_scores) - jigerenshu
print("平均成绩:", pingjun)
print("最高分:", zuigaofen)
print("最低分:", zuidifen)
print("及格人数:", jigerenshu)
print("不及格人数:", bujigerenshu)
print("-------------")
for name, score in sorted(scores.items()):
    print(name, score)
print("-"*20)
name = input("请输入你想要查找的学生")
if name in scores:
    print("查找成功")
    print("成绩:",scores[name])
else:
    print("未找到该学生")
