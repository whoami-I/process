package com.example.processcommunicate.database;

public class DaoUtils {

    public static String getColumType(String type) {
        String value = null;
        if (type.equals("String")) {
            value = "text";
        } else if (type.equals("int")) {
            value = "integer";
        } else if (type.equals("boolean")) {
            value = "boolean";
        } else if (type.equals("float")) {
            value = "float";
        } else if (type.equals("double")) {
            value = "double";
        } else if (type.equals("char")) {
            value = "varchar";
        } else if (type.equals("long")) {
            value = "long";
        }
        return value;
    }
}
