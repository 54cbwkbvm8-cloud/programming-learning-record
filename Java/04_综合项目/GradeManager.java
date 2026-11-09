import java.util.Scanner;

public class GradeManager {

    static final int MAX = 50;
    static String[] ids = new String[MAX];
    static String[] names = new String[MAX];
    static double[] scores = new double[MAX];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("请选择功能编号：");
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    queryStudent();
                    break;
                case 3:
                    showAll();
                    break;
                case 4:
                    showStats();
                    break;
                case 5:
                    bubbleSortDesc();
                    break;
                case 0:
                    running = false;
                    System.out.println("已退出，再见！");
                    break;
                default:
                    System.out.println("无效选项，请输入 0~5 之间的数字。");
            }
        }
        sc.close();
    }

    static void printMenu() {
        System.out.println();
        System.out.println("========== 成绩管理系统 ==========");
        System.out.println("1. 录入学生成绩");
        System.out.println("2. 按学号查询");
        System.out.println("3. 显示全部学生");
        System.out.println("4. 平均分 / 最高分");
        System.out.println("5. 按成绩从高到低排序");
        System.out.println("0. 退出");
        System.out.println("===================================");
    }

    static void exitIfNoInput() {
        if (!sc.hasNext()) {
            System.out.println();
            System.out.println("输入已结束，程序退出。");
            System.exit(0);
        }
    }

    static String readToken(String prompt) {
        System.out.print(prompt);
        exitIfNoInput();
        return sc.next();
    }

    static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            exitIfNoInput();
            sc.next();
            System.out.print("输入无效，请输入整数：");
        }
        return sc.nextInt();
    }

    static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            exitIfNoInput();
            sc.next();
            System.out.print("输入无效，请输入数字：");
        }
        return sc.nextDouble();
    }

    static double readScore() {
        double score = readDouble("请输入成绩（0~100）：");
        while (!(score >= 0 && score <= 100)) {
            System.out.println("成绩必须在 0 到 100 之间，请重新输入。");
            score = readDouble("请输入成绩（0~100）：");
        }
        return score;
    }

    static int findIndexById(String id) {
        for (int i = 0; i < count; i++) {
            if (ids[i].equals(id)) {
                return i;
            }
        }
        return -1;
    }

    static void addStudent() {
        if (count >= MAX) {
            System.out.println("名单已满（最多 " + MAX + " 人），无法继续录入。");
            return;
        }
        String id = readToken("请输入学号：");
        if (findIndexById(id) != -1) {
            System.out.println("录入失败：学号 " + id + " 已存在。");
            return;
        }
        String name = readToken("请输入姓名（不含空格）：");
        double score = readScore();
        ids[count] = id;
        names[count] = name;
        scores[count] = score;
        count++;
        System.out.printf("录入成功：%s %s %.1f 分%n", id, name, score);
    }

    static void queryStudent() {
        if (count == 0) {
            System.out.println("暂无学生数据，请先录入。");
            return;
        }
        String id = readToken("请输入要查询的学号：");
        int index = findIndexById(id);
        if (index == -1) {
            System.out.println("未找到学号为 " + id + " 的学生。");
        } else {
            System.out.printf("学号：%s  姓名：%s  成绩：%.1f%n", ids[index], names[index], scores[index]);
        }
    }

    static void showAll() {
        if (count == 0) {
            System.out.println("暂无学生数据，请先录入。");
            return;
        }
        System.out.println("---- 当前共 " + count + " 名学生 ----");
        for (int i = 0; i < count; i++) {
            System.out.printf("%d. 学号：%s  姓名：%s  成绩：%.1f%n", i + 1, ids[i], names[i], scores[i]);
        }
    }

    static void showStats() {
        if (count == 0) {
            System.out.println("暂无学生数据，无法统计。");
            return;
        }
        double sum = 0;
        double max = scores[0];
        for (int i = 0; i < count; i++) {
            sum += scores[i];
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        double average = sum / count;
        System.out.printf("平均分：%.2f%n", average);
        System.out.printf("最高分：%.1f，获得者：", max);
        for (int i = 0; i < count; i++) {
            if (scores[i] == max) {
                System.out.print(names[i] + "(" + ids[i] + ") ");
            }
        }
        System.out.println();
    }

    static void bubbleSortDesc() {
        if (count == 0) {
            System.out.println("暂无学生数据，无法排序。");
            return;
        }
        for (int round = 0; round < count - 1; round++) {
            boolean swapped = false;
            for (int j = 0; j < count - 1 - round; j++) {
                if (scores[j] < scores[j + 1]) {
                    double tempScore = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = tempScore;
                    String tempId = ids[j];
                    ids[j] = ids[j + 1];
                    ids[j + 1] = tempId;
                    String tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        System.out.println("排序完成（成绩从高到低）：");
        showAll();
    }

}
// import java.util.Scanner; // 导入 Scanner 类：它是 Java 自带的“键盘输入读取工具”，不导入就不能使用

// public class GradeManager { // 定义公开类 GradeManager；类名必须和文件名 GradeManager.java
// 完全一致

// static final int MAX = 50; // 常量：最多存 50 名学生；final 表示赋值后不能再改，static
// 表示所有方法共用这一个变量
// static String[] ids = new String[MAX]; // 学号数组：创建能装 MAX 个字符串的数组，每格初始值是
// null（空）
// static String[] names = new String[MAX]; // 姓名数组：下标 i 的姓名和 ids[i]、scores[i]
// 属于同一个学生
// static double[] scores = new double[MAX]; // 成绩数组：double 能存小数，每格初始值是 0.0
// static int count = 0; // 已录入的学生人数；同时也是“下一个空位”的下标
// static Scanner sc = new Scanner(System.in); // 创建键盘读取对象；System.in 表示标准输入（键盘）

// public static void main(String[] args) { // 程序入口：Java 从这个方法开始执行
// boolean running = true; // 开关变量：true 表示菜单继续循环，选 0 时改成 false
// while (running) { // 只要 running 为 true，就一直重复显示菜单
// printMenu(); // 调用 printMenu 方法，打印功能菜单
// int choice = readInt("请选择功能编号："); // 读取一个整数，保存到 choice 变量
// switch (choice) { // switch 根据 choice 的值，跳到匹配的 case 去执行
// case 1: // choice 等于 1 时，从这里开始执行
// addStudent(); // 调用录入方法
// break; // 跳出 switch；漏写 break 会“穿透”，继续执行下一个 case
// case 2: // choice 等于 2 时
// queryStudent(); // 调用查询方法
// break; // 跳出 switch
// case 3: // choice 等于 3 时
// showAll(); // 调用显示全部学生的方法
// break; // 跳出 switch
// case 4: // choice 等于 4 时
// showStats(); // 调用统计方法（平均分、最高分）
// break; // 跳出 switch
// case 5: // choice 等于 5 时
// bubbleSortDesc(); // 调用冒泡排序方法（成绩从高到低）
// break; // 跳出 switch
// case 0: // choice 等于 0 时
// running = false; // 把开关改成 false，下一次判断 while 条件时循环结束
// System.out.println("已退出，再见！"); // 输出告别信息
// break; // 跳出 switch
// default: // 以上 case 都不匹配时（比如输入 9）执行这里
// System.out.println("无效选项，请输入 0~5 之间的数字。"); // 提示输入范围
// } // switch 的结束大括号
// } // while 循环的结束大括号
// sc.close(); // 关闭键盘读取对象，释放资源
// } // main 方法的结束大括号

// static void printMenu() { // 定义无返回值（void）的方法：只负责打印菜单
// System.out.println(); // 输出一个空行，把菜单和上一次的输出隔开
// System.out.println("========== 成绩管理系统 =========="); // 输出菜单标题
// System.out.println("1. 录入学生成绩"); // 输出选项 1
// System.out.println("2. 按学号查询"); // 输出选项 2
// System.out.println("3. 显示全部学生"); // 输出选项 3
// System.out.println("4. 平均分 / 最高分"); // 输出选项 4
// System.out.println("5. 按成绩从高到低排序"); // 输出选项 5
// System.out.println("0. 退出"); // 输出选项 0
// System.out.println("==================================="); // 输出菜单底线
// } // printMenu 方法的结束大括号

// static void exitIfNoInput() { // 定义方法：如果键盘输入已经彻底结束，就安全退出程序
// if (!sc.hasNext()) { // hasNext() 检查还有没有可读内容；! 表示取反，即“已经没有了”
// System.out.println(); // 输出空行，避免提示语和后面的文字挤在一起
// System.out.println("输入已结束，程序退出。"); // 告诉用户程序为什么结束
// System.exit(0); // 立刻结束整个程序；0 表示正常结束
// } // if 的结束大括号
// } // exitIfNoInput 方法的结束大括号

// static String readToken(String prompt) { // 定义方法：读一个“词”（不含空格的字符串），prompt
// 是提示语，返回 String
// System.out.print(prompt); // 打印提示语；print 不换行，光标留在提示语后面
// exitIfNoInput(); // 先确认还有输入可读，避免 next() 报错
// return sc.next(); // next() 读取下一个以空格或回车分隔的词，并把它作为结果返回
// } // readToken 方法的结束大括号

// static int readInt(String prompt) { // 定义方法：读一个整数，输入不是整数就反复重新要求输入
// System.out.print(prompt); // 打印提示语
// while (!sc.hasNextInt()) { // hasNextInt() 判断下一个词是不是整数；不是就进入循环
// exitIfNoInput(); // 如果已经没有任何输入了，就退出程序
// sc.next(); // 把这个不合法的词读走并丢掉，否则会永远卡在同一个词上
// System.out.print("输入无效，请输入整数："); // 提示用户重新输入
// } // while 的结束大括号
// return sc.nextInt(); // 此时下一个词一定是整数，读出来并返回
// } // readInt 方法的结束大括号

// static double readDouble(String prompt) { // 定义方法：读一个数字（可以带小数），不是数字就重新输入
// System.out.print(prompt); // 打印提示语
// while (!sc.hasNextDouble()) { // hasNextDouble() 判断下一个词是不是数字；不是就进入循环
// exitIfNoInput(); // 如果已经没有任何输入了，就退出程序
// sc.next(); // 读走并丢掉不合法的词
// System.out.print("输入无效，请输入数字："); // 提示用户重新输入
// } // while 的结束大括号
// return sc.nextDouble(); // 下一个词一定是数字，读出来并返回
// } // readDouble 方法的结束大括号

// static double readScore() { // 定义方法：读取一个合法成绩（0 到 100，含 0 和 100）
// double score = readDouble("请输入成绩（0~100）："); // 先读一次，保存到 score
// while (!(score >= 0 && score <= 100)) { // 合法条件是“>=0 并且 <=100”；外面加 !
// 表示不合法就继续循环
// System.out.println("成绩必须在 0 到 100 之间，请重新输入。"); // 告诉用户哪里错了
// score = readDouble("请输入成绩（0~100）："); // 重新读一次，覆盖 score
// } // while 的结束大括号
// return score; // 走到这里说明 score 合法，返回它
// } // readScore 方法的结束大括号

// static int findIndexById(String id) { // 定义方法：按学号找下标，找到返回下标，找不到返回 -1
// for (int i = 0; i < count; i++) { // 只查已录入的 count 个位置，i 从 0 数到 count-1
// if (ids[i].equals(id)) { // 比较字符串内容必须用 equals，不能用 ==
// return i; // 找到了：立刻返回下标，方法结束
// } // if 的结束大括号
// } // for 的结束大括号
// return -1; // 循环走完都没找到：返回 -1（数组下标不可能是 -1，所以可表示“没找到”）
// } // findIndexById 方法的结束大括号

// static void addStudent() { // 定义方法：录入一个学生
// if (count >= MAX) { // 如果数组已经装满
// System.out.println("名单已满（最多 " + MAX + " 人），无法继续录入。"); // 提示已满，+ 用来拼接字符串
// return; // 提前结束方法，不再往下执行
// } // if 的结束大括号
// String id = readToken("请输入学号："); // 读取学号，保存到 id
// if (findIndexById(id) != -1) { // 如果返回值不是 -1，说明这个学号已经存在
// System.out.println("录入失败：学号 " + id + " 已存在。"); // 提示重复学号
// return; // 提前结束方法，这个学生不会被录入
// } // if 的结束大括号
// String name = readToken("请输入姓名（不含空格）："); // 读取姓名，保存到 name
// double score = readScore(); // 读取并检查成绩，保存到 score
// ids[count] = id; // 把学号放进 ids 数组的第 count 个空位
// names[count] = name; // 把姓名放进 names 数组的同一个位置
// scores[count] = score; // 把成绩放进 scores 数组的同一个位置
// count++; // 人数加 1，下一个空位后移一格
// System.out.printf("录入成功：%s %s %.1f 分%n", id, name, score); // printf 格式化输出：%s
// 填字符串，%.1f 填保留 1 位小数的数字，%n 换行
// } // addStudent 方法的结束大括号

// static void queryStudent() { // 定义方法：按学号查询一个学生
// if (count == 0) { // 如果还没有任何数据
// System.out.println("暂无学生数据，请先录入。"); // 提示先录入
// return; // 提前结束方法
// } // if 的结束大括号
// String id = readToken("请输入要查询的学号："); // 读取要查的学号
// int index = findIndexById(id); // 调用查找方法，得到下标或 -1
// if (index == -1) { // 如果是 -1，说明没找到
// System.out.println("未找到学号为 " + id + " 的学生。"); // 提示没找到
// } else { // 否则说明找到了
// System.out.printf("学号：%s 姓名：%s 成绩：%.1f%n", ids[index], names[index],
// scores[index]); // 用下标取出同一个学生的三项信息并输出
// } // if-else 的结束大括号
// } // queryStudent 方法的结束大括号

// static void showAll() { // 定义方法：显示全部学生
// if (count == 0) { // 如果没有数据
// System.out.println("暂无学生数据，请先录入。"); // 提示先录入
// return; // 提前结束方法
// } // if 的结束大括号
// System.out.println("---- 当前共 " + count + " 名学生 ----"); // 输出人数
// for (int i = 0; i < count; i++) { // 依次访问下标 0 到 count-1
// System.out.printf("%d. 学号：%s 姓名：%s 成绩：%.1f%n", i + 1, ids[i], names[i],
// scores[i]); // %d 填整数序号（i+1 让序号从 1
// // 开始），其余同前
// } // for 的结束大括号
// } // showAll 方法的结束大括号

// static void showStats() { // 定义方法：计算并输出平均分和最高分
// if (count == 0) { // 如果没有数据
// System.out.println("暂无学生数据，无法统计。"); // 提示无法统计，同时避免后面“除以 0”
// return; // 提前结束方法
// } // if 的结束大括号
// double sum = 0; // 累加器：用来存所有成绩的总和，从 0 开始
// double max = scores[0]; // 擂台变量：先假设第 1 个学生的成绩最高（此时 count>=1，scores[0] 一定有数据）
// for (int i = 0; i < count; i++) { // 依次访问每一个已录入的成绩
// sum += scores[i]; // 把当前成绩加进总和，等价于 sum = sum + scores[i]
// if (scores[i] > max) { // 如果当前成绩比擂台上的最高分还高
// max = scores[i]; // 就让当前成绩当新的最高分
// } // if 的结束大括号
// } // for 的结束大括号
// double average = sum / count; // 平均分 = 总和 / 人数；sum 是 double，所以结果保留小数
// System.out.printf("平均分：%.2f%n", average); // 输出平均分，保留 2 位小数
// System.out.printf("最高分：%.1f，获得者：", max); // 输出最高分（不换行，后面接着输出获得者）
// for (int i = 0; i < count; i++) { // 再遍历一次，找出所有等于最高分的学生（可能并列）
// if (scores[i] == max) { // max 是从数组里原样复制出来的，所以可以直接用 == 比较
// System.out.print(names[i] + "(" + ids[i] + ") "); // 输出姓名和学号，不换行
// } // if 的结束大括号
// } // for 的结束大括号
// System.out.println(); // 输出换行，结束这一行
// } // showStats 方法的结束大括号

// static void bubbleSortDesc() { // 定义方法：用冒泡排序把学生按成绩从高到低排列
// if (count == 0) { // 如果没有数据
// System.out.println("暂无学生数据，无法排序。"); // 提示无法排序
// return; // 提前结束方法
// } // if 的结束大括号
// for (int round = 0; round < count - 1; round++) { // 外层循环：控制“第几趟”，n 个数最多需要
// n-1 趟
// boolean swapped = false; // 记录这一趟有没有发生交换，每趟开始前先设为 false
// for (int j = 0; j < count - 1 - round; j++) { //
// 内层循环：比较相邻两项；每过一趟，末尾多一个已排好的，所以范围缩小 round
// if (scores[j] < scores[j + 1]) { // 如果前面的成绩比后面的低（降序要求前面的更高），就需要交换
// double tempScore = scores[j]; // 先把前面的成绩存到临时变量，防止被覆盖丢失
// scores[j] = scores[j + 1]; // 把后面的成绩放到前面
// scores[j + 1] = tempScore; // 把临时变量里原来前面的成绩放到后面
// String tempId = ids[j]; // 学号必须跟着成绩一起交换：先保存前面的学号
// ids[j] = ids[j + 1]; // 后面的学号换到前面
// ids[j + 1] = tempId; // 原来前面的学号换到后面
// String tempName = names[j]; // 姓名也要一起交换：先保存前面的姓名
// names[j] = names[j + 1]; // 后面的姓名换到前面
// names[j + 1] = tempName; // 原来前面的姓名换到后面
// swapped = true; // 记录“这一趟发生过交换”
// } // if 的结束大括号
// } // 内层 for 的结束大括号
// if (!swapped) { // 如果这一趟一次交换都没发生，说明已经完全有序
// break; // 提前跳出外层循环，省掉多余的趟数
// } // if 的结束大括号
// } // 外层 for 的结束大括号
// System.out.println("排序完成（成绩从高到低）："); // 提示排序完成
// showAll(); // 调用显示方法，展示排序后的结果
// } // bubbleSortDesc 方法的结束大括号

// } // GradeManager 类的结束大括号
