import java.util.Scanner;

class Student {
    private String id;
    private String name;
    private int score;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public boolean setScore(int score) {
        if (score < 0 || score > 100) {
            return false;
        }
        this.score = score;
        return true;
    }

    @Override
    public String toString() {
        return id + "\t" + name + "\t" + score;
    }
}

class StudentManager {
    private Student[] students;
    private int count;

    public StudentManager(int capacity) {
        this.students = new Student[capacity];
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public Student getStudent(int index) {
        if (index < 0 || index >= count) {
            return null;
        }
        return students[index];
    }

    public Student getStudent(String id) {
        return findById(id);
    }

    public Student[] getStudents() {
        Student[] result = new Student[count];
        System.arraycopy(students, 0, result, 0, count);
        return result;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == students.length;
    }

    public Student findById(String id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equals(id)) {
                return students[i];
            }
        }
        return null;
    }

    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }
        if (isFull()) {
            return false;
        }
        if (findById(student.getId()) != null) {
            return false;
        }
        students[count++] = student;
        return true;
    }

    public double getAverage() {
        if (isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 0; i < count; i++) {
            sum += students[i].getScore();
        }
        return sum / count;
    }

    public int getMaxScore() {
        if (isEmpty()) {
            return -1;
        }
        int max = students[0].getScore();
        for (int i = 1; i < count; i++) {
            if (students[i].getScore() > max) {
                max = students[i].getScore();
            }
        }
        return max;
    }

    public void sortByScoreDesc() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (students[j].getScore() < students[j + 1].getScore()) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }
}

public class StudentManagementSystem {
    private static final int MAX_STUDENTS = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager(MAX_STUDENTS);
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner, "请选择功能编号（0~5）：");
            switch (choice) {
                case 1:
                    addStudent(scanner, manager);
                    break;
                case 2:
                    queryStudent(scanner, manager);
                    break;
                case 3:
                    showAll(manager);
                    break;
                case 4:
                    showStatistics(manager);
                    break;
                case 5:
                    sortByScoreDesc(manager);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("无效的选择，请重新输入 0~5 的数字！");
            }
        }
        scanner.close();
        System.out.println("谢谢使用，再见！");
    }

    private static void printMenu() {
        System.out.println("========== 学生成绩管理系统（面向对象版）==========");
        System.out.println(" 1.录入学生");
        System.out.println(" 2.按学号查询学生");
        System.out.println(" 3.显示全部学生");
        System.out.println(" 4.统计平均分和最高分");
        System.out.println(" 5.按成绩从高到低排序（冒泡排序）");
        System.out.println(" 0.退出");
        System.out.println("===================================================");
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("请输入数字！");
            }
        }
    }

    private static String readText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("输入不能为空！");
        }
    }

    private static void addStudent(Scanner scanner, StudentManager manager) {
        if (manager.isFull()) {
            System.out.println("学生人数已满，无法添加！");
            return;
        }
        String id;
        while (true) {
            id = readText(scanner, "请输入学生学号：");
            if (manager.findById(id) == null) {
                break;
            }
            System.out.println("学号已存在，请重新输入！");
        }
        String name = readText(scanner, "请输入学生姓名：");
        Student student = new Student(id, name);
        while (!student.setScore(readInt(scanner, "请输入学生成绩（0-100）："))) {
            System.out.println("成绩必须在 0~100 之间，请重新输入！");
        }
        if (manager.addStudent(student)) {
            System.out.println("添加成功！" + student);
        } else {
            System.out.println("添加失败！无法保存该学生信息！");
        }
    }

    private static void queryStudent(Scanner scanner, StudentManager manager) {
        if (manager.getCount() == 0) {
            System.out.println("没有学生信息！");
            return;
        }
        String id = readText(scanner, "请输入要查询的学生学号：");
        Student student = manager.findById(id);
        if (student == null) {
            System.out.println("没有找到学号为" + id + "的学生！");
        } else {
            System.out.println("学号\t姓名\t成绩");
            System.out.println(student);
        }
    }

    private static void showAll(StudentManager manager) {
        if (manager.getCount() == 0) {
            System.out.println("没有学生信息！");
            return;
        }
        System.out.println("学号\t姓名\t成绩");
        for (Student student : manager.getStudents()) {
            System.out.println(student);
        }
        System.out.println("共 " + manager.getCount() + " 个学生信息");
    }

    private static void showStatistics(StudentManager manager) {
        if (manager.getCount() == 0) {
            System.out.println("没有学生信息！");
            return;
        }
        int max = manager.getMaxScore();
        System.out.println("学生人数：" + manager.getCount());
        System.out.printf("平均分：%.2f%n", manager.getAverage());
        System.out.println("最高分：" + max);
        System.out.println("最高分学生：");
        for (Student student : manager.getStudents()) {
            if (student.getScore() == max) {
                System.out.println(" " + student);
            }
        }
    }

    private static void sortByScoreDesc(StudentManager manager) {
        if (manager.isEmpty()) {
            System.out.println("暂无学生数据，请先录入！");
            return;
        }
        manager.sortByScoreDesc();
        System.out.println("按成绩降序排序完成！");
        showAll(manager);
    }
}
// // 导入 Scanner：Java 自带的"键盘输入工具"，用它才能读取键盘输入。
// // 它不在默认范围内，必须先 import，否则 Java 不认识 Scanner 这个名字。
// import java.util.Scanner;

