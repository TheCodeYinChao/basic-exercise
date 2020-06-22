package cn.zyc.sort;

/**
 * description: SelectionSort 选择排序<br>
 * date: 2020/6/19 17:16 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class SelectionSort {

    public static void main(String[] args) {

        int[] arr = {3,12,4,6,9,13,123,221,22,313,1};

        for (int i = 0; i < arr.length; i++) {

            int min =arr[i];

            int index =i;

            for (int j = i+1; j < arr.length; j++) {
                if(min > arr[j]){
                    index = j;
                    min = arr[j];
                }
            }
            //然后将最小值与本次循环的，开始值交换
            int temp = arr[i];
            arr[i] = min;
            arr[index] = temp;

        }

        //打印
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
