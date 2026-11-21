class Student extends Person {
    private String studentId;

    public Student(String name, String studentId) {
        super(name);
        this.studentId = studentId;
    }

    @Override
    public void introduce() {
        System.out.println("My name is " + getName() + " and my student ID is " + studentId);
    }

    public void study() {
        System.out.println("I am studying");
    }
}
// /**
// * Student —— "学生"类（子类）：继承 Person，增加学号
// *
// * 【什么是继承】
// * class Student extends Person 表示 Student "继承"了 Person：
// * Person 里有的 getName() 等方法，Student 里不用重新写，就能直接使用。
// * Student 还可以增加自己特有的属性（学号 studentId）和方法（study），
// * 或者把父类的方法"重写"成自己的版本（introduce）。
// *
// * 【关于文件名】
// * 你上传时这个文件叫 Student_2.java（文件名后面的 _2 是上传时为了区分同名文件自动加上的）。
// * 这里的类没有写 public，所以 Java 不要求文件名和类名一致；
// * 规范的做法是文件名和类名保持一致，所以保存为 Student.java。
// *
// * 【检查结果】
// * 这个文件本身的代码没有错误，所以【代码一行都没有改】，只补充了详细注释。
// * 它原来之所以编译不通过，是因为 Person 里的方法名写成了 getname()（小写 n），
// * 而这里调用的是 getName()（大写 N），找不到这个方法。问题在 Person.java，已经在那边修复了。
// *
// * 【运行前提】
// * 这个类继承了 Person，必须和 Person.java 放在同一个文件夹里一起编译：
// * javac Person.java Student.java
// * 它没有 main 方法，不能单独运行。
// */
// class Student extends Person {

// // 子类自己新增的属性（父类里没有的）。private：私有，只有 Student 自己里面的代码能访问。
// private String studentId; // 学号

// // ============================================================
// // 子类的构造方法
// //
// // 【规则】子类构造方法的第一行，必须先调用父类的构造方法，写法是 super(...)。
// // 原因：子类对象里包含了一个"父类的部分"（name），必须先把它准备好，才能在它的基础上准备子类自己的部分。
// // 这里 super(name) 把姓名交给父类 Person 的构造方法去处理（Person 只有一个需要传入姓名的构造方法）。
// // 如果没有写 super(...)，Java 会自动补上 super()（调用父类的无参构造方法），
// // 而 Person 没有无参的构造方法，就会编译报错，所以这里必须手动写 super(name)。
// // ============================================================
// public Student(String name, String studentId) {
// super(name); // 调用父类 Person 的构造方法（必须是第一行）
// this.studentId = studentId; // this.studentId 是成员变量，studentId 是参数，用 this 区分
// }

// // ============================================================
// // 重写父类的 introduce()
// //
// // 重写：子类写一个和父类【方法名、参数列表完全相同】的方法，覆盖掉父类的版本。
// // @Override 是一个标记，意思是"我是在重写父类的方法"，写错了方法名或参数，编译器会立刻提醒你。
// //
// // name 是父类的 private 成员，子类不能直接用，所以通过父类公开的 getName() 来取。
// // 之后通过 Student 类型（或者 Person 类型的变量指向 Student 对象）调用 introduce()，
// // 执行的都是这里的版本，而不是 Person 的。这就是"多态"。
// // ============================================================
// @Override
// public void introduce() {
// System.out.println("My name is " + getName() + " and my student ID is " +
// studentId);
// }

// // Student 特有的方法：Person 里没有。
// // 只有把对象当作 Student 来用（或者向下转型成 Student）之后，才能调用它。
// public void study() {
// System.out.println("I am studying");
// }
// }
