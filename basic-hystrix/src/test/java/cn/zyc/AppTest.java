package cn.zyc;


import org.junit.Test;

import java.util.concurrent.Future;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        new CommandDemo("hystrix-order").execute();

        Future queue = new CommandDemo("hystrix-order").queue();
    }
}