// /**
// * StudentManagementSystem —— 学生成绩管理系统（面向对象版）
// *
// * 运行后会出现一个菜单，用户输入数字选择功能：录入学生、按学号查询、显示全部、
// * 统计平均分和最高分、按成绩从高到低排序、退出。
// *
// * 【程序的整体设计：三个类，各管一件事】
// * 这是"面向对象"的典型写法：把程序拆成几个"类"，每个类只负责一类事情，
// * 好比一家公司分成不同的部门：
// * Student（学生） ：一个学生的数据：学号、姓名、成绩。只负责"保存和保护学生自己的数据"。
// * StudentManager（学生管理器） ：管理一批学生：增加、查找、统计、排序。只负责"管理数据"，不和用户打交道。
// * StudentManagementSystem ：负责和用户打交道：显示菜单、读取输入、显示结果，
// * 需要管理数据时，就去调用 StudentManager。
// * 这样分工的好处：改一个部分，不会牵连到其他部分；每个类也更容易看懂。
// *
// * 【关于一个文件里有多个类】
// * 一个 .java 文件里可以写多个类，但最多只有一个能写 public，而且 public 类的名字必须和文件名一致。
// * 这个文件里 public 的是 StudentManagementSystem（它含有 main 方法，是程序的入口），
// * 所以文件必须叫 StudentManagementSystem.java。Student 和 StudentManager 没有写 public，
// * 只供这个文件内部使用。
// *
// * 【本次修复的问题】
// * 1. 录入学生时，输入有误的提示和实际行为对不上（最影响使用的问题）：
// * · 学号重复时，提示"学号已存在，请重新输入！"，但程序并没有让你重新输入，
// * 而是直接回到了主菜单。用户按提示接着输入新学号，这个新学号会被当成菜单选项，
// * 后面输入的姓名、成绩也会被当成菜单选项，接连出现"请输入数字！""无效的选择"。
// * · 成绩超出 0~100 时，同样是直接回到主菜单，前面已经输入好的学号和姓名全部作废，
// * 用户接着输入的成绩又会被当成菜单选项。
// * 修复：学号重复就在原地重新输入学号，成绩超范围就在原地重新输入成绩，
// * 已经输入好的内容不会丢。
// * 2. 方法名 findByid 的拼写不规范：英文单词 Id 的 I 应该大写，规范写法是 findById
// * （Java 的命名习惯是"驼峰式"：每个单词的首字母大写）。已全部改成 findById。
// * 3. addStudent 方法没有防备传入 null（"什么都没有"）：
// * 如果有人传入 null，后面的 student.getId() 会让程序崩溃（空指针异常）。
// * 现在开头先检查，是 null 就直接返回 false，表示添加失败。
// * 4. 提示文字不统一：菜单提示里的"0～5"用了特殊的波浪线，另一处写成"0~5"，
// * 而且"无效的选择"那句话里的括号一半是英文括号 (、一半是中文括号 ）。已统一。
// * （学生数据的保存、查找、统计、排序等核心逻辑我都实际测试过，是正确的，没有改动。）
// *
// * 【使用时的一个提示】
// * 请用菜单里的 0 退出程序。如果直接强行结束输入（比如在命令行里按 Ctrl+D 或 Ctrl+Z），
// * 程序读不到下一行输入，会报错停止，这是正常现象。
// */

