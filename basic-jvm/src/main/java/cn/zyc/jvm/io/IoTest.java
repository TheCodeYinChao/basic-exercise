package cn.zyc.jvm.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
 * @author zyc
 * @date 2021/3/10
 * @time 22:31
 * @description :
 */
public class IoTest {

    @Test
    public void read()throws Exception{

        Reader reader  = new BufferedReader(new FileReader(""));
        int value;
        while ((value = reader.read())!=-1){
            value+= value;
        }
        System.out.println(value);
    }
}
