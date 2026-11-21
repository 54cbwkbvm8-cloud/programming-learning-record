import java.util.Scanner;

class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + "（" + age + " 岁）";
    }
}

public class CampusManager {

    static final int MAX = 5;

    static final int MAX_AGE = 150;

    static Person[] people = new Person[MAX];

    static int count = 0;

    static Scanner sc = new Scanner(System.in);

    static boolean running = true;

    public static void main(String[] args) {
        System.out.println("===== 校园人员管理系统（容量 " + MAX + " 人） =====");

        while (running) {
            printMenu();

            if (!sc.hasNextLine()) {
                break;
            }

            int choice = toInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    findByName();
                    break;
                case 3:
                    sortByAge();
                    break;
                case 4:
                    showAll();
                    break;
                case 0:
                    System.out.println("再见！");
                    running = false;
                    break;
                default:
                    System.out.println("无效选择，请重新输入。");
            }
        }
    }

    static void printMenu() {
        System.out.println();
        System.out.println("1.录入 2.按照姓名查找 3.按年龄冒泡排序 4.显示全部 0.退出");
        System.out.print("请选择：");
    }

    static int toInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static String ask(String prompt) {
        System.out.print(prompt);

        if (!sc.hasNextLine()) {
            return "";
        }
        return sc.nextLine().trim();
    }

    static void addPerson() {

        if (count >= MAX) {
            System.out.println("容量已满，无法继续录入。");
            return;
        }

        String name = ask("请输入姓名：");
        if (name.isEmpty()) {
            System.out.println("姓名不能为空。");
            return;
        }

        int age = toInt(ask("请输入年龄："));
        if (age < 0 || age > MAX_AGE) {
            System.out.println("年龄输入有误（应为 0~" + MAX_AGE + " 的整数）。");
            return;
        }

        int type = toInt(ask("请选择类型（1.学生 2.教师 3.其他人员）："));

        Person person;

        if (type == 1) {
            String studentId = ask("请输入学号：");
            if (studentId.isEmpty()) {
                System.out.println("学号不能为空。");
                return;
            }
            person = new Student(name, age, studentId);

        } else if (type == 2) {
            String teacherId = ask("请输入工号：");
            if (teacherId.isEmpty()) {
                System.out.println("工号不能为空。");
                return;
            }
            person = new Teacher(name, age, teacherId);

        } else if (type == 3) {
            person = new Person(name, age);

        } else {
            System.out.println("类型输入有误。");
            return;
        }

        people[count++] = person;
        System.out.println("录入成功。");
    }

    static void findByName() {
        String name = ask("请输入要查找的姓名：");

        int found = 0;

        for (int i = 0; i < count; i++) {

            if (people[i].getName().equals(name)) {
                found++;
                System.out.println("找到：" + people[i]);
            }
        }

        if (found == 0) {
            System.out.println("未找到该人员。");
        }
    }

    static void sortByAge() {

        for (int i = 0; i < count - 1; i++) {

            for (int j = 0; j < count - 1 - i; j++) {

                if (people[j].getAge() > people[j + 1].getAge()) {

                    Person temp = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = temp;
                }
            }
        }
        System.out.println("已按年龄从小到大排序。");
    }

    static void showAll() {
        if (count == 0) {
            System.out.println("暂无人员信息。");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + people[i]);
        }
    }
}

class Student extends Person {

    private String studentId;

    public Student(String name, int age, String studentId) {
        super(name, age);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return super.toString() + "，学号：" + studentId;
    }
}

class Teacher extends Person {

    private String teacherId;

    public Teacher(String name, int age, String teacherId) {
        super(name, age);
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        return super.toString() + "，工号：" + teacherId;
    }
}

// // 导入 Scanner：Java 自带的"键盘输入工具"，用它才能读取键盘输入。
// // 它不在默认范围内，必须先 import，否则 Java 不认识 Scanner 这个名字。
// import java.util.Scanner;

