public class AbstractAndInterface {

    public static void main(String[] args) {

        System.out.println("===== 第一组：抽象类 Shape =====");

        Shape[] shapes = new Shape[2];
        shapes[0] = new Circle(2);
        shapes[1] = new Rectangle(3, 4);

        for (Shape shape : shapes) {
            shape.show();
        }
        System.out.println();

        System.out.println("===== 第二组：接口 Flyable / Swimmable =====");

        Flyable[] flyers = { new Bird("麻雀"), new Airplane("C919"), new Duck("唐老鸭") };

        for (Flyable f : flyers) {
            f.fly();
            f.land();

            if (f instanceof Swimmable) {
                ((Swimmable) f).swim();
            }
        }
    }
}

abstract class Shape {

    private String name;

    public Shape(String name) {
        this.name = name;
    }

    public abstract double area();

    public void show() {
        System.out.println(name + " 的面积 = " + area());
    }
}

class Circle extends Shape {

    private double radius;

    public Circle(double radius) {
        super("圆形");
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {

    private double width, height;

    public Rectangle(double width, double height) {
        super("矩形");
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

interface Flyable {

    void fly();

    default void land() {
        System.out.println("着陆");
    }
}

interface Swimmable {
    void swim();
}

class Bird implements Flyable {

    private String name;

    public Bird(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " 在飞翔");
    }
}

class Airplane implements Flyable {

    private String name;

    public Airplane(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " 靠发动机推力飞行");
    }

    @Override
    public void land() {
        System.out.println(name + " 在跑道上降落");
    }
}

class Duck implements Flyable, Swimmable {

    private String name;

    public Duck(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " 靠翅膀飞行");
    }

    @Override
    public void swim() {
        System.out.println(name + " 在水里游泳");
    }
}

// /**
// * AbstractAndInterface —— 演示"抽象类"和"接口"
// *
// * 【两组演示】
// * 第一组：抽象类 Shape（图形）。圆形和矩形都是图形，都能算面积，但"怎么算"各不相同。
// * 第二组：接口 Flyable（会飞的）和 Swimmable（会游泳的）。麻雀、飞机、鸭子都会飞，其中鸭子还会游泳。
// *
// * 【什么是抽象类（abstract class）】
// * 用 abstract 修饰的类。它表示一种"太笼统、不能直接造出实物"的概念：
// * 你可以说"这是一个图形"，但世界上不存在"一个纯粹的图形"，只有具体的圆形、矩形、三角形。
// * 抽象类的特点：
// * · 不能用 new 直接创建对象（new Shape("...") 会编译报错），只能创建它的子类对象
// * · 里面可以有"抽象方法"：只有方法头，没有方法体（连花括号都没有），写法是 public abstract double area();
// * 意思是"所有子类都必须自己实现这个方法，但具体怎么实现，我这里不管"
// * · 也可以有普通的方法、成员变量、构造方法（比如下面 Shape 里的 show()，子类直接继承使用）
// * 子类继承抽象类后，必须实现它所有的抽象方法，否则这个子类也必须被声明成抽象的。
// *
// * 【什么是接口（interface）】
// * 接口像是一份"能力清单 / 合同"：规定了"一个类如果想具备这种能力，必须提供哪些方法"，但不管怎么实现。
// * · 用 interface 定义，用 implements（"实现"）让一个类来履行这份合同
// * · 接口里的方法默认就是 public abstract，不用写
// * · 一个类只能继承一个父类（extends），但可以同时实现多个接口（implements A, B），
// * 所以接口常用来表示"这个类还额外会做什么"（比如鸭子：既会飞，又会游泳）
// * · 接口里可以用 default 写一个"默认实现"：实现类不重写就用这个，想改也可以重写
// *
// * 【抽象类和接口怎么选】
// * 抽象类表示"是什么"（圆形是一种图形），接口表示"能做什么"（鸭子会飞、会游泳）。
// *
// * 【本次修复的问题】
// * 1. 第一组的输出是错的（最重要的一条）：
// * main 里写的是 System.out.println(shape)，打印的是对象本身，
// * 因为 Circle、Rectangle 都没有重写 toString，Java 只能打印默认的
// * "类名@一串十六进制数字"，实际运行的输出是：
// * Circle@7852e922
// * Rectangle@4e25154f
// * 而 Shape 里明明已经写好了专门用来显示面积的 show() 方法，但从来没有被调用过。
// * 修复：改成调用 shape.show()，输出变成：圆形 的面积 = 12.566370614359172。
// * 2. 小问题：Bird 打印的是"麻雀在飞翔"，而 Airplane、Duck 的格式都是"名字 + 空格 + 动作"，
// * 只有这一句没有空格。已统一成"麻雀 在飞翔"。第二组标题末尾多余的两个空格也去掉了。
// * （抽象类、接口的定义和多态的逻辑都是正确的，没有改动。）
// *
// * 【关于一个文件里有多个类和接口】
// * 一个 .java 文件里可以写多个类和接口，但最多只有一个能写 public，而且 public 的名字必须和文件名一致。
// * 这里 public 的是 AbstractAndInterface，所以文件必须叫
// AbstractAndInterface.java；其余的都是内部使用的"配角"。
// */
// public class AbstractAndInterface {

// // main 是程序的入口：运行程序时，Java 从这里的第一行开始，一行一行往下执行。
// public static void main(String[] args) {

// // ==================== 第一组：抽象类 ====================
// System.out.println("===== 第一组：抽象类 Shape =====");

// // 数组的类型是 Shape[]：每个格子里可以放"一个 Shape 的子类对象"。
// // 注意：抽象类不能 new，但是可以用来声明变量和数组的类型。
// // new Shape[2] 只是准备了 2 个空格子，并没有创建任何图形。
// Shape[] shapes = new Shape[2];
// shapes[0] = new Circle(2); // 半径为 2 的圆。子类对象放进父类类型的数组，叫"向上转型"，自动完成
// shapes[1] = new Rectangle(3, 4); // 宽 3、高 4 的矩形

// // 增强 for 循环（for-each）：读作"对于数组 shapes 里的每一个 Shape 类型的元素 shape"，
// // 依次取出每个元素，放进变量 shape，然后执行一遍花括号里的内容。不用自己管下标。
// for (Shape shape : shapes) {
// // 【修复点】原来写的是 System.out.println(shape)，打印出来的是 Circle@7852e922 这种看不懂的东西。
// // 现在调用 shape.show()。
// // 这里体现了"多态"：show() 里调用的 area()，每个图形执行的是【自己真实类型】的版本：
// // shape 是圆形时，执行 Circle 的 area()（圆周率 × 半径 × 半径）
// // shape 是矩形时，执行 Rectangle 的 area()（宽 × 高）
// // 同一行代码，对不同的图形自动采用不同的算法。
// // 预期输出：
// // 圆形 的面积 = 12.566370614359172
// // 矩形 的面积 = 12.0
// shape.show();
// }
// System.out.println(); // 空一行，把两组演示分隔开

// // ==================== 第二组：接口 ====================
// // 【修复点】标题末尾原来有两个多余的空格，已去掉。
// System.out.println("===== 第二组：接口 Flyable / Swimmable =====");

// // 数组的类型是 Flyable[]：每个格子里可以放"任何实现了 Flyable 接口的类的对象"。
// // 麻雀、飞机、鸭子是三个毫无关系的类，但它们都实现了 Flyable，
// // 所以都可以放进同一个 Flyable 数组里，统一当作"会飞的东西"来处理。
// // 花括号 { ... } 是在创建数组的同时直接放入内容。
// Flyable[] flyers = { new Bird("麻雀"), new Airplane("C919"), new Duck("唐老鸭") };

// for (Flyable f : flyers) {
// f.fly(); // 每个对象执行自己的 fly()：麻雀、飞机、鸭子飞的方式各不相同
// f.land(); // 麻雀和鸭子没有重写 land()，用的是接口里的默认实现"着陆"；飞机重写了，用的是自己的版本

// // instanceof 的意思是"是不是……的实例"：f instanceof Swimmable 读作"f 指向的对象会不会游泳（是不是实现了
// Swimmable 接口）"。
// // 用 Flyable 类型的变量 f，只能调用 Flyable 里有的方法（fly、land）。
// // 想调用 Swimmable 里的 swim()，必须先确认这个对象真的会游泳，再把它"转换"成 Swimmable 类型。
// if (f instanceof Swimmable) {
// // (Swimmable) f：强制类型转换，意思是"我确定它会游泳，请当作 Swimmable 来用"。
// // 外面再套一层括号，是为了先完成转换，再调用 .swim()。
// // 转换前必须用 instanceof 检查；如果对象并不会游泳就强行转换，运行时会抛出 ClassCastException 错误。
// // 这里只有鸭子会走到这一行。
// ((Swimmable) f).swim();
// }
// }
// // 预期输出：
// // 麻雀 在飞翔
// // 着陆
// // C919 靠发动机推力飞行
// // C919 在跑道上降落
// // 唐老鸭 靠翅膀飞行
// // 着陆
// // 唐老鸭 在水里游泳
// }
// }

// // ============================================================
// // 抽象类 Shape —— "图形"
// // ============================================================
// abstract class Shape {

// // private：私有，只有 Shape 自己里面的代码能直接访问
// private String name; // 图形的名字，比如"圆形"、"矩形"

// // 构造方法：虽然抽象类不能直接 new，但它的构造方法会在创建子类对象时，被子类的 super(...) 调用，
// // 用来初始化"父类的部分"。
// public Shape(String name) {
// this.name = name;
// }

// // 抽象方法：只有方法头，没有方法体，以分号结尾。
// // 意思是"每一种图形都有面积，但算法各不相同，由各个子类自己实现"。
// // 抽象方法必须写在抽象类里。
// public abstract double area();

// // 普通方法：有完整的方法体，子类可以直接继承使用，不用重复写。
// // 它里面调用了 area()：此时 Java 会根据"对象的真实类型"，自动选择执行 Circle 的还是 Rectangle 的 area()。
// // 这是一种很常用的设计：父类写好固定的流程（显示"名字 的面积 = 结果"），其中变化的部分（怎么算面积）留给子类。
// public void show() {
// System.out.println(name + " 的面积 = " + area());
// }
// }

// // ============================================================
// // Circle —— 圆形：继承 Shape，并实现 area()
// // ============================================================
// class Circle extends Shape {

// private double radius; // 半径

// // 子类构造方法的第一行，必须先调用父类的构造方法：super(...)。
// // 这里把名字 "圆形" 交给父类 Shape 保存。
// public Circle(double radius) {
// super("圆形");
// this.radius = radius;
// }

// // 实现父类的抽象方法。@Override 是一个标记，意思是"我是在重写/实现父类的方法"，
// // 写错了方法名或参数，编译器会立刻提醒你。
// // Math.PI 是 Java 自带的圆周率常量（约 3.14159）。圆的面积 = 圆周率 × 半径 × 半径。
// @Override
// public double area() {
// return Math.PI * radius * radius;
// }
// }

// // ============================================================
// // Rectangle —— 矩形：继承 Shape，并实现 area()
// // ============================================================
// class Rectangle extends Shape {

// // 同一种类型的两个变量可以写在一行，用逗号隔开，等价于分开写两行
// private double width, height; // 宽、高

// public Rectangle(double width, double height) {
// super("矩形");
// this.width = width;
// this.height = height;
// }

// // 矩形的面积 = 宽 × 高
// @Override
// public double area() {
// return width * height;
// }
// }

// // ============================================================
// // 接口 Flyable —— "会飞的"
// // ============================================================
// interface Flyable {

// // 接口里的方法默认就是 public abstract（公开的、抽象的），所以这里不用写这两个词。
// // 意思是：想要"会飞"，必须提供一个 fly() 方法。
// void fly();

// // default 方法：在接口里提供一个"默认实现"。
// // 实现这个接口的类，如果不重写它，就直接使用这个默认版本；如果想用自己的方式，也可以重写。
// // 这样不用让每个类都重复写一遍"着陆"。
// default void land() {
// System.out.println("着陆");
// }
// }

// // ============================================================
// // 接口 Swimmable —— "会游泳的"
// // ============================================================
// interface Swimmable {
// void swim(); // 想要"会游泳"，必须提供一个 swim() 方法
// }

// // ============================================================
// // Bird —— 鸟：实现 Flyable 接口
// //
// // implements Flyable 表示 Bird "履行" Flyable 这份合同：必须实现里面所有的抽象方法（这里是 fly()）。
// // land() 是 default 方法，可以不实现，不实现就用接口里的默认版本。
// // ============================================================
// class Bird implements Flyable {

// private String name;

// public Bird(String name) {
// this.name = name;
// }

// @Override
// public void fly() {
// // 【修复点】原来写的是 name + "在飞翔"，没有空格，和另外两个类的格式不一致。已改成加一个空格。
// System.out.println(name + " 在飞翔");
// }
// }

// // ============================================================
// // Airplane —— 飞机：实现 Flyable 接口，并且重写了默认的 land()
// // ============================================================
// class Airplane implements Flyable {

// private String name;

// public Airplane(String name) {
// this.name = name;
// }

// @Override
// public void fly() {
// System.out.println(name + " 靠发动机推力飞行");
// }

// // 重写接口里的 default 方法：飞机不用默认的"着陆"，要用自己的版本。
// @Override
// public void land() {
// System.out.println(name + " 在跑道上降落");
// }
// }

// // ============================================================
// // Duck —— 鸭子：同时实现两个接口 Flyable 和 Swimmable
// //
// // 一个类只能继承一个父类，但可以同时实现多个接口，用逗号隔开。
// // 这样鸭子既"会飞"，又"会游泳"，必须实现两个接口里所有的抽象方法：fly() 和 swim()。
// // 鸭子没有重写 land()，所以降落时用的是接口里的默认版本"着陆"。
// // ============================================================
// class Duck implements Flyable, Swimmable {

// private String name;

// public Duck(String name) {
// this.name = name;
// }

// @Override
// public void fly() {
// System.out.println(name + " 靠翅膀飞行");
// }

// @Override
// public void swim() {
// System.out.println(name + " 在水里游泳");
// }
// }
