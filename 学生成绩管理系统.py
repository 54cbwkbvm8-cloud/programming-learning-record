print("="*82)  # 打印 82 个等号，形成程序顶部边框
print("-"*30,"欢迎使用学生成绩管理系统","-"*30)  # 两侧各打印 30 个减号，中间是欢迎标语
print("="*82)  # 打印 82 个等号，形成程序底部边框
xuesheng = []  # 空列表，用于存放学生姓名
chengji = []  # 空列表，用于存放学生成绩
while True:  # 无限循环，持续提供菜单直到用户选择退出
    print("请选择程序:")  # 打印菜单提示
    print("1-添加学生")  # 菜单项 1：添加学生
    print("2-添加成绩")  # 菜单项 2：添加成绩
    print("3-查看学生")  # 菜单项 3：查看学生
    print("4-查看成绩")  # 菜单项 4：查看成绩
    print("5-计算平均分")  # 菜单项 5：计算平均分
    print("6-判断是否及格")  # 菜单项 6：判断是否及格
    print("7-退出程序")  # 菜单项 7：退出程序
    gongneng = input()  # 接收用户输入的功能编号
    if gongneng == "1":  # 选择 1：添加学生
        name = input("请输入学生姓名")  # 输入学生姓名
        xuesheng.append(name)  # 把姓名加入学生列表
        print("*****添加成功*****")  # 打印添加成功提示
        continue  # 回到菜单继续操作
    elif gongneng == "2":  # 选择 2：添加成绩
        fenshu = input("请输入分数")  # 输入分数
        try:  # 尝试把字符串转成浮点数
            chengji.append(float(fenshu))  # 转换成功则加入成绩列表
        except ValueError:  # 若输入的不是数字，捕获异常
            print("请输入数字！")  # 提示必须输入数字
            continue  # 回到菜单继续
        print("*****添加成功*****")  # 打印添加成功提示
        continue  # 回到菜单继续
    elif gongneng == "3":  # 选择 3：查看学生
        print(xuesheng)  # 打印学生姓名列表
        continue  # 回到菜单继续
    elif gongneng == "4":  # 选择 4：查看成绩
        # 用循环变量 xueshengs/chengjis，而不是上一个残留下来的 name/fenshu
        for xueshengs, chengjis in zip(xuesheng, chengji):  # 把学生姓名和成绩一一配对遍历
            print("姓名:", xueshengs, "成绩:", chengjis, "分")  # 打印每对学生姓名和成绩
    elif gongneng == "5":  # 选择 5：计算平均分
        # 用 len(chengji) 判断有没有成绩，而不是 sum(chengji) == 0
        if len(chengji) == 0:  # 成绩列表为空说明还没有任何成绩
            print("当前没有任何成绩信息，请添加成绩后再尝试")  # 提示先添加成绩
        else:  # 有成绩存在
            # 除以成绩个数，而不是姓名个数
            total = sum(chengji) / len(chengji)  # 总成绩除以成绩个数得到平均分
            print("平均分:", total)  # 打印平均分
    elif gongneng == "6":  # 选择 6：判断是否及格
        for name, fenshu in zip(xuesheng, chengji):  # 配对遍历姓名和成绩
            if fenshu < 60:  # 成绩小于 60 判定为不及格
                print("姓名:", name, "不及格")  # 打印不及格结果
            else:  # 否则判定为及格
                print("姓名:", name, "及格")  # 打印及格结果
    elif gongneng == "7":  # 选择 7：退出程序
        print("再见！欢迎下次使用Python学生信息登记系统")  # 打印再见语
        break  # 跳出 while 循环，程序结束
    else:  # 输入的不是 1~7 的数字
        print("编号不正确，请输入 1~7 的数字。")  # 提示编号不正确
