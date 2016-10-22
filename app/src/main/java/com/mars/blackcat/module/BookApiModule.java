package com.mars.blackcat.module;

import com.mars.blackcat.api.BookApi;
import com.mars.blackcat.api.other.HeaderInterceptor;
import com.mars.blackcat.api.other.LoggingInterceptor;
import com.mars.blackcat.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class BookApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {

        LoggingInterceptor logging = new LoggingInterceptor(new MyLog());
        logging.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging);
        return builder.build();
    }

    @Provides
    protected BookApi provideBookService(OkHttpClient okHttpClient) {
        return BookApi.getInstance(okHttpClient);
    }

    public static class MyLog implements LoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            LogUtils.i("oklog: " + message);
        }
    }
}