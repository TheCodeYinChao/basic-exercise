package cn.zyc.biginteger;


import java.math.BigInteger;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * dsc: BigInteger
 * date: 2021/1/22 16:28
 * author: zyc
 */
public class BigIntegerDemo {

    /**
     * 根据 16进制求十进制
     * @param args
     */
    public static void main(String[] args) {

        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        String line ;
        BigInteger base = new BigInteger("16"); //2^4
        try {
            while((line = bufr.readLine()) != null){
                line = line.substring(2);
                //int result = Integer.parseInt(line, 16);
                BigInteger result = new BigInteger("0");
                for(int i = 0; i < line.length(); i++){
                    char ch = line.charAt(line.length()-1-i);
                    if(ch >= 'A' && ch <= 'F'){
                        BigInteger tmp = base.pow(i).multiply(new BigInteger(Integer.toString((ch - 'A' + 10))));
                        result = result.add(tmp);
                    }else{
                        BigInteger tmp = base.pow(i).multiply(new BigInteger(Character.toString(ch)));
                        result = result.add(tmp);
                    }
                }
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
