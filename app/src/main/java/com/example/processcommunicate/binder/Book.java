package com.example.processcommunicate.binder;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    String name;

    public Book(String name) {
        this.name = name;
    }

    public Book(Parcel p) {
        this.name = p.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {

        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
