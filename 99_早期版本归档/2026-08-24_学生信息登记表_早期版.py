name = input("姓名")
zy = input("专业")
school = input("学校")
age = input("年龄")
print("--------------------\n学生信息\n--------------------")
if name == "张三" and age == "19" and school == "示例大学" and zy == "示例专业":
    print("姓名:张三\n年龄:19岁\n学校:示例大学\n专业:示例专业\n身份判断:成功")
else:
    print("姓名:",name)
    print("专业:",zy)
    print("学校:",school)
    print("年龄:",age)
    print("身份判断:失败")
