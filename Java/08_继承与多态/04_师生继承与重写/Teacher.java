public class Teacher extends Person {
    private String subject;

    public Teacher(String name, String subject) {
        super(name);
        this.subject = subject;
    }

    @Override
    public void introduce() {
        System.out.println("My name is " + getName() + " and I teach " + subject);
    }

    public void teach() {
        System.out.println("I am teaching " + subject);
    }
}
// /**
// * Teacher —— "老师"类（子类）：继承 Person，增加所教科目
// *
// * 【关于文件名】
// * 这个类前面写了 public，所以文件名必须和类名完全一致，必须叫 Teacher.java（区分大小写）。
// *
// * 【本次修复的问题】（这个文件有编译错误，原来根本编译不通过）
// * 原来的构造方法是这样写的：
// * public Teacher(String name, String subject) {
// * super();
// * setName(name);
// * this.subject = subject;
// * }
// * 这里有两个错误：
// * 1. super() 是调用父类的"无参构造方法"，但 Person 里只写了一个需要传入姓名的构造方法 Person(String name)，
// * 没有无参的。找不到匹配的，编译报错：
// * constructor Person in class Person cannot be applied to given types
// * 2. setName(name) 调用的是父类的 setName 方法，但 Person 里根本没有这个方法，编译报错：
// * cannot find symbol: method setName(String)
// * 而且 Person 里的 name 是 private 的，子类也不能直接去赋值。
// * 修复：改成 super(name)，在调用父类构造方法的同时，把姓名交给它去保存。
// * 这也和同组的 Student 类的写法完全一致。
// * （你也可以反过来给 Person 加一个无参构造方法和 setName 方法来配合原来的写法，
// * 但那样会让对象"先创建出来、再补姓名"，没有姓名的 Person 就能存在了；
// * 让姓名在创建时就必须提供，是更稳妥的设计。）
// *
// * 【运行前提】
// * 这个类继承了 Person，必须和 Person.java 放在同一个文件夹里一起编译：
// * javac Person.java Teacher.java
// * 它没有 main 方法，不能单独运行。
// */
// public class Teacher extends Person {

// // 子类自己新增的属性（父类里没有的）。private：私有，只有 Teacher 自己里面的代码能访问。
// private String subject; // 所教科目

// // ============================================================
// // 子类的构造方法
// //
// // 【规则】子类构造方法的第一行，必须先调用父类的构造方法，写法是 super(...)。
// // 因为子类对象里包含了一个"父类的部分"（name），必须先把它准备好。
// // 【修复点】原来写的是 super(); 然后 setName(name);，现在改成一句 super(name);
// // ============================================================
// public Teacher(String name, String subject) {
// super(name); // 调用父类 Person 的构造方法，把姓名交给它保存（必须是第一行）
// this.subject = subject; // this.subject 是成员变量，subject 是参数，用 this 区分
// }

// // ============================================================
// // 重写父类的 introduce()
// //
// // 重写：子类写一个和父类【方法名、参数列表完全相同】的方法，覆盖掉父类的版本。
// // @Override 是一个标记，意思是"我是在重写父类的方法"，写错了编译器会提醒。
// // name 是父类的 private 成员，要通过父类公开的 getName() 来取（Person.java 里已把方法名修正为 getName）。
// // ============================================================
// @Override
// public void introduce() {
// System.out.println("My name is " + getName() + " and I teach " + subject);
// }

// // Teacher 特有的方法：Person 里没有。
// // 只有把对象当作 Teacher 来用（或者向下转型成 Teacher）之后，才能调用它。
// public void teach() {
// System.out.println("I am teaching " + subject);
// }
// }
