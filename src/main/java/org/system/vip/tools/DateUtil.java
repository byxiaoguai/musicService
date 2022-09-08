package org.system.vip.tools;


import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author 刘政
 * @date 2020-10-28 16:13
 */
public class DateUtil {
    /**
     * 时间转换为yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return YYYY_MM_DD_MM_HH_SS.format(date);
    }

    public static String dateToString1(Date date) {
        SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
        if (null == date) {
            return "";
        }
        return YYYY_MM_DD.format(date);
    }

    public static String dateToString2(Date date) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return YYYY_MM_DD_MM_HH_SS.format(date);
    }

    public static String dateToString3(Date date) {
        SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyyMMdd");
        if (null == date) {
            return "";
        }
        return YYYY_MM_DD.format(date);
    }

    // 获取系统时间并返回时间格式
    public static Date currentDate() {
        try {
            SimpleDateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDates = YYYY_MM_DD_MM_HH_SS.format(new Date());
            return YYYY_MM_DD_MM_HH_SS.parse(currentDates);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = YYYY_MM_DD.parse(YYYY_MM_DD.format(smdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            bdate = YYYY_MM_DD.parse(YYYY_MM_DD.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个时间之间相差的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffDays(Date startDate, Date endDate) {
        long days = 0;
        long start = startDate.getTime();
        long end = endDate.getTime();
        // 一天的毫秒数1000 * 60 * 60 * 24=86400000
        days = (end - start) / 86400000;
        return days;
    }

    /**
     * 计算两个时间之间相差的天数，不满一天按一天算
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffDays2(Date startDate, Date endDate) {
        long start = startDate.getTime();
        long end = endDate.getTime();
        // 一天的毫秒数1000 * 60 * 60 * 24=86400000
        double days = Math.ceil((end - start) / 86400000d); // 不满一天按一天算
        return Long.parseLong(String.format("%.0f", days));
    }

    /**
     * 计算两个时间之间相差的分钟数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffMinutes(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / 60000;
    }

    /**
     * 日期加上月数的时间
     *
     * @param date
     * @param month
     * @return
     */
    public static Date dateAddMonth(Date date, int month) {
        return add(date, Calendar.MONTH, month);
    }

    /**
     * 日期加上天数的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static Date dateAddDay(Date date, int day) {
        return add(date, Calendar.DAY_OF_YEAR, day);
    }

    /**
     * 日期加上年数的时间
     *
     * @param date
     * @param year
     * @return
     */
    public static Date dateAddYear(Date date, int year) {
        return add(date, Calendar.YEAR, year);
    }

    /**
     * 计算剩余时间 (多少天多少时多少分)
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String remainDateToString(Date startDate, Date endDate) {
        StringBuilder result = new StringBuilder();
        if (endDate == null) {
            return "过期";
        }
        long times = endDate.getTime() - startDate.getTime();
        if (times < -1) {
            result.append("过期");
        } else {
            long temp = 1000 * 60 * 60 * 24;
            // 天数
            long d = times / temp;

            // 小时数
            times %= temp;
            temp /= 24;
            long m = times / temp;
            // 分钟数
            times %= temp;
            temp /= 60;
            long s = times / temp;

            result.append(d);
            result.append("天");
            result.append(m);
            result.append("小时");
            result.append(s);
            result.append("分");
        }
        return result.toString();
    }

    private static Date add(Date date, int type, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, value);
        return calendar.getTime();
    }

    /**
     * @MethodName: getLinkUrl
     * @Param: DateUtil flag ： true 转换 false 不转换
     * @Author: gang.lv
     * @Date: 2013-5-8 下午02:52:44
     * @Return:
     * @Descb:
     * @Throws:
     */
    public static String getLinkUrl(boolean flag, String content, String id) {
        if (flag) {
            content = "<a href='finance.do?id=" + id + "'>" + content + "</a>";
        }
        return content;
    }

