package com.example.processcommunicate.database;

import java.util.List;

public interface IDaoSupport<T> {

    public long insert(T t);

    public long insert(List<T> datas);
}
