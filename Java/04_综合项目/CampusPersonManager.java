import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class CampusPersonManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Person> persons = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("===== 校园人员管理系统 （带异常校验）=====");
        try {
            runMenu();
        } catch (NoSuchElementException e) {
            System.out.println("输入结束，程序退出。");
        } finally {
            scanner.close();
            System.out.println("程序结束");
        }
    }
    private static void runMenu() {
        while (true) {
            System.out.println("\n1.录入 2.按姓名查询 3.按年龄冒泡排序 4.显示全部 0.退出");
            System.out.print("请选择: ");
            String line = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("输入有误：请输入菜单里有的数字！你输入的是“" + line + "”");
                continue;
            }
            try {
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
                        System.out.println("退出程序。");
                        return;
                    default:
                        System.out.println("无效选择，请重新输入。");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("输入错误：" + e.getMessage());
            }
        }
    }
    private static void addPerson() {
        System.out.print("请输入姓名: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("姓名不能为空！");
        }
        System.out.print("请输入年龄: ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("年龄必须是整数！");
        }
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("年龄必须在 0 到 150 之间！");
        }
        persons.add(new Person(name, age));
        System.out.println("录入成功：" + name + "（" + age + "岁）");
    }
    private static void findByName() {
        if (persons.isEmpty()) {
            System.out.println("当前没有任何人员信息。");
            return;
        }
        System.out.print("请输入要查询的姓名: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("姓名不能为空！");
        }
        boolean found = false;
        for (Person p : persons) {
            if (p.getName().equals(name)) {
                System.out.println("找到：" + p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到姓名为“" + name + "”的人员。");
        }
    }
    private static void sortByAge() {
        if (persons.isEmpty()) {
            System.out.println("当前没有任何人员信息。");
            return;
        }
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = 0; j < persons.size() - 1 - i; j++) {
                if (persons.get(j).getAge() > persons.get(j + 1).getAge()) {
                    Person temp = persons.get(j);
                    persons.set(j, persons.get(j + 1));
                    persons.set(j + 1, temp);
                }
            }
        }
        System.out.println("已按年龄从小到大排序。");
        showAll();
    }
    private static void showAll() {
        if (persons.isEmpty()) {
            System.out.println("当前没有任何人员信息。");
            return;
        }
        System.out.println("----- 人员列表（共 " + persons.size() + " 人）-----");
        for (int i = 0; i < persons.size(); i++) {
            System.out.println((i + 1) + ". " + persons.get(i));
        }
    }
    private static class Person {
        private final String name;
        private final int age;
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        String getName() {
            return name;
        }
        int getAge() {
            return age;
        }
        @Override
        public String toString() {
            return "姓名：" + name + "，年龄：" + age;
        }
    }
}

