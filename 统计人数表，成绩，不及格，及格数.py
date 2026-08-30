scores = [85,92,76,63,90,56,48]
print("所有成绩:",scores)
alls = sum(scores)
print("总成绩:",alls)
c = len(scores)
print("平均数:",alls/c)
m = max(scores)
print("最大值:",m)
mins = min(scores)
print("最小值:",mins)
scores = [85,92,76,63,90,56,48]
num = 0
mun = 0
a = []
b = []
for score in scores:
    if score < 60:
        a.append(score)
        num = num + 1
    else:
        b.append(score)
        mun = mun + 1
print("及格人数:",mun)
print("不及格人数:",num)
print("不及格:",a)
print("及格:",b)
                
                
