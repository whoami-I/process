package com.example.processcommunicate.log;

public class Log {
    private static int VERBOSE = 0;
    private static int DEBUG = 1;
    private static int INFO = 2;
    private static int WARN = 3;
    private static int ERROR = 4;

    private static int DEBUG_LEVEL = 5;


    public static void v(String TAG, String msg) {
        if (DEBUG_LEVEL > VERBOSE) {
            android.util.Log.v(TAG, msg);
        }
    }

    public static void d(String TAG, String msg) {
        if (DEBUG_LEVEL > DEBUG) {
            android.util.Log.d(TAG, msg);
        }
    }

    public static void i(String TAG, String msg) {
        if (DEBUG_LEVEL > INFO) {
            android.util.Log.i(TAG, msg);
        }
    }

    public static void w(String TAG, String msg) {
        if (DEBUG_LEVEL > WARN) {
            android.util.Log.w(TAG, msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (DEBUG_LEVEL > ERROR) {
            android.util.Log.e(TAG, msg);
        }
    }


}