// // ============================================================
// // 第一部分：Student 类 —— 描述"一个学生"
// //
// // 类 = 设计图纸，对象 = 按图纸造出来的实物。
// // 这张图纸规定：每个学生有学号、姓名、成绩三个属性。
// // ============================================================
// class Student {

// // private：私有，只有 Student 这个类自己里面的代码能访问，类的外面不能直接读写。
// // 这叫"封装"：把数据藏起来，只通过下面公开的方法去读和改，这样类就能对数据做保护。
// private String id; // 学号。用文字类型 String，因为学号不用来做加减运算，而且可能以 0 开头
// private String name; // 姓名
// private int score; // 成绩。没有赋值时，int 的默认值是 0

// // 构造方法：用 new Student(...) 创建对象时自动执行，负责给新对象设置初始值。
// // · 名字必须和类名完全相同 · 没有返回值类型（连 void 都不写）
// // 这里只接收学号和姓名，成绩先保持默认的 0，之后用 setScore 单独设置。
// // 参数名 id、name 和成员变量同名，在方法里只写 id 指的是参数；
// // 要表示"这个对象自己的成员变量 id"，必须写 this.id（this 代表"当前这个对象自己"）。
// public Student(String id, String name) {
// this.id = id;
// this.name = name;
// }

// // getter 方法：因为成员变量是 private，外面不能直接读，所以提供这些公开的"读取入口"。
// // 方法头里的 String / int 是返回值类型：表示方法会把对应类型的值交还给调用者。
// // 这里写的 this.id 和直接写 id 效果完全一样（因为没有同名参数遮挡），this. 可以省略。
// public String getId() {
// return this.id;
// }

// public String getName() {
// return this.name;
// }

// public int getScore() {
// return this.score;
// }

// // setter 方法：对外提供"修改成绩"的入口，并且带检查。
// // 返回 boolean：true 表示修改成功，false 表示因为成绩不合法而被拒绝（成绩保持原样不变）。
// // 这样"成绩必须在 0~100"这条规则只写在这一个地方，别的代码想设置成绩，都要经过这里的检查。
// public boolean setScore(int score) {
// // || 表示"或者"：成绩小于 0，或者大于 100，只要有一个成立，就是不合法
// if (score < 0 || score > 100) {
// return false;
// }
// this.score = score; // 合法才真正修改。这里用 this.score 区分"成员变量"和"参数"
// return true;
// }

// // 重写 toString：规定这个对象"转成文字"时的样子。
// // System.out.println(student) 和 "文字" + student 都会自动调用 toString()。
// // 如果不重写，打印出来是类似 Student@15db9742 这样看不懂的东西。
// // @Override 是一个标记，意思是"我这个方法是在重写父类（Object）里的方法"，
// // 写错了方法名或参数时，编译器会立刻报错提醒你。
// // "\t" 是"制表符"（相当于按一下 Tab 键），让学号、姓名、成绩三列对齐。
// @Override
// public String toString() {
// return id + "\t" + name + "\t" + score;
// }
// }

