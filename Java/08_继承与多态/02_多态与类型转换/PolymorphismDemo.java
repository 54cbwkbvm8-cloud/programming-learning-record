public class PolymorphismDemo {
    public static void main(String[] args) {
        System.out.println("===== 1. 向上转型：父类引用指向子类对象 =====");

        Person p1 = new Student("张三", 20);

        p1.introduce();

        System.out.println("p1 指向的真实对象类型： " + p1.getClass().getName());
        System.out.println();

        System.out.println("===== 2. 多态数组：Person[] 数组存放不同子类对象 =====");

        Person[] people = new Person[3];
        people[0] = new Student("李四", 21);
        people[1] = new Teacher("王五", 35);
        people[2] = new Student("赵六", 19);

        for (Person p : people) {
            p.introduce();
        }
        System.out.println();

        System.out.println("===== 3. 向下转型 与 instanceof =====");

        Student s1 = (Student) p1;

        for (Person p : people) {
            if (p instanceof Student) {
                Student s = (Student) p;
                s.study();
            } else if (p instanceof Teacher) {
                Teacher t = (Teacher) p;
                t.teach();
            } else {
                System.out.println(p.getName() + " 不是学生或老师，无法转换");
            }
        }
    }
}

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

    public void introduce() {
        System.out.println("我叫" + name + "，今年" + age + "岁。");
    }
}

class Student extends Person {
    private String major;

    public Student(String name, int age) {
        this(name, age, "计算机科学与技术");
    }

    public Student(String name, int age, String major) {
        super(name, age);
        this.major = major;
    }

    @Override
    public void introduce() {
        System.out.print("[学生] ");
        super.introduce();
        System.out.println("我的专业是" + major + "。");
    }

    public void study() {
        System.out.println(getName() + " 正在学习" + major + "。");
    }
}

class Teacher extends Person {
    private String subject;

    public Teacher(String name, int age) {
        this(name, age, "Java 程序设计");
    }

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    @Override
    public void introduce() {
        System.out.print("[老师] ");
        super.introduce();
        System.out.println("我教的是" + subject + "。");
    }

    public void teach() {
        System.out.println(getName() + " 正在讲授" + subject + "。");
    }
}
// /**
// * PolymorphismDemo —— 演示"多态"：向上转型、多态数组、向下转型与 instanceof
// *
// * 【什么是多态】
// * 同样一句调用 p.introduce()，如果 p 实际指向的是学生，就执行学生的自我介绍；
// * 如果 p 实际指向的是老师，就执行老师的自我介绍。
// * 也就是：【调用哪个版本的方法，取决于对象真正是什么类型，而不是变量声明的类型】。
// * 这个特点让我们可以用统一的方式（父类的变量）去处理各种不同的子类对象。
// *
// * 【多态的三个条件】
// * ① 有继承关系（Student、Teacher 继承 Person）
// * ② 子类重写了父类的方法（Student、Teacher 都重写了 introduce）
// * ③ 用【父类的变量】指向【子类的对象】（也叫"向上转型"）
// *
// * 【三部分内容】
// * 1. 向上转型：父类变量指向子类对象
// * 2. 多态数组：一个 Person[] 数组里同时放学生和老师，统一遍历
// * 3. 向下转型与 instanceof：想使用子类特有的方法，必须先转回子类类型，转之前要用 instanceof 检查
// *
// * 【关于一个文件里有多个类】
// * 一个 .java 文件里可以写多个类，但最多只有一个能写 public，而且 public 类的名字必须和文件名一致。
// * 这里 public 的是 PolymorphismDemo，所以文件必须叫 PolymorphismDemo.java；
// * Person、Student、Teacher 没有写 public，是这个文件内部用的"配角"。
// *
// * 【检查结果】
// * 这份代码没有错误，运行结果完全正确，所以【逻辑一行都没有改】，只补充了详细注释。
// * 只做了一处很小的文字整理：第 1 部分的标题 "1.向上转型" 的点后面少了一个空格，
// * 和第 2、3 部分的写法不一致，已统一成 "1. 向上转型"。
// *
// * 【重要的使用提示】
// * 这个文件里已经包含了 Person、Student、Teacher 三个类的完整定义。
// * 如果你的文件夹里还有别的文件也定义了 Person 或 Student（比如 InheritanceDemo.java 里也有），
// * 放在一起编译，Java 会报错 "duplicate class: Person"（类重复定义）。
// * 所以每个练习请放在各自独立的文件夹里。我已经按练习分好了文件夹。
// */
// public class PolymorphismDemo {

