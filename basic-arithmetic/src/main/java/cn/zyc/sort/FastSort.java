package cn.zyc.sort;

import java.util.Arrays;

/**
 * 快排
 *
 * @Author wenxiaoYa
 * @Date 2020/06/11  11:24
 **/
public class FastSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};
/*        int[] arr = {3, 1, 4, 12, 22, 88, -7, 99, -2};
        fastSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));*/
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000000);
        }

        long l = System.currentTimeMillis();
        fastSort(arr, 0, arr.length - 1);
        long l1 = System.currentTimeMillis();

        System.out.println();
        System.out.println("花费:" + (l1 - l));

    }


    public static void fastSort(int[] arr, int left, int right) {

        int l = left;

        int r = right;

        int data = arr[(right + left) / 2];

        int temp = 0;

        while (l < r) {

            while (arr[l] < data) {
                l++;
            }

            while (arr[r] > data) {
                r--;
            }

            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == data) {
                r--;
            }

            if (arr[r] == data) {
                l++;
            }
        }

        if (l == r) {
            l++;
            r--;
        }

        if (left < r) {
            fastSort(arr, left, r);
        }

        if (right > l) {
            fastSort(arr, l, right);
        }

    }

}
