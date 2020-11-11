package cn.zyc.array;

import org.junit.Test;

/**
 * description: Stack  栈结构
 * 拓展思考  扩容  移除
 *
 * 练习 ：
 * 字符倒叙  abc  cba
 * 字符匹配判断 ：<abc[123]abc>这是符号相匹配的，如果是 <abc[123>abc] 那就是不匹配的。对于 12<a[b{c}]>，我们分析在栈中的数据：遇到匹配正确的就消除
 *
 * 中缀表达式转换为 后缀和前缀表达式
 * date: 2020/11/2 16:38 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Stack {
    @Test
    public void testStack(){
        CustomStack c = new CustomStack();

        c.push("aa");
        c.push("dd");

        System.out.println(c.pop());
        System.out.println(c.pop());

    }




    class CustomStack{
        int top;//顶层元素
        int max;//栈最大深度
        Object [] meta = new Object[16];


        // 压栈
        void push(Object e){
            meta[top] =e;
            top++;
        }

        //弹栈
        Object pop(){
            top--;
            Object o = meta[top];
            return o;
        }

    }

}
