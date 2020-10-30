package cn.zyc.sort;

/**
 * description: IntroductionSort  冒泡排序<br>
 * date: 2020/6/19 17:05 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class BubblingSort {


    public static void main(String[] args) {
        int[] arr = {3, 12, 4, 6, 9, 13, 123, 221, 22, 313, 1};


        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr.length - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {
                    int i1 = arr[j + 1];
                    int i2 = arr[j];
                    arr[j] = i1;
                    arr[j + 1] = i2;
                }
            }
        }

        //打印
        for (int i : arr) {
            System.out.println(i);
        }

    }

}
