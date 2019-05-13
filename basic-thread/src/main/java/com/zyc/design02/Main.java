package com.zyc.design02;

public class Main {
    public static void main(String[] args) {
        Master master = new Master(new Worker(), 10);

        for(int i = 1; i <= 100; i++){
            Data t = new Data();
            t.setId(i);
            t.setName("ZYC" + i);
            master.submit(t);
        }
        master.execute();
        long start = System.currentTimeMillis();

        while(true){
            if(master.isComplete()){
                long end = System.currentTimeMillis() - start;
                String priceResult = master.getResult();
                System.out.println("最终结果：" + priceResult + ", 执行时间：" + end);
                break;
            }
        }


    }
}
