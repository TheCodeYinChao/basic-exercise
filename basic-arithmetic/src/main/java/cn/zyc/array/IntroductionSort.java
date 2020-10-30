package cn.zyc.array;


import org.junit.Test;

/**
 * description: IntroductionSort 排序入门 <br>
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
     */
    @Test
    public void run(){//md，第一个就演砸啦
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


    private void arrayPring(int[] METE_DATA){
        for (int i = 0; i < METE_DATA.length; i++) {
            int mete_datum = METE_DATA[i];
            System.out.println(mete_datum);
        }
    }
}
