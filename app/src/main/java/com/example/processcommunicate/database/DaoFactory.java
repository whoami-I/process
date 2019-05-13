package com.example.processcommunicate.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

public class DaoFactory {
    SQLiteDatabase mSqLiteDatabase;
    private static DaoFactory mFactory;

    private DaoFactory() {
        File dbRoot = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator +
                "eassyjoke" + File.separator + "database");
        if (!dbRoot.exists()) {
            dbRoot.mkdirs();
        }
        File dbFile = new File(dbRoot, "data.db");

        mSqLiteDatabase = SQLiteDatabase
                .openOrCreateDatabase(dbFile, null);

    }

    public static DaoFactory getFactory() {
        if (mFactory == null) {
            synchronized (DaoFactory.class) {
                if (mFactory == null) {
                    mFactory = new DaoFactory();
                }
            }
        }
        return mFactory;

    }

    public <T> IDaoSupport<T> getDao(Class<T> clazz) {
        IDaoSupport<T> daoSupport = new DaoSupport<>(mSqLiteDatabase,clazz);
        return daoSupport;
    }
}