// // import 导入 java.util.ArrayList，即ArrayList 动态数组列表实现，可增删和按下标访问。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.ArrayList;
// // import 导入 java.util.List，即List 有序列表接口，规定集合的基本操作。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.List;
// // import 导入 java.util.NoSuchElementException，即NoSuchElementException 无可用元素时抛出的异常类。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.NoSuchElementException;
// // import 导入 java.util.Scanner，即Scanner 输入扫描器，用于从标准输入读行。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.Scanner;
// // public class 定义公开类 CampusPersonManager；类是组织字段与方法的容器。
// // 公开类名必须与 .java 文件名一致；左大括号开启类体。
// public class CampusPersonManager {
// // private static final 表示类内共享且引用不可重新赋值的字段；Scanner(System.in) 从标准输入读行。
// // 整个菜单共用一个扫描器，避免多个扫描器竞争输入。
// private static final Scanner scanner = new Scanner(System.in);
// // List<Person> 是只能保存 Person 引用的列表接口类型；ArrayList<> 创建可动态扩容的实现对象。
// // final 只限制引用不能重新指向其他列表，不妨碍添加、排序元素。
// private static final List<Person> persons = new ArrayList<>();
// // main 是 Java 程序的启动入口；public 允许 JVM 调用，static 表示无需创建类实例，void 表示不返回值。
// // String[] args 是命令行参数字符串数组，左大括号开始方法体。
// public static void main(String[] args) {
// // 输出系统名称，说明是带输入异常校验的管理系统。
// System.out.println("===== 校园人员管理系统 （带异常校验）=====");
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // 调用 runMenu 进入反复显示菜单的循环，直到退出或输入结束。
// runMenu();
// // 右大括号结束 try 块；catch 捕获 NoSuchElementException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (NoSuchElementException e) {
// // 当 nextLine 因输入结束抛 NoSuchElementException 时，打印退出提示，不显示异常栈。
// System.out.println("输入结束，程序退出。");
// // 结束前一个处理块并开启 finally 清理块；常规 JVM 控制流中无论正常、异常或 return 离开 try/catch，都执行此块。
// } finally {
// // finally 在正常退出或异常处理后关闭唯一的 Scanner；避免资源继续被占用。
// scanner.close();
// // 输出程序结束，标记清理阶段已执行。
// System.out.println("程序结束");
// // 右大括号结束「finally」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「public static void main(String[] args)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // private static void 声明类内静态菜单方法，无参数无返回值；由 main 调用。
// private static void runMenu() {
// // while(true) 的条件永远为真，菜单会持续运行，直到 return 或异常离开方法。
// while (true) {
// // 输出换行及五种菜单选项；编号与下面 switch 分支对应。
// System.out.println("\n1.录入 2.按姓名查询 3.按年龄冒泡排序 4.显示全部 0.退出");
// // print 不自动换行，使用户可以在提示后输入。
// System.out.print("请选择: ");
// // nextLine 读取一整行，不包含行末换行符；EOF 时抛异常，由 main 捕获。
// String line = scanner.nextLine();
// // 声明 int 类型的 choice，暂未赋值；只有成功解析的路径才会使用它。
// int choice;
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // trim 去除输入两端普通空白，parseInt 将文本转换为整数菜单编号；非法或溢出时抛异常。
// choice = Integer.parseInt(line.trim());
// // 右大括号结束 try 块；catch 捕获 NumberFormatException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (NumberFormatException e) {
// // 捕获非法整数输入后把原文本拼接进错误提示，方便用户定位问题。
// System.out.println("输入有误：请输入菜单里有的数字！你输入的是“" + line + "”");
// // continue 结束本轮 while，重新显示菜单；不会使用没有成功赋值的 choice。
// continue;
// // 右大括号结束「catch (NumberFormatException e)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // switch 根据 choice 的值选择操作，不会依次执行全部分支。
// switch (choice) {
// // case 1 对应录入人员的菜单编号。
// case 1:
// // 调用 addPerson 方法，负责读取和验证姓名、年龄，再添加对象。
// addPerson();
// // break 退出 switch，不退出外层 while；下轮仍显示菜单。
// break;
// // case 2 对应按姓名查询。
// case 2:
// // 调用 findByName 遍历列表并输出姓名匹配的人员。
// findByName();
// // break 阻止贯穿到排序分支，然后继续菜单循环。
// break;
// // case 3 对应按年龄排序。
// case 3:
// // 调用 sortByAge，对当前人员列表进行升序冒泡排序。
// sortByAge();
// // break 结束当前分支，不退出程序。
// break;
// // case 4 对应显示全部人员。
// case 4:
// // 调用 showAll 输出当前人员列表。
// showAll();
// // break 跳出 switch，回到菜单循环。
// break;
// // case 0 对应退出系统。
// case 0:
// // 在退出之前输出提示。
// System.out.println("退出程序。");
// // return 直接结束整个 runMenu 方法，而不是只跳出 switch；main 随后执行 finally。
// return;
// // default 处理所有未列出的整数选项，例如 9 或 -1。
// default:
// // 输出无效选择提示，switch 结束后进入下一轮菜单。
// System.out.println("无效选择，请重新输入。");
// // 右大括号结束「switch (choice)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束 try 块；catch 捕获 IllegalArgumentException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (IllegalArgumentException e) {
// // catch 捕获录入或查询方法主动抛出的 IllegalArgumentException；getMessage 取出具体原因，程序继续循环。
// System.out.println("输入错误：" + e.getMessage());
// // 右大括号结束「catch (IllegalArgumentException e)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「while (true)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 右大括号结束「private static void runMenu()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 声明无参数无返回值的录入方法；内部使用类共享的 scanner 和 persons。
// private static void addPerson() {
// // 输出输入姓名的提示，等待一行文本。
// System.out.print("请输入姓名: ");
// // 读取完整姓名并 trim 两端普通空白；允许姓名中间存在空格。
// String name = scanner.nextLine().trim();
// // isEmpty 检查去除两端空白后的字符串是否长度为 0。
// if (name.isEmpty()) {
// // 空姓名违反录入约定；throw 主动抛出参数异常，返回菜单处的 catch。
// throw new IllegalArgumentException("姓名不能为空！");
// // 右大括号结束「if (name.isEmpty())」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 输出年龄输入提示，只在姓名校验成功后执行。
// System.out.print("请输入年龄: ");
// // 声明整数 age；后续必须成功解析才会使用。
// int age;
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // 读取年龄行、去两端空白、parseInt 转成 int，整数范围外或非整数会失败。
// age = Integer.parseInt(scanner.nextLine().trim());
// // 右大括号结束 try 块；catch 捕获 NumberFormatException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (NumberFormatException e) {
// // 将底层数字格式错误转换为面向使用者的参数异常，交给菜单统一显示。
// throw new IllegalArgumentException("年龄必须是整数！");
// // 右大括号结束「catch (NumberFormatException e)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// //
// //
// //  表示年龄小于 0 或大于 150 任一成立就不合法；0 和 150 本身允许。
// if (age < 0 || age > 150) {
// // 抛出带合法范围提示的异常；不向列表加入无效数据。
// throw new IllegalArgumentException("年龄必须在 0 到 150 之间！");
// // 右大括号结束「if (age < 0
// //
// //  age > 150)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // new Person(name,age) 调用构造方法生成对象，persons.add 将引用加入动态列表末尾。
// // 所有验证通过后才添加，避免半条记录。
// persons.add(new Person(name, age));
// // 输出姓名和年龄确认录入成功，+ 负责字符串拼接。
// System.out.println("录入成功：" + name + "（" + age + "岁）");
// // 右大括号结束「private static void addPerson()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 声明按姓名查询的静态方法，直接使用共享人员列表。
// private static void findByName() {
// // persons.isEmpty 判断列表是否没有任何元素；空列表不必再要求姓名。
// if (persons.isEmpty()) {
// // 输出没有人员的信息，告诉用户需先录入。
// System.out.println("当前没有任何人员信息。");
// // return 结束查询并回到菜单，不退出整个程序。
// return;
// // 右大括号结束「if (persons.isEmpty())」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 输出查询姓名提示。
// System.out.print("请输入要查询的姓名: ");
// // 读取整行姓名并去掉两端普通空白，与录入时采用一致规则。
// String name = scanner.nextLine().trim();
// // 判断查询字符串是否为空，避免无意义的搜索。
// if (name.isEmpty()) {
// // 抛出参数异常，由菜单显示错误后继续运行。
// throw new IllegalArgumentException("姓名不能为空！");
// // 右大括号结束「if (name.isEmpty())」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // boolean 变量 found 初始为 false，记录是否已经找到至少一个匹配。
// boolean found = false;
// // 增强 for 循环依次取出 persons 中的 Person 引用到 p，不需要手写下标。
// for (Person p : persons) {
// // getName 返回对象姓名，equals 按字符串内容精确比较，区分大小写；不用 == 比较引用。
// if (p.getName().equals(name)) {
// // 拼接 Person 对象时会自动调用 toString，输出姓名和年龄信息。
// System.out.println("找到：" + p);
// // 找到匹配后置 found=true；不 break，从而能显示所有同名人员。
// found = true;
// // 右大括号结束「if (p.getName().equals(name))」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「for (Person p : persons)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // !found 表示没有任何匹配；只在遍历全部列表后判断。
// if (!found) {
// // 输出带查询姓名的未找到提示。
// System.out.println("未找到姓名为“" + name + "”的人员。");
// // 右大括号结束「if (!found)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「private static void findByName()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 声明按年龄排序方法；排序改变列表顺序，不删除或修改 Person 字段。
// private static void sortByAge() {
// // 检查是否为空列表，避免无数据时显示排序成功。
// if (persons.isEmpty()) {
// // 输出无人员提示。
// System.out.println("当前没有任何人员信息。");
// // 提前结束排序并返回菜单。
// return;
// // 右大括号结束「if (persons.isEmpty())」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 外层 for 控制冒泡轮次；n 个元素最多 n-1 轮，每轮将剩余最大值送到末端。
// for (int i = 0; i < persons.size() - 1; i++) {
// // 内层 for 比较相邻元素 j 和 j+1；每轮末端已有 i 个元素归位，因此上界减少 i。
// // j 最大为 n-2-i，保证 j+1 不越界。
// for (int j = 0; j < persons.size() - 1 - i; j++) {
// // get(j) 取列表中第 j 个对象，getAge() 读取年龄；只有左侧年龄较大才交换，因此同年龄顺序保持稳定。
// if (persons.get(j).getAge() > persons.get(j + 1).getAge()) {
// // temp 保存左侧 Person 引用，否则下一行覆盖左侧后会丢失它。
// Person temp = persons.get(j);
// // set(j,persons.get(j+1)) 用右侧对象替换左侧槽位，不改变列表大小。
// persons.set(j, persons.get(j + 1));
// // 将原左侧对象 temp 写到右侧位置，完成一次相邻交换。
// persons.set(j + 1, temp);
// // 右大括号结束「if (persons.get(j).getAge() > persons.get(j + 1).getAge())」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「for (int j = 0; j < persons.size() - 1 - i; j++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 右大括号结束「for (int i = 0; i < persons.size() - 1; i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 所有轮次完成后输出排序成功提示。
// System.out.println("已按年龄从小到大排序。");
// // 调用 showAll 展示新的排序结果，不重复编写打印逻辑。
// showAll();
// // 右大括号结束「private static void sortByAge()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 声明显示人员列表的方法，录入和排序共享同一份数据。
// private static void showAll() {
// // 若列表为空则进入提示分支。
// if (persons.isEmpty()) {
// // 输出没有人员信息的提示。
// System.out.println("当前没有任何人员信息。");
// // 提前返回，不输出空的编号列表。
// return;
// // 右大括号结束「if (persons.isEmpty())」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 拼接 persons.size() 即当前人数，输出列表标题。
// System.out.println("----- 人员列表（共 " + persons.size() + " 人）-----");
// // 从 0 遍历至 size()-1；列表下标与数组一样从 0 开始。
// for (int i = 0; i < persons.size(); i++) {
// // i+1 是给用户看的编号（从 1 开始）；get(i) 仍然使用从 0 开始的下标。
// // 对象拼接自动调用其 toString 方法。
// System.out.println((i + 1) + ". " + persons.get(i));
// // 右大括号结束「for (int i = 0; i < persons.size(); i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 右大括号结束「private static void showAll()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // private static class 声明仅外部类内部使用的静态嵌套类 Person；每个实例表示一个人员。
// private static class Person {
// // private final String 字段保存姓名；构造后不能重新赋值，外部通过 getter 读取。
// private final String name;
// // private final int 字段保存年龄；final 表示创建对象后年龄固定。
// private final int age;
// // 构造方法与 Person 类同名且无返回类型，接收姓名和年龄两个参数。
// Person(String name, int age) {
// // this.name 是当前对象的字段，右侧 name 是参数；赋值把传入的姓名保存到该对象。
// this.name = name;
// // this.age 指当前对象的年龄字段；将参数 age 写入它。
// this.age = age;
// // 右大括号结束「Person(String name, int age)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // getName 是返回 String 的无参方法，对外提供受控的姓名读取。
// String getName() {
// // return name 返回该对象保存的姓名引用，不修改字段。
// return name;
// // 右大括号结束「String getName()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // getAge 是返回 int 的无参方法，用于年龄比较。
// int getAge() {
// // 返回当前对象的年龄整数，排序方法通过它读取年龄。
// return age;
// // 右大括号结束「int getAge()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // @Override 注解要求编译器检查下一方法确实重写父类方法，避免误拼写。
// @Override
// // public String toString() 重写 Object 的文本表示方法；打印或字符串拼接对象时可自动调用。
// public String toString() {
// // 使用 + 拼接姓名和年龄并返回整段文本，使对象输出易读。
// return "姓名：" + name + "，年龄：" + age;
// // 右大括号结束「public String toString()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「private static class Person」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「public class CampusPersonManager」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
