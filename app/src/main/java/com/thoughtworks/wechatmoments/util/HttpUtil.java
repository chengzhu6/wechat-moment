package com.thoughtworks.wechatmoments.util;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpUtil {
    private final static OkHttpClient httpClient;

    static {
        httpClient = new OkHttpClient();
    }

    public static <T> T get(String url, Class<T> responseType) throws IOException {
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.url(url).build();
        try (
                Response response = httpClient.newCall(request).execute();
        ) {
            ResponseBody body = response.body();
            if (body != null) {
                return GsonUtil.fromString(body.string(), responseType);
            }
        }
        return null;
    }

    public static <T> ArrayList<T> getList(String url, Class<T> type) throws IOException {
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.url(url).build();
        try (
                Response response = httpClient.newCall(request).execute();
        ) {
            ResponseBody body = response.body();
            if (body != null) {
                return GsonUtil.jsonToList(body.string(), type);
            }
        }
        return null;
    }
}
