package cn.zyc.sort;

/**
 * 希尔排序
 *
 * @Author wenxiaoYa
 * @Date 2020/06/12  15:04
 **/
public class ShellSort {

    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        shellSort(arr);

        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[8000000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long l = System.currentTimeMillis();
        shellSort(arr);
        long l1 = System.currentTimeMillis();

        System.out.println();
        System.out.println("花费:" + (l1 - l));
    }

    public static void shellSort(int[] arr) {

        for (int i = arr.length / 2; i > 0; i /= 2) {
            for (int z = i; z < arr.length; z++) {

                int index = z - i;
                int temp = arr[z];
                while (index >= 0 && temp < arr[index]) {
                    arr[index + i] = arr[index];
                    index -= i;
                }
                if (index != z - i) {
                    arr[index + i] = temp;
                }
            }
        }
    }
}
