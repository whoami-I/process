package com.example.processcommunicate.myokhttp.okhttp;

import android.widget.Switch;

public enum Method {
    POST("POST"), GET("GET"), HEAD("HEAD");
    String name;

    Method(String name) {
        this.name = name;
    }

    public boolean doOutput() {
        switch (this) {
            case POST:
                return true;
        }
        return false;
    }
}
