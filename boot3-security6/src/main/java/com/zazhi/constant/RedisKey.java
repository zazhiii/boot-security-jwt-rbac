package com.zazhi.constant;

/**
 *
 * @author lixh
 * @since 2025/9/16 11:04
 */
public class RedisKey {
    public static final String LOGIN = "login:%s";

    public static String format(String format, Object... args) {
        return String.format(format, args);
    }
}
