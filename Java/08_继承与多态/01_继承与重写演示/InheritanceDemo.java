public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("========== 1. 创建对象：观察构造方法和执行顺序 ==========");

        Student s = new Student("张三", 18, "20260125", 92.5);

        Person p = new Person("王五", 40);
        System.out.println();

        System.out.println("========== 2. 继承：Student 里没写这些方法却可以直接调用 ==========");

        System.out.println("getName() = " + s.getName());

        s.sayHello();
        s.sayHello("李老师");
        System.out.println();

        System.out.println("========== 3. 重写：同名 introduce()，Student 与 Person 的内容不同 ==========");

        System.out.println("---------- Student 对象调用 introduce() ----------");
        s.introduce();
        System.out.println("---------- Person 对象调用 introduce() ----------");
        p.introduce();
        System.out.println();

        System.out.println("========== 4. toString：println(对象) 会自动调用 toString() ==========");

        System.out.println(s);
        System.out.println(p);
        System.out.println();

        System.out.println("========== 5. Object 是所有类的父类 ==========");

        System.out.println("Student 的父类: " + Student.class.getSuperclass().getName());

        System.out.println("Person 的父类: " + Person.class.getSuperclass().getName());

        System.out.println("s.equals(s) = " + s.equals(s));
        System.out.println("s.equals(p) = " + s.equals(p));
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Person 构造方法执行：name=" + name + ", age=" + age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void sayHello() {
        System.out.println("大家好，我是 " + name + "，今年 " + age + " 岁。");
    }

    public void sayHello(String to) {
        System.out.println(name + " 对 " + to + " 说：您好！");
    }

    public void introduce() {
        System.out.println("我是 Person：" + name + "，" + age + " 岁。");
    }

    @Override
    public String toString() {
        return "[Person] name=" + name + ", age=" + age;
    }
}

class Student extends Person {
    private String studentId;
    private double score;

    public Student(String name, int age, String studentId, double score) {
        super(name, age);
        this.studentId = studentId;
        this.score = score;
        System.out.println("Student 构造方法执行：studentId=" + studentId + ", score=" + score);
    }

    public String getStudentId() {
        return studentId;
    }

    public double getScore() {
        return score;
    }

    @Override
    public void introduce() {
        System.out.println("我是 Student：" + getName() + "，" + getAge()
                + " 岁，学号 " + studentId + "，成绩 " + score + "。");
    }

    @Override
    public String toString() {
        return "[Student] name=" + getName() + ", age=" + getAge()
                + ", studentId=" + studentId + ", score=" + score;
    }
}
// /**
// * InheritanceDemo —— 演示"继承"：构造方法的执行顺序、方法的继承、重写、toString、Object 类
// *
// * 【什么是继承】
// * 一个类（子类）可以用 extends 关键字"继承"另一个类（父类），
// * 这样子类就自动拥有父类里的属性和方法，不用重复写。
// * 比如"学生"是一种"人"，所以 Student 可以继承 Person：
// * Person 里有的 getName()、sayHello() 等，Student 里一行都不用写，就能直接使用；
// * Student 还可以增加自己特有的东西（学号、成绩），或者把父类的某个方法"重写"成自己的版本。
// *
// * 【这个程序演示的五件事】
// * 1. 创建对象时，构造方法的执行顺序：先执行父类的，再执行子类的
// * 2. 子类没写、但可以直接调用父类的方法（继承）
// * 3. 重写：子类写一个和父类同名同参数的方法，覆盖掉父类的版本
// * 4. toString：直接打印对象时，会自动调用 toString()
// * 5. Object 是所有类的"祖先"
// *
// * 【关于一个文件里有多个类】
// * 一个 .java 文件里可以写多个类，但最多只有一个能写 public，而且 public 类的名字必须和文件名一致。
// * 这里 public 的是 InheritanceDemo，所以文件必须叫 InheritanceDemo.java；
// * Person 和 Student 没有写 public，是这个文件内部用的"配角"。
// *
// * 【本次修复的问题】
// * 1. toString 末尾偷偷带了换行符（设计上的问题）：
// * 原来的两个 toString 方法最后都拼接了 System.lineSeparator()（"换行符"），
// * 这样 main 里用 print（不换行）打印时，输出才会各占一行。
// * 但这样做不规范：toString 的职责只是"返回对象的文字描述"，不应该自作主张加换行。
// * 比如 "学生信息：" + s 这样拼接时，后面就会多出一个意料之外的换行。
// * 修复：去掉 toString 末尾的换行符；main 里改用 println（打印后换行），输出的效果完全一样。
// * 这也和第 4 部分标题里写的"println(对象)"对上了。
// * 2. 第 4 部分的标题 "to String" 写成了两个单词，方法名是 toString（连在一起）。已改正。
// * 3. 重要的使用提示：这个文件里已经包含了 Person 和 Student 两个类的完整定义。
// * 如果你的文件夹里还有单独的 Person.java、Student.java，把它们和这个文件放在一起编译，
// * Java 会报错 "duplicate class: Person"（类重复定义）。
// * 所以每个练习请放在各自独立的文件夹里。我已经按练习分好了文件夹。
// * （继承、构造方法顺序、重写等其他逻辑都是正确的，没有改动。）
// */
// public class InheritanceDemo {

