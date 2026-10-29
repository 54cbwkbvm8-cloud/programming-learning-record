"""
简介：带 JSON 持久化功能的学生成绩管理系统。
功能：
- 添加、删除、修改和查询学生及成绩。
- 校验姓名重复、空姓名和非法成绩。
- 使用 students.json 保存数据并在启动时自动加载。
用途：综合练习函数、列表、字典、文件读写、JSON 和 Path。
最后修改：2026-09-11
"""
# 文件用途：本文件围绕“学生成绩管理系统 JSON持久化版”展开。
# 核心知识点：列表与字典、字符串、函数、循环、分支判断、文件读写、异常处理。
# 基本流程：根据代码中的输入或初始数据，按条件、循环和函数调用完成处理，再输出结果。
# 输入：从标准输入读取数据或由调用者传入参数。输出：通过控制台打印处理结果。 文件操作：代码中包含文件读写相关逻辑，运行时需留意路径、权限和文件内容。
# 阅读提醒：检查列表/序列的下标范围、字符串处理结果及空数据情况。 异常分支是否覆盖所有错误情况，应结合具体输入和运行环境判断。
# 模块依赖：json, pathlib。
# 导入后续代码需要使用的模块或名称；第三方模块还需在当前 Python 环境安装对应依赖。
import json
# 导入后续代码需要使用的模块或名称；第三方模块还需在当前 Python 环境安装对应依赖。
from pathlib import Path
# 把表达式 `Path(__file__).with_name('students.json')` 的计算结果保存到 `DATA_FILE`；右侧是数据来源，左侧是接收结果的变量。
DATA_FILE = Path(__file__).with_name("students.json")
# 定义函数 `save_data`；参数为空，函数体负责具体处理，调用处应关注传入参数和返回值。
def save_data():
    # 创建/更新字典 `data`；字典用“键: 值”保存关联数据，可以按键快速查找。
    data = {
        "students": xuesheng
    }
    # 进入上下文管理器；例如 `with open(...)` 能在代码块结束后自动关闭文件，减少资源未释放的风险。
    with DATA_FILE.open("w", encoding="utf-8") as f:
        # 把 Python 数据编码为 JSON 并写入目标文件对象；确认数据可序列化且路径正确。
        json.dump(data,f,ensure_ascii=False, indent=2)
# 定义函数 `load_data`；参数为空，函数体负责具体处理，调用处应关注传入参数和返回值。
def load_data():
    # 判断条件 `not DATA_FILE.exists()`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    if not DATA_FILE.exists():
        # 返回一个新列表 `[]`；列表中的值由当前计算得到，函数执行到这里就会立即结束。
        return []
    # 进入上下文管理器；例如 `with open(...)` 能在代码块结束后自动关闭文件，减少资源未释放的风险。
    with DATA_FILE.open("r",encoding="utf-8") as f:
        # 把表达式 `json.load(f)` 的计算结果保存到 `data`；右侧是数据来源，左侧是接收结果的变量。
        data = json.load(f)
        # 结束当前函数调用，并把 `data.get('students', [])` 的结果交给调用方；调用方可以继续使用这个值。
        return data.get("students", [])