// /**
// * CampusManager —— 校园人员管理系统（最多管理 5 个人）
// *
// * 运行后出现一个菜单，输入数字选择功能：
// * 1. 录入人员（可以录入学生、教师、或者其他人员）
// * 2. 按姓名查找
// * 3. 按年龄从小到大排序（冒泡排序）
// * 4. 显示全部人员
// * 0. 退出
// *
// * 【程序用到的面向对象知识】
// * · 继承：Student（学生）和 Teacher（教师）都继承 Person（人）。
// * 学生"是一个"人，教师"也是一个"人，所以它们自动拥有 Person 的姓名、年龄，再各自增加学号、工号。
// * · 多态：下面用一个 Person 类型的数组 people 同时存放普通人员、学生和教师。
// * 显示时统一调用 toString()，每个对象会自动执行【自己真实类型】的版本：
// * 学生显示学号，教师显示工号，普通人员什么都不多显示。
// * · 封装：Person 里的 name、age 是 private 的，外面只能通过 getName()、getAge() 读取。
// *
// * 【本次修复的问题】
// * 1. Student 和 Teacher 两个类定义了，却一次都没有被用到（最重要的一条）：
// * 原来的"录入"功能只问姓名和年龄，创建的永远是普通的 Person。
// * Student 和 Teacher 成了摆设，学号、工号永远录入不了，继承和多态也就没有发挥作用。
// * 修复：录入时增加一步"选择类型"：选学生就继续输入学号并创建 Student，
// * 选教师就继续输入工号并创建 Teacher，选其他就创建普通的 Person。
// * 2. 按姓名查找，遇到重名的人只显示第一个，后面的会被漏掉
// * （实际测试：录入两个"张三"，查找只显示了年龄 20 岁的那个，30 岁的被漏了）。
// * 修复：把所有同名的人都显示出来。
// * 3. 年龄只检查了"不能是负数"，99999 岁这样明显不合理的值也能录入。
// * 修复：年龄必须在 0 到 150 之间（上限放在常量 MAX_AGE 里，想改只改这一处）。
// * 4. 在录入过程中如果输入突然结束（比如强行结束输入、或者用文件喂数据而内容不够），
// * ask 方法里的 nextLine() 会读不到内容而崩溃，抛出 NoSuchElementException。
// * 修复：读之前先检查有没有下一行，没有就当作空输入处理，程序会正常退出。
// * 5. 方法名 printmenu 不规范：Java 的命名习惯是"驼峰式"，从第二个单词开始首字母大写，
// * 应为 printMenu。已改正。
// * （冒泡排序、显示全部等其余逻辑，我都实际测试过，是正确的，没有改动。）
// *
// * 【关于一个文件里有多个类】
// * 一个 .java 文件里可以写多个类，但最多只有一个能写 public，而且 public 类的名字必须和文件名一致。
// * 这里 public 的是 CampusManager，所以文件必须叫 CampusManager.java。
// * Person、Student、Teacher 没有写 public，是这个文件内部用的"配角"。
// * 请把这个文件单独放在一个文件夹里编译：如果同一个文件夹里还有别的文件也定义了
// * Person、Student 或 Teacher，会报错 "duplicate class"（类重复定义）。
// */

// // ============================================================
// // Person —— "人"类：所有校园人员的共同部分（父类）
// // ============================================================
// class Person {

// // private：私有，只有 Person 这个类自己里面的代码能直接访问。
// // 子类 Student、Teacher 虽然继承了 Person，也不能直接用 name 和 age，只能通过 getName()、getAge()
// 读取。
// // 这叫"封装"：把数据藏起来，外界只能通过公开的方法来使用。
// private String name; // 姓名
// private int age; // 年龄

// // 构造方法：new Person(...) 时自动执行，负责给新对象设置初始值。
// // · 名字必须和类名完全相同 · 没有返回值类型（连 void 都不写）
// // 参数 name、age 和成员变量同名。在方法里只写 name，Java 认为指的是离得最近的"参数 name"；
// // 要表示"这个对象自己的成员变量 name"，必须写 this.name（this 代表"当前这个对象自己"）。
// public Person(String name, int age) {
// this.name = name;
// this.age = age;
// }

