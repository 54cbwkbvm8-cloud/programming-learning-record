import java.util.Scanner;

public class StudentArrayDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student[] students = new Student[2];
        System.out.println("刚创建数组时 students[0] = " + students[0]);
        for (int i = 0; i < students.length; i++) {
            System.out.println("请输入第 " + (i + 1) + " 个学生的姓名和成绩（用空格隔开）：");
            String name = sc.next();
            while (!sc.hasNextInt()) {
                System.out.println("成绩必须是整数，请重新输入第 " + (i + 1) + " 个学生的成绩：");
                sc.next();
            }
            int score = sc.nextInt();
            students[i] = new Student(name, score);
        }
        System.out.println("---- 录入结果 ----");
        int sum = 0;
        for (Student stu : students) {
            System.out.println(stu);
            sum += stu.getScore();
        }
        double average = (double) sum / students.length;
        System.out.printf("平均成绩：%.2f%n", average);
        sc.close();
    }
}
 // // 导入 Scanner：Java 自带的"键盘输入工具"，
 // import java.util.Scanner;
 
 // /**
//  * StudentArrayDemo —— 用"对象数组"保存多个学生，录入姓名和成绩，并计算平均分
//  *
//  * 【什么是对象数组】
//  *   数组不仅能存放数字，也能存放对象。Student[] students = new Student[2]; 创建的是
//  *   一个有 2 个"格子"的数组，每个格子将来用来存放【一个 Student 对象的地址】。
//  *   重要：刚创建数组时，只是准备好了 2 个空格子，并【没有】创建任何 Student 对象！
//  *   对象类型数组的格子里，默认值是 null（意思是"什么都没有，不指向任何对象"）。
//  *   要想往里放学生，必须一个一个用 new Student(...) 创建，再把地址放进格子。
//  *
//  * 【运行前提】
//  *   这个程序用到 Student 类，所以要和 Student.java 放在同一个文件夹里一起编译：
//  *     javac StudentArrayDemo.java Student.java
//  *   注意：这里的 Student 是 (姓名, 成绩) 版本的，不是之前那个带学号 id 的版本。
//  *
//  * 【本次修复的问题】（这份代码有 4 处真正的问题）
//  *   1. 提示和代码对不上（最严重）：
//  *      每个学生录入后，程序会提示"请输入第 N 个学生的年龄："，但代码里根本没有读取年龄，
//  *      Student 类里也没有年龄这个属性。用户按提示输入年龄后，这个年龄会被当作【下一个学生的姓名】读走，
//  *      接着读成绩时遇到了真正的姓名，程序就直接崩溃了（InputMismatchException）。
//  *      修复：删掉了这条多余的年龄提示。
//  *   2. 平均分算错了：double average = sum / students.length; 中，sum 和 students.length 都是整数，
//  *      两个整数相除，结果会先【丢掉小数部分】，再赋给 double。
//  *      例如成绩 85 和 90，总和 175 除以 2 应该是 87.5，原程序却得到 87（显示成 87.00）。
//  *      修复：先把 sum 转换成 double 再除：(double) sum / students.length。
//  *   3. 打印"---- 录入结果 ----"用的是 print（不换行），导致第一个学生的信息紧跟在它后面，挤在同一行：
//  *        ---- 录入结果 ----Student{name='Tom', score=90}
//  *      修复：改成 println（打印后换行）。
//  *   4. 成绩位置输入的不是整数（比如输入了字母）时，原代码会直接崩溃。
//  *      修复：先用 hasNextInt() 检查，不是整数就提示重新输入。
//  *   另外把第一行输出里的 "student[0]" 改成了与变量名一致的 "students[0]"，
//  *   并把提示里"第 N个"的间距统一成"第 N 个"。
//  */
// public class StudentArrayDemo {

//     // main 是程序的入口，运行程序时从这里开始一行一行往下执行
//     public static void main(String[] args) {

//         // 创建 Scanner 对象 sc，负责读取键盘输入
//         Scanner sc = new Scanner(System.in);

//         // 创建一个能存放 2 个 Student 的数组。
//         // 注意：这里只是准备了 2 个空格子，一个 Student 对象都还没有创建。
//         Student[] students = new Student[2];