    /**
     * 时间转换为时间戳
     *
     * @param format
     * @param date
     * @return
     * @throws ParseException
     */
    public static long getTimeCur(String format, String date) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(sf.format(date)).getTime();
    }

    /**
     * 时间转换为时间戳
     *
     * @param format
     * @param date
     * @return
     * @throws ParseException
     */
    public static long getTimeCur(String format, Date date) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(sf.format(date)).getTime();
    }

    /**
     * 将时间戳转为字符串
     *
     * @param cc_time
     * @return
     */
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = YYYY_MM_DD_MM_HH_SS.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    public static int getAge(Date birthday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        Calendar nowDate = Calendar.getInstance();

        return nowDate.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
    }

    /**
     * 当前时间减去分钟数得到的时间
     *
     * @param minutes
     * @return
     * @throws ParseException
     */
    public static String getDateMinusMinutes(int minutes) throws ParseException {
        SimpleDateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowTime = new Date();
        String datetest = YYYY_MM_DD_MM_HH_SS.format(nowTime);
        Date date = YYYY_MM_DD_MM_HH_SS.parse(datetest);
        long Time1 = (date.getTime() / 1000) - 60 * minutes;// 减去30分
        date.setTime(Time1 * 1000);
        return YYYY_MM_DD_MM_HH_SS.format(date);
    }

    /**
     * 和当前时间比较是否在给定的时长内
     *
     * @param validTime 给定的时间
     * @param time      给定的时长（s）
     * @return true 有效 false 无效
     */
    public static boolean inValidTime(Date validTime, int time) {
        long currTime = System.currentTimeMillis();
        long valid = validTime.getTime();

        return ((currTime - valid) < time * 1000);
    }

    /**
     * 获取年
     *
     * @param time
     * @return
     */
    public static int getYear(Date time) {
        if (time == null) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     *
     * @param time
     * @return
     */
    public static int getMonth(Date time) {
        if (time == null) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     *
     * @param time
     * @return
     */
    public static int getDay(Date time) {
        if (time == null) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取年
     *
     * @return
     */
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     *
     * @return
     */
    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     *
     * @return
     */
    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    public static String getYMD() {
        return getYear() + "" + getMonth() + "" + getDay();
    }

    /**
     * 当前时间减去天数得到的时间
     *
     * @param day
     * @return
     */
    public static Date getDateMinusDay(int day) {
        Date time = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - day);
        return c.getTime();
    }

    /**
     * 得到当前时间距2013-11-01 00:00:00的小时数
     *
     * @return
     * @throws ParseException
     */
    public long getHours() {
        SimpleDateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = YYYY_MM_DD_MM_HH_SS.parse("2013-11-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millisecond = System.currentTimeMillis() - date.getTime();
        long temp = 1000 * 60 * 60;
        return millisecond / temp;
    }

    public static Date strToDate(String dateString) {
        if (StringUtils.isBlank(dateString)) {
            return new Date();
        }
        try {
            SimpleDateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return YYYY_MM_DD_MM_HH_SS.parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Date dateToStartDate(Date date) {
        if (null == date) {
            return null;
        }
        String yymmdd = dateToString1(date) + " 00:00:00";
        return strToDate(yymmdd);
    }

    public static Date dateToEndDate(Date date) {
        if (null == date) {
            return null;
        }
        String yymmdd = dateToString1(date) + " 23:59:59";
        return strToDate(yymmdd);
    }

    public static Date strToStartDate(String date) {
        if (StringUtils.isBlank(date)) {
            return new Date();
        }
        return strToDate(date + " 00:00:00");
    }

    public static Date strToEndDate(String date) {
        if (StringUtils.isBlank(date)) {
            return new Date();
        }
        return strToDate(date + " 23:59:59");
    }

    /**
     * 获取当前月第一天0晨时间
     *
     * @return
     */
    public static Date firstDayOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dateToStartDate(calendar.getTime());
    }

    /**
     * 获取当前月最后一天23:59:59时间
     *
     * @return
     */
    public static Date lastDayOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateToEndDate(calendar.getTime());
    }

    /**
     * 获取上个月第一天0晨时间
     *
     * @return
     */
    public static Date firstDayOfPerMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return dateToStartDate(cal.getTime());
    }

    /**
     * 获取上个月最后一天23:59:59时间
     *
     * @return
     */
    public static Date lastDayOfPerMonth() {
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return dateToEndDate(cale.getTime());
    }


    /**
     * 把对象转换成日期<BR/>
     * obj为空时返回NULL
     *
     * @param obj
     * @param format 日期格式（默认为yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static Date toDate(String obj, String format) throws ParseException {
        if(StringUtils.isEmpty(obj)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(obj);
    }

    public static String activityCodeId() {
        return new SimpleDateFormat("yyyyMMddHH").format(new Date()) + UUID.randomUUID().toString().substring(0, 4);
    }

    /**
     * 时间戳转Date 时间
     *
     * @param time
     * @return
     */
    public static Date getDate(Long time) throws Exception {


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String d = format.format(time);
        Date date = format.parse(d);


        return date;


    }
}
