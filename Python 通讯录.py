print("#"*20,"欢迎使用Python通讯录","#"*20)
print("1-修改联系人")
print("2-所有联系人")
print("3-添加联系人")
print("4-查询联系人手机号")
print("5-查询手机号的联系人姓名")
print("6-删除联系人")
print("7-退出")

tongxunlu = {}
phone = {}

def search(name):
    if name in tongxunlu:
        print(f"{name}-->{tongxunlu[name]}")
    else:
        print(f"未找到该联系人:{name}")

def search_phone(num):
    if num in phone:
        print(f"{num}-->{phone[num]}")
    else:
        print(f"未找到该手机号:{num}")

def add(name, phone_num):
    if name in tongxunlu:
        print(f"已添加{name}联系人，无需重复添加")
    else:
        tongxunlu[name] = phone_num
        phone[phone_num] = name
        print(f"已添加{name}联系人-->{phone_num}")

def delete(name):
    if name in tongxunlu:
        number = tongxunlu.pop(name)
        phone.pop(number)
        print(f"已成功删除{name}")
    else:
        print(f"删除失败！并未找到{name}")

def modify(name, phone_num):
    if name in tongxunlu:
        old = tongxunlu[name]
        tongxunlu[name] = phone_num
        phone.pop(old)
        phone[phone_num] = name
        print(f"已修改:{name}-->{phone_num}")
    else:
        print(f"未发现{name},请添加后重试")

while True:
    gongneng = input("请输入你需要的程序（输入数字）")
    print("1-修改联系人")
    print("2-所有联系人")
    print("3-添加联系人")
    print("4-查询联系人手机号")
    print("5-查询手机号的联系人姓名")
    print("6-删除联系人")
    print("7-退出")
    if gongneng == "1":
        name = input("请输入姓名")
        phone_num = input("请输入手机号")
        modify(name, phone_num)
    elif gongneng == "2":
        for name, phone_num in tongxunlu.items():
            print("姓名",name,"手机号",phone_num)
    elif gongneng == "3":
        name = input("请输入姓名")
        phone_num = input("请输入手机号")
        add(name, phone_num)
    elif gongneng == "4":
        name = input("请输入姓名")
        search(name)
    elif gongneng == "5":
        num = input("请输入手机号")
        search_phone(num)
    elif gongneng == "6":
        name = input("请输入姓名")
        delete(name)
    elif gongneng == "7":
        break
    else:
        print("输入的数字无效，请重新输入1～7的数字")
