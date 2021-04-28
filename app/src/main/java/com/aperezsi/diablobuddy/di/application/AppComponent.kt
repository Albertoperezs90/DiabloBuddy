package com.aperezsi.diablobuddy.di.application

import android.app.Application
import com.aperezsi.diablobuddy.di.viewmodel.module.ViewModelModule
import com.aperezsi.diablobuddy.view.NavHostActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface AppComponent {

    fun inject(activity: NavHostActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }
}