// // ============================================================
// // 第二部分：StudentManager 类 —— 管理一批学生
// //
// // 这个类用一个"数组"来保存学生。
// // 数组的特点：创建时必须规定好长度，之后长度不能改变。
// // 所以需要两个变量配合使用：
// // students.length ：数组的"容量"，一共能放多少个学生（创建时就定死了）
// // count ：数组里【实际已经放了几个】学生
// // 举例：容量 100，目前录入了 3 个学生，那么 count = 3。
// // 这 3 个学生放在下标 0、1、2 的格子里，下标 3 到 99 的格子还是空的（值是 null）。
// // 所以后面的循环都要写 i < count，而不是 i < students.length，
// // 否则会碰到那些空格子，对 null 调用方法会崩溃。
// // ============================================================
// class StudentManager {

// private Student[] students; // 存放学生的数组（对象数组：每个格子里放的是一个学生对象的地址）
// private int count; // 已经存放的学生个数

// // 构造方法：创建管理器时，指定"最多能放多少个学生"（容量）
// public StudentManager(int capacity) {
// // new Student[capacity]：创建一个能放 capacity 个学生的数组。
// // 注意：这只是准备好了 capacity 个空格子，每个格子里的值都是 null，还没有任何学生对象。
// this.students = new Student[capacity];
// this.count = 0; // 一开始一个学生也没有
// }

// // 获取已经存放的学生个数
// public int getCount() {
// return count;
// }

// // ---------- 两个同名的 getStudent 方法：方法的"重载" ----------
// // 重载：在同一个类里，可以写多个名字相同、但参数不同（个数或类型不同）的方法，
// // 调用时 Java 会根据传入的参数，自动选择匹配的那一个。

// // 版本 1：按"下标"取学生（参数是 int）。
// // 下标必须在 0 到 count - 1 之间，超出范围就返回 null，防止越界崩溃。
// public Student getStudent(int index) {
// if (index < 0 || index >= count) {
// return null;
// }
// return students[index];
// }

// // 版本 2：按"学号"取学生（参数是 String）。直接交给 findById 去查找。
// public Student getStudent(String id) {
// return findById(id);
// }

// // 返回当前所有学生组成的一个【新数组】（长度正好等于 count，没有多余的空格子）。
// // 为什么不直接把内部的 students 数组返回出去？
// // 因为里面有很多空格子（null），而且外面的人拿到内部数组后可以随意改动，会破坏管理器的数据。
// // 所以复制一份给他，这叫"保护性复制"。
// public Student[] getStudents() {
// Student[] result = new Student[count];
// // System.arraycopy(源数组, 源起始下标, 目标数组, 目标起始下标, 复制个数)：
// // 把 students 里从下标 0 开始的 count 个元素，复制到 result 里从下标 0 开始的位置。
// System.arraycopy(students, 0, result, 0, count);
// return result;
// }

// // 是不是空的（一个学生都没有）
// public boolean isEmpty() {
// return count == 0; // == 是"判断相等"（两个等号），结果是 true 或 false，直接返回
// }

// // 是不是满了（已经放到容量上限了）
// public boolean isFull() {
// return count == students.length;
// }

// // ---------- 按学号查找学生 ----------
// // 方法名从 findByid 改成了规范的 findById（Id 的 I 要大写）。
// // 找到了，返回那个学生对象；没找到，返回 null。
// public Student findById(String id) {
// // 线性查找：从头到尾一个一个看（只看已经有学生的那部分，所以是 i < count）
// for (int i = 0; i < count; i++) {
// // 比较两个字符串的内容是否相同，必须用 equals，不能用 ==。
// // 因为对字符串用 == 比较的是"是不是同一个对象"（地址是否相同），而不是内容是否相同。
// if (students[i].getId().equals(id)) {
// return students[i]; // 找到了，立刻返回，后面的不用再看了
// }
// }
// return null; // 整个循环走完都没找到
// }

// // ---------- 添加学生 ----------
// // 返回 true 表示添加成功，false 表示失败（失败的原因：传入 null、已满、学号重复）
// public boolean addStudent(Student student) {

// // 【修复点】防备传入 null。
// // 如果 student 是 null，下面的 student.getId() 会因为"对 null 调用方法"而崩溃。
// if (student == null) {
// return false;
// }
// if (isFull()) {
// return false; // 数组满了，放不下了
// }
// if (findById(student.getId()) != null) {
// return false; // 已经有相同学号的学生了，学号不允许重复
// }

