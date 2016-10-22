package com.mars.blackcat.component;

import android.content.Context;

import com.mars.blackcat.api.BookApi;
import com.mars.blackcat.module.AppModule;
import com.mars.blackcat.module.BookApiModule;

import dagger.Component;

@Component(modules = {AppModule.class, BookApiModule.class})
public interface AppComponent {

    Context getContext();

    BookApi getReaderApi();

}