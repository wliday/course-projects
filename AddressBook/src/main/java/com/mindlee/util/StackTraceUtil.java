package com.mindlee.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * User: wei
 * Date: 9/22/15 3:06 PM
 */

public class StackTraceUtil {
    /**
     * output information in exception.
     */
    public static String getStackTrace(Throwable exception) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            return sw.toString();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}

