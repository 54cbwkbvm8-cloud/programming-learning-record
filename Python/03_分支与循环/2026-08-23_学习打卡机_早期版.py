i = 1
while i <= 9:
    j = 1
    while j <= i:
        print(j,"*",i,"=",j*i,end=" ")
        j = j + 1
    print()
    i = i + 1
day = 1
hour = 1
while day <= 7:
    while hour <= 8:
        print("我今天要学八小时")
        hour = hour + 1
        if hour > 1:
            break
    day = day + 1    
    
