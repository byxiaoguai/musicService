package org.system.vip.tools;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 刘政
 * @date 2020-10-28 16:22
 */
public class Detect {
    /**
     *
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -3754637454616848491L;

    public static final short INVALID_NUMBER_VALUE = 0;

    public static final String EMPTY_STRING = "";

    public static final String DELIMITER = ",";

    public static final String CONNECTORFLAG = "=";// 导入中使用

    public static final String REFERENCELIST_SUFFIX = "_LIST";// 导入中使用

    /**
     * 把Long类型的集合转化为Long类型的数组
     *
     * @param list
     * @return
     */
    public static long[] listToLongArray(List<Long> list) {
        if (notEmpty(list)) {
            long[] arr = new long[list.size()];
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == null) {
                    arr[i] = 0;
                } else {
                    arr[i] = Long.valueOf(String.valueOf(list.get(i)));
                }
            }
            return arr;
        }
        return null;
    }

    /**
     * 把参数转化为byte[]
     *
     * @param t
     * @return
     */
    public static <T extends Object> byte[] convertToByteArray(T t) {
        byte[] bytes = null;
        if (t != null) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(t);
                bytes = byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                    }
                }

                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return bytes;
    }

    /**
     * 把byte[]转化为对象
     *
     * @param bytes
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T convertToObject(byte[] bytes) {
        T t = null;
        if (bytes != null) {
            ByteArrayInputStream byteArrayInputputStream = null;
            ObjectInputStream objectInputStream = null;
            try {
                byteArrayInputputStream = new ByteArrayInputStream(bytes);
                objectInputStream = new ObjectInputStream(byteArrayInputputStream);

                t = (T) objectInputStream.readObject();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            } finally {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                    }
                }

                if (byteArrayInputputStream != null) {
                    try {
                        byteArrayInputputStream.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return t;
    }

    /**
     * 获得随机uuid
     *
     * @return
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    public static boolean bizIdEquals(long bizId1, long bizId2) {
        return bizId1 > 0 && bizId2 > 0 && bizId1 == bizId2;
    }

    /**
     * 判断字符串是否为空
     *
     * @param string
     * @return
     */
    public static boolean notEmpty(String string) {
        return null != string && !EMPTY_STRING.equals(string);
    }

    /**
     * 判断字符串是否为数字
     *
     * @param string
     * @return
     */
