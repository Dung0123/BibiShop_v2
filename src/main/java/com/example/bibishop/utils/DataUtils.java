package com.example.bibishop.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DataUtils {
    public static String safeToString(Object obj1) {
        return safeToString(obj1, "");
    }

    /**
     * @param obj1 Object
     * @return String
     */
    public static String safeToString(Object obj1, String defaultValue) {
        if (obj1 == null || obj1.toString().isEmpty()) {
            return defaultValue;
        }

        return obj1.toString();
    }

    public static Long safeToLong(Object obj1, Long defaultValue) {
        Long result = defaultValue;
        if (null != obj1) {
            if (obj1 instanceof BigDecimal) {
                return ((BigDecimal) obj1).longValue();
            }
            if (obj1 instanceof BigInteger) {
                return ((BigInteger) obj1).longValue();
            }

            try {
                result = Long.parseLong(obj1.toString());
            } catch (Exception e) {
            }
        }
        return result;
    }
    public static boolean isNull(Object obj) {
        return obj == null;
    }
    public static Long safeToLong(Object obj1) {
        return safeToLong(obj1, 0L);
    }
    public static Double parseToDouble(Object obj) {
        if (isNull(obj)) {
            return null;
        }
        return Double.parseDouble(parseToString(obj));
    }

    public static BigInteger parseToBigInteger(Object obj) {
        if (isNull(obj)) {
            return null;
        }
        return new BigInteger(parseToString(obj));
    }
    public static String parseToString(Object obj) {
        if (isNull(obj)) {
            return null;
        }
        return String.valueOf(obj);
    }

    public static String escapeSql(String value) {
        value = value.replace("_", "\\_");
        value = value.replace("%", "\\%");
        return value;
    }
}
