package cn.zyc.jcostumcomlper;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href ="https://blog.csdn.net/u010398771/article/details/90474813"></a>
 * dsc: CompileMain
 * date: 2020/12/7 16:31
 * author: zyc
 */
public class CompileMain {
    public static void main(String[] args) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, "F:\\demo\\Test.java");
        System.out.println(result == 0 ? "编译成功" : "编译失败");

//执行java 命令 , 空参数, 所在文件夹
        Process process = Runtime.getRuntime().exec("java Test",null,new File("F:\\demo\\"));


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
    }

}
