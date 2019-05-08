package com.example.processcommunicate.myokhttp.okhttp;

import android.text.InputType;

import java.io.IOException;
import java.io.InputStream;

public class Response {
    InputStream is;

    public Response(InputStream is) {
        this.is = is;

    }

    public InputStream getInputStream() {
        return is;
    }

    public String string() {
        InputStream input = is;
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        try {
            for (int n; (n = input.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
