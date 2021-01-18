package cn.zyc.时间;

import org.junit.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/**
 * dsc: TimeZoneDemo
 * date: 2021/1/18 10:58
 * author: zyc
 */
public class TimeZoneDemo {

    /**
     * 找地区标识
     */
    @Test
    public void allZone(){
        String[] availableIDs = TimeZone.getAvailableIDs();
        System.out.println("可用zoneId总数：" + availableIDs.length);
        for (String zoneId : availableIDs) {
            System.out.println(zoneId);
        }
    }

    /**
     * 根据提取标识获取时间
     */
    @Test
    public void test2() {
        String patternStr = "yyyy-MM-dd HH:mm:ss";
        // 北京时间（new出来就是默认时区的时间）
        Date bjDate = new Date();

        // 得到纽约的时区
        TimeZone newYorkTimeZone = TimeZone.getTimeZone("America/New_York");//**********
        // 根据此时区 将北京时间转换为纽约的Date
        DateFormat newYorkDateFormat = new SimpleDateFormat(patternStr);
        newYorkDateFormat.setTimeZone(newYorkTimeZone);
        System.out.println("这是北京时间：" + new SimpleDateFormat(patternStr).format(bjDate));
        System.out.println("这是纽约时间：" + newYorkDateFormat.format(bjDate));
    }

    /**
     * 针对没有标识的用时区来获取标识
     */
    @Test
    public void test4() {
        System.out.println(TimeZone.getTimeZone("GMT+08:00").getID());
        System.out.println(TimeZone.getDefault().getID());

        // 纽约时间
        System.out.println(TimeZone.getTimeZone("GMT-05:00").getID());
        System.out.println(TimeZone.getTimeZone("America/New_York").getID());
    }
    /***
     * 倘若时区不同，那么势必影响到程序的运行结果，很容易带来计算逻辑的错误，很可能就乱套了。
     * Java让我们有多种方式可以手动设置/修改默认时区：
     *
     * API方式：强制将时区设为北京时区TimeZone.setDefault(TimeZone.getDefault().getTimeZone("GMT+8"));
     * JVM参数方式：-Duser.timezone=GMT+8
     * 运维设置方式：将操作系统主机时区设置为北京时区，这是推荐方式，可以完全对开发者无感，也方便了运维统一管理
     * 据我了解，很多公司在阿里云、腾讯云、国内外的云主机上部署应用时，全部都是采用运维设置统一时区：中国时区，
     * 这种方式来管理的，这样对程序来说就消除了默认时区不一致的问题，对开发者友好。
     */

    /** ==================================================*/

    /**
     *  总得来说升级jdk版本，不关心夏令时，只关注时区就可以啦
     *
     * @throws ParseException
     */
    @Test
    public void test5() throws ParseException {
        String patterStr = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(patterStr);

        String birthdayStr = "1988-09-11";
        // 字符串 -> Date -> 字符串
        Date birthday = dateFormat.parse(birthdayStr);
        long birthdayTimestamp = birthday.getTime();
        System.out.println("老王的生日是：" + birthday);
        System.out.println("老王的生日的时间戳是：" + birthdayTimestamp);

        System.out.println("==============程序经过一番周转，我的同时 方法入参传来了生日的时间戳=============");
        // 字符串 -> Date -> 时间戳 -> Date -> 字符串
        birthday = new Date(birthdayTimestamp);
        System.out.println("老王的生日是：" + birthday);
        System.out.println("老王的生日的时间戳是：" + dateFormat.format(birthday));
    }

