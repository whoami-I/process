package com.example.processcommunicate.retrofit;

public class TestData {


    /**
     * code : 0000
     * data : {"gender":"male","username":"hahaha"}
     * msg : login success
     */

    private String code;
    private Data data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Data {
        /**
         * gender : male
         * username : hahaha
         */

        private String gender;
        private String username;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
