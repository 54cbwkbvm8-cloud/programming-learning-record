"""
简介：使用字典实现一个可在内存中操作的联系人通讯录。
功能：
- 支持姓名与手机号的双向查询。
- 添加、修改、删除和查看联系人。
- 通过编号菜单持续操作直到退出。
用途：练习函数、字典、菜单循环和模块化组织。
最后修改：2026-09-09
"""
print("#"*20,"欢迎使用Python通讯录","#"*20)  # 打印 20 个 # 与欢迎语组成的标题
print("1-修改联系人")  # 菜单项 1：修改联系人
print("2-所有联系人")  # 菜单项 2：所有联系人
print("3-添加联系人")  # 菜单项 3：添加联系人
print("4-查询联系人手机号")  # 菜单项 4：按姓名查手机号
print("5-查询手机号的联系人姓名")  # 菜单项 5：按手机号查姓名
print("6-删除联系人")  # 菜单项 6：删除联系人
print("7-退出")  # 菜单项 7：退出

tongxunlu = {}  # 空字典：键是姓名，值是手机号
phone = {}  # 空字典：键是手机号，值是姓名

def search(name):  # 定义按姓名查找手机号的函数
    if name in tongxunlu:  # 如果该姓名存在于字典中
        print(f"{name}-->{tongxunlu[name]}")  # 打印姓名和对应手机号
    else:  # 姓名不存在
        print(f"未找到该联系人:{name}")  # 提示未找到该联系人

def search_phone(num):  # 定义按手机号查找姓名的函数
    if num in phone:  # 如果该手机号存在于字典中
        print(f"{num}-->{phone[num]}")  # 打印手机号和对应姓名
    else:  # 手机号不存在
        print(f"未找到该手机号:{num}")  # 提示未找到该手机号

def add(name, phone_num):  # 定义添加联系人的函数
    if name in tongxunlu:  # 如果姓名已存在
        print(f"已添加{name}联系人，无需重复添加")  # 提示已存在，不重复添加
    else:  # 姓名不存在
        tongxunlu[name] = phone_num  # 在姓名字典里登记手机号
        phone[phone_num] = name  # 在手机号字典里登记姓名
        print(f"已添加{name}联系人-->{phone_num}")  # 打印添加成功信息

def delete(name):  # 定义删除联系人的函数
    if name in tongxunlu:  # 如果姓名存在
        number = tongxunlu.pop(name)  # 从姓名字典弹出对应的手机号
        phone.pop(number)  # 再用该手机号删除手机号字典里的记录
        print(f"已成功删除{name}")  # 打印删除成功
    else:  # 姓名不存在
        print(f"删除失败！并未找到{name}")  # 打印删除失败

def modify(name, phone_num):  # 定义修改联系人手机号的函数
    if name in tongxunlu:  # 如果姓名存在
        old = tongxunlu[name]  # 取出原来登记的手机号
        tongxunlu[name] = phone_num  # 把姓名对应的手机号更新为新号码
        phone.pop(old)  # 删除旧手机号对应的记录
        phone[phone_num] = name  # 用新手机号重新登记姓名
        print(f"已修改:{name}-->{phone_num}")  # 打印修改成功
    else:  # 姓名不存在
        print(f"未发现{name},请添加后重试")  # 提示需要先添加

while True:  # 无限循环，直到用户选择退出
    gongneng = input("请输入你需要的程序（输入数字）")  # 接收用户选择的功能编号
    print("1-修改联系人")  # 再次打印菜单项 1
    print("2-所有联系人")  # 菜单项 2
    print("3-添加联系人")  # 菜单项 3
    print("4-查询联系人手机号")  # 菜单项 4
    print("5-查询手机号的联系人姓名")  # 菜单项 5
    print("6-删除联系人")  # 菜单项 6
    print("7-退出")  # 菜单项 7
    if gongneng == "1":  # 选择 1：修改联系人
        name = input("请输入姓名")  # 输入要修改的姓名
        phone_num = input("请输入手机号")  # 输入新的手机号
        modify(name, phone_num)  # 调用修改函数
    elif gongneng == "2":  # 选择 2：显示所有联系人
        for name, phone_num in tongxunlu.items():  # 遍历姓名字典的每个键值对
            print("姓名",name,"手机号",phone_num)  # 打印姓名和手机号
    elif gongneng == "3":  # 选择 3：添加联系人
        name = input("请输入姓名")  # 输入姓名
        phone_num = input("请输入手机号")  # 输入手机号
        add(name, phone_num)  # 调用添加函数
    elif gongneng == "4":  # 选择 4：按姓名查手机号
        name = input("请输入姓名")  # 输入姓名
        search(name)  # 调用按姓名查找的函数
    elif gongneng == "5":  # 选择 5：按手机号查姓名
        num = input("请输入手机号")  # 输入手机号
        search_phone(num)  # 调用按手机号查找的函数
    elif gongneng == "6":  # 选择 6：删除联系人
        name = input("请输入姓名")  # 输入姓名
        delete(name)  # 调用删除函数
    elif gongneng == "7":  # 选择 7：退出
        break  # 跳出循环，程序结束
    else:  # 输入其它数字
        print("输入的数字无效，请重新输入1～7的数字")  # 提示输入无效
