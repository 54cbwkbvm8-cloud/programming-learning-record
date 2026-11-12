public class EncapsulationStaticDemo {
    public static void main(String[] args) {
        System.out.println("========== 第一部分：封装（private + getter/setter） ==========");
        OpenStudent openStu = new OpenStudent();
        openStu.score = -999;
        System.out.println("没有封装时，成绩被改成：" + openStu.score);
        SafeStudent s1 = new SafeStudent("20260101", "张三", 85);
        System.out.println("用 getter 读取：" + s1.getId() + "，" + s1.getName() + "，" + s1.getScore());
        int[] tests = { 150, -1, 101, 0, 100 };
        for (int t : tests) {
            boolean ok = s1.setScore(t);
            System.out.println("setScore(" + t + ") -> " + ok + "，当前成绩：" + s1.getScore());
        }
        System.out.println("========== 第二部分：this 区分成员变量与参数 ==========");
        NoThisStudent bad = new NoThisStudent("错误示范", 50);
        System.out.println("漏写 this：name=" + bad.name + "，score=" + bad.score);
        SafeStudent s2 = new SafeStudent("20260202", "李四", 90);
        System.out.println("写了 this：name=" + s2.getName() + "，score=" + s2.getScore());
        System.out.println("========== 第三部分：static 变量与 static 方法 ==========");
        CounterWrong c1 = new CounterWrong();
        CounterWrong c2 = new CounterWrong();
        CounterWrong c3 = new CounterWrong();
        System.out.println("普通变量计数：c1=" + c1.count + "，c2=" + c2.count + "，c3=" + c3.count);
        SafeStudent s3 = new SafeStudent("20260303", "王五", 80);
        System.out.println("static 计数器：共创建了 " + SafeStudent.getCount() + " 个学生对象");
        System.out.println("========== 第四部分：对象作参数，传入的是地址的副本 ==========");
        System.out.println("调用前 s1 的成绩：" + s1.getScore());
        changeScore(s1);
        System.out.println("调用 changeScore(s1) 后，s1 的成绩：" + s1.getScore());
        reassign(s1);
        System.out.println("调用 reassign(s1) 后，s1 的姓名：" + s1.getName());
        System.out.println("此时 static 计数器：" + SafeStudent.getCount());
    }

    static void changeScore(SafeStudent s) {
        s.setScore(60);
    }

    static void reassign(SafeStudent s) {
        s = new SafeStudent("99999999", "临时", 1);
        System.out.println("方法内部 s 指向的姓名：" + s.getName());
    }
}

class SafeStudent {
    private String id;
    private String name;
    private int score;
    private static int count = 0;

    public SafeStudent(String id, String name, int score) {
        if (!isValidScore(score)) {
            throw new IllegalArgumentException("成绩必须在 0~100 之间，但收到了：" + score);
        }
        this.id = id;
        this.name = name;
        this.score = score;
        count++;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public boolean setScore(int score) {
        if (!isValidScore(score)) {
            return false;
        }
        this.score = score;
        return true;
    }

    public static int getCount() {
        return count;
    }

    private static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }
}

class OpenStudent {
    String id;
    String name;
    int score;
}

class NoThisStudent {
    String name;
    int score;

    NoThisStudent(String name, int score) {
        name = name;
        score = score;
    }
}

class CounterWrong {
    int count = 0;

