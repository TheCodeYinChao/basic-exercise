package cn.zyc.hash;

import org.junit.Test;

/**
 * dsc: HashTable
 * date: 2021/1/28 19:00
 * author: zyc
 *
 * hash("nice")=(("n" - "a") * 26*26*26 + ("i" - "a")*26*26 + ("c" - "a")*26+ ("e"-"a")) / 78978
 */
public class HashTable {




    @Test
    public void hash(){

        int a = getAsc("a");
        int i = ((getAsc("n") - a) * 26 * 26 * 26 + (getAsc("i") - a) * 26 * 26 + (getAsc("c") - a) * 26 + (getAsc("e") - a)) / 78978;
        System.out.println(i);
    }

    public static int getAsc(String st) {
        byte[] gc = st.getBytes();
        int ascNum = (int) gc[0];
        return ascNum;
    }
}
