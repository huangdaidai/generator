package com.dd.autoCreate.common.util;

import java.util.Arrays;

/**
 * Created by page on 2017/6/13.
 */
public class DB2ClassUtil {
    public static String a_b2aB(String str) {
        StringBuilder sb = new StringBuilder();
        String[] temp = str.toLowerCase().split("_");
        Arrays.stream(temp).forEach((t)->sb.append(t.substring(0, 1).toLowerCase() + t.substring(1)));
        return sb.toString();
    }

    public static String a_b2AB(String str) {
        StringBuilder sb = new StringBuilder();
        String[] temp = str.toLowerCase().split("_");
        Arrays.stream(temp).forEach((t)->sb.append(t.substring(0, 1).toUpperCase() + t.substring(1)));
        return sb.toString();
    }

    public static String db2JavaType(String dbType) {
        if (dbType.toLowerCase().contains("int")) {
            return "Integer";
        } else if (dbType.toLowerCase().contains("char")) {
            return "String";
        } else if ("date".equalsIgnoreCase(dbType) || dbType.toLowerCase().contains("time")) {
            return "Date";
        } else if ("decimal".equalsIgnoreCase(dbType) || "number".equalsIgnoreCase(dbType)) {
            return "BigDecimal";
        } else if ("float".equalsIgnoreCase(dbType)) {
            return "Float";
        } else if ("double".equalsIgnoreCase(dbType)) {
            return "Double";
        } else if ("long".equalsIgnoreCase(dbType)) {
            return "Long";
        }
        return "暂时不支持该类型，请手动用正确类型全局替换本句话：" + dbType;
    }

    public static String db2UniqueType(String dbType) {
        if (dbType.toLowerCase().contains("int")) {
            return "java.lang.Integer";
        } else if (dbType.toLowerCase().contains("char")) {
            return "java.lang.String";
        } else if ("date".equalsIgnoreCase(dbType) || dbType.toLowerCase().contains("time")) {
            return "java.util.Date";
        } else if ("decimal".equalsIgnoreCase(dbType) || "number".equalsIgnoreCase(dbType)) {
            return "java.math.BigDecimal";
        } else if ("float".equalsIgnoreCase(dbType)) {
            return "java.lang.Float";
        } else if ("double".equalsIgnoreCase(dbType)) {
            return "java.lang.Double";
        } else if ("long".equalsIgnoreCase(dbType)) {
            return "java.lang.Long";
        }
        return "暂时不支持该类型，请手动用正确类型全局替换本句话：" + dbType;
    }
}