    CounterWrong() {
        count++;
    }
}
// /**
// * EncapsulationStaticDemo —— 演示四个面向对象的基础概念
// *
// * 第一部分：封装（private + getter/setter） —— 为什么要把数据"藏起来"，只留规定的入口
// * 第二部分：this 关键字 —— 当参数名和成员变量名相同时，怎么区分
// * 第三部分：static 变量与 static 方法 —— "属于整个类"的数据，而不是属于某一个对象
// * 第四部分：对象作为方法的参数 —— 传进去的是"地址的副本"
// *
// * 【先建立几个基本概念（零基础必读）】
// * · 类（class）：对一类事物的"设计图纸"。例如 SafeStudent 这张图纸规定了"学生有学号、姓名、成绩"。
// * · 对象（object）：按照图纸造出来的"实物"。用 new 关键字创建，一张图纸可以造出很多个对象。
// * · 成员变量：定义在类里面、方法外面的变量，是对象身上的"属性"（学号、姓名、成绩）。
// * · 构造方法：和类同名、没有返回值类型的特殊方法，在 new 的时候自动执行，负责给新对象"初始化"。
// * · 一个 .java 文件里可以写多个类，但最多只能有一个 public 类，并且 public 类的名字必须和文件名一致。
// * 这个文件里的 public 类是 EncapsulationStaticDemo；后面的几个类都没有写 public，
// * 它们是给 main 方法演示用的"配角"。
// *
// * 【本次修复的问题】
// * 1. 类名冲突：原代码在这个文件里也定义了一个叫 Student 的类，
// * 而你的 Student.java 里已经有一个 Student 类了（两个是不同练习的不同版本）。
// * 把这些文件放进同一个文件夹一起编译时，Java 会报错 "duplicate class: Student"（类重复了），
// * 连带 StudentTest.java 也会跟着报一堆错误。
// * 修复：把这个文件里的 Student 改名为 SafeStudent（"安全的学生"，正好和 OpenStudent
// * "不设防的学生"形成对比），文件里所有用到它的地方都同步改了。
// * 2. 封装漏洞：原来的构造方法直接把传入的成绩赋给 score，没有检查范围。
// * 也就是说 new Student("1", "a", 150) 能创建出成绩为 150 的对象，绕过了 setScore 里的
// * "0~100 才合法"的检查，这和"封装是为了保证数据合法"的目的自相矛盾。
// * 修复：构造方法也用同一条规则检查，不合法就抛出异常，拒绝创建这个对象。
// * 3. 几处输出文字写错了（只影响显示，不影响逻辑）：
// * "setScore(150) - > ..." 里的 "- >" 应为箭头 "->"；
// * "写了this name=：" 应为 "写了 this：name="；
// * 第四部分标题 "传入地址多副本" 语句不通，应为"传入的是地址的副本"；
// * "调用 s1 的成绩" 缺了"前"字，应为"调用前 s1 的成绩"（下一行才是"调用后"）；
// * "用getter 读取" 的空格位置不对。
// * （其余的演示逻辑都是正确的，运行结果没有变化。）
// */
// public class EncapsulationStaticDemo {

// // main 是程序的入口：运行程序时，Java 从这里的第一行开始，一行一行往下执行。
// public static void main(String[] args) {

// // ==================== 第一部分：封装 ====================
// System.out.println("========== 第一部分：封装（private + getter/setter） ==========");

// // ---------- 反面教材：没有封装的类 ----------
// // new OpenStudent()：按 OpenStudent 这张图纸造一个对象，并把它的"地址"存进变量 openStu。
// OpenStudent openStu = new OpenStudent();

// // OpenStudent 的成员变量没有加 private，谁都可以直接用 对象名.变量名 去读和改。
// // 于是可以随便把成绩改成 -999 这种明显不合理的值，类自己完全没有办法阻止。
// openStu.score = -999;
// System.out.println("没有封装时，成绩被改成：" + openStu.score); // -999

// // ---------- 正面示范：封装好的类 ----------
// // new SafeStudent(...)：括号里的三个值会按顺序交给构造方法的三个参数。
// SafeStudent s1 = new SafeStudent("20260101", "张三", 85);

// // SafeStudent 的成员变量是 private（私有），在类的外面不能直接访问（写 s1.score 会编译报错）。
// // 想读取只能调用公开的"getter 方法"（get 开头，意思是"获取"）。
// // 【修复点】原来写的是"用getter 读取"，已改成"用 getter 读取"。
// System.out.println("用 getter 读取：" + s1.getId() + "，" + s1.getName() + "，" +
// s1.getScore());

// // 想修改成绩，只能调用"setter 方法"（set 开头，意思是"设置"）。
// // setter 里写了检查规则：只有 0~100 的成绩才会被接受，否则拒绝并返回 false。
// // 下面用 5 个值来测试，其中前三个不合法，后两个正好是合法范围的边界值：
// int[] tests = { 150, -1, 101, 0, 100 };
// for (int t : tests) { // 增强 for：依次取出数组里的每个值，放进变量 t
// boolean ok = s1.setScore(t); // 返回 true 表示修改成功，false 表示被拒绝
// // 【修复点】原来输出的文字是 "- >"，已改成箭头 "->"。
// // 预期：150、-1、101 被拒绝，成绩仍是 85；0 和 100 是边界值，合法，修改成功。
// System.out.println("setScore(" + t + ") -> " + ok + "，当前成绩：" +
// s1.getScore());
// }
// // 循环结束后，s1 的成绩是最后一次成功设置的 100。

