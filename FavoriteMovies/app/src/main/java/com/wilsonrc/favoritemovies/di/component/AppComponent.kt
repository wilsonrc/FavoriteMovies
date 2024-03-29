package com.wilsonrc.favoritemovies.di.component

import com.wilsonrc.favoritemovies.base.BaseApp
import com.wilsonrc.favoritemovies.data.source.di.DataSourcesModule
import com.wilsonrc.favoritemovies.data.source.local.di.DatabaseModule
import com.wilsonrc.favoritemovies.data.source.remote.di.ApiServiceModule
import com.wilsonrc.favoritemovies.data.source.remote.di.NetworkModule
import com.wilsonrc.favoritemovies.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        AppBindingModule::class,
        ApiServiceModule::class,
        DatabaseModule::class,
        DataSourcesModule::class,
        NetworkModule::class]
)
interface AppComponent : AndroidInjector<BaseApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApp): Builder

        fun build(): AppComponent
    }

}