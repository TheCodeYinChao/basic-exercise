package cn.zyc.sort;


/**
 * 归并排序（分治算法）
 *
 * @Author wenxiaoYa
 * @Date 2020/06/15  16:34
 **/
public class MergeSort {

    public static void main(String[] args) {
       /* int[] arr = {3, 1, 4, 12, 22, 88, -7, 99, -2};
        int[] temp = new int[arr.length];
        separate(arr, 0, arr.length - 1, temp);

        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[8000000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long l = System.currentTimeMillis();
        separate(arr, 0, arr.length - 1, temp);
        long l1 = System.currentTimeMillis();

        System.out.println();
        System.out.println("花费:" + (l1 - l));


    }

    public static void separate(int[] arr, int left, int right, int[] temp) {
        if (left < right) {

            int mid = (left + right) / 2;

            separate(arr, left, mid, temp);
            separate(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }


    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left;
        int j = mid + 1;
        int index = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[index] = arr[i];
                i++;
                index++;
            } else {
                temp[index] = arr[j];
                j++;
                index++;
            }
        }

        while (i <= mid) {
            temp[index] = arr[i];
            i++;
            index++;
        }

        while (j <= right) {
            temp[index] = arr[j];
            j++;
            index++;
        }

        index = 0;
        int tempLefet = left;
        while (tempLefet <= right) {
            arr[tempLefet] = temp[index];
            index++;
            tempLefet++;
        }

    }
}
