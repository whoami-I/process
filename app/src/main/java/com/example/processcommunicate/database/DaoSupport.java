package com.example.processcommunicate.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.ArrayMap;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class DaoSupport<T> implements IDaoSupport<T> {
    private static final String TAG = "DaoSupport";
    private SQLiteDatabase mSqLiteDatabase;
    private Class<T> mClazz;

    //缓存字段和put方法
    private Field[] mFields;
    private Map<String, Method> putMethods = new ArrayMap<>();

    public DaoSupport(SQLiteDatabase sqLiteDatabase, Class<T> clazz) {
        mSqLiteDatabase = sqLiteDatabase;
        mClazz = clazz;

        //创建表
        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists ")
                .append(mClazz.getSimpleName())
                .append(" (id integer primary key autoincrement, ");
        Field[] fields = mClazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            String type = field.getType().getSimpleName();
            sb.append(name).append(" ").append(type).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), ")");
        //执行创建表语句
        mSqLiteDatabase.execSQL(sb.toString());
        String createTableStr = sb.toString();
        Log.e(TAG, "创建表语句 --> " + createTableStr);
    }

    @Override
    public long insert(T obj) {
        //仍然使用传统的方法插入数据，只是根据反射来获取各个字段来获取
        ContentValues contentValues = contentValueByObj(obj);
        //插入数据
        return mSqLiteDatabase.insert(mClazz.getSimpleName(),
                null, contentValues);
    }

    public long insert(List<T> objs) {
        long i = 0;
        //使用事务，批量操作速度会大大加快
        mSqLiteDatabase.beginTransaction();
        for (T obj : objs) {

            i += insert(obj);
        }
        //mSqLiteDatabase.setTransactionSuccessful();
        mSqLiteDatabase.endTransaction();
        return i;
    }

    private ContentValues contentValueByObj(T obj) {
        ContentValues contentValues = new ContentValues();
        //对Field进行缓存
        Field[] fields = mFields;
        if (fields == null) {
            fields = mClazz.getDeclaredFields();
            mFields = fields;
        }
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();

            try {
                Object value = field.get(obj);
                String typeName = value.getClass().getName();

                Method putMethod = putMethods.get(typeName);
                //对方法的缓存
                if (putMethod == null) {
                    putMethod = ContentValues.class.getDeclaredMethod("put",
                            String.class, value.getClass());
                    putMethods.put(typeName, putMethod);
                }

                putMethod = ContentValues.class.getDeclaredMethod("put",
                        String.class, value.getClass());
                putMethod.invoke(contentValues, name, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        }

        return contentValues;


    }
}
