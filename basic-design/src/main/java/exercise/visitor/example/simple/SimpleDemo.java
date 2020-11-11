package exercise.visitor.example.simple;

public class SimpleDemo {

    public static void main(String[] args) {
        int type = 1;

        double pice = 0.00;
        if (type == 1) {

            pice = original(type);
        }
        if (type == 2) {
            pice = coupon(type);
        }

        if (type == 3) {
            pice = inner(type);
        }

        System.out.println("获取价格已经结束");

        System.out.println("获取到的价格为" + pice);

    }


    private static double original(int... arges) {

        System.out.println("原价计算中");
        return 100.00;
    }


    private static double coupon(int... args) {

        System.out.println("优惠券计算中");
        return 90.00;
    }

    private static double inner(int... args) {
        System.out.println("内购人员计算中");
        return 10.00;
    }
}