// // students[count++] = student; 这一句做了两件事，顺序是：
// // ① 先用当前的 count 当作下标，把新学生放进数组的那个格子里
// // ② 然后 count 再加 1
// // 例如 count 是 3 时：新学生被放进 students[3]，然后 count 变成 4。
// // （count++ 是"先使用，后加一"；如果写成 ++count，则是"先加一，后使用"，结果就错位了。）
// students[count++] = student;
// return true;
// }

// // ---------- 计算平均分 ----------
// public double getAverage() {
// if (isEmpty()) {
// return 0.0; // 没有学生时直接返回 0，避免下面"除以 0"
// }
// // sum 用 double 类型：这样最后的除法是"小数除法"，不会丢掉小数部分。
// // 如果 sum 和 count 都是 int，整数相除会直接丢掉小数（例如 175 / 2 得 87 而不是 87.5）。
// double sum = 0.0;
// for (int i = 0; i < count; i++) {
// sum += students[i].getScore(); // sum += x 等价于 sum = sum + x
// }
// return sum / count;
// }

// // ---------- 求最高分 ----------
// // 经典的"打擂台"：先假设第一个人是最高的（擂主），然后让后面的人依次上台比较，分高的当新擂主。
// public int getMaxScore() {
// if (isEmpty()) {
// return -1; // 没有学生时返回 -1。因为合法的成绩最小是 0，所以 -1 可以明确表示"没有最高分"
// }
// int max = students[0].getScore();
// for (int i = 1; i < count; i++) { // 第一个已经当擂主了，所以从下标 1 开始比较
// if (students[i].getScore() > max) {
// max = students[i].getScore();
// }
// }
// return max;
// }

// // ---------- 按成绩从高到低排序（冒泡排序）----------
// // 冒泡排序的思想：反复比较"相邻的两个"，如果顺序不对就交换，
// // 每走完一趟，当前没排好的里面最小的那个就会"沉"到最右边（因为我们要的是从高到低）。
// public void sortByScoreDesc() {
// // 外层：最多走 count - 1 趟。注意用的是 count，不是 students.length，
// // 因为数组后面的格子是 null，不能参与比较（对 null 调用 getScore 会崩溃）。
// for (int i = 0; i < count - 1; i++) {

// // 内层：每趟从左到右比较相邻的两个。
// // 为什么条件是 j < count - 1 - i？
// // · 每次要访问 students[j + 1]，所以 j 最大只能是 count - 2，否则会越界
// // · 每走完一趟，最右边就多一个已经排好的，不需要再比较，所以每趟再少比较 i 个
// for (int j = 0; j < count - 1 - i; j++) {

// // 要的是"从高到低"，所以如果左边的分数【小于】右边的，说明顺序反了，需要交换。
// // 只有严格小于才交换，分数相同的不交换，所以相同分数的学生会保持原来的先后顺序（稳定排序）。
// if (students[j].getScore() < students[j + 1].getScore()) {

// // 交换两个元素需要借助一个临时变量 temp：
// // 如果直接 a = b; b = a;，第一句执行后 a 原来的值就丢了。
// // 类比：想交换两杯饮料，必须再拿一个空杯子当中转。
// Student temp = students[j]; // ① 左边的先存进临时变量
// students[j] = students[j + 1]; // ② 右边的放到左边
// students[j + 1] = temp; // ③ 临时变量里的放到右边，交换完成
// }
// }
// }
// }
// }

// // ============================================================
// // 第三部分：StudentManagementSystem 类 —— 和用户打交道（显示菜单、读输入、显示结果）
// // ============================================================
// public class StudentManagementSystem {

// // 最多能录入多少个学生。
// // private ：私有，只在这个类里使用
// // static ：属于"类"本身，不属于某个对象（main 是 static 方法，只能直接使用 static 的内容）
// // final ：最终的，赋值之后不能再修改，这样的变量叫"常量"
// // 常量的名字习惯全部用大写字母，单词之间用下划线连接。
// // 好处：以后想把上限改成 200，只需要改这一个地方，不用在代码里到处找数字 100。
// private static final int MAX_STUDENTS = 100;