// // getter 方法：对外提供"读取"私有成员变量的入口
// public String getName() {
// return name;
// }

// public int getAge() {
// return age;
// }

// // 重写 toString：规定这个对象"转成文字"时的样子。
// // System.out.println(对象) 和 "文字" + 对象 都会自动调用 toString()。
// // 不重写的话，打印出来是类似 Person@15db9742 这样看不懂的东西。
// // @Override 是一个标记，意思是"我是在重写父类（Object）里的方法"，写错了方法名或参数，编译器会提醒你。
// // 显示成类似：张三（20 岁）
// @Override
// public String toString() {
// return name + "（" + age + " 岁）";
// }
// }

// // ============================================================
// // CampusManager —— 程序入口，负责菜单和各项功能
// // ============================================================
// public class CampusManager {

// // ---------- 全局变量（下面的所有方法都能直接使用）----------
// // static 的意思是"属于类本身"，而不是属于某个对象。因为 main 是 static 方法，
// // 它只能直接使用 static 的变量和方法，所以这里的变量和下面的方法都要写 static。

// // final：最终的，赋值之后不能再修改，这样的变量叫"常量"。常量名习惯全部用大写字母，单词之间用下划线连接。
// // MAX 是最多能录入多少人。以后想改容量，只需要改这一个地方。
// static final int MAX = 5;

// // 【修复点】新增的常量：年龄的上限。原来没有上限，99999 岁也能录入。
// static final int MAX_AGE = 150;

// // 存放人员的数组。类型是 Person[]，每个格子里可以放"一个 Person，或者 Person
// 的任何子类对象（Student、Teacher）"。
// // 这就是多态：用父类类型的数组，统一存放各种子类对象。
// // new Person[MAX] 只是准备好了 MAX 个空格子（里面的值都是 null），还没有创建任何人。
// static Person[] people = new Person[MAX];

// // count：目前已经录入了几个人。数组的长度是固定的 MAX，但真正存了人的只有前 count 个格子。
// // 举例：count 是 3，说明下标 0、1、2 的格子里有人，下标 3、4 的格子还是空的（null）。
// // 所以下面所有遍历数组的循环都写 i < count，不能写 i < MAX，否则会碰到空格子，对 null 调用方法会崩溃。
// static int count = 0;

// // 键盘输入工具（System.in 表示键盘）。放在这里，所有方法都能用，不用每次传来传去。
// static Scanner sc = new Scanner(System.in);

// // 程序是否继续运行的开关。true 表示继续，false 表示退出。
// static boolean running = true;

// // main 是程序的入口：运行程序时，Java 从这里的第一行开始，一行一行往下执行。
// public static void main(String[] args) {
// System.out.println("===== 校园人员管理系统（容量 " + MAX + " 人） =====");

// // 主循环：只要 running 是 true，就一遍一遍地显示菜单、处理用户的选择。
// while (running) {
// printMenu();

// // sc.hasNextLine()：检查输入里还有没有下一行。没有了（输入已经结束），就用 break 跳出循环，结束程序。
// // 先检查再读取，是为了防止输入结束后继续读取导致程序崩溃。
// // ! 表示"取反"，!sc.hasNextLine() 读作"没有下一行了"。
// if (!sc.hasNextLine()) {
// break;
// }

// // 读一整行，trim() 去掉前后多余的空格，再用 toInt 转成整数。
// // 如果输入的不是数字，toInt 会返回 -1，下面的 switch 会把它当作"无效选择"。
// int choice = toInt(sc.nextLine().trim());

