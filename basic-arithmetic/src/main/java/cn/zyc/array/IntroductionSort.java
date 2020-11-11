package cn.zyc.array;


import org.junit.Test;

/**
 * description: IntroductionSort 排序入门
 *
 * 冒泡、选择、插入用大 O 表示法都需要 O(N2) 时间级别。一般不会选择冒泡排序，
 * 虽然冒泡排序书写是最简单的，但是平均性能是没有选择排序和插入排序好的。
 * 选择排序把交换次数降低到最低，但是比较次数还是挺大的。当数据量小，
 * 并且交换数据相对于比较数据更加耗时的情况下，可以应用选择排序。
 *
 * date: 2020/10/30 18:51 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class IntroductionSort {
    /**
     *数组元数据
     */
    private final static int[] METE_DATA ={22,97,42,145,73,543,16,1,3456,32};

    /**
     * 冒泡排序
     * （N-1）+（N-2）+...+1 = N^（N-1）/2 =   N^（N-1）/2
     *  大O 表示法  O(N^2)
     */
    @Test
    public void BubblingRun(){//md，第一个就演砸啦
        /**操作基于同一个 数组 ，第一轮则找出一个最大的放到右边 第二轮就不用管它啦*/
        for (int i = 1; i < METE_DATA.length; i++) { //比较的轮数

            for (int j = 0; j < METE_DATA.length-i; j++) {
                int v1 = METE_DATA[j];
                int v2 = METE_DATA[j+1];
                if(v1 > v2){ //交换
                    METE_DATA[j+1] =v1;
                    METE_DATA[j] = v2;

                }
            }

        }
        arrayPring(METE_DATA);
    }

    /**
     * 选择排序
     * 这个切入点就是 index 索引的交换
     */
    @Test
    public void SelectionRun(){
        /**选择排序 */
        for (int i = 0; i < METE_DATA.length-1; i++) { //比较的轮数
            int min = i;
            //每轮需要比较的次数
            for(int j = i+1 ; j < METE_DATA.length ; j++){
                if(METE_DATA[j]<METE_DATA[min]){
                    min = j;//记录目前能找到的最小值元素的下标
                }
            }
            //将找到的最小值和i位置所在的值进行交换
            if(i != min){
                int temp = METE_DATA[i];
                METE_DATA[i] = METE_DATA[min];
                METE_DATA[min] = temp;
            }
        }
        arrayPring(METE_DATA);
    }


    /**
     * 插入排序
     */
    @Test
    public void InsertRun(){
        int j;
        //从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for(int i = 1 ; i < METE_DATA.length ; i++){
            int tmp = METE_DATA[i];//记录要插入的数据
            j = i;
            while(j > 0 && tmp < METE_DATA[j-1]){//从已经排序的序列最右边的开始比较，找到比其小的数 从右边往左边推
                METE_DATA[j] = METE_DATA[j-1];//向后挪动
                j--;
            }
            METE_DATA[j] = tmp;//存在比其小的数，插入
        }
        arrayPring(METE_DATA);
    }




    private void arrayPring(int[] METE_DATA){
        for (int i = 0; i < METE_DATA.length; i++) {
            int mete_datum = METE_DATA[i];
            System.out.println(mete_datum);
        }
    }
}
