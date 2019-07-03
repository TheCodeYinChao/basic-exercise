package cn.zyc.skill.chained;

import java.util.HashMap;
import java.util.Map;

/**
 * User: zyc
 * Date: 2018-05-30
 * Time: 16:13
 * Version ：1.0
 * Description:
 */
public class MemberInfoChain {
    private String id;
    private String name;
    private Map<String, Object> baseInfo;
    private String otherInfo;

    public MemberInfoChain setId(String id) {
        this.id = id;
        return this;
    }

    public MemberInfoChain setName(String name) {
        this.name = name;
        return this;
    }

    public MemberInfoChain setBaseInfo(String key, Object value) {
        if(this.baseInfo == null){
            this.baseInfo = new HashMap<String, Object>();
        }
        this.baseInfo.put(key,value);
        return this;
    }


    public MemberInfoChain setOtherInfo(String otherInfo) {
        this.otherInfo = new String(new StringBuffer().append(this.otherInfo).append(otherInfo));
        return this;
    }

    @Override
    public String toString() {
        return "MemberInfoChain{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", baseInfo=" + baseInfo +
                ", otherInfo='" + otherInfo + '\'' +
                '}';
    }
}
