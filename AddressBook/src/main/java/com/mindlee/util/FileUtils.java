package com.mindlee.util;

/**
 * User: wei
 * Date: 9/18/15 9:41 PM
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {

    public static List<String> read(File file) {

        List<String> strList = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader((new FileReader(file)));
            String line = null;
            while ((line = br.readLine()) != null) {
                strList.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println(StackTraceUtil.getStackTrace(e));
        } finally {
            try {
                closeIO(br);
            } catch (Exception e) {
                System.out.println(StackTraceUtil.getStackTrace(e));
            }
        }
        return strList;
    }

    public static void closeIO(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (Exception e) {
                System.out.println(StackTraceUtil.getStackTrace(e));
            }
        }
    }

}
