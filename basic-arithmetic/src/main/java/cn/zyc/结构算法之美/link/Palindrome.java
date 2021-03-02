package cn.zyc.结构算法之美.link;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * dsc: Palindrome 回文验证
 * date: 2021/3/2 13:38
 * author: zyc
 */
public class Palindrome {
    public static void main(String[] args) {
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));

        System.out.println(isPalindromeNotReg("A man, a plan, a canal: Panama"));
    }


    public static boolean isPalindrome(String s) {
        if(s==null||s==""){return false;}

        String re = "[a-z0-9]";
        Pattern compile = Pattern.compile(re);
        char[] chars = s.toLowerCase().toCharArray();
        int count  = chars.length;
        for (int i = 0; i < chars.length; i++) {
            String aChar = chars[i]+"";
            Matcher matcher = compile.matcher(aChar);
            if(!matcher.find()){
                for (int i1 = i; i1 < chars.length-1; i1++) {
                    chars[i1] = chars[i1+1];

                }
                i--;
                count--;
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if(chars[i] !=chars[count-1]){
                return false;
            }
            count--;
            if(i>=count){
                break;
            }
        }
        return true;
    }

    public static boolean isPalindromeNotReg(String s) {
        if(s == null || s == ""){return false;}

        char[] chars = s.toLowerCase().toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if(Character.isLetterOrDigit(aChar)){
                sb.append(aChar);
            }
        }
        return sb.toString().equals(sb.reverse().toString());
    }

}