// // ==================== 第二部分：this ====================
// System.out.println("========== 第二部分：this 区分成员变量与参数 ==========");

// // ---------- 反面教材：构造方法里漏写 this ----------
// // 构造方法里的 name = name; 是把参数 name 赋给它自己，成员变量根本没被赋值，
// // 所以成员变量保持默认值：String 默认是 null（什么都没有），int 默认是 0。
// NoThisStudent bad = new NoThisStudent("错误示范", 50);
// System.out.println("漏写 this：name=" + bad.name + "，score=" + bad.score); //
// null 和 0

// // ---------- 正面示范：写了 this ----------
// SafeStudent s2 = new SafeStudent("20260202", "李四", 90);
// // 【修复点】原来输出的文字是"写了this name=："，已改成"写了 this：name="。
// System.out.println("写了 this：name=" + s2.getName() + "，score=" +
// s2.getScore()); // 李四 和 90

// // ==================== 第三部分：static ====================
// System.out.println("========== 第三部分：static 变量与 static 方法 ==========");

// // ---------- 反面教材：用"普通变量"计数 ----------
// // 创建三个 CounterWrong 对象。每个对象都有【自己独立的】一个 count，
// // 每个对象创建时都只把自己的 count 从 0 加到 1，互相不知道对方的存在。
// CounterWrong c1 = new CounterWrong();
// CounterWrong c2 = new CounterWrong();
// CounterWrong c3 = new CounterWrong();
// System.out.println("普通变量计数：c1=" + c1.count + "，c2=" + c2.count + "，c3=" +
// c3.count); // 都是 1

// // ---------- 正面示范：用 static 变量计数 ----------
// // 到目前为止创建过的 SafeStudent：s1、s2，再加上下面的 s3，一共 3 个。
// // （注意：OpenStudent、NoThisStudent 不是 SafeStudent，不会被这个计数器统计。）
// SafeStudent s3 = new SafeStudent("20260303", "王五", 80);

// // static 方法属于"类"本身，不属于某个对象，所以直接用 类名.方法名() 调用，不需要先 new 对象。
// System.out.println("static 计数器：共创建了 " + SafeStudent.getCount() + " 个学生对象");
// // 3

// // ==================== 第四部分：对象作为参数 ====================
// // 【修复点】原标题"传入地址多副本"语句不通，改成"传入的是地址的副本"。
// System.out.println("========== 第四部分：对象作参数，传入的是地址的副本 ==========");

// // 【修复点】原来写的是"调用 s1 的成绩"，漏了"前"字，下一行才是"调用后"。
// System.out.println("调用前 s1 的成绩：" + s1.getScore()); // 100

// // changeScore：方法里通过地址找到了同一个对象，并改了它的成绩 → 外面的 s1 也变了
// changeScore(s1);
// System.out.println("调用 changeScore(s1) 后，s1 的成绩：" + s1.getScore()); // 60

// // reassign：方法里让参数指向了一个新对象，但不影响外面的 s1
// reassign(s1);
// System.out.println("调用 reassign(s1) 后，s1 的姓名：" + s1.getName()); // 仍然是 张三

// // 此时计数器是 4：除了 s1、s2、s3，reassign 方法里还创建了一个临时的 SafeStudent。
// // 那个临时对象虽然方法结束后已经没用了，但"创建"这个动作已经被计数器记录了。
// // static 计数器统计的是"一共创建过多少次"，不是"现在还剩多少个"。
// System.out.println("此时 static 计数器：" + SafeStudent.getCount()); // 4
// }