// // main 是程序的入口：运行程序时，Java 从这里的第一行开始，一行一行往下执行。
// public static void main(String[] args) {

// // 创建 Scanner 对象，负责读取键盘输入（System.in 表示键盘）
// Scanner scanner = new Scanner(System.in);

// // 创建学生管理器，容量是 MAX_STUDENTS（100）。所有的学生数据都放在它里面。
// StudentManager manager = new StudentManager(MAX_STUDENTS);

// // running 是"程序是否继续运行"的开关，true 表示继续，false 表示退出。
// boolean running = true;

// // 主循环：只要 running 是 true，就一遍一遍地显示菜单、处理用户的选择。
// // 用户选择"0.退出"时，把 running 改成 false，循环结束，程序结束。
// while (running) {
// printMenu(); // 显示菜单

// // 读取用户选的功能编号。readInt 会保证用户输入的一定是整数（下面有它的详细讲解）。
// // 【修复点】原来这里的"0～5"用了特殊的波浪线，统一成了"0~5"。
// int choice = readInt(scanner, "请选择功能编号（0~5）：");

// // switch：多分支选择。根据 choice 的值，跳到对应的 case 去执行。
// // 每个 case 末尾的 break 的作用是"执行完就跳出 switch"。
// // 如果漏写 break，程序会"穿透"，继续执行下一个 case 里的代码，造成错误。
// switch (choice) {
// case 1:
// addStudent(scanner, manager);
// break;
// case 2:
// queryStudent(scanner, manager);
// break;
// case 3:
// showAll(manager);
// break;
// case 4:
// showStatistics(manager);
// break;
// case 5:
// sortByScoreDesc(manager);
// break;
// case 0:
// running = false; // 把开关关掉，当前这一轮结束后，while 的条件不成立，循环结束
// break;
// default:
// // default：上面所有 case 都没有匹配上时执行（比如输入了 7）。
// // 【修复点】原来括号一半英文一半中文，现已统一整理。
// System.out.println("无效的选择，请重新输入 0~5 的数字！");
// }
// }

// // 循环结束，用完 Scanner 后关闭它，释放资源。这是个好习惯。
// scanner.close();
// System.out.println("谢谢使用，再见！");
// }

// // 显示菜单。
// // private static void：
// // private ：私有，只在这个类里使用
// // static ：因为要被 static 的 main 直接调用，所以也必须是 static
// // void ：执行完不返回任何结果，只负责做事（这里是打印）
// private static void printMenu() {
// System.out.println("========== 学生成绩管理系统（面向对象版）==========");
// System.out.println(" 1.录入学生");
// System.out.println(" 2.按学号查询学生");
// System.out.println(" 3.显示全部学生");
// System.out.println(" 4.统计平均分和最高分");
// System.out.println(" 5.按成绩从高到低排序（冒泡排序）");
// System.out.println(" 0.退出");
// System.out.println("===================================================");
// }

// // ============================================================
// // readInt：读取一个整数，直到用户输入合法为止
// //
// // 方法头：private static int readInt(Scanner scanner, String prompt)
// // int ：返回值类型：返回一个整数
// // 两个参数 ：scanner（读取输入的工具）和 prompt（要显示给用户的提示文字）
// //
// // 【为什么用 nextLine() 读一整行，再用 Integer.parseInt 转成数字，而不直接用 nextInt()？】
// // nextInt() 只读走数字，会把数字后面的"回车换行"留在输入里没读走。
// // 之后再调用 nextLine() 时，它会先读到这个遗留的"回车"，立刻返回一个空字符串，
// // 于是用户明明还没输入，程序就跳过去了。这是初学者最常踩的坑之一。
// // 统一用 nextLine() 每次都读走整整一行，就不会有遗留问题。
// // ============================================================
// private static int readInt(Scanner scanner, String prompt) {
// // while (true)：故意写的"无限循环"，靠里面的 return 来结束：只有用户输入合法，才会 return 跳出
// while (true) {
// System.out.print(prompt); // print 不换行，用户输入的内容会紧跟在提示后面

