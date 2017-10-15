package com.droidsmith.petri.injection.modules

import android.app.Activity
import com.droidsmith.petri.injection.components.PetriActivityComponent
import com.droidsmith.petri.ui.petri.PetriActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class ActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(PetriActivity::class)
    abstract fun bindPetriActivity(builder: PetriActivityComponent.Builder): AndroidInjector.Factory<out Activity>

}