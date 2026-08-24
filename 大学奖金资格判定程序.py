print("****************")
print("大学奖金资格判定程序")
name = input("姓名:")
pg = float(input("平均成绩:"))
eg = float(input("英语成绩:"))
sj = input("是否挂科:")
has_sj = sj in ("yes","Y","是","YES","y")
if pg >= 85 and eg >= 70 and not has_sj:
    print("符合基本奖金申请条件")
else:
    print("暂时不符合基本奖金申请条件")
          

