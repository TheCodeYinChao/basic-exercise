package cn.zyc.sort;

import org.junit.Test;

/**
 * dsc: Sort
 * date: 2021/3/5 19:33
 * author: zyc
 */
public class Sort {

    /**
     * 冒泡
     */
    @Test
    public void bulling(){
        int [] arr = {3,2,1,6,5};

        for (int i = 0; i < arr.length; i++) {
            boolean br = false;
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]){//这里加1则顾忌到上面-1的情况也就是最后一个索引位置
                    int val = arr[j];
                    int val1 = arr[j+1];
                    arr[j+1] = val;
                    arr[j] = val1;
                    br = true;
                }
            }
            /**
             * 排序分为后半部分有序列，前半部分无序列，当循环跳出，重新发现没有可排序的对象啦，这时候自然就都是有序的啦
             */
            if(!br){
                break;
            }
        }
        print(arr);
    }

    /**
     * 插入
     */
    @Test
    public void insertionSort(){
        int [] arr = {3,2,1,6,5};
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            int j = i-1;
            for (; j >= 0; j--) {
                if(arr[j] > val){
                    arr[j+1] = arr[j];//往后推一位
                }else{break;}//前半部分已经有序
            }
            arr[j+1] = val;
        }
        print(arr);
    }

    /**
     * 选择
     */
    @Test
    public void selectionSort(){
        int [] arr = {3,2,1,6,5};

        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            int min = i;
            for ( int j = i; j < arr.length-1; j++) {
                if(arr[j+1] < val){
                    val = arr[j+1];
                    i = j+1;
                }
            }
            arr[i] = arr[min];
            arr[min] = val;
        }
        print(arr);

    }


     private void print(int[] arr){
        System.out.println();
        for (int i : arr) {
            System.out.print(i+"  ");
        }
    }
}
