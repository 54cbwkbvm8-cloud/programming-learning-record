name = input("请输入你的用户名""----注意：""用户名和密码不能为空")
if name == "":
    print("用户名不能为空")
    name = input("请重新输入你的用户名")
    password = input("请输入你的密码")
    if name == "zach" and password == "123456":
        print("登录成功")
    else:
        print("请检查用户名或者密码是否正确")
else:
    password = input("请输入你的密码")    
    if name == "zach" and password == "123456":
        print("登录成功")
    else:
        print("请检查用户名或者密码是否正确")
