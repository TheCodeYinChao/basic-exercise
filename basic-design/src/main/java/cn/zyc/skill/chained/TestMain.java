package cn.zyc.skill.chained;

import java.util.HashMap;
import java.util.Map;

/**
 * User: zyc
 * Date: 2018-05-30
 * Time: 16:15
 * Version ：1.0
 * Description:
 */
public class TestMain {
    public static void main(String[] args) {

        String id = "1";
        String name = "lili";
        int age = 21;
        String memberLevel = "VIP";
        String otherInfo1 = "other Info1";
        String otherInfo2 = "other Info2";
        String otherInfo3 = "other Info3";


        MemberInfo memberInfo = new MemberInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("age", age);
        map.put("memberLevel", memberLevel);
        StringBuffer sb = new
                StringBuffer().append(otherInfo1).append(otherInfo2).append(otherInfo3);
        String otherInfo = new String(sb);
        memberInfo.setId(id);
        memberInfo.setName(name);
        memberInfo.setBaseInfo(map);
        memberInfo.setOtherInfo(otherInfo);


        MemberInfoChain memberInfoChain = new MemberInfoChain();
        memberInfoChain.setId(id)
                .setName(name)
                .setBaseInfo("age",age)
                .setBaseInfo("memberLevel",memberLevel)
                .setOtherInfo(otherInfo1)
                .setOtherInfo(otherInfo2)
                .setOtherInfo(otherInfo3);

        System.out.println(memberInfo);
        System.out.println(memberInfoChain);

    }
}
