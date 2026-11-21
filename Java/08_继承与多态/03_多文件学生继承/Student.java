public class Student extends Person {
    private String studentId;
    private double score;

    public Student(String name, int age, String studentId, double score) {
        super(name, age);
        this.studentId = studentId;
        this.score = score;

        System.out.println(" [Student 构造方法] 正在创建 Student，studentId = " + studentId);
    }

    @Override
    public void introduce() {
        super.introduce();
        System.out.println("我的学号是：" + studentId + "，我的成绩是：" + score);
    }

    @Override
    public String toString() {
        return "Student{name='" + getName() + "', age=" + getAge() + ", studentId='" + studentId + "', score=" + score
                + "}";
    }
}
// /**
// * Person —— "人"类（父类）：有姓名和年龄，会自我介绍、打招呼
// *
// * 【这是什么文件】
// * 这个文件和同文件夹里的 Student.java 是一对：Person 是父类，Student 是继承它的子类。
// * 它们都只是"类的定义"，没有 main 方法，不能单独运行，需要另一个带 main 方法的文件来创建对象并使用它们。
// *
// * 【关于文件名】
// * 这个类前面写了 public，所以文件名必须和类名完全一致，必须叫 Person.java（区分大小写）。
// *
// * 【本次修复的问题】
// * sayHello(String friendName) 打印的英文句子是错乱的：
// * 原来拼出来的是 "Tom says hello to Ann say : hello !"
// * （"says hello to" 和后面的 "say : hello !" 重复说了两遍 hello，语法也不通）。
// * 现在改成通顺的 "Tom says hello to Ann!"。
// * （其余代码没有问题，没有改动。）
// *
// * 【使用提示】
// * 这个文件夹里的 Person、Student，和 InheritanceDemo.java / PolymorphismDemo.java 里自带的
// Person、Student
// * 是不同的版本。把它们放在同一个文件夹里编译，会报 "duplicate class: Person"（类重复定义）。
// * 所以每一组文件请放在各自独立的文件夹里，我已经按练习分好了。
// */
// public class Person {

// // private：私有，只有 Person 这个类自己里面的代码能直接访问。
// // 子类 Student 虽然继承了 Person，也不能直接访问这两个成员变量，只能通过下面的 getter 方法读取。
// // 这叫"封装"：把数据藏起来，外界只能通过公开的方法来读写。
// private String name; // 姓名
// private int age; // 年龄

// // ============================================================
// // 构造方法：new Person(...) 时自动执行，负责给新对象设置初始值
// // · 名字必须和类名完全相同 · 没有返回值类型（连 void 都不写）
// //
// // 参数 name、age 和成员变量同名。在方法里只写 name，Java 认为指的是离得最近的"参数 name"；
// // 要表示"这个对象自己的成员变量 name"，必须写 this.name（this 代表"当前这个对象自己"）。
// //
// // 最后一句打印，是为了让你在运行时"看到"构造方法被执行了。
// // 因为子类对象创建时，会先执行父类的构造方法，再执行子类的，
// // 所以创建 Student 时，你会先看到这句 [Person 构造方法]，然后才是 [Student 构造方法]。
// // ============================================================
// public Person(String name, int age) {
// this.name = name;
// this.age = age;
// System.out.println(" [Person 构造方法] 正在创建 Person, name = " + name);
// }

// // getter 方法：对外提供"读取"私有成员变量的入口
// public String getName() {
// return name;
// }

// public int getAge() {
// return age;
// }

// // 自我介绍。子类 Student 重写了这个方法（并且在里面用 super.introduce() 调用了这个版本）。
// public void introduce() {
// System.out.println("Hello, my name is " + name + ", I am " + age + " years
// old.");
// }

// // ---------- 两个同名的 sayHello：方法的"重载" ----------
// // 重载：在同一个类里，写多个名字相同、但参数不同（个数或类型不同）的方法。
// // 调用时 Java 会根据传入的参数自动选择：不传参数 → 第一个版本；传一个文字 → 第二个版本。

// // 版本 1：没有参数
// public void sayHello() {
// System.out.println("Hello, my name is " + name);
// }

// // 版本 2：有一个参数，打招呼的对象
// // 【修复点】原来拼接的是 name + " says hello to " + friendName + " say : hello !"，
// // 句子重复而且不通顺。现在改成通顺的 "某某 says hello to 某某!"。
// public void sayHello(String friendName) {
// System.out.println(name + " says hello to " + friendName + "!");
// }

// // 重写 toString：规定这个对象"转成文字"时的样子。
// // System.out.println(person) 和 "文字" + person 都会自动调用 toString()。
// // 不重写的话，打印出来是类似 Person@15db9742 这样看不懂的东西。
// // @Override 是一个标记，意思是"我是在重写父类（Object）里的方法"，写错了方法名或参数，编译器会提醒你。
// @Override
// public String toString() {
// return "Person [name=" + name + ", age=" + age + "]";
// }
// }
