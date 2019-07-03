package cn.zyc.skill.chained;

import java.util.Map;

/**
 * User: zyc
 * Date: 2018-05-30
 * Time: 16:14
 * Version ：1.0
 * Description:
 */
public class MemberInfo {
    private String id;
    private String name;
    private Map<String, Object> baseInfo;
    private String otherInfo;

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBaseInfo(Map<String, Object> baseInfo) {
        this.baseInfo = baseInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", baseInfo=" + baseInfo +
                ", otherInfo='" + otherInfo + '\'' +
                '}';
    }

}