// // main 是程序的入口：运行程序时，Java 从这里的第一行开始，一行一行往下执行。
// public static void main(String[] args) {

// // ==================== 1. 向上转型 ====================
// // 【修复点】原来写的是 "1.向上转型"，点后面少一个空格，已统一。
// System.out.println("===== 1. 向上转型：父类引用指向子类对象 =====");

// // 等号左边：Person p1 —— 变量 p1 的类型是 Person（父类）
// // 等号右边：new Student(...) —— 真正创建出来的是一个 Student（子类）对象
// // 一个学生"也是"一个人，所以可以把学生对象交给"人"的变量保管，这叫"向上转型"，Java 会自动完成，不需要写转换符号。
// //
// // 这个 Student 构造方法只传了 2 个参数，调用的是"两参数"的版本，
// // 它会自动把专业设置成默认值"计算机科学与技术"（见下面 Student 类里的 this(...)）。
// Person p1 = new Student("张三", 20);

// // p1 的类型是 Person，但实际指向的是 Student 对象，
// // 所以执行的是【Student 重写后的】introduce()，而不是 Person 的。这就是多态。
// p1.introduce();

// // getClass() 能得到对象"真正的类型"，.getName() 得到它的名字。
// // 这里证明 p1 虽然是用 Person 变量保存的，但对象的真身是 Student。
// System.out.println("p1 指向的真实对象类型： " + p1.getClass().getName()); // Student
// System.out.println();

// // ==================== 2. 多态数组 ====================
// System.out.println("===== 2. 多态数组：Person[] 数组存放不同子类对象 =====");

// // 数组的类型是 Person[]，每个格子里都可以放"一个 Person，或者 Person 的任何子类对象"。
// // new Person[3] 只是准备了 3 个空格子（里面是 null），并没有创建任何人。
// Person[] people = new Person[3];
// people[0] = new Student("李四", 21); // 放进去一个学生
// people[1] = new Teacher("王五", 35); // 放进去一个老师
// people[2] = new Student("赵六", 19); // 再放一个学生

// // 增强 for 循环（for-each）：读作"对于数组 people 里的每一个 Person 类型的元素 p"，
// // 依次取出每个元素，放进变量 p，执行一遍花括号里的内容。不用自己管下标。
// // 循环里统一调用 p.introduce()，但每个对象执行的是各自真实类型的版本：
// // 学生 → 打印"[学生] ..."；老师 → 打印"[老师] ..."
// // 这就是多态的好处：同一段代码，自动适应不同的对象。
// for (Person p : people) {
// p.introduce();
// }
// System.out.println();

// // ==================== 3. 向下转型 与 instanceof ====================
// System.out.println("===== 3. 向下转型 与 instanceof =====");

// // 【为什么需要向下转型】
// // 用 Person 类型的变量，只能调用 Person 里有的方法。
// // 即使这个变量指向的真身是 Student，写 p1.study() 也会编译报错，
// // 因为编译器只看变量的类型（Person），而 Person 里没有 study()。
// // 想调用 Student 特有的方法，必须先把它"转回"Student 类型，这叫"向下转型"。
// //
// // 向下转型的写法：(Student) p1 —— 括号里写目标类型，叫"强制类型转换"，
// // 意思是"我确定这个对象真身是 Student，请当作 Student 用"。
// // 向上转型是自动的、一定安全的；向下转型必须手动写，而且有风险：
// // 如果对象的真身不是目标类型，运行时会抛出 ClassCastException 错误（见 CastErrorDemo.java）。
// //
// // 这里 p1 的真身确实是 Student，所以转换是安全的。
// // （说明：s1 在这个程序里后面没有再使用，它只是为了演示"安全的向下转型"。
// // 转换成功之后就可以写 s1.study() 了。）
// Student s1 = (Student) p1;

// // 遍历数组，对每个元素根据真实类型做不同的处理。
// for (Person p : people) {