// // switch：多分支选择。根据 choice 的值，跳到对应的 case 去执行。
// // 每个 case 末尾的 break 的作用是"执行完就跳出 switch"。
// // 如果漏写 break，程序会"穿透"，继续执行下一个 case 里的代码，造成错误。
// switch (choice) {
// case 1:
// addPerson();
// break;
// case 2:
// findByName();
// break;
// case 3:
// sortByAge();
// break;
// case 4:
// showAll();
// break;
// case 0:
// System.out.println("再见！");
// running = false; // 把开关关掉，这一轮结束后循环条件不成立，程序结束
// break;
// default:
// // default：上面所有 case 都没有匹配上时执行
// System.out.println("无效选择，请重新输入。");
// }
// }
// }

// // 显示菜单。
// // static ：因为要被 static 的 main 直接调用，所以也要写 static
// // void ：执行完不返回任何结果，只负责做事（这里是打印）
// // 【修复点】方法名原来是 printmenu，已改成规范的驼峰写法 printMenu。
// static void printMenu() {
// System.out.println(); // 先空一行，让每轮菜单之间有间隔
// System.out.println("1.录入 2.按照姓名查找 3.按年龄冒泡排序 4.显示全部 0.退出");
// System.out.print("请选择："); // print 不换行，用户的输入会紧跟在冒号后面
// }

// // ============================================================
// // toInt：把文字转换成整数。转换失败（不是合法的整数）时，返回 -1
// //
// // 参数 text 是用户输入的一段文字。
// // 返回 -1 是一种"约定"：菜单编号和年龄都不会是 -1，所以 -1 可以代表"输入有误"。
// // ============================================================
// static int toInt(String text) {
// // try-catch：预防错误导致程序崩溃。
// // try ：把"可能出错的代码"放在里面试着运行
// // catch ：如果 try 里出了指定类型的错误，就跳到这里处理，程序不会崩溃
// try {
// // Integer.parseInt(文字)：把文字转换成整数。例如 "42" 变成 42。
// // 如果文字不是合法的整数（比如 "abc"、"3.5"、空字符串），它会抛出 NumberFormatException 错误。
// return Integer.parseInt(text);
// } catch (NumberFormatException e) {
// // NumberFormatException = "数字格式异常"，进入这里说明文字不是整数
// return -1;
// }
// }

// // ============================================================
// // ask：显示提示语，读取用户输入的一行文字，并去掉前后空格
// // ============================================================
// static String ask(String prompt) {
// System.out.print(prompt);

// // 【修复点】原来这里直接调用 sc.nextLine()。
// // 如果输入已经结束了（没有下一行），nextLine() 会抛出 NoSuchElementException 让程序崩溃。
// // 现在先检查，没有下一行就返回空字符串 ""（一个字符都没有的文字）。
// // 空字符串会被调用者当作"没有输入"处理（比如姓名为空会提示"姓名不能为空"），
// // 然后回到主菜单，主菜单发现没有下一行，就正常退出了。
// if (!sc.hasNextLine()) {
// return "";
// }
// return sc.nextLine().trim();
// }

// // ============================================================
// // addPerson：录入一个人员（功能 1）
// // ============================================================
// static void addPerson() {

// // 先检查容量。已经满了就不用让用户白白输入一大堆信息了。
// if (count >= MAX) {
// System.out.println("容量已满，无法继续录入。");
// return; // return：立刻结束这个方法，回到主菜单
// }

// // ---------- 第一步：姓名 ----------
// String name = ask("请输入姓名：");
// // isEmpty() 判断文字是不是空的（一个字符都没有）
// if (name.isEmpty()) {
// System.out.println("姓名不能为空。");
// return;
// }

// // ---------- 第二步：年龄 ----------
// // ask(...) 先读取一行文字，toInt(...) 再把它转成整数（转换失败会得到 -1）。
// int age = toInt(ask("请输入年龄："));
// // 【修复点】原来只检查了 age < 0。现在还要求不能超过 MAX_AGE（150），
// // 防止 99999 岁这样明显不合理的值。
// // || 表示"或者"：小于 0，或者大于 150，只要有一个成立就是不合法。
// if (age < 0 || age > MAX_AGE) {
// System.out.println("年龄输入有误（应为 0~" + MAX_AGE + " 的整数）。");
// return;
// }