// // ============================================================
// // 方法 changeScore：修改传进来的对象的成绩
// //
// // 为什么 main 里不用写 EncapsulationStaticDemo.changeScore(...)，直接写
// changeScore(...)？
// // 因为它们在同一个类里，同一个类里的方法可以直接互相调用。
// //
// // 【理解对象传参】
// // 对象存放在内存里，变量 s1 里存的其实是这个对象的"地址"（可以想成门牌号）。
// // 调用 changeScore(s1) 时，是把 s1 里的"地址"复制一份交给参数 s。
// // 于是 s1 和 s 是【两个变量，指向同一个对象】，通过 s 修改对象，s1 看到的当然也变了。
// // ============================================================
// static void changeScore(SafeStudent s) {
// s.setScore(60); // 通过地址找到同一个对象，修改它的成绩。（返回值 true/false 这里不关心，可以不接收）
// }

// // ============================================================
// // 方法 reassign：让参数指向一个全新的对象
// //
// // s = new SafeStudent(...) 的意思是：让方法里的这个变量 s 去指向一个新创建的对象。
// // 这只是把 s 里存的那份"地址的副本"换成了另一个地址，外面的 s1 里存的地址没有任何变化，
// // 仍然指向原来的对象。好比：朋友把手里抄的门牌号涂掉，改成了另一个地址，你家的门牌号并没有变。
// // ============================================================
// static void reassign(SafeStudent s) {
// s = new SafeStudent("99999999", "临时", 1);
// System.out.println("方法内部 s 指向的姓名：" + s.getName()); // 临时（方法内部看到的是新对象）
// }
// }

// // ============================================================
// // SafeStudent —— 封装好的学生类（正面示范）
// //
// // 【什么是封装】
// // 把对象的数据（成员变量）用 private 藏起来，不让外界直接碰，
// // 只开放几个受控的公开方法（getter / setter）供外界读写。
// // 好处：类可以在 setter 里做检查，保证数据永远是合法的（比如成绩只能是 0~100）。
// // 好比银行：你不能自己跑进金库拿钱，只能通过柜台（方法）办理，柜台会核对你的身份和金额。
// //
// // 【修复点】类名从 Student 改成了 SafeStudent，避免和 Student.java 里的 Student 类重名。
// // ============================================================
// class SafeStudent {

// // private：私有，只有 SafeStudent 这个类自己里面的代码能访问，类的外面不能直接访问。
// private String id; // 学号（用文字类型 String，因为学号不用来做加减运算，而且可能以 0 开头）
// private String name; // 姓名
// private int score; // 成绩

// // private static：既是私有的，又是"静态的"。
// // 普通成员变量：每个对象各有一份，互不相干。
// // static 变量：属于"类"本身，整个类只有一份，所有对象共用同一个。
// // 所以用它来记录"一共创建了多少个对象"正合适：不管哪个对象创建，都是在给同一个 count 加 1。
// // = 0 是初始值，程序一开始还没有创建任何对象。
// private static int count = 0;

// // ============================================================
// // 构造方法：用来创建对象时初始化数据
// // · 名字必须和类名完全相同；
// // · 没有返回值类型（连 void 都不写）；
// // · 在 new SafeStudent(...) 的时候自动执行。
// //
// // 【修复点】原来的构造方法直接赋值，没有检查成绩范围，
// // 可以通过构造方法创建出成绩为 150 的对象，绕过 setScore 的检查。
// // 现在构造方法也用同一条规则（isValidScore）检查。
// // ============================================================
// public SafeStudent(String id, String name, int score) {

// // 成绩不合法，就拒绝创建这个对象。
// // throw new IllegalArgumentException(...) 的意思是"主动制造一个错误并抛出去"：
// // IllegalArgumentException 是 Java 自带的错误类型，意思是"传进来的参数不合法"。
// // 抛出后，这个构造方法会立刻结束，后面的赋值和 count++ 都不会执行，
// // 所以这个对象不会被创建，计数器也不会增加。
// // 如果调用的地方没有用 try-catch 接住这个错误，程序会停止并显示错误信息。
// // （你可以自己试试：把 main 里某个 new SafeStudent 的成绩改成 150，运行就能看到这个错误。）
// if (!isValidScore(score)) {
// throw new IllegalArgumentException("成绩必须在 0~100 之间，但收到了：" + score);
// }

