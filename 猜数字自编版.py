target = 37
count = 0
guess = float(input("请输入你的猜测："))
count = count + 1
while guess != target:
    if guess > target:
        print("猜大了")
    else:
        guess < target
        print("猜小了")
    guess = float(input("请输入你的猜测："))
print("猜对了")
print(f"你一共猜了 {count} 次")
