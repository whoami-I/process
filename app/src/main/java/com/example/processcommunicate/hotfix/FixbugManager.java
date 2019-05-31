package com.example.processcommunicate.hotfix;

import android.content.Context;
import android.os.Environment;

import com.example.processcommunicate.log.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

public class FixbugManager {
    private static final String TAG = "FixbugManager";
    private static final String DEX_SUFFIX = ".dex";
    private static final String APK_SUFFIX = ".apk";
    private static final String JAR_SUFFIX = ".jar";
    private static final String ZIP_SUFFIX = ".zip";

    private static final String OPTIMIZEDDIRNAME = "odex";
    private HashSet<File> fixedFiles = new HashSet<>();

    Context mContext;
    String fixedDexDirPath;

    public FixbugManager(Context context, String fixedDexDirPath) {
        this.fixedDexDirPath = fixedDexDirPath;
        this.mContext = context;
    }

    public void fixBug() {
        fixedFiles.clear();

        File fixedDexDir = new File(fixedDexDirPath);
        if (!fixedDexDir.exists() || !fixedDexDir.isDirectory()) {
            Log.e(TAG, fixedDexDirPath + " must exist and is a directory");
            return;
        }
        File[] files = fixedDexDir.listFiles();
        for (File file : files) {
            String filePath = file.getAbsolutePath();
            if (filePath.endsWith(DEX_SUFFIX)
                    || filePath.endsWith(APK_SUFFIX)
                    || filePath.endsWith(JAR_SUFFIX)
                    || filePath.endsWith(ZIP_SUFFIX)) {
                //符合条件
                fixedFiles.add(file);
            }
        }
        if (!fixedFiles.isEmpty()) {
            fixBugWithFiles();
        }

    }

    private void fixBugWithFiles() {
        ClassLoader cl = mContext.getClassLoader();
        Log.e("TAG", "cl --> " + cl);
        File optimizedDIR = new File(mContext.getDir(OPTIMIZEDDIRNAME, Context.MODE_PRIVATE).getAbsolutePath());
        if (!optimizedDIR.exists()) {
            optimizedDIR.mkdir();
        }
        for (File fixedDex : fixedFiles) {
            DexClassLoader dexClassLoader = new DexClassLoader(fixedDex.getAbsolutePath(),
                    optimizedDIR.getAbsolutePath(), null, cl);
            Object[] fixedDexElements = getDexElements(dexClassLoader);
            Object[] bugDexElements = getDexElements(cl);
            bugDexElements = combineArray(fixedDexElements, bugDexElements);
            inject(cl, bugDexElements);
        }

    }



    private void inject(ClassLoader cl, Object injected) {

        try {
            Field pathListField = null;
            Object[] dexElements = null;
            pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
            if (!pathListField.isAccessible()) pathListField.setAccessible(true);
            Object pathList = pathListField.get(cl);

            //3、找到DexPathList的Element[] dexElements
            Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
            if (!dexElementsField.isAccessible()) dexElementsField.setAccessible(true);
            dexElementsField.set(pathList, injected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object[] combineArray(Object[] left, Object[] right) {

        if (left == null || right == null) {
            throw new IllegalArgumentException("left or right can not be null");
        }

        if (!left.getClass().equals(right.getClass())) {
            throw new IllegalArgumentException("left.class must be same with right.class");
        }

        int rightLength = right.length;
        int leftLength = left.length;
        Class<?> leftClass = left[0].getClass();


        Object[] newArray = (Object[]) Array.newInstance(leftClass, leftLength + rightLength);
        for (int i = 0; i < leftLength; i++) {
            newArray[i] = left[i];
        }
        for (int i = 0; i < rightLength; i++) {
            newArray[i + leftLength] = right[i];
        }
        return newArray;
    }


    private Object[] getDexElements(ClassLoader cl) {
        //2、找到PathClassLoader父类的BaseDexClassLoader中的pathList
        Field pathListField = null;
        Object[] dexElements = null;
        try {

            pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
            if (!pathListField.isAccessible()) pathListField.setAccessible(true);
            Object pathList = pathListField.get(cl);

            //3、找到DexPathList的Element[] dexElements
            Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
            if (!dexElementsField.isAccessible()) dexElementsField.setAccessible(true);
            dexElements = (Object[]) dexElementsField.get(pathList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dexElements;

    }

    private void copyFile(File from, File to) {
        try {
            FileInputStream fis = new FileInputStream(from);
            FileOutputStream fos = new FileOutputStream(to);
            byte[] bf = new byte[1024 * 1024];
            int len = -1;
            while ((len = fis.read(bf)) != -1) {
                fos.write(bf, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
