package com.droidsmith.petri.injection.components

import android.app.Application
import com.droidsmith.petri.PetriApplication
import com.droidsmith.petri.injection.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule



@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        PetriApplicationModule::class,
        ActivityModule::class,
        FragmentModule::class
))
interface PetriApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): PetriApplicationComponent
    }

    abstract fun inject(app: PetriApplication)



}