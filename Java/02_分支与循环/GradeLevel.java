import java.util.Scanner;

public class GradeLevel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入成绩（0～100）的整数");
        if (!sc.hasNextInt()) {
            System.out.println("输入错误：请输入整数成绩，例如85");
        } else {
            int score = sc.nextInt();
            if (score < 0 || score > 100) {
                System.out.println("输入错误：成绩必须在0～100之间");
            } else {
                String level;
                if (score >= 90) {
                    level = "A(优秀)";
                } else if (score >= 80) {
                    level = "B(良好)";
                } else if (score >= 70) {
                    level = "C(中等)";
                } else if (score >= 60) {
                    level = "D(及格)";
                } else {
                    level = "E(不及格)";
                }
                System.out.println("写法一（if- else）：" + score + "分 -> " + level);
                String level2;
                switch (score / 10) {
                    case 10:
                    case 9:
                        level2 = "A(优秀)";
                        break;
                    case 8:
                        level2 = "B(良好)";
                        break;
                    case 7:
                        level2 = "C(中等)";
                        break;
                    case 6:
                        level2 = "D(及格)";
                        break;
                    default:
                        level2 = "E(不及格)";
                        break;
                }
                System.out.println("写法二（switch）：" + score + "分 -> " + level2);
            }
        }
        sc.close();
    }
}