// // main 是程序的入口：运行程序时，Java 从这里的第一行开始，一行一行往下执行。
// public static void main(String[] args) {

// // ==================== 1. 创建对象：构造方法的执行顺序 ====================
// System.out.println("========== 1. 创建对象：观察构造方法和执行顺序 ==========");

// // new Student(...) 创建子类对象时，执行顺序是：
// // ① 进入 Student 的构造方法
// // ② 它的第一行 super(name, age) 先去调用【父类】Person 的构造方法 → 打印"Person 构造方法执行"
// // ③ 父类的构造方法执行完，才回来继续执行 Student 构造方法的其余部分 → 打印"Student 构造方法执行"
// // 所以输出的顺序是：先 Person，后 Student。
// // 原因：子类对象里包含了一个"父类的部分"，必须先把父类的部分准备好，才能在它的基础上准备子类自己的部分。
// Student s = new Student("张三", 18, "20260125", 92.5);

// // 创建一个普通的 Person 对象，只会执行 Person 的构造方法
// Person p = new Person("王五", 40);
// System.out.println(); // 空一行，把各部分分隔开

// // ==================== 2. 继承：直接调用父类的方法 ====================
// System.out.println("========== 2. 继承：Student 里没写这些方法却可以直接调用 ==========");

// // Student 类里并没有写 getName()、sayHello()，但它继承了 Person，所以 s 可以直接调用。
// System.out.println("getName() = " + s.getName());

// // sayHello 有两个版本（参数不同），这叫"重载"：同一个类里，方法名相同、参数不同。
// // 调用时 Java 会根据传入的参数自动选择：没有参数 → 第一个版本；传一个文字 → 第二个版本。
// s.sayHello();
// s.sayHello("李老师");
// System.out.println();

// // ==================== 3. 重写：子类覆盖父类的方法 ====================
// System.out.println("========== 3. 重写：同名 introduce()，Student 与 Person 的内容不同
// ==========");

// // Student 重写了 introduce()，所以 s.introduce() 执行的是 Student 里的版本；
// // p 是普通的 Person，p.introduce() 执行的是 Person 里的版本。
// // 调用的方法名一样，具体执行哪个版本，取决于这个对象【真正是什么类型】。
// System.out.println("---------- Student 对象调用 introduce() ----------");
// s.introduce();
// System.out.println("---------- Person 对象调用 introduce() ----------");
// p.introduce();
// System.out.println();

// // ==================== 4. toString ====================
// // 【修复点】标题里原来写的是 "to String"，方法名是 toString，已改正；
// // 并且 toString 末尾不再带换行符，所以这里改用 println 打印。
// System.out.println("========== 4. toString：println(对象) 会自动调用 toString()
// ==========");

// // println(对象) 会自动调用这个对象的 toString() 方法，把返回的文字打印出来。
// // s 的 toString 是 Student 里重写的版本，p 的是 Person 里的版本。
// System.out.println(s);
// System.out.println(p);
// System.out.println();

// // ==================== 5. Object 是所有类的父类 ====================
// System.out.println("========== 5. Object 是所有类的父类 ==========");

// // Student.class 表示"Student 这个类本身"（一个描述类的信息对象）。
// // .getSuperclass() 获取它的父类，.getName() 获取父类的名字（文字）。
// System.out.println("Student 的父类: " +
// Student.class.getSuperclass().getName()); // 预期：Person

// // Person 没有写 extends，Java 会自动让它继承 java.lang.Object。
// // Object 是所有类的"祖先"，所以 Person 的父类是 java.lang.Object。
// System.out.println("Person 的父类: " + Person.class.getSuperclass().getName());
// // 预期：java.lang.Object