// // nextLine()：读取整整一行。trim()：去掉前后多余的空格，
// // 避免用户不小心输入了 " 3 " 这样带空格的内容。
// String line = scanner.nextLine().trim();

// // try-catch：预防错误导致程序崩溃。
// // try ：把"可能出错的代码"放在里面试着运行
// // catch ：如果 try 里出了指定类型的错误，就跳到这里处理，程序不会崩溃
// try {
// // Integer.parseInt(文字)：把文字转换成整数。例如 "42" 变成 42。
// // 如果文字不是合法的整数（比如 "abc"、"3.5"、空字符串），它会抛出 NumberFormatException 错误。
// // 转换成功就直接 return 这个数字，方法结束。
// return Integer.parseInt(line);
// } catch (NumberFormatException e) {
// // NumberFormatException = "数字格式异常"，进入这里说明输入的不是整数。
// // 提示后不 return，回到 while 的开头，让用户重新输入。
// System.out.println("请输入数字！");
// }
// }
// }

// // ============================================================
// // readText：读取一段【不为空】的文字，直到用户输入有内容为止
// // ============================================================
// private static String readText(Scanner scanner, String prompt) {
// while (true) {
// System.out.print(prompt);
// String text = scanner.nextLine().trim(); // 读一行，并去掉前后空格

// // isEmpty() 判断字符串是不是空的（一个字符都没有）。
// // ! 表示"取反"，!text.isEmpty() 读作"不是空的"。
// if (!text.isEmpty()) {
// return text;
// }
// System.out.println("输入不能为空！");
// }
// }

// // ============================================================
// // 功能 1：录入学生
// //
// // 这个方法的名字和 StudentManager 里的 addStudent 一样，但它们属于不同的类，互不冲突：
// // 这里的是"和用户交互、收集信息"，StudentManager 里的是"真正把学生存进数组"。
// // ============================================================
// private static void addStudent(Scanner scanner, StudentManager manager) {

// // 先检查是不是已经满了。满了就不用让用户白白输入一大堆信息了。
// if (manager.isFull()) {
// System.out.println("学生人数已满，无法添加！");
// return; // return：立刻结束这个方法，回到主菜单
// }

// // ---------- 输入学号：重复就让用户在原地重新输入 ----------
// // 【修复点】原来学号重复时，只是提示一句"请重新输入"，然后直接 return 回到了主菜单，
// // 并没有真的让用户重新输入。现在改成在循环里反复输入，直到学号不重复为止。
// String id; // 先声明变量，在下面的循环里赋值
// while (true) {
// id = readText(scanner, "请输入学生学号：");

// // findById 返回 null，说明没有找到相同学号的学生，这个学号可以用
// if (manager.findById(id) == null) {
// break; // 学号可用，跳出循环
// }
// System.out.println("学号已存在，请重新输入！"); // 然后回到循环开头，重新输入
// }

// // ---------- 输入姓名 ----------
// String name = readText(scanner, "请输入学生姓名：");

// // 先用学号和姓名创建出学生对象，此时成绩还是默认的 0
// Student student = new Student(id, name);

// // ---------- 输入成绩：超出范围就让用户在原地重新输入 ----------
// // 【修复点】原来成绩超出 0~100 时，也是提示一句后直接返回主菜单，前面输入的学号和姓名全部作废。
// // 现在改成：只重新输入成绩，学号和姓名保留。
// //
// // 这一行看起来很紧凑，拆开一步步讲：
// // readInt(scanner, "...") ：先显示提示、读取用户输入的整数（保证是整数）
// // student.setScore(那个整数) ：尝试把它设置为成绩。成绩合法返回 true，不合法返回 false，
// // 并且不合法时 setScore 不会修改成绩
// // ! 取反 ：!student.setScore(...) 读作"设置失败了"
// // while (设置失败了) { ... } ：只要设置失败，就提示错误，然后回到循环开头，
// // 再次执行条件里的 readInt，让用户重新输入
// // 这样做的好处：判断成绩是否合法的规则，只写在 Student 的 setScore 里一处，
// // 这里不用再重复写一遍 score < 0 || score > 100。
// while (!student.setScore(readInt(scanner, "请输入学生成绩（0-100）："))) {
// System.out.println("成绩必须在 0~100 之间，请重新输入！");
// }

