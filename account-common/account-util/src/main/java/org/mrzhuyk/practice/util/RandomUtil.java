package org.mrzhuyk.practice.util;


import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机工具类
 */
public class RandomUtil {
    public static final String NUMBER_CODES = "0123456789";
    public static final String UP_CASE_LETTERS = "ABCDEFGHIGKLMNOPQRSTUVMXYZ";
    public static final String LW_CASE_LETTERS = "abcdefghigklmnopqrstuvmxyz";
    
    
    
    /**
     * 根据字符源生产对应长度字符
     *
     * @param length 长度
     * @param sources 来源
     * @return 字符串
     */
    public static String randomChars(int length, String... sources) {
        if (sources == null || sources.length == 0) {
            sources = new String[]{NUMBER_CODES, UP_CASE_LETTERS, LW_CASE_LETTERS};
        }
        StringBuilder sb = new StringBuilder();
        for (String item : sources) {
            sb.append(item);
        }
        String sourceStr = sb.toString();
        StringBuilder result = new StringBuilder(length);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            result.append(sourceStr.charAt(random.nextInt(sourceStr.length() - 1)));
        }
        return result.toString();
    }


}
