package cn.zyc.计算机局部性原理;

/**
 * description: Array2Demo  局部性原理提升的 计算机访问处理的速度<br>
 * date: 2020/9/11 14:40 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Array2Demo {
    public static void main(String[] args) {
        long sum = 0;

        int[][] dataArray = new int[10000][10000];


        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                dataArray[i][j] = 1;
            }
        }

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                sum += dataArray[i][j];//注意这里
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + "  time : " + (end - startTime));

        sum = 0;
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                sum += dataArray[j][i];//注意这里的不同 ，会对性能又质的影响
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("sum = " + sum + "  time : " + (end1 - end));
    }
}