# 调用函数 `load_data()`，把它返回的结果保存到 `xuesheng`；函数具体做什么要看函数定义。
xuesheng = load_data()
# 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
print("="*82)
# 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
print("-"*30,"欢迎使用学生成绩管理系统","-"*30)
# 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
print("="*82)
# 进入持续循环；必须通过循环体中的 `break`、`return` 或异常等方式结束，通常用于反复读取并校验输入。
while True:
    # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
    print("请选择程序:")
    # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
    print("1-添加学生")
    # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
    print("2-删除学生")
    # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
    print("3-修改学生")
    # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
    print("4-查看学生")
    # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
    print("5-保存JSON数据")
    # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
    print("6-退出程序")
    # 把表达式 `input().strip()` 的计算结果保存到 `gongneng`；右侧是数据来源，左侧是接收结果的变量。
    gongneng = input().strip()
    # 判断条件 `gongneng == '1'`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    if gongneng == "1":
        # 把表达式 `input('请输入学生姓名').strip()` 的计算结果保存到 `name`；右侧是数据来源，左侧是接收结果的变量。
        name = input("请输入学生姓名").strip()
        # 判断条件 `name == ''`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if name == "":
            # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
            print("学生姓名不能为空，请重新输入。")
            # `continue` 会跳过本轮循环剩余语句，直接进入下一轮循环判断。
            continue
        # 把表达式 `False` 的计算结果保存到 `yijing_cunzai`；右侧是数据来源，左侧是接收结果的变量。
        yijing_cunzai = False
        # 遍历 `xuesheng`，每轮把当前元素赋给 `student`，再执行循环体；确认容器内容与循环次数是否符合预期。
        for student in xuesheng:
            # 判断条件 `student.get('name') == name`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
            if student.get("name") == name:
                # 把表达式 `True` 的计算结果保存到 `yijing_cunzai`；右侧是数据来源，左侧是接收结果的变量。
                yijing_cunzai = True
                # `break` 会立即结束当前循环，并继续执行循环之后的语句。
                break
        # 判断条件 `yijing_cunzai`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if yijing_cunzai:
            # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
            print("该学生已存在，无法重复添加。")
            # `continue` 会跳过本轮循环剩余语句，直接进入下一轮循环判断。
            continue
        # 把表达式 `input('请输入学生成绩').strip()` 的计算结果保存到 `fenshu_text`；右侧是数据来源，左侧是接收结果的变量。
        fenshu_text = input("请输入学生成绩").strip()
        # 进入异常处理结构：先执行 try 中的语句；若发生匹配的异常，就转入对应 except 分支。
        try:
            # 把表达式转换为浮点数并存入 `fenshu`；输入文本不符合数值格式时可能抛出 `ValueError`。
            fenshu = float(fenshu_text)
            # 判断条件 `fenshu.is_integer()`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
            if fenshu.is_integer():
                # 把 `fenshu` 转换为整数并存入 `fenshu`；如果文本不是合法整数，会抛出 `ValueError`。
                fenshu = int(fenshu)
        # 捕获前面 try 代码块中符合条件的异常；变量名（如有）用于接收异常对象。
        except ValueError:
            # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
            print("成绩必须是数字，例如:88.5或者 77.0，请重新输入。")
            # `continue` 会跳过本轮循环剩余语句，直接进入下一轮循环判断。
            continue
        
        # 把一个元素追加到列表末尾；这是原地修改，列表变量仍指向同一个列表对象。
        xuesheng.append({"name": name, "score": fenshu})
        # 调用 `save_data` 执行操作；如果它有返回值而这里没有接收，通常表示代码只需要它产生的副作用。
        save_data()
        # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
        print("*****添加成功*****")
        # `continue` 会跳过本轮循环剩余语句，直接进入下一轮循环判断。
        continue
    # 判断条件 `gongneng == '2'`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    elif gongneng == "2":
        # 把表达式 `input('请输入要删除的学生姓名').strip()` 的计算结果保存到 `name`；右侧是数据来源，左侧是接收结果的变量。
        name = input("请输入要删除的学生姓名").strip()
        # 把表达式 `False` 的计算结果保存到 `yijing_shanchu`；右侧是数据来源，左侧是接收结果的变量。
        yijing_shanchu = False
        # 遍历 `xuesheng`，每轮把当前元素赋给 `student`，再执行循环体；确认容器内容与循环次数是否符合预期。
        for student in xuesheng:
            # 判断条件 `student.get('name') == name`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
            if student.get("name") == name:
                # 调用 `xuesheng.remove` 执行操作；如果它有返回值而这里没有接收，通常表示代码只需要它产生的副作用。
                xuesheng.remove(student)
                # 把表达式 `True` 的计算结果保存到 `yijing_shanchu`；右侧是数据来源，左侧是接收结果的变量。
                yijing_shanchu = True
                # `break` 会立即结束当前循环，并继续执行循环之后的语句。
                break
        # 判断条件 `yijing_shanchu`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if yijing_shanchu:
            # 调用 `save_data` 执行操作；如果它有返回值而这里没有接收，通常表示代码只需要它产生的副作用。
            save_data()
            # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
            print("删除成功！")
        # 当前条件分支的前置条件均未成立时，执行这个 else 代码块。
        else:
            # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
            print("未找到该学生，请检查姓名是否正确,无法删除。")
        # `continue` 会跳过本轮循环剩余语句，直接进入下一轮循环判断。
        continue
    # 判断条件 `gongneng == '3'`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    elif gongneng == "3":
        # 把表达式 `input('请输入要修改的学生姓名').strip()` 的计算结果保存到 `name`；右侧是数据来源，左侧是接收结果的变量。
        name = input("请输入要修改的学生姓名").strip()
        # 保存需要达到的目标值 `None`；后续算法会比较计算结果与这个目标。
        target = None
        # 遍历 `xuesheng`，每轮把当前元素赋给 `student`，再执行循环体；确认容器内容与循环次数是否符合预期。
        for student in xuesheng:
            # 判断条件 `student.get('name') == name`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
            if student.get("name") == name:
                # 保存需要达到的目标值 `student`；后续算法会比较计算结果与这个目标。
                target = student
                # `break` 会立即结束当前循环，并继续执行循环之后的语句。
                break
        # 判断条件 `target is None`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if target is None:
            # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
            print("未找到该学生,暂时无法修改。")
            # `continue` 会跳过本轮循环剩余语句，直接进入下一轮循环判断。
            continue
        # 把表达式 `input('请输入新的学生姓名(直接回车表示不修改内容):').strip()` 的计算结果保存到 `new_name`；右侧是数据来源，左侧是接收结果的变量。
        new_name = input("请输入新的学生姓名(直接回车表示不修改内容):").strip()
        # 把表达式 `False` 的计算结果保存到 `gai_guo`；右侧是数据来源，左侧是接收结果的变量。
        gai_guo = False
        # 判断条件 `new_name != ''`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if new_name != "":
            # 把表达式 `False` 的计算结果保存到 `chongfu`；右侧是数据来源，左侧是接收结果的变量。
            chongfu = False
            # 遍历 `xuesheng`，每轮把当前元素赋给 `student`，再执行循环体；确认容器内容与循环次数是否符合预期。
            for student in xuesheng:
                # 判断条件 `student is not target and student.get('name') == new_name`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
                if student is not target and student.get("name") == new_name:
                    # 把表达式 `True` 的计算结果保存到 `chongfu`；右侧是数据来源，左侧是接收结果的变量。
                    chongfu = True
                    # `break` 会立即结束当前循环，并继续执行循环之后的语句。
                    break
            # 判断条件 `chongfu`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
            if chongfu:
                # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
                print("该学生已存在，没有姓名可以修改。")
            # 当前条件分支的前置条件均未成立时，执行这个 else 代码块。
            else:
                # 把表达式 `new_name` 的计算结果保存到 `target['name']`；右侧是数据来源，左侧是接收结果的变量。
                target["name"] = new_name
                # 把表达式 `True` 的计算结果保存到 `gai_guo`；右侧是数据来源，左侧是接收结果的变量。
                gai_guo = True
        # 把表达式 `input('请输入新的学生成绩(直接回车表示不修改内容):').strip()` 的计算结果保存到 `new_score_text`；右侧是数据来源，左侧是接收结果的变量。
        new_score_text = input("请输入新的学生成绩(直接回车表示不修改内容):").strip()
        # 判断条件 `new_score_text != ''`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if new_score_text != "":
            # 进入异常处理结构：先执行 try 中的语句；若发生匹配的异常，就转入对应 except 分支。
            try:
                # 把表达式转换为浮点数并存入 `new_score`；输入文本不符合数值格式时可能抛出 `ValueError`。
                new_score = float(new_score_text)
                # 判断条件 `new_score.is_integer()`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
                if new_score.is_integer():
                    # 把 `new_score` 转换为整数并存入 `new_score`；如果文本不是合法整数，会抛出 `ValueError`。
                    new_score = int(new_score)
            # 捕获前面 try 代码块中符合条件的异常；变量名（如有）用于接收异常对象。
            except ValueError:
                # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
                print("新成绩必须是数字，成绩没有被修改。")
            # 当前条件分支的前置条件均未成立时，执行这个 else 代码块。
            else:
                # 把表达式 `new_score` 的计算结果保存到 `target['score']`；右侧是数据来源，左侧是接收结果的变量。
                target["score"] = new_score
                # 把表达式 `True` 的计算结果保存到 `gai_guo`；右侧是数据来源，左侧是接收结果的变量。
                gai_guo = True
        # 判断条件 `gai_guo`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if gai_guo:
            # 调用 `save_data` 执行操作；如果它有返回值而这里没有接收，通常表示代码只需要它产生的副作用。
            save_data()
            # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
            print("******修改成功！******")
        # 当前条件分支的前置条件均未成立时，执行这个 else 代码块。
        else:
            # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
            print("本次没有修改任何内容。")
        # `continue` 会跳过本轮循环剩余语句，直接进入下一轮循环判断。
        continue
    # 判断条件 `gongneng == '4'`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    elif gongneng == "4":
        # 把表达式 `input('请输入要查询的学生姓名').strip()` 的计算结果保存到 `chaxun_name`；右侧是数据来源，左侧是接收结果的变量。
        chaxun_name = input("请输入要查询的学生姓名").strip()
        # 把表达式 `False` 的计算结果保存到 `zhaodao`；右侧是数据来源，左侧是接收结果的变量。
        zhaodao = False
        # 遍历 `xuesheng`，每轮把当前元素赋给 `student`，再执行循环体；确认容器内容与循环次数是否符合预期。
        for student in xuesheng:
            # 把表达式 `student.get('name', '')` 的计算结果保存到 `name`；右侧是数据来源，左侧是接收结果的变量。
            name = student.get("name","")
            # 判断条件 `chaxun_name == '' or chaxun_name in name`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
            if chaxun_name == "" or chaxun_name in name:
                # 把表达式 `student.get('score', '暂无数据')` 的计算结果保存到 `fenshu`；右侧是数据来源，左侧是接收结果的变量。
                fenshu = student.get("score", "暂无数据")
                # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
                print("姓名:", name, "成绩:", fenshu, "分")
                # 把表达式 `True` 的计算结果保存到 `zhaodao`；右侧是数据来源，左侧是接收结果的变量。
                zhaodao = True
        # 判断条件 `not zhaodao`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
        if not zhaodao:
            # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
            print("未找到符合条件的学生。")
        # `continue` 会跳过本轮循环剩余语句，直接进入下一轮循环判断。
        continue
    # 判断条件 `gongneng == '5'`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    elif gongneng == "5":
        # 调用 `save_data` 执行操作；如果它有返回值而这里没有接收，通常表示代码只需要它产生的副作用。
        save_data()
        # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
        print("*****保存成功*****")
        # `continue` 会跳过本轮循环剩余语句，直接进入下一轮循环判断。
        continue
    # 判断条件 `gongneng == '6'`；条件为真时执行 if 分支，否则按后续 elif/else 分支处理。
    elif gongneng == "6":
        # 调用 `save_data` 执行操作；如果它有返回值而这里没有接收，通常表示代码只需要它产生的副作用。
        save_data()
        # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
        print("再见！欢迎下次使用Python学生信息登记系统")
        # `break` 会立即结束当前循环，并继续执行循环之后的语句。
        break
    # 当前条件分支的前置条件均未成立时，执行这个 else 代码块。
    else:
        # 把括号中的内容输出到控制台；字符串中的 `{}`、`%s` 或 `%d` 等格式占位符应与传入的数据类型匹配。
        print("编号不正确，请输入 1~6 的数字。")
