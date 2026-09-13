import json
from pathlib import Path
BASE_DIR = Path(__file__).resolve().parent
TEXT_FILE = BASE_DIR / "学生成绩示例.txt"
JSON_FILE = BASE_DIR / "学生信息.json"
def calculate_scores(scores):
    if len(scores) == 0:
        raise ValueError("列表不能为空")
    max_score = scores[0]
    min_score = scores[0]
    total_score = 0
    pass_count = 0
    for score in scores:
        if score > max_score:
            max_score = score
        if score < min_score:
            min_score = score
        total_score += score
    average_score = total_score / len(scores)
    return max_score, min_score, average_score,pass_count
def find_max(numbers):
    if len(numbers) == 0:
        raise ValueError("列表不能为空")
    max_number = numbers[0]
    for number in numbers[1:]:
        if number > max_number:
            max_number = number
    return numbers
def find_min(numbers):
    if len(numbers) == 0:
        raise ValueError("列表不能为空")
    min_number = numbers[0]
    for number in numbers[1:]:
        if number < min_number:
            min_number = number
    return min_number
def read_text_file():
    with open(TEXT_FILE, "r", encoding="utf-8") as file:
        content = file.read()
    return content
def save_students_to_json(students):
    with open(JSON_FILE, "w", encoding="utf-8") as file:
        json.dump(students, file, ensure_ascii=False, indent=4)
scores = [85, 92, 78, 90, 88]
maximum,minmum,average,pass_count = calculate_scores(scores)
print("最高分：", maximum)
print("最低分：", minmum)
print("平均分：", average)
print("及格人数：", pass_count)
text_content = read_text_file()
print(text_content)
student = [{"姓名": "张三", "年龄": 18, "成绩": 90}, {"姓名": "李四", "年龄": 19, "成绩": 85}, {"姓名": "王五", "年龄": 20, "成绩": 80}]
save_students_to_json(student)
print(f"\n学生信息已保存到{JSON_FILE}")