// // equals 是 Object 提供的方法，所有类都自带。Student 和 Person 都没有重写它，
// // 所以用的是 Object 默认的 equals，效果和 == 一样：只有"同一个对象"才相等。
// System.out.println("s.equals(s) = " + s.equals(s)); // true：自己和自己是同一个对象
// System.out.println("s.equals(p) = " + s.equals(p)); // false：是两个不同的对象
// }
// }

// /**
// * 父类：被 Student 继承
// */
// class Person {

// // private：私有，只有 Person 这个类自己里面的代码能直接访问。
// // 重要：子类 Student 虽然继承了 Person，但【不能直接访问】父类的 private 成员，
// // 只能通过父类提供的公开方法（比如 getName()、getAge()）去读取。
// private String name;
// private int age;

// // 构造方法：new Person(...) 时自动执行。名字必须和类名相同，没有返回值类型。
// // 参数 name、age 和成员变量同名，在方法里写 name 指的是参数；
// // 要表示"这个对象自己的成员变量"，必须写 this.name（this 代表"当前这个对象自己"）。
// public Person(String name, int age) {
// this.name = name;
// this.age = age;
// System.out.println("Person 构造方法执行：name=" + name + ", age=" + age);
// }

// // getter 方法：因为成员变量是 private，所以提供公开的方法让外面读取
// public String getName() {
// return name;
// }

// public int getAge() {
// return age;
// }

// // 方法 sayHello（无参数版本）
// public void sayHello() {
// System.out.println("大家好，我是 " + name + "，今年 " + age + " 岁。");
// }

// // 方法 sayHello（有一个参数的版本）：和上面同名，但参数不同，构成"重载"
// public void sayHello(String to) {
// System.out.println(name + " 对 " + to + " 说：您好！");
// }

// // 方法 introduce：自我介绍。子类 Student 会"重写"它。
// public void introduce() {
// System.out.println("我是 Person：" + name + "，" + age + " 岁。");
// }

// // 重写 toString。所有类都继承自 Object，Object 自带一个 toString，
// // 但它返回的是 "类名@一串数字"，看不出对象里存了什么，所以我们自己写一个更有用的版本。
// // @Override 是一个标记，意思是"我这个方法是在重写父类的方法"。
// // 如果方法名或参数写错了，编译器会立刻报错提醒你，而不是悄悄变成一个新方法。
// //
// // 【修复点】原来这里最后还拼接了 System.lineSeparator()（换行符），已去掉。
// // toString 只负责返回对象的文字描述，换行由调用它的地方（println）负责。
// @Override
// public String toString() {
// return "[Person] name=" + name + ", age=" + age;
// }
// }

// /**
// * 子类：继承 Person，重写 introduce() 和 toString()
// */
// class Student extends Person {

// // 子类自己新增的属性（父类里没有的）
// private String studentId; // 学号
// private double score; // 成绩

// // 子类的构造方法。
// // 【规则】子类构造方法的第一行，必须先调用父类的构造方法（用 super(...)），
// // 因为要先把"父类的部分"初始化好。这里 super(name, age) 把 name 和 age 交给父类 Person 的构造方法处理。
// // 如果子类的构造方法里没有写 super(...)，Java 会自动在第一行加上 super()（调用父类的无参构造方法）；
// // 如果父类没有无参构造方法，就会编译报错，所以这时必须手动写 super(...)。
// public Student(String name, int age, String studentId, double score) {
// super(name, age); // 调用父类 Person 的构造方法（必须是第一行）
// this.studentId = studentId;
// this.score = score;
// System.out.println("Student 构造方法执行：studentId=" + studentId + ", score=" +
// score);
// }

// public String getStudentId() {
// return studentId;
// }

// public double getScore() {
// return score;
// }

// // 重写父类的 introduce()：方法名、参数列表必须和父类完全一样，内容可以不同。
// // 因为 name 和 age 是父类的 private 成员，子类不能直接用，所以要通过 getName()、getAge() 去取。
// @Override
// public void introduce() {
// System.out.println("我是 Student：" + getName() + "，" + getAge()
// + " 岁，学号 " + studentId + "，成绩 " + score + "。");
// }

// // 重写 toString。同样通过 getName()、getAge() 取得父类的数据。
// // 【修复点】原来末尾也拼接了换行符 System.lineSeparator()，已去掉。
// @Override
// public String toString() {
// return "[Student] name=" + getName() + ", age=" + getAge()
// + ", studentId=" + studentId + ", score=" + score;
// }
// }