// // 把学生放进管理器。addStudent 返回 true 表示成功。
// // 理论上前面已经检查过"没满、学号不重复"，不会失败，这里仍然判断一下，是为了稳妥。
// if (manager.addStudent(student)) {
// // "文字" + student：自动调用 student.toString()，显示成 学号 姓名 成绩 的样子
// System.out.println("添加成功！" + student);
// } else {
// System.out.println("添加失败！无法保存该学生信息！");
// }
// }

// // ============================================================
// // 功能 2：按学号查询学生
// // ============================================================
// private static void queryStudent(Scanner scanner, StudentManager manager) {
// if (manager.getCount() == 0) {
// System.out.println("没有学生信息！");
// return;
// }

// String id = readText(scanner, "请输入要查询的学生学号：");

// // 【修复点】方法名 findByid 改成了 findById
// Student student = manager.findById(id);

// // 返回 null 说明没找到
// if (student == null) {
// System.out.println("没有找到学号为" + id + "的学生！");
// } else {
// System.out.println("学号\t姓名\t成绩"); // 表头。\t 是制表符，让列对齐
// System.out.println(student); // 自动调用 toString() 打印这个学生
// }
// }

// // ============================================================
// // 功能 3：显示全部学生
// // ============================================================
// private static void showAll(StudentManager manager) {
// if (manager.getCount() == 0) {
// System.out.println("没有学生信息！");
// return;
// }
// System.out.println("学号\t姓名\t成绩");

// // 增强 for 循环（for-each）：读作"对于 getStudents() 返回的数组里的每一个 Student 类型的元素 student"，
// // 依次取出每个元素，放进变量 student，然后执行一遍花括号里的内容。不用自己管下标。
// for (Student student : manager.getStudents()) {
// System.out.println(student);
// }
// System.out.println("共 " + manager.getCount() + " 个学生信息");
// }

// // ============================================================
// // 功能 4：统计平均分和最高分
// // ============================================================
// private static void showStatistics(StudentManager manager) {
// if (manager.getCount() == 0) {
// System.out.println("没有学生信息！");
// return;
// }

// int max = manager.getMaxScore(); // 先把最高分算出来，存进变量，下面要用两次

// System.out.println("学生人数：" + manager.getCount());

// // printf 是"格式化输出"：用格式串控制输出的样子。
// // %.2f ：把后面的小数按"保留 2 位小数"输出（f 表示浮点数，.2 表示小数点后 2 位，会四舍五入）
// // %n ：换行
// System.out.printf("平均分：%.2f%n", manager.getAverage());
// System.out.println("最高分：" + max);

// // 可能有多个人并列最高分，所以遍历全部学生，把所有分数等于最高分的都列出来
// System.out.println("最高分学生：");
// for (Student student : manager.getStudents()) {
// if (student.getScore() == max) {
// System.out.println(" " + student);
// }
// }
// }

// // ============================================================
// // 功能 5：按成绩从高到低排序
// //
// // 这个方法和 StudentManager 里的 sortByScoreDesc 同名，同样因为属于不同的类，互不冲突：
// // 这里负责检查和显示结果，真正的排序工作交给 manager.sortByScoreDesc() 去做。
// // ============================================================
// private static void sortByScoreDesc(StudentManager manager) {
// if (manager.isEmpty()) {
// System.out.println("暂无学生数据，请先录入！");
// return;
// }
// manager.sortByScoreDesc(); // 让管理器去排序（调用的是 StudentManager 里的方法）
// System.out.println("按成绩降序排序完成！");
// showAll(manager); // 排完序，显示一下结果
// }
// }