//         // 直接把 students[0]（下标为 0 的格子）拼进文字里打印。
//         // 因为格子里还没有放对象，所以显示的是 null。
//         // 这行输出就是在演示"对象数组的格子默认是 null"。
//         // 【修复点】原来输出的文字写的是 "student[0]"，少了 s，已改成和变量名一致的 "students[0]"。
//         System.out.println("刚创建数组时 students[0] = " + students[0]);

//         // 循环录入每个学生。i 是下标，从 0 开始，到 students.length - 1 结束。
//         // （students.length 是数组的长度，这里是 2；注意 length 后面没有括号。）
//         for (int i = 0; i < students.length; i++) {

//             // i 是从 0 开始数的，但人习惯说"第 1 个"，所以提示里用 (i + 1)。
//             // 加括号是为了让 Java 先算加法 i + 1，再把结果拼进文字里；
//             // 否则 "第 " + i + 1 会被拼成"第 01"之类的文字，而不是做加法。
//             System.out.println("请输入第 " + (i + 1) + " 个学生的姓名和成绩（用空格隔开）：");

//             // sc.next() 读取一个"单词"：遇到空格或换行就停止。所以姓名里不能有空格。
//             String name = sc.next();

//             // 【修复点】成绩必须是整数。
//             // sc.hasNextInt() 的意思是："用户接下来输入的内容，能不能当作一个整数？"，只看不取，不会报错。
//             // !sc.hasNextInt() 读作"不是整数"（! 表示取反）。
//             // 只要输入的不是整数，就提示重新输入，并用 sc.next() 把那段错误内容读出来丢掉
//             // （如果不丢掉，它会一直留在输入里，下一轮检查还是看到它，就会无限提示）。
//             while (!sc.hasNextInt()) {
//                 System.out.println("成绩必须是整数，请重新输入第 " + (i + 1) + " 个学生的成绩：");
//                 sc.next();
//             }
//             int score = sc.nextInt();   // 确认是整数后，才真正读取

//             // 【修复点】这里原来还有一句提示："请输入第 N 个学生的年龄："。
//             // 但是代码并没有读取年龄，Student 也没有年龄属性，这条提示会误导用户，已删除。

//             // 创建一个 Student 对象，并把它的地址存进数组的第 i 个格子。
//             // 现在这个格子里不再是 null，而是指向了一个真正的学生对象。
//             students[i] = new Student(name, score);
//         }

//         // 【修复点】原来这里用的是 print（不换行），第一个学生的信息会紧跟在标题后面。
//         // 改成 println，标题单独占一行。
//         System.out.println("---- 录入结果 ----");

//         // sum 用来累加所有人的成绩，初始值必须是 0
//         int sum = 0;

//         // 增强 for 循环（for-each）：读作"对于数组 students 里的每一个 Student 类型的元素 stu"，
//         // 依次取出每一个元素，放进变量 stu，然后执行一遍花括号里的内容。
//         for (Student stu : students) {

//             // println(对象) 会自动调用对象的 toString() 方法，把返回的文字打印出来。
//             // Student 里重写了 toString，所以会显示成 Student{name='Tom', score=90}。
//             System.out.println(stu);

//             // stu.getScore() 取出这个学生的成绩，累加到 sum 里
//             // （sum += x 等价于 sum = sum + x）。
//             // 注意：如果某个格子里还是 null，对 null 调用 getScore() 会崩溃（空指针异常，
//             // 见 NullPointerDemo.java）。这里每个格子在上面的循环里都放了对象，所以不会出问题。
//             sum += stu.getScore();
//         }

//         // 【修复点】计算平均分。
//         // 原来写的是 sum / students.length：两个都是 int，整数相除只保留整数部分，小数被直接丢掉，
//         // 比如 175 / 2 的结果是 87，而不是 87.5。
//         // 现在写成 (double) sum / students.length：
//         //   (double) sum 的意思是"强制类型转换"，先把 sum 变成 double（带小数的数），
//         //   然后 double 再除以 int，Java 会按小数除法计算，结果是 87.5。
//         double average = (double) sum / students.length;

//         // printf 是"格式化输出"：用格式串控制输出的样子。
//         //   %.2f ：把后面的小数按"保留 2 位小数"输出（f 表示浮点数，.2 表示小数点后 2 位，会四舍五入）
//         //   %n   ：换行（和 \n 的作用相同，printf 里推荐用 %n）
//         System.out.printf("平均成绩：%.2f%n", average);

//         // 用完 Scanner 后关闭它，释放资源。这是个好习惯。
//         sc.close();
//     }
// }



