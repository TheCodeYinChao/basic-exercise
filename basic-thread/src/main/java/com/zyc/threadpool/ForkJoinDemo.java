package com.zyc.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * description: ForkJoinDemo <br>
 * date: 2020/3/17 18:19 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class ForkJoinDemo extends RecursiveTask<Integer>{
    private int start = 0;
    private int end = 0;
    private int threshold = 10;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinDemo taskDemo = new ForkJoinDemo(0,100);
        ForkJoinTask<Integer> fork = taskDemo.fork();
        Integer integer = fork.join();
        System.out.println(integer);

        if(fork.isCompletedAbnormally()){
            Throwable exception = fork.getException(); //抛出来的异常
            fork.completeExceptionally(exception); //处理异常
        }
//        ForkJoinPool f = new ForkJoinPool();
//        ForkJoinTask<Integer> submit = f.submit(new TaskDemo(1, 100));
//        Integer integer = submit.get();
//        System.out.println(integer);

    }




        public ForkJoinDemo(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if((end-start) < threshold){
                int sum =0;
                for(int i = start; i <= end; i++){
                    sum += i;
                }
                return sum;
            }else{
                int i = (end + start)  /2 ;
                ForkJoinDemo t1 = new ForkJoinDemo(start,i+1);
                ForkJoinDemo t2 = new ForkJoinDemo(i+1,end);
                t1.fork(); t2.fork();
                return t1.join() + t2.join();
            }
        }
}