// // ---------- 第三步：选择人员类型，并创建对应的对象 ----------
// // 【修复点】这一步是新增的。原来无论怎样都是 new Person(name, age)，
// // 所以 Student 和 Teacher 这两个类从来没有被用到过。
// int type = toInt(ask("请选择类型（1.学生 2.教师 3.其他人员）："));

// // 声明一个 Person 类型的变量，用来保存新创建的对象。
// // 它的类型是父类 Person，但里面可以放 Person、Student、Teacher 中的任何一种对象，
// // 这叫"向上转型"：子类对象交给父类类型的变量保管，Java 会自动完成，不需要写转换符号。
// Person person;

// if (type == 1) {
// // 选的是学生：还要输入学号，然后创建 Student 对象
// String studentId = ask("请输入学号：");
// if (studentId.isEmpty()) {
// System.out.println("学号不能为空。");
// return;
// }
// person = new Student(name, age, studentId);

// } else if (type == 2) {
// // 选的是教师：还要输入工号，然后创建 Teacher 对象
// String teacherId = ask("请输入工号：");
// if (teacherId.isEmpty()) {
// System.out.println("工号不能为空。");
// return;
// }
// person = new Teacher(name, age, teacherId);

// } else if (type == 3) {
// // 选的是其他人员：创建普通的 Person 对象
// person = new Person(name, age);

// } else {
// // 输入的不是 1、2、3（包括输入的不是数字，toInt 返回 -1 的情况）
// System.out.println("类型输入有误。");
// return;
// }

// // people[count++] = person; 这一句做了两件事，顺序是：
// // ① 先用当前的 count 当作下标，把新人员放进数组的那个格子里
// // ② 然后 count 再加 1
// // 例如 count 是 3 时：新人员被放进 people[3]，然后 count 变成 4。
// // （count++ 是"先使用，后加一"；如果写成 ++count，则是"先加一，后使用"，结果就错位了。）
// people[count++] = person;
// System.out.println("录入成功。");
// }

// // ============================================================
// // findByName：按姓名查找（功能 2）
// //
// // 【修复点】原来的做法是先调用 indexOfName 找到第一个同名的人，就停止了。
// // 如果有重名的人（比如两个"张三"），后面的就被漏掉了。
// // 现在改成：把整个数组都检查一遍，所有同名的人都显示出来，并统计找到了几个。
// // （原来的辅助方法 indexOfName 现在用不上了，已删除。）
// // ============================================================
// static void findByName() {
// String name = ask("请输入要查找的姓名：");

// int found = 0; // 计数器：已经找到了几个。初始值必须是 0（一开始还一个都没找到）

// // 遍历已经录入的人（只检查前 count 个格子）
// for (int i = 0; i < count; i++) {

// // 比较两个文字的内容是否相同，必须用 equals，不能用 ==。
// // 因为对文字用 == 比较的是"是不是同一个对象"（地址是否相同），而不是内容是否相同。
// if (people[i].getName().equals(name)) {
// found++; // 找到一个，计数器加 1
// // "找到：" + people[i]：文字和对象用 + 连接时，会自动调用对象的 toString()。
// // 因为 people[i] 可能是 Person、Student 或 Teacher，会自动用各自的版本：
// // 学生会显示学号，教师会显示工号。这就是"多态"。
// System.out.println("找到：" + people[i]);
// }
// }

// // 整个数组都检查完了，一个都没找到（计数器仍然是 0）
// if (found == 0) {
// System.out.println("未找到该人员。");
// }
// }

// // ============================================================
// // sortByAge：按年龄从小到大排序，使用冒泡排序（功能 3）
// //
// // 冒泡排序的思想：反复比较"相邻的两个"，如果顺序不对就交换，
// // 每走完一趟，当前没排好的里面最大的那个就会"浮"到最右边。
// // ============================================================
// static void sortByAge() {

