package com.test.medicalsystem.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xuqiwei-Office on 2016/4/6.
 */
public class RegexTool {
    /**
     * 药剂量
     */
    public static String medicamentRex = "[0-9]*";
    public static boolean isRexMatche(String rexString, String normalString)
    {
        Pattern pattern = Pattern.compile(rexString);
        Matcher matcher = pattern.matcher(normalString);
        if(matcher.matches()) {
//            System.out.println("全部是数字");
            return true;
        }
        return false;

    }
}