// // 【this 的作用】
// // 构造方法的参数名（id、name、score）和成员变量的名字完全一样。
// // 在方法内部，只写 name，Java 会认为指的是离得最近的参数 name，而不是成员变量 name。
// // this.name ：this 代表"当前正在创建的这个对象自己"，this.name 就是"这个对象的成员变量 name"
// // name ：参数 name
// // 所以 this.name = name; 的意思是：把参数 name 的值，赋给这个对象自己的成员变量 name。
// this.id = id;
// this.name = name;
// this.score = score;

// // 每创建一个对象，就让共用的计数器加 1。
// // 放在检查之后：如果上面抛出了异常，这一行不会执行，没有创建成功的对象不会被计数。
// count++;
// }

// // ---------- getter 方法：对外提供"读取"的入口 ----------
// // 因为成员变量是 private，外面不能直接读，所以提供这些公开的方法。
// // 方法头里的 String / int 是返回值类型：表示方法会把对应类型的值交还给调用者。
// public String getId() {
// return id;
// }

// public String getName() {
// return name;
// }

// public int getScore() {
// return score;
// }

// // ---------- setter 方法：对外提供"修改"的入口，并且带检查 ----------
// // 返回 boolean：true 表示修改成功，false 表示因为成绩不合法而被拒绝。
// public boolean setScore(int score) {
// if (!isValidScore(score)) {
// return false; // 不合法，直接返回 false，成绩保持原样不变
// }
// // 合法，才真正修改。这里同样用 this.score 区分"成员变量"和"参数"。
// this.score = score;
// return true;
// }

// // ---------- static 方法：直接通过类名调用 ----------
// // static 方法属于类本身，调用时用 SafeStudent.getCount()，不需要先创建对象。
// // static 方法里【只能访问 static 的变量】（这里的 count 是 static，所以可以），
// // 不能访问 id、name、score 这些属于具体对象的变量，因为调用它时，可能一个对象都还没有。
// public static int getCount() {
// return count;
// }

// // ---------- 私有的辅助方法：判断成绩是否合法 ----------
// // 把"0~100 才合法"这条规则只写一次，构造方法和 setScore 都来调用它。
// // 好处：以后规则要改（比如满分改成 150），只需要改这一个地方，不会漏改。
// // private：只给类自己内部使用，外面不能调用。
// // static：这个判断只依赖传进来的参数，不依赖具体对象，所以可以写成 static。
// // && 表示"并且"：两个条件同时成立才返回 true。
// private static boolean isValidScore(int score) {
// return score >= 0 && score <= 100;
// }
// }

// // ============================================================
// // OpenStudent —— 没有封装的学生类（反面教材）
// //
// // 成员变量前面没有写 private，也没有写任何访问修饰符，
// // 同一个包里的任何代码都可以直接用 对象名.变量名 读取和修改，类对自己的数据毫无保护能力。
// // ============================================================
// class OpenStudent {
// String id;
// String name;
// int score;
// }

// // ============================================================
// // NoThisStudent —— 构造方法里漏写 this 的学生类（反面教材）
// // ============================================================
// class NoThisStudent {
// String name; // 成员变量 name，默认值是 null
// int score; // 成员变量 score，默认值是 0

// NoThisStudent(String name, int score) {
// // 【错误写法】左右两边的 name 都是"参数 name"（因为参数离得最近，会遮住同名的成员变量），
// // 所以这句话的意思是"把参数 name 的值赋给参数 name 自己"，没有任何作用。
// // 成员变量 name 从头到尾没有被赋值，仍然是默认的 null。score 同理，仍然是 0。
// // （这种写法 Java 不会报错，但很多编程工具会提醒你"自己赋值给自己"。）
// // 正确写法是 this.name = name; this.score = score;（见上面的 SafeStudent）
// name = name;
// score = score;
// }
// }

// // ============================================================
// // CounterWrong —— 用"普通变量"计数的类（反面教材）
// //
// // count 前面没有 static，所以它是普通的成员变量：每个 CounterWrong 对象都有自己独立的一个 count。
// // 每个对象在创建时，把"自己的"count 从 0 加到 1，别的对象的 count 它改不到，也看不到。
// // 因此不管创建多少个对象，每个对象的 count 都是 1，没法统计总数。
// // 想统计总数，必须像 SafeStudent 里那样，把 count 声明成 static。
// // ============================================================
// class CounterWrong {
// int count = 0;

// CounterWrong() {
// count++;
// }
// }