// // 外层循环：最多走 count - 1 趟。注意用的是 count，不是 MAX，
// // 因为数组后面的格子可能是 null，不能参与比较（对 null 调用 getAge 会崩溃）。
// for (int i = 0; i < count - 1; i++) {

// // 内层循环：每趟从左到右比较相邻的两个。
// // 为什么条件是 j < count - 1 - i？
// // · 每次要访问 people[j + 1]，所以 j 最大只能是 count - 2，否则会越界
// // · 每走完一趟，最右边就多一个已经排好的，不需要再比较，所以每趟再少比较 i 个
// for (int j = 0; j < count - 1 - i; j++) {

// // 要的是"从小到大"，所以如果左边的年龄【大于】右边的，说明顺序反了，需要交换。
// // 只有严格大于才交换，年龄相同的不交换，所以年龄相同的人会保持原来的先后顺序。
// if (people[j].getAge() > people[j + 1].getAge()) {

// // 交换两个元素需要借助一个临时变量 temp：
// // 如果直接 a = b; b = a;，第一句执行后 a 原来的值就丢了。
// // 类比：想交换两杯饮料，必须再拿一个空杯子当中转。
// Person temp = people[j]; // ① 左边的先存进临时变量
// people[j] = people[j + 1]; // ② 右边的放到左边
// people[j + 1] = temp; // ③ 临时变量里的放到右边，交换完成
// }
// }
// }
// System.out.println("已按年龄从小到大排序。");
// }

// // ============================================================
// // showAll：显示全部人员（功能 4）
// // ============================================================
// static void showAll() {
// if (count == 0) {
// System.out.println("暂无人员信息。");
// return;
// }
// for (int i = 0; i < count; i++) {
// // (i + 1) 要加括号：让 Java 先算 i + 1，再拼接到文字里。
// // 否则 i + 1 会被当成"先拼接 i，再拼接 1"，显示成 "01"、"11" 这样的错误结果。
// // i 从 0 开始，但给人看的序号习惯从 1 开始，所以要加 1。
// // 同样，people[i] 会自动调用 toString()，学生显示学号，教师显示工号。
// System.out.println((i + 1) + ". " + people[i]);
// }
// }
// }

// // ============================================================
// // Student —— "学生"类：继承 Person，增加学号
// //
// // class Student extends Person 表示 Student "继承"了 Person：
// // Person 里有的 getName()、getAge()、toString() 等，Student 不用重新写，就能直接使用。
// // ============================================================
// class Student extends Person {

// private String studentId; // 学号（Student 新增的属性）

// // 子类的构造方法。
// // 【规则】子类构造方法的第一行，必须先调用父类的构造方法，写法是 super(...)。
// // 因为子类对象里包含了一个"父类的部分"（name、age），必须先把它准备好。
// // super(name, age) 把姓名和年龄交给父类 Person 的构造方法处理。
// public Student(String name, int age, String studentId) {
// super(name, age);
// this.studentId = studentId;
// }

// public String getStudentId() {
// return studentId;
// }

// // 重写父类的 toString()：方法名、参数列表必须和父类完全一样，内容可以不同。
// // super.toString() 表示调用【父类】的版本，得到 "张三（20 岁）"，再在后面补充学号，
// // 这样不用把父类已经写过的内容重复写一遍。显示成类似：张三（20 岁），学号：2026001
// @Override
// public String toString() {
// return super.toString() + "，学号：" + studentId;
// }
// }

// // ============================================================
// // Teacher —— "教师"类：继承 Person，增加工号。写法和 Student 完全对应
// // ============================================================
// class Teacher extends Person {

// private String teacherId; // 工号（Teacher 新增的属性）

// public Teacher(String name, int age, String teacherId) {
// super(name, age);
// this.teacherId = teacherId;
// }

// public String getTeacherId() {
// return teacherId;
// }

// // 显示成类似：李老师（35 岁），工号：T001
// @Override
// public String toString() {
// return super.toString() + "，工号：" + teacherId;
// }
// }
