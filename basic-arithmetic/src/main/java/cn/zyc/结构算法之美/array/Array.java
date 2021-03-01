package cn.zyc.结构算法之美.array;

/**
 * @author zyc
 * @date 2021/3/1
 * @time 22:46
 * @description :
 */
public class Array {
    private int[] arr;
    private int n;
    private int count;

    public Array(int n) {
        arr =  new int[n];
        this.n = n;
        this.count = 0;
    }

    /**
     * 索引插入 尾部插入
     */
    public void insert(int  index,int val){
        // 数组空间已满
        if(n == count){
            System.out.println("数组已满");
            return;
        }
        //位置不合法
        if(index<0 || index > count){
            System.out.println("位置不合法");
            return;
        }
        //往后挪动
        for (int i = count; i > index; i--) {
            arr[i]= arr[i-1];
        }
        arr[index] = val;
        count++;
    }

    public int find(int index){
        if(index < 0 || index>=count)return -1;
        return arr[index];
    }

    /**
     * 索引删除
     * @param index
     */
    public void delete(int index){
        if(index < 0 || index >= count){
            return;
        }
        for (int i = index; i < count; i++) {
            arr[i]= arr[i+1];
        }
        --count;
    }

    public void printAll(){
        if(count==0){
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.print(arr[i]+"   ");
        }
    }
    public static void main(String[] args) {
        Array array = new Array(5);
        array.printAll();
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        //array.insert(3, 11);
        array.printAll();
    }
}
