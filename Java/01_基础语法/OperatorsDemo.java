public class OperatorsDemo {
    public static void main(String[] args) {
        int a = 17;
        int b = 5;
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));
        System.out.println("a % b = " + (a % b));
        System.out.println("17.0 / 5 = " + (17.0 / 5));
        System.out.println("-17 % 5 = " + (-17 % 5));
        System.out.println("1 + 2 + \"3\" = " + (1 + 2 + "3"));
        System.out.println("\"1\" + 2 + 3 = " + ("1" + 2 + 3));
        System.out.println("a > b : " + (a > b));
        System.out.println("a >= 17 : " + (a >= 17));
        System.out.println("a <= 16 : " + (a <= 16));
        System.out.println("a == b : " + (a == b));
        System.out.println("a != b : " + (a != b));
        int score = 75;
        boolean pass = score >= 60;
        boolean excellent = score >= 90;
        System.out.println("pass && excellent : " + (pass && excellent));
        System.out.println("pass || excellent : " + (pass || excellent));
        System.out.println("!pass : " + (!pass));
        System.out.println("60 <= score < 90 : " + (score >= 60 && score < 90));
        int n1 = 5;
        boolean r1 = (n1 > 10) && (++n1 > 0);
        System.out.println("&& : r1 = " + r1 + ", n1 = " + n1);
        int n2 = 5;
        boolean r2 = (n2 > 10) & (++n2 > 0);
        System.out.println("& : r2 = " + r2 + ", n2 = " + n2);
        int n3 = 5;
        boolean r3 = (n3 < 10) || (++n3 > 0);
        System.out.println("|| : r3 = " + r3 + ", n3 = " + n3);
        int n4 = 5;
        boolean r4 = (n4 < 10) | (++n4 > 0);
        System.out.println("| : r4 = " + r4 + ", n4 = " + n4);
        int d = 0;
        boolean safe = (d != 0) && (10 / d > 1);
        System.out.println("安全检查safe = " + safe);
        int i = 5;
        int j = i++;
        System.out.println("j = i++ : j = " + j + ", i = " + i);
        int k = i++;
        System.out.println("k = i++ : k = " + k + ",  i = " + i);
        int m = 5;
        System.out.println("m++的值：" + (m++));
        System.out.println("此时m = " + m);
        System.out.println("++m的值：" + (++m));
        int t = 5;
        t = t++;
        System.out.println("t = t++ : t = " + t);
        int s = 3;
        int r = s++ + ++s;
        System.out.println("s++ + ++s : r = " + r + ", s = " + s);
        String result = score >= 60 ? "及格" : "不及格";
        System.out.println("score = " + score + " -> " + result);
        int max = (a > b) ? a : b;
        System.out.println("a,b中较大的值是：" + max);
        int num = 7;
        String parity = (num % 2 == 0) ? "偶数" : "奇数";
        System.out.println(num + "是" + parity);

    }

}
