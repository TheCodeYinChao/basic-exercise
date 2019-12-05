package exercise.visitor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
      /*  Cpu cpu = new Cpu();

        Visitor visitor = new VisitorImpl();
        cpu.accept(visitor);
*/
        new De().test();


    }

    static class De {
        public void test() throws InterruptedException {
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    synchronized (Thread.class) {
                        try {
                            Thread.sleep(Long.MAX_VALUE);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }, "我是线程1");
            t1.start();

            Thread.sleep(500);
            Thread t2 = new Thread("ws");

            System.out.println();
            t2.start();
        }
    }
}
