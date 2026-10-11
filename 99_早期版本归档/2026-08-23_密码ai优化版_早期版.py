while True:
    name = input("请输入你的用户名")

    if name == "":
        print("用户名为空 请重新输入密码")
        continue

    password = input("请输入你的密码")

    if name == "zach" and password == "123456":
        print("登录成功")
        break

    print("请检查用户名或者密码是否正确")
