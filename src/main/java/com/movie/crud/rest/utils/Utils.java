package com.movie.crud.rest.utils;

public class Utils {
    public static <T> T ifNotNull(T data, T existingData) {
        return data == null ? existingData : data;
    }
}
