package cn.zyc.regex;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * dsc: ExpreReg 正则表达式的表述https://www.runoob.com/java/java-regular-expressions.html
 * date: 2021/3/4 20:25
 * author: zyc
 */
public class ExpreReg {
    String a = "\\d";//规范导致要用俩\\

    // "this is text" 完全匹配
    // "this\s+is\s+text"   \s+ 匹配一个或多个空格

    // ^\d+(\.\d+)?  ^\d+ 以1个或多个数字开头 ()? 设置括号里面是可选的 \.匹配原生的.  \d+一个或多个数字

    //.*runoob.*  .*匹配多个字符

    //关于正则表达式的组
    public static void main( String[] args ){

        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

//        \\=\ java 中两个\\ 才等于 别的一个\   要想匹配原有的\  java  \\\  其他 \\
//        \\ 表示：我想要在正则表达式中插入一个普通的（字面上的）反斜杠，请不要给它任何特殊的意义。
//        在 Java 中，\\ 表示：我要插入一个正则表达式的反斜线，所以其后的字符具有特殊的意义。
//        而在 Java 中正则表达式中则需要有两个反斜杠才能被解析为其他语言中的转义作用。
// 也可以简单的理解在 Java 的正则表达式中，两个 \\ 代表其他语言中的一个 \，
// 这也就是为什么表示一位数字的正则表达式是 \\d，而表示一个普通的反斜杠是 \\\\。

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
            /**
             * Found value: This order was placed for QT3000! OK?
             * Found value: This order was placed for QT
             * Found value: 3000
             * Found value: ! OK?
             */
        } else {
            System.out.println("NO MATCH");
        }
    }

    //{n} n 是非负整数。正好匹配 n 次。例如，"o{2}"与"Bob"中的"o"不匹配，但与"food"中的两个"o"匹配。
//    {n,}    n 是非负整数。至少匹配 n 次。例如，"o{2,}"不匹配"Bob"中的"o"，而匹配"foooood"中的所有 o。"o{1,}"等效于"o+"。"o{0,}"等效于"o*"。
//   {n,m}  m 和 n 是非负整数，其中 n <= m。匹配至少 n 次，至多 m 次。例如，"o{1,3}"匹配"fooooood"中的头三个 o。'o{0,1}' 等效于 'o?'。注意：您不能将空格插入逗号和数字之间。
//    ? 当此字符紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，匹配模式是"非贪心的"。"非贪心的"模式匹配搜索到的、尽可能短的字符串，而默认的"贪心的"模式匹配搜索到的、尽可能长的字符串。例如，在字符串"oooo"中，"o+?"只匹配单个"o"，而"o+"匹配所有"o"。
}