    /**
     * 确切的说：Date对象里存的是自格林威治时间（ GMT）1970年1月1日0点至Date所表示时刻所经过的毫秒数，是个数值。
     * 与时区无关，下面的结果毫秒数都是一样的
     */
    @Test
    public void test6() {
        String patterStr = "yyyy-MM-dd HH:mm:ss";
        Date currDate = new Date(System.currentTimeMillis());

        // 北京时区
        DateFormat bjDateFormat = new SimpleDateFormat(patterStr);
        bjDateFormat.setTimeZone(TimeZone.getDefault());
        // 纽约时区
        DateFormat newYorkDateFormat = new SimpleDateFormat(patterStr);
        newYorkDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        // 伦敦时区
        DateFormat londonDateFormat = new SimpleDateFormat(patterStr);
        londonDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));

        System.out.println("毫秒数:" + currDate.getTime() + ", 北京本地时间:" + bjDateFormat.format(currDate));
        System.out.println("毫秒数:" + currDate.getTime() + ", 纽约本地时间:" + newYorkDateFormat.format(currDate));
        System.out.println("毫秒数:" + currDate.getTime() + ", 伦敦本地时间:" + londonDateFormat.format(currDate));
    }

    /**
     * 这里默认契约是北京东八区 所有不会出问题
     * 但是不能给海外用户使用，因为时区是不一样的
     * @throws ParseException
     */

    @Test
    public void test7() throws ParseException {
        String patterStr = "yyyy-MM-dd HH:mm:ss";

        // 模拟请求参数的时间字符串
        String dateStrParam = "2020-01-15 18:00:00";

        // 模拟服务端对此服务换转换为Date类型
        DateFormat dateFormat = new SimpleDateFormat(patterStr);
        System.out.println("格式化器用的时区是：" + dateFormat.getTimeZone().getID());
        Date date = dateFormat.parse(dateStrParam);
        System.out.println(date);
    }

    /**
     * 注意看结果
     *↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓中文地区模式↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     * ====================Date->String====================
     * 公元 公元 公元 星期一 星期一 星期一 上午 上午 上午
     * ====================String->Date====================
     * Sat Jan 03 12:00:00 CST 1970
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓英文地区模式↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     * ====================Date->String====================
     * AD AD AD Mon Mon Monday AM AM AM
     * ====================String->Date====================
     * Sun Jan 01 00:00:00 CST 1970
     * @throws ParseException
     */
    @Test
    public void test9() throws ParseException {
        String patternStr = "G GG GGGGG E EE EEEEE a aa aaaaa";
        Date currDate = new Date();

        System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓中文地区模式↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        System.out.println("====================Date->String====================");
        DateFormat dateFormat = new SimpleDateFormat(patternStr, Locale.CHINA);
        System.out.println(dateFormat.format(currDate));

        System.out.println("====================String->Date====================");
        String dateStrParam = "公元 公元 公元 星期六 星期六 星期六 下午 下午 下午";
        System.out.println(dateFormat.parse(dateStrParam));

        System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓英文地区模式↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        System.out.println("====================Date->String====================");
        dateFormat = new SimpleDateFormat(patternStr, Locale.US);
        System.out.println(dateFormat.format(currDate));

        System.out.println("====================String->Date====================");
        dateStrParam = "AD ad bC Sat SatUrday sunDay PM PM Am";
        System.out.println(dateFormat.parse(dateStrParam));
    }



    //--------------------上面是java的时间  下面是 JSR 310类型------------------------------

    /**
     *  java 时间的缺点
     *     定义并不一致，在java.util和java.sql包中竟然都有Date类，而且呢对它进行格式化/解析类竟然又跑到java.text去了，精神分裂啊
     *     java.util.Date等类在建模日期的设计上行为不一致，缺陷明显。包括易变性、糟糕的偏移值、默认值、命名等等
     *     java.util.Date同时包含日期和时间，而其子类java.sql.Date却仅包含日期，这是什么神继承？
     *
     *
     *     注意包的不同以及显示的不同
     *     java.util.Date：Mon Jan 18 11:16:59 CST 2021//这里又表现日期又表现时间
     * java.sql.Date：2021-01-18 //这里只表示啦日期
     * java.sql.Time：11:16:59//这里又搞了个时间
     * java.sql.Timestamp：2021-01-18 11:16:59.557//这里也是日期时间
     */
    @Test
    public void test10() {
        long currMillis = System.currentTimeMillis();

        java.util.Date date = new Date(currMillis);
        java.sql.Date sqlDate = new java.sql.Date(currMillis);
        java.sql.Time time = new Time(currMillis);
        java.sql.Timestamp timestamp = new Timestamp(currMillis);

        System.out.println("java.util.Date：" + date);
        System.out.println("java.sql.Date：" + sqlDate);
        System.out.println("java.sql.Time：" + time);
        System.out.println("java.sql.Timestamp：" + timestamp);
    }

    //jsr310 java.time这个包内
    /**
     * UTC 这个没有地理时区含义，就是基准时间  时区就是在utc上进行加减时间的操作
     * GMT
     * UTC偏移量仅仅记录了偏移的小时分钟而已，除此之外无任何其它信息。举个例子：+08:00的意思是比UTC时间早8小时，没有地理/时区含义，相应的-03:30代表的意思仅仅是比UTC时间晚3个半小时
     * 时区是特定于地区而言的，它和地理上的地区（包括规则）强绑定在一起。比如整个中国都叫东八区，纽约在西五区等等
     */

    @Test
    public void testjsr1() {
        // JDK 1.8之前做法
        System.out.println(TimeZone.getDefault());
        // JDK 1.8之后做法
        System.out.println(ZoneId.systemDefault());
        /**
         * 输出：
         *     Asia/Shanghai
         *     sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=29,lastRule=null]
         */
    }

    @Test
    public void testjsr2() {
        System.out.println(ZoneId.of("Asia/Shanghai"));


        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        for (String availableZoneId : availableZoneIds) {
            System.out.println(availableZoneId);
        }

        // 报错：java.time.zone.ZoneRulesException: Unknown time-zone ID: Asia/xxx
        System.out.println(ZoneId.of("Asia/xxx"));
    }

    //根据偏移量得到一个ZoneId：
    @Test
    public void testjsr4() {
        ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.of("+8"));
        System.out.println(zoneId);
        // 必须是大写的Z
        zoneId = ZoneId.ofOffset("UTC", ZoneOffset.of("Z"));
        System.out.println(zoneId);
        /**
         *   输出：
         *     UTC+08:00
         *     UTC
         */
    }



    @Test
    public void testjsr7() {
        System.out.println(ZoneOffset.ofHours(8));
        System.out.println(ZoneOffset.ofHoursMinutes(8, 8));
        System.out.println(ZoneOffset.ofHoursMinutesSeconds(8, 8, 8));

        System.out.println(ZoneOffset.ofHours(-5));

        // 指定一个精确的秒数  获取实例（有时候也很有用处）
        System.out.println(ZoneOffset.ofTotalSeconds(8 * 60 * 60));
        /**
         *
         // 输出：
         +08:00
         +08:08
         +08:08:08
         -05:00
         +08:00
         */
    }

    @Test
    public void test8() {
        // 本地日期/时间
        System.out.println("================本地时间================");
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());

        // 时区时间
        System.out.println("================带时区的时间ZonedDateTime================");
        System.out.println(ZonedDateTime.now()); // 使用系统时区
        System.out.println(ZonedDateTime.now(ZoneId.of("America/New_York"))); // 自己指定时区
        System.out.println(ZonedDateTime.now(Clock.systemUTC())); // 自己指定时区
        System.out.println("================带时区的时间OffsetDateTime================");
        System.out.println(OffsetDateTime.now()); // 使用系统时区
        System.out.println(OffsetDateTime.now(ZoneId.of("America/New_York"))); // 自己指定时区
        System.out.println(OffsetDateTime.now(Clock.systemUTC())); // 自己指定时区
    }

    @Test
    public void test11() {
        String dateTimeStrParam = "2021-05-05T18:00";
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStrParam);
        System.out.println("解析后：" + localDateTime);
        /**
         *   输出：
         *     解析后：2021-05-05T18:00
         */
    }


}
