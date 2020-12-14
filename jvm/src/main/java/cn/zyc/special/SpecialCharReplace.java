package cn.zyc.special;

/**
 * dsc: SpecialCharReplace
 * 特殊字符 \\ $的替换需要注意下
 * date: 2020/12/14 12:24
 * author: zyc
 */
public class SpecialCharReplace {
    public static void main(String[] args) {

        String replace = "java".replace("java", "$");
        System.out.println(replace);
        String replace1 = "java".replaceAll("a", "$");
        System.out.println(replace1);


    }
}
