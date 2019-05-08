package com.example.processcommunicate.myokhttp.okhttp;

import java.util.HashMap;
import java.util.Map;

public class Request {

    Method method;
    String url;
    Map<String, String> head;
    RequestBody requestBody;

    public Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.head = builder.head;
        this.requestBody = builder.requestBody;
    }

    public static class Builder {

        Method method;
        String url;
        Map<String, String> head;
        RequestBody requestBody;

        public Builder() {
            //默认情况
            this.method = Method.GET;
            head = new HashMap<>();
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Request build() {
            return new Request(this);
        }

        public Builder get() {
            this.method = Method.GET;
            return this;
        }

        public Builder post(RequestBody body) {
            this.method = Method.POST;
            requestBody = body;
            return this;
        }

        public void header(String key, String value) {
            head.put(key, value);
        }

    }
}
