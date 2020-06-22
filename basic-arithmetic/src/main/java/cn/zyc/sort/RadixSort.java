package cn.zyc.sort;

/**
 * 基数排序
 * <p>
 * 注：此写法，对于存在负数的数组，无效（java.lang.ArrayIndexOutOfBoundsException）
 * （空间换时间）
 *
 * @Author wenxiaoYa
 * @Date 2020/06/18  18:55
 **/
public class RadixSort {

    public static void main(String[] args) {
        /*int[] arr = {3, 1, 4, 12, 22, 88, 454, 421, 621, 6566, 6, 99, 34};
        radixSort(arr);

        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long l = System.currentTimeMillis();
        radixSort(arr);
        long l1 = System.currentTimeMillis();

        System.out.println();
        System.out.println("花费:" + (l1 - l));
    }

    public static void radixSort(int[] arr) {

        int[][] bucket = new int[10][arr.length];

        int[] elementCount = new int[10];

        String max = max(arr) + "";
        for (int i = 0, n = 1; i < max.length(); i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {

                int digitOfelementCount = arr[j] / n % 10;

                bucket[digitOfelementCount][elementCount[digitOfelementCount]] = arr[j];
                elementCount[digitOfelementCount]++;
            }

            int index = 0;
            for (int z = 0; z < bucket.length; z++) {
                if (elementCount[z] != 0) {
                    for (int j = 0; j < elementCount[z]; j++) {
                        arr[index] = bucket[z][j];
                        index++;
                    }
                    elementCount[z] = 0;
                }
            }

        }

    }

    public static int max(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

}
