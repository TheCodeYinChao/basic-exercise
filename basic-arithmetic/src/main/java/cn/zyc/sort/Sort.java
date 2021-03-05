package cn.zyc.sort;

import org.junit.Test;

/**
 * dsc: Sort
 * date: 2021/3/5 19:33
 * author: zyc
 */
public class Sort {

    @Test
    public void bulling(){
        int [] arr = {3,2,1,6,5};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i-1; j++) {
                if(arr[j] > arr[j+1]){
                    int b = arr[j];
                    int a = arr[j+1];
                    arr[j] = a;
                    arr[j+1] = b;
                }
            }
        }

        System.out.println();
        for (int i : arr) {
            System.out.print(i+"  ");
        }
    }
}
