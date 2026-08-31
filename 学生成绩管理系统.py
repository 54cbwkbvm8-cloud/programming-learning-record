print("="*82)
print("-"*30,"欢迎使用学生成绩管理系统","-"*30)
print("="*82)
xuesheng = []
chengji = []
while True:
    print("请选择程序:")
    print("1-添加学生")
    print("2-添加成绩")
    print("3-查看学生")
    print("4-查看成绩")
    print("5-计算平均分")
    print("6-判断是否及格")
    print("7-退出程序")
    gongneng = input()
    if gongneng == "1":
        name = input("请输入学生姓名")
        xuesheng.append(name)
        print("*****添加成功*****")
        continue
    elif gongneng == "2":
        fenshu = input("请输入分数")
        chengji.append(float(fenshu))
        print("*****添加成功*****")
        continue
    elif gongneng == "3":
        print(xuesheng)
        continue
    elif gongneng == "4":
        # 用循环变量 xueshengs/chengjis，而不是上一个残留下来的 name/fenshu
        for xueshengs, chengjis in zip(xuesheng, chengji):
            print("姓名:", xueshengs, "成绩:", chengjis, "分")
    elif gongneng == "5":
        # 用 len(chengji) 判断有没有成绩，而不是 sum(chengji) == 0
        if len(chengji) == 0:
            print("当前没有任何成绩信息，请添加成绩后再尝试")
        else:
            # 除以成绩个数，而不是姓名个数
            total = sum(chengji) / len(chengji)
            print("平均分:", total)
    elif gongneng == "6":
        for name, fenshu in zip(xuesheng, chengji):
            if fenshu < 60:
                print("姓名:", name, "不及格")
            else:
                print("姓名:", name, "及格")
    elif gongneng == "7":
        print("再见！欢迎下次使用Python学生信息登记系统")
        break
    else:
        print("编号不正确，请输入 1~7 的数字。")
