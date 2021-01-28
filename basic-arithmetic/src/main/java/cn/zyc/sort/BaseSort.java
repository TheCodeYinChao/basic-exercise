package cn.zyc.sort;

import org.junit.Test;

/**
 * description: IntroductionSort  冒泡排序<br>
 * date: 2020/6/19 17:05 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 *
 *     1 原地排序算法？ 空间固定 不需要额外的存储空间
 *     2 稳定排序算法？ 相同值不变顺序
 *
 *     满有序度  = n*(n-1)/2
 *
 *     逆序度 = 满有序度 - 有序度
 */
public class BaseSort {

    /**
     * 冒泡排序
     * 一遍遍找最高值冒泡到最顶端 并比较交换
     * 初始       4  5  6  3  2  1
     * 冒泡一     4  5  3  2  1  6
     * 冒泡二     4  3  2  1  5  6
     * 冒泡三     3  2  1  4  5  6
     * 冒泡四     2  1  3  4  5  6
     * 冒泡五     1  2  3  4  5  6
     * 结束
     *
     * 原地排序
     * 稳定排序
     *
     * 最好O(n)
     * 最坏O(N^2)
     * 平均复杂度  O(n^2) 【通过 满有序 取平均概率计算】
     */
    @Test
    public  void bubblingSort() {
        int[] arr = {4,5,6,3,2,1};


        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {
                    int i1 = arr[j + 1];
                    int i2 = arr[j];
                    arr[j] = i1;
                    arr[j + 1] = i2;
                    flag = true;
                }
            }
            //提交结束因为没有数据交换，证明已经有序
            if(!flag){
                break;
            }
        }

        //打印
        for (int i : arr) {
            System.out.print(i+",");
        }

    }

    /**
     * 插入排序
     * 通过比较获取插入位置并变更进适当位置
     *  最好O(n)
     *  最坏O(N^2)  因为要移动位置
     *  平均复杂度O(N^2)
     */
    @Test
    public void insertionSort(){
        int[] arr = {4,5,6,3,2,1};

        int n ;
        if((n = arr.length) == 1) return;//只有一个元素 必须有序

        for (int i = 1; i < n; i++) {
            int  val = arr[i];

            int j = i - 1;
            for(;j >= 0 ;j--){
                if(arr[j] > val){
                    arr[j+1] = arr[j];//交换并求出待插入的位置
                }else {
                    break;
                }
            }
            //因为执行啦 j-- 这里的位置会比求出的位置少一次
            arr[j+1] =val;
        }
        //打印
        for (int i : arr) {
            System.out.print(i+",");
        }

    }


    /**
     * 选择排序
     * 排序空间复杂度为 O(1)，是一种原地排序算法
     * 选择排序是一种不稳定的排序算法
     */
    @Test
    public void selectionSort(){
        int[] arr = {4,5,6,3,2,1};

        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            int val1 = val;
            int index = i;
            for (int j = i ; j < arr.length; j++) {
                if(arr[j] < val1){
                   val1 = arr[j];
                   index = j;
                }
            }

            if(index!=0){
                arr[i] = val1;
                arr[index] =val;
            }
        }
        //打印
        for (int i : arr) {
            System.out.print(i+",");
        }
    }
}
