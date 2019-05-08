package com.example.processcommunicate.myokhttp.okhttp;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RequestBody {
    public static final String FORM = "multipart/form-data";

    private String type;
    private Map<String, Object> params;
    private String boundary = createBoundary();
    private String startBoundary = "--" + boundary;
    private String endBoundary = startBoundary + "--";


    public RequestBody() {
        params = new HashMap<>();
    }

    public RequestBody type(String type) {
        this.type = type + "; boundary=" + boundary;
        return this;
    }

    private String createBoundary() {
        return "okHttp" + UUID.randomUUID().toString();
    }

    public String getContentType() {
        return type;
    }

    public long getContentLenght() {
        long length = 0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                String valueStr = (String) value;
                length += getText(key, valueStr).getBytes().length;
            }
        }

        if (params.size() != 0) {
            length += endBoundary.getBytes().length;
        }

        return length;
    }

    private String getText(String key, String value) {
        return startBoundary + "\r\n" +
                "Content-Disposition: form-data; name = \"" + key + "\"\r\n" +
                "Content-Type: text/plain\r\n" +
                "\r\n" +
                value +
                "\r\n";

    }

    public RequestBody addparams(String key, String value) {
        params.put(key, value);
        return this;
    }

    public void onWriteBody(OutputStream outputStream) throws IOException {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                String valueStr = (String) value;
                String postStr = getText(key, valueStr);
                outputStream.write(postStr.getBytes());
            }
        }

        if (params.size() != 0) {
            outputStream.write(endBoundary.getBytes());
        }
    }
}
