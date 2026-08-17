""" 用代码制作小游戏 """

counts = 3

while counts > 0:
    temp = input("你是东西吗？：")
    guess = int(temp)

    if guess == 8:
        print("是你大坝？？？")
        print("你是春竹吗？这都不会回答")
        break
    else:
        if guess < 8:
            print("啊，对对对，你就不是个东西")
        else:
            print("啊，对对对，你就是个东西")
    counts = counts - 1    

print("不跟你玩了，傻孩子自己玩去吧")   
 
    
 