//    public static boolean isNumber(String string) {
//        if (notEmpty(string) && StringUtils.isNumeric(string)) {
//            return true;
//        }
//        return false;
//    }


    /**
     * 判断byte[]是否为空
     *
     * @param bytes
     * @return
     */
    public static boolean notEmpty(byte[] bytes) {
        return (null != bytes && 0 < bytes.length);
    }

    /**
     * 判断集合是否为空
     *
     * @param list
     * @return
     */
    public static boolean notEmpty(List<?> list) {
        return null != list && !list.isEmpty();
    }

    public static boolean notNullIsEmpty(List<?> list) {
        return null != list && list.isEmpty();
    }

    /**
     * 判断map是否为空
     *
     * @param map
     * @return
     */
    public static boolean notEmpty(Map<?, ?> map) {
        return null != map && !map.isEmpty();
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean notEmpty(Collection<?> collection) {
        return null != collection && !collection.isEmpty();
    }

    /*
     * public static boolean notEmpty(String[] array) { return null != array &&
     * array.length > 0; }
     */

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static boolean notEmpty(short[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static boolean notEmpty(int[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static boolean notEmpty(long[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static boolean notEmpty(String[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static <T extends Object> boolean notEmpty(T[] array) {
        return null != array && array.length > 0;
    }

    /**  */
    public static boolean isNegative(double value) {
        return value < 0;
    }

    public static boolean isPositive(double value) {
        return value > 0;
    }

    public static boolean isTrue(Boolean value) {
        return Boolean.TRUE.equals(value);
    }

    public static boolean isFalse(Boolean value) {
        return Boolean.FALSE.equals(value);
    }

    /**
     * 判断元素是否包含在数组中
     *
     * @param value
     * @param values
     * @return
     */
    public static boolean contains(long value, long[] values) {
        if (notEmpty(values)) {
            int length = values.length;
            for (int i = 0; i < length; i++) {
                if (values[i] == value) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断字符元素是否包含在数组中
     *
     * @param value
     * @param values
     * @return
     */
    public static boolean contains(String value, String[] values) {
        if (notEmpty(values)) {
            int length = values.length;
            for (int i = 0; i < length; i++) {
                if (values[i].equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**  */
    public static boolean containsAll(long[] values, long[] subValues) {
        if (!notEmpty(values) && !notEmpty(subValues)) {
            return true;
        }
        if (notEmpty(subValues)) {
            for (int i = 0; i < subValues.length; i++) {
                if (!contains(subValues[i], values)) {
                    return false;
                }
            }
            return true;
        } else if (notEmpty(values)) {
            return true;
        }
        return false;
    }

    /**
     * 判断元素是否包含在集合中
     *
     * @param one
     * @param list
     * @return
     */
    public static <E> boolean contains(E one, List<E> list) {
        if (notEmpty(list) && null != one) {
            for (E item : list) {
                if (one.equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 分割字符串为数组
     *
     * @param value
     * @param delimiter 分隔符
     * @return
     */
    public static double[] doubleArray(String value, String delimiter) {
        String[] values = StringUtils.split(value, delimiter);

        double[] doubleValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            doubleValues[i] = Double.parseDouble(values[i]);
        }
        return doubleValues;
    }

    public static short[] shortArray(String value) {
        return shortArray(value, DELIMITER);
    }

    /**
     * 分割字符串为数组
     *
     * @param value
     * @param delimiter 分隔符
     * @return
     */
    public static short[] shortArray(String value, char delimiter) {
        if (!notEmpty(value)) {
            return null;
        }
        String[] values = StringUtils.split(value, delimiter);

        short[] shortValues = new short[values.length];
        for (int i = 0; i < values.length; i++) {
            shortValues[i] = Short.parseShort(values[i]);
        }
        return shortValues;
    }

    /**
     * 分割字符串为数组
     *
     * @param value
     * @param delimiter 分隔符
     * @return
     */
    public static short[] shortArray(String value, String delimiter) {
        if (!notEmpty(value)) {
            return null;
        }
        String[] values = StringUtils.split(value, delimiter);

        short[] shortValues = new short[values.length];
        for (int i = 0; i < values.length; i++) {
            shortValues[i] = Short.parseShort(values[i]);
        }
        return shortValues;
    }

    public static long[] longArray(String value) {
        return longArray(value, DELIMITER);
    }

    /**
     * 分割字符串为数组
     *
     * @param value
     * @param delimiter 分隔符
     * @return
     */
    public static long[] longArray(String value, char delimiter) {
        if (!notEmpty(value)) {
            return null;
        }
        String[] values = StringUtils.split(value, delimiter);

        long[] longValues = new long[values.length];
        for (int i = 0; i < values.length; i++) {
            longValues[i] = Long.parseLong(values[i]);
        }
        return longValues;
    }

    /**
     * 分割字符串为数组
     *
     * @param value
     * @param delimiter 分隔符
     * @return
     */
    public static long[] longArray(String value, String delimiter) {
        if (!notEmpty(value)) {
            return null;
        }
        String[] values = StringUtils.split(value, delimiter);

        long[] longValues = new long[values.length];
        for (int i = 0; i < values.length; i++) {
            if (notEmpty(values[i])) {
                longValues[i] = Long.parseLong(values[i].trim());
            }
        }
        return longValues;
    }

    /**
     * 分割字符串为数组
     *
     * @param value
     * @param delimiter 分隔符
     * @return
     */
    public static List<String> stringList(String value, String delimiter) {
        if (!notEmpty(value)) {
            return null;
        }
        String[] values = StringUtils.split(value, delimiter);

        List<String> listValues = new ArrayList<String>();
        for (int i = 0; i < values.length; i++) {
            listValues.add(i, values[i]);
        }
        return listValues;
    }

    /**
     * join
     */
    public static String join(long[] values) {
        return join(values, DELIMITER);
    }

    /**
     * 把数组以"," 分割成字符串
     *
     * @param values
     * @return
     */
    public static String join(String[] values) {
        return StringUtils.join(values, DELIMITER);
    }

    /**
     * 把数组以delimiter分隔符分割成字符串
     *
     * @param values
     * @param delimiter
     * @return
     */
    public static String join(long[] values, String delimiter) {
        return StringUtils.join(ArrayUtils.toObject(values), delimiter);
    }

    /**
     * 把数组以"," 分割成字符串
     *
     * @param values
     * @return
     */
    public static String join(short[] values) {
        return join(values, DELIMITER);
    }

    /**
     * 把数组以delimiter分隔符分割成字符串
     *
     * @param values
     * @param delimiter
     * @return
     */
    public static String join(short[] values, String delimiter) {
        return StringUtils.join(ArrayUtils.toObject(values), delimiter);
    }

    /**
     * as
     */
    public static short asPrimitiveShort(Object value) {
        String stringValue = asString(value);
        if (notEmpty(asString(stringValue))) {
            if (NumberUtils.isNumber(stringValue)) {
                return Short.parseShort(stringValue);
            }
        }
        return INVALID_NUMBER_VALUE;
    }

    /**
     * 把对象转化为集合
     *
     * @param object
     * @return
     */
    public static List<?> asList(Object object) {
        if (object != null && object instanceof List<?>) {
            return (List<?>) object;
        }
        return null;
    }

    /**
     * 把对象转化为long类型
     *
     * @return
     */
    public static long asPrimitiveLong(Object value) {
        String stringValue = asString(value);
        if (notEmpty(stringValue)) {
            if (NumberUtils.isNumber(stringValue)) {
                return Long.parseLong(stringValue);
            }
        }
        return INVALID_NUMBER_VALUE;
    }

    /**
     * 把对象转化为int类型
     *
     * @param value
     * @return
     */
    public static int asPrimitiveInt(Object value) {
        String stringValue = asString(value);
        if (notEmpty(stringValue)) {
            if (NumberUtils.isNumber(stringValue)) {
                return Integer.parseInt(stringValue);
            }
        }
        return INVALID_NUMBER_VALUE;
    }

    /**
     * 把对象转化为double类型
     *
     * @return
     */
    public static double asPrimitiveDouble(Object value) {
        String stringValue = asString(value);
        if (notEmpty(asString(stringValue))) {
            if (NumberUtils.isNumber(stringValue)) {
                return Double.parseDouble(stringValue);
            }
        }
        return INVALID_NUMBER_VALUE;
    }

    /**
     * 把对象转化为String类型
     *
     * @param object
     * @return
     */
    public static String asString(Object object) {
        if (null != object) {
            return StringUtils.trim(String.valueOf(object));
        }
        return null;
    }

    /**
     * 把集合转化为数组
     *
     * @param list
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E> E[] asArray(List<E> list) {
        if (notEmpty(list)) {
            return (E[]) list.toArray();
        }
        return null;
    }

    /**
     * 判断2个字符串是否相等
     *
     * @param left
     * @param right
     * @return
     */
    public static boolean equal(String left, String right) {
        if (null == left && null == right) {
            return true;
        }
        if (null != left && null != right && left.equals(right)) {
            return true;
        }
        return false;
    }

    public static String trim(String string) {
        if (null == string) {
            return null;
        }

        return StringUtils.trim(string);
    }

    public static int getByteLength(String string) {
        if (StringUtils.isEmpty(string)) {
            return 0;
        }
        try {
            return string.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("GBK charset not supported", e);
        }
    }

    public static String trimString(String string, int byteLength) {
        try {
            if (notEmpty(string) && string.getBytes("GBK").length > byteLength) {
                byte[] bytes = string.getBytes("GBK");
                bytes = ArrayUtils.subarray(bytes, 0, byteLength);
                String s = new String(bytes, "GBK");
                return s;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("GBK charset not supported", e);
        }
        return string;
    }

    /**
     * escape
     */
    public static long[] escapeDuplication(long[] values) {
        if (Detect.notEmpty(values)) {
            Set<Long> set = new LinkedHashSet<Long>();
            set.addAll(Arrays.asList(ArrayUtils.toObject(values)));
            return ArrayUtils.toPrimitive((Long[]) set.toArray(new Long[0]));
        }
        return null;
    }

    public static <E> List<E> escapeEmpty(List<E> list) {
        if (notEmpty(list)) {
            return list;
        }
        return null;
    }

    public static String escapeVarchar(String value) {
        if (notEmpty(value) && value.length() > 2000) {
            // return value.substring(0, 3990);
            return value.substring(0, 1990);
        }
        return value;
    }

    public static short[] safeArray(short[] values) {
        if (null == values) {
            values = new short[0];
        }
        return values;
    }

    public static int[] safeArray(int[] values) {
        if (null == values) {
            values = new int[0];
        }
        return values;
    }

    public static long[] safeArray(long[] values) {
        if (null == values) {
            values = new long[0];
        }
        return values;
    }

    public static double[] safeArray(double[] values) {
        if (null == values) {
            values = new double[0];
        }
        return values;
    }

    public static <E> List<E> safeList(List<E> list) {
        if (null == list) {
            list = new ArrayList<E>();
        }
        return list;
    }

    /**
     * 获得集合的第一个元素
     *
     * @param list
     * @return
     */
    public static <E> E firstOne(List<E> list) {
        if (notEmpty(list)) {
            return list.get(0);
            // return list.iterator().next();
        }
        return null;
    }

    /**
     * 获得集合的最后一个元素
     *
     * @param list
     * @return
     */
    public static <E> E lastOne(List<E> list) {
        if (notEmpty(list)) {
            return list.get(list.size() - 1);
            // return list.iterator().next();
        }
        return null;
    }

    /**
     * 判断集合是否只有一个元素
     *
     * @param list
     * @return
     */
    public static boolean onlyOne(List<?> list) {
        if (notEmpty(list) && list.size() == 1) {
            return true;
        }
        return false;
    }

    public static <E> List<E> unmodifiableList(List<E> list) {
        if (notEmpty(list)) {
            return Collections.unmodifiableList(list);
        }
        return null;
    }

    /**
     * 判断value是否在2者之间
     *
     * @param value
     * @param floor
     * @param ceil
     * @return
     */
    public static boolean between(long value, long floor, long ceil) {
        if (value >= floor && value <= ceil) {
            return true;
        } else {
            return false;
        }
    }

    public static void notNull(Object obj, String message) {
        if (null == obj) {
            throw new IllegalArgumentException(message);
        }
    }

    public static String changeSpecialChar(String s) {
        s = s.replace("\\", "\\\\");
        s = s.replace("\r", "\\r");
        s = s.replace("\n", "\\n");
        s = s.replace("\"", "\\\"");
        s = s.replace("\'", "\\\'");
        return s;
    }

    /**
     * 将一个List按照固定的大小拆成很多个小的List
     *
     * @param listObj  需要拆分的List
     * @param groupNum 每个List的最大长度
     * @return 拆分后的List的集合
     */
    public static <T> List<List<T>> getSubList(List<T> listObj, int groupNum) {
        List<List<T>> resultList = new ArrayList<List<T>>();
        // 获取需要拆分的List个数
        int loopCount = (listObj.size() % groupNum == 0) ? (listObj.size() / groupNum)
                : ((listObj.size() / groupNum) + 1);
        // 开始拆分
        for (int i = 0; i < loopCount; i++) {
            // 子List的起始值
            int startNum = i * groupNum;
            // 子List的终止值
            int endNum = (i + 1) * groupNum;
            // 不能整除的时候最后一个List的终止值为原始List的最后一个
            if (i == loopCount - 1) {
                endNum = listObj.size();
            }
            // 拆分List
            List<T> listObjSub = listObj.subList(startNum, endNum);
            // 保存差分后的List
            resultList.add(listObjSub);
        }
        return resultList;

    }

    static Pattern p = Pattern.compile("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
    static Pattern tel = Pattern.compile("(\\d){8,13}?");

    /**
     * 判断手机号码格式 或者座机号码 8 -11位
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (StringUtils.isBlank(mobiles)) {
            return false;
        }
        Matcher m = tel.matcher(mobiles);
        return  m.matches();

    }

    /**
     * 把对象转换成Float<br />
     * 如果对象为null返回null
     *
     * @param obj
     * @return
     */
    public static Float toFloatNull(Object obj) {
        Float value = null;
        String str = toString(obj);
        if (StringUtils.isNotBlank(str)) {
            value = Float.parseFloat(toString(obj));
        }
        return value;
    }


    public static String toString(Object obj) {
        try {
            if (null != obj) {
                return String.valueOf(obj).trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        // System.out.println(longArray("1", "."));

        // boolean aa = bizIdEquals(23, 23);
        System.out.println(Detect.getUUID().substring(0, 16));

        // List<long[]> longValueGroup = grouping(new long[] { 1, 2, 3, 4, 5, 6,
        // 7, 8, 9, 10 }, 5);
        // if (null != longValueGroup) {
        // for (int i = 0; i < longValueGroup.size(); i++) {
        // long[] longValue = longValueGroup.get(i);
        //
        // System.out.println(longValue.length + ": " + join(longValue));
        // }
        // }
        //
        // System.out.println(JsonUtil.marshal(escapeDuplication(new long[] { 1,
        // 2, 2, 5, 2, 2, 1, 2, 3, 4, 1, 2, 3 })));

//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("ids", "[43011]");
//        Object obj = map.get("ids");
//        List<Long> ids = (List<Long>) obj;
//        System.out.println(ids);
    }
}
