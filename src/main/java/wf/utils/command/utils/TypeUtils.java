package wf.utils.command.utils;

import java.util.regex.Pattern;

public class TypeUtils {

    private static final Pattern INTEGER_REGEX = Pattern.compile("-?\\d+");

    private static final Pattern DOUBLE_REGEX = Pattern.compile("-?\\d+(\\.\\d+)?");


    public static boolean isDouble(String arg) {
        if(arg.isEmpty()) return false;
        return DOUBLE_REGEX.matcher(arg).matches();
    }

    public static boolean isInteger(String arg) {
        if(arg.isEmpty()) return false;
        return INTEGER_REGEX.matcher(arg).matches();
    }


    public static boolean isBoolean(String arg){
        if(arg.isEmpty()) return false;
        return arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("false");
    }





}