// // instanceof 的意思是"是不是……的实例"：p instanceof Student 读作"p 指向的对象是不是 Student 类型"。
// // 是就返回 true，不是返回 false。向下转型之前先用它检查，就不会出错。
// if (p instanceof Student) {
// Student s = (Student) p; // 确认了是学生，转换是安全的
// s.study(); // 现在可以调用 Student 特有的 study() 了
// } else if (p instanceof Teacher) {
// Teacher t = (Teacher) p; // 确认了是老师，转换是安全的
// t.teach(); // 调用 Teacher 特有的 teach()
// } else {
// // 既不是学生也不是老师（比如一个普通的 Person），就不转换，只给出提示。
// // 说明：这个数组里放的都是学生或老师，所以这个分支在这次运行中不会被执行到，
// // 它是用来"兜底"的：万一数组里有别的类型，程序也不会出错。
// System.out.println(p.getName() + " 不是学生或老师，无法转换");
// }
// }
// }
// }

// // ============================================================
// // Person —— 父类：所有人的共同部分
// // ============================================================
// class Person {

// // private：私有。子类不能直接访问，只能通过下面的 getter 方法读取。
// private String name;
// private int age;

// // 构造方法：new Person(...) 时自动执行，名字和类名相同，没有返回值类型。
// // 参数和成员变量同名，用 this.name 表示"这个对象自己的成员变量"。
// public Person(String name, int age) {
// this.name = name;
// this.age = age;
// }

// public String getName() {
// return name;
// }

// public int getAge() {
// return age;
// }

// // 自我介绍。子类 Student 和 Teacher 都会重写它。
// public void introduce() {
// System.out.println("我叫" + name + "，今年" + age + "岁。");
// }
// }

// // ============================================================
// // Student —— 子类：学生
// // ============================================================
// class Student extends Person {

// private String major; // 专业（子类新增的属性）

// // 构造方法 1：只传姓名和年龄。
// // this(...) 的意思是"调用本类的另一个构造方法"。这里调用下面那个三个参数的版本，
// // 并把专业设置成默认值 "计算机科学与技术"。
// // 这样做的好处：初始化的代码只写在一个地方（下面那个构造方法），不用重复写。
// // 【规则】this(...) 和 super(...) 都只能写在构造方法的第一行，而且不能同时出现。
// public Student(String name, int age) {
// this(name, age, "计算机科学与技术");
// }

// // 构造方法 2：传姓名、年龄、专业。
// // 同一个类里有多个构造方法，只要参数不同就可以，这叫"构造方法的重载"。
// // super(name, age)：第一行先调用父类 Person 的构造方法，把 name 和 age 交给它处理。
// public Student(String name, int age, String major) {
// super(name, age);
// this.major = major;
// }

// // 重写父类的 introduce()：方法名和参数列表必须和父类完全一样。
// // @Override 是一个标记，意思是"我是在重写父类的方法"，写错了编译器会提醒。
// @Override
// public void introduce() {
// System.out.print("[学生] "); // print 不换行，下一句紧接着在同一行输出
// super.introduce(); // super.方法名()：调用【父类】的版本，先输出"我叫……，今年……岁。"
// System.out.println("我的专业是" + major + "。");
// }

// // Student 特有的方法：Person 里没有。只有转换成 Student 类型之后才能调用。
// public void study() {
// System.out.println(getName() + " 正在学习" + major + "。"); // 通过 getName() 取父类的
// name
// }
// }

// // ============================================================
// // Teacher —— 子类：老师
// // ============================================================
// class Teacher extends Person {

// private String subject; // 所教科目（子类新增的属性）

// // 构造方法 1：只传姓名和年龄，所教科目用默认值 "Java 程序设计"
// public Teacher(String name, int age) {
// this(name, age, "Java 程序设计");
// }

// // 构造方法 2：传姓名、年龄、所教科目
// public Teacher(String name, int age, String subject) {
// super(name, age);
// this.subject = subject;
// }

// @Override
// public void introduce() {
// System.out.print("[老师] ");
// super.introduce();
// System.out.println("我教的是" + subject + "。");
// }

// // Teacher 特有的方法
// public void teach() {
// System.out.println(getName() + " 正在讲授" + subject + "。");
// }
// }
