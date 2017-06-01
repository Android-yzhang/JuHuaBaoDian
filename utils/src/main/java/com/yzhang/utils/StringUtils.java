package com.yzhang.utils;

/**
 * Created by yzhang on 2017/2/9.
 */

public final class StringUtils {
    /**
     * @param input 需要判断的字符串
     * @return 是否包含除空格回车以外的字符
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
}
