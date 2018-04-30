package com.dd.autoCreate.common.util;

/**
 * Created by page on 2017/6/13.
 */
public class DB2ClassUtil {
    public static String a_b2aB(String str){
        if(str.contains("_")){
            String[] temp=str.split("_");
            for(int i=1;i<temp.length;i++){
                temp[0]+=temp[i].substring(0,1).toUpperCase()+temp[i].substring(1);
            }
            return temp[0].substring(0,1).toLowerCase()+temp[0].substring(1);
        }
        return str;
    }

    public static String a_b2AB(String str) {
        StringBuilder sb=new StringBuilder();
        String[] temp = str.split("_");
        for (int i = 0; i < temp.length; i++) {
            sb.append(temp[i].substring(0, 1).toUpperCase() + temp[i].substring(1));
        }
        return sb.toString();
    }
 
}
