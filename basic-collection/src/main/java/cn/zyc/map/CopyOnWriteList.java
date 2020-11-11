package cn.zyc.map;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteList {

    public static void main(String[] args) {

        CopyOnWriteArrayList<String> c = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 10; i++) {
            c.add("i" + i);
        }


        c.remove(0);

        c.get(0);
    }
}
