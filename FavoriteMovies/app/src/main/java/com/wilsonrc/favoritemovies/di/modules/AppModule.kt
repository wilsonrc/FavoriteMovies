package com.wilsonrc.favoritemovies.di.modules

import android.content.Context
import com.wilsonrc.favoritemovies.base.BaseApp
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    @JvmStatic
    internal fun provideContext(app: BaseApp): Context = app

}